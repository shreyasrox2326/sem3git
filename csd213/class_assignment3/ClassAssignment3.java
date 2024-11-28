package sem3git.csd213.class_assignment3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassAssignment3 {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Future Value Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        // Labels and text fields
        JLabel investmentLabel = new JLabel("Investment Amount ($):");
        JTextField investmentField = new JTextField();

        JLabel interestRateLabel = new JLabel("monthly Interest Rate (%):");
        JTextField interestRateField = new JTextField();

        JLabel yearsLabel = new JLabel("Number of Years:");
        JTextField yearsField = new JTextField();

        JLabel futureValueLabel = new JLabel("Future Value ($):");
        JTextField futureValueField = new JTextField();
        futureValueField.setEditable(false);

        // Submit button
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double investment = Double.parseDouble(investmentField.getText());
                    double monthlyInterestRate = Double.parseDouble(interestRateField.getText()) / 100;
                    double years = Double.parseDouble(yearsField.getText());

                    double futureValue = investment * Math.pow(1 + monthlyInterestRate, years * 12);
                    futureValueField.setText(String.format("%.2f", futureValue));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the frame
        frame.add(investmentLabel);
        frame.add(investmentField);

        frame.add(interestRateLabel);
        frame.add(interestRateField);

        frame.add(yearsLabel);
        frame.add(yearsField);

        frame.add(futureValueLabel);
        frame.add(futureValueField);

        frame.add(new JLabel()); // Placeholder for spacing
        frame.add(calculateButton);

        // Display the frame
        frame.setVisible(true);
    }
}
