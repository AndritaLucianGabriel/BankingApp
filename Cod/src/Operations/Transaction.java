package Operations;

import Service.AsigUUID;
import Service.Exceptions.ProviderDBException;
import Service.Exceptions.TransactionException;
import Service.FormatDouble;
import Service.Validations.TransactionValidation;

import java.time.LocalDate;

public abstract class Transaction implements AsigUUID {
    protected String transactionID;
    protected String IBAN;
    protected LocalDate timestamp;
    protected double value;
    protected double tradeValue;
    protected String currency;

    public Transaction() {
        this.transactionID = generateId();
        this.IBAN = "";
        this.timestamp = LocalDate.now();
        this.value = 0;
        this.tradeValue = 0;
        this.currency = "";
    }

    public Transaction(String IBAN, double value, String currency) throws TransactionException {
        TransactionValidation.validateIBAN(IBAN);
        TransactionValidation.validateCurrency(currency);
        this.transactionID = generateId();
        this.IBAN = IBAN;
        this.timestamp = LocalDate.now();
        this.value = value;
        this.tradeValue = 0;
        this.currency = currency;
    }

    //Getteri & Setteri
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate x) {
        this.timestamp = x;
    }

    public double getValue() {
        return FormatDouble.format(value);
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getTradeValue() {
        return tradeValue;
    }

    public void setTradeValue(double tradeValue) throws TransactionException {
        TransactionValidation.validateValue(value);
        this.tradeValue = tradeValue;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) throws TransactionException {
        TransactionValidation.validateCurrency(currency);
        this.currency = currency;
    }

    //Metode abstracte
    public abstract double withdraw(double value);

    public abstract double paymentUtilities(String IBAN, double value) throws ProviderDBException;

    public abstract double deposit(double value);

    @Override
    public String toString() {
        return transactionID +
                ", " + timestamp +
                ", " + tradeValue +
                ", " + currency + "\n";
    }
}
