package org.ummalqura.calendar;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author msarhan.
 */
public class UmmalquraCalendarTests {

	private UmmalquraCalendar cal = new UmmalquraCalendar();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("y-M-d");

	@Before
	public void setup() {
		cal = new UmmalquraCalendar(1436, UmmalquraCalendar.JUMADA_AWWAL, 23, 12, 19, 44);
	}

	@Test
	public void testDisplayName() {

		String displayName = cal.getDisplayName(UmmalquraCalendar.MONTH, UmmalquraCalendar.LONG, new Locale("en"));
		assertEquals("Month long display name for Jumada al-Ula 23, 1436 should be 'Jumada al-Ula'", "Jumada al-Ula", displayName);

		displayName = cal.getDisplayName(UmmalquraCalendar.MONTH, UmmalquraCalendar.SHORT, new Locale("en"));
		assertEquals("Month short display name for Jumada al-Ula 23, 1436 should be 'Jum-I'", "Jum-I", displayName);

		displayName = cal.getDisplayName(UmmalquraCalendar.MONTH, UmmalquraCalendar.LONG, new Locale("ar"));
		assertEquals("Month long display name for Jumada al-Ula 23, 1436 should be 'جمادى الأولى'", "جمادى الأولى", displayName);

		displayName = cal.getDisplayName(UmmalquraCalendar.MONTH, UmmalquraCalendar.SHORT, new Locale("ar"));
		assertEquals("Month short display name for Jumada al-Ula 23, 1436 should be 'جمادى 1'", "جمادى 1", displayName);

	}

	@Test
	public void testDisplayNames() {

		Map<String, Integer> displayNames = cal.getDisplayNames(UmmalquraCalendar.MONTH, UmmalquraCalendar.LONG, new Locale("en"));
		assertEquals("Month long display names count should be 12", 12, displayNames.size());
		assertTrue("Month long display names should contains 'Muharram'", displayNames.containsKey("Muharram"));
		assertTrue("Month long display names should contains 'Thul-Qi'dah'", displayNames.containsKey("Thul-Qi'dah"));

		displayNames = cal.getDisplayNames(UmmalquraCalendar.MONTH, UmmalquraCalendar.SHORT, new Locale("en"));
		assertEquals("Month short display names count should be 12", 12, displayNames.size());
		assertTrue("Month short display names should contains 'Saf'", displayNames.containsKey("Saf"));
		assertTrue("Month short display names should contains 'Thul-Q", displayNames.containsKey("Thul-Q"));

		displayNames = cal.getDisplayNames(UmmalquraCalendar.MONTH, UmmalquraCalendar.LONG, new Locale("ar"));
		assertEquals("Month long display names count should be 12", 12, displayNames.size());
		assertTrue("Month long display names should contains 'محرم'", displayNames.containsKey("محرم"));
		assertTrue("Month long display names should contains 'ذو القعدة'", displayNames.containsKey("ذو القعدة"));

		displayNames = cal.getDisplayNames(UmmalquraCalendar.MONTH, UmmalquraCalendar.SHORT, new Locale("ar"));
		assertEquals("Month short display names count should be 12", 12, displayNames.size());
		assertTrue("Month short display names should contains 'صفر'", displayNames.containsKey("صفر"));
		assertTrue("Month short display names should contains 'ذو القعدة", displayNames.containsKey("ذو القعدة"));

	}

	@Test
	public void testSetYearField() {
		cal.set(UmmalquraCalendar.YEAR, 1430);
		Date date = cal.getTime();

		String expected = String.format("%s-%s-%s", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
		assertEquals("Change year from '1436' to '1430' for '1436-05-23' should result in '1430-5-23'", "1430-5-23", expected);
		assertEquals("Change year from '1436' to '1430' for '1436-05-23' should result in '2009-5-18'", "2009-5-18", dateFormat.format(date));
	}

	@Test
	public void testSetMonthField() {
		cal.set(UmmalquraCalendar.MONTH, UmmalquraCalendar.RABI_AWWAL);
		Date date = cal.getTime();

		String expected = String.format("%s-%s-%s", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
		assertEquals("Change month from '5' to '3' for '1436-05-23' should result in '1436-3-23'", expected, "1436-3-23");
		assertEquals("Change month from '5' to '3' for '1436-05-23' should result in '2015-1-14'", "2015-1-14", dateFormat.format(date));
	}

	@Test
	public void testSetDayField() {
		cal.set(UmmalquraCalendar.DAY_OF_MONTH, 7);
		Date date = cal.getTime();

		String expected = String.format("%s-%s-%s", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
		assertEquals("Change day from '23' to '7' for '1436-05-23' should result in '1436-5-7'", expected, "1436-5-7");
		assertEquals("Change day from '23' to '7' for '1436-05-23' should result in '2015-2-26'", "2015-2-26", dateFormat.format(date));
	}

	@Test
	public void testConstructor1() {
		UmmalquraCalendar cal = new UmmalquraCalendar(1436, UmmalquraCalendar.JUMADA_AWWAL, 23);
		Date date = cal.getTime();

		assertEquals("'1436-5-23' should result in '2015-3-14'", "2015-3-14", dateFormat.format(date));
	}

}
