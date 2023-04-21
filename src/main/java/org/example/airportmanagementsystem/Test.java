package org.example.airportmanagementsystem;

import org.example.airportmanagementsystem.connection.Connect;
import org.example.airportmanagementsystem.entity.Company;
import org.example.airportmanagementsystem.service.CompanyService;
import org.example.airportmanagementsystem.service.PassengerService;
import org.example.airportmanagementsystem.service.TripService;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws FileNotFoundException, SQLException, ParseException {
        String path="src/main/j" +
                "ava/org/example/airportmanagementsystem/resources";
        InsertDataFromFileToDB insert = new InsertDataFromFileToDB();
        File file;
        CompanyService cs = new CompanyService();
        PassengerService ps = new PassengerService();
        TripService ts=new TripService();
        System.out.println(ps.getById(5));
        System.out.println(ps.getById(7));
        Connect.getConnection().close();
//        List<Trip> tripList;
//        tripList=ts.get(0,10,"town_from");
//        for (Trip t:tripList) {
//            System.out.println(t);
//        }
//        List<Passenger> pas;
//        pas=ps.get(5,10,"name");
//        for (Passenger p:pas) {
//            System.out.println(p);
//        }
//        List<Company> companies;
//        companies=cs.get(5,10,"name");
//        for (Company c:companies) {
//            System.out.println(c);
//        }
        //        List<Trip> tripList=ts.getTripsTo("Singapore");
//        for (Trip t:tripList) {
//            System.out.println(t);
//        }
//        Timestamp timeOut= new Timestamp(System.currentTimeMillis());
//        Timestamp timeIn= new Timestamp(System.currentTimeMillis()+20000);
//        ts.delete(5000);
//        ts.update(new Trip(5000,23,"Boing","Moscow","Berlin",timeOut,timeIn));
//        System.out.println(ts.getById(7771));
//        Set<Trip> pas;
//        pas=ts.getAll();
//        for (Trip p:pas) {
//            System.out.println(p);
//        }
//        Company c = new Company("Line",new Date(2023-1900,5,23));
//        cs.saveCompany(c);
//        cs.update(c,1002);
//        cs.delete(5);
//        ps.update(new Passenger("Sergey","041313300","Armenia","Yerevan"),308);
//        Passenger passenger = new Passenger();
//        ps.cancelRegistration(1196,90);
//        Company c = new Company("ArmAir",new Date(2002-1900,0,2));
//        cs.saveCompany(c);
//        Set<Company> companies = cs.getAll();
//        for (Company c:companies) {
//            System.out.println(c);
//        }
//        ps.registerTrip(new Trip(1196),new Passenger(90),"3g");
//        System.out.println(time);
//        List<Passenger> p=ps.getPassengersOfTrip(7771);
//        for (Passenger pes:p) {
//            System.out.println(pes);
//        }
//        ps.update(new Passenger("Sergey","0413313300","Armenia","Yerevan")
//        ,new Passenger("Garik","055771773","Arm","Yerevan"));
//        System.out.println(ps.getById(1001));
//        System.out.println(passenger.getName()+" "+passenger.getPhone()+" "+passenger.getCity()+" "+passenger.getCountry());
//        Set<Passenger> pas;
//        pas=ps.getAll();
//        for (Passenger p:pas) {
//            System.out.println(p);
//        }
        /*
        the order of calling method is important
         */
        //1
//        file=new File(path+"companies.txt");
//        insert.insertCompanies(file);
        //2
//        file=new File(path+"passengers.txt");
//        insert.insertPassengers(file);
        //3
//        file=new File(path+"trip.txt");
//        insert.insertTrip(file);
        //4
//        file=new File(path+"pass_in_trip.txt");
//        insert.insertPassInTrip(file);

    }
}
