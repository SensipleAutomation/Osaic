package operators;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {   

		   String[][] tabArray = null;

		   try {

			   FileInputStream ExcelFile = new FileInputStream(FilePath);

			   // Access the required test data sheet

			   ExcelWBook = new XSSFWorkbook(ExcelFile);

			   ExcelWSheet = ExcelWBook.getSheet(SheetName);

			   int startRow = 1;

			   int startCol = 1;

			   int ci,cj;

			   int totalRows = ExcelWSheet.getLastRowNum();

			   // you can write a function as well to get Column count

			   int totalCols = 1;

			   tabArray=new String[totalRows][totalCols];

			   ci=0;

			   for (int i=startRow;i<=totalRows;i++, ci++) {           	   

				 // cj=0;

				   //for (int j=startCol;j<=totalCols;j++, cj++){

					   tabArray[ci][0]=getCellData(i,0);

					   System.out.println(tabArray[ci][0]);  

					//	}

					}

				}

			catch (FileNotFoundException e){

				System.out.println("Could not read the Excel sheet");

				e.printStackTrace();

				}

			catch (IOException e){

				System.out.println("Could not read the Excel sheet");

				e.printStackTrace();

				}

			return(tabArray);

			}

		public static String getCellData(int RowNum, int ColNum) throws Exception {

			try{
				DataFormatter formatter = new DataFormatter();
				//String val = formatter.formatCellValue(sheet.getRow(row).getCell(col));
				
				Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
				//String cell= formatter.formatCellValue(Cell);
				int dataType = Cell.getCellType();

				
					//String cell= formatter.formatCellValue(Cell.getS);
					double CellData = Cell.getNumericCellValue();
					Double doubleInstance = new Double(CellData);  
					String celval=doubleInstance.toString();
					String cellval=celval.replaceAll("\\.0*$", "");
					return cellval;

				}catch (Exception e){

				System.out.println(e.getMessage());

				throw (e);

				}

			}

	}