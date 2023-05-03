package atmservice.model.response;

import atmservice.model.request.Task;

import java.util.Objects;

public class ATM {
    private final int region;
    private final int atmId;

    public ATM(int atmId, int region) {
        this.atmId = atmId;
        this.region = region;
    }

    public ATM(Task task) {
        this.atmId = task.getAtmId();
        this.region = task.getRegion();
    }


    public int getRegion() {
        return region;
    }

    public int getAtmId() {
        return atmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ATM atm = (ATM) o;
        return region == atm.region && atmId == atm.atmId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(region, atmId);
    }
}
