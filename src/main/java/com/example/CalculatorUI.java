package com.example;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CalculatorUI extends JFrame {

    private JTextField display;
    private Calculator calculator = new Calculator();
    private boolean startNewNumber = true;

    public CalculatorUI() {
        setTitle("Calculatrice");
        setSize(320, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fond général
        getContentPane().setBackground(new Color(240, 240, 240));
        setLayout(new BorderLayout(10, 10));

        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Segoe UI", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(Color.WHITE);
        display.setForeground(new Color(33, 33, 33));
        display.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(240, 240, 240));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "C", "+",
                "="
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        int row = 0, col = 0;
        for (int i = 0; i < buttons.length; i++) {
            String text = buttons[i];
            JButton button = new JButton(text);
            button.setFont(new Font("Segoe UI", Font.BOLD, 20));
            button.setForeground(new Color(33, 33, 33));
            button.setBackground(new Color(220, 220, 220));
            button.setFocusPainted(false);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setBorder(new RoundedBorder(10));

            button.addActionListener(this::onButtonClick);

            gbc.gridx = col;
            gbc.gridy = row;

            if (text.equals("=")) {
                gbc.gridwidth = 4;
                buttonPanel.add(button, gbc);
                break;
            } else {
                gbc.gridwidth = 1;
                buttonPanel.add(button, gbc);
            }

            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }

        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void onButtonClick(ActionEvent e) {
        String cmd = ((JButton) e.getSource()).getText();

        if ("0123456789.".contains(cmd)) {
            if (startNewNumber) {
                display.setText(cmd.equals(".") ? "0." : cmd);
                startNewNumber = false;
            } else {
                if (cmd.equals(".") && display.getText().contains(".")) {
                    return;
                }
                display.setText(display.getText() + cmd);
            }
        } else if ("+-*/=".contains(cmd)) {
            try {
                double operand = Double.parseDouble(display.getText());
                calculator.applyOperator(operand, cmd);
                if (calculator.hasError()) {
                    display.setText("Erreur");
                } else {
                    display.setText(String.valueOf(calculator.getCurrentResult()));
                }
            } catch (ArithmeticException ex) {
                display.setText("Erreur : " + ex.getMessage());
            } catch (NumberFormatException ex) {
                display.setText("Erreur de format");
            }
            startNewNumber = true;
        } else if ("C".equals(cmd)) {
            calculator.clear();
            display.setText("0");
            startNewNumber = true;
        }
    }

    // Classe interne pour coins arrondis des boutons
    private static class RoundedBorder extends AbstractBorder {
        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(180, 180, 180));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 1, this.radius + 1);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = this.radius + 1;
            return insets;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorUI calcUI = new CalculatorUI();
            calcUI.setVisible(true);
        });
    }
}
