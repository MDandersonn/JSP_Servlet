package model.controller;

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
import model.dto.MemberDTO;
import model.service.MemberService;
@WebServlet("/ajax/userIdCheck")
public class userIdCheckController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id= req.getParameter("id");
		System.out.println("id:"+id);
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		MemberService service = new MemberService();
		MemberDTO data=service.selectById(dto);
	
		JsonFactory jf = new JsonFactory();
		StringWriter sw = new StringWriter();
		JsonGenerator jg = jf.createGenerator(sw);
		
		jg.writeStartObject();// {역할
		if(data == null) {//중복된 아이디가 존재하지않을때 가입할수 있다.
			jg.writeStringField("msg", "usable");
		} else {//중복된 아이디가 존재할때 가입할수없다
			jg.writeStringField("msg", "no");
		}
		jg.writeEndObject();// }역할
		jg.close();
		
		PrintWriter out = resp.getWriter();
		out.print(sw.toString());//응답메시지로 전송하겠다는것
		out.flush();
	}

}
