import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        String address = args[0];
        String servicename = args[1];

        if(args.length!=2){
            System.out.println("Usage: java Client <address> <servicename>");
        }
        try {
            ServerInterface si = (ServerInterface) Naming.lookup("rmi://"+address+"/"+servicename);
            Scanner log = new Scanner(System.in);
            List<User> list = si.allUsers();
            System.out.println("\t\t# # # # # # # # # # # # # # # # # # # #\n\t\t#\n\t\t#\tBENVENUTO\n\t\t#\n\t\t# 1) Log in\n\t\t#\n\t\t# 2) Create an account\n\t\t#\n\t\t# # # # # # # # # # # # # # # # # # # #");
            int choise = log.nextInt();

            switch (choise) {

                case 1:

                    System.out.printf("\nUser ID = ");
                    String id = log.next();
                    System.out.printf("Password = ");
                    String psw = log.next();
                    System.out.println(si.logIn(id, psw));
                    System.out.println("\n\n\t\t# # # # # # # # # # # # # # # # # # # #\n\t\t#\n\t\t#\tMenu\n\t\t#\n\t\t# 1) Edit Account\n\t\t#\n\t\t# 2) All Users\n\t\t#\n\t\t# # # # # # # # # # # # # # # # # # # #");
                    choise = log.nextInt();

                    switch (choise) {

                        case 1:
                            break;

                        case 2:
                            System.out.println(list);
                            break;

                        default:
                            System.out.println("Error, wrong digit");
                            break;
                    }
                    break;

                case 2:
                    break;
                default:
                    System.out.println("Error, wrong digit");
                    break;
            }



        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
