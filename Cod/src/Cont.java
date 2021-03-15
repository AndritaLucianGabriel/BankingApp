import java.util.Objects;

public class Cont {
    public String IBAN;
    public String openingDate;
    public String closingDate;
    public double balance;
    public String currency;

    public Cont()
    {
        this.IBAN = "";
        this.openingDate = "";
        this.closingDate = "";
        this.balance = 0;
        this.currency = "";
    }

    public Cont(String IBAN, String openingDate, String closingDate, double balance, String currency) {
        this.IBAN = IBAN;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.balance = balance;
        this.currency = currency;
    }

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

    public String getCurrence(){
        return this.currency;
    }

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

    public void setCurrency(String currency){
        this.currency = currency;
    }

    @Override
    public boolean equals(Object obj) {
        if(this!=obj)
            return false;
        if(obj==null)
            return false;
        if(this.getClass()!=obj.getClass())
            return false;
        Cont cont=(Cont) obj;
        if(!Objects.equals(this.IBAN,cont.IBAN))
            return false;
        if(!Objects.equals(this.openingDate,cont.openingDate))
            return false;
        if(!Objects.equals(this.closingDate,cont.closingDate))
            return false;
        if(!Objects.equals(this.currency,cont.currency))
            return false;
        if(this.balance!=cont.balance)
            return false;

        return true;
    }

    @Override
    public String toString() {
        StringBuilder c=new StringBuilder();
        c.append("Contul ")
                .append(this.IBAN)
                .append(" a fost deschis in data de ")
                .append(this.openingDate);
                //inlocuieste asta cu clasa cu verificarea
                if(!Objects.equals(this.closingDate,null))
                    c.append(" si a fost inchis in data de ")
                    .append(this.closingDate);
                c.append(", avand suma de ")//nu are sens sa aibe suma daca contu e inchis????
                        .append(this.balance)
                        .append(" ")
                        .append(this.currency);
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.IBAN,this.openingDate,this.closingDate,this.balance,this.currency);
    }
}
