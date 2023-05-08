package transactions.parser.json;

import java.util.function.Function;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import transactions.model.response.Account;
import transactions.model.response.Accounts;

public class TransactionsResponseParser {
    public static String parseResponse(Accounts accounts) {
        JSONArray jsonArray = new JSONArray();

        accounts.getAccounts().stream().map(parseTransactionToJsonObject).forEach(jsonArray::add);

        return jsonArray.toJSONString();
    }

    private static final Function<Account, JSONObject> parseTransactionToJsonObject = account -> {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("account", account.getAccount());
        jsonObject.put("debitCount", account.getDebitCount());
        jsonObject.put("creditCount", account.getCreditCount());
        jsonObject.put("balance", account.getBalance());

        return jsonObject;
    };
}
