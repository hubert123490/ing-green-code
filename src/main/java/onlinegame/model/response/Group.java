package onlinegame.model.response;

import onlinegame.model.request.Clan;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group {
    private List<Clan> clans = new ArrayList<>();

    public Group() {}

    public Group(List<Clan> clans) {
        this.clans = clans;
    }

    public List<Clan> getClans() {
        return clans;
    }

    public void setClans(List<Clan> clans) {
        this.clans = clans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return clans.equals(group.clans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clans);
    }
}
