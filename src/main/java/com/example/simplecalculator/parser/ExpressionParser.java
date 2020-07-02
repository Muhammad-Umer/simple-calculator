package com.example.simplecalculator.parser;

import org.springframework.stereotype.Component;

@Component
public class ExpressionParser {

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
