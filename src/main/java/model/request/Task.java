package model.request;

import model.RequestType;

public class Task {
    private int atmId;
    private int region;
    private RequestType requestType;

    public Task(int atmId, int region, RequestType requestType) {
        this.atmId = atmId;
        this.region = region;
        this.requestType = requestType;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public int getAtmId() {
        return atmId;
    }

    public void setAtmId(int atmId) {
        this.atmId = atmId;
    }
}
