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
        //Arrange
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
    }

    @Test
    void deleteMember() {
    }

    @Test
    void isChanges() {
    }

    @Test
    void setChanges() {
    }
}