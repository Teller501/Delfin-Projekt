import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class Member {

    private String name;
    private LocalDate birthday;
    private LocalDate registerDate;
    private boolean active;
    private int phoneNumber;
    private boolean juniorSwimmer, seniorSwimmer;

    public Member(String name, LocalDate birthday, LocalDate registerDate, boolean active, int phoneNumber) {
        this.name = name;
        this.birthday = birthday;
        this.registerDate = registerDate;
        this.active = active;
        this.phoneNumber = phoneNumber;

    }

    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Member() {
        // Empty for loading data
    }

    // Getters for attributes


    public String getName() {
        return name;
    }


    public boolean isActive() {
        return active;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public boolean isJuniorSwimmer() {
        return juniorSwimmer;
    }

    public boolean isSeniorSwimmer() {
        return seniorSwimmer;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
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

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public int calculateAge(){
        LocalDate today = LocalDate.now();
        int age = (int) ChronoUnit.YEARS.between(birthday,today);
        return age;
    }

    public void calculateMemberType(){
        if (calculateAge() < 18){
            juniorSwimmer = true;
            seniorSwimmer = false;
        }else if(calculateAge() > 60){
            seniorSwimmer = true;
            juniorSwimmer = false;
        }
    }


}
