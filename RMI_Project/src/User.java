import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class User implements Serializable {
    private List<Vehicle> vehicles;
    private static final long serialVersionUID = -3253167843904900446L;
    private String id;
    private String psw;
    private String name;
    private String surname;
    private String email;

    User(String n, String s, String e, String i, String p){
        this.name = n;
        this.surname = s;
        this.email = e;
        this.id = i;
        this.psw = p;
        this.vehicles =  new ArrayList<>();
    }

    public List<Vehicle> getvehicles() {return vehicles;}

    public void setvehicles(List<Vehicle> a) {
        this.vehicles=a;
    }

    public int [] FindVehicle (String f, int i) {
        int k = 0;
        for(; i<vehicles.size(); i++) {
            if (vehicles.get(i).getBrand().compareTo(f) == 0) {
                k = 1;
                break;
            }
        }
        return new int[] {i, k};
    }

    public int [] FindLicensePlate(String f) {
        int k =0, i;
        for (i=0; i<vehicles.size(); i++)
            if (vehicles.get(i).getLicensePlate().compareTo(f)==0) {
                k = 1;
                break;
            }
        return new int[] {i, k};
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
        add(t, h, lp, d, b);
    }

    public void add(String t, String h, String lp, String d, String b) {
        this.vehicles.add(new Vehicle(t, h, lp, d, b));
    }

    @Override
    public String toString() {
        return "\n\n\t\t" + name + "\n\t\t" + surname + "\n\t\t" + email;
    }

    public String StringToFile() {
        return name+" "+surname+" "+email+" "+id+" "+psw+"\t";
    }

    public String getName() {
        return name;
    }

    public String getId() { return id; }

    public String getEmail() { return email; }

    public String getSurname() { return surname; }

    public String getPsw() { return psw; }

    public void setId(String id) { this.id = id; }

    public void setPsw(String psw) { this.psw = psw; }

    public void setEmail(String email) { this.email = email; }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) { this.surname = surname; }


}
