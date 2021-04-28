package operations;

import service.exceptions.ProviderDBException;
import service.Timestamp;
import service.FormatDouble;
import service.validations.ProviderDBValidation;

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
        this.currency = "";
    }

    public ProviderDB(String company, String IBAN, String currency) throws ProviderDBException {
        ProviderDBValidation.validateCompany(company);
        ProviderDBValidation.validateIBAN(IBAN);
        ProviderDBValidation.validateCurrency(currency);

        this.company = company;
        this.IBAN = IBAN;
        this.balance = 0;
        this.currency = currency;
    }

    public ProviderDB(String company, String IBAN, double balance, String currency) throws ProviderDBException {
        ProviderDBValidation.validateCompany(company);
        ProviderDBValidation.validateIBAN(IBAN);
        ProviderDBValidation.validateBalance(balance);
        ProviderDBValidation.validateCurrency(currency);

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

    public void setCompany(String company) throws ProviderDBException {
        ProviderDBValidation.validateCompany(company);
        this.company = company;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) throws ProviderDBException {
        ProviderDBValidation.validateIBAN(IBAN);
        this.IBAN = IBAN;
    }

    public double getBalance() {
        return FormatDouble.format(balance);
    }

    public void setBalance(double balance) throws ProviderDBException {
        ProviderDBValidation.validateBalance(balance);
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) throws ProviderDBException {
        ProviderDBValidation.validateCurrency(currency);
        this.currency = currency;
    }

    //Functi ce va face update-ul fisierelor de intrare
    protected String providerDBReaderUpdate() {
        Timestamp.timestamp("ProviderDB,providerDBReaderUpdate");
        return this.company + "," + this.IBAN + "," + FormatDouble.format(this.balance) + "," + this.currency;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        ProviderDB providerDB = (ProviderDB) obj;
        if (this.getClass() != obj.getClass())
            return false;
        if (!Objects.equals(this.company, providerDB.company))
            return false;
        if (!Objects.equals(this.IBAN, providerDB.IBAN))
            return false;
        return this.balance == providerDB.balance;
    }

    @Override
    public String toString() {
        StringBuilder c;
        c = new StringBuilder();
        c.append("Compania '").append(this.company).append("' are ").append(FormatDouble.format(this.balance)).append(" ").append(this.currency).append(" in contul ").append(this.IBAN);
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.company, this.IBAN, this.balance);
    }
}
