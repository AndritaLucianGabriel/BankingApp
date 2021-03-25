package Service.Validations;

public class BankAccountValidation {
    public void validateIBAN(String IBAN) {
        if (!IBAN.matches("^[A-Z]{2}[0-9]{2}[A-Z]{4}[0-9]{16,18}$"))
            System.out.println("IBAN invalid");
    }

    public void validateOpeningDate(String openingDate) {
        if (!openingDate.matches("^([0123]\\d)[- /.]([01]\\d)[- /.]([012]\\d\\d\\d)$"))
            System.out.println("Data de deschidere invalida");
    }

    //nu am gasit cum sa introduc null in regex :(
    public void validateClosingDate(String closingDate) {
        if (!((closingDate == null) || closingDate.matches("^([0123]\\d)[- /.]([01]\\d)[- /.]([012]\\d\\d\\d)$")))
            System.out.println("Data de inchidere invalida");
    }

    public void validateBalance(double balance) {
        if (balance < 0)
            System.out.println("Balanta invalida");
    }

    public void validateCurrency(String currency) {
        if (!currency.matches("^[a-zA-Z]+$"))
            System.out.println("Currency invalid");
    }
}