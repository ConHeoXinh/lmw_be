package longND.fpt.home.util;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Util {
	public static String genCode(int size) {
		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(size);

		for (int i = 0; i < size; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public static String generateRandomString(int length) {
		StringBuilder randomString = new StringBuilder();
		SecureRandom secureRandom = new SecureRandom();

		for (int i = 0; i < length; i++) {
			int randomIndex = secureRandom.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			randomString.append(randomChar);
		}

		return randomString.toString();
	}

	public static boolean checkReturnDate(LocalDateTime newDate, LocalDateTime oldDate) {

		long daysDifference = ChronoUnit.DAYS.between(oldDate, newDate);

		if (daysDifference > 7 || daysDifference <= 0) {
			return false;
		}

		return true;
	}

	public static String formatLocalDateTime(LocalDateTime localDateTime) {
		// Create a DateTimeFormatter for the desired format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// Format the LocalDateTime using the formatter
		return localDateTime.format(formatter);
	}

	public static LocalDateTime convertStringToLocalDateTime(String date) {

		// Define the date format pattern
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// Parse the date string to a LocalDate (date-only)
		LocalDate localDate = LocalDate.parse(date, formatter);

		// Create a LocalDateTime by combining the date with a default time (e.g.,
		// midnight)
		LocalDateTime localDateTime1 = localDate.atTime(LocalTime.MIDNIGHT);

		return localDateTime1;
	}

	public static LocalDate convertStringToLocalDate(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// Parse the String to a LocalDate using the formatter
		LocalDate localDate = LocalDate.parse(dateString, formatter);
		return localDate;
	}

	public static String formatLocalDate(LocalDate localDate) {

		// Define the date formatter for the desired output format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// Format the LocalDate to a String using the formatter
		String dateString = localDate.format(formatter);

		return dateString;
	}
}
