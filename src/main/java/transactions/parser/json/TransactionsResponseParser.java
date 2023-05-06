package transactions.parser.json;

import transactions.model.response.Account;
import transactions.model.response.Accounts;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.util.function.Function;

public class TransactionsResponseParser {
    public static String parseResponse(Accounts accounts) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        accounts.getAccounts().stream().map(parseTransactionToJsonObject).forEach(jsonValue -> arrayBuilder.add(jsonValue.asJsonObject()));

        return arrayBuilder.build().toString();
    }

    private static final Function<Account, JsonValue> parseTransactionToJsonObject = account -> {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("account", account.getAccount());
        objectBuilder.add("debitCount", account.getDebitCount());
        objectBuilder.add("creditCount", account.getCreditCount());
        objectBuilder.add("balance", account.getBalance());

        return objectBuilder.build();
    };
}
