package atmservice.controller;

import atmservice.logic.ATMService;
import atmservice.model.request.ServiceTasks;
import atmservice.model.response.Order;
import atmservice.parser.json.ATMRequestParser;
import atmservice.parser.json.ATMResponseParser;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import server.HttpController;

public class ATMController extends HttpController {

    @Override
    public void handlePostRequest(HttpServerExchange exchange, String requestBody) {
        Order order = parseRequestBody(requestBody);

        String jsonResponse = ATMResponseParser.parseResponse(order);

        this.sendResponse(exchange, jsonResponse);
    }

    private Order parseRequestBody(String requestBody) {
        ServiceTasks serviceTasks = ATMRequestParser.parseRequest(requestBody);

        return ATMService.getOrderFromServiceTasks(serviceTasks);
    }

    private void sendResponse(HttpServerExchange exchange, String response){
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(response);
    }
}
