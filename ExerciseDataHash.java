package com.mycompany.gymorig;


import java.util.HashMap;

public class ExerciseDataHash {

    private HashMap<String, Exercise> map = new HashMap<>();
    private HashMap<Integer, Exercise> mapInt = new HashMap<>();

    private ExerciseData exerciseData;

    public ExerciseDataHash() {

        exerciseData = new ExerciseData();

        populateHashMap();
    }

    private void populateHashMap() {

        for (int i = 0; i < exerciseData.exercises.size(); i++) {

            Exercise currentExercise =
                    exerciseData.exercises.get(i);

            map.put(
                currentExercise.getExerciseName(),
                currentExercise
            );

            mapInt.put(
                currentExercise.getExerciseId(),
                currentExercise
            );
        }
    }

    public Exercise searchExercise(String exerciseName) {

        return map.get(exerciseName);
    }

    public Exercise searchExerciseId(int exerciseId) {

        return mapInt.get(exerciseId);
    }
}