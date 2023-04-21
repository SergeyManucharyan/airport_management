package org.example.airportmanagementsystem.service;

import org.example.airportmanagementsystem.connection.Connect;
import org.example.airportmanagementsystem.entity.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompanyService {
    Connection con=Connect.getConnection();
    Statement st;
    PreparedStatement pst;

    public Company getById(int id) throws SQLException {
        Company company = new Company();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM companies WHERE id=" + id);
            while (rs.next()) {
                company.setCompanyId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                company.setFoundDate(rs.getDate("found_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        st.close();
    }
        return company;
    }

    public Set<Company> getAll() throws SQLException {
        con.beginRequest();
        Set<Company> companySet = new HashSet<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM companies");
            while (rs.next()) {
                companySet.add(new Company(rs.getInt("id"), rs.getString("name"),
                        rs.getDate("found_date")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            st.close();
        }
        return companySet;
    }

    public void saveCompany(Company company) throws SQLException {
        java.sql.Date sqlDate = new java.sql.Date(company.getFoundDate().getTime());
        try {
            pst = con.prepareStatement("insert into companies(name,found_date)values (?,?)");
            pst.setString(1, company.getName());
            pst.setDate(2, sqlDate);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(pst!=null){
                pst.close();
            }
//
        }
    }

    public void delete(int companyId) throws SQLException {
        try {
            pst = con.prepareStatement("SELECT COUNT(company_id) FROM trip WHERE company_id=?");
            pst.setInt(1, companyId);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int count = rs.getInt("count");
            if (count > 0) {
                System.out.println("First remove the row from the trip table");
            } else {
                pst = con.prepareStatement("DELETE FROM companies WHERE id=?");
                pst.setInt(1, companyId);
                int row = pst.executeUpdate();
                if (row > 0) {
                    System.out.println("Company deleted");
                } else {
                    System.out.println("Company does not exist");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
        }
    }

    public void update(Company company, int companyId) throws SQLException, RuntimeException {
        java.sql.Date sqlDate = new java.sql.Date(company.getFoundDate().getTime());
        try {
            pst = con.prepareStatement("UPDATE companies SET  name=?,found_date=? WHERE id=?");
            pst.setString(1, company.getName());
            pst.setDate(2, sqlDate);
            pst.setInt(3, companyId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
        }
    }

    public List<Company> get(int offset, int perPage, String sort) throws SQLException {
        List<Company> company = new ArrayList<>();
        try {
            pst = con.prepareStatement("SELECT * FROM companies ORDER BY "+sort+" LIMIT ? OFFSET ?");
            pst.setString(1, sort);
            pst.setInt(2, perPage);
            pst.setInt(3, offset);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                company.add(new Company(rs.getInt("id"), rs.getString("name"),
                        rs.getDate("found_date")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
        }
        return company;
    }
}
