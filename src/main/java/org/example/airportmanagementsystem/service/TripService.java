package org.example.airportmanagementsystem.service;

import org.example.airportmanagementsystem.connection.Connect;
import org.example.airportmanagementsystem.entity.Trip;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TripService {
    Connection con= Connect.getConnection();
    Statement st;
    PreparedStatement pst;

    public Trip getById(long id) throws SQLException {
        Trip trip = new Trip();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM trip WHERE trip_id=" + id);
            while (rs.next()) {
                trip.setTripId(rs.getInt("trip_id"));
                trip.setCompanyId(rs.getInt("company_id"));
                trip.setAirplane(rs.getString("airplane"));
                trip.setTownFrom(rs.getString("town_from"));
                trip.setTownTo(rs.getString("town_to"));
                trip.setTimeOut(rs.getTimestamp("time_out"));
                trip.setTimeIn(rs.getTimestamp("time_in"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            st.close();
        }
        return trip;
    }

    public Set<Trip> getAll() throws SQLException {
        Set<Trip> passengerSet = new HashSet<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM trip");
            while (rs.next()) {
                passengerSet.add(new Trip(rs.getInt("trip_id"), rs.getInt("company_id"), rs.getString("airplane"),
                        rs.getString("town_from"), rs.getString("town_to"), rs.getTimestamp("time_out"), rs.getTimestamp("time_in")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            st.close();
        }
        return passengerSet;
    }

    public void saveTrip(Trip trip) throws SQLException {
        try {
            pst = con.prepareStatement("insert into trip(trip_id,company_id,airplane," +
                    "town_from,town_to,time_out,time_in)values (?,?,?,?,?,?,?)");
            pst.setInt(1, trip.getTripId());
            pst.setInt(2, trip.getCompanyId());
            pst.setString(3, trip.getAirplane());
            pst.setString(4, trip.getTownFrom());
            pst.setString(5, trip.getTownTo());
            pst.setTimestamp(6, trip.getTimeOut());
            pst.setTimestamp(7, trip.getTimeIn());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
        }
    }

    public void update(Trip trip) throws SQLException, RuntimeException {
        try {
            pst = con.prepareStatement("UPDATE trip SET company_id=?,airplane=?,town_from=?," +
                    "town_to=?,time_out=?,time_in=? WHERE trip_id=" + trip.getTripId());
            pst.setInt(1, trip.getCompanyId());
            pst.setString(2, trip.getAirplane());
            pst.setString(3, trip.getTownFrom());
            pst.setString(4, trip.getTownTo());
            pst.setTimestamp(5, trip.getTimeOut());
            pst.setTimestamp(6, trip.getTimeIn());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
        }
    }

    public void delete(int tripId) throws SQLException {
        try {
            pst = con.prepareStatement("DELETE FROM trip WHERE trip_id=?");
            pst.setInt(1, tripId);
            int row = pst.executeUpdate();
            if (row > 0) {
                System.out.println("Trip deleted");
            } else {
                System.out.println("Trip does not exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
        }
    }

    public List<Trip> getTripsFrom(String city) throws SQLException {
        List<Trip> tripList = new ArrayList<>();
        try {
            pst = con.prepareStatement("SELECT * FROM trip WHERE town_from=?");
            pst.setString(1, city);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tripList.add(new Trip(rs.getInt("trip_id"), rs.getInt("company_id"), rs.getString("airplane"),
                        rs.getString("town_from"), rs.getString("town_to"), rs.getTimestamp("time_out")
                        , rs.getTimestamp("time_in")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
        }
        return tripList;
    }

    public List<Trip> getTripsTo(String city) throws SQLException {
        List<Trip> tripList = new ArrayList<>();
        try {
            pst = con.prepareStatement("SELECT * FROM trip WHERE town_to=?");
            pst.setString(1, city);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tripList.add(new Trip(rs.getInt("trip_id"), rs.getInt("company_id"), rs.getString("airplane"),
                        rs.getString("town_from"), rs.getString("town_to"), rs.getTimestamp("time_out")
                        , rs.getTimestamp("time_in")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
        }
        return tripList;
    }

    public List<Trip> get(int offset, int perPage, String sort) throws SQLException {
        List<Trip> tripList = new ArrayList<>();
        try {
            pst = con.prepareStatement("SELECT * FROM trip ORDER BY " + sort + " LIMIT ? OFFSET ?");
            pst.setInt(1, perPage);
            pst.setInt(2, offset);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tripList.add(new Trip(rs.getInt("trip_id"), rs.getInt("company_id"), rs.getString("airplane"),
                        rs.getString("town_from"), rs.getString("town_to"), rs.getTimestamp("time_out"), rs.getTimestamp("time_in")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
        }
        return tripList;
    }
}
