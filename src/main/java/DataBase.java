
import model.Grant;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DataBase {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static void connection() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:GrantsDB.db3");
            System.out.println("Data Base is connected!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void createDB() {
        try {
            statmt = conn.createStatement();
            statmt.execute("CREATE TABLE IF NOT EXISTS 'Grants' " +
                    "('company' TEXT, " +
                    "'street' TEXT, " +
                    "'quantity' FLOAT, " +
                    "'year' INTEGER, " +
                    "'business_type' TEXT, " +
                    "'work_places' INTEGER)");
            System.out.println("Table has been created or already exists");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeDB(ArrayList<Grant> grants) {
        for (Grant grant : grants) {
            String companyName = grant.getCompany();
            String streetName = grant.getStreet();
            String businessTypeName = grant.getBusinessType();
            companyName = companyName.replace("'", "''");
            streetName = streetName.replace("'", "''");
            businessTypeName = businessTypeName.replace("'", "''");

            String query = String.format(Locale.ENGLISH, "INSERT INTO Grants VALUES ('%s', '%s', %f, %d, '%s', %d);",
                    companyName, streetName, grant.getQuantity(),
                    Integer.parseInt(grant.getFiscalYear().toString()),
                    businessTypeName, grant.getWorkPlaces());
            System.out.println(query);
            try {
                statmt.execute(query);
                System.out.println("Table is filled");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void GraphAveragePlaces() {
        Map<Integer, Double> data = new HashMap<>();
        try {
            statmt = conn.createStatement();
            resSet = statmt.executeQuery("SELECT year, AVG(work_places) as 'Average place quantity' " +
                    "FROM Grants " +
                    "GROUP BY year;");
            while (resSet.next()) {
                int year = resSet.getInt("year");
                double average = resSet.getDouble("Average place quantity");
                data.put(year, average);
            }
            Charts.showResults(data);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String AverageGrant() {
        try {
            statmt = conn.createStatement();
            resSet = statmt.executeQuery("SELECT ROUND(AVG(quantity), 2) AS 'Average Grant' " +
                    "FROM Grants " +
                    "WHERE business_type = 'Salon/Barbershop';");
            String companyRes = resSet.getString("Average Grant");
            return companyRes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String MostWorkPlaces() {
        try {
            statmt = conn.createStatement();
            resSet = statmt.executeQuery("SELECT business_type, work_places " +
                    "FROM Grants " +
                    "WHERE quantity <= 55000 " +
                    "\tAND work_places = (SELECT MAX(work_places) " +
                    "\t\tFROM Grants " +
                    "\t\tWHERE quantity <= 55000);" );
            String result = resSet.getString("business_type");
            String result1 = resSet.getString("work_places");
            return (result + ": " + result1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
