public class UserData {
    protected double weight;
    protected double height;
    protected int age;
    protected String gender;

    // Konstruktor do użycia w GUI
    public UserData(double weight, double height, int age, String gender) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }

    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
}
