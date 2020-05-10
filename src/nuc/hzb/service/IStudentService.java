package nuc.hzb.service;

import java.util.List;

import nuc.hzb.entity.Student;

public interface IStudentService {
	public Student queryStudentBySno(int sno);
	public List<Student> queryAllStudents();
	public boolean updateStudentBySno(int sno, Student student);
	public boolean deleStudentBySno(int sno);
	public boolean addStudent(Student student);
	
	// 总数可以传一个sql可以通用
	public int getTotalCount();
	public List<Student> queryStudentsByPage(int currentPage,int pageSize);
	
}
