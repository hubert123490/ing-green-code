package model.response;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<ATM> atms = new ArrayList<>();

    public List<ATM> getAtms() {
        return atms;
    }

    public void setAtms(List<ATM> atms) {
        this.atms = atms;
    }
}
