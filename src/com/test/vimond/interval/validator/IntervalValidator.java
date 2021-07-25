package com.test.vimond.interval.validator;

import com.test.vimond.model.Interval;

/**
 * Check for the overlapping conditions between inclusive and exclusive intervals which
 * are used to generate the resultant point
 */
public interface IntervalValidator {

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
    public boolean isOverlappedExtensionForInclusiveInterval(Interval inclusive, Interval exclusive);

    /**
     * @param inc
     * @param exc
     * @return true/false
     * <p>
     * There is a overlap between inclusive and exclusive endpoint in which inclusive end is greater than
     * exclusive begin and exclusive end is greater than inclusive end creating in an extension of exclusive end point
     * and then one can remove inclusive endpoint since it wont be required to use again for comparision
     */
    public boolean isOverlappedExtensionForExclusiveInterval(Interval inc, Interval exc);

    /**
     * @param inc
     * @param exc
     * @return true/false
     * <p>
     * There is a overlap between inclusive and exclusive endpoint in which inclusive end is greater than
     * exclusive begin and inclusive end is greater than exclusive end creating in an extension of inclusive end point
     * and then one can remove exclusive endpoint since it wont be required to use again for comparision
     */
    public boolean isInclusiveIntervalExtendsExclusiveInterval(Interval inc, Interval exc);

}
