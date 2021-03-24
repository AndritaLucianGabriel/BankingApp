package Service.Validations;

public class ToProvidersValidation {
    public void validateName(String name)
    {
        if(!name.matches("^[^-\\s\\d][a-zA-Z0-9\\s-.]+$"))
            System.out.println("Nume invalid");
    }

    public void validateIBAN(String IBAN){
        if(!IBAN.matches("^[A-Z]{2}[0-9]{2}[A-Z]{4}[0-9]{16,18}$"))
            System.out.println(IBAN.toString());
    }
}
