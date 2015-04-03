package org.ummalqura.calendar;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Mouaffak A. Sarhan.
 */
class UmmalquraDateFormatSymbols {

	/**
	 * The locale which is used for initializing this DateFormatSymbols object.
	 *
	 * @serial
	 */
	Locale locale = null;

	/**
	 * Month strings. For example: "January", "February", etc.  An array
	 * of 13 strings (some calendars have 13 months), indexed by
	 * <code>Calendar.JANUARY</code>, <code>Calendar.FEBRUARY</code>, etc.
	 *
	 * @serial
	 */
	String[] months = null;

	/**
	 * Short month strings. For example: "Jan", "Feb", etc.  An array of
	 * 13 strings (some calendars have 13 months), indexed by
	 * <code>Calendar.JANUARY</code>, <code>Calendar.FEBRUARY</code>, etc.
	 *
	 * @serial
	 */
	String[] shortMonths = null;

	public UmmalquraDateFormatSymbols() {
		initializeData(Locale.getDefault(Locale.Category.FORMAT));
	}

	public UmmalquraDateFormatSymbols(Locale locale) {
		initializeData(locale);
	}

	/**
	 * Gets month strings. For example: "January", "February", etc.
	 *
	 * @return the month strings.
	 */
	public String[] getMonths() {
		return Arrays.copyOf(months, months.length);
	}

	/**
	 * Gets short month strings. For example: "Jan", "Feb", etc.
	 *
	 * @return the short month strings.
	 */
	public String[] getShortMonths() {
		return Arrays.copyOf(shortMonths, shortMonths.length);
	}

	private void initializeData(Locale desiredLocale) {
		locale = desiredLocale;

		// Initialize the fields from the ResourceBundle for locale.
		ResourceBundle resource = ResourceBundle.getBundle("org.ummalqura.calendar.text.UmmalquraFormatData", locale);

		months = resource.getStringArray("MonthNames");
		shortMonths = resource.getStringArray("MonthAbbreviations");
	}

}
