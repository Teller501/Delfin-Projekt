import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Member {

    private String name;
    private LocalDate birthday;
    private boolean active;
    private int phoneNumber;

    public Member(String name, LocalDate birthday, boolean active, int phoneNumber) {
        this.name = name;
        this.birthday = birthday;
        this.active = active;
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
        return active;
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
        this.active = active;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int calculateAge(){
        LocalDate today = LocalDate.now();
        int age = (int) ChronoUnit.YEARS.between(birthday,today);
        return age;
    }
}
