package com.github.msarhan.ummalqura.calendar;

/**
 * @author Mouaffak A. Sarhan
 */
public class DateTimeException extends RuntimeException {

    public DateTimeException(String message) {
        super(message);
    }

    public DateTimeException(String message, Exception ex) {
        super(message, ex);
    }
}
