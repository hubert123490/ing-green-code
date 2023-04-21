package logic;

import model.RequestType;
import model.request.ServiceTasks;
import model.request.Task;

import javax.json.*;
import java.io.StringReader;
import java.util.*;

public class JsonParser {
    private static final int REGION_LIMIT = 9999;
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

    public static String parseResponse(Map<Integer, Map<RequestType, List<Integer>>> res) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for(int i = 0; i < REGION_LIMIT; i++) {
            if(res.get(i) == null) continue;

            for(int num : getPriorityArray(res.get(i))) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("region", i);
                objectBuilder.add("atmId", num);
                JsonObject obj = objectBuilder.build();
                arrayBuilder.add(obj);
            }
        }

        return arrayBuilder.build().toString();
    }

    private static Set<Integer> getPriorityArray(Map<RequestType, List<Integer>> priorities) {
        Set<Integer> response = new HashSet<>();

        for (RequestType type : RequestType.values()) {
            List<Integer> priorityArray = priorities.get(type);
            if(priorityArray == null) continue;
            response.addAll(priorityArray);
        }

        return response;
    }
}
