
package com.mycompany.gymorig;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class GymPlannerGUI extends JFrame {

    // ---------- Look & Theme Colors ----------
    static final Color ACCENT = new Color(0x2A55F5);
    static final Color BG = new Color(0xE9EDEE);
    static final Color INK = new Color(0x162023);
    static final Color MUTE = new Color(0x5B6B70);
    static final Color LINE = new Color(0xCBD4D6);
    static final Color OK = new Color(0x127A4B);
    static final Color BAD = new Color(0xC2362B);

    static final String[] GOALS = {"Lean", "Gain Muscle", "Weight Loss", "Maintenance"};

    // ---------- Active Session State ----------
    private final Hashtable<String, FirstStep.Account> accounts = new Hashtable<>();
    private FirstStep.Account currentAccount;
    private WeeklyWorkoutPlan currentPlan;
    
    // Schedule Mapping and Activity Tracking
    private Map<LocalDate, ScheduledWorkout> scheduleMap = new HashMap<>();
    private Deque<String> historyDays = new ArrayDeque<>();
    private ArrayList<String[]> activityLog = new ArrayList<>(); // {date, type, status}

    // Calendar Grid State
    private YearMonth currentYearMonth = YearMonth.now();
    private JPanel calendarGridPanel = new JPanel(new GridLayout(0, 7, 8, 8));
    private JLabel monthYearLabel = new JLabel("", SwingConstants.CENTER);

    // ---------- UI Components ----------
    private final CardLayout cards = new CardLayout();
    private final JPanel root = new JPanel(cards);

    private boolean signupMode = false;
    private JLabel authSub, authErr, rulesLabel;
    private JTextField emailField, heightField, weightField;
    private JPasswordField passField;
    private JComboBox<String> goalBox;
    private JPanel signupExtras;
    private JButton authGo, authSwitch;

    private JLabel greeting, statDone, statRate, statBmi, statMissed;
    private JLabel pHeight, pWeight, pBody, pGoal, pBmi;
    private BmiBar bmiBar;
    private DefaultListModel<String> histModel;
    private DefaultTableModel actModel;

    public GymPlannerGUI() {
        super("Gym Planner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 750);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);

        // Load existing accounts from CSV on startup
        DataManager.loadAccountsFromCSV(accounts);

        root.add(buildAuth(), "auth");
        root.add(buildDashboard(), "dash");
        setContentPane(root);
        cards.show(root, "auth");
    }

    // Initialize schedule for the active account
    private void initUserPlan() {
        if (currentAccount == null || currentAccount.profile == null) return;
        currentPlan = new WeeklyWorkoutPlan(currentAccount.profile);
        
        historyDays.clear();
        activityLog.clear();
        scheduleMap.clear();

        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(java.time.DayOfWeek.MONDAY);

        WorkoutSet[] initialSets = {currentPlan.mondayWorkout, currentPlan.wednesdayWorkout, currentPlan.fridayWorkout};
        int[] dayOffsets = {0, 2, 4}; // Monday, Wednesday, Friday

        for (int k = 0; k < dayOffsets.length; k++) {
            LocalDate scheduledDate = monday.plusDays(dayOffsets[k]);
            scheduleMap.put(scheduledDate, new ScheduledWorkout(
                initialSets[k].getWorkoutType(), "To Do", initialSets[k].exercises));
            
            activityLog.add(new String[]{scheduledDate.toString(), initialSets[k].getWorkoutType(), "To Do"});
        }

        // Load user's schedule from CSV if available
        DataManager.loadScheduleFromCSV(currentAccount, scheduleMap, new WorkoutHistoryStack());
        syncActivityLogFromSchedule();
    }

    private void saveCurrentSchedule() {
        if (currentAccount != null) {
            DataManager.saveScheduleToCSV(currentAccount, scheduleMap);
        }
    }

    private void syncActivityLogFromSchedule() {
        activityLog.clear();
        for (Map.Entry<LocalDate, ScheduledWorkout> entry : scheduleMap.entrySet()) {
            activityLog.add(new String[]{entry.getKey().toString(), entry.getValue().getWorkoutType(), entry.getValue().getStatus()});
        }
    }

    // =====================================================
    // UI HELPERS
    // =====================================================
    private static JButton button(String text, boolean primary) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.BOLD, 14));
        b.setFocusPainted(true);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (primary) {
            b.setBackground(ACCENT);
            b.setForeground(Color.WHITE);
        } else {
            b.setBackground(Color.WHITE);
            b.setForeground(INK);
            b.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(LINE, 2),
                    BorderFactory.createEmptyBorder(6, 14, 6, 14)));
        }
        return b;
    }

    private static JLabel label(String text, int size, int style, Color c) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", style, size));
        l.setForeground(c);
        return l;
    }

    private static <T extends JComponent> T left(T c) {
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
        return c;
    }

    private static <T extends JComponent> T wide(T c, int h) {
        c.setMaximumSize(new Dimension(Integer.MAX_VALUE, h));
        return left(c);
    }

    private static JPanel card() {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE, 2),
                BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        return p;
    }

    private static boolean[] passwordRules(String p) {
        return new boolean[]{
            p.length() >= 8,
            p.matches(".*[A-Z].*"),
            p.matches(".*[a-z].*"),
            p.matches(".*[0-9].*"),
            p.matches(".*[^a-zA-Z0-9].*")
        };
    }

    // =====================================================
    // AUTHENTICATION PANEL
    // =====================================================
    private JPanel buildAuth() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(BG);

        JPanel form = card();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        form.add(left(label("GYM PLANNER", 40, Font.BOLD, INK)));
        authSub = left(label(" ", 14, Font.PLAIN, MUTE));
        form.add(authSub);
        form.add(Box.createVerticalStrut(14));

        form.add(left(label("Email", 13, Font.BOLD, INK)));
        emailField = wide(new JTextField(22), 34);
        form.add(emailField);
        form.add(Box.createVerticalStrut(10));

        form.add(left(label("Password", 13, Font.BOLD, INK)));
        passField = wide(new JPasswordField(22), 34);
        form.add(passField);

        signupExtras = new JPanel();
        signupExtras.setLayout(new BoxLayout(signupExtras, BoxLayout.Y_AXIS));
        signupExtras.setOpaque(false);
        left(signupExtras);

        rulesLabel = left(label(" ", 12, Font.PLAIN, MUTE));
        signupExtras.add(Box.createVerticalStrut(4));
        signupExtras.add(rulesLabel);
        signupExtras.add(Box.createVerticalStrut(10));
        signupExtras.add(left(label("Height (meters)", 13, Font.BOLD, INK)));
        heightField = wide(new JTextField(22), 34);
        signupExtras.add(heightField);
        signupExtras.add(Box.createVerticalStrut(10));
        signupExtras.add(left(label("Weight (kg)", 13, Font.BOLD, INK)));
        weightField = wide(new JTextField(22), 34);
        signupExtras.add(weightField);
        signupExtras.add(Box.createVerticalStrut(10));
        signupExtras.add(left(label("Body goal", 13, Font.BOLD, INK)));
        goalBox = wide(new JComboBox<String>(GOALS), 34);
        signupExtras.add(goalBox);
        form.add(signupExtras);

        authErr = left(label(" ", 13, Font.PLAIN, BAD));
        form.add(Box.createVerticalStrut(10));
        form.add(authErr);
        form.add(Box.createVerticalStrut(8));

        authGo = wide(button("Log in", true), 42);
        form.add(authGo);
        form.add(Box.createVerticalStrut(10));
        authSwitch = left(button("Sign up", false));
        form.add(authSwitch);

        passField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { updateRules(); }
            public void removeUpdate(DocumentEvent e) { updateRules(); }
            public void changedUpdate(DocumentEvent e) { updateRules(); }
        });

        authGo.addActionListener(e -> submitAuth());
        passField.addActionListener(e -> submitAuth());
        authSwitch.addActionListener(e -> {
            signupMode = !signupMode;
            applyAuthMode();
        });

        outer.add(form);
        applyAuthMode();
        return outer;
    }

    private void applyAuthMode() {
        signupExtras.setVisible(signupMode);
        authGo.setText(signupMode ? "Create account" : "Log in");
        authSwitch.setText(signupMode ? "I have an account" : "Create an account");
        authSub.setText(signupMode
                ? "Get a week built around your body and goal."
                : "Log in to see this week's workouts.");
        authErr.setText(" ");
        updateRules();
        root.revalidate();
        root.repaint();
    }

    private void updateRules() {
        String[] names = {"8+ characters", "Uppercase letter", "Lowercase letter", "A number", "A special character"};
        boolean[] ok = passwordRules(new String(passField.getPassword()));
        StringBuilder sb = new StringBuilder("<html>");
        for (int i = 0; i < names.length; i++) {
            sb.append(ok[i] ? "<font color='#127A4B'>&#10003; " + names[i] + "</font>" : names[i]);
            if (i < names.length - 1) sb.append(" &nbsp;|&nbsp; ");
        }
        rulesLabel.setText(sb.append("</html>").toString());
    }

    private void submitAuth() {
        String email = emailField.getText().trim();
        String pass = new String(passField.getPassword());

        if (email.isEmpty()) {
            authErr.setText("Enter your email.");
            return;
        }

        if (signupMode) {
            if (accounts.containsKey(email)) {
                authErr.setText("An account with this email already exists.");
                return;
            }
            for (boolean b : passwordRules(pass)) {
                if (!b) {
                    authErr.setText("Password doesn't meet all the rules above.");
                    return;
                }
            }
            float h, w;
            try {
                h = Float.parseFloat(heightField.getText().trim());
                w = Float.parseFloat(weightField.getText().trim());
            } catch (NumberFormatException ex) {
                authErr.setText("Height and weight must be numbers.");
                return;
            }
            if (h < 0.5f || h > 2.5f || w < 20f || w > 400f) {
                authErr.setText("Enter height in meters (e.g. 1.75) and weight in kg.");
                return;
            }
            BMICalculator calc = new BMICalculator();
            double bmi = calc.calculateBMI(w, h);
            Profile profile = new Profile(h, w, bmi, calc.getBodyType(bmi), (String) goalBox.getSelectedItem());
            
            FirstStep.Account acc = new FirstStep.Account(email, pass, profile);
            accounts.put(email, acc);
            currentAccount = acc;

            DataManager.saveAccountsToCSV(accounts);
        } else {
            FirstStep.Account acc = accounts.get(email);
            if (acc == null) {
                authErr.setText("No account found for that email.");
                return;
            }
            if (!acc.pass.equals(pass)) {
                authErr.setText("Wrong password.");
                return;
            }
            currentAccount = acc;
        }

        initUserPlan();
        passField.setText("");
        authErr.setText(" ");
        refreshAll();
        cards.show(root, "dash");
    }

    // =====================================================
    // DASHBOARD PANEL
    // =====================================================
    private JPanel buildDashboard() {
        JPanel p = new JPanel(new BorderLayout(0, 14));
        p.setBackground(BG);
        p.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));

        // Header
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        greeting = label("Hi", 34, Font.BOLD, INK);
        JButton logout = button("Log out", false);
        logout.addActionListener(e -> {
            currentAccount = null;
            signupMode = false;
            applyAuthMode();
            cards.show(root, "auth");
        });
        top.add(greeting, BorderLayout.WEST);
        top.add(logout, BorderLayout.EAST);

        // Quick Stats Header
        JPanel stats = new JPanel(new GridLayout(1, 4, 12, 0));
        stats.setOpaque(false);
        statDone = label("0/0", 30, Font.BOLD, INK);
        statRate = label("0%", 30, Font.BOLD, INK);
        statBmi = label("0", 30, Font.BOLD, INK);
        statMissed = label("0", 30, Font.BOLD, INK);
        stats.add(statCard(statDone, "Workouts completed"));
        stats.add(statCard(statRate, "Completion rate"));
        stats.add(statCard(statBmi, "BMI"));
        stats.add(statCard(statMissed, "Missed / Rescheduled"));

        JPanel north = new JPanel(new BorderLayout(0, 14));
        north.setOpaque(false);
        north.add(top, BorderLayout.NORTH);
        north.add(stats, BorderLayout.CENTER);

        // Navigation Tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("SansSerif", Font.BOLD, 14));
        tabs.addTab("Calendar schedule", buildCalendarTab());
        tabs.addTab("Profile", buildProfileTab());
        tabs.addTab("History", buildHistoryTab());
        tabs.addTab("Activity", buildActivityTab());

        p.add(north, BorderLayout.NORTH);
        p.add(tabs, BorderLayout.CENTER);
        return p;
    }

    private JPanel statCard(JLabel big, String caption) {
        JPanel c = card();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        left(big);
        c.add(big);
        c.add(left(label(caption, 12, Font.PLAIN, MUTE)));
        return c;
    }

    // =====================================================
    // TAB 1: MONTHLY CALENDAR GRID
    // =====================================================
    private JPanel buildCalendarTab() {
        JPanel calendarTab = new JPanel(new BorderLayout(10, 10));
        calendarTab.setBackground(BG);
        calendarTab.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));

        // Month Navigation
        JPanel monthNavPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        monthNavPanel.setOpaque(false);
        JButton prevBtn = button("<", false);
        JButton nextBtn = button(">", false);
        monthYearLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        monthYearLabel.setForeground(INK);

        prevBtn.addActionListener(e -> {
            currentYearMonth = currentYearMonth.minusMonths(1);
            renderCalendar();
        });
        nextBtn.addActionListener(e -> {
            currentYearMonth = currentYearMonth.plusMonths(1);
            renderCalendar();
        });

        monthNavPanel.add(prevBtn);
        monthNavPanel.add(monthYearLabel);
        monthNavPanel.add(nextBtn);
        calendarTab.add(monthNavPanel, BorderLayout.NORTH);

        // Day Headers
        JPanel daysHeader = new JPanel(new GridLayout(1, 7, 8, 0));
        daysHeader.setOpaque(false);
        String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : dayNames) {
            JLabel lbl = new JLabel(day, SwingConstants.CENTER);
            lbl.setFont(new Font("SansSerif", Font.BOLD, 13));
            lbl.setForeground(INK);
            daysHeader.add(lbl);
        }

        calendarGridPanel.setOpaque(false);

        JPanel centerCalPanel = card();
        centerCalPanel.setLayout(new BorderLayout(0, 10));
        centerCalPanel.add(daysHeader, BorderLayout.NORTH);
        centerCalPanel.add(calendarGridPanel, BorderLayout.CENTER);

        // Legend
        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        legendPanel.setOpaque(false);
        legendPanel.add(createLegendItem(ACCENT, "To Do"));
        legendPanel.add(createLegendItem(OK, "Completed"));
        legendPanel.add(createLegendItem(BAD, "Missed / Rescheduled"));

        calendarTab.add(centerCalPanel, BorderLayout.CENTER);
        calendarTab.add(legendPanel, BorderLayout.SOUTH);

        return calendarTab;
    }

    private JPanel createLegendItem(Color color, String labelText) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        panel.setOpaque(false);

        JPanel box = new JPanel();
        box.setPreferredSize(new Dimension(14, 14));
        box.setBackground(color);

        JLabel label = new JLabel(labelText);
        label.setForeground(INK);
        label.setFont(new Font("SansSerif", Font.PLAIN, 12));

        panel.add(box);
        panel.add(label);
        return panel;
    }

    private void renderCalendar() {
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
                    dateBtn.setBackground(OK);
                } else if ("Missed".equalsIgnoreCase(sw.getStatus()) || "Rescheduled".equalsIgnoreCase(sw.getStatus())) {
                    dateBtn.setBackground(BAD);
                } else {
                    dateBtn.setBackground(ACCENT);
                }
                dateBtn.setForeground(Color.WHITE);
                dateBtn.setBorder(BorderFactory.createLineBorder(LINE, 1));
            } else {
                dateBtn.setBackground(Color.WHITE);
                dateBtn.setForeground(INK);
                dateBtn.setBorder(BorderFactory.createLineBorder(LINE, 1));
            }

            dateBtn.addActionListener(e -> showWorkoutDialog(date));
            calendarGridPanel.add(dateBtn);
        }

        calendarGridPanel.revalidate();
        calendarGridPanel.repaint();
    }

    private void showWorkoutDialog(LocalDate date) {
        ScheduledWorkout sw = scheduleMap.get(date);

        if (sw == null) {
            JOptionPane.showMessageDialog(this, "Rest Day. No workouts scheduled for " + date, "Schedule Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(this, "Workout: " + date + " (" + sw.getWorkoutType() + ")", true);
        dialog.setSize(520, 460);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel statusLbl = label("Status: " + sw.getStatus(), 16, Font.BOLD, INK);
        contentPanel.add(statusLbl);
        contentPanel.add(Box.createVerticalStrut(10));

        JLabel instructions = label("Check off completed exercises:", 13, Font.PLAIN, MUTE);
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
            
            // If already completed, check it if it was previously saved as completed
            if (isCompleted && (sw.getCompletedExerciseNames().isEmpty() || sw.getCompletedExerciseNames().contains(ex.getExerciseName()))) {
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
        btnPanel.setBackground(BG);

        JButton completeBtn = button("Mark Completed", true);
        completeBtn.setBackground(OK);
        JButton missedBtn = button("Mark Missed", true);
        missedBtn.setBackground(BAD);

        btnPanel.add(completeBtn);
        btnPanel.add(missedBtn);
        dialog.add(btnPanel, BorderLayout.SOUTH);

        completeBtn.addActionListener(e -> {
            sw.setStatus("Completed");
            
            // Collect only the checked exercises
            ArrayList<String> checkedNames = new ArrayList<>();
            for (int i = 0; i < checkBoxes.size(); i++) {
                if (checkBoxes.get(i).isSelected()) {
                    checkedNames.add(sw.getExercises().get(i).getExerciseName());
                }
            }
            sw.setCompletedExerciseNames(checkedNames);

            historyDays.push(date.toString() + " (" + sw.getWorkoutType() + ")");
            updateActivityLogStatus(date.toString(), sw.getWorkoutType(), "Completed");

            saveCurrentSchedule();
            refreshAll();
            dialog.dispose();
        });

        missedBtn.addActionListener(e -> {
            sw.setStatus("Missed");
            updateActivityLogStatus(date.toString(), sw.getWorkoutType(), "Missed");

            LocalDate nextAvailable = date.plusDays(1);
            while (scheduleMap.containsKey(nextAvailable)) {
                nextAvailable = nextAvailable.plusDays(1);
            }

            ScheduledWorkout movedWorkout = new ScheduledWorkout(sw.getWorkoutType(), "To Do", sw.getExercises());
            scheduleMap.put(nextAvailable, movedWorkout);
            activityLog.add(new String[]{nextAvailable.toString(), sw.getWorkoutType(), "Rescheduled"});

            JOptionPane.showMessageDialog(dialog, "Workout marked missed. Moved to " + nextAvailable, "Rescheduled", JOptionPane.INFORMATION_MESSAGE);

            saveCurrentSchedule();
            refreshAll();
            dialog.dispose();
        });

        dialog.setVisible(true);
    }

    private void updateActivityLogStatus(String dateStr, String workoutType, String newStatus) {
        for (String[] row : activityLog) {
            if (row[0].equals(dateStr) && row[1].equals(workoutType)) {
                row[2] = newStatus;
                return;
            }
        }
        // If not found, add new
        activityLog.add(new String[]{dateStr, workoutType, newStatus});
    }

    // =====================================================
    // TAB 2: PROFILE
    // =====================================================
    private JPanel buildProfileTab() {
        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setBackground(BG);
        wrap.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));

        JPanel c = card();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.add(left(label("Your profile", 26, Font.BOLD, INK)));
        c.add(Box.createVerticalStrut(12));

        pHeight = left(label(" ", 16, Font.PLAIN, INK));
        pWeight = left(label(" ", 16, Font.PLAIN, INK));
        pBody = left(label(" ", 16, Font.PLAIN, INK));
        pGoal = left(label(" ", 16, Font.PLAIN, INK));
        for (JLabel l : new JLabel[]{pHeight, pWeight, pBody, pGoal}) {
            c.add(l);
            c.add(Box.createVerticalStrut(8));
        }

        c.add(Box.createVerticalStrut(10));
        bmiBar = wide(new BmiBar(), 34);
        c.add(bmiBar);
        pBmi = left(label(" ", 13, Font.PLAIN, MUTE));
        c.add(Box.createVerticalStrut(6));
        c.add(pBmi);

        wrap.add(c, BorderLayout.NORTH);
        return wrap;
    }

    static class BmiBar extends JComponent {
        double bmi = 22;

        BmiBar() { setPreferredSize(new Dimension(300, 30)); }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), y = 10, h = 10;
            double[] cut = {0, 0.18, 0.45, 0.70, 1.0};
            Color[] col = {new Color(0x5AA0D6), new Color(0x4FB286), new Color(0xE0B13C), new Color(0xD4553F)};
            for (int i = 0; i < col.length; i++) {
                g2.setColor(col[i]);
                int x1 = (int) (w * cut[i]), x2 = (int) (w * cut[i + 1]);
                g2.fillRect(x1, y, x2 - x1, h);
            }
            double pos = Math.max(0, Math.min(1, (bmi - 14) / 22.0));
            int mx = (int) (pos * (w - 4));
            g2.setColor(INK);
            g2.fillRoundRect(mx, 4, 4, 22, 3, 3);
            g2.dispose();
        }
    }

    // =====================================================
    // TAB 3: HISTORY
    // =====================================================
    private JPanel buildHistoryTab() {
        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setBackground(BG);
        wrap.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));

        JPanel c = card();
        c.setLayout(new BorderLayout(0, 10));
        c.add(label("Workout history", 26, Font.BOLD, INK), BorderLayout.NORTH);
        histModel = new DefaultListModel<>();
        JList<String> list = new JList<>(histModel);
        list.setFont(new Font("SansSerif", Font.PLAIN, 15));
        list.setFixedCellHeight(34);
        c.add(new JScrollPane(list), BorderLayout.CENTER);
        wrap.add(c, BorderLayout.CENTER);
        return wrap;
    }

    // =====================================================
    // TAB 4: ACTIVITY LOG
    // =====================================================
    private JPanel buildActivityTab() {
        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setBackground(BG);
        wrap.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));

        JPanel c = card();
        c.setLayout(new BorderLayout(0, 10));
        c.add(label("Workout activity", 26, Font.BOLD, INK), BorderLayout.NORTH);

        actModel = new DefaultTableModel(new String[]{"Date", "Workout", "Status"}, 0) {
            @Override public boolean isCellEditable(int r, int col) { return false; }
        };
        JTable t = new JTable(actModel);
        t.setRowHeight(32);
        t.setFont(new Font("SansSerif", Font.PLAIN, 14));
        t.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        t.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tb, Object v, boolean s, boolean f, int r, int col) {
                Component comp = super.getTableCellRendererComponent(tb, v, s, f, r, col);
                String st = String.valueOf(v);
                if (!s) {
                    comp.setForeground("Completed".equalsIgnoreCase(st) ? OK : "Missed".equalsIgnoreCase(st) ? BAD : MUTE);
                }
                comp.setFont(comp.getFont().deriveFont(Font.BOLD));
                return comp;
            }
        });
        c.add(new JScrollPane(t), BorderLayout.CENTER);
        wrap.add(c, BorderLayout.CENTER);
        return wrap;
    }

    // =====================================================
    // STATE REFRESH
    // =====================================================
    private void refreshAll() {
        if (currentAccount == null || currentAccount.profile == null) return;
        Profile p = currentAccount.profile;

        greeting.setText("Hi, " + currentAccount.email.split("@")[0]);

        int planned = scheduleMap.size();
        int done = 0;
        int missed = 0;

        for (ScheduledWorkout sw : scheduleMap.values()) {
            if ("Completed".equalsIgnoreCase(sw.getStatus())) done++;
            else if ("Missed".equalsIgnoreCase(sw.getStatus())) missed++;
        }

        statDone.setText(done + "/" + planned);
        statRate.setText((planned == 0 ? 0 : Math.round(done * 100f / planned)) + "%");
        statBmi.setText(String.format("%.1f", p.bmi));
        statMissed.setText(String.valueOf(missed));

        renderCalendar();

        pHeight.setText("Height:  " + p.height + " m");
        pWeight.setText("Weight:  " + p.weight + " kg");
        pBody.setText("Body type:  " + p.bodyType);
        pGoal.setText("Goal:  " + p.bodyGoal);
        pBmi.setText(String.format("BMI %.1f. Underweight below 18.5, normal to 25, overweight to 30.", p.bmi));
        bmiBar.bmi = p.bmi;
        bmiBar.repaint();

        histModel.clear();
        boolean hasCompleted = false;
        for (ScheduledWorkout sw : scheduleMap.values()) {
            if ("Completed".equalsIgnoreCase(sw.getStatus())) {
                hasCompleted = true;
                break;
            }
        }

        if (!hasCompleted) {
            histModel.addElement("Nothing yet. Completed workouts appear here, newest first.");
        } else {
            for (Map.Entry<LocalDate, ScheduledWorkout> entry : scheduleMap.entrySet()) {
                ScheduledWorkout sw = entry.getValue();
                if ("Completed".equalsIgnoreCase(sw.getStatus())) {
                    String exercisesStr = sw.getCompletedExerciseNames().isEmpty() 
                        ? "All exercises" 
                        : String.join(", ", sw.getCompletedExerciseNames());
                    histModel.addElement(entry.getKey() + " [" + sw.getWorkoutType() + "]: " + exercisesStr);
                }
            }
        }

        actModel.setRowCount(0);
        for (String[] r : activityLog) actModel.addRow(new Object[]{r[0], r[1], r[2]});
        
    }

    // =====================================================
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new GymPlannerGUI().setVisible(true));
    }
}