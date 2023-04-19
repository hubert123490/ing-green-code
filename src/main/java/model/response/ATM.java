package model.response;

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
}
