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
            ServerInterface si = (ServerInterface) Naming.lookup("rmi://" + address + "/" + servicename);
            Scanner log = new Scanner(System.in);
            List<User> list = si.allUsers();
            boolean f = true;



            while (f) {
                title();
                int choose = log.nextInt();
                switch (choose) {
                    case 1:
                        System.out.printf("\n\n\t\tUser ID = ");
                        String id = log.next();
                        String access = si.logIn(id, list);
                        if(access.compareTo("Fail")==0)
                            System.out.println("\n\t\tUser not found!\n");
                        else {
                            System.out.printf("\n\t\tPassword = ");
                            String psw = log.next();
                            if (access.compareTo(psw) == 0) {
                                int i[] = si.FindUser(id, list);
                                menu(id, list, i[0]);
                            }
                            else
                                System.out.printf("\n\t\tWrong password!\n");
                        }
                        break;

                    case 2:
                        boolean flag = true;
                        System.out.printf("\n\n\t\tUser ID = ");
                        id = log.next();
                        while (flag) {
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getId().compareTo(id) == 0)
                                    flag = false;
                            }
                            if (flag == false) {
                                System.out.println("\n\tError! ID already exists. Choose another name.");
                                System.out.printf("\n\t\tUser ID = ");
                                id = log.next();
                                flag = true;
                            } else {
                                System.out.println("\n\n\t\tID available.\n\t\tName = ");
                                String n = log.next();
                                System.out.println("\n\t\tSurname = ");
                                String s = log.next();
                                System.out.println("\n\t\tEmail = ");
                                String e = log.next();
                                System.out.println("\n\t\tVeichle = ");
                                String v = log.next();
                                System.out.println("\n\t\tCV = ");
                                String c = log.next();
                                System.out.println("\n\t\tPassword = ");
                                String p = log.next();
                                list.add(new User(n, s, e, v, c, id, p));
                                si.addUsers(list);
                                break;
                            }
                        }
                        break;
                    case 3:
                        f = false;
                        break;
                    default:
                        System.out.println("Error, wrong digit");
                        break;
                }
            }

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static void title() {
        System.out.println("\t\t# # # # # # # # # # # # # # # # # # # #\n\t\t#\n\t\t#\tWELCOME\n\t\t#\n\t\t# " +
                "1) Log in\n\t\t#\n\t\t# " +
                "2) Create an account\n\t\t#\n\t\t# " +
                "3) Exit\n\t\t#\n\t\t" +
                "# # # # # # # # # # # # # # # # # # # #");
    }

    private static void menu(String id_name, List<User> list, int i) {
        Scanner log = new Scanner(System.in);
        int choose=0;
        while (choose!=3){
            System.out.println("\n\n\t\t# # # # # # # # # # # # # # # # # # # #\n\t\t#\n\t\t#\tWelcome " +id_name+ "\n\t\t#\n\t\t# " +
                    "1) Edit Account\n\t\t#\n\t\t# " +
                    "2) All Users\n\t\t#\n\t\t# " +
                    "3) Log out\n\t\t#\n\t\t" +
                    "# # # # # # # # # # # # # # # # # # # #");
            choose = log.nextInt();
            switch (choose) {

                case 1: {
                    System.out.printf(list.get(i).toString());
                    System.out.printf("\n\n\nEdit: ");
                    String a = log.next();
                    if(a.isEmpty())
                        System.out.printf("\nChoose a field to edit");
                    if(a.compareTo("Name")==0) {
                        System.out.printf("New Name: ");
                        a = log.next();
                        list.get(i).setName(a);
                    }
                    if(a.compareTo("Password")==0) {
                        System.out.printf("New Password: ");
                        a = log.next();
                        list.get(i).setPsw(a);
                    }
                    if(a.compareTo("Email")==0) {
                        System.out.printf("New Email: ");
                        a = log.next();
                        list.get(i).setEmail(a);
                    }
                    if(a.compareTo("Vehicle")==0) {
                        System.out.printf("New Vehicle: ");
                        a = log.next();
                        list.get(i).setVehicle(a);
                    }

                }

                    break;

                case 2:
                    System.out.println(list);
                    break;

                case 3:
                    break;

                default:
                    System.out.println("\n\t\tError, wrong digit");
                    break;
            }
        }
    }

    public static void logIn() {

    }
}
