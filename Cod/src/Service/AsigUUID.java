package Service;

import java.util.UUID;

public interface AsigUUID {

    default String generateId() {
        return UUID.randomUUID().toString();
    }

}