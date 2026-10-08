package com.mycompany.gymorig;



public class Exercise {

    

    private int exerciseId;
    private String exerciseName;
    private String bodyTypeTarget; // Underweight, Normal, Overweight, Obese
    private String goalTarget;     // Gain Muscle, Weight Loss, Maintenance
    private boolean isTimeBased;   
    private int sets;
    private int reps;
    private int durationSeconds;   
    private int baseDifficulty;
    private String targetMuscle;

    public Exercise(int id, String exerciseName, String bodyType, String goal, boolean isTime, int sets, int reps, int duration, int difficulty, String targetMuscle) {
        this.exerciseId = id;
        this.exerciseName = exerciseName;
        this.bodyTypeTarget = bodyType;
        this.goalTarget = goal;
        this.isTimeBased = isTime;
        this.sets = sets;
        this.reps = reps;
        this.durationSeconds = duration;
        this.baseDifficulty = difficulty;
        this.targetMuscle = targetMuscle;
    }

   

    public int getExerciseId() { return exerciseId; }
    public String getExerciseName() { return exerciseName; }
    public String getBodyTypeTarget() { return bodyTypeTarget; }
    public String getGoalTarget() { return goalTarget; }
    public boolean isTimeBased() { return isTimeBased; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }
    public int getDurationSeconds() { return durationSeconds; }
    public int getBaseDifficulty() { return baseDifficulty; }
    public String getTargetMuscle() { return targetMuscle; };


    @Override

        public String toString(){
            return getExerciseId() + " " + getExerciseName() + " " + getBodyTypeTarget() + " " + getGoalTarget() + " " + isTimeBased() + " " + getSets() 
            + " " + getReps() + " " + getDurationSeconds() + " " + getBaseDifficulty() + " " + getTargetMuscle();
        }

}