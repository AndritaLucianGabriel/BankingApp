package Operations;

public class Transfer extends Transaction{

    public Transfer()
    {
        super();
    }

    public Transfer(double value) {
        super(value);
    }

    public double withdraw(double value) {
        return this.value-=value;
    }

    @Override
    public double paymentUtilities(String IBAN, double value) {
        return 0;
    }

    public double deposit(double value1) {
        return this.value+=value1;
    }

}
