package imd0412.parkinglot.calculator;

import static org.junit.Assert.assertEquals;
import java.util.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import imd0412.parkinglot.ParkingLotType;
import imd0412.parkinglot.exception.DateFormatException;

@RunWith(value = Parameterized.class)
public class CalculatorTest
{
	// Parameterized test attributes
	private String checkin;
	private String checkout;
	private ParkingLotType type;
	private float expectedPrice;
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] 
		{
			// SHORTERM
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 1
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 2
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 3
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 4
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 5
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 6
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 7
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 8
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 9
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 10
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 11
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 12
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 13
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 14
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 15
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 16
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 17
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 18
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 19
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 20
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 0}, // Case-Test-id: 21
			
		});
	}
	
	/**
	 * Calculates the staying cost in the parking lot.
	 * 
	 * @param checkin String representing check-in date. String follows the format "yyyy.MM.dd HH:mm".
	 * @param checkout String representing check-out date. String follows the format "yyyy.MM.dd HH:mm".
	 * @param type Enum representing the type of service provided by the airport
	 * @param expectedPrice float representing the service's total cost
	 * @return
	 */
	public CalculatorTest(String checkin, String checkout, ParkingLotType type, float expectedPrice) {
		// Pass the values to parameterized attributes 
		this.checkin = checkin;
		this.checkout = checkout;
		this.type = type;
		this.expectedPrice = expectedPrice;
	}
	
	@Test
	public void checkCalculator() {
		
		// Price variable
		float price = 0;
		
		// Create an object of Calculator
		Calculator calc = new Calculator();
		
		// Case of test
		try {
			price = calc.calculateParkingCost(this.checkin, this.checkout, this.type);
		} catch (DateFormatException e) {
			e.printStackTrace();
		}
		
		// Check the result
		assertEquals(this.expectedPrice, price, 0.01);
	}
}