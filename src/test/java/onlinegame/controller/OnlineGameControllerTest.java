package onlinegame.controller;

import com.sun.net.httpserver.HttpExchange;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OnlineGameControllerTest {
    private final OnlineGameController sut = new OnlineGameController();

    @Test
    public void handlePostWorks() throws IOException {
        /// given
        HttpExchange exchange = Mockito.mock(HttpExchange.class);
        String requestBody = "{\"groupCount\":6,\"clans\":[{\"numberOfPlayers\":4,\"points\":50},{\"numberOfPlayers\":2,\"points\":70},{\"numberOfPlayers\":6,\"points\":60},{\"numberOfPlayers\":1,\"points\":15},{\"numberOfPlayers\":5,\"points\":40},{\"numberOfPlayers\":3,\"points\":45},{\"numberOfPlayers\":1,\"points\":12},{\"numberOfPlayers\":4,\"points\":40}]}";
        String expectedResult = "[[{\"numberOfPlayers\":2,\"points\":70},{\"numberOfPlayers\":4,\"points\":50}],[{\"numberOfPlayers\":6,\"points\":60}],[{\"numberOfPlayers\":3,\"points\":45},{\"numberOfPlayers\":1,\"points\":15},{\"numberOfPlayers\":1,\"points\":12}],[{\"numberOfPlayers\":4,\"points\":40}],[{\"numberOfPlayers\":5,\"points\":40}]]";
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