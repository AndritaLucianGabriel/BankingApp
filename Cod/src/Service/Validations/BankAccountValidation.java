package Service.Validations;

public class BankAccountValidation {
    // toate intorc void si printeaza in consola, nu ti opresc executia (cu aruncat de exceptie) sau sa intoarca true/false sa iei tu o decizie
    public void validateIBAN(String IBAN) {
        if (!IBAN.matches("^[A-Z]{2}[0-9]{2}[A-Z]{4}[0-9]{16,18}$"))
            System.out.println("IBAN invalid");
    }

    public void validateOpeningDate(String openingDate) {
        if (!openingDate.matches("^([0123]\\d)[- /.]([01]\\d)[- /.]([012]\\d\\d\\d)$"))
            System.out.println("Data de deschidere invalida");
    }

    public void validateClosingDate(String closingDate) {
        // nu vad rostul sa validezi o data cu regexuri - daca apelez cu 33.15.0123 o sa zica ca e valida
        // am vazut ca ai folosit un LocalDateTime - puteai sa folosesti peste tot LocalDateTime sa te asiguri ca data este valida saau sa parsezi stringul si sa te asiguri ca este valida programatic
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
