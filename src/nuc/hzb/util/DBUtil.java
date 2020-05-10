package nuc.hzb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


// 通用的数据操作方法
public class DBUtil {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
	private static final String USERNAME = "scott";
	private static final String PASSWORD = "tiger";	
	public static Connection connection = null;	
	public static PreparedStatement pstmt = null;
	public static ResultSet rs = null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	public static PreparedStatement createPreParedStatement(String sql, Object[] params) throws ClassNotFoundException, SQLException {
		pstmt = getConnection().prepareStatement(sql);
		if (params!=null) {
			for (int i=0;i<params.length;i++) {
				pstmt.setObject(i+1, params[i]);
			}	
		}
		return pstmt;		
	}
	public static void closeAll(ResultSet rs, Statement stmt,Connection connection) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//查询总数
		public static int getTotalCount(String sql ) { //select count(1) from student
			int count = -1 ;
			try {
				 pstmt = createPreParedStatement(sql, null );
				 rs = pstmt.executeQuery()  ;//88
				if(rs.next()) {
					count = rs.getInt(1) ;
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeAll(rs, pstmt, connection);
			}
			return count;
		}
	// 通用增删改
	public static boolean executeUpdate(String sql, Object[] params) {		
		try {
			// 获取连接对象			
			pstmt = createPreParedStatement(sql, params);		
			int count = pstmt.executeUpdate();
			if (count > 0) {
				System.out.println(count+"*******************");
				return true;
			} else {
				return false;
			}			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;			
		} finally {
			closeAll(null,pstmt,connection);
		}
	}
	
	// 通用查询：通用表示 适合于所有查询
	public static ResultSet executeQuery(String sql,Object[] params) {
		try {
			pstmt = createPreParedStatement(sql, params);
			rs = pstmt.executeQuery();
			return rs;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
}
