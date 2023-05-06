package transactions.parser.json;

import org.junit.Assert;
import org.junit.Test;
import transactions.model.response.Account;
import transactions.model.response.Accounts;

import java.math.BigDecimal;
import java.util.List;

public class TransactionsResponseParserTest {

    @Test
    public void parseResponse() {
        // given
        Accounts accounts = new Accounts(List.of(
                new Account("06105023389842834748547303", 0, 1, new BigDecimal("10.90")),
                new Account("31074318698137062235845814", 1, 0, new BigDecimal("-200.90")),
                new Account("32309111922661937852684864", 1, 1, new BigDecimal("39.20")),
                new Account("66105036543749403346524547", 1, 1, new BigDecimal("150.80"))
        ));
        String expectedResult = "[{\"account\":\"06105023389842834748547303\",\"debitCount\":0,\"creditCount\":1,\"balance\":10.90},{\"account\":\"31074318698137062235845814\",\"debitCount\":1,\"creditCount\":0,\"balance\":-200.90},{\"account\":\"32309111922661937852684864\",\"debitCount\":1,\"creditCount\":1,\"balance\":39.20},{\"account\":\"66105036543749403346524547\",\"debitCount\":1,\"creditCount\":1,\"balance\":150.80}]";

        // when
        String result = TransactionsResponseParser.parseResponse(accounts);

        // then
        Assert.assertEquals(expectedResult, result);
    }
}