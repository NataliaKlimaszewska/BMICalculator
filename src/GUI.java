import javax.swing.*;
import java.awt.*;

class RoundedTextField extends JTextField {
    private int arcSize = 15; // Zaokrąglenie rogów

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

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcSize, arcSize);
        g2.dispose();
    }
}

public class GUI extends JFrame {
    private RoundedTextField heightField, weightField, ageField;
    private JComboBox<String> genderBox;
    private JLabel resultLabel, correctedResultLabel, categoryLabel;

    public GUI() {
        setTitle("BMI Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Wyśrodkowanie okna na ekranie
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#657a86"));
        panel.setLayout(new GridBagLayout()); // Wyśrodkowanie panelu w oknie
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));
        formPanel.setBackground(Color.decode("#657a86"));

        formPanel.add(new JLabel("Age:"));
        ageField = new RoundedTextField(5);
        formPanel.add(ageField);

        formPanel.add(new JLabel("Gender:"));
        genderBox = new JComboBox<>(new String[]{"m", "f"});
        formPanel.add(genderBox);

        formPanel.add(new JLabel("Height (cm):"));
        heightField = new RoundedTextField(5);
        formPanel.add(heightField);

        formPanel.add(new JLabel("Weight (kg):"));
        weightField = new RoundedTextField(5);
        formPanel.add(weightField);

        RoundedButton calculateButton = new RoundedButton("Calculate BMI");
        formPanel.add(calculateButton);

        resultLabel = new JLabel("Your BMI: ");
        formPanel.add(resultLabel);

        correctedResultLabel = new JLabel("Corrected BMI: ");
        formPanel.add(correctedResultLabel);

        categoryLabel = new JLabel("");
        formPanel.add(categoryLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(formPanel, gbc);

        add(panel, BorderLayout.CENTER);

        calculateButton.addActionListener(e -> {
            try {
                double height = Double.parseDouble(heightField.getText());
                double weight = Double.parseDouble(weightField.getText());
                int age = Integer.parseInt(ageField.getText());
                String gender = (String) genderBox.getSelectedItem();

                double bmi = CalculatorBMI.calculateBMI(weight, height);
                String category = CalculatorBMI.getBMICategory(bmi);

                resultLabel.setText(String.format("Your BMI: %.2f", bmi));

                if (age >= 18) {
                    double correctedBMI = CalculatorBMI.calculateCorrectedBMI(bmi, gender, age);
                    correctedResultLabel.setText(String.format("Corrected BMI: %.2f", correctedBMI));
                } else {
                    correctedResultLabel.setText("Corrected BMI: Does not apply to children!");
                }

                categoryLabel.setText("You're in " + category + " category.");

                showBmiTable();

            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid data!");
                correctedResultLabel.setText("");
                categoryLabel.setText(""); // Ukrycie kategorii w razie błędu
            } catch (IllegalArgumentException ex) {
                resultLabel.setText(ex.getMessage());
                correctedResultLabel.setText("");
                categoryLabel.setText(""); // Ukrycie kategorii w razie błędu
            }
        });

        setVisible(true);
    }

    public void showBmiTable() {
        String[]columnNames = {"BMI Range", "Category"};
        Object[][] data = {
                {"< 16 ", "Severely Underweight"},
                {" 16.00 - 18.4", "Underweight"},
                {"18.5 - 24.9", "Normal"},
                {"25.00 - 29.9", "Overweight"},
                {"30.00 - 34.9", "Moderately Obese"},
                {"35.00 - 39.9", "Severely Obese"},
                {">40.00","Morbidly Obese"}
        };
        JTable bmiTable = new JTable(data, columnNames);
        bmiTable.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(bmiTable);
        JDialog dialog = new JDialog(GUI.this, "BMI Calculator", true);
        dialog.setSize(300,200);
        dialog.setLocationRelativeTo(this);
        dialog.add(scrollPane);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
