import java.util.Objects;

public class Client {
    protected String first_name;
    protected String last_name;
    protected int age;
    protected String cnp;

    public Client()
    {
        this.first_name="";
        this.last_name="";
        this.age=0;
        this.cnp="";
    }

    public Client(String first_name, String last_name, int age, String cnp) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.cnp = cnp;
    }

    //getteri
    public String getFirst_name()
    {
        return this.first_name;
    }

    public String getLast_name()
    {
        return this.last_name;
    }

    public int getAge()
    {
        return this.age;
    }

    public String getCnp()
    {
        return this.cnp;
    }

    //setteri
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name)
    {
        this.last_name=last_name;
    }

    public void setAge(int age)
    {
        this.age=age;
    }

    public void setCnp(String cnp)
    {
        this.cnp=cnp;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this==obj)
            return true;
        if(obj==null)
            return false;
        //testeaza chestia asta pentru cast
        if(this.getClass()!=obj.getClass())
            return false;
        Client client = (Client) obj;
        if(!Objects.equals(this.first_name, client.first_name))
            return false;
        if(!Objects.equals(this.last_name, client.last_name))
            return false;
        if(this.age!= client.age)
            return false;
        if(!Objects.equals(this.cnp, client.cnp))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        //verificare nume si chestii din service
        StringBuilder c= new StringBuilder();
        c.append("\t"+ this.first_name + " " + this.last_name + " in varsta de " + this.age + " ani, CNP: " + this.cnp );
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.first_name,this.last_name,this.age,this.cnp);
    }

}