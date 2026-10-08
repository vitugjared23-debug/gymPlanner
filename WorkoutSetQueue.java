package com.mycompany.gymorig;


import java.util.ArrayDeque;
import java.util.Queue;

public class WorkoutSetQueue {

    private Queue<WorkoutSet> workoutQueue;
    

    public WorkoutSetQueue() {
        workoutQueue = new ArrayDeque<>();
    }

    public void addWorkout(WorkoutSet workout) {
        workoutQueue.offer(workout);
    }

    public WorkoutSet nextWorkout() {
        return workoutQueue.peek();
    }

    public WorkoutSet completeWorkout() {
        return workoutQueue.poll();
    }

    public boolean isEmpty() {
        return workoutQueue.isEmpty();
    }

    public void displayQueue() {

        System.out.println("\n=== Workout Queue ===");

        for (WorkoutSet workout : workoutQueue) {
            System.out.println(workout.getWorkoutType() + " Workout:");
        }
    }
}