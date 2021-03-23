import Service.Validations.*;

import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Declarari de validari
        BankValidation bankValidation = new BankValidation();
        BankAccountValidation bankAccountValidation = new BankAccountValidation();
        CardValidation cardValidation = new CardValidation();
        ClientValidation clientValidation = new ClientValidation();
        LoanValidation loanValidation = new LoanValidation();

        //Introducere date BankAccount + validari
        List<BankAccount> bankAccountList = new ArrayList<>();
        BankAccount bankAccount = new BankAccount("RO59RZBR0000060022344800", "12-09-2021", null, 1203.4, "Lei");
        BankAccount bankAccount1 = new BankAccount("RO29ZBR52351552", "25-07-2019", "12-09-2021", 52312, "Dolari");
        BankAccount bankAccount2 = new BankAccount("RO41ZBR23142317", "15-05-2012", null, 12312, "Dolari");
        bankAccountList.add(bankAccount);
        bankAccountList.add(bankAccount1);
        bankAccountList.add(bankAccount2);

        for(BankAccount x: bankAccountList) {
            bankAccountValidation.validateIBAN(x.getIBAN());
            bankAccountValidation.validateOpeningDate(x.getOpeningDate());
            bankAccountValidation.validateClosingDate(x.getClosingDate());
            bankAccountValidation.validateBalance(x.getBalance());
            bankAccountValidation.validateCurrency(x.getCurrency());
        }

        //Introducere date Loan + validari
        List<Loan> loansList = new ArrayList<>();
        Loan loan = new Loan(21410.3, "Lei", "Imprumut pentru nevoi personale", "03-12-2021", 60);
        Loan loan1 = new Loan(15213, "Lei", "Imprumut pentru masina", "03-02-2020", 72);
        Loan loan2 = new Loan(523151, "Dolari", "Imprumut pentru casa", "05-10-2019", 120);
        loansList.add(loan);
        loansList.add(loan1);
        loansList.add(loan2);

        //Introducere date Card + validari
        List<Card> cardList = new ArrayList<>();
        Card card = new Card("5213512152346781", 905, "25-09-2020");
        Card card1 = new Card("1532142", 509, "05-10-2020");
        Card card2 = new Card("8786556", 524, "10-05-2019");
        Card card3 = new Card("2242522", 501, "18-04-2018");
        Card card4 = new Card("9463545", 234, "08-03-2017");
        cardList.add(card);
        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);
        cardList.add(card4);

        for(Card x: cardList) {
            cardValidation.validateCardNumber(card.getCardNumber());
            cardValidation.validateIssueDate(card.getIssueDate());
            cardValidation.validatePin(card.getPIN());
        }

        //Introducere date Client + validari
        Client client1 = new Client("Vadim", "Tudor", 60, "5980926460187");
        Client client2 = new Client("Dan", "Diaconescu", 58, "876864835");

        //Introducere date Bank + validari
        Map<Client, List<BankAccount>> clientBankAccountMap = new HashMap<Client, List<BankAccount>>();
        clientBankAccountMap.put(client1, bankAccountList);

        Map<Client, List<Loan>> clientLoanMap = new HashMap<Client, List<Loan>>();
        clientLoanMap.put(client2, loansList);

        Bank bank = new Bank("Raiffeisen Bank", "Strada Sebastian, Nr 54, Sector 3, Bucuresti", clientBankAccountMap, clientLoanMap);
        bankValidation.validateName(bank.getName());

//        System.out.println("==========================================================================================================================");
//        System.out.println("Initial\n");
//        System.out.println(bank.toString());
//        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
//        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());
//
//        System.out.println("==========================================================================================================================");
//        System.out.println("Am scos al doilea cont al lui Vadim si am refacut indexii + scadere variabila statica");
//        bank.removeAccount(bankAccount1);
//        System.out.println(bank.toString());
//        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
//        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());
//
//        System.out.println("==========================================================================================================================");
//        System.out.println("Eliminare lui Vadim din BankAccountMap, o sa ramana totusi daca are un imprumut\n");
//        bank.removeClientBankAccount(client1.getCnp());
//        System.out.println(bank);
//        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
//        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());
//
//        System.out.println("==========================================================================================================================");
//        System.out.println("Adaugam client nou\n");
//        Client client3=new Client("Ionita","Dragos",20,"52414626");
//        bank.addBankAccountClient(client3);
//        System.out.println(bank.toString());
//        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
//        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());
//
//        System.out.println("==========================================================================================================================");
//        System.out.println("Adaugam un nou cont clientului de la pasul anterior\n");
//        bank.addBankAccount(client3,bankAccount1);
//        System.out.println(bank.toString());
//        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
//        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());
//
//        System.out.println("==========================================================================================================================");
//        System.out.println("Vadim se intoarce\n");
//        bank.addBankAccountClient(client1);
//        bank.addBankAccount(client1,bankAccount);
//        bank.addBankAccount(client1,bankAccount2);
//        //bank.addBankAccount(client1,bankAccount1); //decomentat coincide cu cel al lui Ionita Dragos
//        System.out.println(bank.toString());
//        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
//        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());
//
//        System.out.println("==========================================================================================================================");
//        System.out.println("Diaconescu pleaca de la OTV, iar Ionita incearca sa-i paseze un imprumut, dar e prea tarziu\n");
//        bank.addLoanCLient(client1);
//        bank.addLoan(client1, loan2);
//        bank.removeClientLoan(client2);
//        System.out.println(bank.toString());
//        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
//        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());
//
//        System.out.println("==========================================================================================================================");
//        System.out.println("Ionita devine foarte sarac\n");
//        bank.addLoan(client3, loan);
//        bank.addLoan(client3, loan1);
//        bank.addLoan(client3, loan2);;
//        System.out.println(bank.toString());
//        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
//        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());
//
//        System.out.println("==========================================================================================================================");
//        System.out.println("Vadim se ofera sa-i plateasca lui Ionita un imprumut\n");
//        //bank.removeAccount(bankAccount);
//        bank.removeLoan(loan2);
//        bank.addLoan(client1, loan2);
//        System.out.println(bank.toString());
//        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
//        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());
//
//        System.out.println("==========================================================================================================================");
//        System.out.println("Doar conturile deschise vor avea carduri\n");
//        bank.addCard(bankAccount1,card4);
//        System.out.println(bank.toString());
//        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
//        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());
//
//        System.out.println("==========================================================================================================================");
//        System.out.println("Adaugam carduri lui Vadim\n");
//        bank.addCard(bankAccount, card);
//        bank.addCard(bankAccount, card1);
//        bank.addCard(bankAccount, card2);
//        System.out.println(bank.toString());
//        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
//        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());
//
//        System.out.println("==========================================================================================================================");
//        System.out.println("Mai scoatem din cardurile lui Vadim\n");
//        bank.removeCard(card1);
//        System.out.println(bank.toString());
//        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
//        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        //Zona de Teste
        //Part 6. Testare regex-uri
        System.out.println("==========================================================================================================================");

        clientValidation.validateFirst_name(client1.getFirst_name());
        clientValidation.validateLast_name(client1.getLast_name());
        clientValidation.validateCnp(client1.getCnp());
        clientValidation.validateAge(client1.getAge());

        loanValidation.validateCurrency(loan.getCurrency());
        loanValidation.validateDate(loan.getDate());
        loanValidation.validateValue(loan.getValue());
        loanValidation.validateDurationMonths(loan.getDurationMonths());
    }
}