package atmservice.parser.json;

import atmservice.model.request.RequestType;
import atmservice.model.request.ServiceTasks;
import atmservice.model.request.Task;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ATMRequestParserTest {

    @Test
    public void parseRequestWorks() {
        // given
        String jsonRequest = "[{\"region\":4,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":1,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":2,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":3,\"requestType\":\"PRIORITY\",\"atmId\":2},{\"region\":3,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":2,\"requestType\":\"SIGNAL_LOW\",\"atmId\":1},{\"region\":5,\"requestType\":\"STANDARD\",\"atmId\":2},{\"region\":5,\"requestType\":\"FAILURE_RESTART\",\"atmId\":1}]";
        ServiceTasks expectedResult = new ServiceTasks(List.of(
                new Task(1, 4, RequestType.STANDARD),
                new Task(1, 1, RequestType.STANDARD),
                new Task(1, 2, RequestType.STANDARD),
                new Task(2, 3, RequestType.PRIORITY),
                new Task(1, 3, RequestType.STANDARD),
                new Task(1, 2, RequestType.SIGNAL_LOW),
                new Task(2, 5, RequestType.STANDARD),
                new Task(1, 5, RequestType.FAILURE_RESTART)
        ));

        // when
        ServiceTasks result = ATMRequestParser.parseRequest(jsonRequest);

        // then
        Assert.assertEquals(expectedResult, result);
    }
}