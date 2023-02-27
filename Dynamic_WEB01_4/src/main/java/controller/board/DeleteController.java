package controller.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.BoardDTO;
import model.dto.BoardImageDTO;
import model.dto.Role;
import model.dto.UserDTO;
import model.service.BoardService;

// @WebServlet("/board/delete")
public class DeleteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext sc = req.getServletContext();
		HttpSession session = req.getSession();
		UserDTO user = (UserDTO)session.getAttribute("user");
		
		String id = req.getParameter("id");
		
		BoardDTO dto = new BoardDTO();
		dto.setId(Integer.parseInt(id));
		
		BoardService service = new BoardService();
		BoardDTO data = service.getData(dto);
		
		List<BoardImageDTO> imageList = service.getImages(dto);
		
		if(((Role)session.getAttribute("role")).isAdmin()) {
			boolean result = service.delete(data);//게시글과 이미지 둘다지운다.
			
			for(BoardImageDTO image: imageList) {
				String realPath = sc.getRealPath(image.getPath());
				File f = new File(realPath + image.getUuid());
				f.delete();//리얼패스를 찾아서 해당 파일을 지우고
			}
			
			resp.sendRedirect(req.getContextPath() + "/board");
		} else {
			if(data.getWriter().equals(user.getUserId())) {
				boolean result = service.delete(data); //게시글과 이미지 둘다지운다.
				for(BoardImageDTO image: imageList) {
					String realPath = sc.getRealPath(image.getPath());
					File f = new File(realPath + image.getUuid());
					f.delete();
				}
				
				if(result) {
					resp.sendRedirect(req.getContextPath() + "/board");
				} else {
					resp.sendRedirect(req.getContextPath() + "/board/detail?id=" + data.getId());
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/error");
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String[] ids = req.getParameterValues("chk_id");
		
		if(((Role)session.getAttribute("role")).isAdmin()) {
			BoardService service = new BoardService();
			
//			BoardDTO dto = new BoardDTO();
//			for(String id:ids) {
//				dto.setId(Integer.parseInt(id));
//				service.delete(dto);
//			}//이렇게 해도 됨 그런데 이렇게하면 service.delete에서 dao객체가 생성되고 commit close가 반복되면서
			//dao객체는 session을 반복적으로 생성되었다가 소멸되면서 시간을 잡아먹음.
			//오라클에 질의하고질의받고 하는게 반복되면서 시간이 걸림 그래서 한번생성하고 한번 요청해서 끝낼것이다.
			
			List<Integer> arrId = new ArrayList<>();
			for(int i = 0; i < ids.length; i++) {
				arrId.add(Integer.parseInt(ids[i]));
			}
			service.delete(arrId);
		}
		
		resp.sendRedirect(req.getContextPath() + "/board");
	}
}
