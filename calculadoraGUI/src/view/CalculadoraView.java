package view;

import model.CalculadoraModel;
import exceptions.CalculadoraException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraView extends JFrame {
    private JTextField textField;

    public CalculadoraView() {
        setTitle("Calculadora");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300,400));

        textField = new JTextField();
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 4));
        add(panel, BorderLayout.CENTER);

        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new NumberButtonListener());
            panel.add(numberButtons[i]);
        }

        JButton[] operatorButtons = new JButton[4];
        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");
        for (int i = 0; i < 4; i++) {
            operatorButtons[i].addActionListener(new OperatorButtonListener());
            panel.add(operatorButtons[i]);
        }

        JButton equalsButton = new JButton("=");
        equalsButton.addActionListener(new EqualsButtonListener());
        panel.add(equalsButton);

        JButton clearButton = new JButton("C");
        clearButton.addActionListener(new ClearButtonListener());
        panel.add(clearButton);
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void setInputText(String text) {
        textField.setText(text);
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String number = button.getText();
            textField.setText(textField.getText() + number);
        }
    }

    private class OperatorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String operator = button.getText().trim().substring(0, 1);
            String inputText = textField.getText();
            if (!inputText.isEmpty()) {
                textField.setText(inputText + operator);
            }
        }
    }

    private class EqualsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CalculadoraModel model = new CalculadoraModel();
            model.setInputExpression(textField.getText());
            try {
                double resultado = model.calcular();
                textField.setText(String.valueOf(resultado));
            } catch (CalculadoraException ex) {
                displayErrorMessage(ex.getMensagemErro());
                setInputText("");

            }
        }
    }


    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        CalculadoraView view = new CalculadoraView();
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.pack();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}
