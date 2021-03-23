package Service.Validations;

public class CardValidation {
    public void validateCardNumber(String cardNumber) {
        if (!cardNumber.matches("^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$"))
            System.out.println("Numar card invalid");
    }
    public void validatePin(int PIN){
        if(PIN<0)
            System.out.println("PIN invalid");
    }
    public void validateIssueDate(String issueDate){
        if(!issueDate.matches("^([0123]\\d)[- /.]([01]\\d)[- /.]([012]\\d\\d\\d)$"))
            System.out.println("Data emiterii invalida");
    }
}