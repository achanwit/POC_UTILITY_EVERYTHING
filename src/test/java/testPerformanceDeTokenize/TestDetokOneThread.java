package testPerformanceDeTokenize;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import utilityProgarm.CallWebServiceByGetParam;

public class TestDetokOneThread {
	public static CallWebServiceByGetParam detokService;

	public TestDetokOneThread() {
		
	}

	public static void main(String[] args) throws FileNotFoundException {
		detokService = new CallWebServiceByGetParam();
		// TODO Auto-generated method stub
		String inputFilePath = "/home/chanwit/Documents/LSDetokenize/A.Pre-Study/POC_Perfirmance_WebService/InputLarg/Europarl_Larg_Input.txt";
		//String outputFilePath = "/home/chanwit/Documents/LSTokenize/D.Test/TestInputLarg_Optimize/Output_Europarl_Larg_.txt";
		String pathUse = prepareOutput(inputFilePath);
		//Read File
		long BeginReadFile = System.currentTimeMillis();
		String inputStr = getResultStringFromFile(inputFilePath);
		long EndReadFile = System.currentTimeMillis();
		long TotalReadFile = EndReadFile - BeginReadFile;
		System.out.println("TotalReadFile: "+TotalReadFile);
		
		//Spli to Array
		long BeginSplitArr = System.currentTimeMillis();
		String[] inputArr = inputStr.split("\n");
		long EndSplitArr = System.currentTimeMillis();
		long TotalSplitArr = EndSplitArr - BeginSplitArr;
		System.out.println("TotalSplitArr: "+TotalSplitArr);
		
		 int j = 0;
		 StringBuffer inputBuf = new StringBuffer();
		
//		System.out.println("Array Langth: "+inputArr.length);
		// ExecutorService service = Executors.newFixedThreadPool(6);
		 


			for(int i=0; i<100000; i++) {
				if(j <= 5000) {
					inputBuf = inputBuf.append(inputArr[i]).append("\n");
					j++;
					if(i == inputArr.length-1) {
						String inpurSrt = new String(inputBuf);
						String idStr = i+"";
						String outputFilePath = pathUse+i+"_"+"Output_Europarl_Larg_.txt";
						String output = detokService.Detokenize(idStr, inpurSrt, "EN", idStr, "true", "test");
						
						if(output != null) {
							writeFile(output, outputFilePath);
						}
					}
				}else {
					String inpurSrt = new String(inputBuf);
					String idStr = i+"";
					String outputFilePath = pathUse+i+"_"+"Output_Europarl_Larg_.txt";
					String output = detokService.Detokenize(idStr, inpurSrt, "EN", idStr, "true", "test");
					
					if(output != null) {
						writeFile(output, outputFilePath);
					}
					
					inputBuf = new StringBuffer();
					inpurSrt = new String();
					j = 0;
				}
			}
			long EndAllProcess = System.currentTimeMillis();
			long TotalProcess = EndAllProcess - BeginReadFile;
			System.out.println("TotalProcess: "+TotalProcess);

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
	
private static String prepareOutput(String inputFilePath) {
		
		String[] pathArr = inputFilePath.split("/");
		int lengthOfPathArr = pathArr.length;
		
		StringBuffer pathBuff = new StringBuffer();

		for(int i = 0; i<lengthOfPathArr-1; i++) {
			pathBuff = pathBuff.append(pathArr[i]).append("/");
		}
		String pathUse = new String(pathBuff);
		
		return pathUse;
	}

public static String getResultStringFromFile(String inputFilePath) throws FileNotFoundException {
	
	FileInputStream inputString = new FileInputStream(inputFilePath);
	
	DataInputStream in = new DataInputStream(inputString);
	BufferedReader br1 = new BufferedReader(new InputStreamReader(in));
	StringBuffer inputBuf = new StringBuffer();
	
//	long BeginReadFile = System.currentTimeMillis();
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
//	long EndReadFile = System.currentTimeMillis();
//	long TotalReadFile = EndReadFile - BeginReadFile;
//	System.out.println("TotalReadFile: "+TotalReadFile);
	
	return new String(inputBuf);
}

}
