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
            si.LoadTxtFile(list);
            boolean f = true;

            while (f) {
                list = si.getUserList();
                title();
                int choose = log.nextInt();
                switch (choose) {
                    case 1:
                        list = si.getUserList();
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
                                list = si.getUserList();
                                menu(id, list, i[0], si);
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
                                System.out.printf("\n\t\tName = ");
                                String n = log.next();
                                System.out.printf("\n\t\tSurname = ");
                                String s = log.next();
                                System.out.printf("\n\t\tEmail = ");
                                String e = log.next();
                                System.out.printf("\n\t\tVeichle = ");
                                String v = log.next();
                                System.out.printf("\n\t\tCV = ");
                                String c = log.next();
                                System.out.printf("\n\t\tPassword = ");
                                String p = log.next();
                                list.add(new User(n, s, e, v, c, id, p));
                                si.addUsers(list);
                                si.setUserList(list);
                                si.SaveToTxtFile(list);
                                break;
                            }
                        }
                        break;
                    case 3:
                        si.SaveToTxtFile(list);
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
        System.out.println("\n\t\tWELCOME\n\n\t\t1) Log in\n\t\t2) Create an account\n\t\t3) Exit");

    }

    private static void menu(String id_name, List<User> list, int i, ServerInterface si) {
        Scanner log = new Scanner(System.in);
        int choose=0;
        while (choose!=3){
            System.out.println("\n\n\t\t\tWelcome " +id_name+ "\n\n\t\t" +
                    "1) Edit Account\n\t\t" +
                    "2) All Users\n\t\t" +
                    "3) Log out");
            choose = log.nextInt();
            switch (choose) {

                case 1:
                    System.out.printf(list.get(i).toString());
                    System.out.println("\n\tWhat do you want to edit?\n\1) Name\n2) Password\n3) Email\n4) Vehicle\n" +
                            "5) Cv");
                    String a;
                    switch (choose) {
                        case 1:
                            System.out.printf("New Name: ");
                            a = log.next();
                            list.get(i).setName(a);
                            break;
                        case 2:
                            System.out.println("New password");
                            a = log.next();
                            list.get(i).setPsw(a);
                            break;
                        case 3:
                            System.out.printf("New Email: ");
                            a = log.next();
                            list.get(i).setEmail(a);
                            break;
                        case 4:
                            System.out.printf("New Vehicle: ");
                            a = log.next();
                            list.get(i).setVehicle(a);
                            break;
                        case 5:
                            System.out.printf("New Cv: ");
                            a = log.next();
                            list.get(i).setCv(a);
                            break;
                            default:
                                break;
                    }
                    try {
                        si.SaveToTxtFile(list);
                    } catch (RemoteException e) {
                        e.printStackTrace();
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
}
