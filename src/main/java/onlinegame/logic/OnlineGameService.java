package onlinegame.logic;

import onlinegame.model.request.Clan;
import onlinegame.model.response.Group;
import onlinegame.model.request.Players;
import onlinegame.model.response.Order;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class OnlineGameService {
    private static final Comparator<Clan> clanComparator = Comparator
            .comparingInt(Clan::getPoints)
            .reversed()
            .thenComparingInt(Clan::getNumberOfPlayers);

    public static Order getOrderFromPlayers(Players players) {
        PriorityQueue<Clan> orderedClansQueue = getSortedClansQueue.apply(players);

        return new Order(getSortedGroups.apply(orderedClansQueue, players.getGroupCount()));
    }

    private static final BiFunction<Iterator<Clan>, Integer, List<Clan>> getSortedClans
            = (iterator, groupCount) -> {
        List<Clan> clans = new ArrayList<>();
        int sum = 0;

        while (iterator.hasNext() && sum < groupCount) {
            Clan clan = iterator.next();
            int clanSize = clan.getNumberOfPlayers();

            if (sum + clanSize > groupCount) continue;

            clans.add(clan);
            sum += clanSize;
            iterator.remove();
        }

        return clans;
    };

    private static final BiFunction<PriorityQueue<Clan>, Integer, List<Group>> getSortedGroups
            = (orderedClans, groupCount) -> {
        List<Group> groups = new ArrayList<>();

        while (!orderedClans.isEmpty()) {
            Iterator<Clan> iterator = orderedClans.iterator();

            groups.add(new Group(getSortedClans.apply(iterator, groupCount)));
        }

        return groups;
    };


    private static final Function<Players, PriorityQueue<Clan>> getSortedClansQueue = players -> {
        PriorityQueue<Clan> priorityQueue = new PriorityQueue<>(clanComparator);

        priorityQueue.addAll(players.getClans());

        return priorityQueue;
    };
}
