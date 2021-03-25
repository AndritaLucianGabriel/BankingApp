import Operations.CurrencyExchange;
import Operations.ToProviders;
import Operations.Transaction;
import Operations.Transfer;
import Service.FormatDouble;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankAccount {
    protected static int counterBankAccountID;
    protected int BankAccountID;
    protected String IBAN;
    private String openingDate;
    private String closingDate;
    protected double balance;
    protected String currency;
    List<Card> cardList;
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
    public BankAccount(String IBAN, String openingDate, String closingDate, double balance, String currency) {
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
    public BankAccount(String IBAN, String openingDate, String closingDate, double balance, String currency, List<Card> cardList) {
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

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
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

    //Functii manipulare carduri
    public void addCard(Card card) {
        if (cardList.contains(card))
            System.out.println("Cardul " + card.cardNumber + "exista deja.");
        else
            cardList.add(card);
    }

    public void removeCard(Card card) {
        if (!cardList.contains(card))
            System.out.println("Cardul " + card.cardNumber + " nu exista.");
        else
            cardList.remove(card);
    }

    //Functii manipulare sold
    public void withdraw(double value) {
        Transaction transaction = new Transfer(this.balance, this.currency);
        this.balance = transaction.withdraw(value);
    }

    public void deposit(double value) {
        Transaction transaction = new Transfer(this.balance, this.currency);
        this.balance = transaction.deposit(value);
    }

    public void paymentUtilies(String IBAN, double value) {
        System.out.print(" a virat " + value + " " + this.currency + " din contul " + this.IBAN);
        this.transaction = new ToProviders(this.balance, this.currency);
        this.balance = transaction.paymentUtilities(IBAN, value);
    }

    public void currencyExchange(String wantedCurrency) {
        Pair<Double, String> doubleStringPair = CurrencyExchange.exchangeBankAccount(this.balance, this.currency, wantedCurrency);
        this.balance = doubleStringPair.getKey();
        this.currency = doubleStringPair.getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (this != obj)
            return false;
        if (obj == null)
            return false;
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
        if (!Objects.equals(this.cardList, bankAccount.cardList))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder c = new StringBuilder();
        c.append("[" + this.BankAccountID + "]" + " Contul " + this.IBAN + " a fost deschis in data de " + this.openingDate);
        //inlocuieste asta cu clasa cu verificarea
        if (!Objects.equals(this.closingDate, null))//conturile inchise teoretic nu au carduri
            //nu are sens sa aibe suma daca contu e inchis????
            c.append(" si a fost inchis in data de " + this.closingDate + ", avand suma de " + FormatDouble.Format(this.balance) + " " + this.currency);
        else {
            c.append(", avand suma de " + FormatDouble.Format(this.balance) + " " + this.currency);
            if (!cardList.isEmpty())
                for (Card x : this.cardList) {
                    c.append("\n ~ " + x.toString());
                }
        }
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.IBAN, this.openingDate, this.closingDate, this.balance, this.currency, this.cardList);
    }
}
