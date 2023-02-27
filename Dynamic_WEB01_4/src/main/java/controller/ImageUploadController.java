package controller;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.dto.UserDTO;
import model.service.UserService;

//@WebServlet("/myinfo/uploadImage")
//@MultipartConfig(
//		location = "C:/imageTemp",
//		maxFileSize = 1024 * 1024 * 5,
//		maxRequestSize = 1024 * 1024 * 5,
//		fileSizeThreshold = 1024
//)
public class ImageUploadController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//사용자가 이미지가 업로드가되었으면 서버에 저장이되고  업로드가된위치를 PIMG컬럼에다가 업데이트시킴
		
		String viewPath = "";
		
		HttpSession session = req.getSession();
		UserDTO user = (UserDTO)session.getAttribute("user");
		
		Part imageFile = req.getPart("imageFile");//파일일떄 req.getParameter의 역할
		System.out.println(imageFile.getSubmittedFileName());//업로드한 이미지파일이름출력
//		imageFile.write(imageFile.getSubmittedFileName());
		//로케이션위치에 저장(web.xml 의 <location>은 절대경로로..)하라는말
		/*나중에 서버에 배포작업을하거나 프로젝트경로가바뀔때마다 location의 경로도 같이 변경시켜줘야한다. 그 작업하는게 번거로움
		 * <location>의 경로는 어떠한프로젝트든지 상관없는 공통경로를 하나 적어놓고 거기에 임시로저장될수있게 만든다. 서버의 어느위치에 저장할것인지
		 * 정해놓으란 말.  
		 */
		
		/*
		 * web.xml에 맵핑해논거를 req.getServletContext().getInitParameter로 받아올수있따.
		 * <context-param>
			  	<param-name>maxFileSize</param-name>
			  	<param-value>5242880</param-value>
		</context-param>
		<context-param>
			  	<param-name>permitFileType</param-name>
			  	<param-value>image</param-value>
		</context-param>
		<context-param>
			  	<param-name>permitFileExt</param-name>
			  	<param-value>png,jpeg</param-value>
		</context-param>
		 */
		int maxFileSize = Integer.parseInt(req.getServletContext().getInitParameter("maxFileSize"));//파일사이즈
		String permitFileType = req.getServletContext().getInitParameter("permitFileType");//파일종류
		String[] permitFileExt = req.getServletContext().getInitParameter("permitFileExt").split(",");//확장자
		System.out.println("maxFileSize : "+maxFileSize);
		System.out.println("permitFileType : "+permitFileType);
		System.out.println("permitFileExt : "+Arrays.toString(permitFileExt));
		
		if(imageFile.getSize() <= maxFileSize) {
			if(imageFile.getContentType().startsWith(permitFileType)) {
				if(imageFile.getContentType().endsWith(permitFileExt[0]) ||
						imageFile.getContentType().endsWith(permitFileExt[1])) {
					System.out.println("파일명: " + imageFile.getSubmittedFileName());
					System.out.println("파일크기: " + imageFile.getSize());
					System.out.println("파일종류: " + imageFile.getContentType());
				//getRealPath: 실제 동작하고있는 프로젝트의 경로를 찾을수있다.
					String path = req.getServletContext().getRealPath("/static/img/profile");
					System.out.println("전송위치path:"+path); 
					/*
					 * C:\Users\1215k\eclipse-workspace3\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Dynamic_WEB01_4\static\img\profile
					 * 전송누르면 여기로 전송되어 저장된다.(실제 서버위치에 저장된다)
					 */
					imageFile.write(path + "/" + imageFile.getSubmittedFileName());
					/*전송누르면 path + "/" + imageFile.getSubmittedFileName() 파일명으로 전송되어 저장된다.(실제 서버위치에 저장된다)
					 * 이 경로를 지정안하면 location에 저장함.(아닌데?)
					 * 
					 * clean하면 배포했던거 다 지움 path경로에있는 새로생성된거 지워줌.
					 * 업로드한것들은 프로젝트에있는 이미지가 아니니까 배포할때 동봉되서 배포가안되니까 사라지는것.
					 * 프로젝트를 서버에 배포할때 .
					 */
					
					//서버에 배포 조건이 있을때,조건을 설정한후  조건과 맞지않을때 write하지않고 삭제함
					//imageFile.delete();
					user.setpImg("/static/img/profile/" + imageFile.getSubmittedFileName());
					UserService service = new UserService();
					service.uploadImage(user);
					
					resp.sendRedirect(req.getContextPath() + "/myinfo");
//					resp.sendRedirect(req.getContextPath() + "/logout");//로그아웃을 왜해
					return;
				} else {//파일포맷문제(확장자문제
					req.setAttribute("errorType", "fileExtError");
					viewPath = "/WEB-INF/view/error/imageUploadError.jsp";
				}
			} else {//파일타입문제 (종류문제
				req.setAttribute("errorType", "fileTypeError");
				viewPath = "/WEB-INF/view/error/imageUploadError.jsp";
			}
		} else {//용량크기초과
			req.setAttribute("errorType", "fileSizeError");
			viewPath = "/WEB-INF/view/error/imageUploadError.jsp";
		}
		req.getRequestDispatcher(viewPath).forward(req, resp);
	}
}
