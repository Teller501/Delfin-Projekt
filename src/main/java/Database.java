import java.time.LocalDate;
import java.util.ArrayList;

public class Database {
    private ArrayList<Member> members = new ArrayList<>();
    private boolean changes = false;

    public Member registerMember(String name, LocalDate birthday, LocalDate registerDate , boolean isActive, int phoneNumber) {
        Member member = new Member(name, birthday, registerDate ,isActive, phoneNumber);
        members.add(member);

        changes = true;
        return member;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<Member> searchForMember(String searchTerm) {
        ArrayList<Member> searchResults = new ArrayList<>();

        for (Member member : members) {
            String name = member.getName().toLowerCase();
            if (name.contains(searchTerm.toLowerCase().trim())) {
                searchResults.add(member);
            }
        }

        return searchResults;
    }

    public boolean deleteMember(Member member) {

        getMembers().remove(member);
        boolean success = true;
        changes = true;
        return success;
    }

    public boolean isChanges() {
        return changes;
    }

    public void setChanges(boolean changes) {
        this.changes = changes;
    }
}
