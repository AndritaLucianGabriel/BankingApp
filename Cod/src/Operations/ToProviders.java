package Operations;

import Service.CurrencyExchange;
import Service.Exceptions.BankAccountException;
import Service.Exceptions.ProviderDBException;
import Service.Exceptions.TransactionException;
import Service.Timestamp;
import Service.Files.WriterFiles;
import Service.FormatDouble;
import Service.Validations.BankAccountValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//clasa singleton (functionare tip baza de date? imi pastreaza providerii)
public class ToProviders extends Transaction {
    private static ToProviders instance = null;
    protected static List<ProviderDB> providerDBList = new ArrayList<>();

    //Constructor doar pentru...construit
    private ToProviders() {
    }

    public static ToProviders getInstance() {
        if (instance == null)
            instance = new ToProviders();
        return instance;
    }

    //Constructor folosit doar pentru tranzactii
    private ToProviders(String IBAN, double value, String currency) throws TransactionException {
        super(IBAN, value, currency);
    }

    public static ToProviders getInstance(String IBAN, double value, String currency) throws TransactionException {
        return new ToProviders(IBAN, value, currency);
    }

    public static List<ProviderDB> getProviderDBList() {
        return providerDBList;
    }

    //Functie ce imi adauga un nou provider
    public static void addProvider(ProviderDB providerDB) {
        Timestamp.timestamp("ToProviders: addProvider");
        instance = ToProviders.getInstance();
        if (providerDBList.contains(providerDB))
            System.out.println("Providerul " + providerDB.getCompany() + " exista deja.");
        else {
            providerDBList.add(providerDB);
            System.out.println("Providerul " + providerDB.getCompany() + " a fost adaugat cu succes!");
        }
    }

    //Functie ce imi adauga o lista intreaga de provideri (pentru incarcare date)
    public static void addProvider(List<ProviderDB> providerDBList) {
        Timestamp.timestamp("ToProviders: addProvider");
        instance = ToProviders.getInstance();
        //Vedem ce elemente difera si le introducem in lista
        if (ToProviders.providerDBList.isEmpty()) {
            ToProviders.providerDBList.addAll(providerDBList);
        } else {
            for (ProviderDB x : providerDBList) {
                if (!ToProviders.providerDBList.contains(x))
                    ToProviders.providerDBList.add(x);
            }
        }
    }

    //Functie ce imi scoate un provider pe baza de colectie de date
    public static void removeProvider(ProviderDB providerDB) {
        Timestamp.timestamp("ToProviders: removeProvider");
        if (!providerDBList.remove(providerDB))
            System.out.println("Nu exista providerul " + providerDB.getCompany());
        else
            System.out.println("Providerul " + providerDB.getCompany() + " a fost eliminat cu succes!");
    }

    //Functie ce imi scoate un provider pe baza de IBAN
    public static void removeProvider(String IBAN) throws BankAccountException {
        Timestamp.timestamp("ToProviders: removeProvider");
        BankAccountValidation.validateIBAN(IBAN);
        int c = 0;
        for (ProviderDB x : providerDBList)
            if (x.getIBAN().equals(IBAN)) {
                c++;
                providerDBList.remove(x);
            }
        if (c == 0)
            System.out.println("Nu exista providerul cu IBAN-ul " + IBAN);
        else
            System.out.println("Providerul a fost eliminat cu succes!");
    }

    //Functi ce va face update-ul fisierelor de intrare
    public static List<String> toProviderReaderUpdate() {
        Timestamp.timestamp("ToProviders: toProviderReaderUpdate");
        List<String> local = new ArrayList<>();
        for (ProviderDB x : providerDBList) {
            local.add(x.providerDBReaderUpdate() + "\n");
        }
        return local;
    }

    public String anotherToString() {
        Timestamp.timestamp("ToProviders: anotherToString");
        return transactionID +
                ", " + timestamp +
                ", " + tradeValue +
                ", " + currency + "\n";
    }

    @Override
    public double paymentUtilities(String IBAN, double value) throws ProviderDBException {
        Timestamp.timestamp("ToProviders: paymentUtilities");
        double val = 0, c = 0;
        for (ProviderDB x : providerDBList) {
            if (Objects.equals(x.getIBAN(), IBAN)) {
                c++;
                System.out.println(" in contul " + IBAN + " (" + x.getCompany() + ")" + "\nSold anterior: " + FormatDouble.format(x.getBalance()) + " " + x.getCurrency());
                x.setBalance(x.getBalance() + CurrencyExchange.convertTransfer(value, x.currency, this.currency));
                System.out.println("Sold nou: " + FormatDouble.format(x.getBalance()) + " " + x.currency);
                val = this.value - value;
                this.tradeValue = -value;
                WriterFiles.getInstance().writerAccountStatement(this);
                break;
            }
        }
        if (c == 0)
            System.out.println("Nu exista providerul cu IBAN-ul: " + IBAN);
        return val;
    }

    @Override
    public double withdraw(double value) {
        return 0;
    }

    @Override
    public double deposit(double value) {
        return 0;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        StringBuilder c = new StringBuilder();
        if (providerDBList.isEmpty())
            c.append("\tNu avem nici un provider.");
        else if (providerDBList.size() == 1)
            c.append("\tAvem urmatorul provider:\n" + providerDBList.get(0).toString());
        else {
            c.append("\tAvem urmatorii provideri:");
            for (ProviderDB x : providerDBList)
                c.append("\n" + x.toString());
        }
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(instance, providerDBList);
    }
}