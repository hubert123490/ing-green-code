package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

public abstract class HttpController implements HttpRESTHandler, HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod().toUpperCase();
        String requestBody = this.readRequestBody(exchange);

        switch (requestMethod) {
            case "POST" -> handlePost(exchange, requestBody);
            case "GET" -> handleGet(exchange, requestBody);
            case "PUT" -> handlePut(exchange, requestBody);
            case "PATCH" -> handlePatch(exchange, requestBody);
            case "DELETE" -> handleDelete(exchange, requestBody);
            default -> sendDefaultResponse(exchange);
        }
    }

    @Override
    public void handlePost(HttpExchange exchange, String requestBody) throws IOException {
        exchange.sendResponseHeaders(404, 0);
        exchange.close();
    }

    @Override
    public void handleGet(HttpExchange exchange, String requestBody) throws IOException {
        exchange.sendResponseHeaders(404, 0);
        exchange.close();
    }

    @Override
    public void handlePatch(HttpExchange exchange, String requestBody) throws IOException {
        exchange.sendResponseHeaders(404, 0);
        exchange.close();
    }

    @Override
    public void handlePut(HttpExchange exchange, String requestBody) throws IOException {
        exchange.sendResponseHeaders(404, 0);
        exchange.close();
    }

    @Override
    public void handleDelete(HttpExchange exchange, String requestBody) throws IOException {
        exchange.sendResponseHeaders(404, 0);
        exchange.close();
    }

    private String readRequestBody(HttpExchange exchange) throws IOException {
        InputStream requestBody = exchange.getRequestBody();

        byte[] requestBodyBytes = requestBody.readAllBytes();

        return new String(requestBodyBytes);
    }

    private void sendDefaultResponse(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(404, 0);
        exchange.close();
    }
}
