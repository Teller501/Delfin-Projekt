public class Controller {
    Database database = new Database();

    public void registerMember(String name, int age, boolean isActive){
        database.registerMember(name, age, isActive);
    }
}
