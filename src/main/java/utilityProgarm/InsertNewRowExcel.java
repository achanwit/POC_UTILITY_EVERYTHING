package utilityProgarm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;





public class InsertNewRowExcel {

	public InsertNewRowExcel() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws  IOException, InterruptedException {

		Workbook wb=WorkbookFactory.create(new FileInputStream("/home/chanwit/Documents/Excel/report_2.xls"));
		Sheet sh=wb.getSheet("Instant Messages");  
		int rows=sh.getLastRowNum();
		sh.shiftRows(0,rows,1);   
//		int rowUse = rows-1;
		
		//sh.createRow(1);
		Row row = sh.createRow(0);
		Cell cell = row.createCell(1);
		cell.setCellValue("Instant Messages ("+rows+")");
		
		FileOutputStream output_file =new FileOutputStream(new File("/home/chanwit/Documents/Excel/report_2.xls"));  //Open FileOutputStream to write updates
		wb.write(output_file);
		
		
		 
	}

}
