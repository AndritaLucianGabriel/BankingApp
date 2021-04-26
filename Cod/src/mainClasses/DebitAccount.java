package MainClasses;

import Service.Exceptions.BankAccountException;
import Service.Timestamp;
import Service.FormatDouble;

import java.util.List;
import java.util.Objects;

public class DebitAccount extends BankAccount {
    public DebitAccount() {
        super();
    }

    public DebitAccount(String IBAN, String openingDate, String closingDate, double balance, String currency) throws BankAccountException {
        super(IBAN, openingDate, closingDate, balance, currency);
    }

    public DebitAccount(String IBAN, String openingDate, String closingDate, double balance, String currency, List<Card> cardList) throws BankAccountException {
        super(IBAN, openingDate, closingDate, balance, currency, cardList);
    }

    //Functi ce va face update-ul fisierelor de intrare
    protected String bankAccountReaderUpdate() {
        Timestamp.timestamp("DebitAccount: bankAccountReaderUpdate");
        return this.IBAN + "," + this.openingDate + "," + this.closingDate + "," + FormatDouble.format(this.balance) + "," + this.currency;
    }

    @Override
    public void setAnnualInterestRate(double annualInterestRate) throws BankAccountException {
    }

    @Override
    public String toString() {
        StringBuilder c = new StringBuilder();
        c.append("[" + this.BankAccountID + "]" + " Contul de debit " + this.IBAN + " a fost deschis in data de " + this.openingDate);
        if (!(Objects.equals(this.closingDate, null) || Objects.equals(this.closingDate, "-")))
            c.append(" si a fost inchis in data de " + this.closingDate + ", avand suma de " + FormatDouble.format(this.balance) + " " + this.currency);
        else {
            c.append(", avand suma de " + FormatDouble.format(this.balance) + " " + this.currency);
            if (!cardList.isEmpty())
                for (Card x : this.cardList) {
                    c.append("\n ~ " + x.toString());
                }
        }
        return c.toString();
    }
}
