package utilityProgarm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CallWebServiceByGetParam {

	public CallWebServiceByGetParam() {
		// TODO Auto-generated constructor stub
	}
	
//	public static void main(String[] args) {
//		
//		String idStr = getRandom();
//		String inputStr = "I declare resumed the session of the European Parliament adjourned on Friday 17 December 1999 , and I would like once again to wish you a happy new year in the hope that you enjoyed a pleasant festive period .";
//		String languageStr = "EN";
//		String chunkStr = "01";
//		String realtimeStr = "true";
//		String accountStr = "test";
//		
//		
//		String result = Detokenize(idStr, inputStr, languageStr, chunkStr, realtimeStr, accountStr);
//	}



	public String Detokenize(String idStr, String inputStr, String languageStr, String chunkStr,
			String realtimeStr, String accountStr) {
		
//		String detokServiceURL = "http://172.17.105.217:7030/detokenize/detokenize.jsp";
		String detokServiceURL = "http://localhost:8080/DetokenizeService_Java8/detokenize.jsp";
		
		//System.out.println("Before encoding: " + inputStr);
		String input = null;
		try {
			input = URLEncoder.encode(inputStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("After encoding: " + input);
		
		
		String MARKER = "\n";
		String result_value = "result";
		
		try {
			String encryptNewLine = URLEncoder.encode(MARKER, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String detokenizeParam = "id="+idStr+"&input="+input+"&language="+languageStr+"&chunk="+chunkStr+"&accountno="+accountStr;
		
		//System.out.println(detokenizeParam);
		String detokJSON = null;
		try {
			detokJSON = httpPost(detokServiceURL, detokenizeParam, 300000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("detokJSON: "+detokJSON);
		
		String output = null;
		try {
			output = getJsonResult(detokJSON, result_value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Output: "+output);
		

		return output;
	}

	private static String getJsonResult(String sJSon, String value) throws Exception {

		String sValue = "";

		try {
			JSONParser parser = new JSONParser();
			JSONObject jObject = (JSONObject) parser.parse(sJSon);
			sValue = (String) jObject.get(value);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sValue;
	}

	private static String httpPost(String webUrl, String parameters, int iTimeOut) throws Exception {

		StringBuilder output = new StringBuilder();
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		InputStreamReader isReader = null;

		try {
			// 1 hours
			if (iTimeOut == 0) {
				iTimeOut = 3600000;
			}

			// Send data
			URL url = new URL(webUrl);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(iTimeOut);

			conn.setDoOutput(true);
			writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(parameters);
			writer.flush();

			// Get the response
			isReader = new InputStreamReader(conn.getInputStream(), "UTF-8");
			reader = new BufferedReader(isReader);

			String line;

			while ((line = reader.readLine()) != null) {
				output.append(line);
			}

			if (reader != null) {
				reader.close();
			}
			if (isReader != null) {
				isReader.close();
			}
			if (writer != null) {
				writer.close();
			}

		} catch (Exception e) {
			throw e;
		} finally {

			if (reader != null) {
				reader.close();
			}
			if (isReader != null) {
				isReader.close();
			}
			if (writer != null) {
				writer.close();
			}

		}

		return output.toString();
	}

	private static String getRandom() {
		
		  UUID uid = new UUID(1, 1);
		  
		  String uidStr = uid.randomUUID().toString();
		  String[] uidArr = uidStr.split("-");
		  
		  int langthOfuidArr = uidArr.length;
		  
		  String result = uidArr[langthOfuidArr-1];

		return result;
	}



}
