package Operations;

import javafx.util.Pair;

import java.util.Objects;

//o sa incerc sa adaug un API sa se ocupe de tot ce inseamna valoare, currency, inclusiv partea de conversie
public class CurrencyExchange {
    static String[] currency = {"Lei", "Dolari", "Euro"};
    //Rates @ 25-03-2021 14:41
    //Lei->X           //Dolari->X        //Euro->X
    static Double[][] exchangeRates = {{1.0, 0.24, 0.20}, {4.14, 1.0, 0.85}, {4.89, 1.18, 1.0}};

    //baza pentru transfer intre conturi cu currency diferit
    public static Pair<Double, String> exchangeBankAccount(double value, String baseCurrency, String wantedCurrency) {
        int indexBaseCurrency = 0, indexWantedCurrency = 0;
        for (int i = 0; i < currency.length; i++) {
            if (Objects.equals(currency[i], baseCurrency))
                indexBaseCurrency = i;
            if (Objects.equals(currency[i], wantedCurrency))
                indexWantedCurrency = i;
        }
        System.out.println("\tExchangeRate: " + exchangeRates[indexBaseCurrency][indexWantedCurrency]);
        return new Pair<Double, String>(value * exchangeRates[indexBaseCurrency][indexWantedCurrency], wantedCurrency);
    }

    public static double convertTransfer(double value, String baseCurrency, String wantedCurrency) {
        int indexBaseCurrency = 0, indexWantedCurrency = 0;
        for (int i = 0; i < currency.length; i++) {
            if (Objects.equals(currency[i], baseCurrency))
                indexBaseCurrency = i;
            if (Objects.equals(currency[i], wantedCurrency))
                indexWantedCurrency = i;
        }
        System.out.println("\tExchangeRate: " + exchangeRates[indexWantedCurrency][indexBaseCurrency]);
        return value * exchangeRates[indexWantedCurrency][indexBaseCurrency];
    }
}