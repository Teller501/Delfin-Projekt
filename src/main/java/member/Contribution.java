package member;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Contribution {
    private int contributionPrice;
    private Member member;
    private boolean arrear;
    private int guilt;

    public Contribution(Member member) {
        this.member = member;
    }


    public Member getMember() {
        return member;
    }

    // Calculates the contribution price
    public int calculateContributionPrice(){
        if (!member.isActive()){
            contributionPrice = 500;
        }else{
            if (member.calculateMemberType() == Member.MemberType.JUNIOR_SWIMMER){
                contributionPrice = 1000;
            }else if(member.calculateMemberType() == Member.MemberType.SENIOR_SWIMMER){
                contributionPrice = 1600;
            }else if (member.calculateMemberType() == Member.MemberType.PENSION_SWIMMER){
                contributionPrice = 1200;
            }
        }
        return contributionPrice;
    }


    // Calculates if the member is in arrear
    public boolean isArrear(){
        int yearsBetweeen = (int) ChronoUnit.YEARS.between(member.getRegisterDate(), LocalDate.now());
        arrear = yearsBetweeen > 1;
        return arrear;
    }

    // Calculates how much members in arrear owes the swimclub
    public int calculateGuilt(){
        int yearsBetweeen = (int) ChronoUnit.YEARS.between(member.getRegisterDate(), LocalDate.now());
        if (arrear){
            guilt = yearsBetweeen * contributionPrice;
        }
        return guilt;
    }

}
