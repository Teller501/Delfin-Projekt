import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setup(){
        controller = new Controller();
    }

    @Test
    void registerOneMember(){
        //arrange
        controller.registerMember("Anders Teller", 20, 61123452, true);

        int expected = 1;

        //act
        int actual = controller.getMembers().size();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void registerMultipleMembers(){
        //arrange
        controller.registerMember("Anders Teller", 20, 61123452, true);
        controller.registerMember("Nicolai Andersson", 21, 61123452, true);
        controller.registerMember("Victor Hanert", 22, 61123452, true);
        controller.registerMember("Omar Kayed", 22, 61123452, true);

        int expected = 4;

        //act
        int actual = controller.getMembers().size();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void registerZeroMembers(){
        //arrange
        int expected = 0;

        //act
        int actual = controller.getMembers().size();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void searchOneMember(){
        //arrange
        controller.registerMember("Michael Phelps", 37, 61123452, false);
        ArrayList<Member> results = controller.searchForMember("Michael");
        int expected = 1;

        //act
        int actual = results.size();

        //assert
        assertEquals(expected,actual);
    }

    @Test
    void searchMultipleMembers(){
        //arrange
        controller.registerMember("Michael Phelps", 37, 61123452, false);
        controller.registerMember("Bjarne Phelps", 69, 61123452, true);
        controller.registerMember("Jobbe Phelps", 22, 61123452, true);
        ArrayList<Member> results = controller.searchForMember("Phelps");
        int expected = 3;

        //act
        int actual = results.size();

        //assert
        assertEquals(expected,actual);
    }
}