package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAO;


@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StudentDAO dao = new StudentDAO();
		int sid = Integer.parseInt(request.getParameter("sid"));
		try {
			int result = dao.delete(sid);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
			return;
		}
		response.sendRedirect("DeleteListController");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
