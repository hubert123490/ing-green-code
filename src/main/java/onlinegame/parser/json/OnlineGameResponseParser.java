package onlinegame.parser.json;

import java.util.function.Function;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import onlinegame.model.request.Clan;
import onlinegame.model.response.Group;
import onlinegame.model.response.Order;

public class OnlineGameResponseParser {

    private OnlineGameResponseParser() {}

    public static String parseResponse(Order order) {
        JSONArray jsonArray = new JSONArray();

        order.getGroups().stream().map(parseGroupToJsonArray).forEach(jsonArray::add);

        return jsonArray.toJSONString();
    }

    private static final Function<Clan, JSONObject> parseClanToJsonObject = clan -> {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("numberOfPlayers", clan.getNumberOfPlayers());
        jsonObject.put("points", clan.getPoints());

        return jsonObject;
    };

    private static final Function<Group, JSONArray> parseGroupToJsonArray = group -> {
        JSONArray jsonArray = new JSONArray();

        group.getClans().stream().map(parseClanToJsonObject).forEach(jsonArray::add);

        return jsonArray;
    };
}
