import java.io.Serializable;

public class Vehicle implements Serializable {
    private static final long serialVersionUID= -4620615220800616863L;
    private String brand;
    private String type;
    private String hp;
    private String licensePlate;
    private String displacement;


Vehicle(String t, String h, String lp, String d, String b) {
    this.brand=b;
    this.type=t;
    this.hp=h;
    this.licensePlate=lp;
    this.displacement=d;
}

public String toFile() {
    return " "+brand+" "+type+" "+hp+" "+licensePlate+" "+displacement;
}

public String toString() {
    return "\n\n\t\t"+brand+"\n\t\t"+type+"\n\t\t"+hp+"\n\t\t"+licensePlate+"\n\t\t"+displacement+"\n\n";
}

public String getBrand() {return brand;}
public String getLicensePlate() {return licensePlate;}

}
