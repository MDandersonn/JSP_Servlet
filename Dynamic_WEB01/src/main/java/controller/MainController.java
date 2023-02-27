package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/main")
public class MainController extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doGet(req, resp);
		System.out.println("사용자가 get요청을 보냈슴니다.");
		
		String n=req.getParameter("num");
		Integer number=0;
		if(n !=null && !n.isEmpty()) {//빈문자열이아니면 변환하고 
			number=Integer.parseInt(n);
		}
//		null과 빈문자열은 다른것.!
//		빈문자열이면 number는 0 이고 n는 ""이라는말.
//		리쿼스트객체에 속성으로 저장 (속성명,속성값) =(이름,저장값)
		req.setAttribute("number", number);
		
		System.out.println("클라이언트가 보낸 데이터:"+n);
		
		
//		req,resp객체를 jsp파일로 전달하는과정
		req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
//		request객체가 jsp에도 전달이돼
		
//		요청과 응답객체를 모두 jsp에게 전달해서 jsp실행
		
//		위에를 주석처리해야 밑이 보임 (이미 화면에 떳기떄문에) 덮어쓰기개념이아님..
		
		
//		PrintWriter out =resp.getWriter();
//		out.println("<h1>hello</h1>");
//		out.flush();
		
		
//		flush: 버퍼남아있는데이터까지다 비워서  다보내라라는뜻
		
	}
	
//	이건지금안쓰니까 빼고
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doPost(req, resp);
//	}

}
