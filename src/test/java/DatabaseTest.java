import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    private Database database;

    @BeforeEach
    public void setup() {

        database = new Database();
        database.registerMember("Anders Teller", LocalDate.parse("18-08-2002"), true, 61123452);
        database.registerMember("Nicolai Andersson", LocalDate.parse("18-08-2002"), true, 61123452);
        database.registerMember("Victor Hanert", LocalDate.parse("18-08-2002"), true, 61123452);
        database.registerMember("Omar Kayed", LocalDate.parse("18-08-2002"), true, 61123452);
    }

    @Test
    void registerOneMember() {
        //arrange
        ArrayList<Member> results = new ArrayList<>();

        Member m = database.registerMember("Michael Phelps", LocalDate.parse("18-08-2002"), true, 61123452);
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
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2002"), false,61123452);
        database.registerMember("Bjarne Phelps", LocalDate.parse("18-08-2002"), true, 61123452);
        database.registerMember("Jobbe Phelps", LocalDate.parse("18-08-2002"), true, 61123452);
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
    void isChanges() {
    }

    @Test
    void setChanges() {
    }
}