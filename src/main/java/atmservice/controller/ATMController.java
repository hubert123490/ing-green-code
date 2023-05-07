package atmservice.controller;

import atmservice.logic.ATMService;
import atmservice.model.request.ServiceTasks;
import atmservice.model.response.Order;
import atmservice.parser.json.ATMRequestParser;
import atmservice.parser.json.ATMResponseParser;
import com.sun.net.httpserver.HttpExchange;
import server.HttpController;

import java.io.IOException;
import java.io.OutputStream;

public class ATMController extends HttpController {

    @Override
    public void handlePost(HttpExchange exchange, String requestBody) throws IOException {
        Order order = parseRequestBody(requestBody);

        String jsonResponse = ATMResponseParser.parseResponse(order);

        this.sendResponse(exchange, jsonResponse);
    }

    private Order parseRequestBody(String requestBody) {
        ServiceTasks serviceTasks = ATMRequestParser.parseRequest(requestBody);

        return ATMService.getOrderFromServiceTasks(serviceTasks);
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(response.getBytes());
        }
    }
}
