import com.axw.util.C3P0Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class C3P0test {
    public static void main(String[] args) throws SQLException {
        Connection connection1= C3P0Utils.getConnection();
        Connection connection2= C3P0Utils.getConnection();
        Connection connection3= C3P0Utils.getConnection();
        Connection connection4= C3P0Utils.getConnection();
        Connection connection5= C3P0Utils.getConnection();

        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);
        System.out.println(connection4);
        System.out.println(connection5);
        C3P0Utils.close(connection1,null,null);
        C3P0Utils.close(connection2,null,null);
        C3P0Utils.close(connection3,null,null);
        C3P0Utils.close(connection4,null,null);
        C3P0Utils.close(connection5,null,null);
    }
}
