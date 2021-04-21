package MainClasses;

import Service.Exceptions.CardException;
import Service.Timestamp;
import Service.Validations.CardValidation;

import java.util.Objects;

public class Card {
    protected String cardNumber;
    protected int PIN;
    protected String issueDate;

    public Card() {
        this.cardNumber = "";
        this.PIN = 0;
        this.issueDate = "";
    }

    public Card(String cardNumber, int PIN, String issueDate) throws CardException {
        CardValidation.validateCardNumber(cardNumber);
        CardValidation.validatePin(PIN);
        CardValidation.validateIssueDate(issueDate);

        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.issueDate = issueDate;
    }

    //Getteri & Setteri
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) throws CardException {
        CardValidation.validateCardNumber(cardNumber);
        this.cardNumber = cardNumber;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) throws CardException {
        CardValidation.validatePin(PIN);
        this.PIN = PIN;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) throws CardException {
        CardValidation.validateIssueDate(issueDate);
        this.issueDate = issueDate;
    }

    //Functie ce va ajuta la update-ul fisierelor de intrare
    protected String cardReaderUpdate() {
        Timestamp.timestamp("Card: cardReaderUpdate");
        return this.cardNumber + "," + this.PIN + "," + this.issueDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Card card = (Card) obj;
        if (!Objects.equals(this.cardNumber, card.cardNumber))
            return false;
        if (this.PIN != card.PIN)
            return false;
        if (!Objects.equals(this.issueDate, card.issueDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder c = new StringBuilder();
        c.append("Cardul " + this.cardNumber + " cu pinul " + this.PIN + " a fost emis la data de " + this.issueDate + ".");
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.cardNumber, this.PIN, this.issueDate);
    }
}
