package transactions.model.response;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Accounts {
    private List<Account> accounts = new ArrayList<>();

    public Accounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts accounts1 = (Accounts) o;
        return Objects.equals(accounts, accounts1.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accounts);
    }
}