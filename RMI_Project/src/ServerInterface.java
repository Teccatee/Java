import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {

    String logIn(String a, List<User> c) throws RemoteException;
    List<User> allUsers() throws RemoteException;
    void addUsers(List<User> new_list) throws RemoteException;
    int [] FindUser(String a, List<User> c) throws RemoteException;
    void LoadTxtFile(List<User> c) throws RemoteException;
    void SaveToTxtFile(List<User>c) throws RemoteException;
    List<User> setUserList(List<User> c) throws RemoteException;
    List<User> getUserList() throws RemoteException;
}

