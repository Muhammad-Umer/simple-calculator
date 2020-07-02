package com.example.simplecalculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComplexExpressionCalculatorShould {

    @Autowired
    private Calculator calculator;

    @Test
    public void calculateNestedExpressions() {
        Map<String, String> listNestedExpressionsWithResults = new HashMap<>();
        listNestedExpressionsWithResults.put("add(1, mult(2, 3))", "7");
        listNestedExpressionsWithResults.put("mult(add(2, 2), div(9, 3))", "12");
        listNestedExpressionsWithResults.put("let(a, 5, add(a, a))", "10");
        listNestedExpressionsWithResults.put("let(a, 5, let(b, mult(a, 10), add(b, a)))", "55");
        listNestedExpressionsWithResults.put("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))", "40");

        listNestedExpressionsWithResults.forEach((key, value) -> {
            try {
                assertEquals(value , calculator.calculate(key));
                System.out.println("This is the expression: " + key + " having value: " + value);
                System.out.println("----------------");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
