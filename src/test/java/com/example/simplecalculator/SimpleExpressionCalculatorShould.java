package com.example.simplecalculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SimpleExpressionCalculatorShould {

    @Autowired
    private Calculator calculator;

    @Test
    public void add() throws Exception {
        assertEquals("3" , calculator.calculate("add(1,2)"));
    }

    @Test
    public void subtract() throws Exception {
        assertEquals("-1" , calculator.calculate("sub(1,2)"));
    }

    @Test
    public void multiply() throws Exception {
        assertEquals("2" , calculator.calculate("mult(1,2)"));
    }

    @Test
    public void divide() throws Exception {
        assertEquals("2" , calculator.calculate("div(4,2)"));
    }

    @Test
    public void divideWithDecimal() throws Exception {
        assertEquals("2.5" , calculator.calculate("div(5,2)"));
    }

    @Test
    public void notDivideInvalidExpression() {
        Throwable exception = assertThrows(Exception.class, () -> {
            calculator.calculate("div(0,0[])");
        });

        System.out.println(exception.getMessage());
        assertEquals("Expression could not be computed. Aborting.....", exception.getMessage());
    }

    @Test
    public void notComputeInvalidExpression() {
        Throwable exception = assertThrows(Exception.class, () -> {
            calculator.calculate("1,1");
        });

        System.out.println(exception.getMessage());
        assertEquals("Expression not could not be validated", exception.getMessage());
    }
}
