package com.example.simplecalculator;

import com.example.simplecalculator.parser.ExpressionParser;
import com.example.simplecalculator.validator.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;

@Component
@AllArgsConstructor
@Slf4j
public class Calculator {

    private final Validator validator;
    private final ExpressionParser expressionParser;

    public String calculate(String expression) throws Exception {
         String validExpression = validator.validate(expression);
         double result = execute(validExpression);

         if (result > (double) Integer.MAX_VALUE || result % 1 != 0) {
             return String.valueOf(result);
         } else {
             return String.valueOf((int) result);
         }
    }

    private double execute(String expression) throws Exception {
        try {
            return execute(expression.trim(), emptyMap());
        } catch (Exception e) {
            log.error("Invalid expression: {}", expression);
            throw new Exception("Expression could not be computed. Aborting.....", e);
        }
    }

    private double execute(String x, Map<String, Double> state) {
        if (x.startsWith("add(")) {
            return add(expressionParser.parseBlock(x), state);
        } else if (x.startsWith("sub(")) {
            return subtract(expressionParser.parseBlock(x), state);
        } else if (x.startsWith("mul(")) {
            return multiply(expressionParser.parseBlock(x), state);
        } else if (x.startsWith("div(")) {
            return divide(expressionParser.parseBlock(x), state);
        } else if (x.startsWith("let(")) {
            return let(expressionParser.parseBlock(x), state);
        } else {
            if (x.matches("^[0-9]*")) {
                return Integer.parseInt(x.trim());
            }
            return state.get(x);
        }
    }

    private double add(String x, Map<String, Double> state) {
        log.info("adding.... {}", x);

        Pair<String, String> subExpressions = expressionParser.getSubExpressions(x);
        return execute(subExpressions.getLeft(), state) + execute(subExpressions.getRight(), state);
    }

    private double subtract(String x, Map<String, Double> state) {
        log.info("subtracting.... {}", x);

        Pair<String, String> subExpressions = expressionParser.getSubExpressions(x);
        return execute(subExpressions.getLeft(), state) - execute(subExpressions.getRight(), state);
    }

    private double multiply(String x, Map<String, Double> state) {
        log.info("multiplying .... {}", x);

        Pair<String, String> subExpressions = expressionParser.getSubExpressions(x);
        return execute(subExpressions.getLeft(), state) * execute(subExpressions.getRight(), state);
    }

    private double divide(String x, Map<String, Double> state) {
        log.info("dividing.... {}", x);

        Pair<String, String> subExpressions = expressionParser.getSubExpressions(x);
        return execute(subExpressions.getLeft(), state) / execute(subExpressions.getRight(), state);
    }

    private double let(String x, Map<String, Double> state) {
        log.info("letting.... {}", x);

        int firstComma = x.indexOf(',');
        int secondComma = expressionParser.getBlockIdx(x.substring(firstComma + 1)) + firstComma + 1;
        String p1 = x.substring(0, firstComma).trim();
        String p2 = x.substring(firstComma + 2, secondComma);
        if (p2.startsWith("add(") || p2.startsWith("sub(") || p2.startsWith("mul(") || p2.startsWith("div(") || (p2.startsWith("let("))) {
            int count = 1;
            int i = firstComma + 6;
            while (i < x.length()) {
                if (x.charAt(i) == '(') {
                    count++;
                } else if (x.charAt(i) == ')') {
                    count--;
                }

                if (count == 0) {
                    break;
                }
                ++i;
            }
            p2 = x.substring(firstComma + 2, i);
        }

        String p3 = x.substring(firstComma + 2 +p2.length() + 2).trim();

        double result = execute(p2, state);
        Map<String, Double> newState = new HashMap<>(state);
        newState.put(p1, result);

        return execute(p3, newState);
    }
}
