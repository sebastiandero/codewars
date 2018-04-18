package com.sebastiandero.kata;

import org.junit.Assert;
import org.junit.Test;

public class MathEvaluatorTest {
    @Test
    public void testShuntingYard() {
        test("3 + 4", "3 4 +");
    }

    @Test
    public void testEvaluator() {
        testEval("3 + 4", 7.0);
    }

    @Test
    public void testShuntingYardNoSpace() {
        test("3+4", "3 4 +");
    }

    @Test
    public void testShuntingYardManyOperators() {
        test("3 + 4 * 2 / ( 1 - ((55)) )", "3 4 2 * 1 55 - / +");
    }

    @Test
    public void testEvalManyOperators() {
        testEval("3+4*2/(1-55)", 2.851851);
    }

    @Test
    public void testEvalNegation() {
        testEval("3 - -3", 6.0);
    }

    @Test
    public void testEvalBracketNegation() {
        testEval("3 - -(3)", 6.0);
    }

    @Test
    public void testEvalDoubleNegation() {
        testEval("3 - -( -3)", 0.0);
    }

    @Test
    public void testAddition() {
        testEval("1+1", 2.0);
    }

    @Test
    public void testSubtraction() {
        testEval("1 - 1", 0.0);

    }

    @Test
    public void testMultiplication() {
        testEval("1* 1", 1.0);

    }

    @Test
    public void testDivision() {
        testEval("1 /1", 1.0);

    }

    @Test
    public void testNegative() {
        testEval("-123", -123.0);
    }
    @Test public void testLiteral() {
        testEval("123", 123.0);
    }

    @Test public void testExpression() {
        testEval("2 /2+3 * 4.75- -6", 21.25);
    }

    @Test public void testSimple() {
        testEval("12* 123", 1476.0);
    }

    @Test public void testComplex() {
        testEval("2 / (2 + 3) * 4.33 - -6", 7.732);
    }

    private void test(String expression, String expected) {
        MathEvaluator.ShuntingYard shuntingYard = new MathEvaluator.ShuntingYard(expression);
        String actual = shuntingYard.getParsedExpression();
        System.out.println(String.format("Expected: %s; Actual: %s", expected, actual));
        Assert.assertEquals(expected, actual);
    }

    private void testEval(String expression, Double expected) {
        MathEvaluator mathEvaluator = new MathEvaluator();
        Double actual = mathEvaluator.calculate(expression);
        System.out.println(String.format("Expected: %s; Actual: %s", expected, actual));
        Assert.assertEquals(expected, actual, 0.1);
    }
}

