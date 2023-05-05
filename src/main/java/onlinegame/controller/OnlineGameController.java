package onlinegame.controller;

import com.sun.net.httpserver.HttpExchange;
import onlinegame.logic.OnlineGameService;
import onlinegame.model.request.Players;
import onlinegame.model.response.Order;
import onlinegame.parser.json.OnlineGameRequestParser;
import onlinegame.parser.json.OnlineGameResponseParser;
import server.HttpController;

import java.io.IOException;
import java.io.OutputStream;

public class OnlineGameController extends HttpController {

    @Override
    public void handlePost(HttpExchange exchange, String requestBody) throws IOException {
        Order order = parseRequestBody(requestBody);

        String jsonResponse = OnlineGameResponseParser.parseResponse(order);

        this.sendResponse(exchange, jsonResponse);
    }

    private Order parseRequestBody(String requestBody) {
        Players players = OnlineGameRequestParser.parseRequest(requestBody);

        return OnlineGameService.getOrderFromPlayers(players);
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(response.getBytes());
        }
    }
}
