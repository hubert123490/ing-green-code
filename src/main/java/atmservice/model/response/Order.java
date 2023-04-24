package atmservice.model.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private List<ATM> atms = new ArrayList<>();

    public Order() {}

    public Order(List<ATM> atms) {
        this.atms = atms;
    }

    public List<ATM> getAtms() {
        return atms;
    }

    public void setAtms(List<ATM> atms) {
        this.atms = atms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return atms.equals(order.atms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atms);
    }
}
