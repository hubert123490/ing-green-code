package transactions.model.request;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Transaction {
    private final String debitAccount;
    private final String creditAccount;
    private final BigDecimal amount;

    public Transaction(String debitAccount, String creditAccount, float amount) {
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.amount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(debitAccount, that.debitAccount) && Objects.equals(creditAccount, that.creditAccount) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(debitAccount, creditAccount, amount);
    }
}
