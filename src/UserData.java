/**
 * A class representing a user's basic information including weight, height, age, and gender.
 */
public class UserData {
    protected double weight;  // User's weight in kilograms
    protected double height;  // User's height in centimeters
    protected int age;        // User's age in years
    protected String gender;  // User's gender (e.g., "Male", "Female")

    /**
     * Constructor to initialize the user data with weight, height, age, and gender.
     *
     * @param weight The user's weight in kilograms.
     * @param height The user's height in centimeters.
     * @param age The user's age in years.
     * @param gender The user's gender (e.g., "Male", "Female").
     */
    public UserData(double weight, double height, int age, String gender) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }

    /**
     * Gets the user's weight.
     * @return The user's weight in kilograms.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Gets the user's height.
     * @return The user's height in centimeters.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets the user's age.
     * @return The user's age in years.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the user's gender.
     * @return The user's gender as a string (e.g., "Male", "Female").
     */
    public String getGender() {
        return gender;
    }
}
