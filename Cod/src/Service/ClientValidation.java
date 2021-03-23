package Service;

public class ClientValidation{
    public boolean validateFirst_name(String first_name)
    {
        return first_name.matches("^[a-zA-Z]+$");
    }
    public boolean validateLast_name(String last_name)
    {
        return last_name.matches("^[a-zA-Z- ]+$");
    }
    public boolean validateAge(int age){
        return age > 0;
    }
    public boolean validateCnp(String cnp){return cnp.matches("/^[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d$/g");}
}
