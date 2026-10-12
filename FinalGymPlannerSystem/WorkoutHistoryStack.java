package com.mycompany.gymorig;

import java.util.ArrayDeque;
import java.util.Deque;

public class WorkoutHistoryStack {

    Deque<WorkoutSet> workoutHistory = new ArrayDeque<>();

    public void addCompletedWorkout(WorkoutSet workout) {
        workoutHistory.push(workout);
    }

    public WorkoutSet getLatestWorkout() {
        return workoutHistory.peek();
    }

    public WorkoutSet removeLatestWorkout() {
        return workoutHistory.pop();
    }

    public boolean isEmpty() {
        return workoutHistory.isEmpty();
    }

    public Deque<WorkoutSet> getWorkoutHistory() {
        return workoutHistory;
    }

    public void displayHistory() {
        System.out.println("Workout History:");
        for (WorkoutSet workout : workoutHistory) {
            workout.displayWorkout();
        }
    }
}
/*import java.util.ArrayDeque;
import java.util.Deque;

public class WorkoutHistoryStack {

    Deque<WorkoutSet> workoutHistory = new ArrayDeque<>();

    public void addCompletedWorkout(WorkoutSet workout) {
        workoutHistory.push(workout);
    }

    public WorkoutSet getLatestWorkout() {
        return workoutHistory.peek();
    }

    public WorkoutSet removeLatestWorkout() {
        return workoutHistory.pop();
    }

    public boolean isEmpty() {
        return workoutHistory.isEmpty();
    }

    // Getter method added for Swing GUI & table rendering
    public Deque<WorkoutSet> getWorkoutHistory() {
        return workoutHistory;
    }

    public void displayHistory() {

        System.out.println("Workout History:");

        for (WorkoutSet workout : workoutHistory) {
            workout.displayWorkout();
        }
    }
}
/*
import java.util.ArrayDeque;
import java.util.Deque;

public class WorkoutHistoryStack {

    Deque<WorkoutSet> workoutHistory = new ArrayDeque<>();

    public void addCompletedWorkout(WorkoutSet workout) {
        workoutHistory.push(workout);
    }

    public WorkoutSet getLatestWorkout() {
        return workoutHistory.peek();
    }

    public WorkoutSet removeLatestWorkout() {
        return workoutHistory.pop();
    }

    public boolean isEmpty() {
        return workoutHistory.isEmpty();
    }

    public void displayHistory() {

        System.out.println("Workout History:");

        for (WorkoutSet workout : workoutHistory) {
            workout.displayWorkout();
        }
    }
}
*/