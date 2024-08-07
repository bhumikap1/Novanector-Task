import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PercentageCalculator {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Percentage Calculator");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the panel
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // Set frame visibility
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel instructionsLabel = new JLabel("Select Calculation Type:");
        instructionsLabel.setBounds(10, 20, 200, 25);
        panel.add(instructionsLabel);

        String[] calculations = {"Calculate Percentage", "Percentage Inc/Dec", "Find Whole"};
        JComboBox<String> calculationComboBox = new JComboBox<>(calculations);
        calculationComboBox.setBounds(200, 20, 170, 25);
        panel.add(calculationComboBox);

        JLabel value1Label = new JLabel("Value 1:");
        value1Label.setBounds(10, 50, 80, 25);
        panel.add(value1Label);

        JTextField value1Text = new JTextField(20);
        value1Text.setBounds(100, 50, 165, 25);
        panel.add(value1Text);

        JLabel value2Label = new JLabel("Value 2:");
        value2Label.setBounds(10, 80, 80, 25);
        panel.add(value2Label);

        JTextField value2Text = new JTextField(20);
        value2Text.setBounds(100, 80, 165, 25);
        panel.add(value2Text);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 110, 150, 25);
        panel.add(calculateButton);

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setBounds(10, 140, 80, 25);
        panel.add(resultLabel);

        JTextField resultText = new JTextField(20);
        resultText.setBounds(100, 140, 165, 25);
        resultText.setEditable(false);
        panel.add(resultText);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double value1 = Double.parseDouble(value1Text.getText());
                    double value2 = Double.parseDouble(value2Text.getText());
                    String calculationType = (String) calculationComboBox.getSelectedItem();
                    double result = performCalculation(value1, value2, calculationType);
                    resultText.setText(Double.toString(result));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private static double performCalculation(double value1, double value2, String calculationType) throws Exception {
        switch (calculationType) {
            case "Calculate Percentage":
                return (value1 * value2) / 100;
            case "Percentage Increase/Decrease":
                return ((value2 - value1) / value1) * 100;
            case "Find Whole":
                if (value2 == 0) throw new Exception("Percentage cannot be zero");
                return (value1 * 100) / value2;
            default:
                throw new Exception("Unknown calculation type");
        }
    }
}
