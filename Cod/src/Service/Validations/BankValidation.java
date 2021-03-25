package Service.Validations;

public class BankValidation {
    public void validateName(String name) {
        if (!name.matches("^[^-\\s\\d][a-zA-Z0-9\\s-]+$"))
            System.out.println("Nume invalid");
    }
    //Location poate sa fie orice fel
}
