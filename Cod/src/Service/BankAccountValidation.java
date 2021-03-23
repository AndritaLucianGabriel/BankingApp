package Service;

public class BankAccountValidation {
    public boolean validateIBAN(String IBAN){return IBAN.matches("^[A-Z]{2}(?:[ ]?[0-9]){18,20}$");}
    public boolean validateOpeningDate(String openingDate){return openingDate.matches("(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$");}
    public boolean validateClosingDate(String closingDate){return closingDate.matches("(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$");}
    public boolean validateBalance(double balance){return balance>0;}
    public boolean validateCurrency(String currency){return currency.matches("^[a-zA-Z]+$");}
}