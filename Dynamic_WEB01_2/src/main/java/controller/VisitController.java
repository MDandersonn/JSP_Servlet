package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.VisitDTO;
import model.service.VisitService;
import paging.Paging;

/**
 * 방명록을 작성할 수 있는 기능을 제공하기 위한 Servlet
 * 맵핑된 URL 주소는 /visit 이다.
 */
public class VisitController extends HttpServlet {
	/**
	 * 사용자가 방명록 페이지를 요청하면 방명록을 작성할 수 있는
	 * 폼을 담은 JSP 페이지를 실행하여 제공한다.
	 * JSP 페이지는 /WEB-INF/view/visit.jsp 이다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String p= req.getParameter("p");
		if(p==null) {
			p= "1";
		}else {
////			visit?p=하고 값이없을경우 p="" 가 되는듯 널이 아니라.
			System.out.println("##p:"+p);
			if(p.isEmpty()) {
				p="1";
			}
		}
		//쿠키찾기
		Cookie cookie =null;
		Cookie[] cookies =req.getCookies();
		for(Cookie c: cookies) {
			if(c.getName().equals("cnt")) {//cnt라는 쿠키가있으면
				cookie=c;//cookie에 저장
			}
		}
		//한페이지 목록수(c)가 쿠키와 파라미터로 전달되는데 여기서, 
		//쿠키없고 파라미터없으면(처음접속) 기본 c=10개
		//쿠키없고 파라미터있으면(처음접속하여 select를 변경했을때) 파라미터를 쓸것,쿠키도 재설정(파라미터를 쿠키값으로 재설정)하고
		//쿠키있고 파라미터없으면(위에서 쿠키를 만들어논후 나중에 다시 게시판 접속했을때) 쿠키값사용
		//쿠키있는데 파라미터도있으면 파라미터를 쓸것,쿠키도 재설정(파라미터를 쿠키값으로 재설정) (다시 게시판접속해서 select를 변경했을떄)
		
		String c= req.getParameter("c");
		int cnt = 10;//Integer.parseInt(c);
		if(cookie !=null) {
			if(req.getParameter("c") !=null) {
				if(!req.getParameter("c").isEmpty()) {
					cnt=Integer.parseInt(req.getParameter("c"));
					cookie= new Cookie("cnt", String.valueOf(cnt));
					cookie.setMaxAge(60*60*24*5);
					resp.addCookie(cookie);
				}
			}else {
				cnt=Integer.parseInt(cookie.getValue());
			}
		}else {
			if(req.getParameter("c") !=null) {
				if(!req.getParameter("c").isEmpty()) {
					cnt=Integer.parseInt(req.getParameter("c"));
					cookie= new Cookie("cnt", String.valueOf(cnt));
					cookie.setMaxAge(60*60*24*5);
					resp.addCookie(cookie);
				}
			}
		}
		//리스너테스트
		System.out.println(req.getServletContext().getAttribute("hello"));
		VisitService service = new VisitService();
		Paging data= service.getPage(Integer.parseInt(p), cnt);
		//cnt를 paging데이터에 pageLimit으로입력     p를 currentPageNumber로 입력
		
		//리스너테스트
		req.setAttribute("data", "Hello"); //추가add
		
//		jsp에다가 데이터 넘길려고 visitdto조회해서 set설정한거 (New Data로 출력됨)
//		req.setAttribute("data", data.getData()); //수정
//		req.setAttribute("lastPageNumber", data.getLastPageNumber());
//		req.setAttribute("pageList", data.getPageList());
//		req.setAttribute("cnt", cnt);
//		data안에 있는정보들이라서 이거위에 다 필요없음
		
		req.setAttribute("paging", data);
		req.getRequestDispatcher("/WEB-INF/view/visit.jsp").forward(req, resp);
		//리스너테스트
		req.removeAttribute("data"); //삭제
	}
	
	/**
	 * 사용자가 방명록을 작성 후 저장 요청을 하면 해당 데이터를
	 * 저장하기 위한 처리를 수행한다.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String context = req.getParameter("context");
		String nickname = req.getParameter("nickname");
		
		VisitDTO dto = new VisitDTO();
		dto.setContext(context);
		dto.setNickname(nickname);
		
		VisitService service = new VisitService();
		boolean result = service.add(dto);
		
		if(result) {
			resp.sendRedirect("./visit");
		} else {
			System.out.println("추가 안됨");
		}
	}
}
