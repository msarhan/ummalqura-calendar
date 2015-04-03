package org.ummalqura.calendar;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author msarhan.
 */
public class UmmalquraDateFormatTests {

	private static Locale en = new Locale("en");
	private static Locale ar = new Locale("ar");
	private static UmmalquraCalendar cal = null;

	@Before
	public void setup() {
		cal = new UmmalquraCalendar(1436, UmmalquraCalendar.JUMADA_AWWAL, 23, 12, 19, 44);
	}

	@Test
	public void testStandAloneCount() throws Exception {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

		assertEquals("5", formatDate(en, "M"));
		assertEquals("05", formatDate(en, "MM"));
		assertEquals("Jum-I", formatDate(en, "MMM"));
		assertEquals("Jumada al-Ula", formatDate(en, "MMMM"));
		assertEquals("جمادى 1", formatDate(ar, "MMM"));
		assertEquals("جمادى الأولى", formatDate(ar, "MMMM"));

		assertEquals("1436", formatDate(en, "y"));
		assertEquals("36", formatDate(en, "yy"));
		assertEquals("1436", formatDate(en, "yyyy"));

		assertEquals("23", formatDate(en, "d"));
		assertEquals("23", formatDate(en, "dd"));

		assertEquals("Sat", formatDate(en, "E"));
		assertEquals("Saturday", formatDate(en, "EEEE"));
		assertEquals("س", formatDate(ar, "E"));
		assertEquals("السبت", formatDate(ar, "EEEE"));
	}

	@Test
	public void formatWithEnglishLocale() {

		SimpleDateFormat formatter = new SimpleDateFormat("y-M-d", en);
		formatter.setCalendar(cal);
		assertEquals("{1436,5,23} should format to '1436-05-23' using 'y-M-d'", "1436-5-23", formatter.format(cal.getTime()));

		formatter = new SimpleDateFormat("MMM d, y", en);
		formatter.setCalendar(cal);
		assertEquals("{1436,5,23} should format to 'Jum-I 23, 1436' using 'MMM d, y'", "Jum-I 23, 1436", formatter.format(cal.getTime()));

		formatter = new SimpleDateFormat("MMMM d, y", en);
		formatter.setCalendar(cal);
		assertEquals("{1436,5,23} should format to 'Jumada al-Ula 23, 1436' using 'MMMM d, y'", "Jumada al-Ula 23, 1436", formatter.format(cal.getTime()));

		formatter = new SimpleDateFormat("E, MMMM d, y", en);
		formatter.setCalendar(cal);
		assertEquals("{1436,5,23} should format to 'Sat, Jumada al-Ula 23, 1436' using 'E, MMMM d, y'", "Sat, Jumada al-Ula 23, 1436", formatter.format(cal.getTime()));

		formatter = new SimpleDateFormat("EEEE, MMMM d, y", en);
		formatter.setCalendar(cal);
		assertEquals("{1436,5,23} should format to 'Saturday, Jumada al-Ula 23, 1436' using 'EEEE, MMMM d, y'", "Saturday, Jumada al-Ula 23, 1436", formatter.format(cal.getTime()));

		formatter = new SimpleDateFormat("EEEE, MMMM d, yy", en);
		formatter.setCalendar(cal);
		assertEquals("{1436,5,23} should format to 'Saturday, Jumada al-Ula 23, 36' using 'EEEE, MMMM d, yy'", "Saturday, Jumada al-Ula 23, 36", formatter.format(cal.getTime()));

		formatter = new SimpleDateFormat("EEEE, MMMM d, yy hh:mm:ss", en);
		formatter.setCalendar(cal);
		assertEquals("Should format to 'Saturday, Jumada al-Ula 23, 36 12:19:44' using 'EEEE, MMMM d, yy hh:mm:ss'", "Saturday, Jumada al-Ula 23, 36 12:19:44", formatter.format(cal.getTime()));
	}

	@Test
	public void formatWithArabicLocale() {

		SimpleDateFormat formatter = new SimpleDateFormat("y-M-d", ar);
		formatter.setCalendar(cal);
		assertEquals("{1436,5,23} should format to '1436-05-23' using 'y-M-d'", "1436-5-23", formatter.format(cal.getTime()));

		formatter = new SimpleDateFormat("MMM d, y", ar);
		formatter.setCalendar(cal);
		assertEquals("{1436,5,23} should format to 'جمادى 1 23, 1436' using 'MMM d, y'", "جمادى 1 23, 1436", formatter.format(cal.getTime()));

		formatter = new SimpleDateFormat("MMMM d, y", ar);
		formatter.setCalendar(cal);
		assertEquals("{1436,5,23} should format to 'جمادى الأولى 23, 1436' using 'MMMM d, y'", "جمادى الأولى 23, 1436", formatter.format(cal.getTime()));

		formatter = new SimpleDateFormat("E, MMMM d, y", ar);
		formatter.setCalendar(cal);
		assertEquals("{1436,5,23} should format to 'س, جمادى الأولى 23, 1436' using 'E, MMMM d, y'", "س, جمادى الأولى 23, 1436", formatter.format(cal.getTime()));

		formatter = new SimpleDateFormat("EEEE, MMMM d, y", ar);
		formatter.setCalendar(cal);
		assertEquals("{1436,5,23} should format to 'السبت, جمادى الأولى 23, 1436' using 'EEEE, MMMM d, y'", "السبت, جمادى الأولى 23, 1436", formatter.format(cal.getTime()));

		formatter = new SimpleDateFormat("EEEE, MMMM d, yy", ar);
		formatter.setCalendar(cal);
		assertEquals("{1436,5,23} should format to 'السبت, Jumada al-Ula 23, 36' using 'EEEE, MMMM d, yy'", "السبت, جمادى الأولى 23, 36", formatter.format(cal.getTime()));
	}

	@Test
	public void testDayOfMonth(){
		UmmalquraCalendar cal = new UmmalquraCalendar(1436, UmmalquraCalendar.JUMADA_AWWAL, 4, 12, 19, 44);
		assertEquals("4", formatDate(cal, en, "d"));
		assertEquals("04", formatDate(cal, en, "dd"));
		assertEquals("004", formatDate(cal, en, "ddd"));
	}

	private String formatDate(Locale l, String fmt) {
		return formatDate(cal, l, fmt);
	}

	private String formatDate(Calendar cal, Locale l, String fmt) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(fmt, l);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		dateFormat.setCalendar(cal);
		return dateFormat.format(cal.getTime());
	}

}
