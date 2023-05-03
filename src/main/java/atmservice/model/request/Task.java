package atmservice.model.request;

import java.util.Objects;

public class Task {
    private final int atmId;
    private final int region;
    private final RequestType requestType;

    public Task(int atmId, int region, RequestType requestType) {
        this.atmId = atmId;
        this.region = region;
        this.requestType = requestType;
    }

    public int getRegion() {
        return region;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public int getAtmId() {
        return atmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return atmId == task.atmId && region == task.region && requestType == task.requestType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(atmId, region, requestType);
    }
}
