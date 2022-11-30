import java.util.ArrayList;

public class Team {
    private String name;
//  private Trainer trainer; // TODO : Lav træner klasse

//  private ArrayList<Results> results = new ArrayList<>(); // TODO: Lav resultat klasse
    private ArrayList<Member> members = new ArrayList<>();

    public Team(String name){
        this.name = name;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void addMember(Member member){
        members.add(member);
    }
}
