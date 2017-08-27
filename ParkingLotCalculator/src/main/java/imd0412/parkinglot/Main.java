package imd0412.parkinglot;

import static org.apache.commons.lang3.time.DurationFormatUtils.formatDuration;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;

public class Main {

	public static void main(String[] args) throws Exception {
		// Dados de entrada terão este formato
		String checkinStr = "2016.10.31 10:35";
		String checkoutStr = "2017.12.31 11:30";

		// Converter para objeto do tipo java.util.Date
		Date checkin = Constants.DATE_FORMATTER.parse(checkinStr);
		Date checkout = Constants.DATE_FORMATTER.parse(checkoutStr);

		// Diferença em milisegundos
		long diff = checkout.getTime() - checkin.getTime();
		System.out.println(diff + "ms");

		// Imprimir a diferença no formato de data
		String duration = formatDuration(diff, Constants.VALID_DATE_FORMAT);
		System.out.println(duration);

		// Extrair dados da string usando expressão regular
		Matcher matcher = Constants.DATE_PATTERN.matcher(duration);
		if (matcher.find()) {
			System.out.println("\nDiferença: ");
			System.out.println("\tAno\t" + matcher.group(1));
			System.out.println("\tMês\t" + matcher.group(2));
			System.out.println("\tDia\t" + matcher.group(3));
			System.out.println("\tHora\t" + matcher.group(4));
			System.out.println("\tMin\t" + matcher.group(5));
		}

		// Testar entrada fora do padrão
		String wrongPattern = "2017/1/31";
		try {
			Constants.DATE_FORMATTER.parse(wrongPattern);
		} catch (ParseException e) {
			String errorMsg = "\nString " + wrongPattern
					+ " está fora do padrão.";
			System.out.println(errorMsg);
			
//			throw new DateFormatException(errorMsg);
		}
	}
}
