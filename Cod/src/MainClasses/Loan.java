package MainClasses;

import Service.FormatDouble;

import java.util.Objects;
// 3 dto
public class Loan {
    protected static int counterLoanID;
    protected int LoanID;
    protected double value; // era mai indicat sa folosesti un wrapper (BigDecimal for example) pentru precizie
    protected String currency;
    protected String detail;
    protected String date;
    protected int durationMonths;

    public Loan() {
        counterLoanID++;
        this.LoanID = counterLoanID;
        this.value = 0;
        this.currency = "";
        this.detail = "";
        this.date = "";
        this.durationMonths = 0;
    }

    public Loan(double value, String currency, String detail, String date, int durationMonths) {
        counterLoanID++;
        this.LoanID = counterLoanID;
        this.value = value;
        this.currency = currency;
        this.detail = detail;
        this.date = date;
        this.durationMonths = durationMonths;
    }

    //Getteri & Setteri
    public static int getCounterLoanID() {
        return counterLoanID;
    }

    public static void setCounterLoanID(int counterLoanID) {
        Loan.counterLoanID = counterLoanID;
    }

    public int getLoanID() {
        return LoanID;
    }

    public void setLoanID(int loanID) {
        LoanID = loanID;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    @Override
    public boolean equals(Object obj) {
        if (this != obj) // compari referintele obiectelor
            return false; // metoda asta de equals ar trebui sa ti dea false din prima daca compari loanRef1 cu loanRef2 chiar daca au aceleasi valori, pentru ca sunt 2 referinte diferite
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Loan loan = (Loan) obj;
        if (this.LoanID != loan.LoanID)
            return false;
        if (this.value != loan.value)
            return false;
        if (!Objects.equals(this.currency, loan.currency))
            return false;
        if (this.detail != loan.detail) // compari referintele celor 2 Stringuri, nu valorile. trebuie sa folosesti equals
            return false;
        if (!Objects.equals(this.date, loan.date))
            return false;
        if (!Objects.equals(this.date, loan.date)) // ai verificat deja asta
            return false;
        if (this.durationMonths != loan.durationMonths)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder c = new StringBuilder();
        c.append("[" + this.LoanID + "] Suma = " + FormatDouble.Format(this.value) + " " + this.currency + " in data de " + this.date + " pe " + this.durationMonths + " luni (" + this.detail + ")");
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.LoanID, this.value, this.currency, this.detail, this.date, this.durationMonths);
    }
}
