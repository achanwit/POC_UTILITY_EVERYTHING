package utilityProgarm;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFileToString {
	
	public ReadFileToString() {
		
	}

	public String getResultStringFromFile(String inputFilePath) throws FileNotFoundException {
		// TODO Auto-generated constructor stub
		
		//String inputFilePath = "/home/chanwit/Documents/LSTokenize/D.Test/InputTestTokenizeFilder/Europarl_v1_.txt";
		
		FileInputStream inputString = new FileInputStream(inputFilePath);
	
		DataInputStream in = new DataInputStream(inputString);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(in));
		StringBuffer inputBuf = new StringBuffer();
		
		long BeginReadFile = System.currentTimeMillis();
		try {
			String line = null;
			while ((line = br1.readLine()) != null){
				inputBuf = inputBuf.append(line).append("\n");
			}
		}catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (br1 != null)
					br1.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		long EndReadFile = System.currentTimeMillis();
		long TotalReadFile = EndReadFile - BeginReadFile;
		System.out.println("TotalReadFile: "+TotalReadFile);
		
		return new String(inputBuf);
	}

}
