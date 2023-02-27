package controller.ajax;

import java.io.IOException;
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

@WebServlet("/ajax/recommend")
public class RecommendController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		필터에의해 바로 로그인체크필터를 거친다. 아래과정은 로그인되어있을때만 작동
		
		System.out.println("추천컨트롤 doget작동합니다.");
		HttpSession session = req.getSession();
		List<Integer> recHistory = (List<Integer>)session.getAttribute("boardRecCntHistory");//추천수 리스트
		List<Integer> nRecHistory = (List<Integer>)session.getAttribute("boardNRecCntHistory");//비추천 수리스트
		
		Integer id = Integer.parseInt(req.getParameter("id"));
		String type = req.getParameter("type");
		////  data: { id: id , type: type} 에서 받아온거
		
		
		BoardDTO dto = new BoardDTO();
		dto.setId(id.intValue());
		
		BoardService service = new BoardService();
		
		JsonFactory jf = new JsonFactory();
		StringWriter sw = new StringWriter();
		JsonGenerator jg = jf.createGenerator(sw);
		
		jg.writeStartObject();
		if(type.equals("rec")) {//추천누른경우
			if(!recHistory.contains(id) && !nRecHistory.contains(id)) {
				service.incRecCnt(dto);
				recHistory.add(id);
			} else if(recHistory.contains(id)) {
				service.decreRecCnt(dto);
				recHistory.remove(id);
			}
			session.setAttribute("boardRecCntHistory", recHistory);
			BoardDTO data = service.getData(dto);
			jg.writeStringField("type", "success");
			jg.writeNumberField("count", data.getRecCnt());//추천수 올린 값을 전달하기위함
			
		} else if(type.equals("norec")) {//비추천누른경우
			if(!recHistory.contains(id) && !nRecHistory.contains(id)) {
				service.incNRecCnt(dto);
				nRecHistory.add(id);
			} else if(nRecHistory.contains(id)) {
				service.decreNRecCnt(dto);
				nRecHistory.remove(id);
			}
			session.setAttribute("boardNRecCntHistory", nRecHistory);
			BoardDTO data = service.getData(dto);
			jg.writeStringField("type", "success");
			jg.writeNumberField("count", data.getnRecCnt());//추천수 내린 값을 전달하기위함
		} else {
			jg.writeStringField("type", "error");
			jg.writeStringField("msg", "잘못된 타입 입니다.");
		}
		jg.writeEndObject();
		jg.close();
		
		resp.getWriter().print(sw.toString());
		resp.getWriter().flush();
	}
}
