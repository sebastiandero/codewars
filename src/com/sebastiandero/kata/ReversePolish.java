package com.sebastiandero.kata;

import java.util.Stack;

public class ReversePolish {

    private Stack<Double> operandStack = new Stack<>();

    public double evaluate(String expr) {
        if (!expr.trim().isEmpty()) {
            parseExpression(expr);
        }
        if (operandStack.empty()) {
            return 0;
        }
        return operandStack.pop();
    }

    private void parseExpression(String expression) {
        String[] tokens = expression.split(" ");

        for(String token : tokens) {
            if (isOperator(token)) {
                double number2 = operandStack.pop();
                double number1 = operandStack.pop();

                operandStack.push(calculateResult(number1, number2, token));
            } else {
                operandStack.push(Double.parseDouble(token));
            }
        }
    }

    private double calculateResult(double number1, double number2, String operator) {
        switch (operator) {
            case "*":
                return number1 * number2;
            case "/":
                return number1 / number2;
            case "+":
                return number1 + number2;
            default:
                return number1 - number2;
        }
    }

    private boolean isOperator(String token) {
        boolean result = token.equals("*");
        result |= token.equals("/");
        result |= token.equals("+");
        result |= token.equals("-");
        return result;
    }
}
