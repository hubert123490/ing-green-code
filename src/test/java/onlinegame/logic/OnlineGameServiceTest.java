package onlinegame.logic;

import onlinegame.model.request.Clan;
import onlinegame.model.request.Players;
import onlinegame.model.response.Group;
import onlinegame.model.response.Order;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OnlineGameServiceTest {

    @Test
    public void testCase1() {
        // given
        Players players = new Players(6, List.of(
                new Clan(4, 50),
                new Clan(2, 70),
                new Clan(6, 60),
                new Clan(1, 15),
                new Clan(5, 40),
                new Clan(3, 45),
                new Clan(1, 12),
                new Clan(4, 40)
        ));
        Order expectedResult = new Order(List.of(
                new Group(List.of(
                        new Clan(2, 70),
                        new Clan(4, 50)
                )),
                new Group(List.of(
                        new Clan(6, 60)
                )),
                new Group(List.of(
                        new Clan(3, 45),
                        new Clan(1, 15),
                        new Clan(1, 12)
                )),
                new Group(List.of(
                        new Clan(4, 40)
                )),
                new Group(List.of(
                        new Clan(5, 40)
                ))));

        // when
        Order result = OnlineGameService.process(players);

        // then
        Assert.assertEquals(expectedResult, result);
    }
}