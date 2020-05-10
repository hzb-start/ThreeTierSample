package nuc.hzb.dao;
import java.util.List;
import nuc.hzb.entity.Student;
public interface IStudentDao {
	public Student queryStudentBySno(int sno);
	public List<Student> queryAllStudents();
	public boolean isExist (int sno);
	public boolean addStudent(Student student);
	public boolean updateStudentBySno(int sno, Student student);
	public boolean deleteStudentBySno(int sno);
	public int getTotalCount();
	// currentPage：当前页（页码）pageSize：页面大小（每页显示的数据条数）
	public List<Student> queryStudentByPage(int currentPage, int pageSize);
}
