package Client;

import Server.IFDbOperations;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import static Client.ConnectionContract.*;

/**
 * Created by Faßreiter on 08.05.2017.
 */
public class Client {

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost",1099);
            IFDbOperations op = (IFDbOperations) reg.lookup("jdbc:oracle");
            op.authorize(JDBC_CON_URL, JDBC_CON_USER, JDBC_CON_PW);
            op.select("DCUSTOMER");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
