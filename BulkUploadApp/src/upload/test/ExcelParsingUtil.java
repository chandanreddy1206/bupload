package upload.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 * The Class ExcelParsingUtil.This is used to parse the excel data and return
 * the excel data in the form on List of List
 * 
 * @version <0.1>
 */
public class ExcelParsingUtil {

	/**
	 * Read excell data.
	 * 
	 * @param fileContent
	 *            the file content
	 * @param colsize
	 *            number of columns in excel
	 * @return the list
	 * @throws InvalidFormatException
	 *             the invalid format exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws IllegalFormatException
	 *             the illegal format exception
	 */
	public static List<List<Object>> readExcellData(
			final InputStream fileContent, final int colsize)
			throws InvalidFormatException, IOException, IllegalFormatException {

		final List<List<Object>> tableList = new ArrayList<List<Object>>();
		try {
			System.out.println("********1**********");
			final Workbook wb = WorkbookFactory.create(fileContent);
			final Sheet mySheet = wb.getSheetAt(0);
			List<Object> rowList;
			Cell cell;
			final int rowStart = mySheet.getFirstRowNum();
			final int rowEnd = mySheet.getLastRowNum();
			String wrap = null;
			String wrap2 = null;
			// String formula = null;
			Row r = null;
			int i = 0;
			int lastColumn = 0;
			System.out.println("********2**********");
		/*	//if (rowStart != 0) {
				throw new IllegalFormatException();
			}*/
			//System.out.println("rowStart ::::"+rowStart+"\t rowEnd :::::"+rowEnd);
			for (int rowNum = rowStart; rowNum <= rowEnd; rowNum++) {
				r = mySheet.getRow(rowNum);
				
				if (r != null) {
					lastColumn = colsize;
					int excelCol = r.getLastCellNum();
					//System.out.println("excelCol ::::" + excelCol + " lastColumn ::::" + lastColumn);
					if (rowNum == 0) {//for columns headers
						/*if (excelCol > lastColumn || excelCol < lastColumn) {
							throw new IllegalFormatException();

						}*/
					}else if(excelCol > lastColumn){//for column values
//						throw new IllegalFormatException();
					}
					rowList = new ArrayList();
					int a = 0;

					for (int cn = 0; cn < lastColumn; cn++) {
						cell = r.getCell(cn, r.CREATE_NULL_AS_BLANK);
						if (rowNum == 0) {
							//System.out.println("cell" + cell);
							if (cell == null) {
//								throw new IllegalFormatException();
							}
						}
						switch (cell.getCellType()) {
						// Numeric Cell type (0)-----CELL_TYPE_NUMERIC
						case 0: {
							rowList.add(cell.getNumericCellValue());
							break;
						}
						// String Cell type (1)------CELL_TYPE_STRING
						case 1: {
							wrap = cell.getStringCellValue();
							wrap2 = wrap.replaceAll("[\r\n]", "");// to get all
																	// the lines
																	// of a cell
							rowList.add(wrap2);
							break;
						}
						// cell type formula
						case 2: {
							// formula = cell.getCellFormula();
							i = cell.getCachedFormulaResultType();
							if (i == 0) {
								rowList.add(cell.getNumericCellValue());
								break;
							}
							if (i == 1) {
								rowList.add(cell.getStringCellValue());
								break;

							}
						}
						// Blank Cell type (3)----CELL_TYPE_BLANK
						case 3: {
							rowList.add(null);
							a++;
						}
						}
					}
					if (a < rowList.size()) {
						tableList.add(rowList);
					}
				}
			}
			System.out.println("********3**********");
		} catch (InvalidFormatException e1) {
			System.out.println("out of memory in catch block 1");
			e1.printStackTrace();
			return null;
		} catch (IOException e1) {
			System.out.println("out of memory in catch block2");
			e1.printStackTrace();
			return null;
		}
		return tableList;

	}
}
