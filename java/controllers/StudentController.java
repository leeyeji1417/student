package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAO;
import dto.StudentDTO;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("*.std")
public class StudentController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		StudentDAO dao = new StudentDAO();
		
		try {
			if (uri.equals("/add.std")) {
				String name = request.getParameter("name"); // 매개변수 받아올 수 있음
				int kor = Integer.parseInt(request.getParameter("kor"));
				int eng = Integer.parseInt(request.getParameter("eng"));
				int math = Integer.parseInt(request.getParameter("math"));

				int result = dao.insert(new StudentDTO(0, 0, name, kor, eng, math, null));
				response.sendRedirect("index.jsp");

			} else if (uri.equals("/list.std")) {////////////
				List<StudentDTO> list = dao.selectAll();
				request.setAttribute("list", list);
				request.getRequestDispatcher("listView.jsp").forward(request, response);

			} 
			else if (uri.equals("/delList.std")) {///////////
				List<StudentDTO> list = dao.selectAll();
				request.setAttribute("list", list);
				request.getRequestDispatcher("deleteView.jsp").forward(request, response);

			}else if (uri.equals("/delete.std")) {////////////
				int sid = Integer.parseInt(request.getParameter("sid"));
				int result = dao.delete(sid);
				response.sendRedirect("delList.std");
				
			}else if (uri.equals("/updList.std")) {
				List<StudentDTO> list = dao.selectAll();
				request.setAttribute("list", list);
				request.getRequestDispatcher("updateView.jsp").forward(request, response);
				
			}else if (uri.equals("/update.std")) {
				int sid = Integer.parseInt(request.getParameter("sid"));
				String name = request.getParameter("name");
				int kor = Integer.parseInt(request.getParameter("kor"));
				int eng = Integer.parseInt(request.getParameter("eng"));
				int math = Integer.parseInt(request.getParameter("math"));
				int result = dao.update(new StudentDTO(0,sid,name,kor,eng,math,null));
				response.sendRedirect("upList.std");

			}else if (uri.equals("/search.std")) {
				String name = request.getParameter("name");
				List<StudentDTO> list = dao.search(name);
				
				request.setAttribute("list", list);
				request.getRequestDispatcher("updateView.jsp").forward(request, response);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
