package sec02.ex01;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcServlet
 */
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double won= Double.parseDouble(request.getParameter("won"));
		String money= request.getParameter("select");
		String answer = null;
		if (money.equals("dollar")) {
			answer=String.format("%.6f", won/1300);
			
		}else if (money.equals("yen")) {
			answer=String.format("%.6f", won/1000);
		} else if(money.equals("yuan")){
			answer=String.format("%.6f", won/200);
			
		}
		request.getRequestDispatcher("./view/Calc.jsp?aa="+answer).forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
