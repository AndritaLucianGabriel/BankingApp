package Service.Validations;

import Service.Exceptions.BankAccountException;

public class BankAccountValidation {
    public static void validateIBAN(String IBAN) throws BankAccountException {
        if (!IBAN.matches("^[A-Z]{2}[0-9]{2}[A-Z]{4}[0-9]{16,18}$"))
            throw new BankAccountException("IBAN invalid");
    }

    public static void validateOpeningDate(String openingDate) throws BankAccountException {
        if (!openingDate.matches("^([0123]\\d)[- /.]([01]\\d)[- /.]([012]\\d\\d\\d)$"))
            throw new BankAccountException("Data de deschidere invalida");
    }

    public static void validateClosingDate(String closingDate) throws BankAccountException {
        if (!((closingDate.equals("-")) || closingDate.matches("^([0123]\\d)[- /.]([01]\\d)[- /.]([012]\\d\\d\\d)$")))
            throw new BankAccountException("Data de inchidere invalida");
    }

    public static void validateBalance(double balance) throws BankAccountException {
        if (balance < 0)
            throw new BankAccountException("Balanta invalida");
    }

    public static void validateCurrency(String currency) throws BankAccountException {
        if (!currency.matches("^[a-zA-Z]+$"))
            throw new BankAccountException("Currency invalid");
    }

    public static void validateAnnualInterestRate(double annualInterestRate) throws BankAccountException {
        if (annualInterestRate < 0)
            throw new BankAccountException("Interest rate invalid");
    }

}