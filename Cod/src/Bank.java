import java.util.*;

public class Bank {
    protected static int counterBankID;
    protected int BankID;
    protected String name;
    protected String location;
    protected Map <Client, List<BankAccount>> clientBankAccountMap;
    protected Map <Client, List<Loan>> clientLoanMap;

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
        for(Map.Entry<Client, List<BankAccount>> x: clientBankAccountMap.entrySet())
        {
            normalizeBankIndex(x.getKey());
        }

        this.clientLoanMap = clientListMap;
        for(Map.Entry<Client, List<Loan>> x: clientLoanMap.entrySet())
        {
            normalizeLoanIndex(x.getKey());
        }
    }

    //Doar un client cu mai multe conturi
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

    //Doar un client cu un singur cont
    public Bank(String name, String location, Client clientBankAccount, BankAccount bankAccounts, Client clientLoan, Loan loans) {
        counterBankID++;
        this.BankID = counterBankID;
        this.name = name;
        this.location = location;

        clientBankAccountMap = new HashMap<>();
        List <BankAccount> localBank = new ArrayList<BankAccount>();
        localBank.add(bankAccounts);
        this.clientBankAccountMap.put(clientBankAccount, localBank);
        normalizeBankIndex(clientBankAccount);

        clientLoanMap = new HashMap<>();
        List <Loan> localLoan = new ArrayList<Loan>();
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
    public void normalizeBankIndex(Client client)
    {
        for(Map.Entry<Client,List<BankAccount>> x: this.clientBankAccountMap.entrySet())
        {
            if(x.getKey().equals(client)) {
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
    public void normalizeBankIndex()
    {
        for(Map.Entry<Client,List<BankAccount>> x: this.clientBankAccountMap.entrySet())
        {
            List<BankAccount> dummy = x.getValue();
            int poz=1;
            for(BankAccount y: dummy)
            {
                y.setBankAccountID(poz);
                poz++;
            }
        }
    }

    //Caz particular per Client pt imprumuturi
    public void normalizeLoanIndex(Client client)
    {
        for(Map.Entry<Client,List<Loan>> x: this.clientLoanMap.entrySet())
        {
            if(x.getKey().equals(client)) {
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
    public void normalizeLoanIndex()
    {
        for(Map.Entry<Client,List<Loan>> x: this.clientLoanMap.entrySet())
        {
            List<Loan> dummy = x.getValue();
            int poz=1;
            for(Loan y: dummy)
            {
                y.setLoanID(poz);
                poz++;
            }
        }
    }

    //AddClient pe baza de client - nu are sens altfel (avem nevoie de detalii)
    public void addClient(Client client)
    {
        if(this.clientBankAccountMap.containsKey(client))
        {
            System.out.println("Clientul "+client.first_name+" "+client.last_name+" exista deja!\n");
        }
        else {
            List<BankAccount> dummy = new ArrayList<>();
            this.clientBankAccountMap.put(client, dummy);
        }
    }

    //RemoveClient pe baza de CNP - pentru citire
    public void removeClient(String cnp)
    {
        for(Map.Entry<Client, List<BankAccount>> x: this.clientBankAccountMap.entrySet())
        {
            if(x.getKey().getCnp().equals(cnp))
            {
                this.clientBankAccountMap.remove(x.getKey());
            }
        }
    }

    //RemoveClient pe baza de client - pentru apel
    public void removeClient(Client client)
    {
        this.clientBankAccountMap.remove(client);
    }

    //AddBankAccount pe baza de client - pentru citire
    public void addBankAccount(Client client, BankAccount bankAccount)
    {
        int avem = 0;
        for(Map.Entry<Client, List<BankAccount>> x: this.clientBankAccountMap.entrySet())
        {
            if(x.getKey().equals(client))
            {
                avem++;
                List<BankAccount> dummy = x.getValue();
                if(dummy.contains(bankAccount))
                {
                    System.out.println("Contul "+bankAccount.getIBAN()+" exista deja pentru clientul "+client.first_name+" "+client.last_name+"\n");
                }
                else
                {
                    dummy.add(bankAccount);
                    normalizeBankIndex(client);
                    this.clientBankAccountMap.replace(x.getKey(),dummy);
                }
            }
        }
        //se va apela mai intai addClient cand nu avem clientul deja
        if(avem == 0)
        {
            addClient(client);
            List<BankAccount> dummy = new ArrayList<>();
            dummy.add(bankAccount);
            normalizeBankIndex(client);
            this.clientBankAccountMap.put(client,dummy);
        }
    }

    //RemoveAccount pe baza de IBAN - pentru citire
    public void removeAccount(String IBAN)
    {
        for(Map.Entry<Client, List<BankAccount>> x: this.clientBankAccountMap.entrySet())
        {
            List<BankAccount> dummy= x.getValue();
            BankAccount local = new BankAccount();
            for(BankAccount y : dummy)
            {
                if(y.getIBAN().equals(IBAN))
                {
                    local=y;
                }
            }
            if(dummy.contains(local))
            {
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
            else
            {
                System.out.println("Contul "+ IBAN.toString() + ", nu a fost gasit.\n");
            }
        }
    }

    //RemoveAccount pe baza de cont - pentru apel
    public void removeAccount(BankAccount bankAccount)
    {
        for(Map.Entry<Client, List<BankAccount>> x: this.clientBankAccountMap.entrySet())
        {
            if(x.getValue().remove(bankAccount))
            {
                //scad din counter si refac ID-urile in functie de contul eliminat
                BankAccount.setCounterBankAccountID(BankAccount.getCounterBankAccountID() - 1);
                List<BankAccount> dummy= x.getValue();
                for (BankAccount y : dummy) {
                    if (y.getBankAccountID() > bankAccount.getBankAccountID()) {
                        y.setBankAccountID(y.getBankAccountID() - 1);
                        dummy.set(dummy.indexOf(y), y);
                    }
                }
                this.clientBankAccountMap.replace(x.getKey(), dummy);
            }
            else
            {
                System.out.println(bankAccount.toString() + ", nu a fost gasit.\n");
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(this!=obj)
            return false;
        if(obj==null)
            return false;
        if(this.getClass()!=obj.getClass())
            return false;
        Bank bank= (Bank) obj;
        if(!Objects.equals(this.BankID, bank.BankID))
            return false;
        if(!Objects.equals(this.name, bank.name))
            return false;
        if(!Objects.equals(this.location, bank.location))
            return false;
        if(!Objects.equals(this.location, bank.location))
            return false;
        if(!Objects.equals(this.clientBankAccountMap, bank.clientBankAccountMap))
            return false;
        if(!Objects.equals(this.clientLoanMap, bank.clientLoanMap))
            return false;
        return true;
    }

    //REZOLVA CU AFISAREA
    @Override
    public String toString() {
        StringBuilder c = new StringBuilder();
        c.append("\t\t"+"["+this.BankID+"]"+" Banca " + this.name + " cu ID-ul " + this.BankID + " aflata la " + this.location);
        if(this.clientBankAccountMap.size()==0)
            c.append(" nu are clienti.\n");
        else
            if(this.clientBankAccountMap.size()==1)
            {
                c.append(" are un singur client:\n");
                for(Map.Entry<Client, List<BankAccount>> x: this.clientBankAccountMap.entrySet())
                {
                    if(x.getValue().size()==0)
                        c.append(x.getKey().toString() + " nu are conturi bancare.");
                    else
                        if(x.getValue().size()==1)
                            c.append(x.getKey().toString() + " are un singur cont bancar:\n" + x.getValue().toString() + "\n");
                        else {
                            c.append(x.getKey().toString() + " are urmatoarele conturi bancare:\n");
                            for(BankAccount bankAccount : x.getValue())
                                c.append(bankAccount.toString()+"\n");
                        }
                }
            }
            else
            {
                c.append(" are urmatorii clienti:\n");
                for(Map.Entry<Client, List<BankAccount>> x: this.clientBankAccountMap.entrySet())
                {
                    if(x.getValue().size()==0)
                        c.append(x.getKey().toString() + " nu are conturi bancare.");
                    else
                    if(x.getValue().size()==1)
                        c.append(x.getKey().toString() + " are un singur cont bancar:\n" + x.getValue().toString());
                    else {
                        c.append(x.getKey().toString() + " are urmatoarele conturi bancare:\n");
                        for(BankAccount bankAccount : x.getValue())
                            c.append(bankAccount.toString()+"\n");
                    }
                    c.append("\n\n");
                }
            }
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.BankID, this.name, this.location, this.clientBankAccountMap, this.clientLoanMap);
    }
}