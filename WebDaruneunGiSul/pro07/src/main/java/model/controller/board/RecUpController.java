package model.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.BoardDTO;
import model.service.BoardService;
@WebServlet("/ajax/recup")
public class RecUpController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("추천컨트롤 dopost작동합니다.");
		String articleNO = req.getParameter("articleNO");
		
		HttpSession session = req.getSession();
		List<String> listUP = (List<String>)session.getAttribute("recUP");
		List<String> listDOWN = (List<String>)session.getAttribute("recDOWN");
			JsonFactory jf = new JsonFactory();
			StringWriter sw = new StringWriter();
			JsonGenerator jg = jf.createGenerator(sw);
			BoardService service = new BoardService();
			BoardDTO dto = new BoardDTO();
			dto.setArticleNO(Integer.parseInt(articleNO));
			System.out.println(articleNO);
			jg.writeStartObject();// {역할
			if(!listUP.contains(articleNO)) {//up누른적없다면
				if(!listDOWN.contains(articleNO)){////up누른적없다면,down누른적없다면 ->up만올리기
					service.recUP(dto);
					listUP.add(articleNO);
				}else {///up누른적없다면,down누른적 있다 ->down취소 up올리기
					service.recUP(dto);
					listUP.add(articleNO);
					service.removeRecDOWN(dto);
					listDOWN.remove(articleNO);
				}
			} else {//up누른적있다면
				if(!listDOWN.contains(articleNO)){////up누른적있고,down누른적없다면 ->up취소
					service.removeRecUP(dto);
					listUP.remove(articleNO);
				}
			}
			BoardDTO data= service.selectByArticleNO(dto);
			session.setAttribute("recUP",listUP);
			session.setAttribute("recDOWN",listDOWN);
			System.out.println( "getup" +data.getRecUP());
			jg.writeNumberField("dataUP", data.getRecUP());
			jg.writeNumberField("dataDOWN", data.getRecDOWN());
			
			jg.writeEndObject();// }역할
			jg.close();
			
			PrintWriter out = resp.getWriter();
			out.print(sw.toString());//응답메시지로 전송하겠다는것
			out.flush();
			
			
		}
		
		
		
		
		
	

}
