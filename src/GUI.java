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
    private JComboBox<String> genderBox;
    private JLabel resultLabel, correctedResultLabel, categoryLabel;
    private JTable bmiTable;
    private JScrollPane tableScrollPane;

    public GUI() {
        setTitle("BMI Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("BMI Calculator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBackground(Color.decode("#657a86"));
        titleLabel.setOpaque(true);
        add(titleLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#657a86"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
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

        String[] columnNames = {"BMI Range", "Category"};
        Object[][] data = {
                {"< 16", "Severely Underweight"},
                {"16.00 - 18.4", "Underweight"},
                {"18.5 - 24.9", "Normal"},
                {"25.0 - 29.9", "Overweight"},
                {"30.0 - 34.9", "Moderately Obese"},
                {"35.0 -39.9", "Severely Obese"},
                {">= 40.00", "Morbidly Obese"}
        };

        bmiTable = new JTable(data, columnNames);
        bmiTable.setEnabled(false);
        bmiTable.setRowHeight(25);
        bmiTable.setPreferredScrollableViewportSize(new Dimension(350, 150));
        bmiTable.setFillsViewportHeight(true);
        bmiTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? Color.decode("#657a86") : Color.WHITE);
                return c;
            }
        });

        tableScrollPane = new JScrollPane(bmiTable);
        tableScrollPane.setVisible(false);
        gbc.gridy = 1;
        panel.add(tableScrollPane, gbc);
        add(panel, BorderLayout.CENTER);

        calculateButton.addActionListener(e -> {
            tableScrollPane.setVisible(true);
            revalidate();
            repaint();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
