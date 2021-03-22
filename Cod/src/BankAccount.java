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
    
    public BankAccount()
    {
        counterBankAccountID++;
        this.BankAccountID=counterBankAccountID;
        this.IBAN = "";
        this.openingDate = "";
        this.closingDate = "";
        this.balance = 0;
        this.currency = "";
    }

    public BankAccount(String IBAN, String openingDate, String closingDate, double balance, String currency) {
        counterBankAccountID++;
        this.BankAccountID=counterBankAccountID;
        this.IBAN = IBAN;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.balance = balance;
        this.currency = currency;
    }

    //Getteri
    public static int getCounterBankAccountID() { return counterBankAccountID; }

    public int getBankAccountID() { return BankAccountID; }

    public String getIBAN() {
        return IBAN;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency(){
        return this.currency;
    }

    //Setteri
    public static void setCounterBankAccountID(int counterBankAccountID) { BankAccount.counterBankAccountID = counterBankAccountID; }

    public void setBankAccountID(int bankAccountID) { BankAccountID = bankAccountID; }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCurrency(String currency){ this.currency = currency; }

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

        return true;
    }

    @Override
    public String toString() {
        StringBuilder c=new StringBuilder();
        c.append("["+this.BankAccountID+"]"+" Contul " + this.IBAN + " a fost deschis in data de " + this.openingDate);
        //inlocuieste asta cu clasa cu verificarea
        if(!Objects.equals(this.closingDate,null))
            //nu are sens sa aibe suma daca contu e inchis????
            c.append(" si a fost inchis in data de " + this.closingDate + ", avand suma de " + this.balance + " " + this.currency);
        else
            c.append(", avand suma de " + this.balance + " " + this.currency);
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.IBAN,this.openingDate,this.closingDate,this.balance,this.currency);
    }
}
