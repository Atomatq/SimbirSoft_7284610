package org.example.helper;

import io.qameta.allure.Attachment;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class AllureReport {

    @Attachment(value = "Transactions_{0}", type = "text/csv", fileExtension = ".csv")
    public static byte[] attachCSV(String fileName, List<Map<String, String>> data, String ...headers) {
        byte[] csvFile;
        CSVFormat csvFormat = CSVFormat.Builder.create().
                setAutoFlush(true).
                setRecordSeparator("\n").
                setHeader(headers).
                build();

        try (CSVPrinter csvRecord = new CSVPrinter(new FileWriter(fileName), csvFormat)) {
            for (Map<String, String> row : data) {
                csvRecord.printRecord(row.values());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            File file = new File(fileName);
            csvFile = Files.readAllBytes(file.toPath());
            file.delete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return csvFile;
    }
}
