package mainClasses;

import operations.ProviderDB;
import service.exceptions.*;
import service.files.ReaderFiles;
import service.files.WriterFiles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*
        Diferente fata de Etapa 1:
    -metoda de plata a datoriei (una basic pentru a plati fix cat rata, alta pentru mai mult + recalculare)
    -validari in cadrul constructorilor + setteri + integrare exceptii
    -am transformat ToProviders in singleton + am creat metode de adaugare/stergere al providerilor
    -transformare BankAccount in clasa abstracta + modificari la metodele vechi din BANK
        -crearea lui DebitAccount (practic e fostul BankAccount)
        -crearea lui SavingsAccount (camp nou annualInterestRate)
            -aceleasi facilitati (cu diminuare de annualInterestRate si aplicarea unui comision pe fiecare retragere)
            -metoda care executa penalizarea mentionata mai sus (penalty)
    -CurrencyExchange without text pentru savings account
    -am refacut FormatDouble
    -am adaugat getter in ToProviders pentru a adapta clasa pentru prelucrarea datelor din fisiere (+ checkProviders)
    -am adaugat o noua functie add provider
    -am facut TransactionValidation si TransactionException
    -am adaugat pachetul Files
        -pachetul accountStatement (va retine toate tranzactiile unui cont)
        -pachetul readers (va incarca datele)
        -pachetul writer (afisari)
            -pachetul accountStatementTemp (va contine fisierele cu informatiile filtrare din AccountStatement)
        Obs: O sa pastrez totusi afisarile in consola pentru info updates referitoarea la executia metodelor
        -clasele ReaderFiles + WriterFiles
    -avem cate un fisier pentru fiecare cont in functie de IBAN (pregatesc modificarea clasei AccountStatement)
    -am creat interfata timestamp si am folosit fisierul logs.txt pentru a stoca istoricul apelurilor
    -am creat interfata asigUUID pentru id-urile tranzactiilor
    -am transformat in interfata FormatDouble, AccountStatement, CurrencyExchange
    -am adaugat in fiecare clasa dto o metoda ce afiseaza conform tiparului in fisierele csv noile date + creat in readerFiles metoda propriu-zisa (CRUD UPDATE)
    -am gasit un bug la metodele equals() (hai ca nici n am pierdut 2 zile pana am gasit problema) P.S. Am avut asta inainte de feedback
    -nu se mai pot face tranzactii cu/din conturile inchise
    -currencyExchange pe baza de IBAN
    -modificat BankValidation pentru a include si verificare de semn
    -modificat din LocalDateTime in LocalDate
    -AccountStatement
        -am adaugat o metoda ajutatoare pentru a realiza filtrele pe baza datei
        -am adaugat o metoda ajutatoare pentru afisare
        -am adaugat metodele filterDate()
        -am adaugat metodele filterValue()
        -am adaugat metodele filterCurrency()

        Current wip:

        TO DO:
    ---log pentru exceptii
    ---de introdus ideea de factura (ToProviders) si stocarea ei
    ---fa manevra cu employee -> clerk & admin pentru metodele de crud si separa-le dupa rank
    ---try catch + exceptii + total overhaul

        EXTRA PROJ:
    -implementeaza API pentru currency si valuta
    -transaction automat in functie de curs valutar (Foloseste API)

 */

public class Main {
    public static void main(String[] args) throws BankAccountException, TransactionException, ProviderDBException, LoanException, CardException, ClientException {
        /*                 Rates @ 25-03-2021 14:41
                 Lei->X             Dolari->X          Euro->X
            {1.0, 0.24, 0.20}, {4.14, 1.0, 0.85}, {4.89, 1.18, 1.0}};

                           Informatii cu privire la utilizare metodelor de filtrare din AccountStatement
            = -> returneaza daca exista parametrul
            > -> mai mare decat parametru
            < -> mai mic decat parametru
            <= -> mai mic sau egal decat parametru
            >= -> mai mare sau egal decat parametru
            <> -> orice, doar diferit de parametru
            >< -> sa se afle fix in intervalul specificat
        */

//          Extras
        Loan loan = new Loan(21410.3, "Lei", "Imprumut pentru nevoi personale", "03-12-2021", 60);
        Loan loan1 = new Loan(15213, "Lei", "Imprumut pentru masina", "03-02-2020", 72);
        Loan loan2 = new Loan(523151, "Dolari", "Imprumut pentru casa", "05-10-2019", 120);
//            Loan loan3 = new Loan(523151, "Dolari", "lele", "05-10-2019", 120);

//          Extras
        Card card = new Card("5213512152346781", 905, "25-09-2020");
        Card card1 = new Card("5603512157346791", 509, "05-10-2020");
        Card card2 = new Card("5113512652346763", 524, "10-05-2019");
//        Card card3 = new Card("5223512152346752", 501, "18-04-2018");
        Card card4 = new Card("5151251415234674", 234, "08-03-2017");

//        Extras
        BankAccount bankAccount = new DebitAccount("RO59RZBR0000065122344800", "12-09-2021", "-", 20030, "Lei");
        BankAccount bankAccount1 = new SavingsAccount("RO59INGB0000062522326801", "25-07-2019", "12-09-2021", 1000, "Dolari", 3.3);
        BankAccount bankAccount2 = new DebitAccount("RO59RZBR0000068222375802", "15-05-2012", "-", 12312, "Dolari");
//        BankAccount bankAccount3 = new SavingsAccount("RO69RZBR0000068222375804", "15-05-2000", "-", 12312, "Dolari");

//          Extras
        ProviderDB providerDB = new ProviderDB("S.C. ENEL Energie Muntenia S.A.", "RO28INGB0001000000003333", "Lei");
        ProviderDB providerDB1 = new ProviderDB("ENGIE Romania S.A.", "RO83INGB0001000000000888", "Dolari");
        ProviderDB providerDB2 = new ProviderDB("RCS RDS S.A.", "RO51INGB0001000000018827", "Euro");
        ProviderDB providerDB3 = new ProviderDB("Apa Nova", "RO33BRDE4500501059614500", "Dolari");

//        Extras
        Client client1 = new Client("Vadim", "Tudor", 60, "5980926460187");
        Client client2 = new Client("Dan", "Diaconescu", 58, "3951120450185");
        Client client3 = new Client("Ionita", "Dragos", 20, "5540522460057");

//        Stergerea fostelor informatii din compile-ul precedent
        WriterFiles.getInstance().clearAllFolders();
//        Incarcarea noilor informatii
        Bank bank = new Bank(Objects.requireNonNull(ReaderFiles.getInstance().readerBank()));


        WriterFiles.getInstance().writerBank("\n\tInitial\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        bank.removeAccount(bankAccount1.getIBAN());
        WriterFiles.getInstance().writerBank("\n\tAm scos al doilea cont al lui Vadim si am refacut indexii + scadere variabila statica\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        bank.removeClientBankAccount(client1.getCnp());
        WriterFiles.getInstance().writerBank("\n\tEliminare conturilor lui Vadim\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        bank.addBankAccountClient(client3);
        WriterFiles.getInstance().writerBank("\n\tAdaugam client nou\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        bank.addBankAccount(client3, bankAccount1);
        WriterFiles.getInstance().writerBank("\n\tAdaugam un nou cont clientului de la pasul anterior\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        bank.addBankAccountClient(client1);
        bank.addBankAccount(client1, bankAccount);
        bank.addBankAccount(client1, bankAccount2);
//        bank.addBankAccount(client1,bankAccount1); //decomentat coincide cu cel al lui Ionita Dragos
        WriterFiles.getInstance().writerBank("\n\tVadim se intoarce\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("\n\n----------");
        bank.addLoan(client1, loan2);
        bank.removeClientLoan(client2);
        WriterFiles.getInstance().writerBank("\n\tDiaconescu pleaca de la OTV, iar Ionita incearca sa-i paseze un imprumut, dar e prea tarziu\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        bank.addLoan(client3, loan);
        bank.addLoan(client3, loan1);
        bank.addLoan(client3, loan2);
        WriterFiles.getInstance().writerBank("\n\tIonita devine foarte sarac\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        bank.removeLoan(loan2);
        bank.addLoan(client1, loan2);
        WriterFiles.getInstance().writerBank("\n\tVadim se ofera sa-i plateasca lui Ionita un imprumut\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("\n\n----------");
        bank.addCard(bankAccount1, card4);
        WriterFiles.getInstance().writerBank("\n\tDoar conturile deschise vor avea carduri\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        bank.addCard(bankAccount, card);
        bank.addCard(bankAccount, card1);
        bank.addCard(bankAccount, card2);
        WriterFiles.getInstance().writerBank("\n\tAdaugam carduri lui Vadim\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        bank.removeCard(card1);
        WriterFiles.getInstance().writerBank("\n\tMai scoatem din cardurile lui Vadim\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("\n\n----------");
        bank.balanceCheck(bankAccount);
        bank.balanceCheck(bankAccount2);
        System.out.println("----------");
        bank.interBanking(bankAccount.getIBAN(), bankAccount2.getIBAN(), 500);
        System.out.println();
        bank.balanceCheck(bankAccount);
        bank.balanceCheck(bankAccount2);
        WriterFiles.getInstance().writerBank("\n\tIonita doreste niste bani, Vadim ii da din dintr-un cont\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("\n\n----------");
        bank.paymentUtilies(bankAccount.getIBAN(), "RO51INGB0001000000018827", 100);
        System.out.println("----------");
        bank.paymentUtilies(bankAccount1.getIBAN(), "RO51INGB0001000000018827", 40);
        WriterFiles.getInstance().writerBank("\n\tVadim si Ion isi platesc cablul\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("\n\n----------");
        bank.currencyExchange(bankAccount.getIBAN(), "Dolari");
        WriterFiles.getInstance().writerBank("\n\tIon isi schimba banii in Lei\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        System.out.println("\n\n----------");
        bank.payLoan(bankAccount, loan2, 4500);
        WriterFiles.getInstance().writerBank("\n\tVadim isi plateste din datorii\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

        bank.filterByDate(bankAccount.getIBAN(), LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyy")), "=");
        bank.filterByValue(bankAccount.getIBAN(), 101, ">");
        bank.filterByCurrency(bankAccount.getIBAN(), "Dolari", "<>");
        WriterFiles.getInstance().writerBank("\n\tVadim vrea sa vada ce tranzactii a facut (vezi AccountStatementTemp)\n" + bank + "\nCOUNTER CONTURI: " + BankAccount.getCounterBankAccountID() + "\nCOUNTER IMPRUMUTURI: " + Loan.getCounterLoanID());

//        Update-ul fisierelor de intrare
//        ReaderFiles.getInstance().updateReaders(bank);
    }
}