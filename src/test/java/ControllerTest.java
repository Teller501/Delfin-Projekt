import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        controller.registerMember("Anders Teller", 20, true);

        int expected = 1;

        //act
        int actual = controller.getMembers().size();

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void registerMultipleMembers(){
        //arrange
        controller.registerMember("Anders Teller", 20, true);
        controller.registerMember("Nicolai Andersson", 21, true);
        controller.registerMember("Victor Hanert", 22, true);
        controller.registerMember("Omar Kayed", 22, true);

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

}