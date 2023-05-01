package transactions.logic;

import transactions.model.request.Transaction;
import transactions.model.response.Account;
import transactions.model.response.Accounts;
import transactions.model.request.Transactions;

import java.util.*;

public class TransactionService {
    public static Accounts process(Transactions transactions) {
        Map<String, Account> accountMap = getCalculatedAccounts(transactions);
        PriorityQueue<String> sortedAccountsKeys = getAccountsByAccountName(accountMap);

        List<Account> result = getSortedAccounts(accountMap, sortedAccountsKeys);

        return new Accounts(result);
    }

    private static List<Account> getSortedAccounts(Map<String, Account> accountMap, PriorityQueue<String> sortedAccountsKeys) {
        List<Account> accounts = new ArrayList<>();

        for(String key : sortedAccountsKeys) {
            accounts.add(accountMap.get(key));
        }

        return accounts;
    }

    private static Map<String, Account> getCalculatedAccounts(Transactions transactions) {
        Map<String, Account> accountMap = new HashMap<>();

        for (Transaction transaction : transactions.getTransactions()) {
            Account debitAccount = accountMap.computeIfAbsent(transaction.getDebitAccount(),
                    k -> new Account(transaction.getDebitAccount(), 0, 0, 0.00));
            Account creditAccount = accountMap.computeIfAbsent(transaction.getCreditAccount(),
                    k -> new Account(transaction.getCreditAccount(), 0, 0, 0.00));

            debitAccount.setBalance(debitAccount.getBalance() - transaction.getAmount());
            debitAccount.setDebitCount(debitAccount.getDebitCount() + 1);

            creditAccount.setBalance(creditAccount.getBalance() + transaction.getAmount());
            creditAccount.setCreditCount(creditAccount.getCreditCount() + 1);
        }

        return accountMap;
    }

    private static PriorityQueue<String> getAccountsByAccountName(Map<String, Account> accountMap) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();

        priorityQueue.addAll(accountMap.keySet());

        return priorityQueue;
    }
}
