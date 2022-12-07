package application;

import member.Member;
import team.Team;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Filehandler {

    public void saveData(ArrayList<Member> members, ArrayList<Team> teams) throws FileNotFoundException {
        PrintStream memberOutput = new PrintStream(new File("data/members.csv"));
        PrintStream teamOutput = new PrintStream(new File("data/teams.csv"));

        for (Member member : members) {
            memberOutput.print(member.getName());
            memberOutput.print(";");
            memberOutput.print(member.getBirthday());
            memberOutput.print(";");
            memberOutput.print(member.getRegisterDate());
            memberOutput.print(";");
            memberOutput.print(member.getPhoneNumber());
            memberOutput.print(";");
            memberOutput.print(member.isActive());
            memberOutput.print(";");
            memberOutput.print(member.getTeam());
            memberOutput.print(";");
            memberOutput.println();
        }

        for (Team team : teams){
            teamOutput.print(team.getName());
            teamOutput.print(";");
            teamOutput.print(team.getDisciplin());
            teamOutput.print(";");
            teamOutput.println();
        }

        //Slutte med close:
        memberOutput.close();
        memberOutput.flush();
        teamOutput.close();
        teamOutput.flush();
    }

    public void loadMemberData(ArrayList<Member> members) throws FileNotFoundException {
        // Clear list of data before load:
        members.clear();
        Scanner readerMember = new Scanner(new File("data/members.csv"));

        while (readerMember.hasNextLine()) {
            String memberLine = readerMember.nextLine();
            Member dataMemberObjekt = parseCSVLine(memberLine);
            members.add(dataMemberObjekt);
            System.out.println(memberLine);
        }
    }

    public void loadTeamData(ArrayList<Team> teams) throws FileNotFoundException {
        // Clear list of data before load:
        teams.clear();
        Scanner readerTeams = new Scanner(new File("data/teams.csv"));

        while (readerTeams.hasNextLine()) {
            String teamLine = readerTeams.nextLine();
            Team dataTeamObjekt = parseTeamCSVLine(teamLine);
            teams.add(dataTeamObjekt);
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
            dataObjekt.setTeam(parts[5]);
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
            return dataObjekt;

        }catch(NumberFormatException e ){
            System.out.println("Kan ikke loade data, fejl i input");
            return null;
        }
    }
}
