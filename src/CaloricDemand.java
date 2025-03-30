/**
 * This class calculates the caloric demand (TDEE) of a user based on their weight, height, age, gender,
 * and activity level using the Mifflin-St Jeor equation for BMR calculation.
 */
class CaloricDemand {
    private UserData userData; // Holds the user's data (weight, height, age, gender)

    /**
     * Constructor to initialize the CaloricDemand object with the user's data.
     *
     * @param userData The UserData object containing the user's weight, height, age, and gender.
     */
    public CaloricDemand(UserData userData) {
        this.userData = userData;
    }

    /**
     * Calculates the total daily energy expenditure (TDEE) based on the user's BMR and activity level.
     *
     * @param option The activity level option selected by the user:
     *               1 - Bed rest
     *               2 - Sedentary
     *               3 - Light exercise
     *               4 - Moderate exercise
     *               5 - Heavy exercise
     *               6 - Very heavy exercise
     * @return The user's TDEE (caloric demand) based on their BMR and activity level.
     * @throws IllegalArgumentException If an invalid gender or activity level option is provided.
     */
    public double calculateCaloricDemand(int option) {
        double weight = userData.getWeight(); // User's weight in kg
        double height = userData.getHeight(); // User's height in cm
        int age = userData.getAge(); // User's age
        String gender = userData.getGender(); // User's gender

        // Calculate the BMR (Basal Metabolic Rate) using the Mifflin-St Jeor equation
        double BMR;
        if (gender.equalsIgnoreCase("m")) {
            // BMR for males
            BMR = 88.36 + (13.4 * weight) + (4.8 * height) - (5.7 * age);
        } else if (gender.equalsIgnoreCase("f")) {
            // BMR for females
            BMR = 447.6 + (9.2 * weight) + (3.1 * height) - (4.3 * age);
        } else {
            // Invalid gender input
            throw new IllegalArgumentException("Invalid gender");
        }

        // Determine the activity level based on the user's selected option
        double activityLevel;
        switch (option) {
            case 1: activityLevel = 1.0; break; // Bed rest
            case 2: activityLevel = 1.2; break; // Sedentary
            case 3: activityLevel = 1.375; break; // Light exercise
            case 4: activityLevel = 1.55; break; // Moderate exercise
            case 5: activityLevel = 1.725; break; // Heavy exercise
            case 6: activityLevel = 1.9; break; // Very heavy exercise
            default:
                // Invalid activity level input
                throw new IllegalArgumentException("Invalid option");
        }

        // Calculate the TDEE by multiplying BMR with the activity level
        return BMR * activityLevel;
    }
}
