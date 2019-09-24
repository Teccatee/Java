import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -3253167843904900446L;
    private String id;
    private String psw;
    private String name;
    private String surname;
    private String email;
    private String vehicle;
    private String cv;


    User(String n, String s, String e, String v, String c, String i, String p){
        this.name = n;
        this.surname = s;
        this.email = e;
        this.vehicle = v;
        this.cv = c;
        this.id = i;
        this.psw = p;
    }

    @Override
    public String toString() {
        return "\n\n\t\t# # # # # # # # # # # # # # # # # # # #\n\t\t#\n\t\t# Utente:" + "\n\t\t# Name = " + name +
                "\n\t\t# Surname = " + surname + "\n\t\t# Email = " + email + "\n\t\t# Vehicle = " + vehicle +
                "\n\t\t# Cv = " + cv + "\n\t\t#\n\t\t# # # # # # # # # # # # # # # # # # # #\n";
    }

    public String getName() {
        return name;
    }

    public String getId() { return id;}

    public String getVehicle() { return vehicle; }

    public String getEmail() { return email;}

    public String getSurname() {return surname;}

    public String getCv() {return cv;}

    public void setId(String id) { this.id = id; }

    public String getPsw() { return psw;}

    public void setPsw(String psw) { this.psw = psw; }

    public void setVehicle(String vehicle) { this.vehicle = vehicle; }

    public void setEmail(String email) { this.email = email; }

    public void setName(String name) {
        this.name = name;
    }

}
