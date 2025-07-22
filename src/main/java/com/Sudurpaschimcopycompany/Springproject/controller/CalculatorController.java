package com.Sudurpaschimcopycompany.Springproject.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.Sudurpaschimcopycompany.Springproject.service.CalculatorService;

@Controller
public class CalculatorController {

	// private final CalculatorService calculatorService;

	@Autowired
	private CalculatorService calculatorService;
	/*
	 * @Autowired public CalculatorController(CalculatorService calculatorService) {
	 * this.calculatorService = calculatorService; }
	 */

	// Set the default route to the calculator view
	@GetMapping("/getcalculator")
	public String showCalculator() {
		return "CalculatorForm"; // This refers to the HTML page named "CalculatorForm.html"
	}

	// Endpoint for calculating the result from the expression
	@GetMapping("/calculate")
	@ResponseBody // This annotation ensures the result is returned as JSON
	public CalculationResult calculate(@RequestParam String expression) {
		try {
			double result = calculatorService.evaluateExpression(expression);
			return new CalculationResult(result);
		} catch (Exception e) {
			return new CalculationResult("Error: " + e.getMessage());
		}
	}

	// Inner class for calculation result
	public static class CalculationResult {
		private String result;

		public CalculationResult(double result) {
			this.result = String.valueOf(result);
		}

		public CalculationResult(String errorMessage) {
			this.result = errorMessage;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}
	}
}
