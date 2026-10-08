package com.mycompany.gymorig;


import java.util.Scanner;

public class Profile {

    Scanner input = new Scanner(System.in);
    BMICalculator bmiCalculator = new BMICalculator();

    public float height;
    public float weight;
    public double bmi;
    public String bodyType;
    public String bodyGoal;

    private final String[] availableGoals = {
        "Lean",
        "Gain Muscle",
        "Weight Loss",
        "Maintenance"
    };

    // Console Constructor
    public Profile() {
        System.out.println("Enter your height in meters:");
        height = input.nextFloat();

        System.out.println("Enter your weight in kilograms:");
        weight = input.nextFloat();

        input.nextLine();

        bmi = bmiCalculator.calculateBMI(weight, height);
        bodyType = bmiCalculator.getBodyType(bmi);

        System.out.println("\nYour BMI: " + bmi);
        System.out.println("Your body type: " + bodyType);

        chooseBodyGoal();
    }

    // GUI / CSV Constructor
    public Profile(float height, float weight, double bmi, String bodyType, String bodyGoal) {
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.bodyType = bodyType;
        this.bodyGoal = bodyGoal;
    }

    private void chooseBodyGoal() {
        System.out.println("\nChoose your body goal:");

        for (int i = 0; i < availableGoals.length; i++) {
            System.out.println((i + 1) + ". " + availableGoals[i]);
        }

        int choice;
        while (true) {
            System.out.print("Enter your choice: ");
            if (input.hasNextInt()) {
                choice = input.nextInt();
                if (choice >= 1 && choice <= availableGoals.length) {
                    bodyGoal = availableGoals[choice - 1];
                    break;
                } else {
                    System.out.println("Invalid choice. Please choose from 1 to " + availableGoals.length + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.next();
            }
        }
        input.nextLine();
        System.out.println("Selected body goal: " + bodyGoal);
    }
}
/*
import java.util.Scanner;

public class Profile {

    Scanner input = new Scanner(System.in);
    BMICalculator bmiCalculator = new BMICalculator();

    float height;
    float weight;
    double bmi;
    String bodyType;
    String bodyGoal;

    // Available body goals
    private final String[] availableGoals = {
        "Lean",
        "Gain Muscle",
        "Weight Loss",
        "Maintenance"
    };

    // Used when creating a NEW profile
    Profile() {

        System.out.println("Enter your height in meters");
        height = input.nextFloat();

        System.out.println("Enter your weight in kilograms");
        weight = input.nextFloat();

        input.nextLine();

        // Calculate BMI and body type automatically
        bmi = bmiCalculator.calculateBMI(weight, height);
        bodyType = bmiCalculator.getBodyType(bmi);

        System.out.println("\nYour BMI: " + bmi);
        System.out.println("Your body type: " + bodyType);

        chooseBodyGoal();
    }

    // Used when loading an EXISTING profile from CSV
    Profile(float height, float weight, double bmi,
            String bodyType, String bodyGoal) {

        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.bodyType = bodyType;
        this.bodyGoal = bodyGoal;
    }

    // Let the user choose from valid body goals
    private void chooseBodyGoal() {

        System.out.println("\nChoose your body goal:");

        for (int i = 0; i < availableGoals.length; i++) {

            System.out.println(
                    (i + 1) + ". " + availableGoals[i]
            );
        }

        int choice;

        while (true) {

            System.out.print("Enter your choice: ");

            if (input.hasNextInt()) {

                choice = input.nextInt();

                if (choice >= 1 && choice <= availableGoals.length) {

                    bodyGoal = availableGoals[choice - 1];

                    break;

                } else {

                    System.out.println(
                            "Invalid choice. Please choose from 1 to "
                            + availableGoals.length + "."
                    );

                }

            } else {

                System.out.println(
                        "Invalid input. Please enter a number."
                );

                input.next();
            }
        }

        input.nextLine();

        System.out.println(
                "Selected body goal: " + bodyGoal
        );
    }
}
*/
