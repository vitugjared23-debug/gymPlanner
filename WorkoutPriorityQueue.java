package com.mycompany.gymorig;


import java.util.PriorityQueue;
import java.util.Comparator;

public class WorkoutPriorityQueue {

    private PriorityQueue<Exercise> priorityQueue;

    public WorkoutPriorityQueue() {

        priorityQueue = new PriorityQueue<>(
            Comparator.comparingInt(
                Exercise::getBaseDifficulty
            ).reversed()
        );
    }

    public void addExercise(Exercise exercise) {

        if (exercise != null) {
            priorityQueue.offer(exercise);
        }
    }

    public Exercise peekHighestPriority() {

        return priorityQueue.peek();
    }

    public Exercise removeHighestPriority() {

        return priorityQueue.poll();
    }

    public boolean isEmpty() {

        return priorityQueue.isEmpty();
    }

    public int size() {

        return priorityQueue.size();
    }

    public void displayQueue() {

        System.out.println("\nExercise Priority Queue:");

        for (Exercise exercise : priorityQueue) {

            System.out.println(
                exercise.getExerciseName()
                + " | Difficulty: "
                + exercise.getBaseDifficulty()
            );
        }
    }
}