package org.example.airportmanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertDataFromFileToDB {
    private static final String url = "jdbc:postgresql://localhost:5432/Airport_Management";
    private static final String user = "postgres";
    private static final String pass = "";
    Connection con;
    Statement st;

    void insertCompanies(File file) throws FileNotFoundException, SQLException {
        try {
            con = DriverManager.getConnection(url, user, pass);
            st = con.createStatement();
            Scanner scanner = new Scanner(file);
            String[] query;
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNext()) {
                sb.append("insert into companies(name, found_date) values (");
                query = scanner.nextLine().split(",");
                sb.append("'").append(query[0]).append("','").append(query[1]).append("');");
                st.executeUpdate(sb.toString());
                sb.setLength(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            st.close();
            con.close();
        }
    }

    void insertPassengers(File file) throws FileNotFoundException, SQLException {
        try {
            con = DriverManager.getConnection(url, user, pass);
            st = con.createStatement();
            Scanner scanner = new Scanner(file);
            String[] query;
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNext()) {
                sb.append("insert into passengers(name,phone,country,city) values (");
                query = scanner.nextLine().split(",");
                sb.append("'").append(query[0]).append("','").append(query[1]).append("','")
                        .append(query[2]).append("','").append(query[3]).append("');");
                st.executeUpdate(sb.toString());
                sb.setLength(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            st.close();
            con.close();
        }
    }

    void insertTrip(File file) throws FileNotFoundException, SQLException {
        try {
            con = DriverManager.getConnection(url, user, pass);
            st = con.createStatement();
            Scanner scanner = new Scanner(file);
            String[] query;
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNext()) {
                sb.append("insert into trip(trip_id,   company_id,  airplane,   town_from,  town_to,  time_out,  time_in) values (");
                query = scanner.nextLine().split(",");
                sb.append(query[0]).append(",").append(query[1]).append(",'").append(query[2])
                        .append("','").append(query[3]).append("','").append(query[4]).append("','")
                        .append(query[5]).append("','").append(query[6]).append("');");
                st.executeUpdate(sb.toString());
                sb.setLength(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            st.close();
            con.close();
        }
    }

    void insertPassInTrip(File file) throws FileNotFoundException, SQLException {
        try {
            con = DriverManager.getConnection(url, user, pass);
            st = con.createStatement();
            Scanner scanner = new Scanner(file);
            String[] query;
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNext()) {
                sb.append("insert into pass_in_trip(trip_id,   psg_id,  date,   place) values (");
                query = scanner.nextLine().split(",");
                sb.append(query[0]).append(",").append(query[1]).append(",'").append(query[2])
                        .append("','").append(query[3]).append("');");
                st.executeUpdate(sb.toString());
                sb.setLength(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            st.close();
            con.close();
        }
    }
}
