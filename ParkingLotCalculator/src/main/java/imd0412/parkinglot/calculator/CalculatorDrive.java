package imd0412.parkinglot.calculator;

import imd0412.parkinglot.ParkingLotType;
import imd0412.parkinglot.exception.*;

public class CalculatorDrive {

	public static void main(String[] args) {
		
		// Create an object of Calculator
		Calculator calc = new Calculator();
		
		// Price variable
		float price = 0;
		
		// Run some instances of Calculator
		try {
			price = calc.calculateParkingCost("2015.01.28 17:24", "2015.01.28 18:49", ParkingLotType.ShortTerm);
			
			System.out.println("The calculated price is:\t R$" + price);
		} catch (DateFormatException e) {
			e.printStackTrace();
		}
		catch (InvalidDataException e) {
			e.printStackTrace();
		}
	}
}