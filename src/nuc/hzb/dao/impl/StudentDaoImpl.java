package nuc.hzb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nuc.hzb.dao.IStudentDao;
import nuc.hzb.entity.Student;
import nuc.hzb.util.DBUtil;


/**
 * 数据访问层；原子性的增删改查
 * @author 黄朝博
 * 
 */
public class StudentDaoImpl implements IStudentDao {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
	private static final String USERNAME = "scott";
	private static final String PASSWORD = "tiger";	
/**
 * 根据学号 查询学生
 * @param sno
 * @return Student
 */	
		public Student queryStudentBySno(int sno) {
			Student student = null;
			Connection connection = null;	
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				String sql = "select * from student where sno = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, sno);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					int no= rs.getInt("sno");
					String name = rs.getString("sname");
					int age = rs.getInt("sage");
					String address = rs.getString("saddress");
					student = new Student(no, name, age, address);
				}
				return student;
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
			} finally {
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
/**
 * 查询全部学生(很多学生)
 * @return List<Student>
 * 已经改好
 */
	public List<Student> queryAllStudents() {	
		Student student = null;
		List<Student> students = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from student";
			rs = DBUtil.executeQuery(sql, null);
			while (rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				student = new Student(no, name, age, address);
				students.add(student);
			}
			return students;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(rs, pstmt, DBUtil.connection);
		}
	}
	public boolean isExist (int sno) {
		// true此人存在 false此人不存在
		return queryStudentBySno(sno) == null ? false : true;
	}
	
	/**
	 * 将添加的学生对象传过来
	 * @param student
	 * @return boolean
	 */
	public boolean addStudent(Student student) {	
		String sql = "insert into student(sno,sname,sage,saddress) values(?,?,?,?)";	
		Object[] params = {student.getSno(),student.getSname(),student.getSage(),student.getSaddress()};
		return DBUtil.executeUpdate(sql, params);			
	}
	/**
	 * 根据学号修改学生：根据sno知道待修改的人，把这个人修改成student
	 * @param sno
	 * @param student
	 * @return boolean
	 */	
	public boolean updateStudentBySno(int sno, Student student) {
		// 3号——>改人
		//System.out.println(sno);
		
		System.out.println(student.getSage()+"***************************");
		String sql = "update student set sname=?, sage=?, saddress=? where sno=?";
		Object[] params = {student.getSname(),student.getSage(),student.getSaddress(),sno};
		return DBUtil.executeUpdate(sql, params);	
	}

	/**
	 * 根据学号删除学生
	 * @param sno
	 * @return boolean
	 */
	public boolean deleteStudentBySno(int sno) {
		String sql = "delete from student where sno = ?";
		Object[] params = {sno};
		return DBUtil.executeUpdate(sql, params);
	}
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		String sql = "select count(1) from student";
		return DBUtil.getTotalCount(sql);
	}
	@Override
	public List<Student> queryStudentByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select *from "
				+"("
			    +"select rownum r, t.* from"
				+"(select s.* from student s order by sno asc) t "
			    
				+"where rownum<=?"
				+")"
				+ "where r>=?"
			 ;
		Object[] params = {currentPage * pageSize, (currentPage-1) * pageSize+1}; 
		List<Student> students = new ArrayList<>();
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()) {
				Student student = new Student(rs.getInt("sno"),rs.getString("sname"),rs.getInt("sage"),rs.getString("saddress")) ;
				students.add(student) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}
	
}
