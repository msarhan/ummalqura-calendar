/*
 * The MIT License
 *
 * Copyright 2015 Mouaffak A. Sarhan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.msarhan.ummalqura.calendar;

import java.util.*;

/**
 * @author Mouaffak A. Sarhan.
 */
public class UmmalquraCalendar extends GregorianCalendar {

	/**
	 * Value of the {@link #MONTH} field indicating the
	 * first month of the year in the Ummalqura calendar.
	 */
	public final static int MUHARRAM = 0;

	/**
	 * Value of the {@link #MONTH} field indicating the
	 * second month of the year in the Ummalqura calendar.
	 */
	public final static int SAFAR = 1;

	/**
	 * Value of the {@link #MONTH} field indicating the
	 * third month of the year in the Ummalqura calendar.
	 */
	public final static int RABI_AWWAL = 2;

	/**
	 * Value of the {@link #MONTH} field indicating the
	 * fourth month of the year in the Ummalqura calendar.
	 */
	public final static int RABI_THANI = 3;

	/**
	 * Value of the {@link #MONTH} field indicating the
	 * fifth month of the year in the Ummalqura calendar.
	 */
	public final static int JUMADA_AWWAL = 4;

	/**
	 * Value of the {@link #MONTH} field indicating the
	 * sixth month of the year in the Ummalqura calendar.
	 */
	public final static int JUMADA_THANI = 5;

	/**
	 * Value of the {@link #MONTH} field indicating the
	 * seventh month of the year in the Ummalqura calendar.
	 */
	public final static int RAJAB = 6;

	/**
	 * Value of the {@link #MONTH} field indicating the
	 * eighth month of the year in the Ummalqura calendar.
	 */
	public final static int SHAABAN = 7;

	/**
	 * Value of the {@link #MONTH} field indicating the
	 * ninth month of the year in the Ummalqura calendar.
	 */
	public final static int RAMADHAN = 8;

	/**
	 * Value of the {@link #MONTH} field indicating the
	 * tenth month of the year in the Ummalqura calendar.
	 */
	public final static int SHAWWAL = 9;

	/**
	 * Value of the {@link #MONTH} field indicating the
	 * eleventh month of the year in the Ummalqura calendar.
	 */
	public final static int THUL_QIDAH = 10;

	/**
	 * Value of the {@link #MONTH} field indicating the
	 * twelfth month of the year in the Ummalqura calendar.
	 */
	public final static int THUL_HIJJAH = 11;

	/**
	 * The calendar field values for the currently set time for this calendar.
	 * This is an array of <code>FIELD_COUNT</code> integers, with index values
	 * <code>ERA</code> through <code>DST_OFFSET</code>.
	 *
	 * @serial
	 */
	protected int hFields[];

	/**
	 * Constructs a default <code>UmmalquraCalendar</code> using the current time
	 * in the default time zone with the default locale.
	 */
	public UmmalquraCalendar() {
		this(TimeZone.getDefault(), Locale.getDefault());
	}

	/**
	 * Constructs a <code>UmmalquraCalendar</code> based on the current time
	 * in the default time zone with the given locale.
	 *
	 * @param aLocale the given locale.
	 */
	public UmmalquraCalendar(Locale aLocale) {
		this(TimeZone.getDefault(), aLocale);
	}

	/**
	 * Constructs a <code>UmmalquraCalendar</code> based on the current time
	 * in the given time zone with the given locale.
	 *
	 * @param zone    the given time zone.
	 * @param aLocale the given locale.
	 */
	public UmmalquraCalendar(TimeZone zone, Locale aLocale) {
		super(zone, aLocale);
	}

	/**
	 * Constructs a <code>UmmalquraCalendar</code> with the given date set
	 * in the default time zone with the default locale.
	 *
	 * @param year       the value used to set the <code>YEAR</code> calendar field in the calendar.
	 * @param month      the value used to set the <code>MONTH</code> calendar field in the calendar.
	 *                   Month value is 0-based. e.g., 0 for Muharram.
	 * @param dayOfMonth the value used to set the <code>DAY_OF_MONTH</code> calendar field in the calendar.
	 */
	public UmmalquraCalendar(int year, int month, int dayOfMonth) {
		this(year, month, dayOfMonth, 0, 0, 0);
	}

	/**
	 * Constructs a <code>UmmalquraCalendar</code> with the given date
	 * and time set for the default time zone with the default locale.
	 *
	 * @param year       the value used to set the <code>YEAR</code> calendar field in the calendar.
	 * @param month      the value used to set the <code>MONTH</code> calendar field in the calendar.
	 *                   Month value is 0-based. e.g., 0 for Muharram.
	 * @param dayOfMonth the value used to set the <code>DAY_OF_MONTH</code> calendar field in the calendar.
	 * @param hourOfDay  the value used to set the <code>HOUR_OF_DAY</code> calendar field
	 *                   in the calendar.
	 * @param minute     the value used to set the <code>MINUTE</code> calendar field
	 *                   in the calendar.
	 */
	public UmmalquraCalendar(int year, int month, int dayOfMonth, int hourOfDay,
													 int minute) {
		this(year, month, dayOfMonth, hourOfDay, minute, 0);
	}

	/**
	 * Constructs a <code>UmmalquraCalendar</code> with the given date
	 * and time set for the default time zone with the default locale.
	 *
	 * @param year       the value used to set the <code>YEAR</code> calendar field in the calendar.
	 * @param month      the value used to set the <code>MONTH</code> calendar field in the calendar.
	 *                   Month value is 0-based. e.g., 0 for Muharram.
	 * @param dayOfMonth the value used to set the <code>DAY_OF_MONTH</code> calendar field in the calendar.
	 * @param hourOfDay  the value used to set the <code>HOUR_OF_DAY</code> calendar field
	 *                   in the calendar.
	 * @param minute     the value used to set the <code>MINUTE</code> calendar field
	 *                   in the calendar.
	 * @param second     the value used to set the <code>SECOND</code> calendar field
	 *                   in the calendar.
	 */
	public UmmalquraCalendar(int year, int month, int dayOfMonth, int hourOfDay,
													 int minute, int second) {

		set(YEAR, year);
		set(MONTH, month);
		set(DAY_OF_MONTH, dayOfMonth);
		set(HOUR_OF_DAY, hourOfDay);
		set(MINUTE, minute);
		set(SECOND, second);
	}

	@Override
	public int get(int field) {
		if (field == YEAR || field == MONTH || field == DAY_OF_MONTH) {
			return hFields[field];
		}

		return super.get(field);
	}

	@Override
	public void set(int field, int value) {
		if (field == YEAR || field == MONTH || field == DAY_OF_MONTH) {
			int[] hDateInfo = UmmalquraGregorianConverter.toHijri(getTime());
			if (field == YEAR) {
				hDateInfo[0] = value;
			} else if (field == MONTH) {
				hDateInfo[1] = value;
			} else {
				hDateInfo[2] = value;
			}

			int[] gDateInfo = UmmalquraGregorianConverter.toGregorian(hDateInfo[0], hDateInfo[1],
							hDateInfo[2]);

			super.set(YEAR, gDateInfo[0]);
			super.set(MONTH, gDateInfo[1]);
			super.set(DAY_OF_MONTH, gDateInfo[2]);
			complete();

		} else {
			super.set(field, value);
		}

	}

	/**
	 * Returns the length of the month represented by this calendar.
	 * <p>
	 * This returns the length of the month in days.
	 *
	 * @return the length of the month in days
	 */
	public int lengthOfMonth() {
		return lengthOfMonth(get(YEAR), get(MONTH));
	}

	/**
	 * Returns the length of a Hijri month in a Hijri year.
	 * <p>
	 * This returns the length of the month in days.
	 *
	 * @param year  Hijri year
	 * @param month Hijri month
	 * @return the length of the month in days
	 */
	public static int lengthOfMonth(int year, int month) {
		return UmmalquraGregorianConverter.getDaysInMonth(year, month);
	}

	/**
	 * Returns the length of the year represented by this calendar.
	 * <p>
	 * This returns the length of the year in days, either 354 or 355.
	 *
	 * @return 355 if the year is leap, 354 otherwise
	 */
	public int lengthOfYear() {
		return lengthOfYear(get(YEAR));
	}

	/**
	 * Returns the length of the given year.
	 * <p>
	 * This returns the length of the year in days, either 354 or 355.
	 *
	 * @param year the year to calculate day count for.
	 * @return 355 if the year is leap, 354 otherwise
	 */
	public static int lengthOfYear(int year) {
		int total = 0;
		for (int m = MUHARRAM; m <= THUL_HIJJAH; m++) {
			total += lengthOfMonth(year, m);
		}

		return total;
	}

	/**
	 * Returns the string representation of the calendar
	 * <code>field</code> value in the given <code>style</code> and
	 * <code>locale</code>.  If no string representation is
	 * applicable, <code>null</code> is returned. This method calls
	 * {@link Calendar#get(int) get(field)} to get the calendar
	 * <code>field</code> value if the string representation is
	 * applicable to the given calendar <code>field</code>.
	 * <p>For example, if this <code>Calendar</code>'s date is 1437-01-01, then
	 * the string representation of the {@link #MONTH} field would be
	 * "Muharram" in the long style in an English locale or "Muh" in
	 * the short style. However, no string representation would be
	 * available for the {@link #DAY_OF_MONTH} field, and this method
	 * would return <code>null</code>.
	 * <p>The default implementation supports the calendar fields for
	 * which a {@link UmmalquraDateFormatSymbols} has names in the given
	 * <code>locale</code>.
	 *
	 * @param field  the calendar field for which the string representation
	 *               is returned
	 * @param style  the style applied to the string representation; one of
	 *               {@link #SHORT} or {@link #LONG}.
	 * @param locale the locale for the string representation
	 * @return the string representation of the given
	 * <code>field</code> in the given <code>style</code>, or
	 * <code>null</code> if no string representation is
	 * applicable.
	 * @throws IllegalArgumentException if <code>field</code> or <code>style</code> is invalid,
	 *                                  or if this <code>Calendar</code> is non-lenient and any
	 *                                  of the calendar fields have invalid values
	 * @throws NullPointerException     if <code>locale</code> is null
	 * @since 1.6
	 */
	@Override
	public String getDisplayName(int field, int style, Locale locale) {

		if (field == MONTH) {
			UmmalquraDateFormatSymbols symbols = new UmmalquraDateFormatSymbols(locale);
			String[] strings = getFieldStrings(field, style, symbols);
			if (strings != null) {
				int fieldValue = get(field);
				if (fieldValue < strings.length) {
					return strings[fieldValue];
				}
			}

			return null;
		}

		return super.getDisplayName(field, style, locale);
	}

	/**
	 * Returns a <code>Map</code> containing all names of the calendar
	 * <code>field</code> in the given <code>style</code> and
	 * <code>locale</code> and their corresponding field values. For
	 * example, The returned map would contain "Muh" to
	 * {@link #MUHARRAM}, "Saf" to {@link #SAFAR}, and so on, in the
	 * {@linkplain #SHORT short} style in an English locale.
	 * <p>The values of other calendar fields may be taken into
	 * account to determine a set of display names. For example, if
	 * this <code>Calendar</code> is a lunisolar calendar system and
	 * the year value given by the {@link #YEAR} field has a leap
	 * month, this method would return month names containing the leap
	 * month name, and month names are mapped to their values specific
	 * for the year.
	 * <p>This implementation supports display names contained in
	 * a {@link UmmalquraDateFormatSymbols}. For example, if <code>field</code>
	 * is {@link #MONTH} and <code>style</code> is {@link
	 * #ALL_STYLES}, this method returns a <code>Map</code> containing
	 * all strings returned by {@link UmmalquraDateFormatSymbols#getShortMonths()}
	 * and {@link UmmalquraDateFormatSymbols#getMonths()}.
	 *
	 * @param field  the calendar field for which the display names are returned
	 * @param style  the style applied to the display names; one of {@link
	 *               #SHORT}, {@link #LONG}, or {@link #ALL_STYLES}.
	 * @param locale the locale for the display names
	 * @return a <code>Map</code> containing all display names in
	 * <code>style</code> and <code>locale</code> and their
	 * field values, or <code>null</code> if no display names
	 * are defined for <code>field</code>
	 * @throws IllegalArgumentException if <code>field</code> or <code>style</code> is invalid,
	 *                                  or if this <code>Calendar</code> is non-lenient and any
	 *                                  of the calendar fields have invalid values
	 * @throws NullPointerException     if <code>locale</code> is null
	 */
	public Map<String, Integer> getDisplayNames(int field, int style, Locale locale) {

		if (field == MONTH) {
			// ALL_STYLES
			if (style == ALL_STYLES) {
				Map<String, Integer> shortNames = getDisplayNamesImpl(field, SHORT, locale);
				Map<String, Integer> longNames = getDisplayNamesImpl(field, LONG, locale);
				if (shortNames == null) {
					return longNames;
				}
				if (longNames != null) {
					shortNames.putAll(longNames);
				}
				return shortNames;
			}

			// SHORT or LONG
			return getDisplayNamesImpl(field, style, locale);
		}

		return super.getDisplayNames(field, style, locale);
	}

	private Map<String, Integer> getDisplayNamesImpl(int field, int style, Locale locale) {
		UmmalquraDateFormatSymbols symbols = new UmmalquraDateFormatSymbols(locale);
		String[] strings = getFieldStrings(field, style, symbols);
		if (strings != null) {
			Map<String, Integer> names = new HashMap<String, Integer>();
			for (int i = 0; i < strings.length; i++) {
				if (strings[i].length() == 0) {
					continue;
				}
				names.put(strings[i], i);
			}
			return names;
		}

		return null;
	}

	private String[] getFieldStrings(int field, int style, UmmalquraDateFormatSymbols symbols) {
		if (field == MONTH) {
			if (SHORT == style) {
				return symbols.getShortMonths();
			}

			if (LONG == style) {
				return symbols.getMonths();
			}
		}

		return null;
	}

	public boolean equals(Object obj) {
		return obj instanceof UmmalquraCalendar && super.equals(obj);
	}

	public int hashCode() {
		return super.hashCode() ^ 622;
	}

	@Override
	protected void computeFields() {
		super.computeFields();

		if (hFields == null) {
			hFields = new int[super.fields.length];
		}

		int[] hDateInfo = UmmalquraGregorianConverter.toHijri(time);
		hFields[Calendar.YEAR] = hDateInfo[0];
		hFields[Calendar.MONTH] = hDateInfo[1];
		hFields[Calendar.DAY_OF_MONTH] = hDateInfo[2];
	}

}
