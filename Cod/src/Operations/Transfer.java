package Operations;

import java.util.Objects;

public class Transfer extends Transaction{

    public Transfer()
    {
        super();
    }

    public Transfer(double value) {
        super(value);
    }

    public double withdraw(double value1) {
        return this.value-=value1;
    }

    public double deposit(double value1) {
        return this.value+=value1;
    }

}
