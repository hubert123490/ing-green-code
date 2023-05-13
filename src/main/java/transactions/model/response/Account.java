package transactions.model.response;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private final String id;
    private Integer debitCount;
    private Integer creditCount;
    private BigDecimal balance;

    public Account(String id, Integer debitCount, Integer creditCount, BigDecimal balance) {
        this.id = id;
        this.debitCount = debitCount;
        this.creditCount = creditCount;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public Integer getDebitCount() {
        return debitCount;
    }

    public void setDebitCount(Integer debitCount) {
        this.debitCount = debitCount;
    }

    public Integer getCreditCount() {
        return creditCount;
    }

    public void setCreditCount(Integer creditCount) {
        this.creditCount = creditCount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account1 = (Account) o;
        return Objects.equals(id, account1.id) && Objects.equals(debitCount, account1.debitCount) && Objects.equals(creditCount, account1.creditCount) && Objects.equals(balance, account1.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, debitCount, creditCount, balance);
    }
}
