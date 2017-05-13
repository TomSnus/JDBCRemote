package Server;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    public List<DCustomer> select(String table) throws RemoteException, SQLException {
        List<DCustomer> customers = new ArrayList<DCustomer>();
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
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM DCUSTOMER");
                int custNo = 0;
                rs.beforeFirst();
                while(rs.next()) {
                    custNo = rs.getInt("CSID");
                    int csid = rs.getInt("CUSTID");
                    System.out.println(custNo + " " +csid);
                    System.out.println((String) rs.getString("NAME"));
                    customers.add(new DCustomer(rs.getInt("CSID"), rs.getInt("CUSTID"), rs.getString("NAME"), rs.getString("PLACE"),
                            rs.getString("STATE"), rs.getString("COUNTRY")));
                    // System.out.println(customer);
                }

                 stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }


        return customers;
    }
}
