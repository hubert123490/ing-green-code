package atmservice.controller;

import com.sun.net.httpserver.HttpExchange;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ATMControllerTest {
    private final ATMController sut = new ATMController();

    @Test
    public void handlePostWorks() throws IOException {
        /// given
        HttpExchange exchange = Mockito.mock(HttpExchange.class);
        String requestBody = "[{\"region\":4,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":1,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":2,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":3,\"requestType\":\"PRIORITY\",\"atmId\":2},{\"region\":3,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":2,\"requestType\":\"SIGNAL_LOW\",\"atmId\":1},{\"region\":5,\"requestType\":\"STANDARD\",\"atmId\":2},{\"region\":5,\"requestType\":\"FAILURE_RESTART\",\"atmId\":1}]";
        String expectedResult = "[{\"region\":1,\"atmId\":1},{\"region\":2,\"atmId\":1},{\"region\":3,\"atmId\":2},{\"region\":3,\"atmId\":1},{\"region\":4,\"atmId\":1},{\"region\":5,\"atmId\":1},{\"region\":5,\"atmId\":2}]";

        // set up mock
        InputStream inputStream = new ByteArrayInputStream(requestBody.getBytes());
        Mockito.when(exchange.getRequestBody()).thenReturn(inputStream);
        OutputStream outputStream = Mockito.mock(OutputStream.class);
        Mockito.when(exchange.getResponseBody()).thenReturn(outputStream);

        // when
        sut.handlePost(exchange, requestBody);

        // then
        Mockito.verify(outputStream).write(expectedResult.getBytes());
    }
}