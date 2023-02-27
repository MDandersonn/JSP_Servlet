import java.util.ArrayList;
import java.util.List;

public class test {

	public static void main(String[] args) {
//		if(null== "gg"){
//			
//		}
		List<String> list = new ArrayList();
		list.add("gg");
		list.add("aa");
		
		System.out.println(list.contains("gga"));
		
		String re=String.join("/", "aa", "bb");
		System.out.println(re);
		

	}

}
