public class Controller {
    Database database = new Database();

    public void registerMember(String firstName, String lastName, int age, boolean isActive){
        database.registerMember(firstName, lastName, age, isActive);
    }
}
