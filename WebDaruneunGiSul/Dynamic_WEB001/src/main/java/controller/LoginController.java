package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.UserDTO;
import model.service.UserService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		세션이없는경우 새로생성후 반환
		HttpSession session=req.getSession();
//		req.getSession(false);매개변수가false이면  있으면 있는세션찾아서 저장 없으면 null(세션안만듦)
//		session.getAttribute("세션명");
//		session.setAttribute("세션명1", 저장할객체);
//		session.setAttribute("세션명2", 저장할객체);
//		session.setAttribute("세션명3", 저장할객체);
		
//		브라우저에 저장된 세션ID만 동일하면 언제든지 서버에저장되어있는것을 req.getSession으로 찾아오기가능
//		반면, request는 1회성  공유가 안됨.
//		request요청하고 또 request요청하면 유효하지않음
//		session.removeAttribute("세션명1");
//		session.invalidate();  //세션 객체자체를  삭제
//		로그인 -> 세션 생성 (정보저장)
//		로그아웃 -> 세션을 삭제 (invalidate)
		
		if(session.getAttribute("login") !=null) { //로그인이 되어있는경우
//			
//			로그인이 되어있다면 메인페이지로
			resp.sendRedirect(req.getContextPath());
//			context root= /web01
			return;
		}
//		로그인이 안되어있는경우. 로그인을 시도합니다.
		Cookie[] cookies =req.getCookies();
		for(Cookie c:cookies) {
//			System.out.println(c.getName()+"|"+c.getValue());
			if(c.getName().equals("remember")) {
//				포스트할때 쿠키를 만들어놓음
//				리멤버라는 쿠키가있으면 쿠키값을 가져옴 쿠키값은(사용자ID)
//				쿠키: remember-userid
//				System.out.println("c.get: "+ c.getValue());
				
//				쿠키정보를 로그인jsp에서 초기값으로 설정할수있게끔 데이터를 넘겨주기위해서 req객체에다가 setattribute로 설정
			
//				                      속성명    쿠키에섳찾은ID값
				req.setAttribute("remember", c.getValue());
//				로그인.jsp에서 <input type="text" name="userId" value="${cookie.remember.value }">
//				이걸쓰면 위에처럼 setAttribute할 필요없다.
			}
		}
//									쿠키명 ,쿠키값
//		Cookie cookie =new Cookie("name","value");
		
//		모든 쿠키에는 유효시간이 있다 초단위.
//		localhost:8080/web01/ 안이면 요청할때마다 계속 브라우저가 서버에보낸다

		
//		cookie.setMaxAge(60*60*24);
//		resp.addCookie(cookie);
		
		req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId= req.getParameter("userId");
		String password= req.getParameter("password");
		String remember = req.getParameter("remember");//아이디기억하기 체크여부

		//	체크가 되어있는상태로 로그인:on       체크안되어있는상태로로그인 null
//		System.out.println(remember);
		
		UserDTO dto =new UserDTO();
		dto.setUserId(userId);
		dto.setPassword(password);
		UserService service= new UserService();
		UserDTO userData = service.login(dto);
		
		Cookie cookie= new Cookie("remember",userId);
//		로그인할때 쿠키명은 remember  쿠키값은userid로 설정
		
//		쿠키 만듦 근데 로그인성공했을때만 쿠키를 전송함
		if(userData !=null) {//유저아이디가 존재하고 비번이 맞는경우
			HttpSession session =req.getSession();
//			로그인정보저장
			session.setAttribute("login",true);//로그인이되어있다고 셋팅
			
//			계정정보저장.
			session.setAttribute("user",userData);
//			로그인성공했을때,  사용자 정보도 세션에다가 저장
			
//			5일 유지 .
			if(remember !=null) {//아아디기억하기가 체크되어 있을때 
//				유효기간 5일로 설정
				cookie.setMaxAge(60*60*24*5);
				
			}else {//아이디기억하기가 체크되어있지 않을때.
//				remember가 null인경우 유효시간만0으로설정해주면 브라우저가 지워줌
				cookie.setMaxAge(0);
			}
			resp.addCookie(cookie);
//			응답할때 쿠키를 보내줄수있게 추가한거.
			
			resp.sendRedirect(req.getContextPath());
			
		}else {//비번이 틀리거나 유저아이디가 없는경우.
//			do get에서 만든쿠키 사라짐 (서버에서 사라짐)
//			메서드안에서생성된데이터들은 메서드가종료되면 메모리에서사라짐. 클라이언트에 남음

			req.setAttribute("error", "로그인 실패 다시 시도하세요");
			req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
		}
				
	}
}
