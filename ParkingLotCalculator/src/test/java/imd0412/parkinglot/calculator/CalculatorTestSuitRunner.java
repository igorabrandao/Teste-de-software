package imd0412.parkinglot.calculator;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class CalculatorTestSuitRunner {

	public static void main(String[] args) {

		// Run all provided tests
		Result result = JUnitCore.runClasses(CalculatorTestSuite.class);
		
		// If some fail happen, prints the log
		for (Failure fail : result.getFailures()) {
			System.out.println(fail.toString());
		}
		
		// All tests was succeeded
		if (result.wasSuccessful()) {
			System.out.println("All tests finished successfully...");
		}
	}
}