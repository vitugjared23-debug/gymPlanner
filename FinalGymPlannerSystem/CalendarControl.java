
package com.mycompany.gymorig;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Map;

public class CalendarControl {


public static void renderCalendar(GymPlannerGUI gui, YearMonth currentYearMonth, JPanel calendarGridPanel, JLabel monthYearLabel, Map<LocalDate, ScheduledWorkout> scheduleMap) {
        calendarGridPanel.removeAll();
        monthYearLabel.setText(currentYearMonth.getMonth().name() + " " + currentYearMonth.getYear());

        LocalDate firstOfMonth = currentYearMonth.atDay(1);
        int dayOfWeekVal = firstOfMonth.getDayOfWeek().getValue() % 7;
        int daysInMonth = currentYearMonth.lengthOfMonth();

        for (int i = 0; i < dayOfWeekVal; i++) {
            JPanel empty = new JPanel();
            empty.setOpaque(false);
            calendarGridPanel.add(empty);
        }

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = currentYearMonth.atDay(day);
            JButton dateBtn = new JButton(String.valueOf(day));
            dateBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
            dateBtn.setFocusPainted(false);
            dateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            dateBtn.setOpaque(true);
            dateBtn.setContentAreaFilled(true);

            ScheduledWorkout sw = scheduleMap.get(date);
            if (sw != null) {
                if ("Completed".equalsIgnoreCase(sw.getStatus())) {
                    dateBtn.setBackground(UIHelpers.OK);
                } 
                else if (("Missed".equalsIgnoreCase(sw.getStatus()) || "Rescheduled".equalsIgnoreCase(sw.getStatus())) 
                           && !date.isAfter(java.time.LocalDate.now())) { 
                    dateBtn.setBackground(UIHelpers.BAD);
                }else {
                    dateBtn.setBackground(UIHelpers.ACCENT);
                }
                dateBtn.setForeground(Color.WHITE);
                dateBtn.setBorder(BorderFactory.createLineBorder(UIHelpers.LINE, 1));
            } else {
                dateBtn.setBackground(Color.WHITE);
                dateBtn.setForeground(UIHelpers.INK);
                dateBtn.setBorder(BorderFactory.createLineBorder(UIHelpers.LINE, 1));
            }

            dateBtn.addActionListener(e -> showWorkoutDialog(gui, date, scheduleMap));
            calendarGridPanel.add(dateBtn);
        }

        calendarGridPanel.revalidate();
        calendarGridPanel.repaint();
    }

    public static void showWorkoutDialog(GymPlannerGUI gui, LocalDate date, Map<LocalDate, ScheduledWorkout> scheduleMap) {
        ScheduledWorkout sw = scheduleMap.get(date);

        if (sw == null) {
            // block scheduling on past calendar dates
            if (date.isBefore(java.time.LocalDate.now())) {
                JOptionPane.showMessageDialog(gui, 
                    "This day has already passed! You cannot schedule workouts on historical rest days.", 
                    "Rest Day Locked", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String[] workoutOptions = {"Upper Body", "Lower Body", "Full Body"};
            
            //drop down selection panel directly to the user
            String selectedPlan = (String) JOptionPane.showInputDialog(
                gui,
                "This is a Rest Day. Select a workout routine to schedule on " + date + ":",
                "Schedule a New Workout",
                JOptionPane.QUESTION_MESSAGE,
                null,
                workoutOptions,
                workoutOptions
            );

            //if the user selects a plan and clicks OK
            if (selectedPlan != null) {
                // exercises from the system catalog based on selection
                ArrayList<Exercise> chosenExercises = new ArrayList<>();
                
                // Read ExerciseData library
                ExerciseData masterCatalog = new ExerciseData();
               
                // Difficulty steps up by a safe 5% after  6 COMPLETED WORKOUTS (every 2 weeks)
                int completedStages = gui.historyDays.size() / 6; 
                double progressionMultiplier = 1.0 + (completedStages * 0.05);
                 //get exercises from the catalog
                ArrayList<Exercise> randomizedCatalog = new ArrayList<>(masterCatalog.exercises);
                java.util.Collections.shuffle(randomizedCatalog);
        
                for (Exercise ex : randomizedCatalog) {
                    //stop looping if already have a rworkout set up
                    if (chosenExercises.size() >= 5) {
                        break;
                    }

                    if (ex.getTargetMuscle() != null) {
                        String muscle = ex.getTargetMuscle().toLowerCase();
                        
                        
                        
                        if ("Upper Body".equalsIgnoreCase(selectedPlan)) {
                            if (muscle.contains("chest") || muscle.contains("biceps") || 
                                muscle.contains("triceps") || muscle.contains("shoulders") || 
                                muscle.contains("back")) {
                                
                                 
                                ex.reps = (int)(ex.reps * progressionMultiplier);
                                ex.durationSeconds = (int)(ex.durationSeconds * progressionMultiplier);
                                chosenExercises.add(ex);
                            }
                        } 
                        else if ("Lower Body".equalsIgnoreCase(selectedPlan)) {
                            if (muscle.contains("legs") || muscle.contains("quads") || 
                                muscle.contains("hamstrings") || muscle.contains("calves") || 
                                muscle.contains("glutes")) {
                                // Directly scale the 
                                ex.reps = (int)(ex.reps * progressionMultiplier);
                                ex.durationSeconds = (int)(ex.durationSeconds * progressionMultiplier);
                                chosenExercises.add(ex);
                            }
                        } 
                        else if ("Full Body".equalsIgnoreCase(selectedPlan)) {
                            
                            ex.reps = (int)(ex.reps * progressionMultiplier);
                            ex.durationSeconds = (int)(ex.durationSeconds * progressionMultiplier);
                            chosenExercises.add(ex);
                        }
                    }
                }
                
                // if no exact string matches, grab the first 3 baseline workouts
                if (chosenExercises.isEmpty()) {
                    for (int i = 0; i < Math.min(3, masterCatalog.exercises.size()); i++) {
                        chosenExercises.add(masterCatalog.exercises.get(i));
                    }
                }

                //instantiate the new workout container and map it to the schedule database
                ScheduledWorkout newWorkout = new ScheduledWorkout(selectedPlan, "To Do", chosenExercises);
                scheduleMap.put(date, newWorkout);

                //update user's timeline log
                gui.activityLog.add(new String[]{
                    java.time.LocalDate.now().toString(),
                    selectedPlan,
                    "Manually scheduled for " + date
                });

                JOptionPane.showMessageDialog(gui, 
                    selectedPlan + " routine successfully added to " + date + "!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);

                gui.saveCurrentSchedule();
                gui.refreshAll();
            }
            return;
        }

        JDialog dialog = new JDialog(gui, "Workout: " + date + " (" + sw.getWorkoutType() + ")", true);
        dialog.setSize(600, 460);
        dialog.setLocationRelativeTo(gui);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel statusLbl = UIHelpers.label("Status: " + sw.getStatus(), 16, Font.BOLD, gui.INK);
        contentPanel.add(statusLbl);
        contentPanel.add(Box.createVerticalStrut(10));

        JLabel instructions = UIHelpers.label("Check off completed exercises:", 13, Font.PLAIN, gui.MUTE);
        contentPanel.add(instructions);
        contentPanel.add(Box.createVerticalStrut(8));

        JPanel exercisesPanel = new JPanel();
        exercisesPanel.setLayout(new BoxLayout(exercisesPanel, BoxLayout.Y_AXIS));
        exercisesPanel.setBackground(Color.WHITE);

        ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
        boolean isCompleted = "Completed".equalsIgnoreCase(sw.getStatus());

        for (Exercise ex : sw.getExercises()) {
            String target = ex.isTimeBased() ? ex.getDurationSeconds() + " sec" : ex.getSets() + " x " + ex.getReps();
            JCheckBox cb = new JCheckBox(ex.getExerciseName() + " (" + ex.getTargetMuscle() + " | " + target + " | Diff: " + ex.getBaseDifficulty() + ")");
           
             // only checks the box if the specific exercise name exists in your verified completed array list logs
            if (isCompleted && sw.getCompletedExerciseNames() != null && sw.getCompletedExerciseNames().contains(ex.getExerciseName())) {
                
                cb.setSelected(true);
            }
            
            cb.setFont(new Font("SansSerif", Font.PLAIN, 13));
            cb.setBackground(Color.WHITE);
            checkBoxes.add(cb);
            exercisesPanel.add(cb);
            exercisesPanel.add(Box.createVerticalStrut(4));
        }

        JScrollPane scrollPane = new JScrollPane(exercisesPanel);
        scrollPane.setBorder(null);
        contentPanel.add(scrollPane);

        dialog.add(contentPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        btnPanel.setBackground(UIHelpers.BG);

        JButton completeBtn = UIHelpers.button("Mark Completed", true);
        completeBtn.setBackground(UIHelpers.OK);
        JButton missedBtn = UIHelpers.button("Mark Missed", true);
        missedBtn.setBackground(UIHelpers.BAD);
        
        JButton rescheduleBtn = UIHelpers.button("Reschedule", false);
        JButton deleteBtn = UIHelpers.button("Delete Workout", false);
        deleteBtn.setBackground(new Color(0));
        deleteBtn.setForeground(Color.WHITE);
      
        // Locks the "Mark Completed/ MISSED" option if the day hasn'thappened yet
        if (date.isAfter(java.time.LocalDate.now())) {
            completeBtn.setEnabled(false);
            completeBtn.setToolTipText("You cannot complete a workout before the scheduled date arrives.");
        
            missedBtn.setEnabled(false);
            missedBtn.setToolTipText("You cannot mark a workout as missed before the scheduled date arrives.");
        }else {
            deleteBtn.setEnabled(false);
            deleteBtn.setToolTipText("You cannot delete a historical workout record from a past date.");
        }
        
        btnPanel.add(completeBtn);
        btnPanel.add(missedBtn);
        
        btnPanel.add(rescheduleBtn); 
        btnPanel.add(deleteBtn); 
        dialog.add(btnPanel, BorderLayout.SOUTH);
        
        deleteBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(dialog,
                "Are you sure you want to delete this upcoming workout and return this day to a Rest Day?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
                
            if (confirm == JOptionPane.YES_OPTION) {
                scheduleMap.remove(date);
                
                gui.activityLog.add(new String[]{
                    java.time.LocalDate.now().toString(),
                    sw.getWorkoutType(),
                    "Deleted workout from " + date
                });

                JOptionPane.showMessageDialog(dialog, "Workout cleared successfully.", "Cleared", JOptionPane.INFORMATION_MESSAGE);
                gui.saveCurrentSchedule();
                gui.refreshAll();
                dialog.dispose();
            }
        });
        
        rescheduleBtn.addActionListener(e -> {
            String inputDay = JOptionPane.showInputDialog(dialog, 
                "Enter new day number (1-" + date.lengthOfMonth() + "):", 
                "Reschedule Workout", 
                JOptionPane.QUESTION_MESSAGE);
                
            if (inputDay != null && !inputDay.trim().isEmpty()) {
                try {
                    int dayNumber = Integer.parseInt(inputDay.trim());
                    
                    //build the complete date
                    java.time.LocalDate newDate = java.time.LocalDate.of(date.getYear(), date.getMonthValue(), dayNumber);
                    // blocks the transaction if the user selects a day past to today
                    if (newDate.isBefore(java.time.LocalDate.now())) {
                        JOptionPane.showMessageDialog(dialog, 
                    "Scheduling Violation: You cannot reschedule workouts onto a passed date", 
                     "Validation Error", 
                 JOptionPane.ERROR_MESSAGE);
                        return; 
                    }
                    
                    if (scheduleMap.containsKey(newDate)) {
                        JOptionPane.showMessageDialog(dialog, 
                            "There is already a workout scheduled on " + newDate, 
                            "Date Conflict", 
                            JOptionPane.WARNING_MESSAGE);
                    } else {
                        ScheduledWorkout movingWorkout = scheduleMap.remove(date);
                        movingWorkout.setStatus("Rescheduled");
                        scheduleMap.put(newDate, movingWorkout);
                        
                        gui.activityLog.add(new String[]{
                            java.time.LocalDate.now().toString(), 
                            movingWorkout.getWorkoutType(), 
                            "Rescheduled to " + newDate
                        });
                        
                        JOptionPane.showMessageDialog(dialog, 
                            "Workout successfully moved to " + newDate, 
                            "Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                            
                        gui.saveCurrentSchedule();
                        gui.refreshAll();
                        dialog.dispose();
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(dialog, 
                        "Please enter a valid number character!", 
                        "Input Error", 
                        JOptionPane.ERROR_MESSAGE);
                } catch (java.time.DateTimeException dte) {
                    JOptionPane.showMessageDialog(dialog, 
                        "Invalid day number for this specific month!", 
                        "Date Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        dialog.add(btnPanel, BorderLayout.SOUTH);

        completeBtn.addActionListener(e -> {
            sw.setStatus("Completed");
            
            // Collect only the checked exercises
            ArrayList<String> checkedNames = new ArrayList<>();
            StringBuilder historyTextBuilder = new StringBuilder();
            
            for (int i = 0; i < checkBoxes.size(); i++) {
                if (checkBoxes.get(i).isSelected()) {
                    String cleanName = sw.getExercises().get(i).getExerciseName();
                    checkedNames.add(cleanName);
                    
                    // Build a comma separated list of actual performance checkmarks
                    if (historyTextBuilder.length() > 0) historyTextBuilder.append(", ");
                    historyTextBuilder.append(cleanName);
                }
            }
            sw.setCompletedExerciseNames(checkedNames);

            //if the user left all boxes blank, default cleanly to an isolated notice string
            String customHistoryDisplay = historyTextBuilder.length() > 0 ? historyTextBuilder.toString() : "No exercises selected";

            // Replaces the generic text string with your live checkbox selection properties
            gui.historyDays.push(date.toString() + " [" + sw.getWorkoutType() + "]: " + customHistoryDisplay);
            
            gui.updateActivityLogStatus(date.toString(), sw.getWorkoutType(), "Completed");

            gui.saveCurrentSchedule();
            gui.refreshAll();
            dialog.dispose();
        });

        missedBtn.addActionListener(e -> {
          
            sw.setStatus("Missed");
            gui.updateActivityLogStatus(date.toString(), sw.getWorkoutType(), "Missed");
            
            //Ask the user if they want to schedule a preferable makeup day or just leave it
            String[] options = {"Choose a Makeup Day", "Just Leave as Missed"};
            int choice = JOptionPane.showOptionDialog(dialog,
                "Workout marked as missed. What would you like to do next?",
                "Missed Workout Options",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
            
            //  User wants to pick their own makeup day number
            if (choice == JOptionPane.YES_OPTION) {
                String inputDay = JOptionPane.showInputDialog(dialog, 
                    "Enter your preferred day number for the makeup routine (1-" + date.lengthOfMonth() + "):", 
                    "Schedule Makeup Workout", 
                    JOptionPane.QUESTION_MESSAGE);
                    
                if (inputDay != null && !inputDay.trim().isEmpty()) {
                    try {
                        int dayNumber = Integer.parseInt(inputDay.trim());
                        java.time.LocalDate newDate = java.time.LocalDate.of(date.getYear(), date.getMonthValue(), dayNumber);
                        
                        // Enforce system rules: Check for date conflicts
                        if (scheduleMap.containsKey(newDate)) {
                            JOptionPane.showMessageDialog(dialog, 
                                "There is already a workout scheduled on " + newDate + ". It will remain marked as missed without a duplicate slot.", 
                                "Date Conflict", 
                                JOptionPane.WARNING_MESSAGE);
                        } else {
                            // Create the fresh makeup routine clone on their preferred date
                            ScheduledWorkout movedWorkout = new ScheduledWorkout(sw.getWorkoutType(), "To Do", sw.getExercises());
                            scheduleMap.put(newDate, movedWorkout);
                            gui.activityLog.add(new String[]{newDate.toString(), sw.getWorkoutType(), "Rescheduled Makeup"});
                            
                            JOptionPane.showMessageDialog(dialog, 
                                "Makeup routine successfully scheduled for your preferred day: " + newDate, 
                                "Success", 
                                JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dialog, 
                            "Invalid input! The workout will stay marked as missed without scheduling a makeup day.", 
                            "Input Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                // 3. Scenario B: User selected "Just Leave as Missed"
                JOptionPane.showMessageDialog(dialog, 
                    "Workout tracking saved as missed. No future makeup session was created.", 
                    "Info", 
                    JOptionPane.INFORMATION_MESSAGE);
            }

            gui.saveCurrentSchedule();
            gui.refreshAll();
            dialog.dispose();
        });

        dialog.setVisible(true);
    }
     private static void updateActivityLogStatus(ArrayList<String[]> activityLog, String dateStr, String workoutType, String newStatus) {
        for (String[] row : activityLog) {
            if (row[0].equals(dateStr) && row[1].equals(workoutType)) {
                row[2] = newStatus;
                return;
            }
        }
        activityLog.add(new String[]{dateStr, workoutType, newStatus});
    }
}
