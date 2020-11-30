package com.github.msarhan.ummalqura.calendar;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * @author Mouaffak A. Sarhan
 */
public class UmmalquraDateFormatSymbolsTest {

    @Test
    public void fallbackToEnglishLocale() {
        final UmmalquraDateFormatSymbols symbols = new UmmalquraDateFormatSymbols(Locale.CHINA);
        final String[] actual = symbols.getMonths();
        final String[] expected = new String[]{
                "Muharram",
                "Safar",
                "Rabi' al-Awwal",
                "Rabi' al-Thani",
                "Jumada al-Ula",
                "Jumada al-Akhirah",
                "Rajab",
                "Sha'ban",
                "Ramadhan",
                "Shawwal",
                "Thul-Qi'dah",
                "Thul-Hijjah"};

        Assert.assertArrayEquals("should fallback to English locale for unsupported locale", expected, actual);
    }

    @Test
    public void loadResourceBundleFromPropertiesFile() {
        final UmmalquraDateFormatSymbols symbols = new UmmalquraDateFormatSymbols(Locale.FRENCH);
        final String[] actual = symbols.getShortMonths();
        final String[] expected = "Mouh,Saf,Rabiʻ-oul-A,Rabiʻ-out-T,Djoum.-l-O,Djoum.-t-T,Radj,Cha,Ram,Chaou,Dou-l-Q,Dou-l-H".split(",");
        Assert.assertArrayEquals("should load resource bundle from properties file",
                expected,
                actual
        );
    }

}
