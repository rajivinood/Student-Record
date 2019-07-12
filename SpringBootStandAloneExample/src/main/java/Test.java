import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test {
	@SuppressWarnings("null")
	public static void main(String[] args) {
		// PreparedStatement stmt = null;
		Connection c = null;
		try {
			System.out.println("ok");
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://10.10.10.20:5432/test", "postgres", "postgres");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			String sql = "INSERT INTO STUDENT (personid,firstname,lastname,address,city)" + "VALUES (?, ?,?, ?,?);";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, 1);
			stmt.setString(2, "Raji");
			stmt.setString(3, "Vinod");
			stmt.setString(4, "California");
			stmt.setString(5, "abc");
			System.out.println("query" + sql);
			int i = stmt.executeUpdate();
			System.out.println(i);
			System.out.println("query222222222");
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
