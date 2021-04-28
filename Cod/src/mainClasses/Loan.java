package mainClasses;

import service.exceptions.LoanException;
import service.Timestamp;
import service.FormatDouble;
import service.validations.LoanValidation;

import java.util.Objects;

public class Loan {
    protected static int counterLoanID;
    protected int LoanID;
    protected double value;
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

    public Loan(double value, String currency, String detail, String date, int durationMonths) throws LoanException {
        LoanValidation.validateValue(value);
        LoanValidation.validateCurrency(currency);
        LoanValidation.validateDate(date);
        LoanValidation.validateDurationMonths(durationMonths);

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
        return FormatDouble.format(value);
    }

    public void setValue(double value) throws LoanException {
        LoanValidation.validateValue(value);
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) throws LoanException {
        LoanValidation.validateCurrency(currency);
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

    public void setDate(String date) throws LoanException {
        LoanValidation.validateDate(date);
        this.date = date;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) throws LoanException {
        LoanValidation.validateDurationMonths(durationMonths);
        this.durationMonths = durationMonths;
    }

    //Functie ce returneaza valoarea ratei lunare
    protected double valueMonthlyRate() {
        Timestamp.timestamp("Loan,valueMonthlyRate");
        return this.value / this.durationMonths;
    }

    //Plata unei sume > decat rata lunara
    protected void payMonthlyRate(double value) {
        Timestamp.timestamp("Loan,payMonthlyRate");
        if (value >= this.valueMonthlyRate()) {
            double oldRate = valueMonthlyRate();
            this.value = this.value - value;
            double newRate = valueMonthlyRate();
            this.durationMonths--;
            System.out.println(" a platit " + FormatDouble.format(value) + " " + this.currency + ".\n" + " ~Update rata: " + FormatDouble.format(oldRate) + " -> " + FormatDouble.format(newRate) + "\n ~" + FormatDouble.format(this.value) + " " + this.currency + " ramasi pentru " + this.durationMonths + " de luni");
        } else
            System.out.println(" trebuie sa introduca o suma mai mare decat " + FormatDouble.format(this.valueMonthlyRate()) + " pentru a plati minimul ratei.");
    }

    //Plata ratei fixe
    protected void payMonthlyRate() {
        Timestamp.timestamp("Loan,payMonthlyRate");
        this.value = this.value - valueMonthlyRate();
        this.durationMonths--;
        System.out.println(" si-a platit cu succes rata in valoare de " + FormatDouble.format(this.valueMonthlyRate()) + " " + this.currency + ". " + FormatDouble.format(this.value) + " " + this.currency + " ramasi pentru " + this.durationMonths + " de luni");
    }

    //Functi ce va face update-ul fisierelor de intrare
    protected String loanReaderUpdate() {
        Timestamp.timestamp("Loan,loanReaderUpdate");
        return FormatDouble.format(this.value) + "," + this.currency + "," + this.detail + "," + this.date + "," + this.durationMonths;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (this.getClass() != obj.getClass())
            return false;
        Loan loan = (Loan) obj;
        if (this.LoanID != loan.LoanID)
            return false;
        if (this.value != loan.value)
            return false;
        if (!Objects.equals(this.currency, loan.currency))
            return false;
        if (!Objects.equals(this.detail, loan.detail))
            return false;
        if (!Objects.equals(this.date, loan.date))
            return false;
        return this.durationMonths == loan.durationMonths;
    }

    @Override
    public String toString() {
        StringBuilder c;
        c = new StringBuilder();
        c.append("[").append(this.LoanID).append("] Suma = ").append(FormatDouble.format(this.value)).append(" ").append(this.currency).append(" in data de ").append(this.date).append(" pe ").append(this.durationMonths).append(" luni (").append(this.detail).append(")");
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.LoanID, this.value, this.currency, this.detail, this.date, this.durationMonths);
    }
}
