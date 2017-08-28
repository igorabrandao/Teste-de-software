package imd0412.parkinglot.calculator;

import static org.apache.commons.lang3.time.DurationFormatUtils.formatDuration;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;

import imd0412.parkinglot.Constants;
import imd0412.parkinglot.ParkingLotType;
import imd0412.parkinglot.exception.*;

public class Calculator {
	/**
	 * Calculates the staying cost in the parking lot.
	 * 
	 * @param checkin String representing check-in date. String follows the format "yyyy.MM.dd HH:mm".
	 * @param checkout String representing check-out date. String follows the format "yyyy.MM.dd HH:mm".
	 * @param type
	 * @return
	 */
	Float calculateParkingCost(String checkin, String checkout, ParkingLotType type) throws DateFormatException {
		
		// Local variables
		Date check_in = null;
		Date check_out = null;
		
		// Diff variables
		String diff_year = "";
		String diff_month = "";
		String diff_day = "";
		String diff_hour = "";
		String diff_minute = "";
		
		// Calculated price
		float calculated_price = 0;
		
		// ==========================================================================================
		// DATES INPUT HANDLER
		// ==========================================================================================
		
		// Try to converts the check-in to a java.util.Date
		try {
			check_in = Constants.DATE_FORMATTER.parse(checkin);
		} catch (ParseException e) {
			// Message to the user
			String errorMsg = "\nString " + checkin
					+ " está fora do padrão.";
			System.out.println(errorMsg);
			
			// Throws the custom exception
			throw new DateFormatException(errorMsg);
		}
		
		// Try to converts the check-out to a java.util.Date
		try {
			check_out = Constants.DATE_FORMATTER.parse(checkout);
		} catch (ParseException e) {
			// Message to the user
			String errorMsg = "\nString " + check_out
					+ " está fora do padrão.";
			System.out.println(errorMsg);
			
			// Throws the custom exception
			throw new DateFormatException(errorMsg);
		}
		
		// ==========================================================================================
		// DATE DIFFERENCE CALCULATOR HANDLER
		// ==========================================================================================
		
		// Calculates the difference in milliseconds
		long diff = check_out.getTime() - check_in.getTime();

		// Converts the difference into date type
		String duration = formatDuration(diff, Constants.VALID_DATE_FORMAT);
		
		// Split the difference into sections
		Matcher matcher = Constants.DATE_PATTERN.matcher(duration);
		if (matcher.find()) {
			diff_year = matcher.group(1);
			diff_month = matcher.group(2);
			diff_day = matcher.group(3);
			diff_hour = matcher.group(4);
			diff_minute = matcher.group(5);
		}
		
		// ==========================================================================================
		// Calculate the price for each use type
		// ==========================================================================================
		
		// Check the use type
		switch (type) {
			// ==========================================================================================
			// (I) ShortTerm: use just for a few hours
			// ==========================================================================================
			case ShortTerm:
				break;
			// ==========================================================================================
			// (II) LongTerm: use for more than few hours, maybe some days
			// ==========================================================================================
			case LongTerm:
				break;
			// ==========================================================================================
			// (III) VIP: for whom looking for comfort and security
			// ==========================================================================================
			case VIP:
				break;
		}
		
		// Return the calculated price
		return calculated_price;
	}
}
