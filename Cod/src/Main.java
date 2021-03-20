import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        /*
        //Testare functionalitati BankAccount
        List <BankAccount> bankAccountList = new ArrayList<BankAccount>();
        BankAccount bankAccount =new BankAccount("RO42RZBR15215321","12-09-2021",null,1203.4,"Lei");
        bankAccountList.add(bankAccount);
        System.out.println(bankAccountList.toString());*/

        /*
        //Testare functionalitati Client
        List <BankAccoun t>bankAccountList = new ArrayList<BankAccount>();
        BankAccount bankAccount =new BankAccount("RO42RZBR15215321","12-09-2021",null,1203.4,"Lei");
        BankAccount bankAccount1 =new BankAccount("RO41ZBR52351552","25-07-2019","12-09-2021",52312,"Dolari");
        bankAccountList.add(bankAccount);
        bankAccountList.add(bankAccount1);
        Client client=new Client("Marian","Gusatu",60,"52414626",bankAccountList);
        System.out.println(client.toString());
        */


        //Testare functionalitati Bank
        List <BankAccount> bankAccountList = new ArrayList<BankAccount>();
        BankAccount bankAccount =new BankAccount("RO42RZBR15215321","12-09-2021",null,1203.4,"Lei");
        BankAccount bankAccount1 =new BankAccount("RO41ZBR52351552","25-07-2019","12-09-2021",52312,"Dolari");
        bankAccountList.add(bankAccount);
        bankAccountList.add(bankAccount1);
        Client client=new Client("Marian","Gusatu",60,"52414626");

        //Apelare constructor de tip 2
        //Bank bank = new Bank("Raiffeisen Bank","Strada Sebastian, Nr 54, Sector 3, Bucuresti", client, bankAccountList);

        Map <Client, List<BankAccount>> clientListMap = new HashMap<Client, List<BankAccount>>();
        clientListMap.put(client,bankAccountList);
        Bank bank = new Bank("Raiffeisen Bank","Strada Sebastian, Nr 54, Sector 3, Bucuresti", clientListMap);

        System.out.println(bank.toString());
        System.out.println("==========================================================================================================================\n");
        bank.removeAccount(bankAccount);
        System.out.println(bank.toString());
    }
}