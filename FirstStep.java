package com.mycompany.gymorig;


import java.util.Hashtable;

public class FirstStep {

    // Nested Account class kept directly in FirstStep
    public static class Account {
        public String email;
        public String pass;
        public Profile profile;

        public Account(String email, String pass) {
            this.email = email;
            this.pass = pass;
        }

        public Account(String email, String pass, Profile profile) {
            this.email = email;
            this.pass = pass;
            this.profile = profile;
        }
    }

    public static void main(String[] args) {
        System.out.println("GymPlanner Console Backend Loaded.");
    }
}
/*
import java.util.Hashtable;
import java.util.Scanner;

public class FirstStep {

    private Hashtable<String, Account> accounts =
            new Hashtable<>();

    private Scanner input = new Scanner(System.in);

    *
     * Account stores the user's login information
     * and their fitness profile.
     *
    public class Account {

        private String email;
        private String pass;
        private Profile profile;

        public Account(
                String email,
                String pass,
                Profile profile) {

            this.email = email;
            this.pass = pass;
            this.profile = profile;
        }

        public String getEmail() {
            return email;
        }

        public String getPass() {
            return pass;
        }

        public Profile getProfile() {
            return profile;
        }
    }

    *
     * SIGN UP
     /
    public boolean signUp() {

        System.out.println("\n=================================");
        System.out.println("           SIGN UP");
        System.out.println("=================================");

        System.out.print("Enter email: ");
        String email = input.nextLine();

        if (accounts.containsKey(email)) {

            System.out.println(
                "An account with this email already exists."
            );

            return false;
        }

        System.out.print("Enter password: ");
        String password = input.nextLine();

        if (!isValidPassword(password)) {

            System.out.println(
                "\nPassword must contain:"
            );

            System.out.println("- At least 8 characters");
            System.out.println("- At least one uppercase letter");
            System.out.println("- At least one lowercase letter");
            System.out.println("- At least one number");
            System.out.println("- At least one special character");

            return false;
        }

        *
         * Create the user's fitness profile.
         /
        System.out.println("\n===== CREATE FITNESS PROFILE =====");

        Profile profile = new Profile();

        Account newAccount =
                new Account(
                        email,
                        password,
                        profile
                );

        accounts.put(email, newAccount);

        System.out.println(
            "\nAccount successfully created!"
        );

        return true;
    }

    *
     * LOGIN
     /
    public Account login() {

        System.out.println("\n=================================");
        System.out.println("            LOGIN");
        System.out.println("=================================");

        System.out.print("Enter email: ");
        String email = input.nextLine();

        Account account =
                accounts.get(email);

        if (account == null) {

            System.out.println(
                "Account not found."
            );

            return null;
        }

        int attempts = 5;

        while (attempts > 0) {

            System.out.print("Enter password: ");
            String password = input.nextLine();

            if (account.getPass().equals(password)) {

                System.out.println(
                    "\nLogin successful!"
                );

                return account;
            }

            attempts--;

            System.out.println(
                "Incorrect password."
            );

            System.out.println(
                "Attempts remaining: "
                + attempts
            );
        }

        System.out.println(
            "\nToo many failed attempts."
        );

        return null;
    }

    *
     * PASSWORD VALIDATION
     /
    private boolean isValidPassword(String password) {

        return password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*")
                && password.matches(".*[0-9].*")
                && password.matches(".*[^a-zA-Z0-9].*");
    }
}
*/