package com.test.vimond;

import com.test.vimond.interval.manager.IntervalManager;
import com.test.vimond.interval.manager.IntervalManagerImpl;
import com.test.vimond.model.Interval;
import com.test.vimond.model.IntervalFactory;

import java.util.Set;
import java.util.TreeSet;

/**
 * This is a test class used to test out the scenarios used for testing the
 * inclusive and exclusive intervals
 *
 */

public class Main {

    static Set<Interval> inclusiveSet = new TreeSet<>();
    static Set<Interval> exclusiveSet = new TreeSet<>();

    // Main method
    public static void main(String[] args) {
        prepareInput();
    }

    // some of the inputdata and expected output given in the test
    public static void prepareInput() {

        //Test 1
        inclusiveSet.add(IntervalFactory.createAnInterval(10, 100));
        exclusiveSet.add(IntervalFactory.createAnInterval(20, 30));
        testManager(inclusiveSet, exclusiveSet);
        inclusiveSet.removeAll(inclusiveSet);
        exclusiveSet.removeAll(exclusiveSet);

        //Test 2
        inclusiveSet.add(IntervalFactory.createAnInterval(50, 5000));
        inclusiveSet.add(IntervalFactory.createAnInterval(10, 100));
        testManager(inclusiveSet, exclusiveSet);
        inclusiveSet.removeAll(inclusiveSet);
        exclusiveSet.removeAll(exclusiveSet);

        //Test 3
        inclusiveSet.add(IntervalFactory.createAnInterval(200, 300));
        inclusiveSet.add(IntervalFactory.createAnInterval(50, 150));
        exclusiveSet.add(IntervalFactory.createAnInterval(95, 205));
        testManager(inclusiveSet, exclusiveSet);
        inclusiveSet.removeAll(inclusiveSet);
        exclusiveSet.removeAll(exclusiveSet);

        //Test 4
        inclusiveSet.add(IntervalFactory.createAnInterval(200, 300));
        inclusiveSet.add(IntervalFactory.createAnInterval(10, 100));
        inclusiveSet.add(IntervalFactory.createAnInterval(400, 500));
        exclusiveSet.add(IntervalFactory.createAnInterval(410, 420));
        exclusiveSet.add(IntervalFactory.createAnInterval(95, 205));
        exclusiveSet.add(IntervalFactory.createAnInterval(100, 150));
        testManager(inclusiveSet, exclusiveSet);
        inclusiveSet.removeAll(inclusiveSet);
        exclusiveSet.removeAll(exclusiveSet);
    }

    // This is a test method used to for testing out the inclusive and exclusive intervals
    // one can directly call this method by passing the set of inclusive and exclusive data and expect the result
    // printed on the console
    public static void testManager(Set<Interval> inclusiveSet, Set<Interval> exclusiveSet) {

        Interval[] inclusiveArray = new Interval[inclusiveSet.size()];
        Interval[] exclusiveArray = new Interval[exclusiveSet.size()];

        System.arraycopy(inclusiveSet.toArray(), 0, inclusiveArray, 0, inclusiveSet.size());
        System.arraycopy(exclusiveSet.toArray(), 0, exclusiveArray, 0, exclusiveSet.size());

        IntervalManager manager = new IntervalManagerImpl();
        manager.init(inclusiveArray, exclusiveArray);
        Set<Interval> resultantInterval = manager.formResultantInterval();

        System.out.println(resultantInterval);
        System.out.println("==================================================================================");

    }

}
