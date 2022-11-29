import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void calculateAge(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.parse("29-11-2002",df);

        int expected = 20;

        int actual = (int) ChronoUnit.YEARS.between(birthday,today);

        assertEquals(expected,actual);
    }

    @Test
    void calculateJuniorSwimmerPrice(){
        //arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Member member = new Member("Anders Teller", LocalDate.parse("18-08-2010",df),LocalDate.parse("18-08-2002",df), true, 61123452);

        int expected = 1000;
        int result = member.getContributionPrice();

        assertEquals(expected,result);
    }

}