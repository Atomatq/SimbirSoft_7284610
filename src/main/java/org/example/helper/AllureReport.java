package org.example.helper;

import io.qameta.allure.Allure;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AllureReport {
    public static void attachFile(String filePath, String MIMEType, String fileExtension) {
        try {
            Allure.addAttachment(filePath, MIMEType, new FileInputStream(filePath), fileExtension);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
