/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gymorig;

/**
 *
 * @author User
 */

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataManager {

    private static final String ACCOUNTS_CSV = "accounts.csv";
    private static final String SCHEDULE_CSV = "schedule.csv";

    public static void saveAccountsToCSV(Hashtable<String, FirstStep.Account> accounts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ACCOUNTS_CSV))) {
            for (FirstStep.Account acc : accounts.values()) {
                Profile p = acc.profile;
                if (p != null) {
                    writer.printf("%s,%s,%.2f,%.2f,%.2f,%s,%s\n",
                            acc.email, acc.pass, p.height, p.weight, p.bmi, p.bodyType, p.bodyGoal);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadAccountsFromCSV(Hashtable<String, FirstStep.Account> accounts) {
        File file = new File(ACCOUNTS_CSV);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String email = parts[0];
                    String pass = parts[1];
                    float height = Float.parseFloat(parts[2]);
                    float weight = Float.parseFloat(parts[3]);
                    double bmi = Double.parseDouble(parts[4]);
                    String bodyType = parts[5];
                    String bodyGoal = parts[6];

                    Profile profile = new Profile(height, weight, bmi, bodyType, bodyGoal);
                    accounts.put(email, new FirstStep.Account(email, pass, profile));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveScheduleToCSV(FirstStep.Account account, Map<LocalDate, ScheduledWorkout> scheduleMap) {
        if (account == null) return;
        try (PrintWriter writer = new PrintWriter(new FileWriter(account.email + "_" + SCHEDULE_CSV))) {
            for (Map.Entry<LocalDate, ScheduledWorkout> entry : scheduleMap.entrySet()) {
                writer.printf("%s,%s,%s\n", entry.getKey().toString(), entry.getValue().getWorkoutType(), entry.getValue().getStatus());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadScheduleFromCSV(FirstStep.Account account, Map<LocalDate, ScheduledWorkout> scheduleMap, WorkoutHistoryStack workoutHistory) {
        if (account == null) return;
        File file = new File(account.email + "_" + SCHEDULE_CSV);
        if (!file.exists()) return;

        scheduleMap.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    String type = parts[1];
                    String status = parts[2];

                    WorkoutSet set = new WorkoutSet(type, account.profile);
                    ScheduledWorkout sw = new ScheduledWorkout(type, status, set.exercises);
                    scheduleMap.put(date, sw);

                    if ("Completed".equalsIgnoreCase(status)) {
                        workoutHistory.addCompletedWorkout(set);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}