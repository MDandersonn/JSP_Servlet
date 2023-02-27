package controller.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.UserDTO;
import model.service.UserService;

@WebServlet("/ajax/userIdCheck")
public class UserIdCheckController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		//  data: { userId: element.value} 에서의 userId임 
		System.out.println(userId);
		UserDTO dto = new UserDTO();
		dto.setUserId(userId);
		
		UserService service = new UserService();
		UserDTO data = service.getData(dto);//해당 아이디 유저가있는지 검사하여 가져옴
		
		JsonFactory jf = new JsonFactory();
		StringWriter sw = new StringWriter();
		JsonGenerator jg = jf.createGenerator(sw);
		
		jg.writeStartObject();// {역할
		if(data == null) {//중복된 아이디가 존재하지않을때 가입할수 있다.
			jg.writeStringField("msg", "OK");
		} else {//중복된 아이디가 존재할때 가입할수없다
			jg.writeStringField("msg", "Fail");
		}
		jg.writeEndObject();// }역할
		jg.close();
		
		PrintWriter out = resp.getWriter();
		out.print(sw.toString());//응답메시지로 전송하겠다는것
		out.flush();
	}
}
