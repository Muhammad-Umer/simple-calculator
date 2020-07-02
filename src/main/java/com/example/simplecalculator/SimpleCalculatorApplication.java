package com.example.simplecalculator;

import ch.qos.logback.classic.Level;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.LogLevel;
import org.springframework.context.ApplicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class SimpleCalculatorApplication {
    private final Calculator calculator;

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = SpringApplication.run(SimpleCalculatorApplication.class, args);
        Calculator calculator = applicationContext.getBean(Calculator.class);

        String expression = initExpression(args);
        System.out.println("This is the result: " + calculator.calculate(expression));
    }

    private static String initExpression(String[] args) throws Exception {
        if (args.length == 0 || StringUtils.isEmpty(args[0])) {
            throw new Exception("No expression found.");
        }
        String expression = args[0];

        if (args.length > 1 && !StringUtils.isEmpty(args[1])) {
            log.info("Logging Level......: " + args[1]);
            try {
                ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger("com.example.simplecalculator");
                logger.setLevel(Level.toLevel(args[1]));
            } catch (Exception e) {
                throw new Exception("Logging Level could not be set. Aborting.....", e);
            }
        }
        return expression;
    }
}
