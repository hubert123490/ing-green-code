package transactions.parser.json;

import java.util.function.Function;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import transactions.model.response.Account;
import transactions.model.response.Accounts;

public class TransactionsResponseParser {

    private TransactionsResponseParser() {}
    public static String parseResponse(Accounts accounts) {
        JSONArray jsonArray = new JSONArray();

        accounts.getAccountNames().stream().map(parseTransactionToJsonObject).forEach(jsonArray::add);

        return jsonArray.toJSONString();
    }

    private static final Function<Account, JSONObject> parseTransactionToJsonObject = account -> {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("account", account.getAccountName());
        jsonObject.put("debitCount", account.getDebitCount());
        jsonObject.put("creditCount", account.getCreditCount());
        jsonObject.put("balance", account.getBalance());

        return jsonObject;
    };
}
