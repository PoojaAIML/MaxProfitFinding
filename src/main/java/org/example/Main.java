package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class StockData {
    String date;
    double open;
    double high;
    double low;
    double close;

    public StockData(String date, double open, double high, double low, double close) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }
}

class ResourceObject {
    String buyDate;
    Double buyPrice;
    String sellDate;
    Double sellPrice;
    double profit;

    public ResourceObject(String buyDate, Double buyPrice, String sellDate, Double sellPrice, double profit) {
        this.buyDate = buyDate;
        this.buyPrice = buyPrice;
        this.sellDate = sellDate;
        this.sellPrice = sellPrice;
        this.profit = profit;
    }
}


public class Main {
    public static ResourceObject maxProfit(String stockName, int year) throws Exception {
        String staticDirectoryPath = Main.class.getClassLoader().getResource("static").getFile();
        String csvFile = staticDirectoryPath + "/" + stockName + ".csv";
        String line;
        String cvsSplitBy = ",";
        List<StockData> stockDataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));) {

            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                String date = data[0];
                int dataYear = Integer.parseInt(date.split("-")[0]);
                if (dataYear == year) {
                    double open = Double.parseDouble(data[1]);
                    double high = Double.parseDouble(data[2]);
                    double low = Double.parseDouble(data[3]);
                    double close = Double.parseDouble(data[4]);
                    StockData stockData = new StockData(date, open, high, low, close);
                    stockDataList.add(stockData);
                }
            }
        }

        // Calculate maximum profit
        if(stockDataList.size() == 0) return null;
        if(stockDataList.size() == 1)   return new ResourceObject(null, null, null, null, 0);

        double maxProfit = 0;
        ResourceObject resourceObject = null;
        double lowestBuyPrice = stockDataList.get(0).low;
        String lowestBuyDate = stockDataList.get(0).date;

        for (int i = 1; i < stockDataList.size(); i++) {
            double sellPrice = stockDataList.get(i).high;
            double profit = sellPrice - lowestBuyPrice;
            if (profit > maxProfit) {
                maxProfit = profit;
                resourceObject = new ResourceObject(lowestBuyDate, lowestBuyPrice, stockDataList.get(i).date, sellPrice, profit);
            }
            double low = stockDataList.get(i).low;
            if(lowestBuyPrice > low){
                lowestBuyPrice = low;
                lowestBuyDate = stockDataList.get(i).date;
            }
        }

        return resourceObject;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Please provide the name of the stock: ");
            String stockName = sc.next();
            System.out.print("Please provide the year: ");
            int year = sc.nextInt();

            ResourceObject resourceObject = maxProfit(stockName, year);
            if (resourceObject != null) {
                if (resourceObject.buyDate != null) {
                    System.out.println("Buy date: " + resourceObject.buyDate);
                }
                if (resourceObject.buyPrice != null) {
                    System.out.println("Buy price: " + resourceObject.buyPrice);
                }
                if (resourceObject.sellDate != null) {
                    System.out.println("Sell date: " + resourceObject.sellDate);
                }
                if (resourceObject.sellPrice != null) {
                    System.out.println("Sell price: " + resourceObject.sellPrice);
                }
                System.out.println("Profit: " + resourceObject.profit);
            } else {
                System.out.println("No data found for the given year.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

