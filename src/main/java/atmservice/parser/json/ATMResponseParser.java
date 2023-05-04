package atmservice.parser.json;

import atmservice.model.response.ATM;
import atmservice.model.response.Order;

import javax.json.*;
import java.util.function.Function;

public class ATMResponseParser {

    public static String parseResponse(Order order) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        order.getAtms().stream().map(parseTaskToJsonObject).forEach(jsonValue -> arrayBuilder.add(jsonValue.asJsonObject()));

        return arrayBuilder.build().toString();
    }

    private static final Function<ATM, JsonValue> parseTaskToJsonObject = atm -> {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("region", atm.getRegion());
        objectBuilder.add("atmId", atm.getAtmId());

        return objectBuilder.build();
    };
}
