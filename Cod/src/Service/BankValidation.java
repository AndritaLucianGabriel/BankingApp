package Service;

public class BankValidation {
    public boolean validateName(String name){return name.matches("^[a-zA-Z_][a-zA-Z0-9_]*$");}
    //Location poate sa fie orice fel
}
