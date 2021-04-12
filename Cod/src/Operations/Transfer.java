package Operations;

// nu-mi place cum ai folosit mostenirea. Un transfer si o tranzactie mi se par acelasi lucru. Transfer ar fi putut sa fie o actiune. Ex: transfer o suma de bani unui prieten -> rezulta o tranzactie
public class Transfer extends Transaction {

    public Transfer() {
        super();
    }

    public Transfer(double value, String currency) {
        super(value, currency);
    }

    public double withdraw(double value) {
        return this.value -= value;
    }

    public double deposit(double value) {
        return this.value += value;
    }

    @Override
    public double paymentUtilities(String IBAN, double value) {
        return 0;
    }

    @Override
    public Object getElemArray(int i) {
        return null;
    }
}
