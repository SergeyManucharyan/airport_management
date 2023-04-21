package org.example.airportmanagementsystem.service;

import org.example.airportmanagementsystem.connection.Connect;
import org.example.airportmanagementsystem.entity.Company;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import static org.junit.Assert.*;

public class TestAirportManagement {
    @Test
    public void testConnection() throws SQLException {
        Connection con = Connect.getConnection();
        assertNotNull(con);
        con.close();
    }
    @Test
    public void testInsertCompany() throws SQLException {
        Company company = new Company("NAME",new Date(2020-1900,2,2));
        CompanyService cs = new CompanyService();
        int length=cs.getAll().size();
        cs.saveCompany(company);
        int newLength=cs.getAll().size();
        assertEquals(length+1,newLength);
    }
}

