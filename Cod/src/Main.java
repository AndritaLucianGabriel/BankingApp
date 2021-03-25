import Operations.ToProviders;
import Service.Validations.*;
import Service.AccountStatement;

import java.lang.*;
import java.util.*;

/*
        TO DO (SOON):
    -fa manevra cu employee -> clerk & admin pentru mentodele de crud si separa-le dupa rank
    -mosteniri pentru BankAccount (abstracta)

        TO DO:
    -metoda de plata a datoriei
    -validarile intr-un final se vor face in cadrul constructorilor!!!!
    -agregare/compozitie? verifica
    -renunta la variabila statica pt id si fa o metoda separata (identifiable)
    -bank(addProviderDB)/remove
    -plata de imprumut pentru un client
    -transaction automat in functie de curs valutar (Foloseste API)
    -implementeaza API pentru currency si valuta
    -extrasul de cont va fi legat de un fisier extern pentru log-uri de tranzactii???????
        -metode de filtrare (interfete?)
    -de introdus ideea de factura (ToProviders) si stocarea ei
 */

public class Main {
    public static void main(String[] args) {
        /*      IBAN-uri utilitati (pentru testare):
            RO28INGB0001000000003333 - S.C. ENEL Energie Muntenia S.A.
            RO83INGB0001000000000888 - ENGIE Romania S.A.
            RO51INGB0001000000018827 - RCS RDS S.A.
            RO33BRDE450SV01059614500 - Apa Nova

        Rates @ 25-03-2021 14:41
             Lei->X             Dolari->X          Euro->X
            {1.0, 0.24, 0.20}, {4.14, 1.0, 0.85}, {4.89, 1.18, 1.0}};
        */

        //Declarari de servicii
        BankValidation bankValidation = new BankValidation();
        BankAccountValidation bankAccountValidation = new BankAccountValidation();
        CardValidation cardValidation = new CardValidation();
        ClientValidation clientValidation = new ClientValidation();
        LoanValidation loanValidation = new LoanValidation();
        ToProviders toProviders = new ToProviders();
        ToProvidersValidation toProvidersValidation = new ToProvidersValidation();

        AccountStatement accountStatement = new AccountStatement();

        //Introducere date BankAccount + validari
        List<BankAccount> bankAccountList = new ArrayList<>();
        BankAccount bankAccount = new BankAccount("RO59RZBR0000065122344800", "12-09-2021", null, 1203.2000, "Lei");
        BankAccount bankAccount1 = new BankAccount("RO59INGB0000062522326801", "25-07-2019", "12-09-2021", 1000, "Dolari");
        BankAccount bankAccount2 = new BankAccount("RO59RZBR0000068222375802", "15-05-2012", null, 12312, "Dolari");
        bankAccountList.add(bankAccount);
        bankAccountList.add(bankAccount1);
        bankAccountList.add(bankAccount2);

        for (BankAccount x : bankAccountList) {
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

        for (Loan x : loansList) {
            loanValidation.validateCurrency(x.getCurrency());
            loanValidation.validateDate(x.getDate());
            loanValidation.validateValue(x.getValue());
            loanValidation.validateDurationMonths(x.getDurationMonths());
        }

        //Introducere date Card + validari
        List<Card> cardList = new ArrayList<>();
        Card card = new Card("5213512152346781", 905, "25-09-2020");
        Card card1 = new Card("5603512157346791", 509, "05-10-2020");
        Card card2 = new Card("5113512652346763", 524, "10-05-2019");
        Card card3 = new Card("5223512152346752", 501, "18-04-2018");
        Card card4 = new Card("5151251415234674", 234, "08-03-2017");
        cardList.add(card);
        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);
        cardList.add(card4);

        for (Card x : cardList) {
            cardValidation.validateCardNumber(x.getCardNumber());
            cardValidation.validateIssueDate(x.getIssueDate());
            cardValidation.validatePin(x.getPIN());
        }

        //Introducere date Client + validari
        Client[] FakeClientList = new Client[2];
        Client client1 = new Client("Vadim", "Tudor", 60, "5980926460187");
        Client client2 = new Client("Dan", "Diaconescu", 58, "3951120450185");
        FakeClientList[0] = client1;
        FakeClientList[1] = client2;

        for (int i = 0; i < 2; i++) {
            clientValidation.validateFirst_name(FakeClientList[i].getFirst_name());
            clientValidation.validateLast_name(FakeClientList[i].getLast_name());
            clientValidation.validateCnp(FakeClientList[i].getCnp());
            clientValidation.validateAge(FakeClientList[i].getAge());
        }

        //Introducere date Bank + validari
        Map<Client, List<BankAccount>> clientBankAccountMap = new HashMap<Client, List<BankAccount>>();
        clientBankAccountMap.put(client1, bankAccountList);

        Map<Client, List<Loan>> clientLoanMap = new HashMap<Client, List<Loan>>();
        clientLoanMap.put(client2, loansList);

        Bank bank = new Bank("Raiffeisen Bank", "Strada Sebastian, Nr 54, Sector 3, Bucuresti", clientBankAccountMap, clientLoanMap);
        bankValidation.validateName(bank.getName());

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
        Client client3 = new Client("Ionita", "Dragos", 20, "52414626");
        bank.addBankAccountClient(client3);
        System.out.println(bank.toString());
        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("==========================================================================================================================");
        System.out.println("Adaugam un nou cont clientului de la pasul anterior\n");
        bank.addBankAccount(client3, bankAccount1);
        System.out.println(bank.toString());
        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("==========================================================================================================================");
        System.out.println("Vadim se intoarce\n");
        bank.addBankAccountClient(client1);
        bank.addBankAccount(client1, bankAccount);
        bank.addBankAccount(client1, bankAccount2);
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
        bank.addLoan(client3, loan2);
        ;
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
        bank.addCard(bankAccount1, card4);
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

        System.out.println("==========================================================================================================================");
        System.out.println("Ionita doreste niste bani, Vadim ii da din dintr-un cont\n");
        bank.balanceCheck(bankAccount);
        bank.balanceCheck(bankAccount1);
        System.out.println("----------");
        bank.interBanking(bankAccount1.getIBAN(), bankAccount.getIBAN(), 500);
        System.out.println();
        bank.balanceCheck(bankAccount);
        bank.balanceCheck(bankAccount1);
        System.out.println(bank.toString());
        System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println("COUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("==========================================================================================================================");
        System.out.println("Vadim si Ion isi platesc cablul\n");
        System.out.println(bank.toString());
        bank.paymentUtilies(bankAccount.getIBAN(), "RO51INGB0001000000018827", 100);
        bank.paymentUtilies(bankAccount1.getIBAN(), "RO51INGB0001000000018827", 750);
        System.out.println("-----------------");
        System.out.println(bank.toString());

        System.out.println("==========================================================================================================================");
        System.out.println("Ion isi schimba banii in Lei");
        System.out.println("  Inainte:");
        System.out.println(bankAccount1.toString());
        bank.currencyExchange(bankAccount1, "Lei");
        System.out.println("  Dupa:");
        System.out.println(bankAccount1.toString());
    }
}