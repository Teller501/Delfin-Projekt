import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    private Database database;

    @BeforeEach
    public void setup() {

        database = new Database();
        database.registerMember("Anders Teller", 20, true);
        database.registerMember("Nicolai Andersson", 21, true);
        database.registerMember("Victor Hanert", 22, true);
        database.registerMember("Omar Kayed", 22, true);
    }

    @Test
    void registerOneMember() {
        //arrange
        ArrayList<Member> results = new ArrayList<>();

        Member m = database.registerMember("Michael Phelps", 37, true);
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
        database.registerMember("Michael Phelps", 37, false);
        database.registerMember("Bjarne Phelps", 69, true);
        database.registerMember("Jobbe Phelps", 22, true);
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