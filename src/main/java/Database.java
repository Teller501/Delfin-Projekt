import java.util.ArrayList;

public class Database {
    private ArrayList<Member> members = new ArrayList<>();

    public Member registerMember(String name, int age, boolean isActive){
        Member member = new Member(name, age, isActive);
        members.add(member);

        return member;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<Member> searchForMember(String searchTerm){
        ArrayList<Member> searchResults = new ArrayList<>();

        for (Member member : members){
            String name = member.getName().toLowerCase();
            if (name.contains(searchTerm.toLowerCase().trim())){
                searchResults.add(member);
            }
        }

        return searchResults;
    }
}
