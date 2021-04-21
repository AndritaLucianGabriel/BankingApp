package Service.Files;

import Operations.ToProviders;
import Operations.Transaction;
import Service.Timestamp;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WriterFiles {
    public static final String RESOURCES_FOLDER = System.getProperty("user.dir") + "\\src\\Service\\Files\\Resources\\Writers";
    public static final String RESOURCES_FOLDER_MIX = System.getProperty("user.dir") + "\\src\\Service\\Files\\Resources\\AccountStatement";
    public static BufferedWriter bankWriter;
    public static BufferedWriter accountStatementWriter;
    private static WriterFiles instance = null;

    private WriterFiles() {
    }

    public static WriterFiles getInstance() {
        if (instance == null)
            instance = new WriterFiles();
        return instance;
    }

    //Functii de afisare in fisiere
    public void writerBank(String c) {
        try {
            Timestamp.timestamp("WriterFiles: writerBank");
            bankWriter = new BufferedWriter(new FileWriter(RESOURCES_FOLDER + "\\Bank.txt", true));
            bankWriter.write("\n==========================================================================================================================" + c);
            bankWriter.close();
        } catch (IOException e) {
            System.out.println("Eroare la afisarea in fisierul Bank.txt.");
        }
    }

    public void writerAccountStatement(Transaction transaction) {
        try {
            Timestamp.timestamp("WriterFiles: writerAccountStatement");
            accountStatementWriter = new BufferedWriter(new FileWriter(RESOURCES_FOLDER_MIX + "\\" + transaction.getIBAN() + ".txt", true));
            if (transaction instanceof ToProviders)
                accountStatementWriter.write(((ToProviders) transaction).anotherToString());
            else
                accountStatementWriter.write(transaction.toString());
            accountStatementWriter.close();
        } catch (IOException e) {
            System.out.println("Eroare la afisarea in fisierul " + transaction.getIBAN() + ".txt.");
        }
    }

    public void writerAccountStatementTemp(String IBAN, String text) {
        try {
            Timestamp.timestamp("WriterFiles: writerAccountStatementTemp");
            BufferedWriter accountStatementTempWriter = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\src\\Service\\Files\\Resources\\AccountStatementTemp\\" + IBAN + ".txt", true));
            accountStatementTempWriter.write(text);
            accountStatementTempWriter.close();
        } catch (IOException e) {
            System.out.println("Eroare la afisarea extrasului in fisierul " + IBAN + ".txt.");
        }

    }

    public void clearAllFiles(String folder) {
        try {
            Timestamp.timestamp("WriterFiles: clearAllFiles");
            File dir = new File(System.getProperty("user.dir") + "\\src\\Service\\Files\\Resources\\" + folder);
            List<String> children = new ArrayList<>(Arrays.asList(Objects.requireNonNull(dir.list())));
            if (!children.isEmpty()) {
                int i = 0;
                String filename = children.get(i);
                while (i < children.size() && filename.contains(".txt")) {
                    filename = children.get(i);
                    PrintWriter writer = new PrintWriter(System.getProperty("user.dir") + "\\src\\Service\\Files\\Resources\\" + folder + "\\" + filename);
                    writer.print("");
                    writer.close();
                    i++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Eroare la curatarea fisierelor din folderul '" + folder + "'");
        }
    }

    public void clearAllFolders() {
        File folder = new File(System.getProperty("user.dir") + "\\src\\Service\\Files\\Resources");
        File[] files = folder.listFiles();
        for (File file : files) {
            if (!file.isFile() && !file.getName().equals("Readers")) {
                WriterFiles.getInstance().clearAllFiles(file.getName());
            }
        }
        Timestamp.timestamp("WriterFiles: clearAllFolders");
    }
}
