import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Filehandler {

    public void saveData(ArrayList<Member> members, ArrayList<Team> teams) throws FileNotFoundException {
        PrintStream output = new PrintStream(new File("data/members.csv"));
        PrintStream teamOutput = new PrintStream(new File("data/teams.csv"));

        for (Member member : members) {
            output.print(member.getName());
            output.print(";");
            output.print(member.getBirthday());
            output.print(";");
            output.print(member.getRegisterDate());
            output.print(";");
            output.print(member.getPhoneNumber());
            output.print(";");
            output.print(member.isActive());
            output.print(";");
            output.println();
        }

        for (Team team : teams){
            teamOutput.print(team.getName());
            teamOutput.print(";");
            teamOutput.print(team.getDisciplin());
            teamOutput.print(";");
            teamOutput.print(team.getMembers());
            teamOutput.print(";");
            teamOutput.println();
        }

        //Slutte med close:
        output.close();
        output.flush();
        teamOutput.close();
        teamOutput.flush();
    }

    public void loadData(ArrayList<Member> members, ArrayList<Team> teams) throws FileNotFoundException {
        // Clear list of names before load:
        members.clear();
        teams.clear();

        Scanner reader = new Scanner(new File("data/members.csv"));
        Scanner readerTeams = new Scanner(new File("data/teams.csv"));

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String teamLine = readerTeams.nextLine();

            Member dataObjekt = parseCSVLine(line);
            members.add(dataObjekt);

            Team dataTeamObjekt = parseTeamCSVLine(teamLine);
            teams.add(dataTeamObjekt);


            System.out.println(line);
            System.out.println(teamLine);
        }
    }

    private Member parseCSVLine(String line) {
        try {
            String[] parts = line.split(";");

            Member dataObjekt = new Member();
            dataObjekt.setName(parts[0]);
            dataObjekt.setBirthday(LocalDate.parse(parts[1]));
            dataObjekt.setRegisterDate(LocalDate.parse(parts[2]));
            dataObjekt.setPhoneNumber(Integer.parseInt(parts[3]));
            dataObjekt.setActive(Boolean.parseBoolean(parts[4]));
            return dataObjekt;
        } catch (NumberFormatException e) {
            System.out.println("Kan ikke loade data, fejl i input");
            return null;
        }

    }

    private Team parseTeamCSVLine(String line){
        try{
            String[] parts = line.split(";");

            Team dataObjekt = new Team();
            dataObjekt.setName(parts[0]);
            dataObjekt.setDisciplin(parts[1]);
            dataObjekt.setMembers(dataObjekt.getMembers());
            return dataObjekt;

        }catch(NumberFormatException e ){
            System.out.println("Kan ikke loade data, fejl i input");
            return null;
        }
    }
}
