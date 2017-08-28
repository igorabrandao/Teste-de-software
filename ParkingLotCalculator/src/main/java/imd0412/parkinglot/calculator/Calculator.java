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
		float diff_year = 0;
		float diff_month = 0;
		float diff_day = 0;
		float diff_hour = 0;
		float diff_minute = 0;
		
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
			diff_year = Float.parseFloat(matcher.group(1));
			diff_month = Float.parseFloat(matcher.group(2));
			diff_day = Float.parseFloat(matcher.group(3));
			diff_hour = Float.parseFloat(matcher.group(4));
			diff_minute = Float.parseFloat(matcher.group(5));
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
				
				System.out.println("\nDiferença: ");
				System.out.println("\tAno\t" + diff_year);
				System.out.println("\tMês\t" + diff_month);
				System.out.println("\tDia\t" + diff_day);
				System.out.println("\tHora\t" + diff_hour);
				System.out.println("\tMin\t" + diff_minute);
				
				// Calculation of short term tax (business rule)
				
				// Days
				if (diff_day > 7) {
					// In the first week, each day tax costs R$50,00
					calculated_price += (7 * 50);
					
					// After the first week, the tax cost lows to R$30,00
					calculated_price += ((diff_day - 7) * 30);
					
					// Add the value per hour (without the first hour)
					calculated_price += (((diff_day * 24) - 1) * 2);
					
					// First hour costs R$8,00
					calculated_price += 8;
				}
				else if (diff_day > 0) {
					// Just the usual price per day R$50,00
					calculated_price += (diff_day * 50);
					
					// Add the value per hour (without the first hour)
					calculated_price += (((diff_day * 24) - 1) * 2);
					
					// First hour costs R$8,00
					calculated_price += 8;
				}
				
				// Hours
				if (diff_day == 0) {
					if (diff_hour > 1) {
						// First hour costs R$8,00
						calculated_price += 8;
						
						// The other ones R$2,00
						calculated_price += ((diff_hour - 1) * 2);
					} else {
						// First hour costs R$8,00
						calculated_price += diff_hour * 8;
					}
				} else {
					// The first hour was already charged
					calculated_price += (diff_hour * 2);
				}
				
				// Minutes
				if (diff_minute > 0 && diff_hour == 0) {
					// In this case, the customer stayed less than 1 hour
					calculated_price += 8;
				}
				else if (diff_minute > 0 && diff_hour > 0) {
					// The customer already paid the first our, so this is an extra hour
					calculated_price += 2;
				}
				
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
