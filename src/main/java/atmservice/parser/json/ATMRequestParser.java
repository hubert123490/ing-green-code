package atmservice.parser.json;

import atmservice.model.request.RequestType;
import atmservice.model.request.ServiceTasks;
import atmservice.model.request.Task;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ATMRequestParser {

    public static ServiceTasks parseRequest(String jsonRequest) {
        JSONArray jsonArray = JSONArray.parseArray(jsonRequest);
        List<Task> tasks = parseJsonArrayToTasks(jsonArray);
        return new ServiceTasks(tasks);
    }

    private static List<Task> parseJsonArrayToTasks(JSONArray jsonArray) {
        return jsonArray.stream().map(parseJsonObjectToTask).collect(Collectors.toList());
    }

    private static final Function<Object, Task> parseJsonObjectToTask = obj -> {
        JSONObject jsonObject = (JSONObject) obj;

        int region = jsonObject.getIntValue("region");
        RequestType requestType = parseRequestType(jsonObject);
        int atmId = jsonObject.getIntValue("atmId");

        return new Task(atmId, region, requestType);
    };

    private static RequestType parseRequestType(JSONObject jsonObject) {
        String request = jsonObject.getString("requestType");
        return RequestType.valueOf(request.toUpperCase());
    }
}