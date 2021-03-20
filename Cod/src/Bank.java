import java.util.*;

public class Bank {
    protected static int counterBankID;
    protected int BankID;
    protected String name;
    protected String location;
    protected Map <Client, List<BankAccount>> clientBankAccountMap;

    public Bank() {
        counterBankID++;
        this.BankID = counterBankID;
        this.name = "";
        this.location = "";
        clientBankAccountMap = new HashMap<>();
    }

    //Clasic
    public Bank(String name, String location, Map<Client, List<BankAccount>> clientBankAccountMap) {
        counterBankID++;
        this.BankID = counterBankID;
        this.name = name;
        this.location = location;
        this.clientBankAccountMap = clientBankAccountMap;
    }

    //Doar un client cu mai multe conturi
    public Bank(String name, String location, Client client, List<BankAccount> bankAccounts) {
        counterBankID++;
        this.BankID = counterBankID;
        this.name = name;
        this.location = location;
        clientBankAccountMap = new HashMap<>();
        this.clientBankAccountMap.put(client, bankAccounts);
    }

    //Doar un client cu un singur cont
    public Bank(String name, String location, Client client, BankAccount bankAccounts) {
        counterBankID++;
        this.BankID = counterBankID;
        this.name = name;
        this.location = location;
        clientBankAccountMap = new HashMap<>();
        List <BankAccount> local = new ArrayList<BankAccount>();
        local.add(bankAccounts);
        this.clientBankAccountMap.put(client, local);
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

    //AddClient - TESTEAZA
    public void addClient(Client client)
    {
        if(this.clientBankAccountMap.containsKey(client))
        {
            System.out.println("Clientul exista deja!");
        }
        else
            this.clientBankAccountMap.put(client,null);
    }

    //RemoveClient - TESTEAZA
    public void removeClient(Client client)
    {
        this.clientBankAccountMap.remove(client);
    }

    //AddAccount ?????????????????
    public void addBankAccount(Client client, BankAccount bankAccount)
    {
        /*
        int c=0;
        for(Map.Entry<Client, List<BankAccount>> x: this.clientBankAccountMap.entrySet())
        {
            for(List<BankAccount> y : x.getValue())
            {

            }
        }*/

    }

    //RemoveAccount testeaza cand bagi un account dupa ce ai scos unu
    public void removeAccount(BankAccount bankAccount)
    {
        for(Map.Entry<Client, List<BankAccount>> x: this.clientBankAccountMap.entrySet())
        {
            if(x.getValue().remove(bankAccount))
            {
                BankAccount.setCounterBankAccountID(BankAccount.getCounterBankAccountID() - 1);
                List<BankAccount> dummy = x.getValue();
                for (BankAccount y : dummy)
                {
                    y.setBankAccountID(y.getBankAccountID()-1);
                }
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
            return true;
        Bank bank= (Bank) obj;
        if(!Objects.equals(this.BankID, bank.BankID))
            return false;
        if(!Objects.equals(this.name, bank.name))
            return false;
        if(!Objects.equals(this.location, bank.location))
            return false;
        if(!Objects.equals(this.location, bank.location))
            return false;
        if(!Objects.equals(this.clientBankAccountMap,bank.clientBankAccountMap))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder c = new StringBuilder();
        c.append("\t\t"+"["+this.BankID+"]"+"Banca " + this.name + " cu ID-ul " + this.BankID + " aflata la " + this.location);
        if(this.clientBankAccountMap.size()==0)
            c.append(" nu are clienti\n");
        else
            if(this.clientBankAccountMap.size()==1)
            {
                c.append(" are un singur client:\n");
                for(Map.Entry<Client, List<BankAccount>> x: this.clientBankAccountMap.entrySet())
                {
                    if(x.getValue().size()==0)
                        c.append(x.getKey().toString() + " nu are conturi bancare");
                    else
                        if(x.getValue().size()==1)
                            c.append(x.getKey().toString() + " are un singur cont bancar \n" + x.getValue().toString());
                        else {
                            c.append(x.getKey().toString() + " are urmatoarele conturi bancare:\n");
                            for(BankAccount bankAccount : x.getValue())
                                c.append(bankAccount.toString()+"\n");
                        }
                }
            }
            else
            {
                c.append(" are umatorii clienti:\n");
                for(Map.Entry<Client, List<BankAccount>> x: this.clientBankAccountMap.entrySet())
                {
                    if(x.getValue().size()==0)
                        c.append(x.getKey().toString() + " nu are conturi bancare");
                    else
                    if(x.getValue().size()==1)
                        c.append(x.getKey().toString() + " are un singur cont bancar \n" + x.getValue().toString());
                    else {
                        c.append(x.getKey().toString() + " are urmatoarele conturi bancare:\n");
                        for(BankAccount bankAccount : x.getValue())
                            c.append(bankAccount.toString()+"\n");
                    }
                    c.append("\n");
                }
            }
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.BankID, this.name, this.location, this.clientBankAccountMap);
    }
}