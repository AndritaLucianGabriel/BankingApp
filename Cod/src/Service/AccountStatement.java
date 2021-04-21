package Service;

import Operations.Transaction;
import Service.Files.ReaderFiles;

import java.time.LocalDate;

public interface AccountStatement {

    //Functii de filtrare al tranzactiilor
    default void balanceCheck(double value, String currency) {
        Timestamp.timestamp("AccountStatement: balanceCheck");
        System.out.println("Contul are " + FormatDouble.format(value) + " " + currency);
    }

    default String myTypeOfDateSinceThereIsNoWayToDoItProperly(String date) //18-09-2021 -> 2021-09-18
    {
        Timestamp.timestamp("AccountStatement: myTypeOfDateSinceThereIsNoWayToDoItProperly");
        String[] local = date.split("-");
        return local[2] + "-" + local[1] + "-" + local[0];
    }

    default String filterDate(String IBAN, String startDate, String sign) {
        Timestamp.timestamp("AccountStatement: filterDate");
        StringBuilder text = new StringBuilder();
        LocalDate parsedDate = LocalDate.parse(myTypeOfDateSinceThereIsNoWayToDoItProperly(startDate));
        switch (sign) {
            case ("="): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (x.getTimestamp().equals(parsedDate)) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }
            case (">"): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (x.getTimestamp().compareTo(parsedDate) > 0) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }
            case ("<"): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (x.getTimestamp().compareTo(parsedDate) < 0) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }
            case ("<="): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (x.getTimestamp().compareTo(parsedDate) <= 0) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }
            case (">="): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (x.getTimestamp().compareTo(parsedDate) >= 0) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }
            case ("<>"): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (!x.getTimestamp().equals(parsedDate)) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
            }
            break;
        }
        return text.toString();
    }

    default String filterDate(String IBAN, String startDate, String sign, String stopDate) {
        Timestamp.timestamp("AccountStatement: filterDate");
        StringBuilder text = new StringBuilder();
        LocalDate parsedStartDate = LocalDate.parse(myTypeOfDateSinceThereIsNoWayToDoItProperly(startDate));
        LocalDate parsedStopDate = LocalDate.parse(myTypeOfDateSinceThereIsNoWayToDoItProperly(stopDate));
        switch (sign) {
            case ("><"): { //parsedDate > startDate && parsedDate < stopDate
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (x.getTimestamp().compareTo(parsedStartDate) > 0 && x.getTimestamp().compareTo(parsedStopDate) < 0) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }
            case ("><="): { //parsedDate > startDate && parsedDate <= stopDate
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (x.getTimestamp().compareTo(parsedStartDate) > 0 && x.getTimestamp().compareTo(parsedStopDate) <= 0) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }
            case (">=<"): { //parsedDate >= startDate && parsedDate < stopDate
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (x.getTimestamp().compareTo(parsedStartDate) >= 0 && x.getTimestamp().compareTo(parsedStopDate) < 0) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }

            case (">=<="): { //parsedDate >= startDate && parsedDate <= stopDate
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (x.getTimestamp().compareTo(parsedStartDate) >= 0 && x.getTimestamp().compareTo(parsedStopDate) <= 0) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }

            case ("<>"): { //parsedDate < startDate && parsedDate > stopDate -> sa nu se afle in interval
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (x.getTimestamp().compareTo(parsedStartDate) < 0 && x.getTimestamp().compareTo(parsedStopDate) > 0) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }
        }
        return text.toString();
    }

    default String filterValue(String IBAN, double value, String sign) {
        Timestamp.timestamp("AccountStatement: filterValue");
        StringBuilder text = new StringBuilder();
        switch (sign) {
            case ("="): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (Math.abs(x.getValue()) == value) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }

            case (">"): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (Math.abs(x.getValue()) > value) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }

            case (">="): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (Math.abs(x.getValue()) >= value) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }

            case ("<"): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (Math.abs(x.getValue()) < value) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }

            case ("<="): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (Math.abs(x.getValue()) <= value) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }

            case ("<>"): { //diferit
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (Math.abs(x.getValue()) != value) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }

        }
        return text.toString();
    }

    default String filterValue(String IBAN, double minValue, String sign, double maxValue) {
        Timestamp.timestamp("AccountStatement: filterValue");
        StringBuilder text = new StringBuilder();
        switch (sign) {
            case ("><"): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (Math.abs(x.getValue()) > minValue && Math.abs(x.getValue()) < maxValue) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }

            case ("><="): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (Math.abs(x.getValue()) > minValue && Math.abs(x.getValue()) <= maxValue) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }

            case (">=<"): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (Math.abs(x.getValue()) >= minValue && Math.abs(x.getValue()) < maxValue) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }

            case (">=<="): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (Math.abs(x.getValue()) >= minValue && Math.abs(x.getValue()) <= maxValue) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }

            case ("<>"): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (Math.abs(x.getValue()) < minValue && Math.abs(x.getValue()) > maxValue) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }

        }
        return text.toString();
    }

    default String filterCurrency(String IBAN, String currency, String sign) {
        Timestamp.timestamp("AccountStatement: filterDate");
        StringBuilder text = new StringBuilder();
        switch (sign) {
            case ("="): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (x.getCurrency().equals(currency)) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }
            case ("<>"): {
                for (Transaction x : ReaderFiles.getInstance().readerAccountStatement(IBAN)) {
                    if (!x.getCurrency().equals(currency)) {
                        text.append(x.getTransactionID() + ", ");
                        text.append(x.getTimestamp().toString() + ", ");
                        text.append(x.getValue() + ", ");
                        text.append(x.getCurrency() + "\n");
                    }
                }
                break;
            }
        }
        return text.toString();
    }

}
