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
			// SHORT-TERM
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.ShortTerm, 606},	// Case-Test-id: 1
			{"2015.01.28 17:24", "2015.01.28 18:49", ParkingLotType.ShortTerm, 10}, 	// Case-Test-id: 2
			{"2016.02.28 09:45", "2016.02.28 15:38", ParkingLotType.ShortTerm, 18}, 	// Case-Test-id: 3
			{"2017.02.28 10:32", "2017.03.01 11:30", ParkingLotType.ShortTerm, 112}, 	// Case-Test-id: 4
			{"2014.05.29 19:05", "2014.05.29 21:54", ParkingLotType.ShortTerm, 12}, 	// Case-Test-id: 5
			{"2012.02.29 03:36", "2012.02.29 05:28", ParkingLotType.ShortTerm, 10}, 	// Case-Test-id: 6
			{"2017.03.30 08:10", "2017.03.30 10:15", ParkingLotType.ShortTerm, 12}, 	// Case-Test-id: 7
			{"2017.04.30 16:27", "2017.04.30 18:03", ParkingLotType.ShortTerm, 10}, 	// Case-Test-id: 8
			{"2016.12.30 07:09", "2017.01.10 20:01", ParkingLotType.ShortTerm, 1030}, 	// Case-Test-id: 9
			{"2010.10.31 04:21", "2010.10.31 17:12", ParkingLotType.ShortTerm, 32}, 	// Case-Test-id: 10
			{"2016.12.31 21:30", "2016.12.31 23:15", ParkingLotType.ShortTerm, 10}, 	// Case-Test-id: 11
			
			// LONG-TERM
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.LongTerm, 370},		// Case-Test-id: 1
			{"2015.01.28 17:24", "2015.01.28 18:49", ParkingLotType.LongTerm, 70}, 		// Case-Test-id: 2
			{"2016.02.28 09:45", "2016.02.28 15:38", ParkingLotType.LongTerm, 70}, 		// Case-Test-id: 3
			{"2017.02.28 10:32", "2017.03.01 11:30", ParkingLotType.LongTerm, 120}, 	// Case-Test-id: 4
			{"2014.05.29 19:05", "2014.05.29 21:54", ParkingLotType.LongTerm, 70}, 		// Case-Test-id: 5
			{"2012.02.29 03:36", "2012.02.29 05:28", ParkingLotType.LongTerm, 70}, 		// Case-Test-id: 6
			{"2017.03.30 08:10", "2017.03.30 10:15", ParkingLotType.LongTerm, 70}, 		// Case-Test-id: 7
			{"2017.04.30 16:27", "2017.04.30 18:03", ParkingLotType.LongTerm, 70}, 		// Case-Test-id: 8
			{"2016.12.30 07:09", "2017.01.10 20:01", ParkingLotType.LongTerm, 520}, 	// Case-Test-id: 9
			{"2010.10.31 04:21", "2010.10.31 17:12", ParkingLotType.LongTerm, 70}, 		// Case-Test-id: 10
			{"2016.12.31 21:30", "2016.12.31 23:15", ParkingLotType.LongTerm, 70}, 		// Case-Test-id: 11
			
			// VIP
			{"2017.02.15 10:52", "2017.02.21 16:35", ParkingLotType.VIP, 500}, 			// Case-Test-id: 1
			{"2015.01.28 17:24", "2015.01.28 18:49", ParkingLotType.VIP, 500}, 			// Case-Test-id: 2
			{"2016.02.28 09:45", "2016.02.28 15:38", ParkingLotType.VIP, 500}, 			// Case-Test-id: 3
			{"2017.02.28 10:32", "2017.03.01 11:30", ParkingLotType.VIP, 500}, 			// Case-Test-id: 4
			{"2014.05.29 19:05", "2014.05.29 21:54", ParkingLotType.VIP, 500}, 			// Case-Test-id: 5
			{"2012.02.29 03:36", "2012.02.29 05:28", ParkingLotType.VIP, 500}, 			// Case-Test-id: 6
			{"2017.03.30 08:10", "2017.03.30 10:15", ParkingLotType.VIP, 500}, 			// Case-Test-id: 7
			{"2017.04.30 16:27", "2017.04.30 18:03", ParkingLotType.VIP, 500}, 			// Case-Test-id: 8
			{"2016.12.30 07:09", "2017.01.10 20:01", ParkingLotType.VIP, 900}, 			// Case-Test-id: 9
			{"2010.10.31 04:21", "2010.10.31 17:12", ParkingLotType.VIP, 500}, 			// Case-Test-id: 10
			{"2016.12.31 21:30", "2016.12.31 23:15", ParkingLotType.VIP, 500}, 			// Case-Test-id: 11
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