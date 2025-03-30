import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * A custom JTextField class with rounded corners for a better UI experience.
 */
class RoundedTextField extends JTextField {
    private int arcSize = 15; // The arc size for the rounded corners

    /**
     * Constructor to create a RoundedTextField with a specified number of columns.
     *
     * @param columns The number of columns in the text field.
     */
    public RoundedTextField(int columns) {
        super(columns);
        setOpaque(false); // Makes the background transparent
        setBackground(Color.WHITE); // Sets the background color to white
    }

    /**
     * Paints the rounded rectangle background of the text field.
     *
     * @param g The graphics object used for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Smooths the corners
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcSize, arcSize); // Fills a rounded rectangle
        super.paintComponent(g);
        g2.dispose();
    }

    /**
     * Paints the border of the text field with rounded corners.
     *
     * @param g The graphics object used for painting the border.
     */
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Smooths the edges
        g2.setColor(Color.GRAY); // Border color
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcSize, arcSize); // Draws a rounded rectangle border
        g2.dispose();
    }
}

/**
 * A custom JButton class with rounded corners and a custom background color.
 */
class RoundedButton extends JButton {
    private int arcSize = 20; // The arc size for the rounded corners

    /**
     * Constructor to create a RoundedButton with a specified label.
     *
     * @param text The text to display on the button.
     */
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false); // Prevents the default button content area from being filled
        setFocusPainted(false); // Prevents the focus ring from being painted
        setBackground(Color.decode("#2980b9")); // Custom background color
        setForeground(Color.WHITE); // White text color
    }

    /**
     * Paints the rounded rectangle background of the button.
     *
     * @param g The graphics object used for painting the button.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Smooths the corners
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcSize, arcSize); // Fills a rounded rectangle
        super.paintComponent(g);
        g2.dispose();
    }
}

/**
 * The main graphical user interface for BMI and caloric demand calculation.
 */
public class GUI extends JFrame {
    private RoundedTextField heightField, weightField, ageField;
    private JComboBox<String> genderBox, activityBox, goalBox;
    private JLabel resultLabel, caloricDemandLabel;
    private JCheckBox calculateCaloricDemandCheckBox;
    private Icon caloricDemandIcon;

    /**
     * Constructor to initialize the GUI components and layout.
     */
    public GUI() {
        setTitle("BMI & Caloric Demand Calculator");
        setSize(400, 300);
        try {
            setIconImage(ImageIO.read(GUI.class.getResource("img/calories-calculator.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("BMI & Caloric Demand Calculator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 25));
        titleLabel.setForeground(Color.decode("#34495e"));
        titleLabel.setBackground(Color.decode("#e0f7fa"));
        titleLabel.setOpaque(true);
        add(titleLabel, BorderLayout.NORTH);

        // Panel setup
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#e0f7fa"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 1;

        // Form panel setup
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode("#e0f7fa"));
        gbc.anchor = GridBagConstraints.NORTH; // Przesunięcie formularza do góry
        gbc.gridy = 0;
        gbc.weighty = 1; // Zapobiega przesuwaniu formularza w dół

        // Add form panel to the main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1; // Formularz zajmuje więcej miejsca u góry
        panel.add(formPanel, gbc);
        // Age input
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Age:"), gbc);
        ageField = new RoundedTextField(10);
        gbc.gridx = 1;
        formPanel.add(ageField, gbc);

        // Gender input
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Gender:"), gbc);
        genderBox = new JComboBox<>(new String[]{"m", "f"});
        gbc.gridx = 1;
        formPanel.add(genderBox, gbc);

        // Height input
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Height (cm):"), gbc);
        heightField = new RoundedTextField(10);
        gbc.gridx = 1;
        formPanel.add(heightField, gbc);

        // Weight input
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Weight (kg):"), gbc);
        weightField = new RoundedTextField(10);
        gbc.gridx = 1;
        formPanel.add(weightField, gbc);

        // Activity level input
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Activity Level:"), gbc);
        String[] activityLevels = {"Bed rest", "Sedentary", "Light exercise", "Moderate", "Heavy", "Very heavy"};
        activityBox = new JComboBox<>(activityLevels);
        gbc.gridx = 1;
        formPanel.add(activityBox, gbc);

        // Goal input
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Goal:"), gbc);
        String[] goals = {"Maintain weight", "Weight loss", "Weight gain"};
        goalBox = new JComboBox<>(goals);
        gbc.gridx = 1;
        formPanel.add(goalBox, gbc);

        // Caloric demand checkbox
        calculateCaloricDemandCheckBox = new JCheckBox("Calculate Caloric Demand");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        formPanel.add(calculateCaloricDemandCheckBox, gbc);

        // Calculate button
        RoundedButton calculateButton = new RoundedButton("Calculate");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        formPanel.add(calculateButton, gbc);

        // Results labels
        resultLabel = new JLabel("Your BMI: ");
        resultLabel.setVisible(false);
        gbc.gridy = 8;
        formPanel.add(resultLabel, gbc);

        caloricDemandLabel = new JLabel("Caloric Demand: ");
        caloricDemandLabel.setVisible(false);
        gbc.gridy = 9;
        formPanel.add(caloricDemandLabel, gbc);

        // Add form panel to the main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(formPanel, gbc);
        add(panel, BorderLayout.CENTER);

        // Action listener for calculate button
        calculateButton.addActionListener(e -> calculateResults());

        setVisible(true);
    }

    /**
     * Calculates the BMI and caloric demand based on user inputs.
     */
    private void calculateResults() {
        try {
            // Retrieve input data
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            int age = Integer.parseInt(ageField.getText());
            String gender = (String) genderBox.getSelectedItem();
            int activityLevel = activityBox.getSelectedIndex() + 1;
            String goal = (String) goalBox.getSelectedItem();

            // Validation checks
            if (weight < 30 || weight > 560) {
                JOptionPane.showMessageDialog(this, "Please enter a valid weight.");
                return;
            }

            if (height < 50 || height > 300) {
                JOptionPane.showMessageDialog(this, "Please enter a valid height.");
                return;
            }

            if (age < 13 || age > 120) {
                JOptionPane.showMessageDialog(this, "Please enter a valid age.");
                return;
            }

            // Create a UserData object
            UserData user = new UserData(weight, height, age, gender);
            // Calculate BMI
            double bmi = CalculatorBMI.calculateBMI(weight, height);
            String bmiCategory = CalculatorBMI.getBMICategory(bmi);

            // Display BMI result
            resultLabel.setText(String.format("BMI: %.2f (%s)", bmi, bmiCategory));

            // If selected, calculate caloric demand
            if (calculateCaloricDemandCheckBox.isSelected()) {
                CaloricDemand caloricDemand = new CaloricDemand(user);
                double tdee = caloricDemand.calculateCaloricDemand(activityLevel);

                // Adjust TDEE based on goal
                if (goal.equals("Weight loss")) {
                    tdee *= 0.8;
                } else if (goal.equals("Weight gain")) {
                    tdee *= 1.1;
                }

                caloricDemandLabel.setText(String.format("Caloric Demand: %.2f kcal", tdee));
            } else {
                caloricDemandLabel.setText("");
            }
            if (bmi < 18.5) {
                resultLabel.setForeground(Color.decode("#f39c12"));  // Niedowaga - żółty
            } else if (bmi >= 18.5 && bmi < 24.9) {
                resultLabel.setForeground(Color.decode("#27ae60"));   // Waga normalna - zielony
            } else if (bmi >= 25) {
                resultLabel.setForeground(Color.decode("ea3546"));     // Nadwaga - czerwony
            }
            // Make result labels visible
            resultLabel.setText(String.format("BMI: %.2f (%s)", bmi, bmiCategory));
            resultLabel.setVisible(true);
            caloricDemandLabel.setVisible(true);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
        }

    }


}
