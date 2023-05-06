package transactions.logic;

import org.junit.Assert;
import org.junit.Test;
import transactions.model.request.Transaction;
import transactions.model.request.Transactions;
import transactions.model.response.Account;
import transactions.model.response.Accounts;

import java.math.BigDecimal;
import java.util.List;

public class TransactionsServiceTest {

    @Test
    public void getAccountsFromTransactionsWorks() {
        // given
        Transactions transactions = new Transactions(List.of(
                new Transaction("32309111922661937852684864", "06105023389842834748547303", 10.90f),
                new Transaction("31074318698137062235845814", "66105036543749403346524547", 200.90f),
                new Transaction("66105036543749403346524547", "32309111922661937852684864", 50.10f)
        ));
        Accounts expectedResult = new Accounts(List.of(
                new Account("06105023389842834748547303", 0, 1, new BigDecimal("10.90")),
                new Account("31074318698137062235845814", 1, 0, new BigDecimal("-200.90")),
                new Account("32309111922661937852684864", 1, 1, new BigDecimal("39.20")),
                new Account("66105036543749403346524547", 1, 1, new BigDecimal("150.80"))
        ));

        // when
        Accounts result = TransactionsService.getAccountsFromTransactions(transactions);

        // then
        Assert.assertEquals(expectedResult, result);
    }
}