package com.example.simplecalculator.parser;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

@Component
public class ExpressionParser {

    public Pair<String, String> getSubExpressions(String expression) {
        int firstComma = getBlockIdx(expression);
        String p1 = expression.substring(0, firstComma).trim();
        String p2 = expression.substring(firstComma + 1).trim();

        return Pair.of(p1, p2);
    }

    public String parseBlock(String x) {
        return x.substring(4, getBlockIdx(x));
    }

    public int getBlockIdx(String x) {
        if (!x.matches("(add\\(|sub\\(|mul\\(|div\\(|let\\().*")) {
            return x.indexOf(',');
        }

        int count = 1;
        int i = 4;
        for (; i < x.length(); ++i) {
            if (x.charAt(i) == '(') {
                count++;
            } else if (x.charAt(i) == ')') {
                count--;
            }

            if (count == 0) {
                break;
            }
        }

        if (x.length() > i+2 && x.charAt(i+1) == ',') {
            ++i;
        }
        return i;
    }
}
