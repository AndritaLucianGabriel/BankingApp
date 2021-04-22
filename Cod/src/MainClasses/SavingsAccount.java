package MainClasses;

import Service.CurrencyExchange;
import Service.Exceptions.BankAccountException;
import Service.Exceptions.ProviderDBException;
import Service.Exceptions.TransactionException;
import Service.Timestamp;
import Service.FormatDouble;
import Service.Validations.BankAccountValidation;

import java.util.List;
import java.util.Objects;

//orice fel de retragere de bani din cont, se va pierde 10% din dobanda oferita si se va taxa contul cu 1 euro
public class SavingsAccount extends BankAccount {
    protected double annualInterestRate;

    public SavingsAccount() {
        super();
        annualInterestRate = 0;
    }

    public SavingsAccount(double annualInterestRate) throws BankAccountException {
        super();
        BankAccountValidation.validateAnnualInterestRate(annualInterestRate);
        this.annualInterestRate = annualInterestRate;
    }

    public SavingsAccount(String IBAN, String openingDate, String closingDate, double balance, String currency, double annualInterestRate) throws BankAccountException {
        super(IBAN, openingDate, closingDate, balance, currency);
        BankAccountValidation.validateAnnualInterestRate(annualInterestRate);
        this.annualInterestRate = annualInterestRate;
    }

    public SavingsAccount(String IBAN, String openingDate, String closingDate, double balance, String currency) throws BankAccountException {
        super(IBAN, openingDate, closingDate, balance, currency);
        this.annualInterestRate = 0;
    }

    public SavingsAccount(String IBAN, String openingDate, String closingDate, double balance, String currency, List<MainClasses.Card> cardList, double annualInterestRate) throws BankAccountException {
        super(IBAN, openingDate, closingDate, balance, currency, cardList);
        BankAccountValidation.validateAnnualInterestRate(annualInterestRate);
        this.annualInterestRate = annualInterestRate;
    }

    public SavingsAccount(String IBAN, String openingDate, String closingDate, double balance, String currency, List<MainClasses.Card> cardList) throws BankAccountException {
        super(IBAN, openingDate, closingDate, balance, currency, cardList);
        this.annualInterestRate = 0;
    }

    //Getteri & Setteri
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) throws BankAccountException {
        BankAccountValidation.validateAnnualInterestRate(annualInterestRate);
        this.annualInterestRate = annualInterestRate;
    }

    //Functie care efectueaza penalizarea
    protected void penalty() {
        Timestamp.timestamp("SavingsAccount: penalty");
        System.out.print("\tPenalizare: \n ~Interest: " + FormatDouble.format(this.annualInterestRate) + " -> ");
        this.annualInterestRate = this.annualInterestRate - this.annualInterestRate / 10;
        System.out.print(FormatDouble.format(this.annualInterestRate) + "\n ~Balance (-1 Euro): " + FormatDouble.format(super.balance) + " " + super.currency + " -> ");
        super.balance = super.balance - CurrencyExchange.convertTransferWithoutText(1, super.currency, "Euro");
        System.out.println(FormatDouble.format(super.balance) + " " + super.currency);
    }

    //Functi ce va face update-ul fisierelor de intrare
    protected String bankAccountReaderUpdate() {
        Timestamp.timestamp("SavingsAccount: bankAccountReaderUpdate");
        return this.IBAN + "," + this.openingDate + "," + this.closingDate + "," + FormatDouble.format(this.balance) + "," + this.currency + "," + FormatDouble.format(this.annualInterestRate);
    }

    @Override
    protected void withdraw(double value) throws TransactionException {
        Timestamp.timestamp("SavingsAccount: penalty");
        super.withdraw(value);
        this.penalty();
    }

    @Override
    protected void paymentUtilies(String IBAN, double value) throws ProviderDBException, TransactionException {
        Timestamp.timestamp("SavingsAccount: penalty");
        super.paymentUtilies(IBAN, value);
        this.penalty();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        SavingsAccount savingsAccount = (SavingsAccount) obj;
        if (this.annualInterestRate != savingsAccount.annualInterestRate)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder c = new StringBuilder();
        c.append("[" + this.BankAccountID + "]" + " Contul de economii " + this.IBAN + " a fost deschis in data de " + this.openingDate);
        if (!(Objects.equals(this.closingDate, null) || Objects.equals(this.closingDate, "-")))
            c.append(" si a fost inchis in data de " + this.closingDate + ", avand suma de " + FormatDouble.format(this.balance) + " " + this.currency + ", avand dobanda anuala de " + FormatDouble.format(this.annualInterestRate) + "%");
        else {
            c.append(", avand suma de " + FormatDouble.format(this.balance) + " " + this.currency + ", avand comision anual de " + FormatDouble.format(this.annualInterestRate) + "%");
            if (!cardList.isEmpty())
                for (MainClasses.Card x : this.cardList) {
                    c.append("\n ~ " + x.toString());
                }
        }
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.annualInterestRate);
    }
}