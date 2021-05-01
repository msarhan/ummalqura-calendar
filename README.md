[![Build Status](https://travis-ci.com/msarhan/ummalqura-calendar.svg?branch=master)](https://travis-ci.com/msarhan/ummalqura-calendar)
[![Javadoc](https://www.javadoc.io/badge/com.github.msarhan/ummalqura-calendar.svg)](https://www.javadoc.io/doc/com.github.msarhan/ummalqura-calendar)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/msarhan/ummalqura-calendar/blob/master/LICENSE)
[![Hits-of-Code](https://hitsofcode.com/github/msarhan/ummalqura-calendar?branch=master)](https://hitsofcode.com/view/github/msarhan/ummalqura-calendar?branch=master)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.msarhan/ummalqura-calendar.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.msarhan/ummalqura-calendar)

# ummalqura-calendar
> Implementation of [`java.util.Calendar`](https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html) for the Umm Al-Qura calendar system.
Calculation used in `v2.0.0` was derived from [Java 8](https://docs.oracle.com/javase/8/docs/api/java/time/chrono/HijrahChronology.html).
This calculation is valid from **1300H** to **1600H**.

## Table of contents
  - [Installation](#installation)
  - [Usage](#usage)
    - [Using parametrized constructors](#using-parametrized-constructors)
    - [Get + Set](#get--set)
    - [Conversion](#conversion)
      - [From Gregorian to Ummalqura](#from-gregorian-to-ummalqura)
      - [From Ummalqura to Gregorian](#from-ummalqura-to-gregorian)
    - [Formatting](#formatting)
      - [Format using English locale](#format-using-english-locale)
      - [Format using Arabic locale](#format-using-arabic-locale)
    - [Parsing](#parsing)
      - [Parse using English locale](#parse-using-english-locale)
      - [Parse using Arabic locale](#parse-using-arabic-locale)
  - [Localization: Add support for additional locales](#localization-add-support-for-additional-locales)

## Installation
```xml
<dependency>
	<groupId>com.github.msarhan</groupId>
	<artifactId>ummalqura-calendar</artifactId>
	<version>2.0.1</version>
</dependency>
```

## Usage

```java
// Suppose current gregorian data/time is: Fri Apr 03 18:12:38 AST 2015
UmmalquraCalendar cal = new UmmalquraCalendar();
cal.get(Calendar.YEAR);         // 1436
cal.get(Calendar.MONTH);        // 5 <=> Jumada al-Akhirah
cal.get(Calendar.DAY_OF_MONTH); // 14
```

### Using parametrized constructors

```java
Calendar cal = new UmmalquraCalendar(1436, UmmalquraCalendar.SHAABAN, 11);
cal.getTime(); // Fri May 29 00:00:00 AST 2015
```
```java
Calendar cal = new UmmalquraCalendar(1436, UmmalquraCalendar.SHAABAN, 11, 18, 12, 38);
cal.getTime(); // Fri May 29 18:12:38 AST 2015
```

### Get + Set

```java
Calendar cal = new UmmalquraCalendar();
cal.set(Calendar.YEAR, 1436);
cal.set(Calendar.MONTH, UmmalquraCalendar.SHAABAN);
cal.set(Calendar.DAY_OF_MONTH, 11);

cal.getTime(); // Fri May 29 20:57:37 AST 2015
```

```java
Calendar cal = new UmmalquraCalendar(1436, UmmalquraCalendar.SHAABAN, 11);
cal.get(Calendar.YEAR);         // 1436
cal.get(Calendar.MONTH);        // 7
cal.get(Calendar.DAY_OF_MONTH); // 11
```

Get month length in days
```java
Calendar cal = new UmmalquraCalendar(1437, UmmalquraCalendar.MUHARRAM, 1);
cal.lengthOfMonth();                                               // 30
// Or using static version:
UmmalquraCalendar.lengthOfMonth(1437, UmmalquraCalendar.MUHARRAM); // 30
UmmalquraCalendar.lengthOfMonth(1437, UmmalquraCalendar.SAFAR);    // 29
```

Get year length in days
```java
Calendar cal = new UmmalquraCalendar(1437, UmmalquraCalendar.MUHARRAM, 1);
cal.lengthOfYear();                   // 354
// Or using static version:
UmmalquraCalendar.lengthOfYear(1435); // 355	'Leap year'
UmmalquraCalendar.lengthOfYear(1436); // 354
UmmalquraCalendar.lengthOfYear(1437); // 354
```

Read month or week day name in a given style and `java.util.Locale`.
```java
Calendar cal = new UmmalquraCalendar(1436, UmmalquraCalendar.RABI_THANI, 21);

cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH);         // Rab-II
cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);          // Rabi' al-Thani

cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, new Locale("ar"));       // ربيع 2
cal.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("ar"));        // ربيع الثاني

cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH);   // Tue
cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);    // Tuesday

cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, new Locale("ar")); // ث
cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("ar"));  // الثلاثاء
```
Read month or week day names as a `java.util.Map` in a given style and `java.util.Locale`.
Where entry key will be the field name and entry value will be the corresponding field value.
```java
Calendar cal = new UmmalquraCalendar();

cal.getDisplayNames(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH);
//{Thul-Q=10, Rab-I=2, Saf=1, Shw=9, Muh=0, Thul-H=11, Sha=7, Rab-II=3, Raj=6, Ram=8, Jum-I=4, Jum-II=5}
cal.getDisplayNames(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
//{Ramadhan=8, Rajab=6, Jumada al-Ula=4, Thul-Qi'dah=10, Sha'ban=7, Muharram=0, Safar=1, Thul-Hijjah=11, Shawwal=9, Rabi' al-Awwal=2, Jumada al-Akhirah=5, Rabi' al-Thani=3}

cal.getDisplayNames(Calendar.MONTH, Calendar.SHORT, new Locale("ar"));
//{جمادى 2=5, رمضان=8, جمادى 1=4, ربيع 2=3, ذو القعدة=10, صفر=1, محرم=0, رجب=6, ربيع 1=2, شوال=9, شعبان=7, ذو الحجة=11}
cal.getDisplayNames(Calendar.MONTH, Calendar.LONG, new Locale("ar"));
//{جمادى الآخرة=5, رمضان=8, ذو القعدة=10, صفر=1, محرم=0, رجب=6, ربيع الثاني=3, جمادى الأولى=4, شوال=9, شعبان=7, ربيع الأول=2, ذو الحجة=11}

cal.getDisplayNames(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH);
//{Thu=5, Wed=4, Sun=1, Sat=7, Fri=6, Tue=3, Mon=2}
cal.getDisplayNames(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);
//{Saturday=7, Thursday=5, Monday=2, Tuesday=3, Wednesday=4, Friday=6, Sunday=1}

cal.getDisplayNames(Calendar.DAY_OF_WEEK, Calendar.SHORT, new Locale("ar"));
//{خ=5, ج=6, ح=1, ث=3, س=7, ر=4, ن=2}
cal.getDisplayNames(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("ar"));
//{الاثنين=2, الخميس=5, الجمعة=6, الأربعاء=4, الثلاثاء=3, الأحد=1, السبت=7}
```

### Conversion
#### From Gregorian to Ummalqura

```java
GregorianCalendar gCal = new GregorianCalendar(2012, Calendar.FEBRUARY, 12);
Calendar uCal = new UmmalquraCalendar();
uCal.setTime(gCal.getTime());

uCal.get(Calendar.YEAR);         // 1433
uCal.get(Calendar.MONTH);        // 2
uCal.get(Calendar.DAY_OF_MONTH); // 20

uCal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH);
```

#### From Ummalqura to Gregorian

```java
Calendar uCal = new UmmalquraCalendar(1433, UmmalquraCalendar.RABI_AWWAL, 15);
GregorianCalendar gCal = new GregorianCalendar();
gCal.setTime(uCal.getTime());

gCal.get(Calendar.YEAR);         // 2012
gCal.get(Calendar.MONTH);        // 1
gCal.get(Calendar.DAY_OF_MONTH); // 7
```

### Formatting

You can use `java.text.SimpleDateFormat` to format Ummalqura date.

#### Format using English locale

```java
Calendar uCal = new UmmalquraCalendar(1433, UmmalquraCalendar.RABI_AWWAL, 8, 20, 45, 10);
SimpleDateFormat dateFormat = new SimpleDateFormat("", Locale.ENGLISH);
dateFormat.setCalendar(uCal);

dateFormat.applyPattern("d");
dateFormat.format(uCal.getTime()); // 8

dateFormat.applyPattern("dd");
dateFormat.format(uCal.getTime()); // 08

dateFormat.applyPattern("E");
dateFormat.format(uCal.getTime()); // Tue

dateFormat.applyPattern("EEEE");
dateFormat.format(uCal.getTime()); // Tuesday

dateFormat.applyPattern("M");
dateFormat.format(uCal.getTime()); // 3

dateFormat.applyPattern("MM");
dateFormat.format(uCal.getTime()); // 03

dateFormat.applyPattern("MMM");
dateFormat.format(uCal.getTime()); // Rab-I

dateFormat.applyPattern("MMMM");
dateFormat.format(uCal.getTime()); // Rabi' al-Awwal

dateFormat.applyPattern("y");
dateFormat.format(uCal.getTime()); // 1433

dateFormat.applyPattern("yy");
dateFormat.format(uCal.getTime()); // 33

dateFormat.applyPattern("EEEE d MMMM, y");
dateFormat.format(uCal.getTime()); // Tuesday 8 Rabi' al-Awwal, 1433

dateFormat.applyPattern("y/MM/dd");
dateFormat.format(uCal.getTime()); // 1433/03/08

dateFormat.applyPattern("y/MM/dd hh:mm a");
dateFormat.format(uCal.getTime()); // 1433/03/08 08:45 PM
```

#### Format using Arabic locale

```java
Calendar uCal = new UmmalquraCalendar(1433, UmmalquraCalendar.RABI_AWWAL, 8, 20, 45, 10);
SimpleDateFormat dateFormat = new SimpleDateFormat("", new Locale("ar"));
dateFormat.setCalendar(uCal);

dateFormat.applyPattern("E");
dateFormat.format(uCal.getTime()); // ث

dateFormat.applyPattern("EEEE");
dateFormat.format(uCal.getTime()); // الثلاثاء

dateFormat.applyPattern("MMM");
dateFormat.format(uCal.getTime()); // ربيع 1

dateFormat.applyPattern("MMMM");
dateFormat.format(uCal.getTime()); // ربيع الأول

dateFormat.applyPattern("EEEE d MMMM, y");
dateFormat.format(uCal.getTime()); // الثلاثاء 8 ربيع الأول, 1433
```

### Parsing

You can use [`java.text.SimpleDateFormat`](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html) to parse Ummalqura date.

#### Parse using English locale

```java
Locale en = Locale.ENGLISH;
UmmalquraCalendar uCal = new UmmalquraCalendar(en);

SimpleDateFormat dateFormat = new SimpleDateFormat("", en);
dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
dateFormat.setCalendar(uCal);
uCal.set(Calendar.YEAR, 1420);                  // Used to properly format 'yy' pattern
dateFormat.set2DigitYearStart(uCal.getTime());  // Used to properly format 'yy' pattern

Calendar cal = new UmmalquraCalendar(en);

dateFormat.applyPattern("d/M/y");
cal.setTime(dateFormat.parse("4/7/1435"));
cal.get(Calendar.YEAR);                                      // 1435
cal.getDisplayName(Calendar.MONTH, Calendar.LONG, en);       // Rajab
cal.get(Calendar.DAY_OF_MONTH);                              // 4

dateFormat.applyPattern("MMMM d, y");
cal.setTime(dateFormat.parse("Jumada al-Ula 10, 1436"));
cal.get(Calendar.YEAR);                                      // 1436
cal.getDisplayName(Calendar.MONTH, Calendar.LONG, en);       // Jumada al-Ula
cal.get(Calendar.DAY_OF_MONTH);                              // 10

dateFormat.applyPattern("EEEE, MMMM dd, y");
cal.setTime(dateFormat.parse("Saturday, Sha'ban 07, 1434"));
cal.get(Calendar.YEAR);                                      // 1434
cal.getDisplayName(Calendar.MONTH, Calendar.LONG, en);       // Sha'ban
cal.get(Calendar.DAY_OF_MONTH);                              // 7

dateFormat.applyPattern("EEEE, MMMM d, yy hh:mm:ss");
cal.setTime(dateFormat.parse("Saturday, Jumada al-Ula 23, 36 12:19:44"));
cal.get(Calendar.YEAR);                                      // 1436
cal.getDisplayName(Calendar.MONTH, Calendar.LONG, en);       // Jumada al-Ula
cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, en); // Saturday
cal.get(Calendar.DAY_OF_MONTH);                              // 23
```

#### Parse using Arabic locale

```java
Locale ar = new Locale("ar");
UmmalquraCalendar uCal = new UmmalquraCalendar(ar);

SimpleDateFormat dateFormat = new SimpleDateFormat("", ar);
dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
dateFormat.setCalendar(uCal);
// Used to properly format 'yy' pattern for dates in 14th century
uCal.set(Calendar.YEAR, 1400);
dateFormat.set2DigitYearStart(uCal.getTime());

Calendar cal = new UmmalquraCalendar(ar);

dateFormat.applyPattern("d/M/y");
cal.setTime(dateFormat.parse("4/7/1435");
cal.get(Calendar.YEAR);                                      // 1435
cal.getDisplayName(Calendar.MONTH, Calendar.LONG, ar);       // رجب
cal.get(Calendar.DAY_OF_MONTH);                              // 4

dateFormat.applyPattern("MMMM d, y");
cal.setTime(dateFormat.parse("جمادى الأولى 10, 1436"));
cal.get(Calendar.YEAR);                                      // 1436
cal.getDisplayName(Calendar.MONTH, Calendar.LONG, ar);       // جمادى الأولى
cal.get(Calendar.DAY_OF_MONTH);                              // 10

dateFormat.applyPattern("EEEE, MMMM dd, y");
cal.setTime(dateFormat.parse("السبت, شعبان 07, 1434"));
cal.get(Calendar.YEAR);                                      // 1434
cal.getDisplayName(Calendar.MONTH, Calendar.LONG, ar);       // شعبان
cal.get(Calendar.DAY_OF_MONTH);                              // 7

dateFormat.applyPattern("EEEE, MMMM d, yy hh:mm:ss");
cal.setTime(dateFormat.parse("السبت, جمادى الأولى 23, 36 12:19:44"));
cal.get(Calendar.YEAR);                                      // 1436
cal.getDisplayName(Calendar.MONTH, Calendar.LONG, ar);       // جمادى الأولى
cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, ar); // السبت
cal.get(Calendar.DAY_OF_MONTH);                              // 23
```

## Localization: Add support for additional locales
Currently, `ummalqura-calendar` supports the following locales: Arabic, English and French.
You can add another locale by creating properties file with the following aspects:
- It should be named `UmmalquraFormatData_[LOCALE].properties`, where `[LOCALE]` should be a language tag. e.g. `tr`, `zh_CN`
- It should be placed in `com.github.msarhan.ummalqura.calendar.text` package and add it to the classpath.

As an example, French locale has been added as a [properties file](src/main/resources/com/github/msarhan/ummalqura/calendar/text/UmmalquraFormatData_fr.properties).

Please note that if properties file contains special characters that cannot be
represented in **ISO-8859-1** encoding; it must be encoded by unicode escapes in the form `\uXXXX`.
