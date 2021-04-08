package utilityProgarm;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  

public class PatternRegex {

	public PatternRegex() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main (String[] args) throws UnsupportedEncodingException {
		
		
		
		String inputTest = "ใน นิยาย ที่ ผม เป็น คน เขียน LSSPACELS ไม่มี ใคร ต้องตา ย";
		byte[] byteArrray = inputTest.getBytes();
		String inPutUTF8 = new String(byteArrray, StandardCharsets.UTF_8.name());
	
//		        // TODO Auto-generated method stub  
//		        //pattern/expression to be match  
//		        Pattern  p=Pattern.compile(" LSSPACELS ");   
//		        //Regular expression  
//		        Matcher m=p.matcher(inputTest);  
//		     
//		        if(m.find())  
//		        System.out.println(" Result :  "+m);  
//		        
//		   // String output =    inputTest.replaceAll(p, inputTest);
		
		String output =  inPutUTF8.replace(" ", "");
		System.out.println(output);
		    
		 
	}

}
