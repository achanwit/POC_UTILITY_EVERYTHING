package testPerformanceDeTokenize;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import utilityProgarm.CallWebServiceByGetParam;

public class InterfaceCallDetokAsThread implements Runnable{
	
	private String idStr;
	private String inputStr; 
	private String languageStr; 
	private String chunkStr;
	private String realtimeStr; 
	private String accountStr;
	private CallWebServiceByGetParam detokService;
	private String outFilePath;

	public InterfaceCallDetokAsThread(String idStr, String inputStr, String languageStr, String chunkStr,
			String realtimeStr, String accountStr, String outFilePath) {
		// TODO Auto-generated constructor stub
		this.idStr = idStr;
		this.inputStr = inputStr;
		this.languageStr = languageStr;
		this.chunkStr = chunkStr;
		this.realtimeStr = realtimeStr;
		this.accountStr = accountStr;
		this.detokService = new CallWebServiceByGetParam();
		this.outFilePath = outFilePath;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String output = detokService.Detokenize(idStr, inputStr, languageStr, chunkStr, realtimeStr, accountStr);
		if(output != null) {
			writeFile(output, outFilePath);
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
