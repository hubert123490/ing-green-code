package atmservice.parser.json;

import atmservice.model.request.RequestType;
import atmservice.model.request.ServiceTasks;
import atmservice.model.request.Task;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ATMRequestParser {

    public static ServiceTasks parseRequest(String jsonRequest) {
        JSONArray jsonArray = (JSONArray) JSONValue.parse(jsonRequest);
        List<Task> tasks = parseJsonArrayToTasks(jsonArray);
        return new ServiceTasks(tasks);
    }

    private static List<Task> parseJsonArrayToTasks(JSONArray jsonArray) {
        return jsonArray.stream().map(parseJsonObjectToTask).collect(Collectors.toList());
    }

    private static final Function<Object, Task> parseJsonObjectToTask = obj -> {
        JSONObject jsonObject = (JSONObject) obj;

        int region = Integer.parseInt(jsonObject.get("region").toString());
        RequestType requestType = parseRequestType(jsonObject);
        int atmId = Integer.parseInt(jsonObject.get("atmId").toString());

        return new Task(atmId, region, requestType);
    };

    private static RequestType parseRequestType(JSONObject jsonObject) {
        String request = jsonObject.get("requestType").toString();
        return RequestType.valueOf(request.toUpperCase());
    }
}