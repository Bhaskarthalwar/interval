package com.test.vimond.interval.manager;

import com.test.vimond.interval.exception.IntervalException;
import com.test.vimond.interval.validator.IntervalValidator;
import com.test.vimond.interval.validator.IntervalValidatorImpl;
import com.test.vimond.model.Interval;
import com.test.vimond.model.IntervalFactory;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * The implementation of interval manager
 * <p>
 * init() - initialize the required data set
 * <p>
 * mergeOverlappingIntervals() - merge overlapping intervals
 * <p>
 * formResultantInterval() - generate the resultant intervals
 */
public class IntervalManagerImpl implements IntervalManager {

    private Set<Interval> inclusiveIntervalSet;
    private Set<Interval> exclusiveIntervalSet;
    private IntervalValidator intervalValidator;

    /**
     * @param inclusive
     * @param exclusive Initialize the set of intervals required for generating the final intervals
     *                  It also merges the overlappig intervals for the inclusive and exclusive sets
     */
    @Override
    public void init(Interval[] inclusive, Interval[] exclusive) {

        inclusiveIntervalSet = new ConcurrentSkipListSet<>();
        exclusiveIntervalSet = new ConcurrentSkipListSet<>();
        intervalValidator = new IntervalValidatorImpl();

        inclusive = mergeOverlappingIntervals(inclusive);
        exclusive = mergeOverlappingIntervals(exclusive);

        for (Interval interval : inclusive) {
            if (interval != null)
                inclusiveIntervalSet.add(interval);
        }

        for (Interval interval : exclusive) {
            if (interval != null)
                exclusiveIntervalSet.add(interval);
        }

        if (inclusiveIntervalSet.size() <= 0) {
            throw new IntervalException("The inclusive interval is mandatory");
        }

    }

    /**
     * @param arr
     * @return set of intervals got after removing
     * the overlapping intervals for both inclusive and exclusive intervals
     */
    @Override
    public Interval[] mergeOverlappingIntervals(Interval[] arr) {

        // Stores index of last element
        int index = 0;

        for (int i = 1; i < arr.length; i++) {
            // If this is not first Interval and overlaps with the previous one
            if (arr[index].getEnd() >= arr[i].getStart()) {
                // Merge previous and current Intervals
                int end = Math.max(arr[index].getEnd(), arr[i].getEnd());
                int start = Math.min(arr[index].getStart(), arr[i].getStart());
                Interval newInterval = IntervalFactory.createAnInterval(start, end);
                arr[index] = newInterval;
            } else {
                index++;
                arr[index] = arr[i];
            }
        }
        //The elements residing after the merge needs to be set to null
        if (arr.length > index) {
            int i = index + 1;
            while (i < arr.length) {
                arr[i] = null;
                i++;
            }
        }
        return arr;
    }

    /**
     * @return Set - the final resultant set
     * Iterate through the input and output set then use the
     * interval validator to validate if there is a overlapping between inclusive interval and exclusive interval
     * generate the output range and also see to it if the range goes out of scope then remove that element
     */
    @Override
    public Set<Interval> formResultantInterval() {
        final Set<Interval> resultantInterval = new TreeSet<>();

        if (exclusiveIntervalSet.size() <= 0)
            return inclusiveIntervalSet;

        for (Interval inclusive : inclusiveIntervalSet) {
            for (Interval exclusive : exclusiveIntervalSet) {

                // case - when one in-point and one ex-point results in two resultant points
                // since the bigger range is for the inclusive than the exclusive and inclusive end is greater than
                // exclusive end and inclusive overlaps with exclusive
                // example I(1,10) E(3,8) = (1,2)(9,10)

                if (intervalValidator.isOverlappedExtensionForInclusiveInterval(inclusive, exclusive)) {
                    Interval firstRange = IntervalFactory.createAnInterval(inclusive.getStart(), exclusive.getStart() - 1);
                    Interval secondRange = IntervalFactory.createAnInterval(exclusive.getEnd() + 1, inclusive.getEnd());
                    resultantInterval.add(firstRange);
                    resultantInterval.add(secondRange);
                }

                // case - when one in-point and one ex-point results in one resultant point then remove the
                // inclusive point since it goes out of range for checking with other endpoints and has to be removed
                // to not make any more comparision with that point and results in few more lesser comparisions
                // example (1,5) (3,7) = (1,2)

                if (intervalValidator.isOverlappedExtensionForExclusiveInterval(inclusive, exclusive)) {
                    Interval range = IntervalFactory.createAnInterval(inclusive.getStart(), exclusive.getStart() - 1);
                    resultantInterval.add(range);
                    inclusiveIntervalSet.remove(inclusive);
                    break;
                }

                // case - when one in-point and one ex-point results in one resultant point then then remove the
                // exclusive point since it goes out of range for checking with other endpoints and has to be removed
                // to not make any more comparision with that point and results in few more lesser comparisions
                // example (200,300) (95,205) = (206,300)

                if (intervalValidator.isInclusiveIntervalExtendsExclusiveInterval(inclusive, exclusive)) {
                    Interval range = IntervalFactory.createAnInterval(exclusive.getEnd() + 1, inclusive.getEnd());
                    resultantInterval.add(range);
                    exclusiveIntervalSet.remove(exclusive);
                }

            }
        }
        return resultantInterval;
    }
}
