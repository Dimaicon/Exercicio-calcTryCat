package model;

import exceptions.CalculadoraException;

public class CalculadoraModel {
    private String inputExpression;

    public void setInputExpression(String inputExpression) {
        this.inputExpression = inputExpression;
    }

    public double calcular() throws CalculadoraException {

        inputExpression = inputExpression.trim();


        String[] partes = inputExpression.split("\\+|-|\\*|/");
        if (partes.length != 2) {
            throw new CalculadoraException(2);
        }
        double operando1 = Double.parseDouble(partes[0]);
        double operando2 = Double.parseDouble(partes[1]);


        char sinal = inputExpression.charAt(partes[0].length());


        switch (sinal) {
            case '+':
                return operando1 + operando2;
            case '-':
                return operando1 - operando2;
            case '*':
                return operando1 * operando2;
            case '/':
                if (operando2 != 0) {
                    return operando1 / operando2;
                } else {
                    throw new CalculadoraException(1);
                }
            default:
                throw new CalculadoraException(2);
        }
    }
}
