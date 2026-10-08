package com.mycompany.gymorig;


public class ExerciseDataBST {

    private ExerciseData exerciseData;
    private ExerciseBST exerciseBST;

    public ExerciseDataBST() {

        exerciseData = new ExerciseData();

        exerciseBST = new ExerciseBST();

        populateBST();
    }

    private void populateBST() {

        for (int i = 0;
             i < exerciseData.exercises.size();
             i++) {

            Exercise currentExercise =
                    exerciseData.exercises.get(i);

            exerciseBST.insert(currentExercise);
        }
    }

    public Exercise searchExerciseId(int exerciseId) {

        return exerciseBST.search(exerciseId);
    }

    public void displayExercises() {

        exerciseBST.inorder();
    }
}