package atmservice.model.response;

import java.util.Objects;

public class ATM {
    private int region;
    private int atmId;

    public ATM(int atmId, int region) {
        this.atmId = atmId;
        this.region = region;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getAtmId() {
        return atmId;
    }

    public void setAtmId(int atmId) {
        this.atmId = atmId;
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
