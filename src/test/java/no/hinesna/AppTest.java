package no.hinesna;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{


    public void testCreatePerson(){
        // LAGRE PERSON
        Person personTest = new Person("Ola", "Nordmann");
        PersonDAO dao = new PersonDAO();
        dao.savePerson(personTest);

        // HENT PERSON
        Person personFraDB = dao.getPerson("Ola", "Nordmann");

        assertEquals(personTest.getFornavn(), personFraDB.getFornavn());
        assertEquals(personTest.getEtternavn(), personFraDB.getEtternavn());
    }

    public void testListPersons(){
        PersonDAO dao = new PersonDAO();
        List<Person> personer = dao.getAll();
        assertNotSame(0, personer.size());
    }
}
