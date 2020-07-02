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
        String exp = "let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))";
        String exp1 = "add(1, 2)";

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

        Calculator calculator = applicationContext.getBean(Calculator.class);
        System.out.println(calculator.calculate(expression));
    }
}
