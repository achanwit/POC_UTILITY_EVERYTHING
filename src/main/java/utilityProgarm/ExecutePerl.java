package utilityProgarm;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ExecutePerl {

	public ExecutePerl() {
		
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		long Begin = System.currentTimeMillis();
		
		
		Process proc = Runtime.getRuntime().exec(
				"perl /home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/tokenizer.perl"
				+ " -l en"
				+ " -threads 1"
				+ " < /home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/test.txt >"
				+ " /home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/out_test_3.txt",
				 null, new File("/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/"));
				
		
		StringBuilder output = new StringBuilder();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(proc.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line + "\n");
        }

        int exitVal = 0;
		try {
			exitVal = proc.waitFor();
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
	
		
		
		
		
		
		
		
		
		
		
//		 ProcessBuilder builder = new ProcessBuilder(
//					"perl", "/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/tokenizer.perl"
//					,"-l", "en"
//					,"-threads", "1"
//					,"<", "/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/Europarl_Larg_.txt", ">"
//					,"/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/out_java"
//				 );
//		 File directory = new File("/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/");
//		 File output = new File("/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/out_java.txt");
//		 builder.directory(directory);
//		 
//		 builder.redirectErrorStream(true);
//		 
////		 	String[] commandArr = {
////		 			"perl", "/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/tokenizer.perl"
////					,"-l", "en"
////					,"-threads", "1"
////					,"<", "/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/Europarl_Larg_.txt", ">"
////					,"/home/chanwit/Documents/LSTokenize/A.Pre-Study/mosesdecoder/moses/scripts/tokenizer/out_java.txt"
////		 	};
//	        Process p = builder.start();
////	        Process p = Runtime.getRuntime().exec(commandArr);
//	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//	        
//			
//	        //BufferedReader r = new BufferedReader(p.getOutputStream());
//	        String line;
////	        while (true) {
//	        for(int i = 0; i< 3; i++) {
//	            line = r.readLine();
//	            if (line == null) { break; }
//	            System.out.println(line);
//	        }
//				 
//
//		
//		
//		
//		long End = System.currentTimeMillis();
//		long Total = End - Begin;
//		System.out.println("Total: "+Total);

	}

}
