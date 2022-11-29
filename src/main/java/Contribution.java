public class Contribution {
    private int price;
    private Member member;

    public Contribution(Member member) {
        this.member = member;
    }


    public Member getMember() {
        return member;
    }

    public int calculateContributionPrice(){
        if (!member.isActive()){
            price = 500;
        }else{
            if (member.calculateMemberType() == MemberType.JUNIOR_SWIMMER){
                price = 1000;
            }else if(member.calculateMemberType() == MemberType.SENIOR_SWIMMER){
                price = 1600;
            }else if (member.calculateMemberType() == MemberType.PENSION_SWIMMER){
                price = 1200;
            }
        }
        return price;
    }

}
