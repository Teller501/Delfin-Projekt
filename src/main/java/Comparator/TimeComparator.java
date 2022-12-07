package Comparator;

import Member.Result;

import java.util.Comparator;

public class TimeComparator implements Comparator<Result> {

    @Override
    public int compare(Result o1, Result o2) {
        return Double.compare(o1.getTime(),o2.getTime());
    }
}
