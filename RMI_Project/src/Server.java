import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Server extends UnicastRemoteObject implements ServerInterface {

    private List<User> userList;


    public Server(List<User> list) throws RemoteException {
        super();
        this.userList = list;
    }

    @Override
    public String logIn(String a, List<User> c) throws RemoteException {
        int result [] = FindUser(a, c);
        if(result[1]==1)
                return c.get(result[0]).getPsw();
        else
        return "Fail";
    }
    @Override
    public int [] FindUser(String b, List<User> d) throws RemoteException {
        int j, k=0;
        for (j = 0; j < d.size(); j++) {
            if (d.get(j).getId().compareTo(b) == 0) {
            k=1;
            break;
            }
        }
        return new int[]{j, k};
    }

    @Override
    public List<User> allUsers() throws RemoteException {
        return userList;
    }

    @Override
    public void addUsers(List<User> new_list) throws RemoteException {
        this.userList = new_list;
    }


    private static List<User> initializeList() {
        List<User> list = new ArrayList<>();
        list.add(new User("Daniel", "Pispisa", "danielpispisa@gmail.com", "Opel Astra", "130", "Pispis", "92"));
        list.add(new User("Pippo", "La China", "pippolachina@gmail.com", "Fiat Punto", "80", "Pippo", "1234"));
        list.add(new User("Pietro", "Pisacane", "pietropisacane@gmail.com", "Peugeot 206", "65", "Pietro", "pp"));
        return list;
    }

    public void LoadTxtFile (List<User> c) throws RemoteException{
        int j=0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("Database.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {

            String line = reader.readLine();
            while (line!=null) {
                StringTokenizer st = new StringTokenizer(line);
                while (st.hasMoreElements()) {
                    String n= (String) st.nextElement();
                    String s= (String) st.nextElement();
                    String e= (String) st.nextElement();
                    String v= (String) st.nextElement();
                    String cv= (String) st.nextElement();
                    String id= (String) st.nextElement();
                    String p= (String) st.nextElement();
                    int i[]=FindUser(id, c);
                    j++;
                    if (i[1]==1) {
                        j--;
                        break;
                    }
                    else {
                        c.add(new User(n, s, e, v, cv, id, p));
                        addUsers(c);
                    }
                }
            line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    System.out.printf("Loading successfull "+j+" users from local database!\n");
    }

    public static void main(String args[]){

        try {
            //System.setProperty("java.rmi.server.hostname","localhost");

            // when testing on remote node, a registry previously located must be used
            Registry registry = LocateRegistry.getRegistry();

            ServerInterface server = new Server(initializeList());

            // when testing on remote node, a registry previously located must be used
            registry.bind("databaseservice",server);
            // when testing on local:
            //Naming.rebind("calculatorservice",server);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
        /*
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        */
    }
}