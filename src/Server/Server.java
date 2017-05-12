package Server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

/**
 * Created by Faßreiter on 08.05.2017.
 */
public class Server {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            IFDbOperations op = new DbOperations();
            IFDbOperations stub = (IFDbOperations) UnicastRemoteObject.exportObject(op, 0);
            reg.bind("jdbc:oracle", stub);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
