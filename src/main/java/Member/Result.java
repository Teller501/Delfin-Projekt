package Member;

import Member.Member;

import java.time.LocalDate;

public class Result {
    private LocalDate date;
    private Member member;
    private String convention;
    private int placement;
    private double time;

    public Result(Member member, LocalDate date, double time){
        this.member = member;
        this.date = date;
        this.time = time;
    }

    public Result(Member member, LocalDate date, double time, String convention, int placement){
        this.member = member;
        this.date = date;
        this.time = time;
        this.convention = convention;
        this.placement = placement;
    }

}
