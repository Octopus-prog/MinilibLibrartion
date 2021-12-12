package minilib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import minilib.util.DBUtil;
import minilib.vo.Title;
import minilib.vo.CodeBookType;
public class ManageTitlesDao {

	public List findAll(){ 
		List list = new ArrayList();
		try{
		//建立数据库连接
		Connection conn=DBUtil.getConnection();
		if (conn==null){
			System.out.print("数据库未建立连接");
		}
		//定义查询语句
		String sql="select * from t_book order by t_book.bookid";
		System.out.print("sql="+sql);
		PreparedStatement pstmt= conn.prepareStatement(sql);
		//查询获得结果集
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()){
			Title title = new Title();
			title.setIsbn(rs.getString(3));
			title.setTitle(rs.getString(5));
			title.setAuthors(rs.getString(6));
			title.setPressid(rs.getString(8));
			list.add(title);
			}
		rs.close();
		pstmt.close();
		conn.close();
		
		}catch(InstantiationException e){
			e.printStackTrace();	
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			  e.printStackTrace();
		}catch(SQLException e){
			  e.printStackTrace();
		}
		return list;
	}
	public List findbookType()throws InstantiationException,IllegalAccessException,SQLException, ClassNotFoundException{
		List list=new ArrayList();
		Connection conn=DBUtil.getConnection();
		Statement stmt=conn.createStatement();
		//定义查询图书类型语句
		String strsql="select codeid,codename from code_booktype order by codeid";
		System.out.print("+++strsql="+strsql+"+++");
		
		ResultSet rs=stmt.executeQuery(strsql);
		while(rs.next()) {
			CodeBookType booktype=new CodeBookType();
			booktype.setCodeId(rs.getString(1));
			booktype.setCodeName(rs.getString(2));
			list.add(booktype);
		}
		rs.close();
		stmt.close();
		conn.close();
		return list;
	}
	public void saveTitle(Title book)throws InstantiationException,IllegalAccessException,SQLException, ClassNotFoundException {
		Connection conn=DBUtil.getConnection();
		String sql="insert into t_book(typeid,title)"+"values('"+book.getTypeid()+"',"+"'"+book.getTitle()+"'"+")";
		System.out.print("saveTitle="+sql);
		Statement stmt;
		stmt=conn.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
		conn.close();
	}
}
