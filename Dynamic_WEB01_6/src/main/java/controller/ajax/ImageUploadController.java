package controller.ajax;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@WebServlet("/ajax/imageUpload")
@MultipartConfig
public class ImageUploadController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext sc = req.getServletContext();
		
		Part image = req.getPart("imageUpload");
		String location = req.getParameter("location");
		
		int maxFileSize = Integer.parseInt(sc.getInitParameter("maxFileSize"));
		String permitFileType = sc.getInitParameter("permitFileType");
		String[] permitFileExt = sc.getInitParameter("permitFileExt").split(",");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		String path = "static/img/" + location + "/" + df.format(new Date());
		String realPath = sc.getRealPath(path);
		File f = new File(realPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		if(image.getSize() <= maxFileSize) {
			if(image.getContentType().startsWith(permitFileType)) {
				if(image.getContentType().endsWith(permitFileExt[0]) ||
						image.getContentType().endsWith(permitFileExt[1])) {
					String uuid = UUID.randomUUID().toString();
					System.out.println("image.write에 쓸거: "+String.join("/", realPath, uuid));
					image.write(String.join("/", realPath, uuid));//서버에 저장(서버경로를 넣어야함)
					//이미지를 /static/img/board/년-월-일/uuid.확장자 형식으로 저장
					//저장경로를 json 형식으로 반환하게 합니다. 
					
					//아래는 웹에서사용하는 이미지 URL주소(실제경로가아니라 웹URL주소임)
					resp.getWriter().print("{\"imageUrl\": \"" + String.join("/", req.getContextPath(), path, uuid) + "\"}");
					//이미지업로드 요청이오면 서버에서 저장하고 저장위치(경로를) json형식으로 알려주면
					//토스트에디터가 경로를 에디터에다가 작성해줌.
					resp.getWriter().flush(); //d12
					
				}
			}
		}
		
	}
}
