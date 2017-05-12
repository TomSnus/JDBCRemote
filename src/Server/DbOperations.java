package Server;
import Server.DCustomer;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.Map;

/**
 * Created by Faßreiter on 08.05.2017.
 */
public class DbOperations implements IFDbOperations {
    Connection con;

    @Override
    public void authorize(String url, String user, String pw) throws RemoteException {
        try {
            con = DriverManager.getConnection(url, user, pw);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void select(String table) throws RemoteException, SQLException {

        try {
            Map map = con.getTypeMap();
            map.put("CUSTOMER_DATA", Class.forName("Server.DCustomer"));
            con.setTypeMap(map);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(con != null) {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM DCUSTOMER");
                int custNo = 0;
                rs.beforeFirst();
                while(rs.next())
                    custNo = rs.getInt("CSID");
                int csid = rs.getInt("CUSTID");
                    System.out.println(custNo + " " +csid);
                    System.out.println((String) rs.getString("NAME"));
                   // System.out.println(customer);
                 stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }


    }
}
