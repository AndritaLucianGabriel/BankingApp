package MainClasses;

import java.util.Objects;

public class Client {
    protected String firstName;
    protected String lastName;
    protected int age;
    protected String cnp;

    public Client() {
        this.firstName = "";
        this.lastName = "";
        this.age = 0;
        this.cnp = "";
    }

    public Client(String firstName, String lastName, int age, String cnp) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.cnp = cnp;
    }

    //getteri
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getAge() {
        return this.age;
    }

    public String getCnp() {
        return this.cnp;
    }

    //setteri
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        //testeaza chestia asta pentru cast
        if (this.getClass() != obj.getClass())
            return false;
        Client client = (Client) obj;
        if (!Objects.equals(this.firstName, client.firstName))
            return false;
        if (!Objects.equals(this.lastName, client.lastName))
            return false;
        if (this.age != client.age)
            return false;
        if (!Objects.equals(this.cnp, client.cnp))
            return false;
        return true;
    }

    @Override
    public String toString() {
        //verificare nume si chestii din service
        StringBuilder c = new StringBuilder();
        c.append("\t" + this.firstName + " " + this.lastName + " in varsta de " + this.age + " ani, CNP: " + this.cnp);
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.firstName, this.lastName, this.age, this.cnp);
    }

}