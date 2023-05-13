package transactions.model.response;


import java.util.List;
import java.util.Objects;

public class Accounts {
    private final List<Account> accountNames;

    public Accounts(List<Account> accountNames) {
        this.accountNames = accountNames;
    }

    public List<Account> getAccountNames() {
        return accountNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts accounts1 = (Accounts) o;
        return Objects.equals(accountNames, accounts1.accountNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNames);
    }
}