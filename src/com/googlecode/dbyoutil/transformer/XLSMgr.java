package com.googlecode.dbyoutil.transformer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.sql.rowset.CachedRowSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.googlecode.dbyoutil.tool.CommonUtil;
import com.googlecode.dbyoutil.tool.Spreadsheet;

public class XLSMgr {
	private boolean sheetCreated = false;
	private HSSFSheet spreadSheet;
	private HSSFRow row;
	private short currentRow = -1;

	/**
	 * This method accept SQL result set and convert it to a single sheet based
	 * Excel spreadsheet (with paging support).
	 */
	public final static byte[] toExcelSheet(final Spreadsheet wb,
			final String wbName, final CachedRowSet crs, ResultSet rs, boolean pagedSheet) {
		byte[] retVal = null;
		if (rs != null) {
			try {
				HSSFSheet spreadSheet = wb.createSheet(wbName);

				final ResultSetMetaData rsmd = rs.getMetaData();
				final int numberOfColumns = rsmd.getColumnCount();
				short i;
				HSSFRow row = spreadSheet.createRow(0);
				HSSFCell cell = null;
				for (i = 1; i <= numberOfColumns; i++) {
					spreadSheet.setColumnWidth((short) (i - 1),
							(short) (256 * 25));
					cell = row.createCell((short) (i - 1));
					setCellView(wb, cell);
					cell.setCellValue(rsmd.getColumnName(i));
				}

				int rowNum = 1;
				final int MS_EXCEL_MAX_ROW = 65535;
				int sheetCount = 1;
				while (rs.next()) {
					if (rowNum > MS_EXCEL_MAX_ROW) {
						spreadSheet = wb.createSheet(wbName + "("
								+ ++sheetCount + ")");
						rowNum = 1;
					}
					row = spreadSheet.createRow(rowNum);
					rowNum++;
					for (i = 1; i <= numberOfColumns; i++) {
						cell = row.createCell((short) (i - 1));
						cell.setCellValue(CommonUtil.handleLOBValue(rs
								.getObject(i)));
					}
				}
				final ByteArrayOutputStream output = new ByteArrayOutputStream();
				wb.write(output);
				output.flush();
				output.close();
				retVal = output.toByteArray();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

		return retVal;
	}

	private static void setCellView(HSSFWorkbook wb, HSSFCell cell) {
		HSSFFont f = wb.createFont();
		HSSFCellStyle cs = wb.createCellStyle();

		// set font to 10 point type
		f.setFontHeightInPoints((short) 10);
		f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		f.setColor((short) HSSFFont.COLOR_RED);

		cs.setFont(f);
		cell.setCellStyle(cs);
	}

	public void generateExcel(File xmlDocument) {
		try {// Creating a Workbook
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet spreadSheet = wb.createSheet("spreadSheet");

			spreadSheet.setColumnWidth((short) 0, (short) (256 * 25));
			spreadSheet.setColumnWidth((short) 1, (short) (256 * 25));
			// Parsing XML Document
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlDocument);
			NodeList nodeList = document.getElementsByTagName("stmt");
			// Creating Rows
			HSSFRow row = spreadSheet.createRow(0);

			HSSFCell cell = row.createCell((short) 1);
			cell.setCellValue("Year 2005");
			cell = row.createCell((short) 2);
			cell.setCellValue("Year 2004");

			HSSFRow row1 = spreadSheet.createRow(1);
			HSSFRow row2 = spreadSheet.createRow(2);
			HSSFRow row3 = spreadSheet.createRow(3);
			HSSFRow row4 = spreadSheet.createRow(4);
			HSSFRow row5 = spreadSheet.createRow(5);
			HSSFRow row6 = spreadSheet.createRow(6);
			HSSFRow row7 = spreadSheet.createRow(7);
			HSSFRow row8 = spreadSheet.createRow(8);
			HSSFRow row9 = spreadSheet.createRow(9);
			HSSFRow row10 = spreadSheet.createRow(10);
			HSSFRow row11 = spreadSheet.createRow(11);

			for (int i = 0; i < nodeList.getLength(); i++) {
				HSSFCellStyle cellStyle = wb.createCellStyle();
				cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

				switch (i) {
				// Creating column1 (Row label) and column 2 (Year 2005 stmt)
				case 0:

					cell = row1.createCell((short) 0);
					cell.setCellValue("Revenue ($)");

					cell = row1.createCell((short) 1);
					cell.setCellValue(((Element) (nodeList.item(0)))
							.getElementsByTagName("revenue").item(0)
							.getFirstChild().getNodeValue());

					cell = row2.createCell((short) 0);
					cell.setCellValue("Cost of Revenue ($)");

					cell = row2.createCell((short) 1);
					cell.setCellValue(((Element) (nodeList.item(0)))
							.getElementsByTagName("costofrevenue").item(0)
							.getFirstChild().getNodeValue());

					cell = row3.createCell((short) 0);
					cell.setCellValue("Research and Development ($)");

					cell = row3.createCell((short) 1);
					cell.setCellValue(((Element) (nodeList.item(0)))
							.getElementsByTagName("researchdevelopment")
							.item(0).getFirstChild().getNodeValue());

					cell = row4.createCell((short) 0);
					cell.setCellValue("Sales and Marketing ($)");

					cell = row4.createCell((short) 1);
					cell.setCellValue(((Element) (nodeList.item(0)))
							.getElementsByTagName("salesmarketing").item(0)
							.getFirstChild().getNodeValue());

					cell = row5.createCell((short) 0);
					cell.setCellValue("General and Administrative ($)");

					cell = row5.createCell((short) 1);
					cell.setCellValue(((Element) (nodeList.item(0)))
							.getElementsByTagName("generaladmin").item(0)
							.getFirstChild().getNodeValue());

					cell = row6.createCell((short) 0);
					cell.setCellValue("Total Operating Expenses ($)");
					cell.setCellStyle(cellStyle);
					cell = row6.createCell((short) 1);
					cell.setCellValue(((Element) (nodeList.item(0)))
							.getElementsByTagName("totaloperexpenses").item(0)
							.getFirstChild().getNodeValue());

					cell.setCellStyle(cellStyle);

					cell = row7.createCell((short) 0);
					cell.setCellValue("Operating Income ($)");

					cell = row7.createCell((short) 1);
					cell.setCellValue(((Element) (nodeList.item(0)))
							.getElementsByTagName("operincome").item(0)
							.getFirstChild().getNodeValue());

					cell = row8.createCell((short) 0);
					cell.setCellValue("Investment Income ($)");

					cell = row8.createCell((short) 1);
					cell.setCellValue(((Element) (nodeList.item(0)))
							.getElementsByTagName("invincome").item(0)
							.getFirstChild().getNodeValue());

					cell = row9.createCell((short) 0);
					cell.setCellValue("Income Before Taxes ($)");
					cell.setCellStyle(cellStyle);

					cell = row9.createCell((short) 1);
					cell.setCellValue(((Element) (nodeList.item(0)))
							.getElementsByTagName("incbeforetaxes").item(0)
							.getFirstChild().getNodeValue());

					cell.setCellStyle(cellStyle);

					cell = row10.createCell((short) 0);
					cell.setCellValue("Taxes ($)");

					cell = row10.createCell((short) 1);
					cell.setCellValue(((Element) (nodeList.item(0)))
							.getElementsByTagName("taxes").item(0)
							.getFirstChild().getNodeValue());

					cell = row11.createCell((short) 0);
					cell.setCellValue("Net Income ($)");
					cell.setCellStyle(cellStyle);

					cell = row11.createCell((short) 1);
					cell.setCellValue(((Element) (nodeList.item(0)))
							.getElementsByTagName("netincome").item(0)
							.getFirstChild().getNodeValue());

					cell.setCellStyle(cellStyle);

					break;
				// Creating column 3 (Year 2004 stmt)
				case 1:

					cell = row1.createCell((short) 2);
					cell.setCellValue(((Element) (nodeList.item(1)))
							.getElementsByTagName("revenue").item(0)
							.getFirstChild().getNodeValue());

					cell = row2.createCell((short) 2);
					cell.setCellValue(((Element) (nodeList.item(1)))
							.getElementsByTagName("costofrevenue").item(0)
							.getFirstChild().getNodeValue());

					cell = row3.createCell((short) 2);
					cell.setCellValue(((Element) (nodeList.item(1)))
							.getElementsByTagName("researchdevelopment")
							.item(0).getFirstChild().getNodeValue());

					cell = row4.createCell((short) 2);
					cell.setCellValue(((Element) (nodeList.item(1)))
							.getElementsByTagName("salesmarketing").item(0)
							.getFirstChild().getNodeValue());

					cell = row5.createCell((short) 2);
					cell.setCellValue(((Element) (nodeList.item(1)))
							.getElementsByTagName("generaladmin").item(0)
							.getFirstChild().getNodeValue());

					cell = row6.createCell((short) 2);
					cell.setCellValue(((Element) (nodeList.item(1)))
							.getElementsByTagName("totaloperexpenses").item(0)
							.getFirstChild().getNodeValue());

					cell.setCellStyle(cellStyle);

					cell = row7.createCell((short) 2);
					cell.setCellValue(((Element) (nodeList.item(1)))
							.getElementsByTagName("operincome").item(0)
							.getFirstChild().getNodeValue());

					cell = row8.createCell((short) 2);
					cell.setCellValue(((Element) (nodeList.item(1)))
							.getElementsByTagName("invincome").item(0)
							.getFirstChild().getNodeValue());

					cell = row9.createCell((short) 2);
					cell.setCellValue(((Element) (nodeList.item(1)))
							.getElementsByTagName("incbeforetaxes").item(0)
							.getFirstChild().getNodeValue());

					cell.setCellStyle(cellStyle);

					cell = row10.createCell((short) 2);
					cell.setCellValue(((Element) (nodeList.item(1)))
							.getElementsByTagName("taxes").item(0)
							.getFirstChild().getNodeValue());

					cell = row11.createCell((short) 2);
					cell.setCellValue(((Element) (nodeList.item(1)))
							.getElementsByTagName("netincome").item(0)
							.getFirstChild().getNodeValue());
					cell.setCellStyle(cellStyle);
					break;

				default:
					break;
				}

			}
			// Outputting to Excel spreadsheet
			FileOutputStream output = new FileOutputStream(new File(
					"IncomeStatements.xls"));
			wb.write(output);
			output.flush();
			output.close();
		} catch (IOException e) {
			System.out.println("IOException " + e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out
					.println("ParserConfigurationException " + e.getMessage());
		} catch (SAXException e) {
			System.out.println("SAXException " + e.getMessage());
		}

	}
}