package Client;

import Server.DCustomer;
import Server.DbObject;
import Server.DbSet;
import Server.IFDbOperations;


import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            DbSet dbo = (DbSet) op.select("SELECT * FROM DCUSTOMER");

            List<DCustomer> customers = new ArrayList<>();
            for (DbObject c : dbo) {
                customers.add(new DCustomer((BigDecimal) c.get(0), (BigDecimal) c.get(1), (String)c.get(2), (String) c.get(3),(String) c.get(4), (String) c.get(5)));
            }
            for (DCustomer c: customers
                 ) {
                System.out.println(c);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
