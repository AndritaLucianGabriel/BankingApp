import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Testare functionalitati Bank
        List<BankAccount> bankAccountList = new ArrayList<>();
        BankAccount bankAccount = new BankAccount("RO42RZBR15215321", "12-09-2021", null, 1203.4, "Lei");
        BankAccount bankAccount1 = new BankAccount("RO29ZBR52351552", "25-07-2019", "12-09-2021", 52312, "Dolari");
        BankAccount bankAccount2 = new BankAccount("RO41ZBR23142317", "15-05-2012", null, 12312, "Dolari");
        bankAccountList.add(bankAccount);
        bankAccountList.add(bankAccount1);
        bankAccountList.add(bankAccount2);

        List<Loan> loansList = new ArrayList<>();
        Loan loan = new Loan(21410.3, "Lei", "Imprumut pentru nevoi personale", "03-21-2021", 60);
        Loan loan1 = new Loan(15213, "Lei", "Imprumut pentru masina", "03-02-2020", 72);
        Loan loan2 = new Loan(523151, "Dolari", "Imprumut pentru casa", "05-10-2019", 120);
        loansList.add(loan);
        loansList.add(loan1);
        loansList.add(loan2);

        Card card = new Card("5213512", 905, "13-09-2021");
        Card card1 = new Card("1532142", 509, "05-10-2020");
        Card card2 = new Card("8786556", 524, "10-05-2019");
        Card card3 = new Card("2242522", 501, "18-04-2018");
        Card card4 = new Card("9463545", 234, "08-03-2017");

        Client client1 = new Client("Vadim", "Tudor", 60, "52414626");
        Client client2 = new Client("Dan", "Diaconescu", 58, "876864835");

        Map<Client, List<BankAccount>> clientBankAccountMap = new HashMap<Client, List<BankAccount>>();
        clientBankAccountMap.put(client1, bankAccountList);

        Map<Client, List<Loan>> clientLoanMap = new HashMap<Client, List<Loan>>();
        clientLoanMap.put(client2, loansList);

        Bank bank = new Bank("Raiffeisen Bank", "Strada Sebastian, Nr 54, Sector 3, Bucuresti", clientBankAccountMap, clientLoanMap);

        System.out.println("==========================================================================================================================");
        System.out.println("Initial\n");
        System.out.println(bank.toString());
        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("==========================================================================================================================");
        System.out.println("Am scos al doilea cont al lui Vadim si am refacut indexii + scadere variabila statica");
        bank.removeAccount(bankAccount1);
        System.out.println(bank.toString());
        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("==========================================================================================================================");
        System.out.println("Eliminare lui Vadim din BankAccountMap, o sa ramana totusi daca are un imprumut\n");
        bank.removeClientBankAccount(client1.getCnp());
        System.out.println(bank);
        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("==========================================================================================================================");
        System.out.println("Adaugam client nou\n");
        Client client3=new Client("Ionita","Dragos",20,"52414626");
        bank.addBankAccountClient(client3);
        System.out.println(bank.toString());
        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("==========================================================================================================================");
        System.out.println("Adaugam un nou cont clientului de la pasul anterior\n");
        bank.addBankAccount(client3,bankAccount1);
        System.out.println(bank.toString());
        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("==========================================================================================================================");
        System.out.println("Vadim se intoarce\n");
        bank.addBankAccountClient(client1);
        bank.addBankAccount(client1,bankAccount);
        bank.addBankAccount(client1,bankAccount2);
        //bank.addBankAccount(client1,bankAccount1); //decomentat coincide cu cel al lui Ionita Dragos
        System.out.println(bank.toString());
        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("==========================================================================================================================");
        System.out.println("Diaconescu pleaca de la OTV, iar Ionita incearca sa-i paseze un imprumut, dar e prea tarziu\n");
        bank.addLoanCLient(client1);
        bank.addLoan(client1, loan2);
        bank.removeClientLoan(client2);
        System.out.println(bank.toString());
        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("==========================================================================================================================");
        System.out.println("Ionita devine foarte sarac\n");
        bank.addLoan(client3, loan);
        bank.addLoan(client3, loan1);
        bank.addLoan(client3, loan2);;
        System.out.println(bank.toString());
        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("==========================================================================================================================");
        System.out.println("Vadim se ofera sa-i plateasca lui Ionita un imprumut\n");
        //bank.removeAccount(bankAccount);
        bank.removeLoan(loan2);
        bank.addLoan(client1, loan2);
        System.out.println(bank.toString());
        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("==========================================================================================================================");
        System.out.println("Doar conturile deschise vor avea carduri\n");
        bank.addCard(bankAccount1,card4);
        System.out.println(bank.toString());
        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("==========================================================================================================================");
        System.out.println("Adaugam carduri lui Vadim\n");
        bank.addCard(bankAccount, card);
        bank.addCard(bankAccount, card1);
        bank.addCard(bankAccount, card2);
        System.out.println(bank.toString());
        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("==========================================================================================================================");
        System.out.println("Mai scoatem din cardurile lui Vadim\n");
        bank.removeCard(card1);
        System.out.println(bank.toString());
        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());
    }
}