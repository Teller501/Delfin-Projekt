package team;

import member.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Team {
    private String name;
    private String disciplin;
    private ArrayList<Result> trainingResults = new ArrayList<>();
    private ArrayList<Result> competitionResults = new ArrayList<>();
    private ArrayList<Member> teamMembers = new ArrayList<>();

    public Team(String name, String disciplin){
        this.name = name;
        this.disciplin = disciplin;
    }

    public Team(){

    }

    public ArrayList<Member> getTeamMembers() {
        return teamMembers;
    }

    public void addMember(Member member){
        teamMembers.add(member);
    }

    public String getName() {
        return name;
    }

    public String getDisciplin() {
        return disciplin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisciplin(String disciplin) {
        this.disciplin = disciplin;
    }

    public void setTeamMembers(ArrayList<Member> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public void addTrainingResult(Member member, LocalDate date, double time){
        Result result = new Result(member,date,time);
        trainingResults.add(result);
    }

    public void addCompetitionResult(Member member, LocalDate date, double time, String convention, int placement){
        Result result = new Result(member,date,time, convention, placement);
        competitionResults.add(result);
    }

    public ArrayList<Result> getCompetitionResults() {
        return competitionResults;
    }

    public ArrayList<Result> getTrainingResults() {
        return trainingResults;
    }
}
