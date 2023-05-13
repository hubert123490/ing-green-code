package onlinegame.parser.json;

import java.util.List;
import java.util.function.Function;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import onlinegame.model.request.Clan;
import onlinegame.model.request.Players;

public class OnlineGameRequestParser {

    private OnlineGameRequestParser() {}

    public static Players parseRequest(String jsonRequest) {
        JSONObject jsonObject = JSONObject.parseObject(jsonRequest);
        JSONArray jsonArray = jsonObject.getJSONArray("clans");

        int groupCount = jsonObject.getIntValue("groupCount");
        List<Clan> clans = parseJsonArrayToClans(jsonArray);

        return new Players(groupCount, clans);
    }

    private static List<Clan> parseJsonArrayToClans(JSONArray jsonArray) {
        return jsonArray.stream()
                .map(parseJsonObjectToClan)
                .toList();
    }

    private static final Function<Object, Clan> parseJsonObjectToClan = jsonObject -> {
        int numberOfPlayers = ((JSONObject) jsonObject).getIntValue("numberOfPlayers");
        int points = ((JSONObject) jsonObject).getIntValue("points");

        return new Clan(numberOfPlayers, points);
    };
}
