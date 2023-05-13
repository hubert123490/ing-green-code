package transactions.model.response;


import java.util.List;
import java.util.Objects;

public class Accounts {
    private final List<Account> accountList;

    public Accounts(List<Account> accountList) {
        this.accountList = accountList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts accounts1 = (Accounts) o;
        return Objects.equals(accountList, accounts1.accountList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountList);
    }
}