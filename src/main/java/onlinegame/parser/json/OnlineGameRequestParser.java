package onlinegame.parser.json;

import onlinegame.model.request.Clan;
import onlinegame.model.request.Players;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OnlineGameRequestParser {
    public static Players parseRequest(String jsonRequest) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonRequest);
        JSONArray jsonArray = (JSONArray) jsonObject.get("clans");

        int groupCount = Integer.parseInt(jsonObject.get("groupCount").toString());
        List<Clan> clans = parseJsonArrayToClans(jsonArray);

        return new Players(groupCount, clans);
    }

    private static List<Clan> parseJsonArrayToClans(JSONArray jsonArray) {
        return jsonArray.stream()
                .map(parseJsonObjectToClan)
                .collect(Collectors.toList());
    }

    private static final Function<Object, Clan> parseJsonObjectToClan = jsonObject -> {
        int numberOfPlayers = Integer.parseInt(((JSONObject) jsonObject).get("numberOfPlayers").toString());
        int points = Integer.parseInt(((JSONObject) jsonObject).get("points").toString());

        return new Clan(numberOfPlayers, points);
    };
}