package Service.Validations;

import Service.Exceptions.ProviderDBException;

public class ProviderDBValidation {
    public static void validateCompany(String name) throws ProviderDBException {
        if (!name.matches("^[^-\\s\\d][a-zA-Z0-9\\s-.]+$"))
            throw new ProviderDBException("Nume companie invalid");
    }

    public static void validateIBAN(String IBAN) throws ProviderDBException {
        if (!IBAN.matches("^[A-Z]{2}[0-9]{2}[A-Z]{4}[0-9]{16,18}$"))
            throw new ProviderDBException("IBAN invalid");
    }

    public static void validateBalance(double balance) throws ProviderDBException {
        if (balance < 0)
            throw new ProviderDBException("Balanta invalida");
    }

    public static void validateCurrency(String currency) throws ProviderDBException {
        if (!currency.matches("^[a-zA-Z]+$"))
            throw new ProviderDBException("Currency invalid");
    }
}
