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
        this.LoanID=counterLoanID;
        this.value = 0;
        this.currency = "";
        this.detail = "";
        this.date = "";
        this.durationMonths=0;
    }

    public Loan(double value, String currency, String detail, String date, int durationMonths) {
        counterLoanID++;
        this.LoanID=counterLoanID;
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

    //Metode de plata a datoriei - vezi valoare

    @Override
    public boolean equals(Object obj) {
        if(this!=obj)
            return false;
        if(obj==null)
            return false;
        if(this.getClass()!=obj.getClass())
            return false;
        Loan loan= (Loan) obj;
        if(this.LoanID!=loan.LoanID)
            return false;
        if(this.value!=loan.value)
            return false;
        if(!Objects.equals(this.currency,loan.currency))
            return false;
        if(this.detail!=loan.detail)
            return false;
        if(!Objects.equals(this.date,loan.date))
            return false;
        if(!Objects.equals(this.date,loan.date))
            return false;
        if(this.durationMonths!=loan.durationMonths)
            return false;
        return true;
    }

    //Fa asta???
    @Override
    public String toString() {
        StringBuilder c = new StringBuilder();
        c.append("["+this.LoanID+"] Suma = "+ this.value + " "+ this.currency+" in data de "+ this.date+" pe "+ this.durationMonths+" luni ("+this.detail+").\n");
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.LoanID, this.value, this.currency, this.detail, this.date, this.durationMonths);
    }

}
