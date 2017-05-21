package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Faßreiter on 08.05.2017.
 */
public interface IFDbOperations extends Remote {
    void authorize(String url, String user, String pw) throws RemoteException;
    DbSet select(String table) throws RemoteException, SQLException;

}
