public class User {
    protected String first_name;
    protected String last_name;
    protected int age;
    protected String cnp;

    public User()
    {
        this.first_name="";
        this.last_name="";
        this.age=0;
        this.cnp="";
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
        return first_name + " " + last_name + " " + age + " ani, CNP: " +cnp;
    }

    public final Class getClass()
    {
        
    }


}
