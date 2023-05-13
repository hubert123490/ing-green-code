package onlinegame.controller;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import onlinegame.logic.OnlineGameService;
import onlinegame.model.request.Players;
import onlinegame.model.response.Order;
import onlinegame.parser.json.OnlineGameRequestParser;
import onlinegame.parser.json.OnlineGameResponseParser;
import server.HttpController;


public class OnlineGameController extends HttpController {

    @Override
    public void handlePostRequest(HttpServerExchange exchange, String requestBody) {
        Order order = parseRequestBody(requestBody);

        String jsonResponse = OnlineGameResponseParser.parseResponse(order);

        this.sendResponse(exchange, jsonResponse);
    }

    private Order parseRequestBody(String requestBody) {
        Players players = OnlineGameRequestParser.parseRequest(requestBody);

        return OnlineGameService.getOrderFromPlayers(players);
    }

    private void sendResponse(HttpServerExchange exchange, String response) {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(response);
    }
}
