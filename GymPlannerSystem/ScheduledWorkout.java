/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gymorig;


import java.util.ArrayList;

public class ScheduledWorkout {
    private String workoutType;
    private String status;
    private ArrayList<Exercise> exercises;
    private ArrayList<String> completedExerciseNames = new ArrayList<>();

    public ScheduledWorkout(String workoutType, String status, ArrayList<Exercise> exercises) {
        this.workoutType = workoutType;
        this.status = status;
        this.exercises = exercises;
    }

    public String getWorkoutType() { return workoutType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public ArrayList<Exercise> getExercises() { return exercises; }

    public ArrayList<String> getCompletedExerciseNames() { return completedExerciseNames; }
    public void setCompletedExerciseNames(ArrayList<String> completedExerciseNames) {
        this.completedExerciseNames = completedExerciseNames;
    }
}