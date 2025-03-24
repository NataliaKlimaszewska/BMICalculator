public class CalculatorBMI {
    public static double calculateBMI(double weight, double height) {
        if (height <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Invalid height or weight!");
        }

        double heightInMeters = height / 100;
        return weight / (heightInMeters * heightInMeters);
    }


    public static String getBMICategory(double bmi) {
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
