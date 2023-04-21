package org.example.jdbccrud;
import java.sql.*;

public class JdbcCrud {
    private static final String url = "jdbc:postgresql://localhost:5432/contact";
    private static final String user = "postgres";
    private static final String password = "java";

    /**
     * adding contact in the database
     *
     * @param firstName contact's first name
     * @param lastName  contact's last name
     * @param phone     contact's phone number
     */
    public static void addContact(String firstName, String lastName, String phone) {
        Connection con=null;
        PreparedStatement st=null;
        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.prepareStatement("insert into contacts(first_name,last_name,phone)values (?,?,?)");
            st.setString(1, firstName);
            st.setString(2, lastName);
            st.setString(3, phone);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {try {
            if(st!=null) {
                st.close();
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * display all contacts
     */
    public static void displayAllContact() {
        Connection con=null;
        Statement st =null;
        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM contacts");
            while (rs.next()) {
                System.out.print(rs.getString("id") + "\t");
                System.out.print(rs.getString("first_name") + "\t");
                System.out.print(rs.getString("last_name") + "\t");
                System.out.print(rs.getString("phone") + "\t");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(st!=null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * deleting contact by phone number
     * @param phoneNumber contact phone number
     */
    public static void deleteContact(String phoneNumber) {
        Connection con=null;
        PreparedStatement st=null;
        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.prepareStatement("delete from contacts where phone=?");
            st.setString(1,phoneNumber);
            int r=st.executeUpdate();
            if(r>0) {
                System.out.println("Contact with phone number " + phoneNumber + " deleted successfully");
            }else {
                System.out.println("Contact not found");
            }
            con.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(st!=null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * update existing contact
     * @param oldNumber old number
     * @param firstName new first name
     * @param lastName new last nam
     * @param newNumber new number
     */
    public static void updateContact(String oldNumber,String firstName,String lastName,String newNumber){
        try{
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement st = con.prepareStatement("update contacts set first_name=?,last_name=?,phone=? where phone=?");
            st.setString(1,firstName);
            st.setString(2,lastName);
            st.setString(3,newNumber);
            st.setString(4,oldNumber);
            st.execute();
            con.close();
            st.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        addContact("Sergey", "Manucharyan", "041313300");
//        deleteContact("041313300");
//        updateContact("041313300","Sergey","Manucharyan","011111111");
        displayAllContact();
    }
}