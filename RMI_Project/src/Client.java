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
            List<User> list;
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
                                System.out.printf("\n\t\tVehichle = ");
                                String v = log.next();
                                System.out.printf("\n\t\tCV = ");
                                String c = log.next();
                                System.out.printf("\n\t\tPassword = ");
                                String p = log.next();
                                si.addUsers(list, n, s, e, v, c, id, p);
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
        System.out.println("\n\t\tWELCOME\n\n\t\t1) Log in\n\t\t2) Create an account\n\t\t3) Exit");

    }

    private static void menu(String id_name, List<User> list, int i, ServerInterface si) throws RemoteException {
        Scanner log = new Scanner(System.in);
        int choose=0;
        boolean flag = true;
        while (flag){
            System.out.println("\n\n\t\t\tWelcome " +id_name+ "\n\n\t\t" +
                    "1) Edit Account\n\t\t" +
                    "2) All Users\n\t\t" +
                    "3) Delete Account\n\t\t" +
                    "4) Log out");

            choose = log.nextInt();
            switch (choose) {

                case 1:
                    System.out.printf(list.get(i).toString());
                    System.out.println("\n\tWhat do you want to edit?\n\t\t1) Name\n\t\t2) Surname\n\t\t3) Password\n\t\t" +
                            "4) Email\n" +
                            "\t\t5) Vehicle\n\t\t6) Cv");
                    String a;
                    choose = log.nextInt();
                    switch (choose) {
                        case 1:
                            System.out.printf("\t\tNew Name: ");
                            a = log.next();
                            list.get(i).setName(a);
                            si.setUserList(list);
                            break;
                        case 2:
                            System.out.printf("\t\tNew Surname: ");
                            a = log.next();
                            list.get(i).setSurname(a);
                            si.setUserList(list);
                            break;
                        case 3:
                            System.out.printf("\t\tNew password: ");
                            a = log.next();
                            list.get(i).setPsw(a);
                            si.setUserList(list);
                            break;
                        case 4:
                            System.out.printf("\t\tNew Email: ");
                            a = log.next();
                            list.get(i).setEmail(a);
                            si.setUserList(list);
                            break;
                        case 5:
                            System.out.printf("\t\tNew Vehicle: ");
                            a = log.next();
                            list.get(i).setVehicle(a);
                            si.setUserList(list);
                            break;
                        case 6:
                            System.out.printf("\t\tNew Cv: ");
                            a = log.next();
                            list.get(i).setCv(a);
                            si.setUserList(list);
                            break;
                        default:
                            break;
                    }
                    break;

                case 2:
                    System.out.println(list);
                    break;

                case 3:
                    si.removeUser(list, i);
                    flag = false;
                    break;

                case 4:
                    flag = false;
                    break;

                default:
                    System.out.println("\n\t\tError, wrong digit");
                    break;
            }
        }
    }
}
