import java.util.Scanner;

class CaloricDemand {
    private UserData userData;

    public CaloricDemand(UserData userData) {
        this.userData = userData;
    }

    public double calculateCaloricDemand(int option) {
        double weight = userData.getWeight();
        double height = userData.getHeight();
        int age = userData.getAge();
        String gender = userData.getGender();

        double BMR;
        if (gender.equalsIgnoreCase("m")) {
            BMR = 88.36 + (13.4 * weight) + (4.8 * height) - (5.7 * age);
        } else if (gender.equalsIgnoreCase("f")) {
            BMR = 447.6 + (9.2 * weight) + (3.1 * height) - (4.3 * age);
        } else {
            throw new IllegalArgumentException("Invalid gender");
        }

        double activityLevel;
        switch (option) {
            case 1: activityLevel = 1.0; break;
            case 2: activityLevel = 1.2; break;
            case 3: activityLevel = 1.375; break;
            case 4: activityLevel = 1.55; break;
            case 5: activityLevel = 1.725; break;
            case 6: activityLevel = 1.9; break;
            default:
                throw new IllegalArgumentException("Invalid option");
        }

        return BMR * activityLevel;
    }
}
