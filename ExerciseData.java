package com.mycompany.gymorig;


import java.util.ArrayList;

public class ExerciseData {

    ArrayList<Exercise> exercises = new ArrayList<>();

    ExerciseData() {

        /*
         * =========================================================
         * CHEST
         * =========================================================
         */

        // LEAN
        exercises.add(new Exercise(1001, "Push Up", "Underweight", "Lean", false, 3, 12, 0, 4, "Chest"));
        exercises.add(new Exercise(1002, "Bench Press", "Normal", "Lean", false, 3, 10, 0, 6, "Chest"));
        exercises.add(new Exercise(1003, "Incline Bench Press", "Overweight", "Lean", false, 3, 10, 0, 7, "Chest"));
        exercises.add(new Exercise(1004, "Chest Fly", "Obese", "Lean", false, 3, 12, 0, 5, "Chest"));

        // GAIN MUSCLE
        exercises.add(new Exercise(1005, "Bench Press", "Underweight", "Gain Muscle", false, 4, 10, 0, 6, "Chest"));
        exercises.add(new Exercise(1006, "Bench Press", "Normal", "Gain Muscle", false, 4, 10, 0, 6, "Chest"));
        exercises.add(new Exercise(1007, "Incline Bench Press", "Overweight", "Gain Muscle", false, 4, 10, 0, 7, "Chest"));
        exercises.add(new Exercise(1008, "Chest Fly", "Obese", "Gain Muscle", false, 3, 12, 0, 5, "Chest"));

        // WEIGHT LOSS
        exercises.add(new Exercise(1009, "Decline Push Up", "Underweight", "Weight Loss", false, 3, 10, 0, 5, "Chest"));
        exercises.add(new Exercise(1010, "Push Up", "Normal", "Weight Loss", false, 3, 12, 0, 4, "Chest"));
        exercises.add(new Exercise(1011, "Decline Push Up", "Overweight", "Weight Loss", false, 3, 10, 0, 5, "Chest"));
        exercises.add(new Exercise(1012, "Push Up", "Obese", "Weight Loss", false, 3, 8, 0, 4, "Chest"));

        // MAINTENANCE
        exercises.add(new Exercise(1013, "Push Up", "Underweight", "Maintenance", false, 3, 12, 0, 4, "Chest"));
        exercises.add(new Exercise(1014, "Push Up", "Normal", "Maintenance", false, 3, 12, 0, 4, "Chest"));
        exercises.add(new Exercise(1015, "Decline Push Up", "Overweight", "Maintenance", false, 3, 10, 0, 5, "Chest"));
        exercises.add(new Exercise(1016, "Push Up", "Obese", "Maintenance", false, 3, 8, 0, 4, "Chest"));


        /*
         * =========================================================
         * TRICEPS
         * =========================================================
         */

        exercises.add(new Exercise(1017, "Tricep Pushdown", "Underweight", "Lean", false, 3, 12, 0, 5, "Triceps"));
        exercises.add(new Exercise(1018, "Tricep Pushdown", "Normal", "Lean", false, 3, 12, 0, 5, "Triceps"));
        exercises.add(new Exercise(1019, "Tricep Dip", "Overweight", "Lean", false, 3, 10, 0, 6, "Triceps"));
        exercises.add(new Exercise(1020, "Close Grip Push Up", "Obese", "Lean", false, 3, 10, 0, 5, "Triceps"));

        exercises.add(new Exercise(1021, "Tricep Pushdown", "Underweight", "Gain Muscle", false, 3, 12, 0, 5, "Triceps"));
        exercises.add(new Exercise(1022, "Tricep Dip", "Normal", "Gain Muscle", false, 3, 10, 0, 6, "Triceps"));
        exercises.add(new Exercise(1023, "Overhead Tricep Extension", "Overweight", "Gain Muscle", false, 3, 12, 0, 5, "Triceps"));
        exercises.add(new Exercise(1024, "Diamond Push Up", "Obese", "Gain Muscle", false, 3, 10, 0, 7, "Triceps"));

        exercises.add(new Exercise(1025, "Close Grip Push Up", "Underweight", "Weight Loss", false, 3, 12, 0, 5, "Triceps"));
        exercises.add(new Exercise(1026, "Tricep Pushdown", "Normal", "Weight Loss", false, 3, 12, 0, 5, "Triceps"));
        exercises.add(new Exercise(1027, "Close Grip Push Up", "Overweight", "Weight Loss", false, 3, 10, 0, 5, "Triceps"));
        exercises.add(new Exercise(1028, "Tricep Pushdown", "Obese", "Weight Loss", false, 3, 10, 0, 5, "Triceps"));

        exercises.add(new Exercise(1029, "Tricep Pushdown", "Underweight", "Maintenance", false, 3, 12, 0, 5, "Triceps"));
        exercises.add(new Exercise(1030, "Close Grip Push Up", "Normal", "Maintenance", false, 3, 12, 0, 5, "Triceps"));
        exercises.add(new Exercise(1031, "Tricep Dip", "Overweight", "Maintenance", false, 3, 10, 0, 6, "Triceps"));
        exercises.add(new Exercise(1032, "Tricep Pushdown", "Obese", "Maintenance", false, 3, 10, 0, 5, "Triceps"));


        /*
         * =========================================================
         * SHOULDERS
         * =========================================================
         */

        exercises.add(new Exercise(1033, "Shoulder Press", "Underweight", "Lean", false, 3, 10, 0, 6, "Shoulders"));
        exercises.add(new Exercise(1034, "Lateral Raise", "Normal", "Lean", false, 3, 12, 0, 4, "Shoulders"));
        exercises.add(new Exercise(1035, "Front Raise", "Overweight", "Lean", false, 3, 12, 0, 4, "Shoulders"));
        exercises.add(new Exercise(1036, "Lateral Raise", "Obese", "Lean", false, 3, 12, 0, 4, "Shoulders"));

        exercises.add(new Exercise(1037, "Shoulder Press", "Underweight", "Gain Muscle", false, 4, 10, 0, 6, "Shoulders"));
        exercises.add(new Exercise(1038, "Lateral Raise", "Normal", "Gain Muscle", false, 3, 12, 0, 4, "Shoulders"));
        exercises.add(new Exercise(1039, "Arnold Press", "Overweight", "Gain Muscle", false, 3, 10, 0, 7, "Shoulders"));
        exercises.add(new Exercise(1040, "Shoulder Press", "Obese", "Gain Muscle", false, 3, 10, 0, 6, "Shoulders"));

        exercises.add(new Exercise(1041, "Front Raise", "Underweight", "Weight Loss", false, 3, 12, 0, 4, "Shoulders"));
        exercises.add(new Exercise(1042, "Lateral Raise", "Normal", "Weight Loss", false, 3, 12, 0, 4, "Shoulders"));
        exercises.add(new Exercise(1043, "Pike Push Up", "Overweight", "Weight Loss", false, 3, 10, 0, 6, "Shoulders"));
        exercises.add(new Exercise(1044, "Lateral Raise", "Obese", "Weight Loss", false, 3, 12, 0, 4, "Shoulders"));

        exercises.add(new Exercise(1045, "Lateral Raise", "Underweight", "Maintenance", false, 3, 12, 0, 4, "Shoulders"));
        exercises.add(new Exercise(1046, "Front Raise", "Normal", "Maintenance", false, 3, 12, 0, 4, "Shoulders"));
        exercises.add(new Exercise(1047, "Pike Push Up", "Overweight", "Maintenance", false, 3, 10, 0, 6, "Shoulders"));
        exercises.add(new Exercise(1048, "Lateral Raise", "Obese", "Maintenance", false, 3, 12, 0, 4, "Shoulders"));


        /*
         * =========================================================
         * BACK
         * =========================================================
         */

        exercises.add(new Exercise(1049, "Lat Pulldown", "Underweight", "Lean", false, 3, 10, 0, 6, "Back"));
        exercises.add(new Exercise(1050, "Seated Cable Row", "Normal", "Lean", false, 3, 12, 0, 5, "Back"));
        exercises.add(new Exercise(1051, "Dumbbell Row", "Overweight", "Lean", false, 3, 10, 0, 5, "Back"));
        exercises.add(new Exercise(1052, "Lat Pulldown", "Obese", "Lean", false, 3, 10, 0, 6, "Back"));

        exercises.add(new Exercise(1053, "Pull Up", "Underweight", "Gain Muscle", false, 3, 8, 0, 8, "Back"));
        exercises.add(new Exercise(1054, "Lat Pulldown", "Normal", "Gain Muscle", false, 4, 10, 0, 6, "Back"));
        exercises.add(new Exercise(1055, "Barbell Row", "Overweight", "Gain Muscle", false, 4, 10, 0, 7, "Back"));
        exercises.add(new Exercise(1056, "Seated Cable Row", "Obese", "Gain Muscle", false, 3, 12, 0, 5, "Back"));

        exercises.add(new Exercise(1057, "Seated Cable Row", "Underweight", "Weight Loss", false, 3, 12, 0, 5, "Back"));
        exercises.add(new Exercise(1058, "Lat Pulldown", "Normal", "Weight Loss", false, 3, 10, 0, 6, "Back"));
        exercises.add(new Exercise(1059, "Dumbbell Row", "Overweight", "Weight Loss", false, 3, 10, 0, 5, "Back"));
        exercises.add(new Exercise(1060, "Lat Pulldown", "Obese", "Weight Loss", false, 3, 8, 0, 6, "Back"));

        exercises.add(new Exercise(1061, "Dumbbell Row", "Underweight", "Maintenance", false, 3, 10, 0, 5, "Back"));
        exercises.add(new Exercise(1062, "Seated Cable Row", "Normal", "Maintenance", false, 3, 12, 0, 5, "Back"));
        exercises.add(new Exercise(1063, "Lat Pulldown", "Overweight", "Maintenance", false, 3, 10, 0, 6, "Back"));
        exercises.add(new Exercise(1064, "Seated Cable Row", "Obese", "Maintenance", false, 3, 10, 0, 5, "Back"));


        /*
         * =========================================================
         * BICEPS
         * =========================================================
         */

        exercises.add(new Exercise(1065, "Bicep Curl", "Underweight", "Lean", false, 3, 12, 0, 4, "Biceps"));
        exercises.add(new Exercise(1066, "Hammer Curl", "Normal", "Lean", false, 3, 12, 0, 5, "Biceps"));
        exercises.add(new Exercise(1067, "Cable Curl", "Overweight", "Lean", false, 3, 12, 0, 4, "Biceps"));
        exercises.add(new Exercise(1068, "Bicep Curl", "Obese", "Lean", false, 3, 10, 0, 4, "Biceps"));

        exercises.add(new Exercise(1069, "Bicep Curl", "Underweight", "Gain Muscle", false, 3, 12, 0, 4, "Biceps"));
        exercises.add(new Exercise(1070, "Hammer Curl", "Normal", "Gain Muscle", false, 3, 12, 0, 5, "Biceps"));
        exercises.add(new Exercise(1071, "Preacher Curl", "Overweight", "Gain Muscle", false, 3, 10, 0, 5, "Biceps"));
        exercises.add(new Exercise(1072, "Hammer Curl", "Obese", "Gain Muscle", false, 3, 10, 0, 5, "Biceps"));

        exercises.add(new Exercise(1073, "Bicep Curl", "Underweight", "Weight Loss", false, 3, 12, 0, 4, "Biceps"));
        exercises.add(new Exercise(1074, "Hammer Curl", "Normal", "Weight Loss", false, 3, 12, 0, 5, "Biceps"));
        exercises.add(new Exercise(1075, "Cable Curl", "Overweight", "Weight Loss", false, 3, 12, 0, 4, "Biceps"));
        exercises.add(new Exercise(1076, "Bicep Curl", "Obese", "Weight Loss", false, 3, 10, 0, 4, "Biceps"));

        exercises.add(new Exercise(1077, "Cable Curl", "Underweight", "Maintenance", false, 3, 12, 0, 4, "Biceps"));
        exercises.add(new Exercise(1078, "Hammer Curl", "Normal", "Maintenance", false, 3, 12, 0, 5, "Biceps"));
        exercises.add(new Exercise(1079, "Bicep Curl", "Overweight", "Maintenance", false, 3, 12, 0, 4, "Biceps"));
        exercises.add(new Exercise(1080, "Cable Curl", "Obese", "Maintenance", false, 3, 10, 0, 4, "Biceps"));


        /*
         * =========================================================
         * ABS
         * =========================================================
         */

        exercises.add(new Exercise(1081, "Plank", "Underweight", "Lean", true, 3, 0, 30, 4, "Abs"));
        exercises.add(new Exercise(1082, "Crunch", "Normal", "Lean", false, 3, 15, 0, 3, "Abs"));
        exercises.add(new Exercise(1083, "Plank", "Overweight", "Lean", true, 3, 0, 30, 4, "Abs"));
        exercises.add(new Exercise(1084, "Plank", "Obese", "Lean", true, 3, 0, 20, 4, "Abs"));

        exercises.add(new Exercise(1085, "Crunch", "Underweight", "Gain Muscle", false, 3, 15, 0, 3, "Abs"));
        exercises.add(new Exercise(1086, "Sit Up", "Normal", "Gain Muscle", false, 3, 15, 0, 4, "Abs"));
        exercises.add(new Exercise(1087, "Leg Raise", "Overweight", "Gain Muscle", false, 3, 12, 0, 5, "Abs"));
        exercises.add(new Exercise(1088, "Plank", "Obese", "Gain Muscle", true, 3, 0, 20, 4, "Abs"));

        exercises.add(new Exercise(1089, "Crunch", "Underweight", "Weight Loss", false, 3, 15, 0, 3, "Abs"));
        exercises.add(new Exercise(1090, "Bicycle Crunch", "Normal", "Weight Loss", false, 3, 15, 0, 5, "Abs"));
        exercises.add(new Exercise(1091, "Leg Raise", "Overweight", "Weight Loss", false, 3, 12, 0, 5, "Abs"));
        exercises.add(new Exercise(1092, "Plank", "Obese", "Weight Loss", true, 3, 0, 20, 4, "Abs"));

        exercises.add(new Exercise(1093, "Plank", "Underweight", "Maintenance", true, 3, 0, 30, 4, "Abs"));
        exercises.add(new Exercise(1094, "Plank", "Normal", "Maintenance", true, 3, 0, 30, 4, "Abs"));
        exercises.add(new Exercise(1095, "Bicycle Crunch", "Overweight", "Maintenance", false, 3, 15, 0, 5, "Abs"));
        exercises.add(new Exercise(1096, "Plank", "Obese", "Maintenance", true, 3, 0, 20, 4, "Abs"));


        /*
         * =========================================================
         * QUADS
         * =========================================================
         */

        exercises.add(new Exercise(1097, "Bodyweight Squat", "Underweight", "Lean", false, 3, 12, 0, 4, "Quads"));
        exercises.add(new Exercise(1098, "Bodyweight Squat", "Normal", "Lean", false, 3, 15, 0, 4, "Quads"));
        exercises.add(new Exercise(1099, "Walking Lunge", "Overweight", "Lean", false, 3, 12, 0, 5, "Quads"));
        exercises.add(new Exercise(1100, "Bodyweight Squat", "Obese", "Lean", false, 3, 10, 0, 4, "Quads"));

        exercises.add(new Exercise(1101, "Barbell Squat", "Underweight", "Gain Muscle", false, 4, 10, 0, 8, "Quads"));
        exercises.add(new Exercise(1102, "Barbell Squat", "Normal", "Gain Muscle", false, 4, 10, 0, 8, "Quads"));
        exercises.add(new Exercise(1103, "Leg Press", "Overweight", "Gain Muscle", false, 4, 10, 0, 7, "Quads"));
        exercises.add(new Exercise(1104, "Leg Extension", "Obese", "Gain Muscle", false, 3, 12, 0, 5, "Quads"));

        exercises.add(new Exercise(1105, "Bodyweight Squat", "Underweight", "Weight Loss", false, 3, 15, 0, 4, "Quads"));
        exercises.add(new Exercise(1106, "Walking Lunge", "Normal", "Weight Loss", false, 3, 12, 0, 5, "Quads"));
        exercises.add(new Exercise(1107, "Bodyweight Squat", "Overweight", "Weight Loss", false, 3, 12, 0, 4, "Quads"));
        exercises.add(new Exercise(1108, "Bodyweight Squat", "Obese", "Weight Loss", false, 3, 10, 0, 4, "Quads"));

        exercises.add(new Exercise(1109, "Bodyweight Squat", "Underweight", "Maintenance", false, 3, 12, 0, 4, "Quads"));
        exercises.add(new Exercise(1110, "Bodyweight Squat", "Normal", "Maintenance", false, 3, 15, 0, 4, "Quads"));
        exercises.add(new Exercise(1111, "Walking Lunge", "Overweight", "Maintenance", false, 3, 12, 0, 5, "Quads"));
        exercises.add(new Exercise(1112, "Bodyweight Squat", "Obese", "Maintenance", false, 3, 10, 0, 4, "Quads"));


        /*
         * =========================================================
         * HAMSTRINGS
         * =========================================================
         */

        exercises.add(new Exercise(1113, "Romanian Deadlift", "Underweight", "Lean", false, 3, 10, 0, 8, "Hamstrings"));
        exercises.add(new Exercise(1114, "Lying Leg Curl", "Normal", "Lean", false, 3, 12, 0, 5, "Hamstrings"));
        exercises.add(new Exercise(1115, "Seated Leg Curl", "Overweight", "Lean", false, 3, 12, 0, 5, "Hamstrings"));
        exercises.add(new Exercise(1116, "Lying Leg Curl", "Obese", "Lean", false, 3, 10, 0, 5, "Hamstrings"));

        exercises.add(new Exercise(1117, "Romanian Deadlift", "Underweight", "Gain Muscle", false, 4, 10, 0, 8, "Hamstrings"));
        exercises.add(new Exercise(1118, "Lying Leg Curl", "Normal", "Gain Muscle", false, 3, 12, 0, 5, "Hamstrings"));
        exercises.add(new Exercise(1119, "Seated Leg Curl", "Overweight", "Gain Muscle", false, 3, 12, 0, 5, "Hamstrings"));
        exercises.add(new Exercise(1120, "Good Morning", "Obese", "Gain Muscle", false, 3, 10, 0, 7, "Hamstrings"));

        exercises.add(new Exercise(1121, "Lying Leg Curl", "Underweight", "Weight Loss", false, 3, 12, 0, 5, "Hamstrings"));
        exercises.add(new Exercise(1122, "Seated Leg Curl", "Normal", "Weight Loss", false, 3, 12, 0, 5, "Hamstrings"));
        exercises.add(new Exercise(1123, "Single Leg Deadlift", "Overweight", "Weight Loss", false, 3, 10, 0, 6, "Hamstrings"));
        exercises.add(new Exercise(1124, "Lying Leg Curl", "Obese", "Weight Loss", false, 3, 10, 0, 5, "Hamstrings"));

        exercises.add(new Exercise(1125, "Single Leg Deadlift", "Underweight", "Maintenance", false, 3, 10, 0, 6, "Hamstrings"));
        exercises.add(new Exercise(1126, "Lying Leg Curl", "Normal", "Maintenance", false, 3, 12, 0, 5, "Hamstrings"));
        exercises.add(new Exercise(1127, "Seated Leg Curl", "Overweight", "Maintenance", false, 3, 12, 0, 5, "Hamstrings"));
        exercises.add(new Exercise(1128, "Lying Leg Curl", "Obese", "Maintenance", false, 3, 10, 0, 5, "Hamstrings"));


        /*
         * =========================================================
         * GLUTES
         * =========================================================
         */

        exercises.add(new Exercise(1129, "Glute Bridge", "Underweight", "Lean", false, 3, 15, 0, 4, "Glutes"));
        exercises.add(new Exercise(1130, "Hip Thrust", "Normal", "Lean", false, 3, 10, 0, 6, "Glutes"));
        exercises.add(new Exercise(1131, "Glute Kickback", "Overweight", "Lean", false, 3, 12, 0, 4, "Glutes"));
        exercises.add(new Exercise(1132, "Glute Bridge", "Obese", "Lean", false, 3, 12, 0, 4, "Glutes"));

        exercises.add(new Exercise(1133, "Hip Thrust", "Underweight", "Gain Muscle", false, 4, 10, 0, 6, "Glutes"));
        exercises.add(new Exercise(1134, "Hip Thrust", "Normal", "Gain Muscle", false, 4, 10, 0, 6, "Glutes"));
        exercises.add(new Exercise(1135, "Bulgarian Split Squat", "Overweight", "Gain Muscle", false, 3, 10, 0, 7, "Glutes"));
        exercises.add(new Exercise(1136, "Glute Bridge", "Obese", "Gain Muscle", false, 3, 15, 0, 4, "Glutes"));

        exercises.add(new Exercise(1137, "Glute Bridge", "Underweight", "Weight Loss", false, 3, 15, 0, 4, "Glutes"));
        exercises.add(new Exercise(1138, "Step Up", "Normal", "Weight Loss", false, 3, 12, 0, 5, "Glutes"));
        exercises.add(new Exercise(1139, "Glute Kickback", "Overweight", "Weight Loss", false, 3, 12, 0, 4, "Glutes"));
        exercises.add(new Exercise(1140, "Glute Bridge", "Obese", "Weight Loss", false, 3, 12, 0, 4, "Glutes"));

        exercises.add(new Exercise(1141, "Glute Bridge", "Underweight", "Maintenance", false, 3, 15, 0, 4, "Glutes"));
        exercises.add(new Exercise(1142, "Hip Thrust", "Normal", "Maintenance", false, 3, 10, 0, 6, "Glutes"));
        exercises.add(new Exercise(1143, "Step Up", "Overweight", "Maintenance", false, 3, 12, 0, 5, "Glutes"));
        exercises.add(new Exercise(1144, "Glute Bridge", "Obese", "Maintenance", false, 3, 12, 0, 4, "Glutes"));


        /*
         * =========================================================
         * CALVES
         * =========================================================
         */

        exercises.add(new Exercise(1145, "Standing Calf Raise", "Underweight", "Lean", false, 3, 15, 0, 3, "Calves"));
        exercises.add(new Exercise(1146, "Seated Calf Raise", "Normal", "Lean", false, 3, 15, 0, 4, "Calves"));
        exercises.add(new Exercise(1147, "Single Leg Calf Raise", "Overweight", "Lean", false, 3, 12, 0, 4, "Calves"));
        exercises.add(new Exercise(1148, "Standing Calf Raise", "Obese", "Lean", false, 3, 12, 0, 3, "Calves"));

        exercises.add(new Exercise(1149, "Standing Calf Raise", "Underweight", "Gain Muscle", false, 3, 15, 0, 3, "Calves"));
        exercises.add(new Exercise(1150, "Seated Calf Raise", "Normal", "Gain Muscle", false, 3, 15, 0, 4, "Calves"));
        exercises.add(new Exercise(1151, "Single Leg Calf Raise", "Overweight", "Gain Muscle", false, 3, 12, 0, 4, "Calves"));
        exercises.add(new Exercise(1152, "Standing Calf Raise", "Obese", "Gain Muscle", false, 3, 12, 0, 3, "Calves"));

        exercises.add(new Exercise(1153, "Jump Rope", "Underweight", "Weight Loss", true, 3, 0, 60, 5, "Calves"));
        exercises.add(new Exercise(1154, "Jump Rope", "Normal", "Weight Loss", true, 3, 0, 60, 5, "Calves"));
        exercises.add(new Exercise(1155, "Calf Jump", "Overweight", "Weight Loss", false, 3, 15, 0, 5, "Calves"));
        exercises.add(new Exercise(1156, "Standing Calf Raise", "Obese", "Weight Loss", false, 3, 12, 0, 3, "Calves"));

        exercises.add(new Exercise(1157, "Single Leg Calf Raise", "Underweight", "Maintenance", false, 3, 12, 0, 4, "Calves"));
        exercises.add(new Exercise(1158, "Seated Calf Raise", "Normal", "Maintenance", false, 3, 15, 0, 4, "Calves"));
        exercises.add(new Exercise(1159, "Single Leg Calf Raise", "Overweight", "Maintenance", false, 3, 12, 0, 4, "Calves"));
        exercises.add(new Exercise(1160, "Standing Calf Raise", "Obese", "Maintenance", false, 3, 12, 0, 3, "Calves"));
    }


    /*
     * =========================================================
     * TRAVERSAL
     * =========================================================
     */

    void traverseExercise(){

        for(int i = 0; i < exercises.size(); i++){

            Exercise currentExercise = exercises.get(i);

            System.out.println(currentExercise.getExerciseName());
        }
    }


    /*
     * =========================================================
     * SEARCH BY EXERCISE NAME
     * =========================================================
     */

    void searchExercise(String searchName){

        boolean flag = false;

        for(int i = 0; i < exercises.size(); i++){

            Exercise currentExercise = exercises.get(i);

            if(searchName.equals(currentExercise.getExerciseName())){

                System.out.println(currentExercise.getExerciseName());

                flag = true;

                break;
            }
        }

        if(flag == false){

            System.out.println("No excercise");
        }
    }


    /*
     * =========================================================
     * SEARCH BY MUSCLE GROUP
     * =========================================================
     */

    void searchMuscleGroup(String searchMuscle){

        boolean flag = false;

        for(int i = 0; i < exercises.size(); i++){

            Exercise muscleGroup = exercises.get(i);

            if(searchMuscle.equals(muscleGroup.getTargetMuscle())){

                System.out.println(muscleGroup);

                flag = true;
            }
        }

        if(flag == false){

            System.out.println("Cannot find what are you looking for!");
        }
    }


    /*
     * =========================================================
     * SEARCH BY DIFFICULTY + BODY TYPE
     * =========================================================
     */

    void searchDiffultyTarget(String difficulty, String targetBody){

        boolean flag = false;

        for(int i = 0; i < exercises.size(); i++){

            Exercise find = exercises.get(i);

            /*
             * NOTE:
             * getBaseDifficulty() returns an int.
             * This method originally accepted String difficulty.
             *
             * The comparison below keeps your original method,
             * but converts the difficulty to String.
             */

            if(difficulty.equals(String.valueOf(find.getBaseDifficulty()))
                    && targetBody.equals(find.getBodyTypeTarget())){

                System.out.println(
                        find.getBaseDifficulty()
                        + " "
                        + find.getBodyTypeTarget()
                );

                flag = true;
            }
        }

        if(flag == false){

            System.out.println("Cannot find what are you looking for!");
        }
    }


    /*
     * =========================================================
     * SEARCH BY BODY TYPE + GOAL
     * =========================================================
     */

    void searchBodyTarget(String bodyType, String target){

        boolean flag = false;

        for(int i = 0; i < exercises.size(); i++){

            Exercise find = exercises.get(i);

            if(bodyType.equals(find.getBodyTypeTarget())
                    && target.equals(find.getGoalTarget())){

                System.out.println(find);

                flag = true;
            }
        }

        if(flag == false){

            System.out.println("Cannot find what are you looking for!");
        }
    }


    /*
     * =========================================================
     * SELECTION SORT - LOWEST DIFFICULTY
     * =========================================================
     */

    void sortDifficultyLowest(){

        int smallest;

        for(int i = 0; i < exercises.size(); i++){

            smallest = i;

            for(int j = i + 1; j < exercises.size(); j++){

                if(exercises.get(j).getBaseDifficulty()
                        < exercises.get(smallest).getBaseDifficulty()){

                    smallest = j;
                }
            }

            Exercise temp = exercises.get(i);

            exercises.set(i, exercises.get(smallest));

            exercises.set(smallest, temp);
        }
    }


    /*
     * =========================================================
     * SELECTION SORT - HIGHEST DIFFICULTY
     * =========================================================
     */

    void sortDifficultyHighest(){

        int largest;

        for(int i = 0; i < exercises.size(); i++){

            largest = i;

            for(int j = i + 1; j < exercises.size(); j++){

                if(exercises.get(j).getBaseDifficulty()
                        > exercises.get(largest).getBaseDifficulty()){

                    largest = j;
                }
            }

            Exercise temp = exercises.get(i);

            exercises.set(i, exercises.get(largest));

            exercises.set(largest, temp);
        }
    }
}

