package com.test.vimond.interval.exception;

/**
 * it captures any runtime exceptions relating to the interval
 */
public class IntervalException extends RuntimeException {

    /**
     * @param errorMessage
     */
    public IntervalException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * @param errorMessage
     * @param err
     */
    public IntervalException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
