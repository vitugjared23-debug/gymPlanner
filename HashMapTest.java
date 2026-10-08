package com.mycompany.gymorig;



public class HashMapTest {

    public static void main(String[] args) {

        ExerciseDataHash exerciseHash =
                new ExerciseDataHash();

        Exercise result =
                exerciseHash.searchExercise("Bench Press");

        if (result != null) {

            System.out.println("Exercise found:");
            System.out.println(result);

        } else {

            System.out.println("Exercise not found.");
        }

        Exercise resultById =
        exerciseHash.searchExerciseId(1006);

        if (resultById != null) {

            System.out.println("\nExercise found by ID:");
            System.out.println(resultById);

        } else {

            System.out.println("Exercise not found.");
        }
    }
}