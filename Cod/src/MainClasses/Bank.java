package MainClasses;

import Service.CurrencyExchange;
import Operations.ProviderDB;
import Operations.ToProviders;
import Service.AccountStatement;
import Service.Exceptions.*;
import Service.Timestamp;
import Service.Files.WriterFiles;
import Service.FormatDouble;
import Service.Validations.BankAccountValidation;
import Service.Validations.BankValidation;

import java.util.*;

public class Bank implements AccountStatement {
    protected static int counterBankID;
    protected int BankID;
    protected String name;
    protected String location;
    protected Map<Client, List<BankAccount>> clientBankAccountMap;
    protected Map<Client, List<Loan>> clientLoanMap;

    public Bank() {
        counterBankID++;
        this.BankID = counterBankID;
        this.name = "";
        this.location = "";
        this.clientBankAccountMap = new HashMap<>();
        this.clientLoanMap = new HashMap<>();
    }

    //Constructor destinat incarcarii tutoror datelor bancii in acelasi timp
    public Bank(Bank copy) {
        counterBankID++;
        this.BankID = counterBankID;
        this.name = copy.name;
        this.location = copy.location;
        this.clientBankAccountMap = copy.clientBankAccountMap;
        this.clientLoanMap = copy.clientLoanMap;
    }

    //Clasic
    public Bank(String name, String location, Map<Client, List<BankAccount>> clientBankAccountMap, Map<Client, List<Loan>> clientListMap) throws BankException {
        BankValidation.validateName(name);
        counterBankID++;
        this.BankID = counterBankID;
        this.name = name;
        this.location = location;

        this.clientBankAccountMap = clientBankAccountMap;
        for (Map.Entry<Client, List<BankAccount>> x : clientBankAccountMap.entrySet()) {

            normalizeBankIndex(x.getKey());
        }

        this.clientLoanMap = clientListMap;
        for (Map.Entry<Client, List<Loan>> x : clientLoanMap.entrySet()) {
            normalizeLoanIndex(x.getKey());
        }
    }

    //Doar un client cu mai multe conturi si mai multe imprumuturi
    public Bank(String name, String location, Client clientBankAccount, List<BankAccount> bankAccounts, Client clientLoan, List<Loan> loans) throws BankException {
        BankValidation.validateName(name);
        counterBankID++;
        this.BankID = counterBankID;
        this.name = name;
        this.location = location;

        clientBankAccountMap = new HashMap<>();
        this.clientBankAccountMap.put(clientBankAccount, bankAccounts);
        normalizeBankIndex(clientBankAccount);

        clientLoanMap = new HashMap<>();
        this.clientLoanMap.put(clientLoan, loans);
        normalizeLoanIndex(clientLoan);

    }

    //Doar un client cu un singur cont si un singur imprumut
    public Bank(String name, String location, Client clientBankAccount, BankAccount bankAccounts, Client clientLoan, Loan loans) throws BankException {
        BankValidation.validateName(name);
        counterBankID++;
        this.BankID = counterBankID;
        this.name = name;
        this.location = location;

        clientBankAccountMap = new HashMap<>();
        List<BankAccount> localBank = new ArrayList<BankAccount>();
        localBank.add(bankAccounts);
        this.clientBankAccountMap.put(clientBankAccount, localBank);
        normalizeBankIndex(clientBankAccount);

        clientLoanMap = new HashMap<>();
        List<Loan> localLoan = new ArrayList<Loan>();
        localLoan.add(loans);
        this.clientLoanMap.put(clientLoan, localLoan);
        normalizeLoanIndex(clientLoan);
    }

    //Getteri & Setteri
    public static int getCounterBankID() {
        return counterBankID;
    }

    public static void setCounterBankID(int counterBankID) {
        Bank.counterBankID = counterBankID;
    }

    public int getBankID() {
        return BankID;
    }

    public void setBankID(int bankID) {
        BankID = bankID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws BankException {
        BankValidation.validateName(name);
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<Client, List<BankAccount>> getClientBankAccountMap() {
        return clientBankAccountMap;
    }

    public void setClientBankAccountMap(Map<Client, List<BankAccount>> clientBankAccountMap) {
        this.clientBankAccountMap = clientBankAccountMap;
    }

    public Map<Client, List<Loan>> getClientLoanMap() {
        return clientLoanMap;
    }

    public void setClientLoanMap(Map<Client, List<Loan>> clientLoanMap) {
        this.clientLoanMap = clientLoanMap;
    }

    //Caz particular per client pt conturi
    public void normalizeBankIndex(Client client) {
        Timestamp.timestamp("Bank: normalizeBankIndex");
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            if (x.getKey().equals(client)) {
                List<BankAccount> dummy = x.getValue();
                int poz = 1;
                for (BankAccount y : dummy) {
                    y.setBankAccountID(poz);
                    poz++;
                }
            }
        }
    }

    //Caz general pentru conturi
    public void normalizeBankIndex() {
        Timestamp.timestamp("Bank: normalizeBankIndex");
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            List<BankAccount> dummy = x.getValue();
            int poz = 1;
            for (BankAccount y : dummy) {
                y.setBankAccountID(poz);
                poz++;
            }
        }
    }

    //Caz particular per Client pt imprumuturi
    public void normalizeLoanIndex(Client client) {
        Timestamp.timestamp("Bank: normalizeLoanIndex");
        for (Map.Entry<Client, List<Loan>> x : this.clientLoanMap.entrySet()) {
            if (x.getKey().equals(client)) {
                List<Loan> dummy = x.getValue();
                int poz = 1;
                for (Loan y : dummy) {
                    y.setLoanID(poz);
                    poz++;
                }
            }
        }
    }

    //Caz general pentru imprumuturi
    public void normalizeLoanIndex() {
        Timestamp.timestamp("Bank: normalizeLoanIndex");
        for (Map.Entry<Client, List<Loan>> x : this.clientLoanMap.entrySet()) {
            List<Loan> dummy = x.getValue();
            int poz = 1;
            for (Loan y : dummy) {
                y.setLoanID(poz);
                poz++;
            }
        }
    }

    //AddBankAccountClient pe baza de client - nu are sens altfel (avem nevoie de detalii)
    public void addBankAccountClient(Client client) {
        Timestamp.timestamp("Bank: addBankAccountClient");
        if (this.clientBankAccountMap.containsKey(client)) {
            System.out.println("Clientul " + client.getFirstName() + " " + client.getLastName() + " exista deja!\n");
        } else {
            List<BankAccount> dummy = new ArrayList<>();
            this.clientBankAccountMap.put(client, dummy);
        }
    }

    //AddBankAccount pe baza de client - pentru citire
    public void addBankAccount(Client client, BankAccount bankAccount) {
        Timestamp.timestamp("Bank: addBankAccount");
        int avem = 0, c = 0;
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            for (BankAccount y : x.getValue()) {
                if (y.equals(bankAccount)) {
                    System.out.println("Contul " + bankAccount.getIBAN() + " exista deja pentru clientul " + x.getKey().getFirstName() + " " + x.getKey().getLastName() + "\n");
                    c++;
                }
            }
        }
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            if (x.getKey().equals(client)) {
                avem++;
                List<BankAccount> dummy = x.getValue();
                if (c == 0) {
                    if (dummy.isEmpty())
                        bankAccount.setBankAccountID(1);
                    dummy.add(bankAccount);
                    normalizeBankIndex(client);
                    BankAccount.setCounterBankAccountID(BankAccount.getCounterBankAccountID() + 1);
                    this.clientBankAccountMap.replace(x.getKey(), dummy);
                }
            }
        }
        //se va apela mai intai addClient cand nu avem clientul deja
        if (avem == 0 && c == 0) {
            addBankAccountClient(client);
            List<BankAccount> dummy = new ArrayList<>();
            bankAccount.setBankAccountID(1);
            dummy.add(bankAccount);
            normalizeBankIndex(client);
            BankAccount.setCounterBankAccountID(BankAccount.getCounterBankAccountID() + 1);
            this.clientBankAccountMap.put(client, dummy);
        }
    }

    //AddLoanClient pe baza de client - nu are sens altfel (avem nevoie de detalii)
    public void addLoanCLient(Client client) {
        Timestamp.timestamp("Bank: addLoanCLient");
        if (this.clientLoanMap.containsKey(client)) {
            System.out.println("Clientul " + client.getFirstName() + " " + client.getLastName() + " exista deja!\n");
        } else {
            List<Loan> dummy = new ArrayList<>();
            this.clientLoanMap.put(client, dummy);
        }
    }

    //AddLoan pe baza de client - pentru citire
    public void addLoan(Client client, Loan loan) {
        Timestamp.timestamp("Bank: addLoan");
        int avem = 0, c = 0;
        for (Map.Entry<Client, List<Loan>> x : this.clientLoanMap.entrySet()) {
            for (Loan y : x.getValue()) {
                if (y.equals(loan)) {
                    System.out.println("Imprumutul exista deja pentru clientul " + x.getKey().getFirstName() + " " + x.getKey().getLastName() + "\n");
                    c++;
                }
            }
        }
        for (Map.Entry<Client, List<Loan>> x : this.clientLoanMap.entrySet()) {
            if (x.getKey().equals(client)) {
                avem++;
                List<Loan> dummy = x.getValue();
                if (c == 0) {
                    if (dummy.isEmpty())
                        loan.setLoanID(1);
                    dummy.add(loan);
                    normalizeLoanIndex(client);
                    Loan.setCounterLoanID(Loan.getCounterLoanID() + 1);
                    this.clientLoanMap.replace(x.getKey(), dummy);
                }
            }
        }
        //se va apela mai intai addClient cand nu avem clientul deja
        if (avem == 0 && c == 0) {
            addLoanCLient(client);
            List<Loan> dummy = new ArrayList<>();
            loan.setLoanID(1);
            dummy.add(loan);
            normalizeLoanIndex(client);
            Loan.setCounterLoanID(Loan.getCounterLoanID() + 1);
            this.clientLoanMap.put(client, dummy);
        }
    }

    //AddCard pe baza de card
    public void addCard(BankAccount bankAccount, Card card) {
        Timestamp.timestamp("Bank: addCard");
        int c = 0;
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            if (x.getValue().contains(bankAccount)) {
                c++;
                for (BankAccount y : x.getValue()) {
                    if (y.equals(bankAccount)) {
                        if (!y.cardList.contains(card) && (y.getClosingDate() == null || y.getClosingDate().equals("-")))
                            y.addCard(card);
                        else if (y.getClosingDate() != null && !Objects.equals(y.getClosingDate(), "-"))
                            System.out.println("Contul " + bankAccount.getIBAN() + " a fost inchis deja, va rugam nu adaugati carduri.");
                    }
                }
            }
        }
        if (c == 0)
            System.out.println("Contul " + bankAccount.getIBAN() + " nu exista.");
    }

    //RemoveCard pe baza de card
    public void removeCard(Card card) {
        Timestamp.timestamp("Bank: removeCard");
        int c = 0;
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            for (BankAccount y : x.getValue()) {
                if (y.cardList.contains(card)) {
                    c++;
                    y.cardList.remove(card);
                }
            }
        }
        if (c == 0) {
            System.out.println("Cardul " + card.cardNumber + " nu exista");
        }
    }

    //RemoveCard pe baza de numar de card
    public void removeCard(String cardNumber) {
        Timestamp.timestamp("Bank: removeCard");
        int c = 0;
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            for (BankAccount y : x.getValue()) {
                for (Card z : y.getCardList()) {
                    if (z.getCardNumber().equals(cardNumber)) {
                        c++;
                        y.cardList.remove(z);
                    }
                }
            }
        }
        if (c == 0) {
            System.out.println("Cardul " + cardNumber.toString() + " nu exista");
        }
    }

    //RemoveClientLoan pe baza de CNP - pentru citire
    public void removeClientLoan(String cnp) {
        Timestamp.timestamp("Bank: removeClientLoan");
        Client evitConcurrentModificationException = new Client();
        int c = 0;
        int nrLoans = 0;
        for (Map.Entry<Client, List<Loan>> x : this.clientLoanMap.entrySet()) {
            if (x.getKey().getCnp().equals(cnp)) {
                evitConcurrentModificationException = x.getKey();
                c++;
                nrLoans = x.getValue().size();
            }
        }
        if (c != 0) {
            Loan.setCounterLoanID(Loan.getCounterLoanID() - nrLoans);
            this.clientLoanMap.remove(evitConcurrentModificationException);
        }
    }

    //RemoveClientLoan pe baza de client - pentru apel
    public void removeClientLoan(Client client) {
        Timestamp.timestamp("Bank: removeClientLoan");
        int nrLoans = 0;
        if (this.clientLoanMap.containsKey(client)) {
            nrLoans = this.clientLoanMap.values().size() + 1;
            Loan.setCounterLoanID(Loan.getCounterLoanID() - nrLoans);
            this.clientLoanMap.remove(client);
        }
    }

    //RemoveLoan pe baza de imprumut - nu am un camp unic sa zic ca pot sa l identific usor asa ca o sa o singura functie pt asta
    public void removeLoan(Loan loan) {
        Timestamp.timestamp("Bank: removeLoan");
        int c = 0;
        for (Map.Entry<Client, List<Loan>> x : this.clientLoanMap.entrySet()) {
            if (x.getValue().remove(loan)) {
                c++;
                //scad din counter si refac ID-urile in functie de contul eliminat
                Loan.setCounterLoanID(Loan.getCounterLoanID() - 1);
                List<Loan> dummy = x.getValue();
                for (Loan y : dummy) {
                    if (y.getLoanID() > loan.getLoanID()) {
                        y.setLoanID(y.getLoanID() - 1);
                        dummy.set(dummy.indexOf(y), y);
                    }
                }
                this.clientLoanMap.replace(x.getKey(), dummy);
            }
        }
        if (c == 0) {
            System.out.println(loan.toString() + ", nu a fost gasit.\n");
        }
    }

    //RemoveClientBankAccount pe baza de CNP - pentru citire
    public void removeClientBankAccount(String cnp) {
        Timestamp.timestamp("Bank: removeClientBankAccount");
        Client evitConcurrentModificationException = new Client();
        int c = 0;
        int nrConturi = 0;
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            if (x.getKey().getCnp().equals(cnp)) {
                evitConcurrentModificationException = x.getKey();
                c++;
                nrConturi = x.getValue().size();
            }
        }
        if (c != 0) {
            BankAccount.setCounterBankAccountID(BankAccount.getCounterBankAccountID() - nrConturi);
            this.clientBankAccountMap.remove(evitConcurrentModificationException);
        }
    }

    //RemoveClientBankAccountt pe baza de client - pentru apel
    public void removeClientBankAccount(Client client) {
        Timestamp.timestamp("Bank: removeClientBankAccount");
        int nrConturi = 0;
        if (this.clientBankAccountMap.containsKey(client)) {
            nrConturi = this.clientBankAccountMap.values().size() + 1;
            BankAccount.setCounterBankAccountID(BankAccount.getCounterBankAccountID() - nrConturi);
            this.clientBankAccountMap.remove(client);
        }
    }

    //RemoveAccount pe baza de IBAN - pentru citire
    public void removeAccount(String IBAN) {
        Timestamp.timestamp("Bank: removeClientBankAccount");
        int c = 0;
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            BankAccount local = null;
            List<BankAccount> dummy = x.getValue();
            for (BankAccount y : dummy) {
                if (y.getIBAN().equals(IBAN)) {
                    local = y;
                }
            }
            if (dummy.contains(local)) {
                c++;
                x.getValue().remove(local);
                BankAccount.setCounterBankAccountID(BankAccount.getCounterBankAccountID() - 1);//am mai declarat un BankAccount in cadrul functiei
                //alta metoda de normalizare al idexului
                for (BankAccount y : dummy) {
                    assert local != null;
                    if (y.getBankAccountID() > local.getBankAccountID()) {
                        y.setBankAccountID(y.getBankAccountID() - 1);
                        dummy.set(dummy.indexOf(y), y);
                    }
                }
                this.clientBankAccountMap.replace(x.getKey(), dummy);
            }
        }
        if (c == 0) {
            System.out.println("Contul " + IBAN.toString() + ", nu a fost gasit.\n");
        }
    }

    //RemoveAccount pe baza de cont - pentru apel
    public void removeAccount(BankAccount bankAccount) {
        Timestamp.timestamp("Bank: removeAccount");
        int c = 0;
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            if (x.getValue().remove(bankAccount)) {
                c++;
                //scad din counter si refac ID-urile in functie de contul eliminat
                BankAccount.setCounterBankAccountID(BankAccount.getCounterBankAccountID() - 1);
                List<BankAccount> dummy = x.getValue();
                for (BankAccount y : dummy) {
                    if (y.getBankAccountID() > bankAccount.getBankAccountID()) {
                        y.setBankAccountID(y.getBankAccountID() - 1);
                        dummy.set(dummy.indexOf(y), y);
                    }
                }
                this.clientBankAccountMap.replace(x.getKey(), dummy);
            }
        }
        if (c == 0) {
            System.out.println(bankAccount.toString() + ", nu a fost gasit.\n");
        }
    }

    //Transfer de fonduri intre conturi (deposit+withdraw)
    public void interBanking(String receiver, String sender, double value) throws TransactionException {
        Timestamp.timestamp("Bank: interBanking");
        int c = 0, c1 = 0, c2 = 0, c3 = 0, k = 0;
        if (value <= 0) {
            System.out.println("De ce incerci asta? Fa-o invers :)");
        } else {
            BankAccount dupeReceiver = null;
            BankAccount dupeSender = null;
            for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
                for (BankAccount y : x.getValue()) {
                    if (y.getIBAN().equals(receiver)) {
                        dupeReceiver = y;
                        c++;
                        if (!y.getClosingDate().equals("-"))
                            c2++;
                    }
                    if (y.getIBAN().equals(sender)) {
                        dupeSender = y;
                        c1++;
                        if (!y.getClosingDate().equals("-"))
                            c3++;
                    }
                }
            }
            if (c != 0 && c1 != 0 && c2 == 0 && c3 == 0) {
                dupeSender.withdraw(value);
                System.out.println("Transferul din contul " + dupeSender.getIBAN() + " in contul " + dupeReceiver.getIBAN() + " in valoare de " + FormatDouble.format(value) + " " + dupeSender.getCurrency() + " a avut succes!");
                value = CurrencyExchange.convertTransfer(value, dupeReceiver.getCurrency(), dupeSender.getCurrency());
                dupeReceiver.deposit(value);
                k++;
            }
            if (c2 != 0 && c3 == 0)
                System.out.println("Nu se poate face transferul! Contul " + Objects.requireNonNull(dupeReceiver).getIBAN() + " a fost inchis!");
            else if (c2 == 0 && c3 != 0)
                System.out.println("Nu se poate face transferul! Contul " + Objects.requireNonNull(dupeSender).getIBAN() + " a fost inchis!");
            else if (c2 != 0)
                System.out.println("Nu se poate face transferul! Ambele conturi au fost inchise!");
            else if (c != 0 && c1 == 0)
                System.out.println("Nu exista contul in care transferati");
            else if (c1 != 0 && c == 0)
                System.out.println("Nu exista contul din care transferati");
            else if (k == 0)
                System.out.println("Nu exista nici un cont");
        }
    }

    //Balance check folosind clasa serviciu
    public void balanceCheck(BankAccount bankAccount) {
        Timestamp.timestamp("Bank: balanceCheck");
        int c = 0;
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            if (x.getValue().contains(bankAccount)) {
                c++;
                for (BankAccount y : x.getValue()) {
                    if (y.equals(bankAccount)) {
                        balanceCheck(y.getBalance(), y.getCurrency());
                    }
                }
            }
        }
        if (c == 0)
            System.out.println("Nu exista contul selectat, va rugam verificati informatiile");
    }

    //Metoda ce filtreaza extrasele de cont dupa o data anume
    public void filterByDate(String IBAN, String startDate, String sign) {
        try {
            Timestamp.timestamp("Bank: filterByDate");
            BankAccountValidation.validateIBAN(IBAN);
            BankAccountValidation.validateOpeningDate(startDate);
            BankValidation.validateSign(sign);
            WriterFiles.getInstance().writerAccountStatementTemp(IBAN, filterDate(IBAN, startDate, sign));
        } catch (BankAccountException | BankException e) {
            e.printStackTrace();
        }
    }

    //Metoda ce filtreaza extrasele de cont dupa un interval
    public void filterByDate(String IBAN, String startDate, String sign, String stopDate) {
        try {
            Timestamp.timestamp("Bank: filterByDate");
            BankAccountValidation.validateIBAN(IBAN);
            BankAccountValidation.validateOpeningDate(startDate);
            BankValidation.validateSign(sign);
            WriterFiles.getInstance().writerAccountStatementTemp(IBAN, filterDate(IBAN, startDate, sign, stopDate));

        } catch (BankAccountException | BankException e) {
            e.printStackTrace();
        }
    }

    //Metoda ce filtreaza extrasele de cont dupa o valoare anume
    public void filterByValue(String IBAN, double value, String sign) {
        try {
            Timestamp.timestamp("Bank: filterByValue");
            BankAccountValidation.validateIBAN(IBAN);
            BankValidation.validateSign(sign);
            WriterFiles.getInstance().writerAccountStatementTemp(IBAN, filterValue(IBAN, value, sign));
        } catch (BankAccountException | BankException e) {
            e.printStackTrace();
        }
    }

    //Metoda ce filtreaza extrasele de cont dupa un interval
    public void filterByValue(String IBAN, double minValue, String sign, double maxValue) {
        try {
            Timestamp.timestamp("Bank: filterByValue");
            BankAccountValidation.validateIBAN(IBAN);
            BankValidation.validateSign(sign);
            WriterFiles.getInstance().writerAccountStatementTemp(IBAN, filterValue(IBAN, minValue, sign, maxValue));
        } catch (BankAccountException | BankException e) {
            e.printStackTrace();
        }
    }

    //Metoda ce filtreaza extrasele de cont dupa un tip de valuta
    public void filterByCurrency(String IBAN, String currency, String sign) {
        try {
            Timestamp.timestamp("Bank: filterByValue");
            BankAccountValidation.validateIBAN(IBAN);
            BankValidation.validateSign(sign);
            WriterFiles.getInstance().writerAccountStatementTemp(IBAN, filterCurrency(IBAN, currency, sign));
        } catch (BankAccountException | BankException e) {
            e.printStackTrace();
        }
    }

    //Plata catre furnizorii deja existenti
    public void paymentUtilies(String Sender, String Receiver, double value) throws ProviderDBException, TransactionException {
        Timestamp.timestamp("Bank: paymentUtilies");
        int c = 0, c1 = 0;
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            for (BankAccount y : x.getValue()) {
                if (y.getIBAN().equals(Sender)) {
                    c++;
                    if (!y.getClosingDate().equals("-"))
                        c1++;
                    else {
                        System.out.print("\tClientul " + x.getKey().getFirstName() + " " + x.getKey().getLastName());
                        y.paymentUtilies(Receiver, value);
                    }
                }
            }
        }
        if (c == 0)
            System.out.println("Nu exista contul " + Sender);
        else if (c1 != 0)
            System.out.println("Nu se poate plati providerul! Contul " + Sender + " a fost inchis!");

    }

    //CurrencyExchange pe cont
    public void currencyExchange(BankAccount bankAccount, String wantedCurrency) {
        Timestamp.timestamp("Bank: currencyExchange");
        int c = 0, c1 = 0;
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            if (x.getValue().contains(bankAccount)) {
                c++;
                if (!bankAccount.getClosingDate().equals("-"))
                    c1++;
                else {
                    System.out.println("Conversie realizata! (" + bankAccount.getCurrency() + "->" + wantedCurrency + ")");
                    bankAccount.currencyExchange(wantedCurrency);
                }
            }
        }
        if (c == 0)
            System.out.println("Nu exista contul " + bankAccount.getIBAN());
        else if (c1 != 0)
            System.out.println("Nu se poate plati providerul! Contul " + bankAccount.getIBAN() + " a fost inchis!");
    }

    //CurrencyExchange pe IBAN
    public void currencyExchange(String IBAN, String wantedCurrency) {
        Timestamp.timestamp("Bank: currencyExchange");
        int c = 0, c1 = 0;
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            for (BankAccount y : x.getValue()) {
                if (y.getIBAN().equals(IBAN)) {
                    c++;
                    if (!y.getClosingDate().equals("-"))
                        c1++;
                    else {
                        System.out.println("Conversie realizata! (" + y.getCurrency() + "->" + wantedCurrency + ")");
                        y.currencyExchange(wantedCurrency);
                    }
                }
            }
        }

        if (c == 0)
            System.out.println("Nu exista contul " + IBAN);
        else if (c1 != 0)
            System.out.println("Nu se potae face conversia! Contul " + IBAN + " a fost inchis!");
    }

    //Plata unei rate mai mari decat cea fixa
    public void payLoan(BankAccount bankAccount, Loan loan, double value) throws TransactionException {
        Timestamp.timestamp("Bank: payLoan");
        Client dummy = new Client();
        Loan dupe = new Loan();
        Loan.setCounterLoanID(Loan.getCounterLoanID() - 1);
        int c = 0, c1 = 0, c2 = 0;
        for (Map.Entry<Client, List<Loan>> x : this.clientLoanMap.entrySet())
            if (x.getValue().contains(loan)) {
                dummy = x.getKey();
                c++;
                for (Loan z : x.getValue())
                    if (z.equals(loan))
                        dupe = z;
            }
        for (Map.Entry<Client, List<BankAccount>> y : this.clientBankAccountMap.entrySet())
            if (y.getValue().contains(bankAccount)) {
                c1++;
                if (!(y.getKey().equals(dummy))) {
                    System.out.println("Cele doua produse nu apartin aceluiasi client");
                } else {
                    if (!bankAccount.getClosingDate().equals("-"))
                        c2++;
                    else {
                        bankAccount.withdraw(CurrencyExchange.convertTransfer(value, bankAccount.getCurrency(), loan.getCurrency()));
                        System.out.print("Clientul " + dummy.getFirstName() + " " + dummy.getLastName());
                        dupe.payMonthlyRate(value);
                    }
                }
            }
        if (c == 0 && c1 != 0)
            System.out.println("Imprumutul nu exista");
        else if (c != 0 && c1 == 0)
            System.out.println("Contul " + bankAccount.getIBAN() + " nu exista.");
        else if (c == 0)
            System.out.println("Nici contul, nici imprumutul nu exista");
        else if (c2 != 0)
            System.out.println("Nu se poate plati rata! Contul " + bankAccount.getIBAN() + " a fost inchis!");
    }

    //Plata unei rate fixe
    public void payLoan(BankAccount bankAccount, Loan loan) throws TransactionException {
        Timestamp.timestamp("Bank: payLoan");
        Client dummy = new Client();
        Loan dupe = new Loan();
        int c = 0, c1 = 0, c2 = 0;
        for (Map.Entry<Client, List<Loan>> x : this.clientLoanMap.entrySet())
            if (x.getValue().contains(loan)) {
                dummy = x.getKey();
                c++;
                for (Loan z : x.getValue())
                    if (z.equals(loan))
                        dupe = z;
            }
        for (Map.Entry<Client, List<BankAccount>> y : this.clientBankAccountMap.entrySet())
            if (y.getValue().contains(bankAccount)) {
                c1++;
                if (!(y.getKey().equals(dummy))) {
                    System.out.println("Cele doua produse nu apartin aceluiasi client");
                } else {
                    if (!bankAccount.getClosingDate().equals("-"))
                        c2++;
                    else {
                        bankAccount.withdraw(CurrencyExchange.convertTransfer(loan.valueMonthlyRate(), bankAccount.getCurrency(), loan.getCurrency()));
                        System.out.print("Clientul " + dummy.getFirstName() + " " + dummy.getLastName());
                        dupe.payMonthlyRate();
                    }
                }
            }
        if (c == 0 && c1 != 0)
            System.out.println("Imprumutul nu exista");
        else if (c != 0 && c1 == 0)
            System.out.println("Contul " + bankAccount.getIBAN() + " nu exista.");
        else if (c == 0)
            System.out.println("Nici contul, nici imprumutul nu exista");
        else if (c2 != 0)
            System.out.println("Nu se poate plati rata! Contul " + bankAccount.getIBAN() + " a fost inchis!");
    }

    //Adaugarea unui provider
    public void addProvider(ProviderDB providerDB) {
        Timestamp.timestamp("Bank: addProvider");
        ToProviders.addProvider(providerDB);
    }

    //Adaugarea unei intregi liste de provideri (pentru incarcare date)
    public void addProvider(List<ProviderDB> providerDBList) {
        Timestamp.timestamp("Bank: addProvider");
        ToProviders.addProvider(providerDBList);
    }

    //Stergerea unui provider folosind colectia de informatii
    public void removeProvider(ProviderDB providerDB) {
        Timestamp.timestamp("Bank: removeProvider");
        ToProviders.removeProvider(providerDB);
    }

    //Stergerea unui provider pe baza de cont
    public void removeProvider(String IBAN) throws BankAccountException {
        Timestamp.timestamp("Bank: removeProvider");
        ToProviders.removeProvider(IBAN);
    }

    //Afisarea providerilor o data cu banca
    public StringBuilder checkProviders(StringBuilder c) {
        Timestamp.timestamp("Bank: checkProviders");
        if (ToProviders.getInstance() == null)
            System.out.println("Inca nu exista nici un provider");
        else {
            c.append("\tAvem urmatorii provideri:\n");
            for (ProviderDB x : ToProviders.getProviderDBList())
                c.append(" ~ " + x.toString() + "\n");
        }
        return c;
    }

    //Face update la fisierul Bank.txt de intrare
    public String bankReaderUpdate() {
        Timestamp.timestamp("Bank: bankReaderUpdate");
        return this.name + "," + this.location + "\n";
    }

    //Face update la fisierul BankAccount.txt de intrare
    public List<String> bankAccountReaderUpdate() {
        Timestamp.timestamp("Bank: bankAccountReaderUpdate");
        List<String> local = new ArrayList<>();
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            for (BankAccount y : x.getValue()) {
                local.add(x.getKey().getCnp() + "," + y.bankAccountReaderUpdate() + "\n");
            }
        }
        return local;
    }

    //Face update la fisierul Card.txt de intrare
    public List<String> cardReaderUpdate() {
        Timestamp.timestamp("Bank: cardReaderUpdate");
        List<String> local = new ArrayList<>();
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            for (BankAccount y : x.getValue()) {
                for (Card z : y.getCardList()) {
                    local.add(y.getIBAN() + "," + z.cardReaderUpdate() + "\n");
                }
            }
        }
        return local;
    }

    //Face update la fisierul Client.txt de intrare
    public List<String> clientReaderUpdate() {
        Timestamp.timestamp("Bank: clientReaderUpdate");
        List<Client> localClient = new ArrayList<>();
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            localClient.add(x.getKey());
        }

        for (Map.Entry<Client, List<Loan>> y : this.clientLoanMap.entrySet()) {
            if (!localClient.contains(y.getKey()))
                localClient.add(y.getKey());
        }

        List<String> local = new ArrayList<>();
        for (Client z : localClient) {
            local.add(z.clientReaderUpdate() + "\n");
        }

        return local;
    }

    //Face update la fisierul Loan.txt de intrare
    public List<String> loanReaderUpdate() {
        Timestamp.timestamp("Bank: loanReaderUpdate");
        List<String> local = new ArrayList<>();
        for (Map.Entry<Client, List<Loan>> x : this.clientLoanMap.entrySet()) {
            for (Loan y : x.getValue()) {
                local.add(x.getKey().getCnp() + "," + y.loanReaderUpdate() + "\n");
            }
        }
        return local;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Bank bank = (Bank) obj;
        if (!Objects.equals(this.BankID, bank.BankID))
            return false;
        if (!Objects.equals(this.name, bank.name))
            return false;
        if (!Objects.equals(this.location, bank.location))
            return false;
        if (!Objects.equals(this.clientBankAccountMap, bank.clientBankAccountMap))
            return false;
        if (!Objects.equals(this.clientLoanMap, bank.clientLoanMap))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder c = new StringBuilder();
        c.append("\n\t\t" + "[" + this.BankID + "]" + " Banca " + this.name + " cu ID-ul " + this.BankID + " aflata la " + this.location);
        if (this.clientBankAccountMap.size() == 0 && this.clientLoanMap.size() == 0)
            c.append(" nu are clienti.\n");
        else {
            List<Client> dummy = new ArrayList<>(); //lista locala pentru toti clientii indiferent de produs
            for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
                dummy.add(x.getKey());
            }
            for (Map.Entry<Client, List<Loan>> y : this.clientLoanMap.entrySet()) {
                if (!dummy.contains(y.getKey()))
                    dummy.add(y.getKey());
            }
            if (dummy.size() == 1) //daca ai un singur client
            {
                c.append(" are un singur client.\n");
                int contCont = 0, contImpr = 0;
                if (this.clientBankAccountMap.containsKey(dummy.get(0))) {
                    for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
                        if (x.getKey().equals(dummy.get(0)) && (!x.getValue().isEmpty())) {
                            c.append(dummy.get(0).toString() + "\n  ->CONTURI:\n");
                            for (BankAccount y : x.getValue())
                                c.append(y.toString() + "\n");
                            contCont++;
                        }
                    }
                }
                if (this.clientLoanMap.containsKey(dummy.get(0))) {
                    if (this.clientLoanMap.containsKey(dummy.get(0))) {
                        for (Map.Entry<Client, List<Loan>> x : this.clientLoanMap.entrySet()) {
                            if (x.getKey().equals(dummy.get(0)) && (!x.getValue().isEmpty())) {
                                if (contCont == 0)
                                    c.append(dummy.get(0).toString() + "\n");
                                c.append("  ->IMPRUMUTURI:\n");
                                for (Loan y : x.getValue())
                                    c.append(y.toString() + "\n");
                                contImpr++;
                            }
                        }
                    }
                }
                if (contCont == 0)
                    c.append(" ->NU ARE CONTURI\n");
                if (contImpr == 0)
                    c.append(" ->NU ARE IMPRUMUTURI\n");
                c.append("\n");
            } else {
                c.append(" are urmatorii clienti:\n"); //daca am mai multi clienti
                for (Client local : dummy) {
                    int contCont = 0, contImpr = 0;
                    if (this.clientBankAccountMap.containsKey(local)) {
                        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
                            if (x.getKey().equals(local) && (!x.getValue().isEmpty())) // daca se foloseste addBankAccount sa nu considere ca are conturi
                            {
                                c.append(local.toString() + "\n  ->CONTURI:\n");
                                for (BankAccount y : x.getValue())
                                    c.append(y.toString() + "\n");
                                contCont++;
                            }
                        }
                    }
                    if (this.clientLoanMap.containsKey(local)) {
                        if (this.clientLoanMap.containsKey(local)) {
                            for (Map.Entry<Client, List<Loan>> x : this.clientLoanMap.entrySet()) {
                                if (x.getKey().equals(local) && (!x.getValue().isEmpty())) // daca se foloseste addLoan sa nu considere ca are conturi
                                {
                                    if (contCont == 0)
                                        c.append(local.toString() + "\n");
                                    c.append("  ->IMPRUMUTURI:\n");
                                    for (Loan y : x.getValue())
                                        c.append(y.toString() + "\n");
                                    contImpr++;
                                }
                            }
                        }
                    }
                    if (contCont == 0 && contImpr != 0)
                        c.append(" ->NU ARE CONTURI\n");
                    else if (contImpr == 0 && contCont != 0)
                        c.append("  ->NU ARE IMPRUMUTURI\n");
                    else if (contImpr == 0)
                        c.append(local.toString() + " nu are nici conturi, nici imprumuturi.\n");
                    c.append("\n");
                }
            }
        }
        this.checkProviders(c);
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.BankID, this.name, this.location, this.clientBankAccountMap, this.clientLoanMap);
    }
}