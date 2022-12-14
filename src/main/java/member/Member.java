package member;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Member {

    public enum MemberType {
        JUNIOR_SWIMMER,
        SENIOR_SWIMMER,
        PENSION_SWIMMER
    }
    private String name;
    private LocalDate birthday;
    private LocalDate registerDate;
    private boolean active;
    private int phoneNumber;
    private Contribution contribution = new Contribution(this);
    private String team;

    public Member(String name, LocalDate birthday, LocalDate registerDate, boolean active, int phoneNumber) {
        this.name = name;
        this.birthday = birthday;
        this.registerDate = registerDate;
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


    public boolean isActive() {
        return active;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
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

    // Calculating members age from their birthday to the current date
    public int calculateAge(){
        LocalDate today = LocalDate.now();
        int age = (int) ChronoUnit.YEARS.between(birthday,today);
        return age;
    }

    // Calculating what type the member is based of member age
    public MemberType calculateMemberType(){
        if (calculateAge() < 18){
            return Member.MemberType.JUNIOR_SWIMMER;
        }else if(calculateAge() > 60){
            return Member.MemberType.PENSION_SWIMMER;
        }else{
            return Member.MemberType.SENIOR_SWIMMER;
        }
    }

    public int getContributionPrice(){
        return contribution.calculateContributionPrice();
    }

    public boolean getArrearStatus(){
        return contribution.isArrear();
    }

    public int getOwes(){
        return contribution.calculateGuilt();
    }


    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }


}
