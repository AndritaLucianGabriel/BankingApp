package Operations;

import Service.Validations.ToProvidersValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ToProviders extends Transaction {
    ToProvidersValidation toProvidersValidation = new ToProvidersValidation();

    ProviderDB providerDB=new ProviderDB("S.C. ENEL Energie Muntenia S.A.","RO28INGB0001000000003333");
    ProviderDB providerDB1=new ProviderDB("ENGIE Romania S.A.","RO83INGB0001000000000888");
    ProviderDB providerDB2=new ProviderDB("RCS RDS S.A.","RO51INGB0001000000018827");
    ProviderDB providerDB3=new ProviderDB("Apa Nova","RO33BRDE4500501059614500");
    ProviderDB[] array= {providerDB,providerDB1,providerDB2,providerDB3};

    //o apelez in main pt moment in modul asta sa pot sa verific regex-ul
    public void toProvidersValidation()
    {
        for(ProviderDB x: array) {
            toProvidersValidation.validateIBAN(x.IBAN);
            toProvidersValidation.validateName(x.company);
        }
    }

    public ToProviders() {
        super();
    }

    public ToProviders(double value) {
        super(value);
    }

    @Override
    public double paymentUtilities(String IBAN, double value) {
        double val=0;
        for(ProviderDB x: array)
        {
            if(Objects.equals(x.getIBAN(),IBAN))
            {
                x.setBalance(x.getBalance()+value);
                val=this.value-=value;
            }
        }
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
