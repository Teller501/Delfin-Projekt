import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ContributionTest {


    @Test
    void calculateJuniorSwimmerPrice(){
        //arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Member member = new Member("Anders Teller", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);


        int expected = 1000;
        int result = member.getContributionPrice();

        assertEquals(expected,result);
    }

    @Test
    void calculateSeniorSwimmerPrice(){
        //arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Member member = new Member("Anders Teller", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);


        int expected = 1600;
        int result = member.getContributionPrice();

        assertEquals(expected,result);
    }

    @Test
    void calculatePensionSwimmerPrice(){
        //arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Member member = new Member("Anders Teller", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);


        int expected = 1200;
        int result = member.getContributionPrice();

        assertEquals(expected,result);
    }

}