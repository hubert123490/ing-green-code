import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import atmservice.logic.JsonParser;
import atmservice.logic.ATMService;

import java.io.*;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new JsonHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server running on port 8080");

    }

    static class JsonHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String requestMethod = exchange.getRequestMethod();
            if (requestMethod.equalsIgnoreCase("POST") && exchange.getRequestURI().toString().equals("/atms/calculateOrder")) {
                // Read the request body
                InputStream requestBody = exchange.getRequestBody();
                byte[] requestBodyBytes = requestBody.readAllBytes();
                String requestBodyString = new String(requestBodyBytes);

                var parsedReq = JsonParser.parseRequest(requestBodyString);
                var result = ATMService.process(parsedReq);


                // Send a response
                String response = JsonParser.parseResponse(result);
                exchange.sendResponseHeaders(200, response.length());
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(response.getBytes());
                outputStream.close();
            } else {
                exchange.sendResponseHeaders(500, 0);
                exchange.close();
            }
        }
    }
}
