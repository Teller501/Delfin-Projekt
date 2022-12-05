import Data.Database;
import Member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    private Database database;

    @BeforeEach
    public void setup() {
        database = new Database();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    @Test
    void registerOneMember() {
        //arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ArrayList<Member> results = new ArrayList<>();

        Member m = database.registerMember("Michael Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);
        results.add(m);

        int expected = 1;

        //act
        int actual = results.size();

        //assert
        assertEquals(expected,actual);

    }

    @Test
    void getMembers() {
    }

    @Test
    void searchForMember() {
        //arrange
        ArrayList<Member> results = database.searchForMember("Victor");
        int expected = 1;

        //act
        int actual = results.size();

        //assert
        assertEquals(expected,actual);

    }

    @Test
    void deleteMember() {
        //arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), false,61123452);
        database.registerMember("Bjarne Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);
        database.registerMember("Jobbe Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);
        ArrayList<Member> results = database.getMembers();
        Member member = results.get(0);
        int expectedSize = results.size() - 1;

        //act
        boolean actualResult = database.deleteMember(member);
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult);
        ArrayList<Member> resultsAfterDelete = database.getMembers();
        int actualSize = resultsAfterDelete.size();

        //assert
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void getTotalContribution(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2002",df), true,61123452);
        database.registerMember("Bjarne Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);
        database.registerMember("Jobbe Phelps", LocalDate.parse("18-08-1912",df),LocalDate.parse("18-08-2002",df), true, 61123452);

        int expected = 3800;
        int actual = database.calculateTotalContribution();

        assertEquals(expected,actual);
    }

    @Test
    void getOneInArrear(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2002",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2022",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2022",df), true,61123452);

        int expected = 1;
        int actual = database.getMembersInArrear().size();

        assertEquals(expected,actual);
    }

    @Test
    void getMultipleInArrear(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2002",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2002",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2002",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2002",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2022",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2022",df), true,61123452);

        int expected = 4;
        int actual = database.getMembersInArrear().size();

        assertEquals(expected,actual);
    }

    @Test
    void getZeroInArrear(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2022",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2022",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2022",df), true,61123452);

        int expected = 0;
        int actual = database.getMembersInArrear().size();

        assertEquals(expected,actual);
    }

    @Test
    void viewTeams(){
        int expected = 8;
        int actual = database.getTeams().size();

        assertEquals(expected,actual);
    }

}