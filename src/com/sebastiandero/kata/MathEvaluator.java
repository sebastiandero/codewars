package com.sebastiandero.kata;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathEvaluator {

    public double calculate(String expression) {
        System.out.println(expression);
        ShuntingYard shuntingYard = new ShuntingYard(expression);
        String reversePolishNotation = shuntingYard.getParsedExpression();
        ReversePolishNotationSolver solver = new ReversePolishNotationSolver(reversePolishNotation);
        return solver.getResult();
    }

    public static class ReversePolishNotationSolver {
        Stack<String> stack = new Stack<>();
        String expression;

        public ReversePolishNotationSolver(String expression) {
            this.expression = expression;
        }

        public double getResult() {
            StringTokenizer stringTokenizer = new StringTokenizer(expression, " ");

            while (stringTokenizer.hasMoreTokens()) {
                String token = stringTokenizer.nextToken();
                Operator operator = Operator.of(token);
                if (operator != null) {
                    double operand2 = Double.parseDouble(stack.pop());
                    double operand1 = Double.parseDouble(stack.pop());

                    double result = operator.apply(operand1, operand2);
                    stack.push(String.valueOf(result));
                } else {
                    stack.push(token);
                }
            }
            return Double.parseDouble(stack.pop());
        }
    }

    public static class ShuntingYard {
        Queue<String> outputQueue = new LinkedList<>();
        Stack<Operator> operatorStack = new Stack<>();
        StringBuilder numberBuffer = new StringBuilder();
        String[] tokens;

        public ShuntingYard(String expression) {
            expression = sanizize(expression);
            this.tokens = expression.split("");
            handleExpression();
        }

        private String sanizize(String expression) {
            String sanitaryString = expression;

            Pattern pattern = Pattern.compile("[+\\-*/](?: *)(-\\((.*)\\))");
            Matcher matcher = pattern.matcher(expression);

            if (matcher.find()) {
                String newGroup = String.format("(0 - (%s))", matcher.group(2));
                sanitaryString = sanitaryString.replace(matcher.group(1), newGroup);
                sanitaryString = sanizize(sanitaryString);
            }

            pattern = Pattern.compile("\\((?: *)(-\\((.*)\\))\\)");
            matcher = pattern.matcher(expression);
            if (matcher.find()) {
                String newGroup = String.format("(0 - (%s))", matcher.group(2));
                sanitaryString = sanitaryString.replace(matcher.group(1), newGroup);
                sanitaryString = sanizize(sanitaryString);
            }
            return sanitaryString;
        }

        String getParsedExpression() {
            return pollWholeOutput();
        }

        private void handleExpression() {
            boolean wasOperator = false;
            for (int i = 0; i < tokens.length; i++) {
                String token = tokens[i];
                Operator operator = Operator.of(token);

                if (token.matches("\\d|\\.") || (operator != null && (wasOperator || i == 0) && operator.operator.equals("-"))) {
                    numberBuffer.append(token);
                    wasOperator = false;
                } else {
                    clearNumberBuffer();
                    if (operator != null && !operator.operator.equals(")")) {
                        if (!operatorStack.empty()) {
                            Operator topOfStack = operatorStack.peek();
                            while (!operatorStack.empty()
                                    && topOfStack.precedence >= operator.precedence
                                    && !operator.operator.equals("(")) {
                                outputQueue.add(operatorStack.pop().operator);
                                if (!operatorStack.empty()) {
                                    topOfStack = operatorStack.peek();
                                }
                            }
                        }
                        operatorStack.push(operator);
                        wasOperator = true;
                    } else if (operator != null) {
                        if (!operatorStack.empty()) {
                            Operator topOfStack = operatorStack.peek();
                            while (!operatorStack.empty() && !topOfStack.operator.equals("(")) {
                                outputQueue.add(operatorStack.pop().operator);
                                if (!operatorStack.empty()) {
                                    topOfStack = operatorStack.peek();
                                }
                            }
                            operatorStack.pop();
                        }
                    }
                }
                if (i == tokens.length - 1) {
                    clearNumberBuffer();
                    while (!operatorStack.empty()) {
                        outputQueue.add(operatorStack.pop().operator);
                    }
                }
            }
        }

        private void clearNumberBuffer() {
            if (!numberBuffer.toString().trim().equals("")) {
                outputQueue.add(numberBuffer.toString());
                numberBuffer = new StringBuilder();
            }
        }

        private String pollWholeOutput() {
            StringBuilder stringBuilder = new StringBuilder();
            while (!outputQueue.isEmpty()) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.append(" ");
                }
                stringBuilder.append(outputQueue.poll());
            }
            return stringBuilder.toString();
        }
    }

    private static class Operator {
        int precedence;
        String operator;

        public Operator(int precedence, String operator) {
            this.precedence = precedence;
            this.operator = operator;
        }

        static Operator of(String token) {
            switch (token) {
                case "+":
                    return new Operator(0, "+");
                case "-":
                    return new Operator(0, "-");
                case "*":
                    return new Operator(1, "*");
                case "/":
                    return new Operator(1, "/");
                case "(":
                    return new Operator(-1, "(");
                case ")":
                    return new Operator(-1, ")");
            }
            return null;
        }

        double apply(double operand1, double operand2) {
            switch (operator) {
                case "+":
                    return operand1 + operand2;
                case "-":
                    return operand1 - operand2;
                case "*":
                    return operand1 * operand2;
                case "/":
                    return operand1 / operand2;
            }
            return 0;
        }
    }
}
