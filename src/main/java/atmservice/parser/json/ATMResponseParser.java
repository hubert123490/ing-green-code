package atmservice.parser.json;

import atmservice.model.response.ATM;
import atmservice.model.response.Order;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.function.Function;

public class ATMResponseParser {

    public static String parseResponse(Order order) {
        JSONArray jsonArray = new JSONArray();

        order.getAtms().stream().map(parseTaskToJsonObject).forEach(jsonArray::add);

        return jsonArray.toJSONString();
    }

    private static final Function<ATM, JSONObject> parseTaskToJsonObject = atm -> {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("region", atm.getRegion());
        jsonObject.put("atmId", atm.getAtmId());

        return jsonObject;
    };
}
