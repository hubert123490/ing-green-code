package transactions.parser.json;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import transactions.model.request.Transaction;
import transactions.model.request.Transactions;

public class TransactionsRequestParser {
    public static Transactions parseRequest(String jsonRequest) {
        JSONArray jsonArray = JSONArray.parseArray(jsonRequest);

        List<Transaction> transactions = parseJsonArrayToTransactions(jsonArray);

        return new Transactions(transactions);
    }

    private static List<Transaction> parseJsonArrayToTransactions(JSONArray jsonArray) {
        return jsonArray.stream().map(parseJsonObjectToTransaction).collect(Collectors.toList());
    }

    private static final Function<Object, Transaction> parseJsonObjectToTransaction = obj -> {
        JSONObject jsonObject = (JSONObject) obj;

        String debitAccount = jsonObject.getString("debitAccount");
        String creditAccount = jsonObject.getString("creditAccount");
        float amount = jsonObject.getFloatValue("amount");

        return new Transaction(debitAccount, creditAccount, amount);
    };
}
