# BankingApp
## **Operations Package**
#### *1. CurrencyExchange*
 The class provides a service that allows the transfer of funds between two different accounts regardless of currency.
   1. Pair<Double, String> exchangeBankAccount(double value, String baseCurrency, String wantedCurrency)
      > Method that executes the conversion between two accounts.
   2. double convertTransfer(double value, String baseCurrency, String wantedCurrency)
      > Method that converts the current bank account currency to another one.
#### *2. ProviderDB*
 The class stores information about the registered providers at the bank.
#### *3. ToProviders*
 Derived class from *Transaction* that oversees the transfer of funds to one of the providers.
   1. paymentUtilities(String IBAN, double value)
      > Method executes the transfers itself and converts, if needed, the ammount to the provider's preferred currency.
#### *4. Transaction*
 Abstract class that will be used later to generate an account statement based on some filters.
#### *5. Transfer*
 Derived class from *Transaction* handles deposits and withdraws to and from an account.
   1. withdraw(double value)
      > Method that withdraws an ammount from the account and updates it's balance.
   2. deposit(double value)
      > Method that deposits an ammount to the account and updates it's balance.
## **Service Packge**
#### *1. AccountStatement*
 The class will be heavily modified later and will be used to generate an account statement based on some filters.
   1. balanceCheck(double value, String currency)
      > Method that checks the account's balance.
#### *2. FormatDouble*
 The class formats the double type when the toString() method is called.
   1. String Format(double d)
      > Method that formats the given value.
## **Validations SubPackage**
#### *1. BankAccountValidation*
 Service class that checks all the accounts' information to match the specific template and reduce the chance to insert faulty data.
#### *2. BankValidation*
 Service class that checks all the banks' information to match the specific template and reduce the chance to insert faulty data.
#### *3. CardValidation*
 Service class that checks all the cards' information to match the specific template and reduce the chance to insert faulty data.
#### *4. ClientValidation*
 Service class that checks all the clients' information to match the specific template and reduce the chance to insert faulty data. 
#### *5. LoanValidation*
 Service class that checks all the loans' information to match the specific template and reduce the chance to insert faulty data. 
#### *6. ToProvidersValidation*
 Service class that checks all the providers' information to match the specific template and reduce the chance to insert faulty data. 
## **MainClasses**
#### *1. Bank*
 The class stores details like the bank's name and location and uses two maps to store the information regarding it's clients, their bank accounts and loans.
   1. normalizeBankIndex(Client client)
      > Helps the bank with the correct indexing of the bank accounts of a given client in its storage by normalizing it in relation to the others.
   2. normalizeBankIndex()
      > Helps the bank with the correct indexing of the all the bank accounts in its storage.
   3. normalizeLoanIndex(Client client)
      > Helps the bank with the correct indexing of the loans of a given client in its storage by normalizing it in relation to the others.
   4. normalizeLoanIndex()
      > Helps the bank with the correct indexing of the all the loans in its storage.
   5. addBankAccountClient(Client client)
      > Method that flags the bank that a client's bank account will be added in the near future.
   6. addBankAccount(Client client, BankAccount bankAccount)
      > Method that adds a bank account for a client. If the client is not registered in the bank's system, a new entry will be created using the method above.
   7. addLoanCLient(Client client)
      > Method that flags the bank that a client's loan will be added in the near future.
   8. addLoan(Client client, Loan loan)
      > Method that adds a loan for a client. If the client is not registered in the bank's system, a new entry will be created using the method above.
   9. addCard(BankAccount bankAccount, Card card)
      > Method that links a card to a bank account.
   10. removeCard(String cardNumber)
       > Method that removes a card from a bank account based on its' card number.
   11. removeCard(Card card)
       > Method that removes a card from a bank account using it's entire data collection (DB oriented method).
   12. removeClientLoan(String cnp)
       > Method that removes all the client's loans from the bank's storage system based on his social security number.
   13. removeClientLoan(Client client)
       > Method that removes a client from the loans' storage system completely using his entire data collection (DB oriented method).
   14. removeLoan(Loan loan)
       > Method that removes a specific loan from the client's entry at the bank and reorders all the loans' IDs of the given client.
   15. removeClientBankAccount(String cnp)
       > Method that removes all the client's bank accounts from the bank's storage system based on his social security number.
   16. removeClientBankAccount(Client client)
       > Method that removes all the client's bank accounts from the bank's storage system using his entire data collection (DB oriented method).
   17. removeAccount(String IBAN)
       > Method that removes a specific bank account from the client's entry at the bank and reorders all the bank accounts' IDs of the given client.
   18. removeAccount(BankAccount bankAccount)
       > Method that removes a specific bank account from the client's entry at the bank and reorders all the bank accounts' IDs of the given client (DB oriented method).
   19. interBanking(String receiver, String sender, double value)
       > Method that allows a client to transfer funds from one of their bank accounts to another client. The method will convert the amount to his receiver's bank account's type of currency based on a conversion ratio.
   20. balanceCheck(BankAccount bankAccount)
       > Method that allows a client to see the balance of one of his bank accounts.
   21. paymentUtilies(String Sender, String Receiver, double value)
       > Method that allows a client to pay his utilities using an existing DB in the bank systems. The method will convert the amount to his receiver's bank accounts's type of currency based on a conversion ratio.
   22. currencyExchange(BankAccount bankAccount, String wantedCurrency)
       > Method that allows a client to convert his bank account currency.
#### *2. BankAccount*
 The class creates a unique ID of the account in relation to the client and then stores data about the clients' money, the type of currency he prefers and a list of all the cards that were linked to this account.
   1. addCard(Card card)
      > Method that links a card to the bank account.
   2. removeCard(Card card)
      > Method that removes a card from the bank account.
   3. withdraw(double value)
      > Method that withdraws an amount from the selected account.
   4. deposit(double value)
      > Method that deposits an amount into the selected account.
   5. paymentUtilies(String IBAN, double value)
      > Method that pays to one of the bank's registered providers an amount. The method checks for a difference in the accounts' currency and automatically converts the value to match the receiver.
   6. currencyExchange(String wantedCurrency)
      > Method that converts the selected account to a given type of currency
#### *3. Card*
 The class stores information about the clients' cards like the unique card number, PIN and the issue date of the card.
#### *4. Client*
 The class stores private information about the bank's clients.
#### *5. Loan*
 The class stores information the clients' loans at the bank.
