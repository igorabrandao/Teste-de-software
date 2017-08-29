package imd0412.parkinglot.calculator;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import imd0412.parkinglot.ParkingLotType;
import imd0412.parkinglot.exception.DateFormatException;
import imd0412.parkinglot.exception.InvalidDataException;

@RunWith(value = Parameterized.class)
public class CalculatorTestException {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	// Parameterized test attributes
	private String checkin;
	private String checkout;
	private ParkingLotType type;
	public Class<? extends Exception> expectedException;
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] 
		{
			{"2017.02.29 08:10", "2017.02.29 11:24", ParkingLotType.ShortTerm, InvalidDataException.class},	// Case-Test-id: 14
			{"2006.02.30 15:38", "2006.02.30 17:00", ParkingLotType.ShortTerm, InvalidDataException.class}, // Case-Test-id: 15
			{"2011.02.31 11:00", "2011.02.31 13:10", ParkingLotType.ShortTerm, InvalidDataException.class}, // Case-Test-id: 16
			{"2015.04.31 06:25", "2015.04.31 09:30", ParkingLotType.ShortTerm, InvalidDataException.class}, // Case-Test-id: 17
			{"2017.08.01 16:30", "2017.07.30 19:36", ParkingLotType.ShortTerm, InvalidDataException.class}, // Case-Test-id: 18
			{"2017-08-28 07:10", "2017-08-29 08:00", ParkingLotType.ShortTerm, DateFormatException.class}, 	// Case-Test-id: 19
			{"2018.01.10 14:28", "2018.01.10 15:33", ParkingLotType.ShortTerm, InvalidDataException.class}, // Case-Test-id: 20
			{"2017.05.20 12:32", "2019.02.20 16:41", ParkingLotType.ShortTerm, InvalidDataException.class}, // Case-Test-id: 21
			{"2017.13.05 10:10", "2017.13.05 11:25", ParkingLotType.ShortTerm, InvalidDataException.class}, // Case-Test-id: 22
			{"2017.06.32 17:19", "2017.06.32 18:34", ParkingLotType.ShortTerm, InvalidDataException.class}, // Case-Test-id: 23
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
	public CalculatorTestException(String checkin, String checkout, ParkingLotType type, Class<? extends Exception> expectedException) {
		// Pass the values to parameterized attributes 
		this.checkin = checkin;
		this.checkout = checkout;
		this.type = type;
		this.expectedException = expectedException;
	}
	
	/**
	 * Method to test if a defined exception is thrown
	 * 	
	 * @throws DateFormatException
	 * @throws InvalidDataException
	 */
	@Test
	public void testException() throws DateFormatException, InvalidDataException {
		// Create an object of Calculator
		Calculator calc = new Calculator();
		
	    exception.expect(this.expectedException);
	    
		// Case of test
		calc.calculateParkingCost(this.checkin, this.checkout, this.type);
	}
}