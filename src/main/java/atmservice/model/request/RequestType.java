package atmservice.model.request;

public enum RequestType {
    //    kept in order from the highest priority to the lowest priority
    FAILURE_RESTART,
    PRIORITY,
    SIGNAL_LOW,
    STANDARD
}
