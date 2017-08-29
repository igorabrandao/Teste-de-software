package imd0412.parkinglot.calculator;

import imd0412.parkinglot.ParkingLotType;
import imd0412.parkinglot.exception.DateFormatException;

public class CalculatorDrive {

	public static void main(String[] args) {
		
		// Create an object of Calculator
		Calculator calc = new Calculator();
		
		// Price variable
		float price = 0;
		
		// Run some instances of Calculator
		try {
			price = calc.calculateParkingCost("2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm);
			
			System.out.println("The calculated price is:\t R$" + price);
		} catch (DateFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}