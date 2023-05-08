import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        Main.main(new String[0]);
    }

    @Test
    public void atmEndpointWorks() throws IOException {
        // given
        String url = "http://localhost:8080/atms/calculateOrder";
        String requestBody = "[{\"region\":4,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":1,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":2,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":3,\"requestType\":\"PRIORITY\",\"atmId\":2},{\"region\":3,\"requestType\":\"STANDARD\",\"atmId\":1},{\"region\":2,\"requestType\":\"SIGNAL_LOW\",\"atmId\":1},{\"region\":5,\"requestType\":\"STANDARD\",\"atmId\":2},{\"region\":5,\"requestType\":\"FAILURE_RESTART\",\"atmId\":1}]";
        String expectedResponse = "[{\"region\":1,\"atmId\":1},{\"region\":2,\"atmId\":1},{\"region\":3,\"atmId\":2},{\"region\":3,\"atmId\":1},{\"region\":4,\"atmId\":1},{\"region\":5,\"atmId\":1},{\"region\":5,\"atmId\":2}]";

        // when
        EndpointResult result = this.getEndpointTestResults(url, requestBody);

        // then
        assertEquals(200, result.getStatusCode());
        assertEquals(expectedResponse, result.getResponseBody());
    }

    @Test
    public void onlineGameEndpointWorks() throws IOException {
        // given
        String url = "http://localhost:8080/onlinegame/calculate";
        String requestBody = "{\"groupCount\":6,\"clans\":[{\"numberOfPlayers\":4,\"points\":50},{\"numberOfPlayers\":2,\"points\":70},{\"numberOfPlayers\":6,\"points\":60},{\"numberOfPlayers\":1,\"points\":15},{\"numberOfPlayers\":5,\"points\":40},{\"numberOfPlayers\":3,\"points\":45},{\"numberOfPlayers\":1,\"points\":12},{\"numberOfPlayers\":4,\"points\":40}]}";
        String expectedResponse = "[[{\"numberOfPlayers\":2,\"points\":70},{\"numberOfPlayers\":4,\"points\":50}],[{\"numberOfPlayers\":6,\"points\":60}],[{\"numberOfPlayers\":3,\"points\":45},{\"numberOfPlayers\":1,\"points\":15},{\"numberOfPlayers\":1,\"points\":12}],[{\"numberOfPlayers\":4,\"points\":40}],[{\"numberOfPlayers\":5,\"points\":40}]]";

        // when
        EndpointResult result = this.getEndpointTestResults(url, requestBody);

        // then
        assertEquals(200, result.getStatusCode());
        assertEquals(expectedResponse, result.getResponseBody());
    }

    @Test
    public void transactionsEndpointWorks() throws IOException {
        // given
        String url = "http://localhost:8080/transactions/report";
        String requestBody = "[{\"debitAccount\":\"32309111922661937852684864\",\"creditAccount\":\"06105023389842834748547303\",\"amount\":10.90},{\"debitAccount\":\"31074318698137062235845814\",\"creditAccount\":\"66105036543749403346524547\",\"amount\":200.90},{\"debitAccount\":\"66105036543749403346524547\",\"creditAccount\":\"32309111922661937852684864\",\"amount\":50.10}]";
        String expectedResponse = "[{\"account\":\"06105023389842834748547303\",\"debitCount\":0,\"creditCount\":1,\"balance\":10.90},{\"account\":\"31074318698137062235845814\",\"debitCount\":1,\"creditCount\":0,\"balance\":-200.90},{\"account\":\"32309111922661937852684864\",\"debitCount\":1,\"creditCount\":1,\"balance\":39.20},{\"account\":\"66105036543749403346524547\",\"debitCount\":1,\"creditCount\":1,\"balance\":150.80}]";

        // when
        EndpointResult result = this.getEndpointTestResults(url, requestBody);

        // then
        assertEquals(200, result.getStatusCode());
        assertEquals(expectedResponse, result.getResponseBody());
    }

    @Test
    public void notSupportedMethodsResponseWorks() throws IOException {
        // given
        int expectedResult = 404;
        URL endpoint = new URL("http://localhost:8080/transactions/report");
        HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);

        // when
        connection.connect();
        int result = connection.getResponseCode();

        // then
        assertEquals(expectedResult, result);
    }

    private EndpointResult getEndpointTestResults(String url, String requestBody) throws IOException {
        // given
        URL endpoint = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();

        // when
        this.setUpConnection(connection, requestBody);
        connection.connect();

        String result = this.readRequestResponse(connection);

        // then
        return new EndpointResult(connection.getResponseCode(), result);
    }

    private void setUpConnection(HttpURLConnection connection, String requestBody) throws IOException {
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        this.sendRequestBodyToConnection(connection, requestBody);
    }

    private void sendRequestBodyToConnection(HttpURLConnection connection, String requestBody) throws IOException {
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(requestBody.getBytes());
        }
    }

    private String readRequestResponse(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return in.readLine();
    }

    private static class EndpointResult {
        int statusCode;
        String responseBody;

        public EndpointResult(int statusCode, String responseBody) {
            this.statusCode = statusCode;
            this.responseBody = responseBody;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public String getResponseBody() {
            return responseBody;
        }
    }
}

