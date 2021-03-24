import Service.AccountStatement;

import java.util.*;

public class Bank {
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

    //Clasic
    public Bank(String name, String location, Map<Client, List<BankAccount>> clientBankAccountMap, Map<Client, List<Loan>> clientListMap) {
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
    public Bank(String name, String location, Client clientBankAccount, List<BankAccount> bankAccounts, Client clientLoan, List<Loan> loans) {
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
    public Bank(String name, String location, Client clientBankAccount, BankAccount bankAccounts, Client clientLoan, Loan loans) {
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

    public void setName(String name) {
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

    //Caz particular per Client pt conturi
    public void normalizeBankIndex(Client client) {
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
        if (this.clientBankAccountMap.containsKey(client)) {
            System.out.println("Clientul " + client.getFirst_name() + " " + client.getLast_name() + " exista deja!\n");
        } else {
            List<BankAccount> dummy = new ArrayList<>();
            this.clientBankAccountMap.put(client, dummy);
        }
    }

    //AddBankAccount pe baza de client - pentru citire
    public void addBankAccount(Client client, BankAccount bankAccount) {
        int avem = 0, c = 0;
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            for (BankAccount y : x.getValue()) {
                if (y.equals(bankAccount)) {
                    System.out.println("Contul " + bankAccount.getIBAN() + " exista deja pentru clientul " + x.getKey().getFirst_name() + " " + x.getKey().getLast_name() + "\n");
                    c++;
                }
            }
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
        if (this.clientLoanMap.containsKey(client)) {
            System.out.println("Clientul " + client.getFirst_name() + " " + client.getLast_name() + " exista deja!\n");
        } else {
            List<Loan> dummy = new ArrayList<>();
            this.clientLoanMap.put(client, dummy);
        }
    }

    //AddLoan pe baza de client - pentru citire
    public void addLoan(Client client, Loan loan) {
        int avem = 0, c = 0;
        for (Map.Entry<Client, List<Loan>> x : this.clientLoanMap.entrySet()) {
            for (Loan y : x.getValue()) {
                if (y.equals(loan)) {
                    System.out.println("Imprumutul exista deja pentru clientul " + x.getKey().getFirst_name() + " " + x.getKey().getLast_name() + "\n");
                    c++;
                }
            }
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
        int c=0;
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet())
        {
            if (x.getValue().contains(bankAccount))
            {
                c++;
                for (BankAccount y : x.getValue()) {
                    if(y.equals(bankAccount)) {
                        if (!y.cardList.contains(card)&&y.getClosingDate()==null)
                            y.addCard(card);
                        else
                            if(y.getClosingDate()!=null)
                                System.out.println("Contul " + bankAccount.getIBAN() + " a fost inchis deja, va rugam nu adaugati carduri.");
                    }
                }
            }
        }
        if(c==0)
            System.out.println("Contul " + bankAccount.getIBAN() + " nu exista.");
    }

    //RemoveCard pe baza de card
    public void removeCard(Card card) {
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

    //RemoveClientLoan pe baza de CNP - pentru citire
    public void removeClientLoan(String cnp) {
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
        int nrLoans = 0;
        if (this.clientLoanMap.containsKey(client)) {
            nrLoans = this.clientLoanMap.values().size() + 1;
            Loan.setCounterLoanID(Loan.getCounterLoanID() - nrLoans);
            this.clientLoanMap.remove(client);
        }
    }

    //RemoveLoan pe baza de imprumut - nu am un camp unic sa zic ca pot sa l identific usor asa ca o sa o singura functie pt asta
    public void removeLoan(Loan loan) {
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
        int nrConturi = 0;
        if (this.clientBankAccountMap.containsKey(client)) {
            nrConturi = this.clientBankAccountMap.values().size() + 1;
            BankAccount.setCounterBankAccountID(BankAccount.getCounterBankAccountID() - nrConturi);
            this.clientBankAccountMap.remove(client);
        }
    }

    //RemoveAccount pe baza de IBAN - pentru citire
    public void removeAccount(String IBAN) {
        int c = 0;
        for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
            List<BankAccount> dummy = x.getValue();
            BankAccount local = new BankAccount();
            for (BankAccount y : dummy) {
                if (y.getIBAN().equals(IBAN)) {
                    local = y;
                }
            }
            if (dummy.contains(local)) {
                c++;
                x.getValue().remove(local);
                BankAccount.setCounterBankAccountID(BankAccount.getCounterBankAccountID() - 2);//am mai declarat un BankAccount in cadrul functiei
                //alta metoda de normalizare al idexului
                for (BankAccount y : dummy) {
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
    public void interBanking(String receiver, String sender, double value)
    {
        int c=0, c1=0, k=0;
        if(value<=0) {
            System.out.println("De ce incerci asta? Fa-o invers :)");
        }
        else {
            BankAccount dupeReceiver = new BankAccount();
            BankAccount dupeSender = new BankAccount();
            BankAccount.setCounterBankAccountID(BankAccount.getCounterBankAccountID()-2);
            for (Map.Entry<Client, List<BankAccount>> x : this.clientBankAccountMap.entrySet()) {
                for (BankAccount y : x.getValue()) {
                    if (y.getIBAN().equals(receiver)) {
                        dupeReceiver = y;
                        c++;
                    }
                    if (y.getIBAN().equals(sender)) {
                        dupeSender=y;
                        c1++;
                    }
                }
            }
            if(c!=0&&c1!=0) {
                System.out.println("Transfer realizat cu succes");
                dupeReceiver.deposit(value);
                dupeSender.withdraw(value);
                k++;
            }
            if (c != 0&&c1==0)
                System.out.println("Nu exista contul in care transferati");
            else if (c1 != 0&&c==0)
                System.out.println("Nu exista contul din care transferati");
            else if(k==0)
                System.out.println("Nu exista nici un cont");
        }
    }

    //Balance check folosind clasa serviciu
    public void balanceCheck(BankAccount bankAccount)
    {
        int c=0;
        for(Map.Entry<Client,List<BankAccount>> x: this.clientBankAccountMap.entrySet())
        {
            if(x.getValue().contains(bankAccount)) {
                c++;
                for (BankAccount y : x.getValue()) {
                    if(y.equals(bankAccount)) {
                        new AccountStatement().balanceCheck(y.getBalance(),y.getCurrency());
                    }
                }
            }
        }
        if(c==0)
            System.out.println("Nu exista contul selectat, va rugam verificati informatiile");
    }

    @Override
    public boolean equals(Object obj) {
        if (this != obj)
            return false;
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
                                c.append(y.toString() + ".\n");
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
                                    c.append(y.toString() + ".\n");
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
                                    c.append(y.toString() + ".\n");
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
                                        c.append(y.toString() + ".\n");
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
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.BankID, this.name, this.location, this.clientBankAccountMap, this.clientLoanMap);
    }
}