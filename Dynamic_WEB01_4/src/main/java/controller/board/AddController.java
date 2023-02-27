package controller.board;

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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.dto.BoardDTO;
import model.dto.BoardImageDTO;
import model.dto.UserDTO;
import model.service.BoardService;

// @WebServlet("/board/add")
public class AddController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/board/add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ServletContext sc = req.getServletContext();
		
		String title = req.getParameter("title");
		String context = req.getParameter("context");
		
		BoardDTO dto = new BoardDTO();
		dto.setTitle(title);
		dto.setContext(context);
		dto.setWriter(((UserDTO)session.getAttribute("user")).getUserId());
		dto.setBtype("B");
		if(req.getParameter("notice") != null) {//공지사항체크가 되어있다면
			if(req.getParameter("notice").equals("yes")) {//공지사항체크가 되어있다면
				dto.setBtype("N");
			}
		}
		
		int maxFileSize = Integer.parseInt(sc.getInitParameter("maxFileSize"));
		String permitFileType = sc.getInitParameter("permitFileType");
		String[] permitFileExt = sc.getInitParameter("permitFileExt").split(",");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		String path = "/static/img/board/" + df.format(new Date()) + "/";
		// /static/img/board/2023-02-06/
		System.out.println("path : "+ path);
		
		String realPath = sc.getRealPath(path);
		//C:\Users\1215k\eclipse-workspace3\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Dynamic_WEB01_4\static\img\board\2023-02-08\
		System.out.println("realPath : "+ realPath);
		File f = new File(realPath);
		if(!f.exists()) {//realPath 폴더가 존재하지않으면
			f.mkdirs();// 폴더를 만들어줌 중간에 빠진폴더가 없으면 다 만들어줌.
		}
		
		Collection<Part> parts = req.getParts();//하나의 input에 multiple로 여러개 올릴때
		for(Part part: parts) {
			System.out.println(part.getName());
			//req.getParts()안에는 form에 쌓여 전달된 input들의 name이 들어있다.
		}
		List<BoardImageDTO> boardImageList = new ArrayList<BoardImageDTO>();
		for(Part part: parts) {
			if(part.getName().equals("imageUpload")) {
				if(part.getSize() <= maxFileSize) {
					if(part.getContentType().startsWith(permitFileType)) {
						if(part.getContentType().endsWith(permitFileExt[0]) ||
								part.getContentType().endsWith(permitFileExt[1])) {
							String uuid = UUID.randomUUID().toString();//랜덤 고유식별자
							System.out.println("uuid : "+ uuid);
							part.write(String.join("/", realPath, uuid));
							// realpath로 파일명과 함께 서버로  전송 저장
							//실제 저장할떄는 절대경로가 필요해서 realPath를구하는건데 데베에 저장할때는 path만저장하면
							//불러올떄 realPath에서 찾아서 이미지 가져옴
							BoardImageDTO boardImageDTO = new BoardImageDTO();
							boardImageDTO.setPath(path);//업로드한 이미지의 경로 /static/img/board/2023-02-06/
							boardImageDTO.setName(part.getSubmittedFileName());// 파일명.확장자
							boardImageDTO.setUuid(uuid);
							boardImageList.add(boardImageDTO);
						}
					}
				}
			}
		}
		
		BoardService service = new BoardService();
		boolean result = service.add(dto, boardImageList);
		if(result) {
			resp.sendRedirect(req.getContextPath() + "/board/detail?id=" + dto.getId());
		} else {
			req.getRequestDispatcher("/WEB-INF/view/board/add.jsp").forward(req, resp);
		}
		
	}
}
