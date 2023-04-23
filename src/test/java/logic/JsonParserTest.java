package logic;

import model.RequestType;
import model.request.ServiceTasks;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JsonParserTest {

    @Test
    public void parseRequest() {
        // given
        String req = "[{\"region\":4,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":1,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":2,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":3,\"requestType\":\"PRIORITY\",\"atmId\":2},{\"region\":3,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":2,\"requestType\":\"SIGNAL_LOW\",\"atmId\":1},{\"region\":5,\"requestType\":\"STANDARD\",\"atmId\":2},{\"region\":5,\"requestType\":\"FAILURE_RESTART\",\"atmId\":1}]";

        // when
        ServiceTasks result  = JsonParser.parseRequest(req);

        // then

    }


    @Test
    public void parseResponse() {
        // given
        Map<Integer, Map<RequestType, List<Integer>>> res = new HashMap<>();
        res.put(1, new HashMap<>());
        res.put(2, new HashMap<>());
        res.get(1).put(RequestType.SIGNAL_LOW, List.of(2));
        res.get(1).put(RequestType.PRIORITY, List.of(1));
        res.get(2).put(RequestType.STANDARD, List.of(3, 4));

        // when
        var result = JsonParser.parseResponse(res);

        // then

    }
}