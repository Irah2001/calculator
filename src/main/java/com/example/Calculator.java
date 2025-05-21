package com.example;

public class Calculator {

    private double currentResult = 0;
    private String lastOperator = "";
    private boolean error = false;

    public double getCurrentResult() {
        return currentResult;
    }

    public boolean hasError() {
        return error;
    }

    public void clear() {
        currentResult = 0;
        lastOperator = "";
        error = false;
    }

    public void applyOperator(double operand, String operator) {
        if (error) return;

        try {
            if (lastOperator.isEmpty()) {
                currentResult = operand;
            } else {
                currentResult = calculate(currentResult, operand, lastOperator);
            }
            lastOperator = operator.equals("=") ? "" : operator;
        } catch (ArithmeticException e) {
            error = true;
            throw e;
        }
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) throw new ArithmeticException("Division par zéro");
        return a / b;
    }

    public double calculate(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return add(a, b);
            case "-":
                return subtract(a, b);
            case "*":
                return multiply(a, b);
            case "/":
                return divide(a, b);
            default:
                throw new IllegalArgumentException("Opérateur invalide : " + operator);
        }
    }
}
