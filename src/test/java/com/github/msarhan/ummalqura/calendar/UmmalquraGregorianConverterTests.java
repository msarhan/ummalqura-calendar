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

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Test;

/**
 * @author msarhan.
 */
public class UmmalquraGregorianConverterTests {

    @Test
    public void gregorianToHijri1() {
        Calendar cal = new GregorianCalendar(2015, Calendar.MARCH, 14);
        int[] hDateInfo = UmmalquraGregorianConverter.toHijri(cal.getTime());

        assertTrue("2015-03-14 G should be 1436-05-23 H",
                hDateInfo[0] == 1436 && hDateInfo[1] == 4 && hDateInfo[2] == 23);
    }

    @Test
    public void gregorianToHijri2() {
        Calendar cal = new GregorianCalendar(1999, Calendar.APRIL, 1);
        int[] hDateInfo = UmmalquraGregorianConverter.toHijri(cal.getTime());

        assertTrue("1999-04-01 G should be 1419-12-15 H",
                hDateInfo[0] == 1419 && hDateInfo[1] == 11 && hDateInfo[2] == 15);
    }

    @Test
    public void gregorianToHijri3() {
        Calendar cal = new GregorianCalendar(1989, Calendar.FEBRUARY, 25);
        int[] hDateInfo = UmmalquraGregorianConverter.toHijri(cal.getTime());

        assertTrue("1989-02-25 G should be 1409-07-19 H",
                hDateInfo[0] == 1409 && hDateInfo[1] == 6 && hDateInfo[2] == 19);
    }

    @Test
    public void hijriToGregorian1() {
        int[] hDateInfo = UmmalquraGregorianConverter.toGregorian(1436, 4, 23);

        assertTrue("1436-05-23 H should be 2015-03-14 G",
                hDateInfo[0] == 2015 && hDateInfo[1] == 2 && hDateInfo[2] == 14);
    }

    @Test
    public void hijriToGregorian2() {
        int[] hDateInfo = UmmalquraGregorianConverter.toGregorian(1419, 11, 15);

        assertTrue("1419-12-15 H should be 1999-04-01 G",
                hDateInfo[0] == 1999 && hDateInfo[1] == 3 && hDateInfo[2] == 1);
    }

}
