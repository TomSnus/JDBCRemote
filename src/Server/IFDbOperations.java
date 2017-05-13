package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Faßreiter on 08.05.2017.
 */
public interface IFDbOperations extends Remote {
    void authorize(String url, String user, String pw) throws RemoteException;
    List<DCustomer> select(String table) throws RemoteException, SQLException;

}
