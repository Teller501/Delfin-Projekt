public class Member {

    private String name;
    private int age;
    private boolean isActive;
    private int phoneNumber;


    public Member(String name, int age, int phoneNumber, boolean isActive){
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }

    public Member(){
        // Empty for loading data
    }

    // Getters for attributes


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    // Setters for attributes
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
