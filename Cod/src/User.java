import java.util.Objects;

public class User {
    protected String first_name;
    protected String last_name;
    protected int age;
    protected String cnp;
    protected Cont[] conts;

    public User()
    {
        this.first_name="";
        this.last_name="";
        this.age=0;
        this.cnp="";
        this.conts=null;
    }

    //termina cu Cont si verifica chestii
    public User(String first_name, String last_name, int age, String cnp, Cont[] conts) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.cnp = cnp;
        this.conts = conts;
    }

    public User(String first_name, String last_name, int age, String cnp)
    {
        this.first_name=first_name;
        this.last_name=last_name;
        this.age=age;
        this.cnp=cnp;
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
    public String toString()
    {
        //verificare nume si chestii din service
        StringBuilder c= new StringBuilder();
        c.append(this.first_name)
            .append(" ")
                .append(this.last_name)
                .append(" in varsta de ")
                .append(this.age)
                .append(" ani, CNP: ")
                .append(this.cnp);
        return c.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.first_name,this.last_name,this.age,this.cnp);
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
        User user= (User) obj;
        if(!Objects.equals(this.first_name,user.first_name))
            return false;
        if(!Objects.equals(this.last_name,user.last_name))
            return false;
        if(this.age!=user.age)
            return false;
        if(!Objects.equals(this.cnp,user.cnp))
            return false;

        return true;
    }

}