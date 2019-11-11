import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {

    int logIn(String a) throws RemoteException;

    int[] FindUser(String a) throws RemoteException;
    int [] FindQRcode(String vecID) throws RemoteException;
    void addUsers(String n, String s, String e, String id, String p, boolean f) throws RemoteException;
    void removeUser(int i) throws RemoteException;
    void setUserList(List<User> c) throws RemoteException;
    List<User> getUserList() throws RemoteException;
}

