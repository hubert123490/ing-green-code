package transactions.logic;

import transactions.model.request.Transaction;
import transactions.model.response.Account;
import transactions.model.response.Accounts;
import transactions.model.request.Transactions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class TransactionsService {
    private TransactionsService() {}

    public static Accounts getAccountsFromTransactions(Transactions transactions) {
        Map<String, Account> accountMap = getCalculatedAccounts(transactions);
        PriorityQueue<String> sortedAccountsKeys = getAccountsByAccountName(accountMap);

        List<Account> result = sortedAccountsKeys.parallelStream()
                .map(accountMap::get)
                .toList();

        return new Accounts(result);
    }

    private static Map<String, Account> getCalculatedAccounts(Transactions transactions) {
        Map<String, Account> accountMap = new HashMap<>();

        for (Transaction transaction : transactions.getTransactionList()) {
            updateAccount(accountMap, transaction.getDebitAccount(), transaction.getAmount().negate(), true);
            updateAccount(accountMap, transaction.getCreditAccount(), transaction.getAmount(), false);
        }

        return accountMap;
    }

    private static void updateAccount(Map<String, Account> accountMap, String accountName, BigDecimal amount, boolean isDebit) {
        Account account = accountMap.computeIfAbsent(accountName,
                k -> new Account(accountName, 0, 0, BigDecimal.valueOf(0.00f)));

        account.setBalance(account.getBalance().add(amount).setScale(2, RoundingMode.HALF_UP));
        if (isDebit) {
            account.setDebitCount(account.getDebitCount() + 1);
        } else {
            account.setCreditCount(account.getCreditCount() + 1);
        }
    }

    private static PriorityQueue<String> getAccountsByAccountName(Map<String, Account> accountMap) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();

        priorityQueue.addAll(accountMap.keySet());

        return priorityQueue;
    }
}
