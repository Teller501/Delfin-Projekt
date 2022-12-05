import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    Database database = new Database();
    Filehandler fileHandler = new Filehandler();

    public void registerMember(String name, LocalDate birthday,LocalDate registerDate , boolean isActive, int phoneNumber) {
        database.registerMember(name, birthday, registerDate, isActive, phoneNumber);
    }

    public ArrayList<Member> getMembers() {
        return database.getMembers();
    }

    public ArrayList<Member> searchForMember(String searchTerm) {
        return database.searchForMember(searchTerm);
    }

    // Calls the save method from FileHandler Class
    public void saveData() {
        try {
            fileHandler.saveData(database.getMembers(), database.getTeams());
        } catch (FileNotFoundException e) { // if the file does not exist
            System.out.println("Filen findes ikke...");
        }
    }

    // Calls the load method from FileHandler Class
    public void loadData() {
        try {
            fileHandler.loadData(database.getMembers(), database.getTeams());
        } catch (FileNotFoundException e) {// if the file does not exist
            System.out.println("Filen findes ikke...");
        }
    }

    public void deleteMember(Member deleteMember) {
        database.deleteMember(deleteMember);
    }

    public boolean isChanges() {
        return database.isChanges();
    }

    public int getTotalContribution(){
        return database.calculateTotalContribution();
    }

    public ArrayList<Member> getMembersInArrear(){
        return database.getMembersInArrear();
    }

    public ArrayList<Team> getTeams(){
        return database.getTeams();
    }

    public void addMemberToTeam(Team teamChosen, Member memberChosen) {
        database.addMemberToTeam(teamChosen, memberChosen);
    }

 /*   public void addMemberToTeam(Team team, Member member){
        team.addMember(member);
        isChanges();
    }*/
}
