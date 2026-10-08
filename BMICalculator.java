package com.mycompany.gymorig;


public class BMICalculator {

    public double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }

    public String getBodyType(double bmi) {

        if (bmi < 18.5) {
            return "Underweight";

        } else if (bmi < 25) {
            return "Normal";

        } else if (bmi < 30) {
            return "Overweight";

        } else {
            return "Obese";
        }
    }
}
