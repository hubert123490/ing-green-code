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

        if ("POST".equals(requestMethod)) {
            handlePost(exchange, requestBody);
        } else {
            sendDefaultResponse(exchange);
        }
    }

    @Override
    public abstract void handlePost(HttpExchange exchange, String requestBody) throws IOException;

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
