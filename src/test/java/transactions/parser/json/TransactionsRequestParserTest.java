package transactions.parser.json;

import org.junit.Assert;
import org.junit.Test;
import transactions.model.request.Transaction;
import transactions.model.request.Transactions;

import java.util.List;

public class TransactionsRequestParserTest {

    @Test
    public void parseRequest() {
        // given
        String jsonRequest = "[{\"debitAccount\":\"32309111922661937852684864\",\"creditAccount\":\"06105023389842834748547303\",\"amount\":10.90},{\"debitAccount\":\"31074318698137062235845814\",\"creditAccount\":\"66105036543749403346524547\",\"amount\":200.90},{\"debitAccount\":\"66105036543749403346524547\",\"creditAccount\":\"32309111922661937852684864\",\"amount\":50.10}]";
        Transactions expectedResult = new Transactions(
                List.of(
                        new Transaction("32309111922661937852684864", "06105023389842834748547303", 10.90f),
                        new Transaction("31074318698137062235845814", "66105036543749403346524547", 200.90f),
                        new Transaction("66105036543749403346524547", "32309111922661937852684864", 50.10f)
                )
        );

        // when
        Transactions result = TransactionsRequestParser.parseRequest(jsonRequest);

        // then
        Assert.assertEquals(expectedResult, result);
    }
}