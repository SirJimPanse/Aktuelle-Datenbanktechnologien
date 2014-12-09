import java.sql.*;
import java.util.Properties;
import java.math.*;
import org.postgresql.Driver;

public class Controller {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	Connection conn = null;

	String url = "jdbc:postgresql://141.100.70.17/study4";
	Properties props = new Properties();
	props.setProperty("user", "study4");
	props.setProperty("password", "study4");

	try {
	    conn = DriverManager.getConnection(url, props);
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	for (int i = 1; i <= 10000; i++) {
	    int x1 = 0;
	    int x2 = 1;
	    int y1 = i * 2;
	    int y2 = (int) (i * 2 + Math.random() * 2);

	    insertData(conn, "INSERT INTO coorinates2(id, geo) VALUES(" + i
		    + ", " + "'LINESTRING(" + x1 + " " + y1 + "," + x2 + " "
		    + y2 + ")');");
	}

	try {
	    if (conn != null) {
		conn.close();
	    }

	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
    }

	public static void insertData(Connection conn, String sql) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {

			try {
				if (pst != null) {
					pst.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
    }

}
