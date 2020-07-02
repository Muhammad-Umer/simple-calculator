# simple-calculator

[![Build Status](https://travis-ci.org/Muhammad-Umer/simple-calculator.svg?branch=master)](https://travis-ci.org/Muhammad-Umer/simple-calculator)

A calculator for evaluating nested mathematical expressions written in English

## Overview
This is a simple calculator through which mathematical expressions could be evaluated and results sent
back to the console. 
Main mathematical functions which are catered are as under 
1) Addition
2) Subtraction
3) Multiplication
4) Division

Apart from these, there is another expression called `let` which is used for assigning data
to a variable and then using it for evaluating further mathematical functions. 

## Examples
Some of the example inputs and respective output is given below: 

| Input | Output |
|---|:-----------:|
| add(1, 2) | 3 |
| add(1, mult(2, 3)) | 7 | 
| mult(add(2, 2), div(9, 3)) | 12 |
| let(a, 5, add(a, a)) | 10 |
| let(a, 5, let(b, mult(a, 10), add(b, a))) | 55 |
| let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)) | 40 |

## Explanation of Example 
- The general expression is in the format => `expr(subExpr ...)` where `expr` is an expression
and `subExpr...` are multiple sub-expressions within an expression.
- A sub-expression can be either a number of an expression
    1. `add(1, 3)` => `expr == add(` and `subExpr.. == [1, 2]`
    2. `mult(sub(1, 3), 3)` => `expr == mult(` and `subExpr.. == [sub(1, 3), 3]`
- A `let` operator for assigning values to variables
    1. `let(variable, value, expression(variable))`
    2. `let(a, 5, add(a, a))` => `variable == a` and `value == 5` and `expression(variable) == add(a, a)`
    
    
## Solution Principle
1. Validate the expression and trim any spaces
2. Extract an isolated block from an expression and evaluation that block recursively.
3. Base cases are the direct mathematical evaluations. `add(5, 3) is a base case since 
it would return mathematical result rather than expression`.
4. Starting point is to identify the mathematical expression so which would be the first
evaluation in recursion.
5. `evaluation of add(expression1, expression2)` is equvivalant to `evaluation of 
expression1(subExpr...) + expression1(subExpr2...)`

## How to Run
`java -jar simple-calculator-0.0.1-SNAPSHOT.jar arg1 arg 2` where:
- arg1 = Mathematical Expression `(let(a, 5, add(a, a)))`
- arg2 = Log Level `(ERROR, WARN, INFO, DEBUG, TRACE, ALL)`

## Built With
 
 * [IntelliJ](https://www.jetbrains.com/idea/) - IDE used for Development
 * [Java](https://java.com/) -  Project development language
 * [Travis CI](https://travis-ci.org/) - CI for the project
 
## Authors
 
 * **Muhammad Umer** 
 
 
## License
 
 This project is licensed under the `MIT License` - see the [LICENSE](LICENSE) file for details
