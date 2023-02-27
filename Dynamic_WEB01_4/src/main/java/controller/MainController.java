package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @WebServlet("/main")
public class MainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String n = req.getParameter("num");
		Integer number = 0;
		if(n != null && !n.isEmpty()) {
			number = Integer.parseInt(n);
		}
		req.setAttribute("number", number);
		req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
		System.out.println(req.getAttribute("send"));
	}

}
