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

import static com.github.msarhan.ummalqura.calendar.UmmalquraCalendar.JUMADA_AWWAL;
import static com.github.msarhan.ummalqura.calendar.UmmalquraCalendar.THUL_QIDAH;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.Before;
import org.junit.Test;

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
    public void testStandAloneCountFormat() throws Exception {
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

        assertEquals("{1436,5,23} should format to '1436-05-23' using 'y-M-d'", "1436-5-23",
                formatDate(en, "y-M-d"));

        assertEquals("{1436,5,23} should format to 'Jum-I 23, 1436' using 'MMM d, y'",
                "Jum-I 23, 1436",
                formatDate(en, "MMM d, y"));

        assertEquals("{1436,5,23} should format to 'Jumada al-Ula 23, 1436' using 'MMMM d, y'",
                "Jumada al-Ula 23, 1436",
                formatDate(en, "MMMM d, y"));

        assertEquals(
                "{1436,5,23} should format to 'Sat, Jumada al-Ula 23, 1436' using 'E, MMMM d, y'",
                "Sat, Jumada al-Ula 23, 1436",
                formatDate(en, "E, MMMM d, y"));

        assertEquals(
                "{1436,5,23} should format to 'Saturday, Jumada al-Ula 23, 1436' using 'EEEE, MMMM d, y'",
                "Saturday, Jumada al-Ula 23, 1436",
                formatDate(en, "EEEE, MMMM d, y"));

        assertEquals(
                "{1436,5,23} should format to 'Saturday, Jumada al-Ula 23, 36' using 'EEEE, MMMM d, yy'",
                "Saturday, Jumada al-Ula 23, 36",
                formatDate(en, "EEEE, MMMM d, yy"));

        assertEquals(
                "Should format to 'Saturday, Jumada al-Ula 23, 36 12:19:44' using 'EEEE, MMMM d, yy hh:mm:ss'",
                "Saturday, Jumada al-Ula 23, 36 12:19:44",
                formatDate(en, "EEEE, MMMM d, yy hh:mm:ss"));

    }

    @Test
    public void formatWithArabicLocale() {

        assertEquals("{1436,5,23} should format to '1436-05-23' using 'y-M-d'", "1436-5-23",
                formatDate(ar, "y-M-d"));

        assertEquals("{1436,5,23} should format to 'جمادى 1 23, 1436' using 'MMM d, y'",
                "جمادى 1 23, 1436",
                formatDate(ar, "MMM d, y"));

        assertEquals("{1436,5,23} should format to 'جمادى الأولى 23, 1436' using 'MMMM d, y'",
                "جمادى الأولى 23, 1436",
                formatDate(ar, "MMMM d, y"));

        assertEquals("{1436,5,23} should format to 'س, جمادى الأولى 23, 1436' using 'E, MMMM d, y'",
                "س, جمادى الأولى 23, 1436",
                formatDate(ar, "E, MMMM d, y"));

        assertEquals(
                "{1436,5,23} should format to 'السبت, جمادى الأولى 23, 1436' using 'EEEE, MMMM d, y'",
                "السبت, جمادى الأولى 23, 1436",
                formatDate(ar, "EEEE, MMMM d, y"));

        assertEquals(
                "{1436,5,23} should format to 'السبت, جمادى الأولى 23, 36' using 'EEEE, MMMM d, yy'",
                "السبت, جمادى الأولى 23, 36",
                formatDate(ar, "EEEE, MMMM d, yy"));

    }

    @Test
    public void testDayOfMonth() {
        UmmalquraCalendar cal = new UmmalquraCalendar(1436, UmmalquraCalendar.JUMADA_AWWAL, 4, 12,
                19, 44);
        assertEquals("4", formatDate(cal, en, "d"));
        assertEquals("04", formatDate(cal, en, "dd"));
        assertEquals("004", formatDate(cal, en, "ddd"));
    }

    //-----------------------------------------------------------------------------------

    @Test
    public void testStandAloneCountParse() throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

        assertEquals(JUMADA_AWWAL, parseDate("5", "M", en).get(Calendar.MONTH));
        assertEquals(JUMADA_AWWAL, parseDate("05", "MM", en).get(Calendar.MONTH));
        assertEquals(JUMADA_AWWAL, parseDate("Jum-I", "MMM", en).get(Calendar.MONTH));
        assertEquals(JUMADA_AWWAL, parseDate("Jumada al-Ula", "MMMM", en).get(Calendar.MONTH));
        assertEquals(THUL_QIDAH, parseDate("Thul-Qi'dah", "MMMM", en).get(Calendar.MONTH));
        assertEquals(JUMADA_AWWAL, parseDate("جمادى 1", "MMM", ar).get(Calendar.MONTH));
        assertEquals(JUMADA_AWWAL, parseDate("جمادى الأولى", "MMMM", ar).get(Calendar.MONTH));

        assertEquals(1436, parseDate("1436", "y", en).get(Calendar.YEAR));
        assertEquals(1435, parseDate("35", "yy", en).get(Calendar.YEAR));
        assertEquals(1435, parseDate("1435", "yyyy", en).get(Calendar.YEAR));

        assertEquals(9, parseDate("9", "d", en).get(Calendar.DAY_OF_MONTH));
        assertEquals(9, parseDate("09", "dd", en).get(Calendar.DAY_OF_MONTH));
        assertEquals(9, parseDate("009", "ddd", en).get(Calendar.DAY_OF_MONTH));

        assertEquals(Calendar.SATURDAY, parseDate("Sat", "E", en).get(Calendar.DAY_OF_WEEK));
        assertEquals(Calendar.SATURDAY,
                parseDate("Saturday", "EEEE", en).get(Calendar.DAY_OF_WEEK));
        assertEquals(Calendar.SATURDAY, parseDate("س", "E", ar).get(Calendar.DAY_OF_WEEK));
        assertEquals(Calendar.SATURDAY, parseDate("السبت", "EEEE", ar).get(Calendar.DAY_OF_WEEK));
    }

    @Test
    public void parseWithEnglishLocale() throws ParseException {

        Calendar uCal = parseDate("1436-5-23", "y-M-d", en);
        assertEquals("'1436-5-23' should parse using 'y-M-d' format",
                "1436-5-23",
                String.format("%d-%d-%d", uCal.get(Calendar.YEAR), uCal.get(Calendar.MONTH) + 1,
                        uCal.get(Calendar.DAY_OF_MONTH)));

        uCal = parseDate("Jum-I 7, 1436", "MMM d, y", en);
        assertEquals("'Jum-I 7, 1436' should parse using 'MMM d, y' format",
                "1436-5-7",
                String.format("%d-%d-%d", uCal.get(Calendar.YEAR), uCal.get(Calendar.MONTH) + 1,
                        uCal.get(Calendar.DAY_OF_MONTH)));

        uCal = parseDate("Jumada al-Ula 10, 1436", "MMMM d, y", en);
        assertEquals("'Jumada al-Ula 10, 1436' should parse using 'MMMM d, y' format",
                "1436-5-10",
                String.format("%d-%d-%d", uCal.get(Calendar.YEAR), uCal.get(Calendar.MONTH) + 1,
                        uCal.get(Calendar.DAY_OF_MONTH)));

        uCal = parseDate("Sun, Jumada al-Ula 15, 1430", "E, MMMM d, y", en);
        assertEquals("'Sun, Jumada al-Ula 15, 1430' should parse using 'E, MMMM d, y' format",
                "1430-5-15",
                String.format("%d-%d-%d", uCal.get(Calendar.YEAR), uCal.get(Calendar.MONTH) + 1,
                        uCal.get(Calendar.DAY_OF_MONTH)));

        uCal = parseDate("Friday, Jumada al-Ula 23, 1431", "EEEE, MMMM d, y", en);
        assertEquals("'Friday, Jumada al-Ula 23, 1431' should parse using 'EEEE, MMMM d, y' format",
                "1431-5-23",
                String.format("%d-%d-%d", uCal.get(Calendar.YEAR), uCal.get(Calendar.MONTH) + 1,
                        uCal.get(Calendar.DAY_OF_MONTH)));

        uCal = parseDate("Sunday, Sha'ban 07, 1434", "EEEE, MMMM dd, y", en);
        assertEquals("'Sunday, Sha'ban 07, 1434' should parse using 'EEEE, MMMM d, y' format",
                "1434-8-7",
                String.format("%d-%d-%d", uCal.get(Calendar.YEAR), uCal.get(Calendar.MONTH) + 1,
                        uCal.get(Calendar.DAY_OF_MONTH)));

        uCal = parseDate("Friday, Jumada al-Ula 9, 31", "EEEE, MMMM d, yy", en);
        assertEquals("'Friday, Jumada al-Ula 9, 31' should parse using 'EEEE, MMMM d, yy' format",
                "1431-5-9",
                String.format("%d-%d-%d", uCal.get(Calendar.YEAR), uCal.get(Calendar.MONTH) + 1,
                        uCal.get(Calendar.DAY_OF_MONTH)));

        uCal = parseDate("Saturday, Jumada al-Ula 23, 36 12:19:44", "EEEE, MMMM d, yy hh:mm:ss",
                en);
        assertEquals(
                "'Saturday, Jumada al-Ula 23, 36 12:19:44' should parse using 'EEEE, MMMM d, yy hh:mm:ss' format",
                "1436-5-23",
                String.format("%d-%d-%d", uCal.get(Calendar.YEAR), uCal.get(Calendar.MONTH) + 1,
                        uCal.get(Calendar.DAY_OF_MONTH)));

    }

    @Test
    public void parseWithArabicLocale() throws ParseException {

        Calendar uCal = parseDate("1436-5-23", "y-M-d", ar);
        assertEquals("'1436-5-23' should parse using 'y-M-d' format",
                "1436-5-23",
                String.format("%d-%d-%d", uCal.get(Calendar.YEAR), uCal.get(Calendar.MONTH) + 1,
                        uCal.get(Calendar.DAY_OF_MONTH)));

        uCal = parseDate("جمادى 2 23, 1436", "MMM d, y", ar);
        assertEquals("'جمادى 2 23, 1436' should parse using 'MMM d, y' format",
                "1436-6-23",
                String.format("%d-%d-%d", uCal.get(Calendar.YEAR), uCal.get(Calendar.MONTH) + 1,
                        uCal.get(Calendar.DAY_OF_MONTH)));

        uCal = parseDate("جمادى الآخرة 23, 1436", "MMMM d, y", ar);
        assertEquals("'جمادى الآخرة 23, 1436' should parse using 'MMM d, y' format",
                "1436-6-23",
                String.format("%d-%d-%d", uCal.get(Calendar.YEAR), uCal.get(Calendar.MONTH) + 1,
                        uCal.get(Calendar.DAY_OF_MONTH)));

        uCal = parseDate("س, جمادى الأولى 23, 1436", "E, MMMM d, y", ar);
        assertEquals(" 'س, جمادى الأولى 23, 1436' should parse using 'E, MMMM d, y' format",
                "1436-5-23",
                String.format("%d-%d-%d", uCal.get(Calendar.YEAR), uCal.get(Calendar.MONTH) + 1,
                        uCal.get(Calendar.DAY_OF_MONTH)));

        uCal = parseDate("السبت, جمادى الأولى 23, 1436", "EEEE, MMMM d, y", ar);
        assertEquals(" 'السبت, جمادى الأولى 23, 1436' should parse using 'EEEE, MMMM d, y' format",
                "1436-5-23",
                String.format("%d-%d-%d", uCal.get(Calendar.YEAR), uCal.get(Calendar.MONTH) + 1,
                        uCal.get(Calendar.DAY_OF_MONTH)));

        uCal = parseDate("السبت, جمادى الأولى 23, 36", "EEEE, MMMM d, yy", ar);
        assertEquals(" 'السبت, جمادى الأولى 23, 36' should parse using 'EEEE, MMMM d, yy' format",
                "1436-5-23",
                String.format("%d-%d-%d", uCal.get(Calendar.YEAR), uCal.get(Calendar.MONTH) + 1,
                        uCal.get(Calendar.DAY_OF_MONTH)));

    }

    //-----------------------------------------------------------------------------------

    private String formatDate(Locale l, String fmt) {
        return formatDate(cal, l, fmt);
    }

    private String formatDate(Calendar cal, Locale l, String fmt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(fmt, l);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        dateFormat.setCalendar(cal);
        return dateFormat.format(cal.getTime());
    }

    private UmmalquraCalendar parseDate(String dateText, String fmt, Locale l)
            throws ParseException {
        UmmalquraCalendar uCal = new UmmalquraCalendar(l);

        SimpleDateFormat dateFormat = new SimpleDateFormat(fmt, l);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        dateFormat.setCalendar(uCal);
        uCal.set(Calendar.YEAR, 1420);
        dateFormat.set2DigitYearStart(uCal.getTime());

        uCal.setTime(dateFormat.parse(dateText));
        return uCal;
    }

}
