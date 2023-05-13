package server;

import io.undertow.server.HttpServerExchange;

import java.io.IOException;

public interface HttpRESTHandler {
    void handlePostRequest(HttpServerExchange exchange, String requestBody) throws IOException;
}
