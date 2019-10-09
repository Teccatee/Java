import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class User implements Serializable {
    private static List<Vehicle> vehicles = new ArrayList<>();
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

    public List<Vehicle> getvehicles() {return vehicles;}

    public void setVehicles(List<Vehicle> a) {
        vehicles=a;
    }

    public void addVehicle() {
        Scanner p = new Scanner(System.in);
        System.out.printf("Enter brand: ");
        String b=p.next();
        System.out.printf("\nEnter type: ");
        String t=p.next();
        System.out.printf("\nEnter license plate: ");
        String lp=p.next();
        System.out.printf("\nEnter hp: ");
        String h=p.next();
        System.out.printf("\nEnter displacement: ");
        String d=p.next();
        this.vehicles.add(new Vehicle(t, h, lp, d, b));
    }

    @Override
    public String toString() {
        return "\n\n\t\t" + name + "\n\t\t" + surname + "\n\t\t" + email + "\n\t\t" + vehicle + "\n\t\t" + cv;
    }

    public String getName() {
        return name;
    }

    public String getId() { return id; }

    public String getVehicle() { return vehicle; }

    public String getEmail() { return email; }

    public String getSurname() { return surname; }

    public String getCv() { return cv; }

    public String getPsw() { return psw; }

    public void setId(String id) { this.id = id; }

    public void setPsw(String psw) { this.psw = psw; }

    public void setVehicle(String vehicle) { this.vehicle = vehicle; }

    public void setEmail(String email) { this.email = email; }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) { this.surname = surname; }

    public void setCv(String cv) { this.cv = cv;}

}
