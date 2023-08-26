package org.example.helper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.checkerframework.checker.index.qual.Positive;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Helper {
    public static int getFibonacci(int n) {
        if (n <= 2) return n-1;
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

    public static void createCSV(String fileName, List<String> headers, List<Map<String, String>> data) {
        CSVFormat csvFormat = CSVFormat.Builder.create().
                setAutoFlush(true).
                setRecordSeparator("\n").
                setHeader("Дата-времяТранзакции", "Сумма", "ТипТранзакции").
                build();

        try (Writer writer = new FileWriter(fileName);
             CSVPrinter csvRecord = new CSVPrinter(writer, csvFormat)) {
            for (Map<String, String> row : data) {
                csvRecord.printRecord(row.values());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
