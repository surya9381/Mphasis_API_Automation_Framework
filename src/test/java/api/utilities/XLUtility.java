package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XLUtility {

	public Workbook book;
	public Sheet sh;
	public Row row;
	public Cell cel;
	public FileInputStream fis;
	public FileOutputStream fos;
	
//	String path=System.getProperty("user.dir")+"\\testdata\\Userdata.xlsx";
	String path;
	public XLUtility(String path)
	{
		this.path=path;
	}
	
	public String getCellData(String sheetname, int rownum, int celnum) throws EncryptedDocumentException, IOException
	{
	    fis=new FileInputStream(path);
	    book=WorkbookFactory.create(fis);
		sh= book.getSheet(sheetname);
		row=sh.getRow(rownum);
		cel=row.getCell(celnum);
				
		DataFormatter formatter=new DataFormatter();
		String data=formatter.formatCellValue(cel);
		book.close();
		fis.close();
		return data;
	}
	
	public int getRowCount(String sheetname) throws EncryptedDocumentException, IOException
	{
	   fis=new FileInputStream(path);
	   book=WorkbookFactory.create(fis);
	   sh=book.getSheet(sheetname);
	   int rowcount=sh.getLastRowNum();
	   book.close();
	   fis.close();
		return rowcount;
	}
	
	public int getCellCount(String sheetname, int rownum) throws EncryptedDocumentException, IOException
	{
		fis=new FileInputStream(path);
		book=WorkbookFactory.create(fis);
		sh=book.getSheet(sheetname);
		row=sh.getRow(rownum);
		int celcount=row.getLastCellNum();
		
		book.close();
		fis.close();
		return celcount;
	}
	
	public String setCellData(String sheetname, int rownum, int celnum, String data) throws EncryptedDocumentException, IOException
	{
		fis=new FileInputStream(path);
		book=WorkbookFactory.create(fis);
		sh=book.getSheet(sheetname);
		sh.createRow(rownum).createCell(celnum).setCellValue(data);
		
		fos=new FileOutputStream(path);
		book.write(fos);
		book.close();
		fos.flush();
		
		return data;
	}
}
