import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {

    String logIn(String a, String b) throws RemoteException;
    List<User> allUsers() throws RemoteException;


}

