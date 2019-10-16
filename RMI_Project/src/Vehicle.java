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

public String toString() {
    return "\n\n\t\tBrand: "+brand+"\n\t\tType: "+type+"\n\t\tHp: "+hp+"\n\t\tLicense plate: "+licensePlate+"\n\t\tDispacement: "+displacement;
}
public String StringToFile() {
    return " "+brand+" "+type+" "+hp+" "+licensePlate+" "+displacement+"\t";
}

public String getBrand() {return brand;}
public String getLicensePlate() {return licensePlate;}
public String getType() {return type;}
public String getHp() {return hp;}
public String getDisplacement() {return displacement;}

}
