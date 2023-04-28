package onlinegame.model.request;

import java.util.List;
import java.util.Objects;

public class Players {
    private int groupCount;
    private List<Clan> clans;

    public Players() {}

    public Players(int groupCount, List<Clan> clans) {
        this.groupCount = groupCount;
        this.clans = clans;
    }

    public int getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(int groupCount) {
        this.groupCount = groupCount;
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
        Players players = (Players) o;
        return groupCount == players.groupCount && clans.equals(players.clans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupCount, clans);
    }
}
