package Service.Validations;

public class ClientValidation {
    public void validateFirst_name(String first_name) {
        if (!first_name.matches("^[a-zA-Z]+$"))
            System.out.println("First name invalid");
    }

    public void validateLast_name(String last_name) {
        if (!last_name.matches("^[a-zA-Z- ]+$"))
            System.out.println("Last name invalid");
    }

    public void validateAge(int age) {
        if (age < 0)
            System.out.println("Varsta invalida");
    }

    public void validateCnp(String cnp) {
        if (!cnp.matches("^[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d$"))
            System.out.println("CNP invalid");
    }
}
