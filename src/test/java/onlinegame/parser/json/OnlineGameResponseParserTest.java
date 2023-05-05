package onlinegame.parser.json;

import onlinegame.model.request.Clan;
import onlinegame.model.response.Group;
import onlinegame.model.response.Order;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OnlineGameResponseParserTest {

    @Test
    public void parseResponseWorks() {
        // given
        Order order = new Order(List.of(
                new Group(List.of(
                        new Clan(2, 70),
                        new Clan(4, 50)
                )),
                new Group(List.of(new Clan(6, 60))),
                new Group(List.of(
                        new Clan(3, 45),
                        new Clan(1, 15),
                        new Clan(1, 12)
                )),
                new Group(List.of(new Clan(4, 40))),
                new Group(List.of(new Clan(5, 40)))
        ));
        String expectedResult = "[[{\"numberOfPlayers\":2,\"points\":70},{\"numberOfPlayers\":4,\"points\":50}],[{\"numberOfPlayers\":6,\"points\":60}],[{\"numberOfPlayers\":3,\"points\":45},{\"numberOfPlayers\":1,\"points\":15},{\"numberOfPlayers\":1,\"points\":12}],[{\"numberOfPlayers\":4,\"points\":40}],[{\"numberOfPlayers\":5,\"points\":40}]]";

        // when
        String result = OnlineGameResponseParser.parseResponse(order);

        // then
        Assert.assertEquals(expectedResult, result);
    }
}