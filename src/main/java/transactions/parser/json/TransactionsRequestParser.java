package transactions.parser.json;

import transactions.model.request.Transaction;
import transactions.model.request.Transactions;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionsRequestParser {
    public static Transactions parseRequest(String jsonRequest) {
        JSONArray jsonArray = (JSONArray) JSONValue.parse(jsonRequest);

        List<Transaction> transactions = parseJsonArrayToTransactions(jsonArray);

        return new Transactions(transactions);
    }

    private static List<Transaction> parseJsonArrayToTransactions(JSONArray jsonArray) {
        return jsonArray.stream().map(parseJsonObjectToTransaction).collect(Collectors.toList());
    }

    private static final Function<Object, Transaction> parseJsonObjectToTransaction = obj -> {
        JSONObject jsonObject = (JSONObject) obj;

        String debitAccount = jsonObject.get("debitAccount").toString();
        String creditAccount = jsonObject.get("creditAccount").toString();
        float amount = Float.parseFloat(jsonObject.get("amount").toString());

        return new Transaction(debitAccount, creditAccount, amount);
    };
}
