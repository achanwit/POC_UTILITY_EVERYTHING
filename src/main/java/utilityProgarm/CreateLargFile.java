package utilityProgarm;

import java.io.FileNotFoundException;

public class CreateLargFile {

	private static ReadFileToString readFileToStr; 
	private static WriteFile writeFile; 
	
	public CreateLargFile() {
		
	}

	public static void main(String[] args) throws FileNotFoundException {
		long Begin = System.currentTimeMillis();
		readFileToStr = new ReadFileToString();
		writeFile = new WriteFile();
		String inputFilePath = "/home/chanwit/Documents/LSSpace/A.Input/TH-TestSet1.txt";
		String outputFilePath = "/home/chanwit/Documents/LSSpace/A.Input/Larg_TH-TestSet1.txt";
		String result = readFileToStr.getResultStringFromFile(inputFilePath);
		//System.out.println("result: "+result);
		
		for(int i = 0;i<=190651;i++) {
			writeFile.writeFile(result, outputFilePath);
			
		}
		long End = System.currentTimeMillis();
		long Total = End - Begin;
		System.out.println("Total Time Use: "+Total);
	}

}
