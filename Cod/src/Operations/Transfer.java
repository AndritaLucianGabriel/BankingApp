package Operations;

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

    public double deposit(double value1) {
        return this.value += value1;
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
