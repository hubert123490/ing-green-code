package server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface HttpRESTHandler {
    void handlePost(HttpExchange exchange, String requestBody) throws IOException;
}
