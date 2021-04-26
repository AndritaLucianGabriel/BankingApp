package Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public interface Timestamp {
    String RESOURCE_FOLDER_LOGS = System.getProperty("user.dir") + "\\src\\Service\\Files\\Resources\\Logs\\Logs.txt";

    static void timestamp(String text) {
        try {
            BufferedWriter LogsReader = new BufferedWriter(new FileWriter(RESOURCE_FOLDER_LOGS, true));
            LogsReader.write(text + " " + LocalDateTime.now() + "\n");
            LogsReader.close();
        } catch (IOException e) {
            System.out.println("Eroare la scrierea in fisierul Logs.txt (" + text + ").");
        }
    }

}
