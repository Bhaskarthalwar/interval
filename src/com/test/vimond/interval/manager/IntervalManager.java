package com.test.vimond.interval.manager;

import com.test.vimond.model.Interval;

import java.util.Set;

/**
 * @param <T> - Interval
 *            <p>
 *            This is the main service layer interface which operates on a set of
 *            intervals like initialing the data structures then removing the overlapping intervals
 *            then it takes in to account of inclusive interval and exclusive intervals to generate
 *            the final set of resultant intervals
 */
public interface IntervalManager<T extends Interval> {

    /**
     * The initiation of inclusive set and exclusive set and also other required data structures further
     *
     * @param inclusive
     * @param exclusive
     */
    public void init(T[] inclusive, T[] exclusive);

    /**
     * @param intervals
     * @return T[] - Set of intervals after the removal of overlapping intervals
     */
    public T[] mergeOverlappingIntervals(T[] intervals);

    /**
     * @return Set - form the final output set after going through the input and output sets
     */
    public Set<T> formResultantInterval();

}
