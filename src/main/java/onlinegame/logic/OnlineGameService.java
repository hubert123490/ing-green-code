package onlinegame.logic;

import onlinegame.model.request.Clan;
import onlinegame.model.response.Group;
import onlinegame.model.request.Players;
import onlinegame.model.response.Order;

import java.util.Iterator;
import java.util.PriorityQueue;

public class OnlineGameService {
    public static Order process(Players req) {
        Order order = new Order();
        PriorityQueue<Clan> priorityQueue = new PriorityQueue<>((Clan p1, Clan p2) ->
        {
            int result = p2.getPoints() - p1.getPoints();
            if (result == 0) {
                result = Integer.compare(p1.getNumberOfPlayers(), p2.getNumberOfPlayers());
            }
            return result;
        });

        priorityQueue.addAll(req.getClans());

        while (!priorityQueue.isEmpty()) {
            int sum = 0;
            Iterator<Clan> iterator = priorityQueue.iterator();
            Group group = new Group();
            order.getGroups().add(group);
            while (iterator.hasNext() && sum != req.getGroupCount()) {
                Clan clan = iterator.next();
                if (sum + clan.getNumberOfPlayers() > req.getGroupCount()) continue;
                sum += clan.getNumberOfPlayers();
                group.getClans().add(clan);
                iterator.remove();
            }
        }

        return order;
    }
}

