package model.controller.board;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.dto.BoardDTO;
import model.dto.BoardImageDTO;
import model.dto.MemberDTO;
import model.service.BoardService;
@WebServlet("/board/insert")
@MultipartConfig
public class BoardInsertController extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			HttpSession session =req.getSession();
			if(session.getAttribute("isLogin") ==null) {
				resp.sendRedirect(req.getContextPath()+"/login");
				return;
			}
			req.getRequestDispatcher("/view/boardInsert.jsp").forward(req, resp);
		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String title= req.getParameter("title");
			String content= req.getParameter("content");
			BoardDTO dto = new BoardDTO();
			dto.setTitle(title);
			dto.setContent(content);
			Part imageFile = req.getPart("img");
			System.out.println("imageFile:"+imageFile);
			String path = req.getServletContext().getRealPath("/static/img/profile");
			System.out.println("path:" +path);
			
			
			System.out.println(" imageFile.getSubmittedFileName():"+ imageFile.getSubmittedFileName());
			imageFile.write(path + "/" + imageFile.getSubmittedFileName());
			dto.setImageFileName("/static/img/profile/" + imageFile.getSubmittedFileName());
			
			BoardService service = new BoardService();
			int result=service.getSequence();
			dto.setArticleNO(result);
			HttpSession session =req.getSession();
			MemberDTO member=(MemberDTO)session.getAttribute("login");
			dto.setId(member.getId());
			
			service.insertBoard(dto);
			
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String path2 = "/static/img/board/" + df.format(new Date()) + "/";
			///static/img/board/2022-10-06/
			String realPath = req.getServletContext().getRealPath(path2);
			//실제경로 받음
			File f = new File(realPath);
			if(!f.exists()) {
				f.mkdirs();
			}//없으면 폴더생성
			List<BoardImageDTO> boardImageList = new ArrayList<BoardImageDTO>();
			
			Collection<Part> parts = req.getParts();
			for(Part part: parts) {
				if(part.getName().equals("imgMulti")) {
					String uuid = UUID.randomUUID().toString();
					System.out.println(realPath);
					///static/img/board/2022-10-06/asdio-dfdsfs-dfdf
					part.write(String.join("/", realPath, uuid));
					BoardImageDTO boardImageDTO = new BoardImageDTO();
					boardImageDTO.setPath(path2);
					boardImageDTO.setName(part.getSubmittedFileName());
					boardImageDTO.setUuid(uuid);
					boardImageList.add(boardImageDTO);
					service.insertImage(result,boardImageList);
					
				}
			
			}
			
			resp.sendRedirect(req.getContextPath()+"/board");
		}

}
