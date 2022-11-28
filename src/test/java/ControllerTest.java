import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setup() {
        controller = new Controller();
    }

    @Test
    void registerOneMember() {
        //arrange
        controller.registerMember("Anders Teller", 20, true, 61123452);

        int expected = 1;

        //act
        int actual = controller.getMembers().size();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void registerMultipleMembers() {
        //arrange
        controller.registerMember("Anders Teller", 20, true,61123452);
        controller.registerMember("Nicolai Andersson", 21, true, 61123452);
        controller.registerMember("Victor Hanert", 22, true, 61123452);
        controller.registerMember("Omar Kayed", 22, true, 61123452);

        int expected = 4;

        //act
        int actual = controller.getMembers().size();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void registerZeroMembers() {
        //arrange
        int expected = 0;

        //act
        int actual = controller.getMembers().size();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void searchOneMember() {
        //arrange
        controller.registerMember("Michael Phelps", 37, false, 61123452);
        ArrayList<Member> results = controller.searchForMember("Michael");
        int expected = 1;

        //act
        int actual = results.size();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void searchMultipleMembers() {
        //arrange
        controller.registerMember("Michael Phelps", 37, false, 61123452);
        controller.registerMember("Bjarne Phelps", 69, true, 61123452);
        controller.registerMember("Jobbe Phelps", 22, true, 61123452);
        ArrayList<Member> results = controller.searchForMember("Phelps");
        int expected = 3;

        //act
        int actual = results.size();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void deleteMember() {
        //arrange
        controller.registerMember("Michael Phelps", 37, false, 61123452);
        controller.registerMember("Bjarne Phelps", 69, true, 61123452);
        controller.registerMember("Jobbe Phelps", 22, true, 61123452);
        ArrayList<Member> results = controller.getMembers();
        Member member = results.get(0);
        int expectedSize = results.size() - 1;

        //act
        boolean actualResult = controller.database.deleteMember(member);
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult);
        ArrayList<Member> resultsAfterDelete = controller.getMembers();
        int actualSize = resultsAfterDelete.size();

        //assert
        assertEquals(expectedSize, actualSize);
    }
}