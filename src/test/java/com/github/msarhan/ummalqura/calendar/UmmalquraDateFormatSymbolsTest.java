package com.github.msarhan.ummalqura.calendar;

import java.util.Locale;
import org.junit.Test;

/**
 * @author Mouaffak A. Sarhan
 */
public class UmmalquraDateFormatSymbolsTest {

    @Test(expected = IllegalArgumentException.class)
    public void rejectUnsupportedLocale() {
        new UmmalquraDateFormatSymbols(Locale.CHINA);
    }

}
