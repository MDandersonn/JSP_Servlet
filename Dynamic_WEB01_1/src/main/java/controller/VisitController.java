package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.VisitDTO;
import model.service.VisitService;

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
		//p는 현재 페이지넘버
		String p= req.getParameter("p");//p의 id값에 아무것도 안넣어주니가 null에러
		
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
		//한페이지 목록수가 쿠키와 파라미터로 전달. 
		//쿠키없고 파라미터없으면 기본 10개
		//쿠키없고 파라미터있으면  파라미터를 쓸것,쿠키도 재설정하고
		//쿠키있고 파라미터없으면 쿠키값사용
		//쿠키있는데 파라미터도있으면 파라미터를 쓸것,쿠키도 재설정하고
		
		//c는 jsp파일에서 submit하여서 보낸 select의 value값. (한페이지의 목록수)
		String c= req.getParameter("c");
		
		//쿠키가있다는 말은 이전에 설정해놓은 값이 있다는얘기고
		//쿠키가 없다는말은 처음 접속하는경우.
		// request에 c가 있다는말은   select에서 submit으로값을 바꾼요청이 있었느냐 라는의미
		//cnt는 한페이지 목록수(c에서 유래 기본값은10)
		int cnt = 10;//Integer.parseInt(c);
		if(cookie !=null) {//쿠키가 있을때 
			if(req.getParameter("c") !=null) {//쿠키가 있는상황에서 onchange로 submit되어 요청이 들어왔을때
				if(!req.getParameter("c").isEmpty()) {
					cnt=Integer.parseInt(req.getParameter("c"));
					cookie= new Cookie("cnt", String.valueOf(cnt));
					cookie.setMaxAge(60*60*24*5);
					resp.addCookie(cookie);
				}
			}else {//쿠키가있는상황인데 c가 null( submit요청은 없는경우)  
				//딴데 갔다가 다시 방명록을 방문했을때
				//있는 쿠키에서 c값을뽑아내서 cnt로 사용한다.
				cnt=Integer.parseInt(cookie.getValue());
			}
		}else {//쿠키가 없을때
			if(req.getParameter("c") !=null) {//쿠키가없는데 c가 있을떄.
				//(처음으로 jsp에서 onchange에 의해 submit되어서 요청이 들어왔을때 처음으로 쿠키를만듦 
				
				if(!req.getParameter("c").isEmpty()) {
					cnt=Integer.parseInt(req.getParameter("c"));
					cookie= new Cookie("cnt", String.valueOf(cnt));
					cookie.setMaxAge(60*60*24*5);
					resp.addCookie(cookie);
				}
			}//쿠키가없고, c가 null일때, ( 처음접속했을경우
			
		}
		//리스너테스트
		System.out.println(req.getServletContext().getAttribute("context객체"));
		VisitService service = new VisitService();
//		List<VisitDTO> data = service.getAll();//page로 전환되면서 필요없어짐
		List<VisitDTO> data = service.getPage(Integer.parseInt(p),cnt);
		int totalRow= service.totalRow();//전체목록수
		int lastPageNumber= (totalRow/cnt)+(totalRow%cnt == 0 ? 0 : 1);//마지막페이지넘버
		
		//리스너테스트
		req.setAttribute("data", "Hello"); //추가add

		List<Integer> pageList= new ArrayList<Integer>();
		for(int i=1 ; i<=lastPageNumber; i++) {
			pageList.add(i);
		}
		
//		jsp에다가 데이터 넘길려고 visitdto조회해서 set설정한거 (New Data로 출력됨)
		req.setAttribute("data", data); //리스너테스트 수정
		req.setAttribute("lastPageNumber", lastPageNumber);
		req.setAttribute("pageList", pageList);
		req.setAttribute("cnt", cnt);
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
