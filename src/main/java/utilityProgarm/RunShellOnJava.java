package utilityProgarm;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunShellOnJava {

	public RunShellOnJava() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String perlScript = "/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/tokenizer.perl";
//		String language = "en";
//		String threadNum = "6";
//		String inputFile =  "/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/Europarl_Larg_.txt";
//		String outputFile = "/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/out";
		
//		String perlScript = "/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/detokenizer.perl";
//		String language = "en";
//		String threadNum = "1";
//		String inputFile =  "/home/chanwit/Documents/LSDetokenize/A.Pre-Study/POC_Perfirmance_WebService/InputLarg/InputDetokenize.txt";
//		String outputFile = "/home/chanwit/Documents/LSDetokenize/A.Pre-Study/POC_Perfirmance_WebService/InputLarg/OutnputDetokenize.txt";
		
		String perlScript = "/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/detokenizer.perl";
		String language = "en";
		String threadNum = "1";
		String inputFile =  "/home/chanwit/Documents/LSSpace/A.Input/InputLarg_TH_UTF.txt";
		String outputFile = "/home/chanwit/Documents/LSSpace/A.Input/OutputLarg_TH_UTF.txt";
		try {
			Process process = Runtime.getRuntime().exec("/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/TestPerformance_six_thread.sh "+perlScript+" "+language+" "+threadNum+" "+inputFile+" "+outputFile, null, new File("/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/"));
			
			StringBuilder output = new StringBuilder();

	        BufferedReader reader = new BufferedReader(
	                new InputStreamReader(process.getInputStream()));

	        String line;
	        while ((line = reader.readLine()) != null) {
	            output.append(line + "\n");
	        }

	        int exitVal = 0;
			try {
				exitVal = process.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        if (exitVal == 0) {
	            System.out.println("Success!");
	            System.out.println(output);
	            System.exit(0);
	        } else {
	            //abnormal...
	        }
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
