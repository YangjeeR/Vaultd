package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {

    protected XSSFSheet sheet1;
    protected XSSFWorkbook wb;
    
   public ExcelDataConfig(String excelPath)
   {
	   try
	   {
	   File src=new File(excelPath);
	   FileInputStream fis=new FileInputStream(src);
	   wb=new XSSFWorkbook(fis);
	   }
	   
	   catch(Exception e)
	   {
		   System.out.println(e.getMessage());
		   
	   }
	   
   }
   
   public String ExcelGetData(int SheetNumber,int row,int Col)
   {
	   sheet1=wb.getSheetAt(SheetNumber);
	   XSSFCell  yourCell = sheet1.getRow(row).getCell(Col);
	   yourCell.setCellType(CellType.STRING);
	   String data=yourCell.getStringCellValue();
	   return data;
   }
   
   public int GetRowCount(int sheetIndex)
   {
	   int row=wb.getSheetAt(sheetIndex).getLastRowNum();
	   row=row+1;
	   return row;
	   
   }

    
}
