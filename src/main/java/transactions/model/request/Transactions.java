package transactions.model.request;

import java.util.List;
import java.util.Objects;

public class Transactions {
    private final List<Transaction> transactionList;

    public Transactions(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transactions that = (Transactions) o;
        return Objects.equals(transactionList, that.transactionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionList);
    }
}
