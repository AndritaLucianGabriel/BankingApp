package Service;

import javafx.util.Pair;

import java.util.Objects;

//RIP tentativa de API
public interface CurrencyExchange {
    String[] currency = {"Lei", "Dolari", "Euro"};
    //Rates @ 25-03-2021 14:41
    //Lei->X           //Dolari->X        //Euro->X
    Double[][] exchangeRates = {{1.0, 0.24, 0.20}, {4.14, 1.0, 0.85}, {4.89, 1.18, 1.0}};

    //baza pentru transfer intre conturi cu currency diferit
    static Pair<Double, String> exchangeBankAccount(double value, String baseCurrency, String wantedCurrency) {
        Timestamp.timestamp("CurrencyExchange: exchangeBankAccount");
        int indexBaseCurrency = 0, indexWantedCurrency = 0;
        for (int i = 0; i < currency.length; i++) {
            if (Objects.equals(currency[i], baseCurrency))
                indexBaseCurrency = i;
            if (Objects.equals(currency[i], wantedCurrency))
                indexWantedCurrency = i;
        }
        System.out.println("\tExchangeRate: " + FormatDouble.format(exchangeRates[indexBaseCurrency][indexWantedCurrency]));
        return new Pair<>(value * exchangeRates[indexBaseCurrency][indexWantedCurrency], wantedCurrency);
    }

    static double convertTransfer(double value, String baseCurrency, String wantedCurrency) {
        Timestamp.timestamp("CurrencyExchange: convertTransfer");
        int indexBaseCurrency = 0, indexWantedCurrency = 0;
        for (int i = 0; i < currency.length; i++) {
            if (Objects.equals(currency[i], baseCurrency))
                indexBaseCurrency = i;
            if (Objects.equals(currency[i], wantedCurrency))
                indexWantedCurrency = i;
        }
        System.out.println("\tExchangeRate: " + FormatDouble.format(exchangeRates[indexWantedCurrency][indexBaseCurrency]));
        return value * exchangeRates[indexWantedCurrency][indexBaseCurrency];
    }

    static double convertTransferWithoutText(double value, String baseCurrency, String wantedCurrency) {
        Timestamp.timestamp("CurrencyExchange: convertTransferWithoutText");
        int indexBaseCurrency = 0, indexWantedCurrency = 0;
        for (int i = 0; i < currency.length; i++) {
            if (Objects.equals(currency[i], baseCurrency))
                indexBaseCurrency = i;
            if (Objects.equals(currency[i], wantedCurrency))
                indexWantedCurrency = i;
        }
        return value * exchangeRates[indexWantedCurrency][indexBaseCurrency];
    }

}