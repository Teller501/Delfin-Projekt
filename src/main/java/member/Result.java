package member;

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


    public Member getMember() {
        return member;
    }

    public double getTime() {
        return time;
    }

    public int getPlacement() {
        return placement;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getConvention() {
        return convention;
    }

    public void setConvention(String convention) {
        this.convention = convention;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
