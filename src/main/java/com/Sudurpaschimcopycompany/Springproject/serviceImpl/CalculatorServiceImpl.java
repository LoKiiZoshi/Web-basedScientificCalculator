package com.Sudurpaschimcopycompany.Springproject.serviceImpl;

import java.util.Stack;
import org.springframework.stereotype.Service;
import com.Sudurpaschimcopycompany.Springproject.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {  

	@Override
	public double evaluateExpression(String expression) throws Exception {
		// Handle trigonometric and logarithmic functions first
		if (expression.contains("sin")) {
			return Math.sin(Math.toRadians(Double.parseDouble(expression.replace("sin", ""))));
		} else if (expression.contains("cos")) {
			return Math.cos(Math.toRadians(Double.parseDouble(expression.replace("cos", ""))));
		} else if (expression.contains("tan")) {
			return Math.tan(Math.toRadians(Double.parseDouble(expression.replace("tan", ""))));
		} else if (expression.contains("log")) {
			return Math.log10(Double.parseDouble(expression.replace("log", "")));
		} else if (expression.contains("√")) {
			return Math.sqrt(Double.parseDouble(expression.replace("√", "")));
		} else {
			// Handle arithmetic operations (including multiple operators)
			return evalArithmetic(expression);
		}
	}

	private double evalArithmetic(String expression) {
		expression = expression.replaceAll("\\s+", ""); // Remove all whitespaces
		Stack<Double> values = new Stack<>();
		Stack<Character> ops = new Stack<>();
		int i = 0;

		while (i < expression.length()) {
			char ch = expression.charAt(i);

			// Parse numbers (including decimal points)
			if (Character.isDigit(ch) || ch == '.') {
				StringBuilder sbuf = new StringBuilder();
				while (i < expression.length()
						&& (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
					sbuf.append(expression.charAt(i++));
				}
				values.push(Double.parseDouble(sbuf.toString()));
				i--; // Revert index after loop
			} else if (ch == '(') {
				ops.push(ch);
			} else if (ch == ')') {
				while (ops.peek() != '(') {
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));
				}
				ops.pop(); // Remove '(' from ops
			} else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
				// Evaluate the expression with the correct precedence
				while (!ops.isEmpty() && hasPrecedence(ch, ops.peek())) {
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));
				}
				ops.push(ch); // Push the current operator to the stack
			}
			i++;
		}

		// Apply remaining operations in the stack
		while (!ops.isEmpty()) {
			values.push(applyOp(ops.pop(), values.pop(), values.pop()));
		}

		return values.pop(); // Final result
	}

	private boolean hasPrecedence(char op1, char op2) {
		// Left-to-right evaluation of operators with equal precedence
		if (op2 == '(' || op2 == ')')
			return false;
		return ((op1 == '+' || op1 == '-') && (op2 == '+' || op2 == '-'))
				|| ((op1 == '*' || op1 == '/') && (op2 == '*' || op2 == '/'));
	}

	private double applyOp(char op, double b, double a) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			if (b == 0)
				throw new UnsupportedOperationException("Cannot divide by zero");
			return a / b;
		}
		return 0;
	}
}
