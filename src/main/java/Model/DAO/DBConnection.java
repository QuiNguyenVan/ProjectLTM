package Model.DAO;



import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
 private static final String URL = "jdbc:mysql://localhost:3306/PROJECTLTM";
 private static final String username="root";
 private static final String password="";
 	public static Connection getConnection()
 	{
 		Connection conn=null;
 		try
 		{
 			Class.forName("com.mysql.cj.jdbc.Driver");
 			conn=DriverManager.getConnection(URL,username,password);
 		}catch(Exception e) {e.getStackTrace();}
 		return conn;
 	}
}
