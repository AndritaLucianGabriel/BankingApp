package Service.Validations;

import Service.Exceptions.LoanException;

public class LoanValidation {
    public static void validateValue(double value) throws LoanException {
        if (value < 0)
            throw new LoanException("Valoare invalida");
    }

    public static void validateCurrency(String currency) throws LoanException {
        if (!currency.matches("^[a-zA-Z]+$"))
            throw new LoanException("Currency invalid");
    }

    public static void validateDate(String date) throws LoanException {
        if (!date.matches("^([0123]\\d)[- /.]([01]\\d)[- /.]([012]\\d\\d\\d)$"))
            throw new LoanException("Data invalida");
    }

    public static void validateDurationMonths(int durationMonths) throws LoanException {
        if (durationMonths < 0)
            throw new LoanException("Durata invalida");
    }
}
