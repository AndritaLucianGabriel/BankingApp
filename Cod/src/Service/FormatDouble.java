package Service;

public class FormatDouble {

    // metodele incep cu litera mica
    public static String Format(double d) {
        long x = (long) d;
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }
}
