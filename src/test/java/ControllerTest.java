import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setup() {
        controller = new Controller();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    @Test
    void registerOneMember() {
        //arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        controller.registerMember("Anders Teller", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);

        int expected = 1;

        //act
        int actual = controller.getMembers().size();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void registerMultipleMembers() {
        //arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        controller.registerMember("Anders Teller", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true,61123452);
        controller.registerMember("Nicolai Andersson", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);
        controller.registerMember("Victor Hanert", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);
        controller.registerMember("Omar Kayed", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);

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
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        controller.registerMember("Michael Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), false, 61123452);
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
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        controller.registerMember("Michael Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), false, 61123452);
        controller.registerMember("Bjarne Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);
        controller.registerMember("Jobbe Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);
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
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        controller.registerMember("Michael Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), false, 61123452);
        controller.registerMember("Bjarne Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);
        controller.registerMember("Jobbe Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);
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