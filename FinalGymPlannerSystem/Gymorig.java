package com.mycompany.gymorig;


import java.util.Scanner;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Gymorig {

    private static Scanner input =
            new Scanner(System.in);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            
            GymPlannerGUI gui = new GymPlannerGUI();
            gui.setVisible(true);
        });
    }
}
        
        /*
        FirstStep system =
                new FirstStep();

        boolean running = true;

        while (running) {

            displayWelcome();

            int choice =
                    getMainChoice();

            switch (choice) {

                case 1:

                    system.signUp();

                    break;

                case 2:

                    FirstStep.Account account =
                            system.login();

                    if (account != null) {

                        dashboard(account);
                    }

                    break;

                case 3:

                    running = false;

                    System.out.println(
                        "\nThank you for using "
                        + "Fitness Planner!"
                    );

                    break;
            }
        }
    }

    /*
     * ================================
     * MAIN MENU
     * ================================
     *

    private static void displayWelcome() {

        System.out.println("\n");
        System.out.println("=================================");
        System.out.println("       FITNESS PLANNER");
        System.out.println("=================================");
        System.out.println("1. Sign Up");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println("=================================");
    }

    private static int getMainChoice() {

        while (true) {

            System.out.print("Choose an option: ");

            if (input.hasNextInt()) {

                int choice =
                        input.nextInt();

                input.nextLine();

                if (choice >= 1 && choice <= 3) {

                    return choice;
                }

                System.out.println(
                    "Please choose 1, 2, or 3."
                );

            } else {

                System.out.println(
                    "Please enter a number."
                );

                input.nextLine();
            }
        }
    }

    /*
     * ================================
     * DASHBOARD
     * ================================
     *

    private static void dashboard(
            FirstStep.Account account) {

        Profile profile =
                account.getProfile();

        WeeklyWorkoutPlan weeklyPlan =
                new WeeklyWorkoutPlan(profile);

        boolean loggedIn = true;

        while (loggedIn) {

            displayDashboard(account);

            int choice =
                    getDashboardChoice();

            switch (choice) {

                case 1:

                    displayProfile(profile);

                    break;

                case 2:

                    weeklyPlan.displayWeeklyPlan();

                    break;

                case 3:

                    weeklyPlan.simulateWeek();

                    break;

                case 4:

                    weeklyPlan.displayWorkoutHistory();

                    break;

                case 5:

                    weeklyPlan.displayWorkoutActivity();

                    break;

                case 6:

                    loggedIn = false;

                    System.out.println(
                        "\nLogging out..."
                    );

                    break;
            }
        }
    }

    /*
     * ================================
     * DASHBOARD MENU
     * ================================
     *

    private static void displayDashboard(
            FirstStep.Account account) {

        System.out.println("\n");
        System.out.println("=================================");
        System.out.println("            DASHBOARD");
        System.out.println("=================================");

        System.out.println(
            "Welcome, " + account.getEmail()
        );

        System.out.println("=================================");
        System.out.println("1. View Profile");
        System.out.println("2. View Weekly Workout Plan");
        System.out.println("3. Start / Simulate Workout Week");
        System.out.println("4. View Workout History");
        System.out.println("5. View Workout Activity");
        System.out.println("6. Logout");
        System.out.println("=================================");
    }

    private static int getDashboardChoice() {

        while (true) {

            System.out.print("Choose an option: ");

            if (input.hasNextInt()) {

                int choice =
                        input.nextInt();

                input.nextLine();

                if (choice >= 1 && choice <= 6) {

                    return choice;
                }

                System.out.println(
                    "Please choose 1 to 6."
                );

            } else {

                System.out.println(
                    "Please enter a number."
                );

                input.nextLine();
            }
        }
    }

    /*
     * ================================
     * PROFILE
     * ================================
     *

    private static void displayProfile(
            Profile profile) {

        System.out.println("\n");
        System.out.println("=================================");
        System.out.println("             PROFILE");
        System.out.println("=================================");

        System.out.println(
            "Height: "
            + profile.height
            + " m"
        );

        System.out.println(
            "Weight: "
            + profile.weight
            + " kg"
        );

        System.out.println(
            "BMI: "
            + profile.bmi
        );

        System.out.println(
            "Body Type: "
            + profile.bodyType
        );

        System.out.println(
            "Body Goal: "
            + profile.bodyGoal
        );

        System.out.println("=================================");
    }
}
*/