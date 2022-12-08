import application.Database;
import member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.Team;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    private Database database;
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @BeforeEach
    public void setup() {
        database = new Database();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    @Test
    void registerOneMember() {
        //arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ArrayList<Member> results = new ArrayList<>();

        Member m = database.registerMember("Michael Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);
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
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), false,61123452);
        //arrange
        ArrayList<Member> results = database.searchForMember("Michael");
        int expected = 1;

        //act
        int actual = results.size();

        //assert
        assertEquals(expected,actual);

    }

    @Test
    void deleteMember() {
        //arrange
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), false,61123452);
        database.registerMember("Bjarne Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);
        database.registerMember("Jobbe Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);
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
    void getTotalContribution(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2002",df), true,61123452);
        database.registerMember("Bjarne Phelps", LocalDate.parse("18-08-2002",df),LocalDate.parse("18-08-2002",df), true, 61123452);
        database.registerMember("Jobbe Phelps", LocalDate.parse("18-08-1912",df),LocalDate.parse("18-08-2002",df), true, 61123452);

        int expected = 3800;
        int actual = database.calculateTotalContribution();

        assertEquals(expected,actual);
    }

    @Test
    void getOneInArrear(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2002",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2022",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2022",df), true,61123452);

        int expected = 1;
        int actual = database.getMembersInArrear().size();

        assertEquals(expected,actual);
    }

    @Test
    void getMultipleInArrear(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2002",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2002",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2002",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2002",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2022",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2022",df), true,61123452);

        int expected = 4;
        int actual = database.getMembersInArrear().size();

        assertEquals(expected,actual);
    }

    @Test
    void getZeroInArrear(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2022",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2022",df), true,61123452);
        database.registerMember("Michael Phelps", LocalDate.parse("18-08-2012",df),LocalDate.parse("18-08-2022",df), true,61123452);

        int expected = 0;
        int actual = database.getMembersInArrear().size();

        assertEquals(expected,actual);
    }

    @Test
    void viewTeams(){
        Team team = new Team();
        Team team2 = new Team();
        Team team3 = new Team();
        Team team4 = new Team();
        Team team5 = new Team();
        Team team6 = new Team();
        Team team7 = new Team();
        Team team8 = new Team();

        ArrayList<Team> teams = new ArrayList<>();
        teams.add(team);
        teams.add(team2);
        teams.add(team3);
        teams.add(team4);
        teams.add(team5);
        teams.add(team6);
        teams.add(team7);
        teams.add(team8);


        int expected = 8;
        int actual = teams.size();

        assertEquals(expected,actual);
    }

}