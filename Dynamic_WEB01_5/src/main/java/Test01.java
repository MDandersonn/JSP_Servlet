import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

class User{
	private String name;
	private int age;
	private String password;
	private List<Integer> arr;
	
	public List<Integer> getArr() {
		return arr;
	}
	public void setArr(List<Integer> arr) {
		this.arr = arr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

public class Test01 {

	public static void main(String[] args) throws IOException {
		JsonFactory jf = new JsonFactory();
		StringWriter sw = new StringWriter(); //StringWriter: 입출력객체
		JsonGenerator jg = jf.createGenerator(sw);
		
		jg.writeStartObject();
		jg.writeFieldName("id");
		jg.writeString("Json");
		
		jg.writeFieldName("age");
		jg.writeNumber(30);
		
		jg.writeBooleanField("type", false);
		
		jg.writeStringField("id2", "Json2");
		
		int[] iArr= {1,2,3,4,5};
		jg.writeFieldName("arr");
		jg.writeArray(iArr,0,iArr.length);
		
		jg.writeEndObject();
		jg.close();
		System.out.println( sw.toString());
		
		
		
		/*
		 * 데이터담는 객체가있으면 그 객체값가지고 json형식만들거면 ObjectMapper쓰면됨
		 * ObjectMapper로 dto를 JSON형식으로 만들어줄수있음
		 */
		StringWriter sw1= new StringWriter();
		ObjectMapper om= new ObjectMapper();
		om.setSerializationInclusion(Include.NON_NULL);//제이슨에서 널값 제외
		//숫자도 0인거제외하고싶으면 DTO에서 int말고 Integer로 해서 위에 널값제외 적용시키면 된다.		
		User user = new User();
		
		user.setName("Jackson");
//		user.setAge(30);
//		user.setPassword("pass");
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(1);arr.add(3);arr.add(5);
		user.setArr(arr);
		om.writeValue(sw1, user);
		System.out.println(sw1.toString());
		
		
		
		

	}

}
