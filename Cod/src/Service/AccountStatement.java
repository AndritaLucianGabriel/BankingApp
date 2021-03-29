package Service;

public class AccountStatement {
    //Va prelucra date cand voi avea un fisier ce stocheaza toate tranzactiile
    //Momentan e fix getterul din MainClasses.BankAccount
    public void balanceCheck(double value, String currency) {
        System.out.println("Contul are " + value + " " + currency);
    }
}
