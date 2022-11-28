import java.time.LocalDate;

public class Member {

    private String name;
    private LocalDate birthday;
    private boolean isActive;
    private int phoneNumber;

    public Member(String name, LocalDate birthday, boolean isActive, int phoneNumber) {
        this.name = name;
        this.birthday = birthday;
        this.isActive = isActive;
        this.phoneNumber = phoneNumber;
    }

    public Member() {
        // Empty for loading data
    }

    // Getters for attributes


    public String getName() {
        return name;
    }

//    public int getAge() {
//        return age;
//    }

    public boolean isActive() {
        return isActive;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    // Setters for attributes
    public void setName(String name) {
        this.name = name;
    }


    public void setActive(boolean active) {
        isActive = active;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
