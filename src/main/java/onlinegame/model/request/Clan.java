package onlinegame.model.request;

import java.util.Objects;

public class Clan {
    public Clan(int numberOfPlayers, int points) {
        this.numberOfPlayers = numberOfPlayers;
        this.points = points;
    }

    private final int numberOfPlayers;
    private final int points;

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clan clan = (Clan) o;
        return numberOfPlayers == clan.numberOfPlayers && points == clan.points;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfPlayers, points);
    }
}
