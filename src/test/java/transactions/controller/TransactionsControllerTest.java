package transactions.controller;

import com.sun.net.httpserver.HttpExchange;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TransactionsControllerTest {
    TransactionsController sut = new TransactionsController();

    @Test
    public void handlePost() throws IOException {
        /// given
        HttpExchange exchange = Mockito.mock(HttpExchange.class);
        String requestBody = "[{\"debitAccount\":\"32309111922661937852684864\",\"creditAccount\":\"06105023389842834748547303\",\"amount\":10.90},{\"debitAccount\":\"31074318698137062235845814\",\"creditAccount\":\"66105036543749403346524547\",\"amount\":200.90},{\"debitAccount\":\"66105036543749403346524547\",\"creditAccount\":\"32309111922661937852684864\",\"amount\":50.10}]";
        String expectedResult = "[{\"account\":\"06105023389842834748547303\",\"debitCount\":0,\"creditCount\":1,\"balance\":10.90},{\"account\":\"31074318698137062235845814\",\"debitCount\":1,\"creditCount\":0,\"balance\":-200.90},{\"account\":\"32309111922661937852684864\",\"debitCount\":1,\"creditCount\":1,\"balance\":39.20},{\"account\":\"66105036543749403346524547\",\"debitCount\":1,\"creditCount\":1,\"balance\":150.80}]";
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