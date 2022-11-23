import java.util.ArrayList;

public class Database {
    private ArrayList<Member> members = new ArrayList<>();

    public Member registerMember(String firstName, String lastName, int age, boolean isActive){
        Member member = new Member(firstName, lastName, age, isActive);
        members.add(member);

        return member;
    }
}
