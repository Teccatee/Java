import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {

    String logIn(String a, List<User> c) throws RemoteException;
    int [] FindUser(String a, List<User> c) throws RemoteException;
    int [] FindQRcode(String vecID) throws RemoteException;
    void addUsers(List<User> new_list, String n, String s, String e, String id, String p, boolean f) throws RemoteException;
    void removeUser(List<User> new_list, int i) throws RemoteException;
    void setUserList(List<User> c) throws RemoteException;
    List<User> getUserList() throws RemoteException;
    List<User> allUsers() throws RemoteException;
}

