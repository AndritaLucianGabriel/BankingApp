package Service;

public class LoanValidation {
    public boolean validateValue(double value){return value>0;}
    public boolean validateCurrency(String currency){return currency.matches("^[a-zA-Z]+$");}
    //fara validateDetail pentru ca poate sa fie orice
    public boolean validateDate(String date){return date.matches("^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$");}
    public boolean validateDurationMonths(int durationMonths){return durationMonths>0;}
}
