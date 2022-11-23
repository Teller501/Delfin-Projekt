import java.util.ArrayList;

public class Controller {
    Database database = new Database();

    public void registerMember(String name, int age, boolean isActive){
        database.registerMember(name, age, isActive);
    }

    public ArrayList<Member> getMembers(){
        return database.getMembers();
    }

    public ArrayList<Member> searchForMember(String searchTerm){
        return database.searchForMember(searchTerm);
    }
}
