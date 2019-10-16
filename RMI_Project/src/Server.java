import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Server extends UnicastRemoteObject implements ServerInterface {

    private static List<User> userList;

    public List<User> getUserList() { return userList; }

    public Server(List<User> list) throws RemoteException {
        super();
        userList = list;
    }

    public Server() throws RemoteException {
        super();
    }

    public static void main(String[] args) {

        try {
            System.setProperty("java.rmi.server.hostname", "localhost");
            Registry registry = LocateRegistry.getRegistry();
            ServerInterface si = new Server(initializeList());
            Naming.rebind("databaseservice", si);
            Server server = new Server();
            server.LoadTxtFile();
            System.out.println("Server Aggiornato...");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setUserList(List<User> a) {
        userList = a;
        SaveToTxtFile();
    }

    @Override
    public String logIn(String a) throws RemoteException {
        int[] result = FindUser(a);
        if(result[1]==1)
            return userList.get(result[0]).getPsw();
        else
        return "Fail";
    }

    public int[] FindQRcode (String vecId) throws RemoteException {
        int j, k=0;
        String p;
        for(j=0; j<userList.size(); j++) {
            p=userList.get(j).getId()+userList.get(j).getvehicles().toString();
            if(p.compareTo(vecId)==0){
                k=1;
                break;
            }
        }
        return  new int[]{j,k};
    }

    @Override
    public int[] FindUser(String b) throws RemoteException {
        List<User> d = userList;
        int j, k = 0;
        for (j = 0; j < d.size(); j++) {
            if (d.get(j).getId().compareTo(b) == 0) {
                k = 1;
                break;
            }
        }
        return new int[]{j, k};
    }

    @Override
    public void addUsers(String n, String s, String e, String id, String p, boolean f) throws RemoteException {
        userList.add(new User(n, s, e, id, p));
        if (f==true)
            SaveToTxtFile();
    }

    private static List<User> initializeList() {
        List<User> list = new ArrayList<>();
        return list;
    }

    @Override
    public void removeUser(int i) throws RemoteException {
        userList.remove(i);
        SaveToTxtFile();
    }

    public void SaveToTxtFile() {
        List<User> c = userList;
        FileWriter fw = null;
        try {
            fw = new FileWriter("Database.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i=0; i < c.size(); i++) {
            try {
                fw.write(c.get(i).StringToFile());
                fw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!c.get(i).getvehicles().isEmpty()) {
                for (int j = 0; j < c.get(i).getvehicles().size(); j++) {
                    try {
                        fw.write(c.get(i).getvehicles().get(j).StringToFile());
                        fw.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            try {
                fw.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoadTxtFile() {
        List<User> c = userList;
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
                    String n= (String) st.nextElement();
                    String s= (String) st.nextElement();
                    String e= (String) st.nextElement();
                    String id= (String) st.nextElement();
                    String p= (String) st.nextElement();
                int[] i = FindUser(id);
                    j++;
                    if (i[1]==1) {
                        j--;
                        break;
                    }
                    else {
                        addUsers(n, s, e, id, p, false);
                        i = FindUser(id);
                        List <Vehicle> new_VehicleList = getUserList().get(i[0]).getvehicles();
                        while (st.hasMoreElements()) {
                            String brand = (String) st.nextElement();
                            String type = (String) st.nextElement();
                            String hp = (String) st.nextElement();
                            String license = (String) st.nextElement();
                            String disp = (String) st.nextElement();
                            new_VehicleList.add(new Vehicle(type, hp, license, disp, brand));
                            userList.get(i[0]).setvehicles(new_VehicleList);
                       }
                    }

            line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            SaveToTxtFile();
          reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}