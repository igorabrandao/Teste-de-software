package imd0412.parkinglot.calculator;

import imd0412.parkinglot.ParkingLotType;
import imd0412.parkinglot.exception.DateFormatException;

public class CalculatorDrive {

	public static void main(String[] args) {
		
		// Create an object of Calculator
		Calculator calc = new Calculator();
		
		// Run some instances of Calculator
		try {
			calc.calculateParkingCost("2017.08.28 12:30", "2017.08.28 13:45", ParkingLotType.ShortTerm);
		} catch (DateFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}