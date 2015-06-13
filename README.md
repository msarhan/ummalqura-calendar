#ummalqura-calendar
Implements the Umm Al-Qura calendar system in Java language.

**Useful links:**

1. [http://www.staff.science.uu.nl/~gent0113/islam/ummalqura.htm](http://www.staff.science.uu.nl/~gent0113/islam/ummalqura.htm).
2. [http://www.ummulqura.org.sa/](http://www.ummulqura.org.sa).

##Installation
```xml
<dependency>
	<groupId>com.github.msarhan</groupId>
	<artifactId>ummalqura-calendar</artifactId>
	<version>1.1.5</version>
</dependency>
```

##Compilation

* Install [Maven 3](http://maven.apache.org/download.html)
* Check out this repo and: `mvn clean install`

##Usage

```java
// Suppose current gregorian data/time is: Fri Apr 03 18:12:38 AST 2015
UmmalquraCalendar cal = new UmmalquraCalendar();
System.out.println( cal.get(Calendar.YEAR) );         // 1436
System.out.println( cal.get(Calendar.MONTH) );        // 5 <=> Jumada al-Akhirah
System.out.println( cal.get(Calendar.DAY_OF_MONTH) ); // 14
```

###Using parametrized constructor(s)

```java
Calendar cal = new UmmalquraCalendar(1436, UmmalquraCalendar.SHAABAN, 11);
System.out.println(cal.getTime()); // Fri May 29 00:00:00 AST 2015
```
```java
Calendar cal = new UmmalquraCalendar(1436, UmmalquraCalendar.SHAABAN, 11, 18, 12, 38);
System.out.println(cal.getTime()); // Fri May 29 18:12:38 AST 2015
```

###Using Calendar::set methods

```java
Calendar cal = new UmmalquraCalendar();
cal.set(Calendar.YEAR, 1436);
cal.set(Calendar.MONTH, UmmalquraCalendar.SHAABAN);
cal.set(Calendar.DAY_OF_MONTH, 11);

System.out.println(cal.getTime()); // Fri May 29 20:57:37 AST 2015
```

###Conversion
-------------
####From Gregorian to Ummalqura

```java
GregorianCalendar gCal = new GregorianCalendar(2012, Calendar.FEBRUARY, 12);
Calendar uCal = new UmmalquraCalendar();
uCal.setTime(gCal.getTime());

System.out.println(uCal.get(Calendar.YEAR));         // 1433
System.out.println(uCal.get(Calendar.MONTH));        // 2
System.out.println(uCal.get(Calendar.DAY_OF_MONTH)); // 20
```

####From Ummalqura to Gregorian

```java
Calendar uCal = new UmmalquraCalendar(1433, UmmalquraCalendar.RABI_AWWAL, 15);
GregorianCalendar gCal = new GregorianCalendar();
gCal.setTime(uCal.getTime());

System.out.println(gCal.get(Calendar.YEAR));         // 2012
System.out.println(gCal.get(Calendar.MONTH));        // 1
System.out.println(gCal.get(Calendar.DAY_OF_MONTH)); // 7
```

###Formatting
-------------
You can use `java.text.SimpleDateFormat` to format Ummalqura date.

####Format using English locale

```java
Calendar uCal = new UmmalquraCalendar(1433, UmmalquraCalendar.RABI_AWWAL, 8, 20, 45, 10);
SimpleDateFormat dateFormat = new SimpleDateFormat("", Locale.ENGLISH);
dateFormat.setCalendar(uCal);

dateFormat.applyPattern("d");
System.out.println(dateFormat.format(uCal.getTime())); // 8

dateFormat.applyPattern("dd");
System.out.println(dateFormat.format(uCal.getTime())); // 08

dateFormat.applyPattern("E");
System.out.println(dateFormat.format(uCal.getTime())); // Tue

dateFormat.applyPattern("EEEE");
System.out.println(dateFormat.format(uCal.getTime())); // Tuesday

dateFormat.applyPattern("M");
System.out.println(dateFormat.format(uCal.getTime())); // 3

dateFormat.applyPattern("MM");
System.out.println(dateFormat.format(uCal.getTime())); // 03

dateFormat.applyPattern("MMM");
System.out.println(dateFormat.format(uCal.getTime())); // Rab-I

dateFormat.applyPattern("MMMM");
System.out.println(dateFormat.format(uCal.getTime())); // Rabi' al-Awwal

dateFormat.applyPattern("y");
System.out.println(dateFormat.format(uCal.getTime())); // 1433

dateFormat.applyPattern("yy");
System.out.println(dateFormat.format(uCal.getTime())); // 33

dateFormat.applyPattern("EEEE d MMMM, y");
System.out.println(dateFormat.format(uCal.getTime())); // Tuesday 8 Rabi' al-Awwal, 1433

dateFormat.applyPattern("y/MM/dd");
System.out.println(dateFormat.format(uCal.getTime())); // 1433/03/08

dateFormat.applyPattern("y/MM/dd hh:mm a");
System.out.println(dateFormat.format(uCal.getTime())); // 1433/03/08 08:45 PM
```

####Format using Arabic locale

```java
Calendar uCal = new UmmalquraCalendar(1433, UmmalquraCalendar.RABI_AWWAL, 8, 20, 45, 10);
SimpleDateFormat dateFormat = new SimpleDateFormat("", new Locale("ar"));
dateFormat.setCalendar(uCal);

dateFormat.applyPattern("E");
System.out.println(dateFormat.format(uCal.getTime())); // ث

dateFormat.applyPattern("EEEE");
System.out.println(dateFormat.format(uCal.getTime())); // الثلاثاء

dateFormat.applyPattern("MMM");
System.out.println(dateFormat.format(uCal.getTime())); // ربيع 1

dateFormat.applyPattern("MMMM");
System.out.println(dateFormat.format(uCal.getTime())); // ربيع الأول

dateFormat.applyPattern("EEEE d MMMM, y");
System.out.println(dateFormat.format(uCal.getTime())); // الثلاثاء 8 ربيع الأول, 1433
```

###Parsing
----------

You can use `java.text.SimpleDateFormat` to parse Ummalqura date.

####Parse using English locale

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
System.out.println(cal.get(Calendar.YEAR));                                      // 1435
System.out.println(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, en));       // Rajab
System.out.println(cal.get(Calendar.DAY_OF_MONTH));                              // 4

dateFormat.applyPattern("MMMM d, y");
cal.setTime(dateFormat.parse("Jumada al-Ula 10, 1436"));
System.out.println(cal.get(Calendar.YEAR));                                      // 1436
System.out.println(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, en));       // Jumada al-Ula
System.out.println(cal.get(Calendar.DAY_OF_MONTH));                              // 10

dateFormat.applyPattern("EEEE, MMMM dd, y");
cal.setTime(dateFormat.parse("Saturday, Sha'ban 07, 1434"));
System.out.println(cal.get(Calendar.YEAR));                                      // 1434
System.out.println(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, en));       // Sha'ban
System.out.println(cal.get(Calendar.DAY_OF_MONTH));                              // 7

dateFormat.applyPattern("EEEE, MMMM d, yy hh:mm:ss");
cal.setTime(dateFormat.parse("Saturday, Jumada al-Ula 23, 36 12:19:44"));
System.out.println(cal.get(Calendar.YEAR));                                      // 1436
System.out.println(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, en));       // Jumada al-Ula
System.out.println(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, en)); // Saturday
System.out.println(cal.get(Calendar.DAY_OF_MONTH));                              // 23
```

####Parse using Arabic locale

```java
Locale ar = new Locale("ar");
UmmalquraCalendar uCal = new UmmalquraCalendar(ar);

SimpleDateFormat dateFormat = new SimpleDateFormat("", ar);
dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
dateFormat.setCalendar(uCal);
uCal.set(Calendar.YEAR, 1420);                  // Used to properly format 'yy' pattern
dateFormat.set2DigitYearStart(uCal.getTime());  // Used to properly format 'yy' pattern

Calendar cal = new UmmalquraCalendar(ar);

dateFormat.applyPattern("d/M/y");
cal.setTime(dateFormat.parse("4/7/1435"));
System.out.println(cal.get(Calendar.YEAR));                                      // 1435
System.out.println(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, ar));       // رجب
System.out.println(cal.get(Calendar.DAY_OF_MONTH));                              // 4

dateFormat.applyPattern("MMMM d, y");
cal.setTime(dateFormat.parse("جمادى الأولى 10, 1436"));
System.out.println(cal.get(Calendar.YEAR));                                      // 1436
System.out.println(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, ar));       // جمادى الأولى
System.out.println(cal.get(Calendar.DAY_OF_MONTH));                              // 10

dateFormat.applyPattern("EEEE, MMMM dd, y");
cal.setTime(dateFormat.parse("السبت, شعبان 07, 1434"));
System.out.println(cal.get(Calendar.YEAR));                                      // 1434
System.out.println(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, ar));       // شعبان
System.out.println(cal.get(Calendar.DAY_OF_MONTH));                              // 7

dateFormat.applyPattern("EEEE, MMMM d, yy hh:mm:ss");
cal.setTime(dateFormat.parse("السبت, جمادى الأولى 23, 36 12:19:44"));
System.out.println(cal.get(Calendar.YEAR));                                      // 1436
System.out.println(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, ar));       // جمادى الأولى
System.out.println(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, ar)); // السبت
System.out.println(cal.get(Calendar.DAY_OF_MONTH));                              // 23
```
