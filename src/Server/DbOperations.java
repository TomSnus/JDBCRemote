package Server;
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
    public DbSet select(String sql) throws RemoteException, SQLException {
        ResultSet rs = null;
        DbSet dbo = new DbSet();
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
                rs = stmt.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                rs.beforeFirst();
                while (rs.next()) {
                    DbObject dbobject = new DbObject();
                   for(int i = 1; i <= columnsNumber; i++) {
                       dbobject.add(rs.getObject(i));
                   }
                   dbo.add(dbobject);

                }
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }
        return dbo;
    }

}
