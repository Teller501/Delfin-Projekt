import java.util.ArrayList;

public class Database {
    private ArrayList<Member> members = new ArrayList<>();

    public Member registerMember(String name, int age, boolean isActive){
        Member member = new Member(name, age, isActive);
        members.add(member);

        return member;
    }
}
