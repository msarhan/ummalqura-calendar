/*
 * Copyright (c) 2012, 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * Copyright (c) 2012, Stephen Colebourne & Michael Nascimento Santos
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of JSR-310 nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Modified by Mouaffak A. Sarhan
 * Removed unused methods.
 */
package com.github.msarhan.ummalqura.calendar;

import java.io.InputStream;
import java.io.Serializable;
import java.util.*;

class HijrahChronology implements Serializable {

    /**
     * Serialization version.
     */
    private static final long serialVersionUID = 3127340209035924785L;
    /**
     * Singleton instance of the Islamic Umm Al-Qura calendar of Saudi Arabia.
     * Other Hijrah chronology variants may be available from
     */
    public static final HijrahChronology INSTANCE;
    /**
     * Flag to indicate the initialization of configuration data is complete.
     *
     * @see #checkCalendarInit()
     */
    private transient volatile boolean initComplete;
    /**
     * Array of epoch days indexed by Hijrah Epoch month.
     * Computed by {@link #loadCalendarData}.
     */
    private transient int[] hijrahEpochMonthStartDays;
    /**
     * The minimum epoch day of this Hijrah calendar.
     * Computed by {@link #loadCalendarData}.
     */
    private transient int minEpochDay;
    /**
     * The maximum epoch day for which calendar data is available.
     * Computed by {@link #loadCalendarData}.
     */
    private transient int maxEpochDay;
    /**
     * The minimum epoch month.
     * Computed by {@link #loadCalendarData}.
     */
    private transient int hijrahStartEpochMonth;
    /**
     * The minimum length of a month.
     * Computed by {@link #createEpochMonths}.
     */
    private transient int minMonthLength;
    /**
     * The maximum length of a month.
     * Computed by {@link #createEpochMonths}.
     */
    private transient int maxMonthLength;
    /**
     * The minimum length of a year in days.
     * Computed by {@link #createEpochMonths}.
     */
    private transient int minYearLength;
    /**
     * The maximum length of a year in days.
     * Computed by {@link #createEpochMonths}.
     */
    private transient int maxYearLength;

    /**
     * Prefix of property names for Hijrah calendar variants.
     */
    private static final String PROP_PREFIX = "calendar.hijrah.";
    /**
     * Suffix of property names containing the calendar type of a variant.
     */
    private static final String PROP_TYPE_SUFFIX = ".type";

    static {
        try {
            INSTANCE = new HijrahChronology();
        } catch (Exception ex) {
            throw new RuntimeException("Unable to initialize Hijrah-umalqura calendar", ex.getCause());
        }
    }

    /**
     * Create a HijrahChronology for the named variant.
     * The resource and calendar type are retrieved from properties
     * in the {@code calendars.properties}.
     * The property names are {@code "calendar.hijrah." + id}
     * and  {@code "calendar.hijrah." + id + ".type"}
     *
     * @throws DateTimeException        if the calendar type is missing from the properties file.
     * @throws IllegalArgumentException if the id is empty
     */
    private HijrahChronology() throws DateTimeException {
    }

    /**
     * Check and ensure that the calendar data has been initialized.
     * The initialization check is performed at the boundary between
     * public and package methods.  If a public calls another public method
     * a check is not necessary in the caller.
     * The constructors of HijrahDate call {@link #getEpochDay} or
     * {@link #getHijrahDateInfo} so every call from HijrahDate to a
     * HijrahChronology via package private methods has been checked.
     *
     * @throws DateTimeException if the calendar data configuration is
     *                           malformed or IOExceptions occur loading the data
     */
    private void checkCalendarInit() {
        // Keep this short so it can be inlined for performance
        if (!initComplete) {
            loadCalendarData();
            initComplete = true;
        }
    }

    //-----------------------------------------------------------------------
    public boolean isLeapYear(long prolepticYear) {
        checkCalendarInit();
        if (prolepticYear < getMinimumYear() || prolepticYear > getMaximumYear()) {
            return false;
        }
        int len = getYearLength((int) prolepticYear);
        return (len > 354);
    }

    /**
     * Check the validity of a year.
     *
     * @param prolepticYear the year to check
     */
    int checkValidYear(long prolepticYear) {
        if (prolepticYear < getMinimumYear() || prolepticYear > getMaximumYear()) {
            throw new DateTimeException("Invalid Hijrah year: " + prolepticYear);
        }
        return (int) prolepticYear;
    }

    void checkValidDayOfYear(int dayOfYear) {
        if (dayOfYear < 1 || dayOfYear > getMaximumDayOfYear()) {
            throw new DateTimeException("Invalid Hijrah day of year: " + dayOfYear);
        }
    }

    void checkValidMonth(int month) {
        if (month < 1 || month > 12) {
            throw new DateTimeException("Invalid Hijrah month: " + month);
        }
    }

    //-----------------------------------------------------------------------

    /**
     * Returns an array containing the Hijrah year, month and day
     * computed from the epoch day.
     *
     * @param epochDay the EpochDay
     * @return int[0] = YEAR, int[1] = MONTH, int[2] = DATE
     */
    int[] getHijrahDateInfo(int epochDay) {
        checkCalendarInit();    // ensure that the chronology is initialized
        if (epochDay < minEpochDay || epochDay >= maxEpochDay) {
            throw new DateTimeException("Hijrah date out of range");
        }

        int epochMonth = epochDayToEpochMonth(epochDay);
        int year = epochMonthToYear(epochMonth);
        int month = epochMonthToMonth(epochMonth);
        int day1 = epochMonthToEpochDay(epochMonth);
        int date = epochDay - day1; // epochDay - dayOfEpoch(year, month);

        int[] dateInfo = new int[3];
        dateInfo[0] = year;
        dateInfo[1] = month; // ms: keep 0-based.
        dateInfo[2] = date + 1; // change to 1-based.
        return dateInfo;
    }

    /**
     * Return the epoch day computed from Hijrah year, month, and day.
     *
     * @param prolepticYear the year to represent, 0-origin
     * @param monthOfYear   the month-of-year to represent, 1-origin
     * @param dayOfMonth    the day-of-month to represent, 1-origin
     * @return the epoch day
     */
    long getEpochDay(int prolepticYear, int monthOfYear, int dayOfMonth) {
        checkCalendarInit();    // ensure that the chronology is initialized
        checkValidMonth(monthOfYear);
        int epochMonth = yearToEpochMonth(prolepticYear) + (monthOfYear - 1);
        if (epochMonth < 0 || epochMonth >= hijrahEpochMonthStartDays.length) {
            throw new DateTimeException("Invalid Hijrah date, year: " +
                    prolepticYear + ", month: " + monthOfYear);
        }
        if (dayOfMonth < 1 || dayOfMonth > getMonthLength(prolepticYear, monthOfYear)) {
            throw new DateTimeException("Invalid Hijrah day of month: " + dayOfMonth);
        }
        return epochMonthToEpochDay(epochMonth) + (dayOfMonth - 1);
    }

    /**
     * Returns day of year for the year and month.
     *
     * @param prolepticYear a proleptic year
     * @param month         a month, 1-origin
     * @return the day of year, 1-origin
     */
    int getDayOfYear(int prolepticYear, int month) {
        return yearMonthToDayOfYear(prolepticYear, (month - 1));
    }

    /**
     * Returns month length for the year and month.
     *
     * @param prolepticYear a proleptic year
     * @param monthOfYear   a month, 1-origin.
     * @return the length of the month
     */
    int getMonthLength(int prolepticYear, int monthOfYear) {
        int epochMonth = yearToEpochMonth(prolepticYear) + (monthOfYear - 1);
        if (epochMonth < 0 || epochMonth >= hijrahEpochMonthStartDays.length) {
            throw new DateTimeException("Invalid Hijrah date, year: " +
                    prolepticYear + ", month: " + monthOfYear);
        }
        return epochMonthLength(epochMonth);
    }

    /**
     * Returns year length.
     * Note: The 12th month must exist in the data.
     *
     * @param prolepticYear a proleptic year
     * @return year length in days
     */
    int getYearLength(int prolepticYear) {
        return yearMonthToDayOfYear(prolepticYear, 12);
    }

    /**
     * Return the minimum supported Hijrah year.
     *
     * @return the minimum
     */
    int getMinimumYear() {
        return epochMonthToYear(0);
    }

    /**
     * Return the maximum supported Hijrah ear.
     *
     * @return the minimum
     */
    int getMaximumYear() {
        return epochMonthToYear(hijrahEpochMonthStartDays.length - 1) - 1;
    }

    /**
     * Returns maximum day-of-month.
     *
     * @return maximum day-of-month
     */
    int getMaximumMonthLength() {
        return maxMonthLength;
    }

    /**
     * Returns smallest maximum day-of-month.
     *
     * @return smallest maximum day-of-month
     */
    int getMinimumMonthLength() {
        return minMonthLength;
    }

    /**
     * Returns maximum day-of-year.
     *
     * @return maximum day-of-year
     */
    int getMaximumDayOfYear() {
        return maxYearLength;
    }

    /**
     * Returns smallest maximum day-of-year.
     *
     * @return smallest maximum day-of-year
     */
    int getSmallestMaximumDayOfYear() {
        return minYearLength;
    }

    /**
     * Returns the epochMonth found by locating the epochDay in the table. The
     * epochMonth is the index in the table
     *
     * @param epochDay epochDay
     * @return The index of the element of the start of the month containing the
     * epochDay.
     */
    private int epochDayToEpochMonth(int epochDay) {
        // binary search
        int ndx = Arrays.binarySearch(hijrahEpochMonthStartDays, epochDay);
        if (ndx < 0) {
            ndx = -ndx - 2;
        }
        return ndx;
    }

    /**
     * Returns the year computed from the epochMonth
     *
     * @param epochMonth the epochMonth
     * @return the Hijrah Year
     */
    private int epochMonthToYear(int epochMonth) {
        return (epochMonth + hijrahStartEpochMonth) / 12;
    }

    /**
     * Returns the epochMonth for the Hijrah Year.
     *
     * @param year the HijrahYear
     * @return the epochMonth for the beginning of the year.
     */
    private int yearToEpochMonth(int year) {
        return (year * 12) - hijrahStartEpochMonth;
    }

    /**
     * Returns the Hijrah month from the epochMonth.
     *
     * @param epochMonth the epochMonth
     * @return the month of the Hijrah Year
     */
    private int epochMonthToMonth(int epochMonth) {
        return (epochMonth + hijrahStartEpochMonth) % 12;
    }

    /**
     * Returns the epochDay for the start of the epochMonth.
     *
     * @param epochMonth the epochMonth
     * @return the epochDay for the start of the epochMonth.
     */
    private int epochMonthToEpochDay(int epochMonth) {
        return hijrahEpochMonthStartDays[epochMonth];

    }

    /**
     * Returns the day of year for the requested HijrahYear and month.
     *
     * @param prolepticYear the Hijrah year
     * @param month         the Hijrah month
     * @return the day of year for the start of the month of the year
     */
    private int yearMonthToDayOfYear(int prolepticYear, int month) {
        int epochMonthFirst = yearToEpochMonth(prolepticYear);
        return epochMonthToEpochDay(epochMonthFirst + month)
                - epochMonthToEpochDay(epochMonthFirst);
    }

    /**
     * Returns the length of the epochMonth. It is computed from the start of
     * the following month minus the start of the requested month.
     *
     * @param epochMonth the epochMonth; assumed to be within range
     * @return the length in days of the epochMonth
     */
    private int epochMonthLength(int epochMonth) {
        // The very last entry in the epochMonth table is not the start of a month
        return hijrahEpochMonthStartDays[epochMonth + 1]
                - hijrahEpochMonthStartDays[epochMonth];
    }

    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_VERSION = "version";
    private static final String KEY_ISO_START = "iso-start";

    /**
     * Return the configuration properties from the resource.
     * <p>
     * The default location of the variant configuration resource is:
     * <pre>
     *   "$java.home/lib/" + resource-name
     * </pre>
     *
     * @return a Properties containing the properties read from the resource.
     * @throws Exception if access to the property resource fails
     */
    private static Properties readConfigProperties() throws Exception {
        Properties props = new Properties();
        try (InputStream is = HijrahChronology.class.getResourceAsStream("/com/github/msarhan/ummalqura/calendar/hijrah-config.properties")) {
            props.load(is);
        }
        return props;
    }

    /**
     * Loads and processes the Hijrah calendar properties file for this calendarType.
     * The starting Hijrah date and the corresponding ISO date are
     * extracted and used to calculate the epochDate offset.
     * The version number is identified and ignored.
     * Everything else is the data for a year with containing the length of each
     * of 12 months.
     *
     * @throws DateTimeException if initialization of the calendar data from the
     *                           resource fails
     */
    private void loadCalendarData() {
        try {
            Properties props = readConfigProperties();

            Map<Integer, int[]> years = new HashMap<>();
            int minYear = Integer.MAX_VALUE;
            int maxYear = Integer.MIN_VALUE;
            String version = null;
            int isoStart = 0;
            for (Map.Entry<Object, Object> entry : props.entrySet()) {
                String key = (String) entry.getKey();
                switch (key) {
                    case KEY_VERSION:
                        version = (String) entry.getValue();
                        break;
                    case KEY_ISO_START: {
                        int[] ymd = parseYMD((String) entry.getValue());
                        isoStart = (int) toIsoEpochDay(ymd[0], ymd[1], ymd[2]);
                        break;
                    }
                    case KEY_TYPE:
                    case KEY_ID:
                        break;
                    default:
                        try {
                            // Everything else is either a year or invalid
                            int year = Integer.parseInt(key);
                            int[] months = parseMonths((String) entry.getValue());
                            years.put(year, months);
                            maxYear = Math.max(maxYear, year);
                            minYear = Math.min(minYear, year);
                        } catch (NumberFormatException nfe) {
                            throw new IllegalArgumentException("bad key: " + key);
                        }
                }
            }

            if (version == null || version.isEmpty()) {
                throw new IllegalArgumentException("Configuration does not contain a version");
            }
            if (isoStart == 0) {
                throw new IllegalArgumentException("Configuration does not contain a ISO start date");
            }

            // Now create and validate the array of epochDays indexed by epochMonth
            hijrahStartEpochMonth = minYear * 12;
            minEpochDay = isoStart;
            hijrahEpochMonthStartDays = createEpochMonths(minEpochDay, minYear, maxYear, years);
            maxEpochDay = hijrahEpochMonthStartDays[hijrahEpochMonthStartDays.length - 1];

            // Compute the min and max year length in days.
            for (int year = minYear; year < maxYear; year++) {
                int length = getYearLength(year);
                minYearLength = Math.min(minYearLength, length);
                maxYearLength = Math.max(maxYearLength, length);
            }
        } catch (Exception ex) {
            throw new DateTimeException("Unable to initialize HijrahCalendar", ex);
        }
    }

    /**
     * Converts the map of year to month lengths ranging from minYear to maxYear
     * into a linear contiguous array of epochDays. The index is the hijrahMonth
     * computed from year and month and offset by minYear. The value of each
     * entry is the epochDay corresponding to the first day of the month.
     *
     * @param minYear The minimum year for which data is provided
     * @param maxYear The maximum year for which data is provided
     * @param years   a Map of year to the array of 12 month lengths
     * @return array of epochDays for each month from min to max
     */
    private int[] createEpochMonths(int epochDay, int minYear, int maxYear, Map<Integer, int[]> years) {
        // Compute the size for the array of dates
        int numMonths = (maxYear - minYear + 1) * 12 + 1;

        // Initialize the running epochDay as the corresponding ISO Epoch day
        int epochMonth = 0; // index into array of epochMonths
        int[] epochMonths = new int[numMonths];
        minMonthLength = Integer.MAX_VALUE;
        maxMonthLength = Integer.MIN_VALUE;

        // Only whole years are valid, any zero's in the array are illegal
        for (int year = minYear; year <= maxYear; year++) {
            int[] months = years.get(year);// must not be gaps
            for (int month = 0; month < 12; month++) {
                int length = months[month];
                epochMonths[epochMonth++] = epochDay;

                if (length < 29 || length > 32) {
                    throw new IllegalArgumentException("Invalid month length in year: " + minYear);
                }
                epochDay += length;
                minMonthLength = Math.min(minMonthLength, length);
                maxMonthLength = Math.max(maxMonthLength, length);
            }
        }

        // Insert the final epochDay
        epochMonths[epochMonth++] = epochDay;

        if (epochMonth != epochMonths.length) {
            throw new IllegalStateException("Did not fill epochMonths exactly: ndx = " + epochMonth
                    + " should be " + epochMonths.length);
        }

        return epochMonths;
    }

    /**
     * Parses the 12 months lengths from a property value for a specific year.
     *
     * @param line the value of a year property
     * @return an array of int[12] containing the 12 month lengths
     * @throws IllegalArgumentException if the number of months is not 12
     * @throws NumberFormatException    if the 12 tokens are not numbers
     */
    private int[] parseMonths(String line) {
        int[] months = new int[12];
        String[] numbers = line.split("\\s");
        if (numbers.length != 12) {
            throw new IllegalArgumentException("wrong number of months on line: " + Arrays.toString(numbers) + "; count: " + numbers.length);
        }
        for (int i = 0; i < 12; i++) {
            try {
                months[i] = Integer.parseInt(numbers[i]);
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("bad key: " + numbers[i]);
            }
        }
        return months;
    }

    /**
     * Parse yyyy-MM-dd into a 3 element array [yyyy, mm, dd].
     *
     * @param string the input string
     * @return the 3 element array with year, month, day
     */
    private int[] parseYMD(String string) {
        // yyyy-MM-dd
        string = string.trim();
        try {
            if (string.charAt(4) != '-' || string.charAt(7) != '-') {
                throw new IllegalArgumentException("date must be yyyy-MM-dd");
            }
            int[] ymd = new int[3];
            ymd[0] = Integer.parseInt(string.substring(0, 4));
            ymd[1] = Integer.parseInt(string.substring(5, 7));
            ymd[2] = Integer.parseInt(string.substring(8, 10));
            return ymd;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("date must be yyyy-MM-dd", ex);
        }
    }

    // -------------------------
    private static final int DAYS_PER_CYCLE = 146097;
    static final long DAYS_0000_TO_1970 = (DAYS_PER_CYCLE * 5L) - (30L * 365L + 7L);

    public static Date fromIsoEpochDay(long epochDay) {
        long zeroDay = epochDay + DAYS_0000_TO_1970;
        // find the march-based year
        zeroDay -= 60;  // adjust to 0000-03-01 so leap day is at end of four year cycle
        long adjust = 0;
        if (zeroDay < 0) {
            // adjust negative years to positive for calculation
            long adjustCycles = (zeroDay + 1) / DAYS_PER_CYCLE - 1;
            adjust = adjustCycles * 400;
            zeroDay += -adjustCycles * DAYS_PER_CYCLE;
        }
        long yearEst = (400 * zeroDay + 591) / DAYS_PER_CYCLE;
        long doyEst = zeroDay - (365 * yearEst + yearEst / 4 - yearEst / 100 + yearEst / 400);
        if (doyEst < 0) {
            // fix estimate
            yearEst--;
            doyEst = zeroDay - (365 * yearEst + yearEst / 4 - yearEst / 100 + yearEst / 400);
        }
        yearEst += adjust;  // reset any negative year
        int marchDoy0 = (int) doyEst;

        // convert march-based values back to january-based
        int marchMonth0 = (marchDoy0 * 5 + 2) / 153;
        int month = (marchMonth0 + 2) % 12 + 1;
        int dom = marchDoy0 - (marchMonth0 * 306 + 5) / 10 + 1;
        yearEst += marchMonth0 / 10;

        return new GregorianCalendar((int) yearEst, month - 1, dom).getTime();
    }

    public long toIsoEpochDay(int year, int month, int day) {
        long y = year;
        long m = month;
        long total = 0;
        total += 365 * y;
        if (y >= 0) {
            total += (y + 3) / 4 - (y + 99) / 100 + (y + 399) / 400;
        } else {
            total -= y / -4 - y / -100 + y / -400;
        }
        total += ((367 * m - 362) / 12);
        total += day - 1;
        if (m > 2) {
            total--;
            if (!isIsoLeapYear(year)) {
                total--;
            }
        }
        return total - DAYS_0000_TO_1970;
    }

    public boolean isIsoLeapYear(long prolepticYear) {
        return ((prolepticYear & 3) == 0) && ((prolepticYear % 100) != 0 || (prolepticYear % 400) == 0);
    }

    static int[] toHijri(Date date) {
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return INSTANCE.getHijrahDateInfo((int) INSTANCE.toIsoEpochDay(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH)
        ));
    }

    static int[] toGregorian(int hYear, int hMonth, int hDay) {
        final Date date = HijrahChronology.fromIsoEpochDay(INSTANCE.getEpochDay(hYear, hMonth + 1, hDay));
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return new int[]{
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        };
    }

    static int getDaysInMonth(int year, int month) {
        return INSTANCE.getMonthLength(year, month);
    }

}
