package atmservice.logic;

import atmservice.model.RequestType;
import atmservice.model.request.ServiceTasks;
import atmservice.model.request.Task;
import atmservice.model.response.ATM;
import atmservice.model.response.Order;

import javax.json.*;
import java.io.StringReader;

public class JsonParser {

    public static ServiceTasks parseRequest(String req) {
        ServiceTasks serviceTasks = new ServiceTasks();

        try (JsonReader reader = Json.createReader(new StringReader(req))) {
            JsonArray jsonArray = reader.readArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.getJsonObject(i);
                int region = jsonObject.getInt("region");
                String request = jsonObject.getString("requestType");
                RequestType requestType = RequestType.valueOf(request.toUpperCase());
                int atmId = jsonObject.getInt("atmId");
                Task task = new Task(atmId, region, requestType);
                serviceTasks.getTasks().add(task);
            }
        }
        return serviceTasks;
    }

    public static String parseResponse(Order res) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for(ATM atm : res.getAtms()) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("region", atm.getRegion());
                objectBuilder.add("atmId", atm.getAtmId());
                JsonObject obj = objectBuilder.build();
                arrayBuilder.add(obj);
        }

        return arrayBuilder.build().toString();
    }

}
