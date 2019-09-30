import javax.swing.plaf.ListUI;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
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

    public List<User> getUserList() { return userList; }
    public List<User> setUserList(List<User> a) { return userList = a; }


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
        list.add(new User("Daniel", "Pispisa", "danielpispisa@gmail.com", "OpelAstra", "130", "Pispis", "92"));
        list.add(new User("Pippo", "Lachina", "pippolachina@gmail.com", "FiatPunto", "80", "Pippo", "1234"));
        list.add(new User("Pietro", "Pisacane", "pietropisacane@gmail.com", "Peugeot206", "65", "Pietro", "pp"));
        return list;
    }

    public void SaveToTxtFile (List <User> c) throws RemoteException {
        int i;
        FileWriter fw = null;
        //System.out.printf("\nUpdating local database..\n");
        try {
            fw = new FileWriter("Database.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (i=0; i < c.size(); i++) {
            try {
                fw.write(c.get(i).getName());
                fw.write(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fw.write(c.get(i).getSurname());
                fw.write(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fw.write(c.get(i).getEmail());
                fw.write(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fw.write(c.get(i).getVehicle());
                fw.write(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fw.write(c.get(i).getCv());
                fw.write(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fw.write(c.get(i).getId());
                fw.write(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fw.write(c.get(i).getPsw());
                fw.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fw.close();
            //System.out.printf(+i+" users are saved to local database\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoadTxtFile (List<User> c) throws RemoteException{
        int j=0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("Database.txt"));
        } catch (FileNotFoundException e) {
            System.out.printf("\nError, local database not found!\n");
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
    //System.out.printf("\nLoading successfull "+j+" new users from local database!\n");
    }

    public static void main(String args[]){

        try {
            System.setProperty("java.rmi.server.hostname","localhost");

            // when testing on remote node, a registry previously located must be used
            Registry registry = LocateRegistry.getRegistry();
            ServerInterface si = new Server(initializeList());
            Server s = new Server(initializeList());
            // when testing on remote node, a registry previously located must be used
            //registry.bind("databaseservice",server);
            // when testing on local:
            Naming.rebind("databaseservice",si);

            while(true) {
                Thread.sleep(1000);
                s.LoadTxtFile(s.userList);
                s.SaveToTxtFile(s.userList);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        */
    }
}