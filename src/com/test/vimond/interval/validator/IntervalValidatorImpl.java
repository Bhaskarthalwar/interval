package com.test.vimond.interval.validator;

import com.test.vimond.model.Interval;

/**
 * This class implements the IntervalValidator which is basically needed to
 * validate the overlapping interval conditions as described below by each condition.
 */

public class IntervalValidatorImpl implements IntervalValidator {

    /**
     * @param inclusive
     * @param exclusive
     * @return true/false
     * <p>
     * The inclusive point overlaps with the exclusive point with inclusive end being
     * greater than exclusive start and exclusive endpoint lesser than inclusive end point
     * and inclusive start being lesser than exclusive start . This condition results in creating
     * two range points .
     */
    @Override
    public boolean isOverlappedExtensionForInclusiveInterval(Interval inclusive, Interval exclusive) {
        return ((inclusive.getStart() < exclusive.getStart()) &&
                (exclusive.getEnd() < inclusive.getEnd()) &&
                (inclusive.getEnd() > exclusive.getStart()));
    }

    /**
     * @param inclusive
     * @param exclusive
     * @return true/false
     * <p>
     * The inclusive point overlaps with the exclusive point with inclusive end being
     * greater than exclusive start and exclusive endpoint lesser than inclusive end point
     * and inclusive start being lesser than exclusive start . This condition results in creating
     * two range points .
     */
    @Override
    public boolean isOverlappedExtensionForExclusiveInterval(Interval inclusive, Interval exclusive) {
        return ((inclusive.getStart() < exclusive.getStart()) &&
                (exclusive.getEnd() >= inclusive.getEnd()) &&
                (inclusive.getEnd() >= exclusive.getStart()));
    }

    /**
     * @param inclusive
     * @param exclusive
     * @return true/false
     * <p>
     * The inclusive point overlaps with the exclusive point with inclusive end being
     * greater than exclusive start and exclusive endpoint lesser than inclusive end point
     * and inclusive start being lesser than exclusive start . This condition results in creating
     * two range points .
     */
    @Override
    public boolean isInclusiveIntervalExtendsExclusiveInterval(Interval inclusive, Interval exclusive) {
        return ((inclusive.getStart() >= exclusive.getStart()) &&
                (inclusive.getEnd() > exclusive.getEnd()));
    }
}
