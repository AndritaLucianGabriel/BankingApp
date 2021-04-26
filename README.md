# BankingApp
## **Operations Package**
#### *1. ProviderDB*
 The class stores information about the registered providers at the bank.
   1. providerDBReaderUpdate()
       > Method that is used to update the reader file for the providers.
#### *2. ToProviders*
 Derived singleton class from *Transaction* that oversees the transfer of funds to one of the providers.
   1. addProvider(ProviderDB providerDB)
       > Method allows the addition of a new provider.
   2. addProvider(List<ProviderDB> providerDBList)
       > Method allows the addition of a new list of providers.
   3. removeProvider(ProviderDB providerDB)
       > Method allows the removal of a privder.
   4. removeProvider(String IBAN)
       > Method allows the removal of a privder based on the bank account's IBAN.
   5. toProviderReaderUpdate()
       > Method that is used to update the reader file for the providers.
   6. anotherToString()
       > Method that is used to update the reader file for the providers.
   7. paymentUtilities(String IBAN, double value)
       > Method executes the transfers themselves and converts, if needed, the amount to the provider's preferred currency.
#### *3. Transaction*
 Abstract class that will receive information about any type of transaction to and from a bank account. Every transaction will use the AsigUUID interface to receive a unique ID and a timestamp of the current day will be created using the LocalDate class. All the information will be stored in a unique file based on the bank accounts' IBAN in order to be processed later for the creation of an account statement.
   1. withdraw(double value)
       > Abstract method.
   2. paymentUtilities(String IBAN, double value)
       > Abstract method.
   3. deposit(double value)
       > Abstract method.
#### *4. Transfer*
 Derived class from *Transaction* handles deposits and withdraws to and from an account.
   1. withdraw(double value)
       > Method that withdraws an amount from the account and updates its balance.
   2. deposit(double value)
       > Method that deposits an amount to the account and updates its balance.
## **Service Packge**
#### *1. AccountStatement*
 Interface that will provide functionalities to select specific transactions.

   0. Table of signs
       >     =  -> returns the given parameter if it exists
       >     >  -> bigger than the given parameter
       >     <  -> smaller than the given parameter
       >     <= -> smaller than or equal to the given parameter
       >     >= -> smaller than or equal to the given parameter
       >     <> -> anything but the given parameter
       >     >< -> everything in the given range
   1. balanceCheck(double value, String currency)
       > Method that checks the account's balance.
   2. myTypeOfDateSinceThereIsNoWayToDoItProperly(String date)
       > Method that will return the European date format.
   3. showTransaction(Transaction transaction)
       > Method that returns the formatted transaction.
   4. filterDate(String IBAN, String startDate, String sign)
       > Method that allows a client to filter their transactions based on a date.
   5. filterDate(String IBAN, String startDate, String sign, String stopDate)
       > Method that allows a client to filter their transactions based on a period of time.
   6. filterValue(String IBAN, double value, String sign)
       > Method that allows a client to filter their transactions based on a value.
   7. filterValue(String IBAN, double minValue, String sign, double maxValue)
       > Method that allows a client to filter their transactions based on a range of values.
   8. filterCurrency(String IBAN, String currency, String sign)
       > Method that allows a client to filter their transactions based on a type of currency.
#### *2. AsigUUID*
 Interface that will provide each transaction with an UUID.
   1. generateId()
       > Method that generates an UUID.
#### *3. CurrencyExchange*
 Interface that provides a service which allows the transfer of funds between two different accounts regardless of currency.
   1. exchangeBankAccount(double value, String baseCurrency, String wantedCurrency)
       > Method that executes the conversion between two accounts.
   2. convertTransfer(double value, String baseCurrency, String wantedCurrency)
       > Method that converts the current bank account currency to another one.
   3. convertTransferWithoutText(double value, String baseCurrency, String wantedCurrency)
       > This method is exactly the method above but without some printlns.
#### *4. FormatDouble*
 Interfce that formats the double type when the toString() method is called.
   1. format(double d)
       > Method that formats the given value to only show 2 digits after the floating point.
#### *5. Timestamp*
 Interface that will provide a timestamp of each function and store it in Service/Files/Resources/Logs
   1. timestamp(String text)
       > Method that will create the timestamp and store it in Logs.txt file.
## **Exceptions SubPackage**
#### *1. BankAccountException*
 Exception class for the BankAccount class.
   1. BankAccountException(String text)
       > Method that will throw a custom exception based on given text.
#### *2. BankException*
 Exception class for the Bank class.
   1. BankException(String text)
       > Method that will throw a custom exception based on given text.
#### *3. CardException*
 Exception class for the Card class.
   1. CardException(String text)
       > Method that will throw a custom exception based on given text.
#### *4. ClientException*
 Exception class for the Client class.
   1. ClientException(String text)
       > Method that will throw a custom exception based on given text.
#### *5. LoanException*
 Exception class for the Loan class.
   1. LoanException(String text)
       > Method that will throw a custom exception based on given text.
#### *6. ProviderDBException*
 Exception class for the ProviderDB class.
   1. ProviderDBException(String text)
       > Method that will throw a custom exception based on given text.
#### *7. TransactionException*
 Exception class for the Transaction class.
   1. TransactionException(String text)
       > Method that will thow a custom exception based on given text.
## **Files SubPackage**
#### *1. ReaderFiles*
 Exception class for the BankAccount class.
   1. readerBank()
       > Method that will retrieve all the information needed for a bank.
   2. readerBankAccount(List<Client> clientList)
       > Method that will read the bank accounts' information from a reader file based on a list of clients.
   3. readerCard(String IBAN)
       > Method that will read all the cards from a reader file.
   4. readerClient()
       > Method that will read all the clients from a reader file.
   5. readerLoan(List<Client> clientList)
       > Method that will read the loans' information from a reader file based on a list of clients.
   6. readerProviderDB()
       > Method that will read all the providers from a reader file.
   7. readerAccountStatement(String IBAN)
       > Method that will read all the transactions from a reader file in order to process it and return a sorted list of transaction based on their values.
   8. updateReaders(Bank bank)
       > Method that will update all the input files at the end of the execution.
#### *2. WriterFiles*
 Exception class for the Bank class.
   1. writerBank(String c)
       > Method that will write in file the toString() method of the bank.
   2. writerAccountStatement(Transaction transaction)
       > Method that will write in file the given transaction.
   3. writerAccountStatementTemp(String IBAN, String text)
       > Method that will write in file the given transaction based on the filter used in a previous method.
   4. clearAllFiles(String folder)
       > Method that will be called by the one below in order to clear all the output files in a given folder after each run.
   5. clearAllFolders()
       > Method that will return all the folders in the Service folder.
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
 The class stores details like the bank's name and location and uses two maps to store the information regarding its clients, their bank accounts and loans.
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
       > Method that removes all the client's loans from the bank's storage system based on their social security number.
   13. removeClientLoan(Client client)
       > Method that removes a client from the loans' storage system completely using their entire data collection (DB oriented method).
   14. removeLoan(Loan loan)
       > Method that removes a specific loan from the client's entry at the bank and reorders all the loans' IDs of the given client.
   15. removeClientBankAccount(String cnp)
       > Method that removes all the client's bank accounts from the bank's storage system based on their social security number.
   16. removeClientBankAccount(Client client)
       > Method that removes all the client's bank accounts from the bank's storage system using their entire data collection (DB oriented method).
   17. removeAccount(String IBAN)
       > Method that removes a specific bank account from the client's entry at the bank and reorders all the bank accounts' IDs of the given client.
   18. removeAccount(BankAccount bankAccount)
       > Method that removes a specific bank account from the client's entry at the bank and reorders all the bank accounts' IDs of the given client (DB oriented method).
   19. interBanking(String receiver, String sender, double value)
       > Method that allows a client to transfer funds from one of their bank accounts to another client. The method will convert the amount to his receiver's bank account's type of currency based on a conversion ratio.
   20. balanceCheck(BankAccount bankAccount)
       > Method that allows a client to see the balance of one of their bank accounts.
   21. filterByDate(String IBAN, String startDate, String sign)
       > Method that allows a client filter all the transactions of one of their accounts. The method itself first checks for the integrity of the received data, then uses the AccountStatement interface to check for the specified dates based on the provided sign. The information is received via Service/File/Resources/AccountStatement and then the statement is written in the Service/File/Resources/AccountStatementTemp folder based on the account's IBAN.
   22. filterByDate(String IBAN, String startDate, String sign, String stopDate)
       > Method that allows a client filter all the transactions of one of their accounts. The method itself first checks for the integrity of the received data, then uses the AccountStatement interface to check for the specified period of time (startDate, stopDate) based on the provided sign. The information is received via Service/File/Resources/AccountStatement and then the statement is written in the Service/File/Resources/AccountStatementTemp folder based on the account's IBAN.
   23. filterByValue(String IBAN, double value, String sign)
       > Method that allows a client filter all the transactions of one of their accounts. The method itself first checks for the integrity of the received data, then uses the AccountStatement interface to check for the specified values based on the provided sign. The information is received via Service/File/Resources/AccountStatement and then the statement is written in the Service/File/Resources/AccountStatementTemp folder based on the account's IBAN.
   24. filterByValue(String IBAN, double minValue, String sign, double maxValue)
       > Method that allows a client filter all the transactions of one of their accounts. The method itself first checks for the integrity of the received data, then uses the AccountStatement interface to check for the specified range of values based on the provided sign. The information is received via Service/File/Resources/AccountStatement and then the statement is written in the Service/File/Resources/AccountStatementTemp folder based on the account's IBAN.
   25. filterByCurrency(String IBAN, String currency, String sign)
       > Method that allows a client filter all the transactions of one of their accounts. The method itself first checks for the integrity of the received data, then uses the AccountStatement interface to check for the specified type of currency based on the provided sign. The information is received via Service/File/Resources/AccountStatement and then the statement is written in the Service/File/Resources/AccountStatementTemp folder based on the account's IBAN.
   26. paymentUtilies(String Sender, String Receiver, double value)
       > Method that allows a client to pay their utilities using an existing DB in the bank systems. The method will convert the amount to their receiver's bank accounts' type of currency based on a conversion ratio.
   27. currencyExchange(BankAccount bankAccount, String wantedCurrency)
       > Method that allows a client to convert their bank account currency.
   28. currencyExchange(String IBAN, String wantedCurrency)
       > Method that allows a client to convert their bank account currency based of their IBAN.
   29. payLoan(BankAccount bankAccount, Loan loan, double value)
       > Method that allows a client to pay more than one of their monthly installments using one of their bank accounts. The method recalculates the remaining loan and creates a new monthly installment and reduces the period of the loan.
   30. payLoan(BankAccount bankAccount, Loan loan)
       > Method that allows a client to pay exactly one of their monthly installments using one of their bank accounts. The method recalculates the remaining loan and creates a new monthly installment and reduces the period of the loan.
   31. addProvider(ProviderDB providerDB)
       > Method that adds a new provider in the bank's database.
   32. addProvider(List<ProviderDB> providerDBList)
       > Method that adds a list of new providers in the bank's database.
   33. removeProvider(ProviderDB providerDB)
       > Method that removes a provider from the bank's database.
   34. removeProvider(String IBAN)
       > Method that removes a provider from the bank's database based on their IBAN.
   35. checkProviders()
       > Method that will be integrated in the bank's toString() method to show all the information about providers.
   36. bankReaderUpdate()
       > Method that is used to update the reader file for the bank.
   37. List<String> bankAccountReaderUpdate()
       > Method that is used to update the reader file for the bank accounts.
   38. List<String> cardReaderUpdate()
       > Method that is used to update the reader file for the cards.
   39. List<String> clientReaderUpdate()
       > Method that is used to update the reader file for the clients.
   40. List<String> loanReaderUpdate()
       > Method that is used to update the reader file for the loans.
#### *2. BankAccount*
 Abstract class that creates a unique ID of the account in relation to the client and then stores data about the clients' money, the type of currency they prefer and a list of all the cards that were linked to this account.
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
   7. bankAccountReaderUpdate()
       > Abstract method that is used to update the reader file for the bank accounts.
#### *3. Card*
 The class stores information about the clients' cards like the unique card number, PIN and the issue date of the card.
   1. cardReaderUpdate()
       > Method that is used to update the reader file for the cards.
#### *4. Client*
 The class stores private information about the bank's clients.
   1. clientReaderUpdate()
       > Method that is used to update the reader file for the clients.
#### *5. DebitAccount*
 The class inherits all the information from the abstract class *BankAccount*.
   1. bankAccountReaderUpdate()
       > Method that is used to update the reader file for the bank accounts.
#### *6. Loan*
 The class stores information the clients' loans at the bank.
   1. valueMonthlyRate()
       > Method that calculates the monthly installment.
   2. payMonthlyRate(double value)
       > Method that allows the client to pay more than their monthly installment, recalculating the new monthly installment and reducing the period of time by 1 month.
   3. payMonthlyRate()
       > Method that allows the client to pay exactly their monthly installment.
   4. loanReaderUpdate()
       > Method that is used to update the reader file for the loans.
#### *7. SavingsAccount*
 The class inherits all the information from the abstract class *BankAccount* and has a new member for the annual interest rate. It will be a penalty of 10% interest rate and 1 euro tax whenever there is a transaction (withdrawing) made with this account.
   1. penalty()
       > Method that implements the actual loss of interest rate and the 1 euro tax.
   2. bankAccountReaderUpdate()
       > Method that is used to update the reader file for the bank accounts.
   3. withdraw(double value)
       > *Overrided* method that now implements the penalty.
   4. paymentUtilies(String IBAN, double value)
       > *Overrided* method that now implements the penalty.