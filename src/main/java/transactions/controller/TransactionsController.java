package transactions.controller;

import com.sun.net.httpserver.HttpExchange;

import server.HttpController;
import transactions.logic.TransactionsService;
import transactions.model.request.Transactions;
import transactions.model.response.Accounts;
import transactions.parser.json.TransactionsRequestParser;
import transactions.parser.json.TransactionsResponseParser;

import java.io.IOException;
import java.io.OutputStream;

public class TransactionsController extends HttpController {

    @Override
    public void handlePost(HttpExchange exchange, String requestBody) throws IOException {
        Accounts accounts = parseRequestBody(requestBody);

        String jsonResponse = TransactionsResponseParser.parseResponse(accounts);

        this.sendResponse(exchange, jsonResponse);
    }

    private Accounts parseRequestBody(String requestBody) {
        Transactions transactions = TransactionsRequestParser.parseRequest(requestBody);

        return TransactionsService.getAccountsFromTransactions(transactions);
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(response.getBytes());
        }
    }
}
