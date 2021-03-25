package Operations;

import java.util.Objects;

public class ProviderDB {
    protected String company;
    protected String IBAN;
    protected double balance;
    protected String currency;

    public ProviderDB() {
        this.company = "";
        this.IBAN = "";
        this.balance = 0;
        currency = "";
    }

    public ProviderDB(String company, String IBAN, String currency) {
        this.company = company;
        this.IBAN = IBAN;
        this.balance = 0;
        this.currency = currency;
    }

    public ProviderDB(String company, String IBAN, double balance, String currency) {
        this.company = company;
        this.IBAN = IBAN;
        this.balance = balance;
        this.currency = currency;
    }

    public ProviderDB(ProviderDB providerDB) {
        this.company = providerDB.company;
        this.IBAN = providerDB.IBAN;
        this.balance = providerDB.balance;
        this.currency = providerDB.currency;
    }

    //Getteri & Setteri
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this != obj)
            return false;
        ProviderDB providerDB = (ProviderDB) obj;
        if (this.getClass() != obj.getClass())
            return false;
        if (!Objects.equals(this.company, providerDB.company))
            return false;
        if (!Objects.equals(this.IBAN, providerDB.IBAN))
            return false;
        if (this.balance != providerDB.balance)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder c = new StringBuilder();
        c.append("Compania '" + this.company + "' are " + this.balance + " " + this.currency + " in contul " + this.IBAN);
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.company, this.IBAN, this.balance);
    }
}
