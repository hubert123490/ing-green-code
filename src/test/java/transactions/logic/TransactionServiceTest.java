package transactions.logic;

import org.junit.Assert;
import org.junit.Test;
import transactions.model.request.Transaction;
import transactions.model.request.Transactions;
import transactions.model.response.Account;
import transactions.model.response.Accounts;

import java.util.List;


public class TransactionServiceTest {

    @Test
    public void testCase1() {
        // given
        Transactions transactions = new Transactions(List.of(
                new Transaction("32309111922661937852684864", "06105023389842834748547303", 10.90),
                new Transaction("31074318698137062235845814", "66105036543749403346524547", 200.90),
                new Transaction("66105036543749403346524547", "32309111922661937852684864", 50.10)
        ));
        Accounts expectedResult = new Accounts(List.of(
                new Account("06105023389842834748547303", 0, 1, 10.90),
                new Account("31074318698137062235845814", 1, 0, -200.90),
                new Account("32309111922661937852684864", 1, 1, 39.20),
                new Account("66105036543749403346524547", 1, 1, 150.80)
        ));

        // when
        Accounts result = TransactionService.process(transactions);

        // then
        Assert.assertEquals(expectedResult, result);
    }
}