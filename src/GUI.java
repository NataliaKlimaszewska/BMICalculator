import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class RoundedTextField extends JTextField {
    private int arcSize = 15;

    public RoundedTextField(int columns) {
        super(columns);
        setOpaque(false);
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcSize, arcSize);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcSize, arcSize);
        g2.dispose();
    }
}

class RoundedButton extends JButton {
    private int arcSize = 20;

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBackground(Color.decode("#01161E"));
        setForeground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcSize, arcSize);
        super.paintComponent(g);
        g2.dispose();
    }
}

public class GUI extends JFrame {
    private RoundedTextField heightField, weightField, ageField;
    private JComboBox<String> genderBox, activityBox, goalBox; // Nowy JComboBox dla celu
    private JLabel resultLabel, caloricDemandLabel;

    public GUI() {
        setTitle("BMI & Caloric Demand Calculator");
        setSize(400, 700); // Zwiększono rozmiar, aby pomieścić dodatkowe pole
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tytuł
        JLabel titleLabel = new JLabel("BMI & Caloric Demand Calculator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBackground(Color.decode("#657a86"));
        titleLabel.setOpaque(true);
        add(titleLabel, BorderLayout.NORTH);

        // Panel główny
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#657a86"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Panel formularza
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode("#657a86"));
        gbc.anchor = GridBagConstraints.LINE_END;

        // Age
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Age:"), gbc);
        ageField = new RoundedTextField(10);
        gbc.gridx = 1;
        formPanel.add(ageField, gbc);

        // Gender
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Gender:"), gbc);
        genderBox = new JComboBox<>(new String[]{"m", "f"});
        gbc.gridx = 1;
        formPanel.add(genderBox, gbc);

        // Height
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Height (cm):"), gbc);
        heightField = new RoundedTextField(10);
        gbc.gridx = 1;
        formPanel.add(heightField, gbc);

        // Weight
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Weight (kg):"), gbc);
        weightField = new RoundedTextField(10);
        gbc.gridx = 1;
        formPanel.add(weightField, gbc);

        // Activity Level
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Activity Level:"), gbc);
        String[] activityLevels = {"Bed rest", "Sedentary", "Light exercise", "Moderate", "Heavy", "Very heavy"};
        activityBox = new JComboBox<>(activityLevels);
        gbc.gridx = 1;
        formPanel.add(activityBox, gbc);

        // Goal (Cel redukcji masy)
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Goal:"), gbc);
        String[] goals = {"Maintain weight", "Weight loss", "Weight gain"};
        goalBox = new JComboBox<>(goals);
        gbc.gridx = 1;
        formPanel.add(goalBox, gbc);

        // Calculate Button
        RoundedButton calculateButton = new RoundedButton("Calculate");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        formPanel.add(calculateButton, gbc);

        // Result Labels
        resultLabel = new JLabel("Your BMI: ");
        gbc.gridy = 7;
        formPanel.add(resultLabel, gbc);

        caloricDemandLabel = new JLabel("Caloric Demand: ");
        gbc.gridy = 8;
        formPanel.add(caloricDemandLabel, gbc);

        // Dodanie formularza do panelu
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(formPanel, gbc);
        add(panel, BorderLayout.CENTER);

        // Akcja przycisku
        calculateButton.addActionListener(e -> calculateResults());

        setVisible(true);
    }

    private void calculateResults() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            int age = Integer.parseInt(ageField.getText());
            String gender = (String) genderBox.getSelectedItem();
            int activityLevel = activityBox.getSelectedIndex() + 1;
            String goal = (String) goalBox.getSelectedItem();

            // Obliczanie BMI
            UserData user = new UserData(weight, height, age, gender);
            double bmi = CalculatorBMI.calculateBMI(weight, height);
            String bmiCategory = CalculatorBMI.getBMICategory(bmi);

            // Obliczanie zapotrzebowania kalorycznego
            CaloricDemand caloricDemand = new CaloricDemand(user);
            double tdee = caloricDemand.calculateCaloricDemand(activityLevel);

            // Dostosowanie zapotrzebowania w zależności od celu
            if (goal.equals("Weight loss")) {
                tdee *= 0.8; // Redukcja o 20%
            } else if (goal.equals("Weight gain")) {
                tdee *= 1.1; // Zwiększenie o 10%
            }

            resultLabel.setText(String.format("BMI: %.2f (%s)", bmi, bmiCategory));
            caloricDemandLabel.setText(String.format("Caloric Demand: %.2f kcal", tdee));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}
