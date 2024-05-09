package org.example;

import static org.junit.Assert.*;
import org.example.ResourceObject;
import org.junit.Test;

import java.io.File;

public class MainTest{
    // Unit tests for maxProfit function

    // Test for max profit in 2024
    @Test
    public void testMaxProfit_2024() throws Exception {
        // Mock CSV file content for testing
        String csvContent = "Date,Open,High,Low,Close,Adj Close,Volume\n" +
                "2024-01-01,100,110,90,105,105,1000\n" +
                "2024-01-02,105,115,95,110,110,1200\n" +
                "2024-01-03,110,120,100,115,115,1500\n" +
                "2024-01-04,115,125,105,120,120,1800\n" +
                "2024-01-05,120,130,110,125,125,2000\n" +
                "2024-01-06,125,135,115,130,130,2200\n";

        // Create a temporary CSV file for testing
        String staticDirectoryPath = Main.class.getClassLoader().getResource("static").getFile();
        java.io.File tempFile = java.io.File.createTempFile("temp", ".csv", new File(staticDirectoryPath));
        java.io.PrintWriter writer = new java.io.PrintWriter(tempFile);
        writer.write(csvContent);
        writer.close();

        // Test for max profit in 2024
        ResourceObject result = Main.maxProfit(tempFile.getName().replace(".csv", ""), 2024);
        assertEquals("2024-01-01", result.buyDate);
        assertEquals(90.0, (double) result.buyPrice, (double) 0.0001);
        assertEquals("2024-01-06", result.sellDate);
        assertEquals(135.0, (double) result.sellPrice, (double) 0.0001);
        assertEquals(45.0, result.profit, (double) 0.0001);
    }

    // Test for no data found for a specific year
    @Test
    public void testMaxProfit_NoData() throws Exception {
        // Mock CSV file content for testing (empty)
        String csvContent = "";

        // Create a temporary CSV file for testing
        String staticDirectoryPath = Main.class.getClassLoader().getResource("static").getFile();
        java.io.File tempFile = java.io.File.createTempFile("temp", ".csv", new File(staticDirectoryPath));
        java.io.PrintWriter writer = new java.io.PrintWriter(tempFile);
        writer.write(csvContent);
        writer.close();

        // Test for no data found for 2023
        ResourceObject result = Main.maxProfit(tempFile.getName().replace(".csv", ""), 2023);
        assertNull(result);
    }

    // Test for continuously decreasing prices
    @Test
    public void testMaxProfit_DecreasingPrices() throws Exception {
        // Mock CSV file content for testing (prices decreasing every day)
        String csvContent = "Date,Open,High,Low,Close,Adj Close,Volume\n" +
                "2024-01-01,100,100,90,90,20,1000\n" +
                "2024-01-02,90,90,80,80,20,1200\n" +
                "2024-01-03,80,80,70,70,20,1500\n" +
                "2024-01-04,70,70,60,60,20,1800\n";

        // Create a temporary CSV file for testing
        String staticDirectoryPath = Main.class.getClassLoader().getResource("static").getFile();
        java.io.File tempFile = java.io.File.createTempFile("temp", ".csv", new File(staticDirectoryPath));
        java.io.PrintWriter writer = new java.io.PrintWriter(tempFile);
        writer.write(csvContent);
        writer.close();

        // Test for continuously decreasing prices in 2024
        ResourceObject result = Main.maxProfit(tempFile.getName().replace(".csv", ""), 2024);
        assertNull(result);
    }
}

