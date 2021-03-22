import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankAccount {
    protected static int counterBankAccountID;
    protected int BankAccountID;
    protected String IBAN;
    protected String openingDate;
    protected String closingDate;
    protected double balance;
    protected String currency;
    List<Card> cardList;

    public BankAccount()
    {
        counterBankAccountID++;
        this.BankAccountID=counterBankAccountID;
        this.IBAN = "";
        this.openingDate = "";
        this.closingDate = "";
        this.balance = 0;
        this.currency = "";
        this.cardList= new ArrayList<>();
    }

    //Constructor pentru pentru conturi fara carduri
    public BankAccount(String IBAN, String openingDate, String closingDate, double balance, String currency){
        counterBankAccountID++;
        this.BankAccountID=counterBankAccountID;
        this.IBAN = IBAN;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.balance = balance;
        this.currency = currency;
    }

    //Constructor pentru pentru conturi cu carduri
    public BankAccount(String IBAN, String openingDate, String closingDate, double balance, String currency, List<Card> cardList){
        counterBankAccountID++;
        this.BankAccountID=counterBankAccountID;
        this.IBAN = IBAN;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.balance = balance;
        this.currency = currency;
        this.cardList=cardList;
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

    //adaugi card/uri
    //stergi card/uri

    @Override
    public boolean equals(Object obj) {
        if(this!=obj)
            return false;
        if(obj==null)
            return false;
        if(this.getClass()!=obj.getClass())
            return false;
        BankAccount bankAccount =(BankAccount) obj;
        if(this.BankAccountID!= bankAccount.BankAccountID)
            return false;
        if(!Objects.equals(this.IBAN, bankAccount.IBAN))
            return false;
        if(!Objects.equals(this.openingDate, bankAccount.openingDate))
            return false;
        if(!Objects.equals(this.closingDate, bankAccount.closingDate))
            return false;
        if(this.balance!= bankAccount.balance)
            return false;
        if(!Objects.equals(this.currency, bankAccount.currency))
            return false;
        if(!Objects.equals(this.cardList,bankAccount.cardList))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder c=new StringBuilder();
        c.append("["+this.BankAccountID+"]"+" Contul " + this.IBAN + " a fost deschis in data de " + this.openingDate);
        //inlocuieste asta cu clasa cu verificarea
        if(!Objects.equals(this.closingDate,null))//conturile inchise teoretic nu au carduri
            //nu are sens sa aibe suma daca contu e inchis????
            c.append(" si a fost inchis in data de " + this.closingDate + ", avand suma de " + this.balance + " " + this.currency);
        else {
            c.append(", avand suma de " + this.balance + " " + this.currency);
            //CONTINUA DE AICI + vezi afisare la Card
        }
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.IBAN,this.openingDate,this.closingDate,this.balance,this.currency, this.cardList);
    }
}
