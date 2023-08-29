package org.example.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Helper {
    public static int getFibonacci(int n) {
        if (n <= 2) return n - 1;
        else return getFibonacci(n-1) + getFibonacci(n-2);
    }

    public static int getCurrentDay() {
        return LocalDateTime.now().getDayOfMonth();
    }

    public static String convertDateTime(String dateTime, String fromFormat, String toFormat) {
        try {
            LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(fromFormat, Locale.ENGLISH));
            return DateTimeFormatter.ofPattern(toFormat, Locale.ENGLISH).format(parsedDateTime);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
