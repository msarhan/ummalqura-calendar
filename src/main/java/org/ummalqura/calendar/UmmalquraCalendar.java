/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ummalqura.calendar;

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
	public final static int THUL_Hijjah = 11;

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
		String[] strings = null;
		if (field == MONTH) {
			strings = (style == LONG) ? symbols.getMonths() : symbols.getShortMonths();
		}

		return strings;
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

		int[] hDateInfo = UmmalquraGregorianConverter.toHijri(getTime());
		hFields[Calendar.YEAR] = hDateInfo[0];
		hFields[Calendar.MONTH] = hDateInfo[1];
		hFields[Calendar.DAY_OF_MONTH] = hDateInfo[2];
	}

}
