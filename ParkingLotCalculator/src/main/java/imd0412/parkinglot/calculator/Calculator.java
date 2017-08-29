package imd0412.parkinglot.calculator;

import static org.apache.commons.lang3.time.DurationFormatUtils.formatDuration;

import java.text.ParseException;
import java.util.Calendar;
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
	 * @param type Enum representing the type of service provided by the airport
	 * @return
	 */
	Float calculateParkingCost(String checkin, String checkout, ParkingLotType type) throws DateFormatException, 
		InvalidDataException {
		
		// Calendar instance
		Calendar checkinCalendar = Calendar.getInstance();
		Calendar checkoutCalendar = Calendar.getInstance();
		
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
			
			// Throws the custom exception
			throw new DateFormatException(errorMsg);
		}
		
		// ==========================================================================================
		// DATES VALIDATION HANDLER
		// ==========================================================================================
				
		// Set the calendar instance with check-in and check-out dates
		checkinCalendar.setTime(check_in);
		checkoutCalendar.setTime(check_out);
		
		// Split the time into sections
		int checkin_year = checkinCalendar.get(Calendar.YEAR);
		int checkin_month = checkinCalendar.get(Calendar.MONTH);
		// int checkin_day = checkinCalendar.get(Calendar.DAY_OF_MONTH);
		int checkin_day = Integer.parseInt(checkin.substring(8, 10)); // Note: If use Calendar.DAY_OF_MONTH February 29 and 30 will be converted to 1 and 2 respectively 
		boolean isCheckinLeapYear = ( (checkin_year % 400 == 0) || ((checkin_year % 4 == 0) && (checkin_year % 100 != 0)) );
		
		int checkout_year = checkoutCalendar.get(Calendar.YEAR);
		int checkout_month = checkoutCalendar.get(Calendar.MONTH);
		// int checkout_day = checkoutCalendar.get(Calendar.DAY_OF_MONTH);
		int checkout_day = Integer.parseInt(checkout.substring(8, 10));
		boolean isCheckoutLeapYear = ( (checkout_year % 400 == 0) || ((checkout_year % 4 == 0) && (checkout_year % 100 != 0)) );
		
		// Check-in validation
		if ((1970 > checkin_year) || (checkin_year > 2017)) {
			// Invalid year
			throw new InvalidDataException(InvalidDataType.InvalidYear);
		}
		else if ((1 > checkin_month) || (checkin_month > 12)) {
			// Invalid month
			throw new InvalidDataException(InvalidDataType.InvalidMonth);
		}
		else if ((1 > checkin_day) || (checkin_day > 31)) {
			// Invalid month
			throw new InvalidDataException(InvalidDataType.InvalidDay);
		}
		else if ( (checkin_day == 29 && checkin_month == 2) && isCheckinLeapYear == false ) {
			// In this case, the 29th February doesn't exists
			throw new InvalidDataException(InvalidDataType.NonexistentDate);
		}
		else if ( (checkin_day == 30 && checkin_month == 2) ) {
			// In this case, the 30th February never exists
			throw new InvalidDataException(InvalidDataType.NonexistentDate);
		}
		
		// Check-out validation
		if ((1970 > checkout_year) || (checkout_year > 2017)) {
			// Invalid year
			throw new InvalidDataException(InvalidDataType.InvalidYear);
		}
		else if ((1 > checkout_month) || (checkout_month > 12)) {
			// Invalid month
			throw new InvalidDataException(InvalidDataType.InvalidMonth);
		}
		else if ((1 > checkout_day) || (checkout_day > 31)) {
			// Invalid month
			throw new InvalidDataException(InvalidDataType.InvalidDay);
		}
		else if ( (checkout_day == 29 && checkout_month == 2) && isCheckoutLeapYear == false ) {
			// In this case, the 29th February doesn't exists
			throw new InvalidDataException(InvalidDataType.NonexistentDate);
		}
		else if ( (checkout_day == 30 && checkout_day == 2) ) {
			// In this case, the 30th February never exists
			throw new InvalidDataException(InvalidDataType.NonexistentDate);
		}
		
		// ==========================================================================================
		// DATE DIFFERENCE CALCULATOR HANDLER
		// ==========================================================================================
		
		// Calculates the difference in milliseconds
		long diff = check_out.getTime() - check_in.getTime();
		
		// Check if check-in came after check-out
		if ( diff < 0 ) {
			throw new InvalidDataException(InvalidDataType.CheckinGreaterThanCheckout);
		}

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
		
		/*System.out.println("\nDiferença: ");
		System.out.println("\tAno\t" + diff_year);
		System.out.println("\tMês\t" + diff_month);
		System.out.println("\tDia\t" + diff_day);
		System.out.println("\tHora\t" + diff_hour);
		System.out.println("\tMin\t" + diff_minute);*/
		
		// Check the use type
		switch (type) {
			// ==========================================================================================
			// (I) ShortTerm: use just for a few hours
			// ==========================================================================================
			case ShortTerm:
				
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
					// The customer already paid the first hour, so this is an extra hour
					calculated_price += 2;
				}
				
				break;
			// ==========================================================================================
			// (II) LongTerm: use for more than few hours, maybe some days
			// ==========================================================================================
			case LongTerm:
				
				// Calculation of long term tax (business rule)
				
				// Days
				if (diff_day > 7) {
					// The first day costs R$70,00
					calculated_price += 70;
					
					// In the first week, each day tax costs R$50,00
					calculated_price += (6 * 50);
					
					// After the first week, the tax cost lows to R$30,00
					calculated_price += ((diff_day - 7) * 30);
				}
				else if (diff_day > 0) {
					// The first day costs R$70,00
					calculated_price += 70;
					
					// In the first week, each day tax costs R$50,00
					calculated_price += ((diff_day - 1) * 50);
				}
				
				// Hours
				if (diff_hour > 0 && diff_day == 0) {
					// In this case, the customer stayed less than 1 day
					calculated_price += 70;
				}
				else if (diff_hour > 0 && diff_day > 7) {
					// The customer already paid the first day, so this is an extra day (after 1 week)
					calculated_price += 30;
				}
				else if (diff_hour > 0 && diff_day > 0) {
					// The customer already paid the first day, so this is an extra day
					calculated_price += 50;
				}
				
				// Minutes
				if (diff_minute > 0 && diff_day == 0 && diff_hour == 0) {
					// In this case, the customer stayed less than 1 day
					calculated_price += 70;
				}
				else if (diff_minute > 0 && diff_day > 7 && diff_hour == 0) {
					// The customer already paid the first day, so this is an extra day (after 1 week)
					calculated_price += 30;
				}
				else if (diff_minute > 0 && diff_day > 0 && diff_hour == 0) {
					// The customer already paid the first day, so this is an extra day
					calculated_price += 50;
				}
				
				break;
			// ==========================================================================================
			// (III) VIP: for whom looking for comfort and security
			// ==========================================================================================
			case VIP:
				
				// Calculation of long term tax (business rule)
				
				// Days
				if (diff_day > 14) {
					// The first week costs R$500,00
					calculated_price += 500;
					
					// In the second week, each day tax costs R$100,00
					calculated_price += (7 * 100);
					
					// After the second week, the tax cost lows to R$80,00
					calculated_price += ((diff_day - 14) * 80);
				}
				else if (diff_day > 7) {
					// The first week costs R$500,00
					calculated_price += 500;
					
					// The next days cost R$100,00
					calculated_price += ((diff_day - 7) * 100);
				}
				else {
					// The first week costs R$500,00
					calculated_price += 500;
				}
				
				break;
		}

		// Return the calculated price
		return calculated_price;
	}
}