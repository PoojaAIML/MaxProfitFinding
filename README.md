
# Maximum Profit Finding

## Overview
I designed a Java program to analyze stock data stored in CSV files. It provides functionality to compute the maximum profit that can be earned by buying and selling a stock once within a given year.

## Requirements
- Java 8 or higher
- Maven

## Maven Project Structure
The project follows the standard Maven project structure

## Usage
1. Clone or download the MaxProfitFinding repository.
2. The Main.java file contains the main application code requested in the problem statement.
3. Navigate to the project directory.
4. Compile the project using Maven.
    ```bash
    mvn compile
    ```
5. Run the compiled program using the following command:
    ```bash
    mvn exec:java -Dexec.mainClass="org.example.Main"
    ```
6. Follow the prompts to input the stock name and year when prompted.

## Output
The program will output the following information:
- Buy date
- Buy price
- Sell date
- Sell price
- Profit




## Unit Tests

The MainTest.java file contains unit tests for the Main class. To run the unit tests, use Maven:

```bash
mvn test
```


