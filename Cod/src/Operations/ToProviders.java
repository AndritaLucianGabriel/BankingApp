package Operations;

import Service.FormatDouble;
import Service.Validations.ToProvidersValidation;

import java.util.Objects;

public class ToProviders extends Transaction {
    ToProvidersValidation toProvidersValidation = new ToProvidersValidation();

    static ProviderDB providerDB = new ProviderDB("S.C. ENEL Energie Muntenia S.A.", "RO28INGB0001000000003333", "Lei");
    static ProviderDB providerDB1 = new ProviderDB("ENGIE Romania S.A.", "RO83INGB0001000000000888", "Dolari");
    static ProviderDB providerDB2 = new ProviderDB("RCS RDS S.A.", "RO51INGB0001000000018827", "Euro");
    static ProviderDB providerDB3 = new ProviderDB("Apa Nova", "RO33BRDE4500501059614500", "Dolari");
    private ProviderDB[] array = {providerDB, providerDB1, providerDB2, providerDB3};

    //Getteri & Setteri
    public ProviderDB[] getArray() {
        return array;
    }

    public void setArray(ProviderDB[] array) {
        this.array = array;
    }

    public ProviderDB getElemArray(int i) {
        return array[i];
    }

    //O apelez in main pt moment in modul asta sa pot sa verific regex-ul
    public void toProvidersValidation() {
        for (ProviderDB x : array) {
            toProvidersValidation.validateIBAN(x.IBAN);
            toProvidersValidation.validateName(x.company);
        }
    }

    public ToProviders() {
        super();
    }

    public ToProviders(double value, String currency) {
        super(value, currency);
    }

    @Override
    public double paymentUtilities(String IBAN, double value) {
        double val = 0, c = 0;
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(this.array[i].getIBAN(), IBAN)) {
                c++;
                System.out.println(" in contul " + IBAN + " (" + this.array[i].getCompany() + ")" + "\nSold anterior: " + FormatDouble.Format(this.array[i].getBalance()) + " " + this.array[i].currency);
                this.array[i].setBalance(this.array[i].getBalance() + CurrencyExchange.convertTransfer(value, this.array[i].currency, this.currency));
                System.out.println("Sold nou: " + FormatDouble.Format(this.array[i].getBalance()) + " " + this.array[i].currency);
                val = this.value -= value;
                break;
            }
        }
        if (c == 0)
            System.out.println("Nu exista providerul cu IBAN-ul: " + IBAN);
        return val;
    }

    @Override
    public double withdraw(double value) {
        return 0;
    }

    @Override
    public double deposit(double value) {
        return 0;
    }
}