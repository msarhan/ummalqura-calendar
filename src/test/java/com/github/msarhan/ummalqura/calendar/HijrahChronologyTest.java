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

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertTrue;

/**
 * @author msarhan.
 */
public class HijrahChronologyTest {

    @Test
    public void g2h1() {
        Calendar cal = new GregorianCalendar(2015, Calendar.MARCH, 14);
        int[] hDateInfo = HijrahChronology.toHijri(cal.getTime());
        System.out.println(Arrays.toString(hDateInfo));
        assertTrue("2015-03-14 G should convert to: 1436-05-23 H",
                hDateInfo[0] == 1436 && hDateInfo[1] == UmmalquraCalendar.JUMADA_AWWAL && hDateInfo[2] == 23);
    }

    @Test
    public void g2h2() {
        Calendar cal = new GregorianCalendar(1999, Calendar.APRIL, 1);
        int[] hDateInfo = HijrahChronology.toHijri(cal.getTime());

        assertTrue("1999-04-01 G should convert to: 1419-12-14 H",
                hDateInfo[0] == 1419 && hDateInfo[1] == UmmalquraCalendar.THUL_HIJJAH && hDateInfo[2] == 14);
    }

    @Test
    public void g2h3() {
        Calendar cal = new GregorianCalendar(1989, Calendar.FEBRUARY, 25);
        int[] hDateInfo = HijrahChronology.toHijri(cal.getTime());

        assertTrue("1989-02-25 G should convert to: 1409-07-19 H",
                hDateInfo[0] == 1409 && hDateInfo[1] == UmmalquraCalendar.RAJAB && hDateInfo[2] == 18);
    }

    @Test
    public void g2h4() {
        Calendar cal = new GregorianCalendar(2020, Calendar.NOVEMBER, 30);
        int[] hDateInfo = HijrahChronology.toHijri(cal.getTime());

        assertTrue("2020-11-30 G should convert to: 1442-04-15 H",
                hDateInfo[0] == 1442 && hDateInfo[1] == UmmalquraCalendar.RABI_THANI && hDateInfo[2] == 15);
    }

    @Test
    public void g2h5() {
        Calendar cal = new GregorianCalendar(2020, Calendar.OCTOBER, 17);
        int[] hDateInfo = HijrahChronology.toHijri(cal.getTime());
        assertTrue("2020-10-17 G should convert to: 1442-02-30 H",
                hDateInfo[0] == 1442 && hDateInfo[1] == UmmalquraCalendar.SAFAR && hDateInfo[2] == 30);
    }

    @Test
    public void h2g1() {
        int[] hDateInfo = HijrahChronology.toGregorian(1436, UmmalquraCalendar.JUMADA_AWWAL, 23);

        assertTrue("1436-05-23 H should convert to: 2015-03-14 G",
                hDateInfo[0] == 2015 && hDateInfo[1] == Calendar.MARCH && hDateInfo[2] == 14);
    }

    @Test
    public void h2g2() {
        int[] hDateInfo = HijrahChronology.toGregorian(1419, UmmalquraCalendar.THUL_HIJJAH, 15);

        assertTrue("1419-12-15 H should convert to: 1999-04-02 G",
                hDateInfo[0] == 1999 && hDateInfo[1] == Calendar.APRIL && hDateInfo[2] == 2);
    }

    @Test
    public void h2g3() {
        int[] dateInfo = HijrahChronology.toGregorian(1442, UmmalquraCalendar.SAFAR, 30);

        assertTrue("1442-02-30 H should convert to: 2020-10-17 G",
                dateInfo[0] == 2020 && dateInfo[1] == Calendar.OCTOBER && dateInfo[2] == 17);
    }
}
