package minilib.util;
import java.sql.Connection;
import java.sql.*;
public class DBUtil {
	private static String rootname="root";  	
private static String rootpass="77820vygr";
	//MySQL的驱动程序
	private static String driver="com.mysql.cj.jdbc.Driver";
	//数据库的位置
	//private static String url="jdbc:mysql://localhost:3306/book";
     private static String url="jdbc:mysql://127.0.0.1:3306/book?useSSL=true&serverTimezone=GMT";
	//创建与数据库连接
	public static Connection getConnection()throws InstantiationException,IllegalAccessException,ClassNotFoundException,SQLException{
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url,rootname,rootpass);
		}
}