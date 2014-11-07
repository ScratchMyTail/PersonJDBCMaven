package no.hinesna;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by christerhansen on 07.11.14.
 */
public class PersonDAO {
    private final String URL = "jdbc:mysql://localhost:3306/spring";
    private final String USER = "root";
    private final String PASS = "";

    Connection conn;
    Statement stmt;

    public void savePerson(Person person){
        try{
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate("insert into person(fornavn, etternavn) VALUES('"+person.getFornavn()+"', '"+person.getEtternavn()+"')");
            conn.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public Person getPerson(String fornavn, String etternavn){
        Person person = new Person();
        try{
            conn = DriverManager.getConnection(URL,USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from person where fornavn = '"+fornavn+"' and etternavn = '"+etternavn+"'");
            rs.next();
            person = new Person(rs.getString("fornavn"), rs.getString("etternavn"));
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        return person;
    }

    public List<Person> getAll(){
        List<Person> personList = new ArrayList<Person>();
        try{
            conn = DriverManager.getConnection(URL,USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from person");
            while(rs.next()){
                Person person = new Person(rs.getString("fornavn"), rs.getString("etternavn"));
                personList.add(person);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        return personList;
    }

}
