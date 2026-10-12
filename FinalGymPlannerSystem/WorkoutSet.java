package com.mycompany.gymorig;


import java.util.ArrayList;

public class WorkoutSet {

    ExerciseData exerciseSource = new ExerciseData();

    ExerciseDataHash exerciseHash =
            new ExerciseDataHash();

    ExerciseDataBST exerciseBST =
            new ExerciseDataBST();

    WorkoutPriorityQueue exercisePriorityQueue =
            new WorkoutPriorityQueue();

    ExerciseDataGraph exerciseGraph =
            new ExerciseDataGraph();

    ArrayList<Exercise> exercises =
            new ArrayList<>();

    private String workoutType;
    private Profile profile;

    public WorkoutSet(
            String workoutType,
            Profile profile) {

        this.workoutType = workoutType;
        this.profile = profile;

        populateWorkoutSet();
    }

    public String getWorkoutType() {

        return workoutType;
    }

    void populateWorkoutSet() {

        for (int i = 0;
             i < exerciseSource.exercises.size();
             i++) {

            Exercise currentExercise =
                    exerciseSource.exercises.get(i);

            String targetMuscle =
                    currentExercise.getTargetMuscle();

            boolean bodyTypeMatches =
                    currentExercise.getBodyTypeTarget()
                            .equalsIgnoreCase(
                                    profile.bodyType
                            );

            boolean goalMatches =
                    currentExercise.getGoalTarget()
                            .equalsIgnoreCase(
                                    profile.bodyGoal
                            );

            if (bodyTypeMatches && goalMatches) {

                boolean workoutMatches = false;

                if (workoutType.equalsIgnoreCase("Upper")
                        && isUpperBody(targetMuscle)) {

                    workoutMatches = true;

                } else if (workoutType.equalsIgnoreCase("Lower")
                        && isLowerBody(targetMuscle)) {

                    workoutMatches = true;

                } else if (workoutType.equalsIgnoreCase("Full Body")
                        && (isUpperBody(targetMuscle)
                        || isLowerBody(targetMuscle))) {

                    workoutMatches = true;
                }

                if (workoutMatches) {

                    // =========================
                    // HASHMAP LOOKUP
                    // =========================

                    Exercise exerciseFromHash =
                            exerciseHash.searchExerciseId(
                                    currentExercise.getExerciseId()
                            );

                    if (exerciseFromHash != null) {

                        // =========================
                        // BST LOOKUP
                        // =========================

                        Exercise exerciseFromBST =
                                exerciseBST.searchExerciseId(
                                        exerciseFromHash.getExerciseId()
                                );

                        if (exerciseFromBST != null) {

                            // =========================
                            // GRAPH LOOKUP
                            // =========================

                            boolean graphMatches =
                                    exerciseGraph.isConnected(
                                            exerciseFromBST.getExerciseName(),
                                            exerciseFromBST.getTargetMuscle()
                                    );

                            if (graphMatches) {

                                // =========================
                                // PRIORITY QUEUE
                                // =========================

                                exercisePriorityQueue.addExercise(
                                        exerciseFromBST
                                );
                            }
                        }
                    }
                }
            }
        }

        /*
         * Remove exercises from the Priority Queue
         * according to their priority.
         */
        while (!exercisePriorityQueue.isEmpty()) {

            Exercise highestPriority =
                    exercisePriorityQueue.removeHighestPriority();

            exercises.add(highestPriority);
        }
    }

    private boolean isUpperBody(String muscle) {

        return muscle.equalsIgnoreCase("Chest")
                || muscle.equalsIgnoreCase("Triceps")
                || muscle.equalsIgnoreCase("Back")
                || muscle.equalsIgnoreCase("Shoulders")
                || muscle.equalsIgnoreCase("Biceps");
    }

    private boolean isLowerBody(String muscle) {

        return muscle.equalsIgnoreCase("Quadriceps")
                || muscle.equalsIgnoreCase("Quads")
                || muscle.equalsIgnoreCase("Hamstrings")
                || muscle.equalsIgnoreCase("Glutes")
                || muscle.equalsIgnoreCase("Calves");
    }

    public void displayWorkout() {

        System.out.println(workoutType + " Workout:");

        for (Exercise exercise : exercises) {

            System.out.println(
                "- "
                + exercise.getExerciseName()
                + " | Difficulty: "
                + exercise.getBaseDifficulty()
            );
        }
    }
}