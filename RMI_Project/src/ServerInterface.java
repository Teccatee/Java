import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {

    String logIn(String a, List<User> c) throws RemoteException;
    List<User> allUsers() throws RemoteException;
    void addUsers(List<User> new_list) throws RemoteException;

}

