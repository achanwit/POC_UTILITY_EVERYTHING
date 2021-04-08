package utilityProgarm;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;



public class CreateMultipleFile {

	public CreateMultipleFile() {
		
		
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		String inputFilePath = "/home/chanwit/Documents/LSTokenize/D.Test/InputTestTokenizeFilder/Europarl_v1.en.txt";
		int fileCount = 1000;
		
		//read file
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
		
		String input = new String(inputBuf);
		
		for(int i=0; i<fileCount; i++) {
			String output = "/home/chanwit/Documents/LSTokenize/D.Test/InputTestTokenizeFilder/Europarl_v"+i+"_.txt";
			writeFile(input,output);
		}
		
		
		
	}
	
	/*** Common method for create new file ***/
	private static void writeFile(String result, String outFilePath) {
		File file = new File(outFilePath);	
		try {
			if(file.createNewFile()) {
				writeLineinFile(result, outFilePath, file);
			}else {
				writeLineinFile(result, outFilePath, file);
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/*** Common method for write file ***/
	private static void writeLineinFile(String result, String outFilePath, File file) {
		
		FileWriter writer;
		try {
			writer = new FileWriter(file, true);
			writer.write(result+"\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
