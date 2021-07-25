package com.test.vimond.model;

import com.test.vimond.interval.exception.IntervalException;

/**
 * Factory class used to create the intervals , used to this class instead of directly instantiating the
 * Interval through the Interval model class , inorder to maintain the immutability aspect for the Interval class
 * also to ensure a better encapsulation strategy.
 */
public class IntervalFactory extends Interval {

    /**
     * Creates an Interval object by calling the Interval super class
     *
     * @param start
     * @param end
     */
    private IntervalFactory(int start, int end) {
        super(start, end);
    }

    /**
     * This is the single place to create an interval object
     *
     * @param start
     * @param end
     * @return Interval
     */
    public static Interval createAnInterval(final int start, final int end) {
        if (start > end)
            throw new IntervalException("The end of an interval always needs to be greater than start of the interval");
        return new IntervalFactory(start, end);
    }
}
