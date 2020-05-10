package nuc.hzb.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nuc.hzb.service.impl.StudentServiceImpl;

public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 删除 传递学号
		request.setCharacterEncoding("utf-8");
		// 接收前端传来的学号
		request.getParameter("sno");
		int no = Integer.parseInt(request.getParameter("sno"));
		StudentServiceImpl service = new StudentServiceImpl();
		boolean result = service.deleStudentBySno(no);
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		// PrintWriter out = response.getWriter();	
		if (result) {
			// response.getWriter().println("删除成功！");
			response.sendRedirect("QueryStudentByPage");// 重新查询所有学生
			
		} else {
			response.getWriter().println("删除失败！");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
