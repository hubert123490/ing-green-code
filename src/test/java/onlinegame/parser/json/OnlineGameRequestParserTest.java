package onlinegame.parser.json;

import onlinegame.model.request.Clan;
import onlinegame.model.request.Players;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OnlineGameRequestParserTest {

    @Test
    public void parseRequestWorks() {
        // given
        String jsonRequest = "{\"groupCount\":6,\"clans\":[{\"numberOfPlayers\":4,\"points\":50},{\"numberOfPlayers\":2,\"points\":70},{\"numberOfPlayers\":6,\"points\":60},{\"numberOfPlayers\":1,\"points\":15},{\"numberOfPlayers\":5,\"points\":40},{\"numberOfPlayers\":3,\"points\":45},{\"numberOfPlayers\":1,\"points\":12},{\"numberOfPlayers\":4,\"points\":40}]}";
        Players expectedResult = new Players(
                6, List.of(
                new Clan(4, 50),
                new Clan(2, 70),
                new Clan(6, 60),
                new Clan(1, 15),
                new Clan(5, 40),
                new Clan(3, 45),
                new Clan(1, 12),
                new Clan(4, 40)
        )
        );

        // when
        Players result = OnlineGameRequestParser.parseRequest(jsonRequest);

        // then
        Assert.assertEquals(expectedResult, result);
    }
}