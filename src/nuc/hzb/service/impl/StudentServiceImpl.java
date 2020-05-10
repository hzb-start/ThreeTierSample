package nuc.hzb.service.impl;

import java.util.List;

import nuc.hzb.dao.impl.StudentDaoImpl;
import nuc.hzb.entity.Student;
import nuc.hzb.service.IStudentService;

// 业务逻辑层，逻辑性的增删改查（增：查+增）对dao层进行组装
public class StudentServiceImpl implements IStudentService {
	StudentDaoImpl studentDao = new StudentDaoImpl();
	public Student queryStudentBySno(int sno) {
		return studentDao.queryStudentBySno(sno);
	}
	// 查询全部学生
	public List<Student> queryAllStudents(){
		return studentDao.queryAllStudents();
	}
	public boolean updateStudentBySno(int sno, Student student) {
		if (studentDao.isExist(sno)) {
			studentDao.updateStudentBySno(sno, student);
			return true;
		} else {
			return false;
		}
	}
	public boolean deleStudentBySno(int sno) {
		if (studentDao.isExist(sno)) {
			studentDao.deleteStudentBySno(sno);
			return true;
		} else {
			return false;
		}
	}
	public boolean addStudent(Student student) {
		if (!studentDao.isExist(student.getSno())) { // 不存在
			studentDao.addStudent(student);
			return true;
		} else {
			System.out.println("此人已存在！");
			return false;
		}
	}
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return studentDao.getTotalCount();
	}
	// 查询当前页的数据集合
	@Override
	public List<Student> queryStudentsByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return studentDao.queryStudentByPage(currentPage, pageSize);
	}
	
}
