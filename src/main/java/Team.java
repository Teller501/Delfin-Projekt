import java.util.ArrayList;

public class Team {
    private String name;
    private String disciplin;
//  private Trainer trainer; // TODO : Lav træner klasse

//  private ArrayList<Result> trainingResults = new ArrayList<>(); // TODO: Lav resultat klasse
//  private ArrayList<Result> competitionResults = new ArrayList<>(); // TODO: Lav resultat klasse
    private ArrayList<Member> members = new ArrayList<>();

    public Team(String name, String disciplin){
        this.name = name;
        this.disciplin = disciplin;
    }

    public Team(){

    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void addMember(Member member){
        members.add(member);
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

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }
}
