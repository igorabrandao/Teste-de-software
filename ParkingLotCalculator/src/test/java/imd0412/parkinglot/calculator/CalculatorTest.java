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
			// EQUILATERAL
			/*{ 6,6,6, TriangleType.EQUILATERAL },
			
			// ISOCELES
			{ 5,5,4, TriangleType.ISOCELES },
			{ 5,3,4, TriangleType.ISOCELES },
			{ 4,5,5, TriangleType.ISOCELES },
			
			// SCALENE
			{ 5,4,3, TriangleType.SCALENE }, // Case-Test-id: A
			
			// NOT TRIANGLE
			{ 25,3,5, TriangleType.NOT_TRIANGLE }*/
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