package Service;

public class FormatDouble {

    public static String Format(double d) {
        long x = (long) d;
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }
}
