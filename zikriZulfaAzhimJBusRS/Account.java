package zikriZulfaAzhimJBusRS;


public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    public Account(int id, String name, String email, String password){
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public String toString(){
        return "Name: "+this.name+"\nEmail: "+this.email+"\nPassword: "+this.password;
    }
}
