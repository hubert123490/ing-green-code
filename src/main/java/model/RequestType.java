package model;

public enum RequestType {
    FAILURE_RESTART("FAILURE_RESTART"),
    PRIORITY("PRIORITY"),
    SIGNAL_LOW("SIGNAL_LOW"),
    STANDARD("STANDARD");


    private final String value;

    RequestType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
