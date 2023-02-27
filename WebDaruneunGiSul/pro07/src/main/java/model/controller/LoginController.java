package model.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.MemberDTO;
import model.service.MemberService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	List userList= new ArrayList();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session =req.getSession();
		if(session.getAttribute("isLogin")==null) {//로그인 안되어잇는경우
//			로그인을 시도합니다. 
			Cookie[] allValues= req.getCookies();
			for(Cookie s : allValues) {
				if(s.getName().equals("id")) {
					req.setAttribute("id" ,s.getValue() );
				}
			}
			req.getRequestDispatcher( "./view/Login.jsp").forward(req, resp);
		
		}else {//로그인 되어있는경우
			resp.sendRedirect("./");
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id= req.getParameter("id");
		String pwd= req.getParameter("pwd");
		String remember= req.getParameter("remember");//켜져있으면on 꺼져있으면 null
		MemberDTO dtoId= new MemberDTO();
		dtoId.setId(id);
		MemberService service= new MemberService();
		MemberDTO dto= service.selectById(dtoId);
		
		
		if(dto!=null) {//아디가 맞았을때
			if(pwd.equals(dto.getPwd()) ) {// 아디, 비번이 맞았을때	
				Cookie c= new Cookie("id",id);
				if(remember!=null && remember.equals("on")) {
					c.setMaxAge(24*60*60);
				}else {
					c.setMaxAge(0);
				}
				
				resp.addCookie(c);
				HttpSession session = req.getSession();
				ServletContext context = getServletContext();
				
				userList.add(dto);
				context.setAttribute("userList", userList);
				session.setAttribute("isLogin", true);
				session.setAttribute("login", dto);
//				req.getRequestDispatcher("./index.jsp").forward(req, resp);
				//새로고침할때마다 계속 실행되서 접속아이디가 증가하는 문제가남..
				
				
				resp.sendRedirect("./index.jsp");
				return;
			}
		}
		//비번이 틀리거나 유저아이디가 없는경우.
		String msg="아이디 혹은 비밀번호를 확인해주세요.";
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("./view/Login.jsp").forward(req, resp);

	
		
		
		
	}

}
