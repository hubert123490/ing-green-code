package server;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.Methods;

import java.io.IOException;

public abstract class HttpController implements HttpRESTHandler, HttpHandler {

    @Override
    public void handleRequest(HttpServerExchange exchange) throws RuntimeException {
        if (exchange.getRequestMethod().equals(Methods.POST)) {
            handlePostRequest(exchange);
        } else {
            sendNonSupportedMethodResponse(exchange);
        }
    }

    private void handlePostRequest(HttpServerExchange exchange) {
        exchange.getRequestReceiver().receiveFullBytes((currExchange, bytes) -> {
            String requestBody = new String(bytes);
            try {
                handlePostRequest(currExchange, requestBody);
            } catch (IOException e) {
                throw new RequestHandlingException("Error handling request", e);
            }
        });
    }

    private void sendNonSupportedMethodResponse(HttpServerExchange exchange) {
        exchange.setStatusCode(404);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        exchange.getResponseSender().send("Not Found");
    }
}
