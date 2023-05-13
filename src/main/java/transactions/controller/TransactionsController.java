package transactions.controller;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import server.HttpController;
import transactions.logic.TransactionsService;
import transactions.model.request.Transactions;
import transactions.model.response.Accounts;
import transactions.parser.json.TransactionsRequestParser;
import transactions.parser.json.TransactionsResponseParser;

public class TransactionsController extends HttpController {

    @Override
    public void handlePostRequest(HttpServerExchange exchange, String requestBody) {
        Accounts accounts = parseRequestBody(requestBody);

        String jsonResponse = TransactionsResponseParser.parseResponse(accounts);

        this.sendResponse(exchange, jsonResponse);
    }

    private Accounts parseRequestBody(String requestBody) {
        Transactions transactions = TransactionsRequestParser.parseRequest(requestBody);

        return TransactionsService.getAccountsFromTransactions(transactions);
    }

    private void sendResponse(HttpServerExchange exchange, String response) {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(response);
    }
}
