package server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface HttpRESTHandler {
    void handlePost(HttpExchange exchange, String requestBody) throws IOException;
    void handleGet(HttpExchange exchange, String requestBody) throws IOException;
    void handlePatch(HttpExchange exchange, String requestBody) throws IOException;
    void handlePut(HttpExchange exchange, String requestBody) throws IOException;
    void handleDelete(HttpExchange exchange, String requestBody) throws IOException;
}
