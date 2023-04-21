package org.example.airportmanagementsystem.service;

import org.example.airportmanagementsystem.entity.Passenger;
import org.example.airportmanagementsystem.entity.Trip;
import org.example.airportmanagementsystem.connection.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassengerService {
    Connection con=Connect.getConnection();
    Statement st;
    PreparedStatement pst;

    public Passenger getById(int id) throws SQLException {
        Passenger passenger = new Passenger();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM passengers WHERE id=" + id);
            while (rs.next()) {
                passenger.setId(rs.getInt("id"));
                passenger.setName(rs.getString("name"));
                passenger.setCity(rs.getString("city"));
                passenger.setCountry(rs.getString("country"));
                passenger.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            st.close();
        }
        return passenger;
    }

    public Set<Passenger> getAll() throws SQLException {
        Set<Passenger> passengerSet = new HashSet<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM passengers");
            while (rs.next()) {
                passengerSet.add(new Passenger(rs.getString("name"), rs.getString("phone"),
                        rs.getString("country"), rs.getString("city"), rs.getInt("id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            st.close();
        }
        return passengerSet;
    }

    public void savePassenger(Passenger passenger) throws SQLException {
        try {
            pst = con.prepareStatement("insert into passengers(name,phone,country,city)values (?,?,?,?)");
            pst.setString(1, passenger.getName());
            pst.setString(2, passenger.getPhone());
            pst.setString(3, passenger.getCountry());
            pst.setString(4, passenger.getCity());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
        }
    }

    public void update(Passenger passenger, int psgId) throws SQLException, RuntimeException {
        try {
            pst = con.prepareStatement("UPDATE passengers SET  name=?,phone=?,country=?,city=? WHERE id=?");
            pst.setString(1, passenger.getName());
            pst.setString(2, passenger.getPhone());
            pst.setString(3, passenger.getCountry());
            pst.setString(4, passenger.getCity());
            pst.setInt(5, psgId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
        }
    }

    public void delete(int psgId) throws SQLException {
        try {
            pst = con.prepareStatement("SELECT COUNT(psg_id) FROM pass_in_trip WHERE psg_id=?");
            pst.setInt(1, psgId);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int count = rs.getInt("count");
            if (count > 0) {
                System.out.println("First remove the passenger from the pass_in_trip table");
            } else {
                pst = con.prepareStatement("DELETE FROM passengers WHERE id=?");
                pst.setInt(1, psgId);
                int row = pst.executeUpdate();
                if (row > 0) {
                    System.out.println("Passenger deleted");
                } else {
                    System.out.println("Passenger does not exist");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
        }
    }

    public List<Passenger> getPassengersOfTrip(int tripNumber) throws SQLException {
        List<Passenger> passengerList = new ArrayList<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT psg_id FROM pass_in_trip WHERE trip_id=" + tripNumber);
            while (rs.next()) {
                passengerList.add(getById(rs.getInt("psg_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            st.close();
        }
        return passengerList;
    }

    public void registerTrip(Trip trip, Passenger passenger, String place) throws SQLException {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try {
            pst = con.prepareStatement("insert into pass_in_trip(trip_id,psg_id,date,place)values (?,?,?,?)");
            pst.setInt(1, trip.getTripId());
            pst.setInt(2, passenger.getId());
            pst.setDate(3, sqlDate);
            pst.setString(4, place);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
        }

    }

    public void cancelRegistration(int tripId, int psgId) throws SQLException {
        try {
            pst = con.prepareStatement("DELETE FROM pass_in_trip WHERE trip_id=? AND psg_id=?");
            pst.setInt(1, tripId);
            pst.setInt(2, psgId);
            int row = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
        }
    }

    public List<Passenger> get(int offset, int perPage, String sort) throws SQLException {
        List<Passenger> passengers = new ArrayList<>();
        try {
            pst = con.prepareStatement("SELECT * FROM passengers ORDER BY " + sort + " LIMIT ? OFFSET ?");
            pst.setInt(1, perPage);
            pst.setInt(2, offset);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                passengers.add(new Passenger(rs.getString("name"), rs.getString("phone"),
                        rs.getString("country"), rs.getString("city"), rs.getInt("id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
        }
        return passengers;
    }
}
