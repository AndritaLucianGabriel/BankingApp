import java.util.Objects;

public class Card {
    protected String cardNumber;
    protected int PIN;
    protected String issueDate;

    public Card()
    {
        this.cardNumber = "";
        this.PIN = 0;
        this.issueDate = "";
    }

    public Card(String cardNumber, int PIN, String issueDate) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.issueDate = issueDate;
    }

    //Getteri & Setteri
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public boolean equals(Object obj) {
        if(this!=obj)
            return false;
        if(obj==null)
            return false;
        if(this.getClass()!=obj.getClass())
            return false;
        Card card= (Card) obj;
        if(!Objects.equals(this.cardNumber, this.cardNumber))
            return false;
        if(this.PIN!=card.PIN)
            return false;
        if(!Objects.equals(this.issueDate, this.issueDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder c= new StringBuilder();
        c.append("Cardul " + this.cardNumber + " cu pinul " + this.PIN + " a fost emis la data de " + this.issueDate);
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.cardNumber, this.PIN, this.issueDate);
    }
}
