package com.example.simplecalculator.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Validator {

    public String validate(String expression) throws Exception {
        String validExpression = expression.replaceAll("mult\\(", "mul(").trim();
        if (!(validExpression.startsWith("add(") ||
                validExpression.startsWith("sub(") ||
                validExpression.startsWith("mul(") ||
                validExpression.startsWith("div(") ||
                (validExpression.startsWith("let("))))  {

            log.error("Invalid expression: {}", expression);
            throw new Exception("Expression not could not be validated");
        } else {
            return validExpression;
        }
    }
}
