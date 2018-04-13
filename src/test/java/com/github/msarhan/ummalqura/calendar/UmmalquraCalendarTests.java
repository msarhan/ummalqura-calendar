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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import org.junit.Test;

/**
 * @author msarhan.
 */
public class UmmalquraCalendarTests {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("y-M-d");

    @Test
    public void testDisplayName() {
        UmmalquraCalendar cal = new UmmalquraCalendar(1436, UmmalquraCalendar.JUMADA_AWWAL, 23, 12,
                19, 44);

        String displayName = cal
                .getDisplayName(UmmalquraCalendar.MONTH, UmmalquraCalendar.LONG, new Locale("en"));
        assertEquals("Month long display name for Jumada al-Ula 23, 1436 should be 'Jumada al-Ula'",
                "Jumada al-Ula", displayName);

        displayName = cal
                .getDisplayName(UmmalquraCalendar.MONTH, UmmalquraCalendar.SHORT, new Locale("en"));
        assertEquals("Month short display name for Jumada al-Ula 23, 1436 should be 'Jum-I'",
                "Jum-I", displayName);

        displayName = cal
                .getDisplayName(UmmalquraCalendar.MONTH, UmmalquraCalendar.LONG, new Locale("ar"));
        assertEquals("Month long display name for Jumada al-Ula 23, 1436 should be 'جمادى الأولى'",
                "جمادى الأولى", displayName);

        displayName = cal
                .getDisplayName(UmmalquraCalendar.MONTH, UmmalquraCalendar.SHORT, new Locale("ar"));
        assertEquals("Month short display name for Jumada al-Ula 23, 1436 should be 'جمادى 1'",
                "جمادى 1", displayName);

    }

    @Test
    public void testDisplayNames() {
        UmmalquraCalendar cal = new UmmalquraCalendar(1436, UmmalquraCalendar.JUMADA_AWWAL, 23, 12,
                19, 44);

        Map<String, Integer> displayNames = cal
                .getDisplayNames(UmmalquraCalendar.MONTH, UmmalquraCalendar.LONG, new Locale("en"));
        assertEquals("Month long display names count should be 12", 12, displayNames.size());
        assertTrue("Month long display names should contains 'Muharram'",
                displayNames.containsKey("Muharram"));
        assertTrue("Month long display names should contains 'Thul-Qi'dah'",
                displayNames.containsKey("Thul-Qi'dah"));

        displayNames = cal.getDisplayNames(UmmalquraCalendar.MONTH, UmmalquraCalendar.SHORT,
                new Locale("en"));
        assertEquals("Month short display names count should be 12", 12, displayNames.size());
        assertTrue("Month short display names should contains 'Saf'",
                displayNames.containsKey("Saf"));
        assertTrue("Month short display names should contains 'Thul-Q",
                displayNames.containsKey("Thul-Q"));

        displayNames = cal
                .getDisplayNames(UmmalquraCalendar.MONTH, UmmalquraCalendar.LONG, new Locale("ar"));
        assertEquals("Month long display names count should be 12", 12, displayNames.size());
        assertTrue("Month long display names should contains 'محرم'",
                displayNames.containsKey("محرم"));
        assertTrue("Month long display names should contains 'ذو القعدة'",
                displayNames.containsKey("ذو القعدة"));

        displayNames = cal.getDisplayNames(UmmalquraCalendar.MONTH, UmmalquraCalendar.SHORT,
                new Locale("ar"));
        assertEquals("Month short display names count should be 12", 12, displayNames.size());
        assertTrue("Month short display names should contains 'صفر'",
                displayNames.containsKey("صفر"));
        assertTrue("Month short display names should contains 'ذو القعدة",
                displayNames.containsKey("ذو القعدة"));

    }

    @Test
    public void testSetYearField() {
        UmmalquraCalendar cal = new UmmalquraCalendar(1436, UmmalquraCalendar.JUMADA_AWWAL, 23, 12,
                19, 44);

        cal.set(UmmalquraCalendar.YEAR, 1430);
        Date date = cal.getTime();

        String expected = String
                .format("%s-%s-%s", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
                        cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(
                "Change year from '1436' to '1430' for '1436-05-23' should result in '1430-5-23'",
                "1430-5-23", expected);
        assertEquals(
                "Change year from '1436' to '1430' for '1436-05-23' should result in '2009-5-18'",
                "2009-5-18", dateFormat.format(date));
    }

    @Test
    public void testSetMonthField() {
        UmmalquraCalendar cal = new UmmalquraCalendar(1436, UmmalquraCalendar.JUMADA_AWWAL, 23, 12,
                19, 44);

        cal.set(UmmalquraCalendar.MONTH, UmmalquraCalendar.RABI_AWWAL);
        Date date = cal.getTime();

        String expected = String
                .format("%s-%s-%s", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
                        cal.get(Calendar.DAY_OF_MONTH));
        assertEquals("Change month from '5' to '3' for '1436-05-23' should result in '1436-3-23'",
                expected, "1436-3-23");
        assertEquals("Change month from '5' to '3' for '1436-05-23' should result in '2015-1-14'",
                "2015-1-14", dateFormat.format(date));
    }

    @Test
    public void testSetDayField() {
        UmmalquraCalendar cal = new UmmalquraCalendar(1436, UmmalquraCalendar.JUMADA_AWWAL, 23, 12,
                19, 44);

        cal.set(UmmalquraCalendar.DAY_OF_MONTH, 7);
        Date date = cal.getTime();

        String expected = String
                .format("%s-%s-%s", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
                        cal.get(Calendar.DAY_OF_MONTH));
        assertEquals("Change day from '23' to '7' for '1436-05-23' should result in '1436-5-7'",
                expected, "1436-5-7");
        assertEquals("Change day from '23' to '7' for '1436-05-23' should result in '2015-2-26'",
                "2015-2-26", dateFormat.format(date));
    }

    @Test
    public void testLengthOfMonth() {
        assertEquals(30,
                new UmmalquraCalendar(1437, UmmalquraCalendar.MUHARRAM, 1).lengthOfMonth());
        assertEquals(29, new UmmalquraCalendar(1437, UmmalquraCalendar.SAFAR, 1).lengthOfMonth());
        assertEquals(30,
                new UmmalquraCalendar(1437, UmmalquraCalendar.RABI_AWWAL, 1).lengthOfMonth());
        assertEquals(30,
                new UmmalquraCalendar(1437, UmmalquraCalendar.RABI_THANI, 1).lengthOfMonth());
        assertEquals(29,
                new UmmalquraCalendar(1437, UmmalquraCalendar.JUMADA_AWWAL, 1).lengthOfMonth());
        assertEquals(29,
                new UmmalquraCalendar(1437, UmmalquraCalendar.JUMADA_THANI, 1).lengthOfMonth());
        assertEquals(30, new UmmalquraCalendar(1437, UmmalquraCalendar.RAJAB, 1).lengthOfMonth());
        assertEquals(29, new UmmalquraCalendar(1437, UmmalquraCalendar.SHAABAN, 1).lengthOfMonth());
        assertEquals(30,
                new UmmalquraCalendar(1437, UmmalquraCalendar.RAMADHAN, 1).lengthOfMonth());
        assertEquals(29, new UmmalquraCalendar(1437, UmmalquraCalendar.SHAWWAL, 1).lengthOfMonth());
        assertEquals(29,
                new UmmalquraCalendar(1437, UmmalquraCalendar.THUL_QIDAH, 1).lengthOfMonth());
        assertEquals(30,
                new UmmalquraCalendar(1437, UmmalquraCalendar.THUL_HIJJAH, 1).lengthOfMonth());
    }

    @Test
    public void testLengthOfYear() {
        assertEquals(355,
                new UmmalquraCalendar(1435, UmmalquraCalendar.MUHARRAM, 1).lengthOfYear());
        assertEquals(354,
                new UmmalquraCalendar(1436, UmmalquraCalendar.MUHARRAM, 1).lengthOfYear());
        assertEquals(354,
                new UmmalquraCalendar(1437, UmmalquraCalendar.MUHARRAM, 1).lengthOfYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectYearBefore1356() {
        new UmmalquraCalendar(1350, UmmalquraCalendar.SHAABAN, 11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectYearAfter1500() {
        new UmmalquraCalendar(1501, UmmalquraCalendar.SHAABAN, 11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInvalidGregorianValue() {
        GregorianCalendar gCal = new GregorianCalendar(1930, Calendar.FEBRUARY, 12);
        Calendar uCal = new UmmalquraCalendar();
        uCal.setTime(gCal.getTime());
    }

    @Test
    public void testConstructor1() {
        UmmalquraCalendar cal = new UmmalquraCalendar(1436, UmmalquraCalendar.JUMADA_AWWAL, 23);
        Date date = cal.getTime();

        assertEquals("'1436-5-23' should result in '2015-3-14'", "2015-3-14",
                dateFormat.format(date));
    }

}
