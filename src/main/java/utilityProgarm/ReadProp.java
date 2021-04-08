package utilityProgarm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProp {

	String result = "";
	InputStream inputStream;
	
	public ReadProp() {
		// TODO Auto-generated constructor stub
	}
	
	public String getProp() throws IOException {
		
		try {
		Properties prop = new Properties();
		String propFileName = "config.properties";

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}

		String company1 = prop.getProperty("company1");
		String company2 = prop.getProperty("company2");
		
		if(company2 == null) {
			System.out.println("Company 2  as null");
		}
		
		System.out.println("company1: "+company1);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		

		
		
		return null;
		
	}

}
