import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void calculateAge(){
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.parse("29-11-2002", DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        int expected = 19;

        int actual = (int) ChronoUnit.YEARS.between(birthday,today);

        assertEquals(expected,actual);
    }

}