package org.ummalqura.calendar.text;

import java.util.ListResourceBundle;

/**
 * @author Mouaffak A. Sarhan.
 */
public class UmmalquraFormatData_en extends ListResourceBundle {

	@Override
	protected Object[][] getContents() {
		return new Object[][]{
						{"MonthNames",
										new String[]{
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
														"Thul-Hijjah"}
						},
						{"MonthAbbreviations",
										new String[]{
														"Muh"
														, "Saf"
														, "Rab-I"
														, "Rab-II"
														, "Jum-I"
														, "Jum-II"
														, "Raj"
														, "Sha"
														, "Ram"
														, "Shw"
														, "Thul-Q"
														, "Thul-H"}}
		};
	}

}
