package mainClasses;

import service.CurrencyExchange;
import operations.ToProviders;
import operations.Transaction;
import operations.Transfer;
import service.exceptions.BankAccountException;
import service.exceptions.ProviderDBException;
import service.exceptions.TransactionException;
import service.Timestamp;
import service.FormatDouble;
import service.validations.BankAccountValidation;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class BankAccount {
    protected static int counterBankAccountID;
    protected int BankAccountID;
    protected String IBAN;
    protected String openingDate;
    protected String closingDate;
    protected double balance;
    protected String currency;
    protected List<Card> cardList;
    protected Transaction transaction;

    public BankAccount() {
        counterBankAccountID++;
        this.BankAccountID = counterBankAccountID;
        this.IBAN = "";
        this.openingDate = "";
        this.closingDate = "";
        this.balance = 0;
        this.currency = "";
        this.cardList = new ArrayList<>();
    }

    //Constructor pentru pentru conturi fara carduri
    public BankAccount(String IBAN, String openingDate, String closingDate, double balance, String currency) throws BankAccountException {
        BankAccountValidation.validateIBAN(IBAN);
        BankAccountValidation.validateOpeningDate(openingDate);
        BankAccountValidation.validateClosingDate(closingDate);
        BankAccountValidation.validateBalance(balance);
        BankAccountValidation.validateCurrency(currency);

        counterBankAccountID++;
        this.BankAccountID = counterBankAccountID;
        this.IBAN = IBAN;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.balance = balance;
        this.currency = currency;
        this.cardList = new ArrayList<>();
    }

    //Constructor pentru pentru conturi cu carduri
    public BankAccount(String IBAN, String openingDate, String closingDate, double balance, String currency, List<Card> cardList) throws BankAccountException {
        BankAccountValidation.validateIBAN(IBAN);
        BankAccountValidation.validateOpeningDate(openingDate);
        BankAccountValidation.validateClosingDate(closingDate);
        BankAccountValidation.validateBalance(balance);
        BankAccountValidation.validateCurrency(currency);

        counterBankAccountID++;
        this.BankAccountID = counterBankAccountID;
        this.IBAN = IBAN;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.balance = balance;
        this.currency = currency;
        this.cardList = cardList;
    }

    //Getteri & Setteri
    public static int getCounterBankAccountID() {
        return counterBankAccountID;
    }

    public static void setCounterBankAccountID(int counterBankAccountID) {
        BankAccount.counterBankAccountID = counterBankAccountID;
    }

    public int getBankAccountID() {
        return BankAccountID;
    }

    public void setBankAccountID(int bankAccountID) {
        BankAccountID = bankAccountID;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) throws BankAccountException {
        BankAccountValidation.validateIBAN(IBAN);
        this.IBAN = IBAN;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) throws BankAccountException {
        BankAccountValidation.validateOpeningDate(openingDate);
        this.openingDate = openingDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) throws BankAccountException {
        BankAccountValidation.validateClosingDate(closingDate);
        this.closingDate = closingDate;
    }

    public double getBalance() {
        return FormatDouble.format(balance);
    }

    public void setBalance(double balance) throws BankAccountException {
        BankAccountValidation.validateBalance(balance);
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) throws BankAccountException {
        BankAccountValidation.validateCurrency(currency);
        this.currency = currency;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public abstract void setAnnualInterestRate(double annualInterestRate) throws BankAccountException;

    //Functii manipulare carduri
    protected void addCard(Card card) {
        Timestamp.timestamp("BankAccount: addCard");
        if (cardList.contains(card))
            System.out.println("Cardul " + card.cardNumber + "exista deja.");
        else
            cardList.add(card);
    }

    protected void removeCard(Card card) {
        Timestamp.timestamp("BankAccount: removeCard");
        if (!cardList.contains(card))
            System.out.println("Cardul " + card.cardNumber + " nu exista.");
        else
            cardList.remove(card);
    }

    //Functii manipulare sold
    protected void withdraw(double value) throws TransactionException {
        Timestamp.timestamp("BankAccount: withdraw");
        Transaction transaction = new Transfer(this.IBAN, this.balance, this.currency);
        this.balance = transaction.withdraw(value);
    }

    protected void deposit(double value) throws TransactionException {
        Timestamp.timestamp("BankAccount: deposit");
        Transaction transaction = new Transfer(this.IBAN, this.balance, this.currency);
        this.balance = transaction.deposit(value);
    }

    protected void paymentUtilies(String IBAN, double value) throws ProviderDBException, TransactionException {
        Timestamp.timestamp("BankAccount: paymentUtilies");
        System.out.print(" a virat " + FormatDouble.format(value) + " " + this.currency + " din contul " + this.IBAN);
        this.transaction = ToProviders.getInstance(this.IBAN, this.balance, this.currency);
        this.balance = transaction.paymentUtilities(IBAN, value);
    }

    //Functie de schimbare a valutei
    protected void currencyExchange(String wantedCurrency) {
        Timestamp.timestamp("BankAccount: currencyExchange");
        Pair<Double, String> doubleStringPair = CurrencyExchange.exchangeBankAccount(this.balance, this.currency, wantedCurrency);
        this.balance = doubleStringPair.getKey();
        this.currency = doubleStringPair.getValue();
    }

    //Functie ce va ajuta la update-ul fisierelor de intrare
    protected abstract String bankAccountReaderUpdate();

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (this.getClass() != obj.getClass())
            return false;
        BankAccount bankAccount = (BankAccount) obj;
        if (this.BankAccountID != bankAccount.BankAccountID)
            return false;
        if (!Objects.equals(this.IBAN, bankAccount.IBAN))
            return false;
        if (!Objects.equals(this.openingDate, bankAccount.openingDate))
            return false;
        if (!Objects.equals(this.closingDate, bankAccount.closingDate))
            return false;
        if (this.balance != bankAccount.balance)
            return false;
        if (!Objects.equals(this.currency, bankAccount.currency))
            return false;
        return Objects.equals(this.cardList, bankAccount.cardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.IBAN, this.openingDate, this.closingDate, this.balance, this.currency, this.cardList);
    }
}