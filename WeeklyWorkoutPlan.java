package com.mycompany.gymorig;

public class WeeklyWorkoutPlan {

    // Made fields public for GUI access
    public WorkoutSet mondayWorkout;
    public WorkoutSet wednesdayWorkout;
    public WorkoutSet fridayWorkout;

    public WeeklyWorkoutPlan(Profile profile) {
        this.mondayWorkout = new WorkoutSet("Upper", profile);
        this.wednesdayWorkout = new WorkoutSet("Lower", profile);
        this.fridayWorkout = new WorkoutSet("Full Body", profile);
    }

    public void displayWeeklyPlan() {
        System.out.println("--- Monday Workout ---");
        mondayWorkout.displayWorkout();

        System.out.println("\n--- Wednesday Workout ---");
        wednesdayWorkout.displayWorkout();

        System.out.println("\n--- Friday Workout ---");
        fridayWorkout.displayWorkout();
    }
}
/*
import java.util.Scanner;

public class WeeklyWorkoutPlan {

    private WorkoutSetQueue workoutQueue;
    private WorkoutLinkedList workoutList;

    private String[] days = {
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
    };

    private WorkoutSet[] weeklyPlan = new WorkoutSet[7];

    private String[] workoutStatus = new String[7];

    private Profile profile;

    private WorkoutHistoryStack workoutHistory;

    Scanner input = new Scanner(System.in);

    public WeeklyWorkoutPlan(Profile profile) {

        this.profile = profile;

        this.workoutHistory = new WorkoutHistoryStack();

        this.workoutQueue = new WorkoutSetQueue();

        this.workoutList = new WorkoutLinkedList();

        generateWeeklyPlan();
    }

    private void generateWeeklyPlan() {

        WorkoutSet upper = new WorkoutSet("Upper", profile);
        WorkoutSet lower = new WorkoutSet("Lower", profile);
        WorkoutSet fullBody = new WorkoutSet("Full Body", profile);

        weeklyPlan[0] = upper;
        weeklyPlan[2] = lower;
        weeklyPlan[4] = fullBody;

        workoutStatus[0] = "Not Completed";
        workoutStatus[2] = "Not Completed";
        workoutStatus[4] = "Not Completed";

        // Queue
        workoutQueue.addWorkout(upper);
        workoutQueue.addWorkout(lower);
        workoutQueue.addWorkout(fullBody);

        // Linked List activity records
        workoutList.addLast(
            upper,
            "Monday",
            "Not Completed"
        );

        workoutList.addLast(
            lower,
            "Wednesday",
            "Not Completed"
        );

        workoutList.addLast(
            fullBody,
            "Friday",
            "Not Completed"
        );
    }

    public void displayWeeklyPlan() {

        System.out.println("\n===== WEEKLY WORKOUT PLAN =====");

        for (int i = 0; i < days.length; i++) {

            System.out.println("\n" + days[i] + ":");

            if (weeklyPlan[i] == null) {

                System.out.println("Rest");

            } else {

                weeklyPlan[i].displayWorkout();

                System.out.println(
                    "Status: " + workoutStatus[i]
                );
            }
        }
    }

    public void simulateWeek() {

        System.out.println("\n=================================");
        System.out.println("       WEEK SIMULATION");
        System.out.println("=================================");

        for (int i = 0; i < days.length; i++) {

            System.out.println("\n=================================");
            System.out.println("TODAY: " + days[i]);
            System.out.println("=================================");

            if (weeklyPlan[i] == null) {

                System.out.println("Today is a rest day.");

                continue;
            }

            weeklyPlan[i].displayWorkout();

            System.out.println("\nDid you complete this workout?");
            System.out.println("1. Yes");
            System.out.println("2. No");

            int choice = getWorkoutChoice();

            if (choice == 1) {

                workoutStatus[i] = "Completed";

                // Update Linked List
                workoutList.updateStatus(
                    weeklyPlan[i].getWorkoutType(),
                    days[i],
                    "Completed"
                );

                // Remove completed workout from Queue
                workoutQueue.completeWorkout();

                // Add completed workout to Stack
                workoutHistory.addCompletedWorkout(
                    weeklyPlan[i]
                );

                System.out.println("\nWorkout completed!");

            } else {

                workoutStatus[i] = "Missed";

                // Update Linked List
                workoutList.updateStatus(
                    weeklyPlan[i].getWorkoutType(),
                    days[i],
                    "Missed"
                );

                System.out.println("\nWorkout missed.");

                moveMissedWorkout(i);
            }
        }

        System.out.println("\n=================================");
        System.out.println("       END OF WEEK");
        System.out.println("=================================");

        displayWeeklyPlan();

        displayWorkoutHistory();

        displayWorkoutActivity();
    }

    private int getWorkoutChoice() {

        int choice;

        while (true) {

            System.out.print("Enter choice: ");

            if (input.hasNextInt()) {

                choice = input.nextInt();

                if (choice == 1 || choice == 2) {

                    return choice;
                }

                System.out.println(
                    "Please enter 1 or 2."
                );

            } else {

                System.out.println(
                    "Invalid input. Please enter 1 or 2."
                );

                input.next();
            }
        }
    }

    private void moveMissedWorkout(int missedDayIndex) {

        System.out.println(
            "\nSearching for an available day..."
        );

        for (int i = missedDayIndex + 1;
             i < days.length;
             i++) {

            if (weeklyPlan[i] == null) {

                String oldDay = days[missedDayIndex];
                String newDay = days[i];

                weeklyPlan[i] =
                    weeklyPlan[missedDayIndex];

                workoutStatus[i] =
                    "Not Completed";

                weeklyPlan[missedDayIndex] =
                    null;

                workoutStatus[missedDayIndex] =
                    null;

                // Update Linked List
                workoutList.updateDay(
                    weeklyPlan[i].getWorkoutType(),
                    oldDay,
                    newDay
                );

                System.out.println(
                    "Workout moved from "
                    + oldDay
                    + " to "
                    + newDay
                );

                return;
            }
        }

        System.out.println(
            "No available day found for the missed workout."
        );
    }

    public void displayWorkoutHistory() {

        System.out.println("\n=================================");
        System.out.println("       WORKOUT HISTORY");
        System.out.println("=================================");

        workoutHistory.displayHistory();
    }

    public void displayWorkoutActivity() {

        System.out.println("\n=================================");
        System.out.println("       WORKOUT ACTIVITY");
        System.out.println("=================================");

        workoutList.traverse();
    }
}
*/