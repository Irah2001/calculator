package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    public void testAddition() {
        double a = 2;
        double b = 3;
        double expected = 5.0;
        double result = calculator.add(a, b);
        assertEquals(expected, result);
    }

    @Test
    public void testSoustraction() {
        double a = 5;
        double b = 3;
        double expected = 2.0;
        double result = calculator.subtract(a, b);
        assertEquals(expected, result);
    }

    @Test
    public void testMultiplication() {
        double a = 2;
        double b = 3;
        double expected = 6.0;
        double result = calculator.multiply(a, b);
        assertEquals(expected, result);
    }

    @Test
    public void testDivision() {
        double a = 6;
        double b = 3;
        double expected = 2.0;
        double result = calculator.divide(a, b);
        assertEquals(expected, result);
    }

    @Test
    public void testDivisionParZero() {
        double a = 4;
        double b = 0;
        assertThrows(ArithmeticException.class, () -> calculator.divide(a, b));
    }

    @Test
    public void testCalculateAvecOperateurInvalide() {
        double a = 1;
        double b = 2;
        String invalidOperator = "%";
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(a, b, invalidOperator));
    }
}
