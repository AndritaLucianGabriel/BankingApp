package Operations;

import java.time.LocalDateTime;

//o sa duc informatia intr un fisier pt extras, momentan datele membre nu prea au sens ca doar prelucrez date
public abstract class Transaction {
    protected static int counterTransactionID;
    protected int transactionID;
    //am nevoie de timestamp pentru filtrele ulterioare
    protected LocalDateTime timestamp;
    protected double value;
    protected String currency;

    public Transaction() {
        counterTransactionID++;
        this.transactionID = counterTransactionID;
        this.timestamp = LocalDateTime.now();
        this.value = 0;
        this.currency = "";
    }

    public Transaction(double value, String currency) {
        counterTransactionID++;
        this.transactionID = counterTransactionID;
        this.timestamp = LocalDateTime.now();
        this.value = value;
        this.currency = currency;
    }

    //Getteri & Setteri
    public static int getCounterTransactionID() {
        return counterTransactionID;
    }

    public static void setCounterTransactionID(int counterTransactionID) {
        Transaction.counterTransactionID = counterTransactionID;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime x) {
        this.timestamp = x;
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

    //Metode abstracte
    public abstract double withdraw(double value);

    public abstract double paymentUtilities(String IBAN, double value);

    public abstract double deposit(double value);

    public abstract Object getElemArray(int i);

}
