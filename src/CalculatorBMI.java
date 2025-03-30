/**
 * A class to calculate Body Mass Index (BMI) and categorize it into different BMI categories.
 */
public class CalculatorBMI {

    /**
     * Calculates the Body Mass Index (BMI) based on the provided weight and height.
     *
     * @param weight The weight of the person in kilograms.
     * @param height The height of the person in centimeters.
     * @return The calculated BMI value.
     * @throws IllegalArgumentException If weight or height is less than or equal to zero.
     */
    public static double calculateBMI(double weight, double height) {
        // Ensure the weight and height are positive numbers
        if (height <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Invalid height or weight!");
        }

        // Convert height from centimeters to meters
        double heightInMeters = height / 100;
        // Calculate and return the BMI
        return weight / (heightInMeters * heightInMeters);
    }

    /**
     * Returns the BMI category based on the calculated BMI value.
     *
     * @param bmi The calculated BMI value.
     * @return A string representing the BMI category.
     */
    public static String getBMICategory(double bmi) {
        // Categorize BMI into appropriate range
        if (bmi < 16.00) {
            return "Severely Underweight";
        } else if (bmi >= 16.00 && bmi <= 18.4) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "Normal";
        } else if (bmi >= 25.0 && bmi <= 29.9) {
            return "Overweight";
        } else if (bmi >= 30.0 && bmi <= 34.9) {
            return "Moderately Obese";
        } else if (bmi >= 35.0 && bmi <= 39.9) {
            return "Severely Obese";
        } else {
            return "Morbidly Obese";
        }
    }
}
