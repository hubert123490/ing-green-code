package onlinegame.model.response;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private List<Group> groups = new ArrayList<>();

    public Order() {}

    public Order(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return groups.equals(order.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groups);
    }
}