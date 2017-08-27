package imd0412.parkinglot;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class Constants {
	public static final String VALID_DATE_FORMAT = "yyyy.MM.dd HH:mm";

	private static final String VALID_DATE_REGEX = "(\\d*)\\.(\\d*)\\.(\\d*) (\\d*):(\\d*)";

	public static final Pattern DATE_PATTERN;

	public static final SimpleDateFormat DATE_FORMATTER;

	static {
		DATE_PATTERN = Pattern.compile(VALID_DATE_REGEX);

		DATE_FORMATTER = new SimpleDateFormat(Constants.VALID_DATE_FORMAT);
	}
}
