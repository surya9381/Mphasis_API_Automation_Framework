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

public class ExcelUtility {

	String path;
	public FileInputStream fis;
	public FileOutputStream fos;
	public Workbook book;
	public Sheet sh;
	public Row row;
	public Cell cel;
	
	public ExcelUtility(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String sheetname) throws EncryptedDocumentException, IOException
	{
		fis=new FileInputStream(path);
		book=WorkbookFactory.create(fis);
		Sheet sh=book.getSheet(sheetname);
		int rowcount = sh.getLastRowNum();
		book.close();
		fis.close();
		return rowcount;
	}
	
	public int getCelCount(String sheetname, int rownum) throws EncryptedDocumentException, IOException
	{
		fis=new FileInputStream(path);
		book=WorkbookFactory.create(fis);
	    sh = book.getSheet(sheetname);
	    row=sh.getRow(rownum);
	    short celcount = row.getLastCellNum();
	    book.close();
	    fis.close();
	    return celcount;
	}
	
	public String getCelData(String sheetname, int rownum, int celnum) throws EncryptedDocumentException, IOException
	{
		fis=new FileInputStream(path);
		book=WorkbookFactory.create(fis);
		sh=book.getSheet(sheetname);
		row=sh.getRow(rownum);
		cel=row.getCell(celnum);
		DataFormatter formatter=new DataFormatter();
		String data=formatter.formatCellValue(cel);
		book.close();
		fis.close();
		
		return data;
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
		fos.close();
		
		return data;
	}
}
