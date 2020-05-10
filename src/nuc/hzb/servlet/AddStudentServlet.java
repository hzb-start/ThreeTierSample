package nuc.hzb.servlet;

import java.io.IOException;
// import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nuc.hzb.entity.Student;
import nuc.hzb.service.IStudentService;
import nuc.hzb.service.impl.StudentServiceImpl;


public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int no = Integer.parseInt(request.getParameter("sno"));
		String name = request.getParameter("sname");
		int age = Integer.parseInt(request.getParameter("sage"));
		String address = request.getParameter("saddress");
		Student student = new Student(no, name, age, address);
		
		// 接口x = 实现类();
		IStudentService studentService = new StudentServiceImpl();
		boolean result = studentService.addStudent(student);
		/*
		 *   out  request  response session application 
		 *   out:	PrintWriter out = response.getWriter() ;
		 *   session:	request.getSession()
		 *   application:	request.getServletContext()
		 */
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		// PrintWriter out = response.getWriter();	
		
		/*if (result) {
			response.sendRedirect("QueryAllStudentsServlet");
			
		} else {
			out.println("增加失败");
		}*/
		if (!result) {
			request.setAttribute("error", "addError");
		} else {
			request.setAttribute("error", "noaddError");
		}
		request.getRequestDispatcher("QueryStudentByPage").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
