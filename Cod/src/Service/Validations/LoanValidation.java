package Service.Validations;

public class LoanValidation {
    public void validateValue(double value){
        if(value<0)
            System.out.println("Valoare invalida");
    }
    public void validateCurrency(String currency){
        if(!currency.matches("^[a-zA-Z]+$"))
            System.out.println("Currency invalid");
    }
    //fara validateDetail pentru ca poate sa fie orice
    public void validateDate(String date){
        if(!date.matches("^([0123]\\d)[- /.]([01]\\d)[- /.]([012]\\d\\d\\d)$"))
            System.out.println("Data invalida");
    }
    public void validateDurationMonths(int durationMonths){
        if(durationMonths<0)
            System.out.println("Durata invalida");
    }
}
