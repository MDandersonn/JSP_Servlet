package ex2;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/notice")
public class NoticeListServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ArrayList<Notice> list= new NoticeService().selectList();
		String list=null;
		String page =null;
		if(list != null) {//공지가 있으면
			page="view/noticeList.jsp";
			request.setAttribute("list", list);
			
		}else {//공지가 없으면
			page="view/errorPage.jsp";
			request.setAttribute("msg","공지사항 조회에 실패하였습니다.");
			
		}
		request.getRequestDispatcher(page).forward(request, response);
		
	}
	

}
