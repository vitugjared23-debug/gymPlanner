package com.mycompany.gymorig;


public class ExerciseDataGraph {

    private ExerciseData exerciseData;
    private ExerciseGraph exerciseGraph;

    public ExerciseDataGraph() {

        exerciseData = new ExerciseData();

        exerciseGraph = new ExerciseGraph();

        populateGraph();
    }

    private void populateGraph() {

        for (int i = 0;
             i < exerciseData.exercises.size();
             i++) {

            Exercise currentExercise =
                    exerciseData.exercises.get(i);

            String exerciseName =
                    currentExercise.getExerciseName();

            String muscle =
                    currentExercise.getTargetMuscle();

            exerciseGraph.addEdge(
                    exerciseName,
                    muscle
            );
        }
    }

    public boolean isConnected(
            String exercise,
            String muscle) {

        return exerciseGraph.hasConnection(
                exercise,
                muscle
        );
    }

    public void displayGraph() {

        exerciseGraph.displayGraph();
    }

    public void showExerciseConnections(
            String exercise) {

        exerciseGraph.showConnections(exercise);
    }
}