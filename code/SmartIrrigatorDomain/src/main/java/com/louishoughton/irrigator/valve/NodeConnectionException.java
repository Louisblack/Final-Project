package com.louishoughton.irrigator.valve;

public class NodeConnectionException extends Exception {

    private static final long serialVersionUID = 7858938110616553154L;

    public NodeConnectionException(String message, Throwable exception) {
        super(message, exception);
    }

    public NodeConnectionException(String message) {
        super(message);
    }
}
