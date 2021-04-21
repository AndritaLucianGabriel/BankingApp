package Service.Validations;

import Service.Exceptions.CardException;

public class CardValidation {
    public static void validateCardNumber(String cardNumber) throws CardException {
        if (!cardNumber.matches("^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$"))
            throw new CardException("Numar card invalid");
    }

    public static void validatePin(int PIN) throws CardException {
        if (PIN < 0)
            throw new CardException("PIN invalid");
    }

    public static void validateIssueDate(String issueDate) throws CardException {
        if (!issueDate.matches("^([0123]\\d)[- /.]([01]\\d)[- /.]([012]\\d\\d\\d)$"))
            throw new CardException("Data emiterii invalida");
    }
}