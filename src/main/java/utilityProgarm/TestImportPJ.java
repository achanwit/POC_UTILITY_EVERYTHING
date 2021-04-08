package utilityProgarm;

import com.omniscien.lc.sentencesegment.service.SentenceServiceImpl;
import com.omniscien.tokenize.process.TokenizeProcessMain;

import LSLanguageid.LSLanguageid;
import extractDocument.MSOffice;

public class TestImportPJ {

	public TestImportPJ() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		extractDocument.MSOffice extract = new MSOffice();
		LSLanguageid languageid = new LSLanguageid();
		SentenceServiceImpl ss = new SentenceServiceImpl();
		TokenizeProcessMain tk = new TokenizeProcessMain();
//		ner.Ner ner = new ner.NER();
		
		String inputfile = "/home/chanwit/Documents/01_HMRC_WorkFlow/Input/Word/input_en.doc";
		String output = "/home/chanwit/Documents/01_HMRC_WorkFlow/Input/Word/input_en.doc.txt";
		String outputSS =  "/home/chanwit/Documents/01_HMRC_WorkFlow/Input/Word/input_en.doc.txt.ss";
		String outputTK = "/home/chanwit/Documents/01_HMRC_WorkFlow/Input/Word/input_en.doc.txt.ss.tk.txt";
		String language = "en";
		
		//Extract
		long beginExtract = System.currentTimeMillis();
		TestExtract(extract, inputfile, output);
		long endExtract = System.currentTimeMillis();
		long totalExtarct = endExtract-beginExtract;
		System.out.println("totalExtarct: "+totalExtarct);
		
		//Languid
		long beginLanguageid = System.currentTimeMillis();
		TestLanguageid(languageid, output);
		long endLanguageid = System.currentTimeMillis();
		long totalLanguageid = endLanguageid-beginLanguageid;
		System.out.println("totalLanguageid: "+totalLanguageid);
		
		//Sentence Segment
		long beginSentenceSegment = System.currentTimeMillis();
		TesttotalSentenceSegment(ss,output,outputSS,language, "\n");
		long endSentenceSegment = System.currentTimeMillis();
		long totalSentenceSegment = endSentenceSegment-beginSentenceSegment;
		System.out.println("totalSentenceSegment: "+totalSentenceSegment);
		
		//Tokenize
		long beginTokenize = System.currentTimeMillis();
		TestTokenize(tk, outputSS, outputTK, language);
		long endTokenize = System.currentTimeMillis();
		long totalTokenize = endTokenize-beginTokenize;
		System.out.println("totalTokenize: "+totalTokenize);
		
		

	}





	



	private static void TestTokenize(TokenizeProcessMain tk, String outputSS, String outputTK, String language) {
		tk.ProcessTokenize(outputSS, outputTK, language, 1);
		
	}

	private static void TesttotalSentenceSegment(SentenceServiceImpl ss, String input, String output,
			 String language, String encript) {
		try {
		ss.getSentenceSegmentToOutputFile(input, output, language, encript);
		}catch(Exception e) {
			
		}
	}

	private static void TestLanguageid(LSLanguageid languageid, String inputfile) {
		try {
			System.out.println(languageid.GetLanguageIDGeneralFromFile(inputfile));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void TestExtract(MSOffice extract, String inputfile, String output) {
		
		extract.ExtractFileToParagraphText("01", inputfile, output);
		
	}

}
