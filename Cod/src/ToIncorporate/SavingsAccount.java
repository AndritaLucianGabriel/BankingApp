package ToIncorporate;

import java.util.List;
import java.util.Objects;

//public class SavingsAccount extends BankAccount {
//    protected double annualInterestRate;
//
//    public SavingsAccount(double annualInterestRate) {
//        this.annualInterestRate = annualInterestRate;
//    }
//
//    public SavingsAccount(String IBAN, String openingDate, String closingDate, double balance, String currency, double annualInterestRate) {
//        super(IBAN, openingDate, closingDate, balance, currency);
//        this.annualInterestRate = annualInterestRate;
//    }
//
//    public SavingsAccount(String IBAN, String openingDate, String closingDate, double balance, String currency, List<Card> cardList, double annualInterestRate) {
//        super(IBAN, openingDate, closingDate, balance, currency, cardList);
//        this.annualInterestRate = annualInterestRate;
//    }
//
//    //Getteri & Setteri
//    public double getAnnualInterestRate() {
//        return annualInterestRate;
//    }
//
//    public void setAnnualInterestRate(double annualInterestRate) {
//        this.annualInterestRate = annualInterestRate;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//        if(this!=obj)
//            return false;
//        if(obj==null)
//            return false;
//        SavingsAccount savingsAccount=(SavingsAccount) obj;
//        if(this.annualInterestRate!=savingsAccount.annualInterestRate)
//            return false;
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder c=new StringBuilder();
//        c.append("["+this.BankAccountID+"]"+" Contul " + this.IBAN + " a fost deschis in data de " + this.openingDate);
//        //inlocuieste asta cu clasa cu verificarea
//        if(!Objects.equals(this.closingDate,null))//conturile inchise teoretic nu au carduri
//            //nu are sens sa aibe suma daca contu e inchis????
//            c.append(" si a fost inchis in data de " + this.closingDate + ", avand suma de " + this.balance + " " + this.currency);
//        else {
//            c.append(", avand suma de " + this.balance + " " + this.currency);
//            if(!cardList.isEmpty())
//                for( Card x: this.cardList)
//                {
//                    c.append("\n ~ "+x.toString());
//                }
//        }
//        return c.toString();
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(),this.annualInterestRate);
//    }
//}
