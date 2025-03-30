import javax.swing.*;

/**
 * The BMIMain class is the entry point for the BMI and Caloric Demand Calculator application.
 * It initializes and displays the GUI when the application is run.
 */
public class BMIMain {
    /**
     * The main method that is the entry point of the application.
     * It uses SwingUtilities.invokeLater to ensure that the GUI is created and updated on the Event Dispatch Thread (EDT).
     * This is necessary for thread safety in Swing applications.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensures that the GUI is created on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
