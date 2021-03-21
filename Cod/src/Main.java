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
        List <BankAccount> bankAccountList = new ArrayList<>();
        BankAccount bankAccount =new BankAccount("RO42RZBR15215321","12-09-2021",null,1203.4,"Lei");
        BankAccount bankAccount1 =new BankAccount("RO29ZBR52351552","25-07-2019","12-09-2021",52312,"Dolari");
        BankAccount bankAccount2 =new BankAccount("RO41ZBR23142317","15-05-2012",null,12312,"Dolari");
        bankAccountList.add(bankAccount);
        bankAccountList.add(bankAccount1);
        bankAccountList.add(bankAccount2);

        List <Loan> loansList = new ArrayList<>();
        Loan loan =new Loan(21410.3, "Lei", "Imprumut pentru nevoi personale", "03-21-2021", 60);
        Loan loan1 =new Loan(15213, "Lei", "Imprumut pentru masina", "03-02-2020", 72);
        Loan loan2 =new Loan(523151, "Dolari", "Imprumut pentru casa", "05-10-2019", 120);
        loansList.add(loan);
        loansList.add(loan1);
        loansList.add(loan2);

        Client client1=new Client("Vadim","Tudor",60,"52414626");
        Client client2=new Client("Dan", "Diaconescu", 58, "876864835");

        //Apelare constructor de tip 2
        //Bank bank = new Bank("Raiffeisen Bank","Strada Sebastian, Nr 54, Sector 3, Bucuresti", client, bankAccountList);

        Map <Client, List<BankAccount>> clientListMap = new HashMap<Client, List<BankAccount>>();
        Map <Client, List<Loan>> clientLoanMap = new HashMap<Client, List<Loan>>();
        clientListMap.put(client1,bankAccountList);
        clientLoanMap.put(client2,loansList);
        Bank bank = new Bank("Raiffeisen Bank","Strada Sebastian, Nr 54, Sector 3, Bucuresti", clientListMap, clientLoanMap);
        System.out.println(bank.toString());

        System.out.println("==========================================================================================================================\n");
        bank.removeAccount(bankAccount1);
            //System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());
        System.out.println(bank.toString());

        System.out.println("==========================================================================================================================\n");
            //bank.removeClient(client1.getCnp()); //testare eliminare
        Client client3=new Client("Ionita","Dragos",20,"52414626");
            //bank.addClient(client2); //testare adaugare Client
        bank.addBankAccount(client3,bankAccount2); //testare adaugare client nou + cont
        bank.addBankAccount(client2,bankAccount2);
        bank.addBankAccount(client1,bankAccount2); //testare adaugare cont nou
        System.out.println(bank.toString());
            //System.out.println("COUNTER CONTURI: " + BankAccount.getCounterBankAccountID());


    }
}