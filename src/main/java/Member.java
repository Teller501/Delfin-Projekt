public class Member {

    private String firstName;
    private String lastName;
    private int age;
    private boolean isActive;

    public Member(String firstName, String lastName, int age, boolean isActive){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isActive = isActive;
    }

    public Member(){
        // Empty for loading data
    }

    // Getters for attributes
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return isActive;
    }


    // Setters for attributes
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
