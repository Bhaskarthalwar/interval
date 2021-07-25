package com.test.vimond.model;

import java.util.Objects;

/**
 * Interval class defines an interval which has a starting number and an ending number
 * This class forms the basis for set of inclusive and exclusive intervals
 */
public abstract class Interval implements Comparable<Interval> {

    private int start, end;

    /**
     * @param start
     * @param end
     */
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @return starting interval
     */
    public final int getStart() {
        return this.start;
    }

    /**
     * @return ending interval
     */
    public final int getEnd() {
        return this.end;
    }


    /**
     * @param o
     * @return 0, 1 or -1
     */
    @Override
    public int compareTo(Interval o) {
        int diff = (this.start - o.start);
        return diff == 0 ? (this.end - o.end) : diff;
    }

    /**
     * @param o
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interval)) return false;
        Interval interval = (Interval) o;
        return start == interval.start &&
                end == interval.end;
    }

    /**
     * @return hashCode of an object
     */
    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    /**
     * @return String representation of an interval
     */
    @Override
    public String toString() {
        return "(" + start +
                "," + end + ")";

    }
}

