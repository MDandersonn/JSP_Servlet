package controller;

import java.io.IOException;

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
//		location = "D:/imageTemp",
//		maxFileSize = 1024 * 1024 * 5,
//		maxRequestSize = 1024 * 1024 * 5,
//		fileSizeThreshold = 1024
//)
public class ImageUploadController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewPath = "";
		
		HttpSession session = req.getSession();
		UserDTO user = (UserDTO)session.getAttribute("user");
		
		Part imageFile = req.getPart("imageFile");
		int maxFileSize = Integer.parseInt(req.getServletContext().getInitParameter("maxFileSize"));
		String permitFileType = req.getServletContext().getInitParameter("permitFileType");
		String[] permitFileExt = req.getServletContext().getInitParameter("permitFileExt").split(",");
		
		if(imageFile.getSize() <= maxFileSize) {
			if(imageFile.getContentType().startsWith(permitFileType)) {
				if(imageFile.getContentType().endsWith(permitFileExt[0]) ||
						imageFile.getContentType().endsWith(permitFileExt[1])) {
					System.out.println("파일명: " + imageFile.getSubmittedFileName());
					System.out.println("파일크기: " + imageFile.getSize());
					System.out.println("파일종류: " + imageFile.getContentType());
				
					String path = req.getServletContext().getRealPath("/static/img/profile");
					imageFile.write(path + "/" + imageFile.getSubmittedFileName());
					
					user.setpImg("/static/img/profile/" + imageFile.getSubmittedFileName());
					UserService service = new UserService();
					service.uploadImage(user);
					
					// resp.sendRedirect(req.getContextPath() + "/myinfo");
					resp.sendRedirect(req.getContextPath() + "/logout");
					return;
				} else {
					req.setAttribute("errorType", "fileExtError");
					viewPath = "/WEB-INF/view/error/imageUploadError.jsp";
				}
			} else {
				req.setAttribute("errorType", "fileTypeError");
				viewPath = "/WEB-INF/view/error/imageUploadError.jsp";
			}
		} else {
			req.setAttribute("errorType", "fileSizeError");
			viewPath = "/WEB-INF/view/error/imageUploadError.jsp";
		}
		req.getRequestDispatcher(viewPath).forward(req, resp);
	}
}
