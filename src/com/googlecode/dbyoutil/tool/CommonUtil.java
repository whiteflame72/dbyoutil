package com.googlecode.dbyoutil.tool;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.sql.rowset.CachedRowSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.serialize.LineSeparator;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;

import com.googlecode.dbyoutil.transformer.SQLClient;
import com.googlecode.dbyoutil.transformer.SQLMgr;
import com.googlecode.dbyoutil.transformer.XLSMgr;
import com.googlecode.dbyoutil.transformer.SQLMgr.ErrorHandler;
import com.sun.rowset.CachedRowSetImpl;

public class CommonUtil {

	public static final String buildNum = "RC66";
	private static boolean formatAllowed;
	
	private static String currentDirAbsolutePath;

	private static String waterMark = "" + buildNum + " ("
			+ StringUtil.getCurrentTime() + ")";

	private static String currentTable;
	
	public static final String[][] SYSTEM_ERROR = null;
	public static final int CLIENT_DEFAULT_LINE_WIDTH = 59;
	public static String SQL_DELIMITER = System.getProperty("line.separator")
			+ "/";
	public static List<String> sqlName = null;

	public static final int MS_ACCESS = 0;
	public static final int ORACLE = 1;
	public static final int ORACLE_LITE = 2;

	public static String getWaterMark() {
		return CommonUtil.safeCDATAForXML(waterMark);
	}

	public static void setWaterMark(String waterMark) {
		CommonUtil.waterMark = waterMark;
	}
	
	public static boolean isFormatAllowed() {
		return formatAllowed;
	}

	public static void setFormatAllowed(boolean formatAllowed) {
		CommonUtil.formatAllowed = formatAllowed;
	}

	public static String getCurrentTable() {
		return currentTable;
	}

	public static void setCurrentTable(String currentTable) {
		CommonUtil.currentTable = currentTable;
	}

	/**
	 * This is the first entry method of all for binary output.
	 */
	public static byte[] getBinaryData(Statement stat, ResultSet rs,
			final int mode, String sql, final Spreadsheet wb,
			final String wbName, boolean pagedExcelSheet) throws Exception {
		byte[] retVal = null;

		if (sql != null && !sql.trim().equals("")) {
			final int descCommandLength = 5;
			String currentCommandGrabbed = sql.trim().substring(0,
					descCommandLength);

			if (sql.length() >= descCommandLength
					&& currentCommandGrabbed.trim().startsWith("desc")) {
				final String param = sql.trim().substring(descCommandLength,
						sql.trim().length());

				retVal = getNonStandardSQLBytes(stat, rs, mode,
						currentCommandGrabbed.trim(), param, sql, wb, wbName);
			} else {
				// === support multiple SQLs concurrently in one sql string
				if (sql != null && sql.trim().length() > 1
						&& sql.indexOf(SQL_DELIMITER) > -1) {
					sql = encodeSQLForwardSlashes(sql);
					final StringTokenizer st = new StringTokenizer(sql,
							SQL_DELIMITER);
					retVal = null;
					int sheetCount = 1;
					String name = null;
					while (st.hasMoreTokens()) {
						currentCommandGrabbed = st.nextToken(SQL_DELIMITER);
						currentCommandGrabbed = decodeSQLForwardSlashes(currentCommandGrabbed);
						if (!"Sheet".equals(sqlName.get(sheetCount - 1))) {
							name = sqlName.get(sheetCount - 1);
						} else {
							name = sqlName.get(sheetCount - 1) + sheetCount;
						}
						retVal = getBinaryData(stat, rs, mode,
								currentCommandGrabbed, wb, name,
								pagedExcelSheet);
						sheetCount++;
					}
				} else {
					retVal = getStandardSQLBytes(stat, rs, mode, sql, wb,
							wbName);
				}
			}
		}

		return retVal;
	}

	private static byte[] getNonStandardSQLBytes(Statement stmt, ResultSet rs,
			final int mode, final String command, final String parameter,
			final String sqlQuery, final Spreadsheet wb, final String wbName)
			throws Exception {
		byte[] retVal = null;
		if ((sqlQuery == null) || sqlQuery.equals("")) {
			throw new Exception("SQL Query string can't be NULL or empty.");
		}
		// final String connectSstatus[][] = connect(); // DB2

		// if (connectStatus != SYSTEM_ERROR) {
		// return StringUtil.toStringWidth(
		// getErrorMessageString(connectSstatus),
		// CLIENT_DEFAULT_LINE_WIDTH).getBytes();
		// }

		try {
			String sqlCmd = null;

			// System.out.println("non-SQL compliant command '" + command + "'
			// param '" + parameter + "' sql '" + sqlQuery + "'");
			if (command.trim().startsWith("desc")) {
				sqlCmd = "select * from " + parameter;

				stmt.setMaxRows(1); // quick and dirty approach
				rs = stmt.executeQuery(sqlCmd);
				retVal = XLSMgr.toExcelSheet(wb, "desc", handleResultSet(rs),
						rs, false);
			} else {
				// just pass it through
				rs = stmt.executeQuery(sqlQuery);
				retVal = XLSMgr.toExcelSheet(wb, wbName, handleResultSet(rs),
						rs, false);
			}
		} catch (final Exception e) {
			System.err.println("getNonStandardSQLBytes:1: " + e.getMessage());

			// === reconnect for later use when it is up

			// loadDBDriver(); //DB2
			return StringUtil.toStringWidth(e.getMessage(),
					CLIENT_DEFAULT_LINE_WIDTH).getBytes();
		}
		// finally {
		// disconnect(); // DB2
		// }

		return retVal;
	}

	public static List getTables(Connection conn) {
		//TDB - for mysql don't forget to use catalog, for oracle use schema
		String catalog = null;
		String schema = null;
		List tables = new ArrayList();
		
		String[] types = {"TABLE"};
		try {
			DatabaseMetaData dbmd = conn.getMetaData();
			ResultSet rs = dbmd.getTables(catalog, schema, "%", types);
			while(rs.next()){
				tables.add(rs.getString("TABLE_NAME"));
				System.out.print(rs.getString("TABLE_NAME") + " ");
			}
		} catch (SQLException e) {
			//JDBC 4.0 style
			for (Throwable ex : e) {
				ex.printStackTrace();
			}		
		}			
		
		return tables;
	}

	/*
	 * class ColumnComparator implements Comparable { // === fields to be sorted
	 * private String name;
	 * 
	 * private String type;
	 * 
	 * private String precision; // === initializer public
	 * ColumnComparator(String name, String type, String precision) { this.name
	 * = name; this.type = type; this.precision = precision; } // === the
	 * required compareTo method. public int compareTo(Object o) {
	 * ColumnComparator column = (ColumnComparator) o; //
	 * System.out.println("comparing '" + name + "' with '" + // column.name +
	 * "' ..."); // === sort ascending return name.compareTo(column.name); } }
	 */
	public static String getNonStandardSQLData(int dbType, Statement stmt,
			ResultSet rs, final int mode, final String command,
			final String parameter, final String sqlQuery) throws Exception {
		String retVal = "";
		if ((sqlQuery == null) || sqlQuery.equals("")) {
			throw new Exception("SQL Query string can't be NULL or empty.");
		}
		// final String connectSstatus[][] = connect(); // DB2

		// if (connectSstatus != SYSTEM_ERROR) {
		//	
		// return StringUtil.toStringWidth(
		// getErrorMessageString(connectSstatus),
		// CLIENT_DEFAULT_LINE_WIDTH);
		// }

		StringBuffer headerXmlString = null;
		final StringBuffer xmlString = new StringBuffer();
		try {
			boolean countRowColumn = false;
			boolean sortColumn = false;
			String sqlCmd = null;
			// support displaying of values
			boolean valueFlag = false;

			if ("descv".equalsIgnoreCase(command)) {
				valueFlag = true;
			}

			// System.out.println("non-SQL compliant command '" + command + "'
			// param '" + parameter + "' sql '" + sqlQuery + "'");
			if (command.trim().startsWith("desc")) {
				countRowColumn = true;
				sortColumn = true;
				// if (isMSAccess(dbUrl)) {
//				if (dbType == MS_ACCESS) {
//					// this might not be needed as only the first record of the
//					// result set is processed
//					sqlCmd = "select top 1 * from " + parameter;
//				} else {
					sqlCmd = "select * from " + parameter;
//				}

				rs = stmt.executeQuery(sqlCmd);
			} else {
				countRowColumn = true;
				// just pass it through
				rs = stmt.executeQuery(sqlQuery);
			}
			final ResultSetMetaData rsmd = rs.getMetaData();
			final int numberOfColumns = rsmd.getColumnCount();

			int i;
			int row = 0;
			rs.next(); // do only for the first record
			row++;

			if (countRowColumn) {
				xmlString.append("<row").append(row).append(" count=\"")
						.append(numberOfColumns).append("\">");
			} else {
				xmlString.append("<row").append(row).append(">");
			}

			if (sortColumn) {
				try {
					ColumnComparator.sortColumnArrayList(rs, rsmd, xmlString, valueFlag);
				} catch (Exception e) {
					System.out.println("getNonStandardSQLData:1 " + e.getMessage());
				}
			} else {
				for (i = 1; i <= numberOfColumns; i++) {
					xmlString.append("<").append(
							rsmd.getColumnName(i).replace(' ', '_'))
							.append(
									" type = '" + rsmd.getColumnTypeName(i)
											+ "' size = '"
											+ rsmd.getPrecision(i) + "'");
					if (!valueFlag) {
						xmlString.append("/>");
					} else {
						xmlString.append(rs.getRowId(i)).append("/").append(
								rsmd.getColumnName(i).replace(' ', '_'))
								.append(">");
					}
				}
			}
			xmlString.append("</row").append(row).append(">");

			headerXmlString = new StringBuffer("<dataSet name=\"" + currentTable + "\" count=\"" + row
					+ "\" tag=\"" + waterMark + "\">"
					+ System.getProperty("line.separator"));
			headerXmlString.append(xmlString.toString()).append("</dataSet>");

			// logger.info(xmlString.toString());
		} catch (Exception e) {
			e.printStackTrace();

			// === reconnect for later use when it is up

			// loadDBDriver(); //DB2
			return StringUtil.toStringWidth(e.getMessage(),
					CLIENT_DEFAULT_LINE_WIDTH);
		}
		// finally {
		//	
		// disconnect(); // DB2
		// }

		// System.out.println("before XML formatting ... XML = '" + xmlString +
		// "'");

		// === remove count(*) from the tag name - applies to Oracle database
		// only
		// String newXMLString = StringUtil.replace(headerXmlString.toString(),
		// "count(*)",
		// "count");
		String newXMLString = headerXmlString.toString();
		newXMLString = handleSQLValue(newXMLString);

		retVal = formatXML(newXMLString, "UTF-16");

		retVal = StringUtils.replace(retVal,
				"<?xml version=\"1.0\" encoding=\"UTF-16\"?>", "");

		return retVal;
	}

	private static byte[] getStandardPagingSQLBytes(Statement stmt,
			ResultSet rs, final int mode, final String sqlQuery,
			final Spreadsheet wb, final String wbName) throws Exception {
		byte[] retVal = null;
		if ((sqlQuery == null) || sqlQuery.trim().equals("")) {
			throw new Exception("SQL Query string can't be NULL or empty.");
		}
		// final String connectSstatus[][] = connect(); // DB2

		// if (connectSstatus != SYSTEM_ERROR) {
		// return StringUtil.toStringWidth(
		// getErrorMessageString(connectSstatus),
		// CLIENT_DEFAULT_LINE_WIDTH).getBytes();
		// }

		try {
			stmt.setFetchSize(10);
			// retrieve resultset
			rs = stmt.executeQuery(sqlQuery);

			retVal = XLSMgr.toExcelSheet(wb, wbName, handleResultSet(rs), rs,
					true);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return retVal;
	}

	public static final CachedRowSet handleResultSet(ResultSet rs) {
		CachedRowSet crs = null;
		try {
			crs = new CachedRowSetImpl();
			crs.setMaxRows(20);
			crs.setPageSize(4);
			crs.setFetchDirection(ResultSet.FETCH_FORWARD);
			crs.populate(rs, 10);
		} catch (SQLException e) {
			// if ("Result set type is TYPE_FORWARD_ONLY".indexOf(e.toString())
			// < 0) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// }
		}

		return crs;
	}

	private static byte[] getStandardSQLBytes(Statement stmt, ResultSet rs,
			final int mode, final String sqlQuery, final Spreadsheet wb,
			final String wbName) throws Exception {
		byte[] retVal = null;
		if ((sqlQuery == null) || sqlQuery.trim().equals("")) {
			throw new Exception("SQL Query string can't be NULL or empty.");
		}
		// final String connectSstatus[][] = connect(); // DB2
		//		
		// if (connectSstatus != SYSTEM_ERROR) {
		// return StringUtil.toStringWidth(
		// getErrorMessageString(connectSstatus),
		// CLIENT_DEFAULT_LINE_WIDTH).getBytes();
		// }

		try {
			// retrieve resultset
			rs = stmt.executeQuery(sqlQuery);
			retVal = XLSMgr.toExcelSheet(wb, wbName, handleResultSet(rs), rs,
					false);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return retVal;
	}

	/**
	 * This is the most important method among all that does the real work.
	 * 
	 * @param mode
	 * @param sqlQuery
	 * @return
	 * @throws Exception
	 */
	public static String getStandardSQLData(Statement stmt, ResultSet rs,
			final int mode, final String sqlQuery) throws Exception {
		String retVal = "";
		if ((sqlQuery == null) || sqlQuery.trim().equals("")) {
			throw new Exception("SQL Query string can't be NULL or empty.");
		}
		// final String connectSstatus[][] = connect(); // DB2
		//		
		// if (connectSstatus != SYSTEM_ERROR) {
		//		
		// return StringUtil.toStringWidth(
		// getErrorMessageString(connectSstatus),
		// CLIENT_DEFAULT_LINE_WIDTH);
		// }
		final StringBuffer xmlString = new StringBuffer();
		StringBuffer headerXmlString = null;

		try {

			// retrieve resultset
			rs = stmt.executeQuery(sqlQuery);
			final ResultSetMetaData rsmd = rs.getMetaData();
			final int numberOfColumns = rsmd.getColumnCount();

			int i;
			int row = 0;
			if (mode == SQLClient.CSV_EXCEL_DATA) {
				for (i = 1; i <= numberOfColumns - 1; i++) {
					xmlString.append(rsmd.getColumnName(i).replace(' ', '_'))
							.append(",");
				}
				xmlString.append(rsmd.getColumnName(i).replace(' ', '_'))
						.append(System.getProperty("line.separator"));
				while (rs.next()) {
					row++;
					for (i = 1; i <= numberOfColumns - 1; i++) {
						xmlString.append("\"").append(
								URLEncoder.encode(handleLOBValue(rs
										.getObject(i)))).append("\"").append(
								",");
					}
					xmlString.append("\"").append(
							URLEncoder.encode(String.valueOf(rs.getObject(i))))
							.append("\"").append(
									System.getProperty("line.separator"));
				}
//			} 
//			else if (mode == SQLClient.DISPLAY_COLUMN_NAME_AND_URL_ENCODE_DATA) {
//				while (rs.next()) {
//					row++;
//
//					// //logger.info(rs.getObject(1) + " " + rs.getObject(2));
//					xmlString.append("<row").append(row).append(">");
//					for (i = 1; i <= numberOfColumns; i++) {
//						xmlString
//								.append("<")
//								.append(rsmd.getColumnName(i).replace(' ', '_'))
//								.append(">")
//								.append(
//										URLEncoder.encode(handleLOBValue(rs
//												.getObject(i))))
//								.append("</")
//								.append(rsmd.getColumnName(i).replace(' ', '_'))
//								.append(">");
//					}
//					xmlString.append("</row").append(row).append(">");
//				}
			} // end of if(mode ==
			// SQL2XML.DISPLAY_COLUMN_NAME_AND_URL_ENCODE_DATA)
			else if (mode == SQLClient.DISPLAY_COLUMN_NAME_AND_PURE_DATA) {
				while (rs.next()) {
					row++;

					xmlString.append("<row").append(row).append(">");
					for (i = 1; i <= numberOfColumns; i++) {
						xmlString
								.append("<")
								.append(rsmd.getColumnName(i).replace(' ', '_'))
								.append(">")
								.append(handleLOBValue(rs.getObject(i)))
								.append("</")
								.append(rsmd.getColumnName(i).replace(' ', '_'))
								.append(">");
					}
					xmlString.append("</row").append(row).append(">");
				}
			} // end of if(mode ==
			// DISPLAY_COLUMN_NAME_AND_URL_ENCODE_DATA)
			else {
				if (mode == SQLClient.DISPLAY_COLUMN_NAME_AND_HTML_DATA) {
					while (rs.next()) {
						row++;

						xmlString.append("<row").append(row).append(">");
						for (i = 1; i <= numberOfColumns; i++) {
							xmlString
									.append("<")
									.append(
											rsmd.getColumnName(i).replace(' ',
													'_'))
									.append(">")
									.append(
											handleXMLDataInHTML(handleLOBValue(rs
													.getObject(i)))).append(
											"</").append(
											rsmd.getColumnName(i).replace(' ',
													'_')).append(">");
						}
						xmlString.append("</row").append(row).append(">");
					}
				} // end of if(mode ==
				// DISPLAY_COLUMN_NAME_AND_URL_ENCODE_DATA)
				else if (mode == SQLClient.NO_COLUMN_NAME_AND_URL_ENCODE_DATA) {
					while (rs.next()) {
						row++;
						xmlString.append("<row").append(row).append(">");
						for (i = 1; i <= numberOfColumns; i++) {

							xmlString.append("<column").append(i).append(">")
									.append(
											URLEncoder.encode(handleLOBValue(rs
													.getObject(i)))).append(
											"</column").append(i).append(">");
						}
						xmlString.append("</row").append(row).append(">");
					}
				} else {
					if (mode == SQLClient.NO_COLUMN_NAME_AND_PURE_DATA) {
						while (rs.next()) {
							row++;
							xmlString.append("<row").append(row).append(">");
							for (i = 1; i <= numberOfColumns; i++) {
								xmlString.append("<column").append(i).append(
										">").append(
										handleLOBValue(rs.getObject(i)))
										.append("</column").append(i).append(
												">");
							}
							xmlString.append("</row").append(row).append(">");
						}
					} else {
						if (mode == SQLClient.NO_COLUMN_NAME_AND_HTML_DATA) {
							while (rs.next()) {
								row++;
								xmlString.append("<row").append(row)
										.append(">");
								for (i = 1; i <= numberOfColumns; i++) {
									xmlString
											.append("<column")
											.append(i)
											.append(">")
											.append(
													handleXMLDataInHTML(handleLOBValue(rs
															.getObject(i))))
											.append("</column").append(i)
											.append(">");
								}
								xmlString.append("</row").append(row).append(
										">");
							}
						}
					}
				}
			}

			if (mode == SQLClient.CSV_EXCEL_DATA) {
				headerXmlString = xmlString;
			} else {
				headerXmlString = new StringBuffer("<dataSet count=\"" + row
						+ "\" tag=\"" + waterMark + "\">"
						+ System.getProperty("line.separator"));
				headerXmlString.append(xmlString.toString()).append(
						"</dataSet>");
			}
		} catch (final Exception e) {
			System.err.println("getStandardSQLData:1: " + e.getMessage());

			return StringUtil.toStringWidth(e.getMessage(),
					CLIENT_DEFAULT_LINE_WIDTH);
		}
		// finally {
		// disconnect(); // DB2
		// }

		// System.out.println("before XML formatting ... XML = '" + xmlString +
		// "'");

		// === remove count(*) from the tag name - applies to Oracle database
		// only
		String newXMLString = StringUtil.replace(headerXmlString.toString(),
				"count(*)", "count");

		currentDirAbsolutePath = StringUtil
				.handleEmptyString(currentDirAbsolutePath);

		final String tempXMLFile = currentDirAbsolutePath + "rawXML.xml";
		StringUtil.writeIntoFile(tempXMLFile, newXMLString, false);

		newXMLString = handleSQLValue(newXMLString);

		System.out.println("MODE IS " + mode);
		
		if (mode == SQLClient.CSV_EXCEL_DATA) {
			retVal = newXMLString;
//			System.out.println("mode 1");
		} else if (mode == SQLClient.DISPLAY_RAW_XML) {
			retVal = CommonUtil.safeCDATAForXML(StringUtil.handleUnicode(newXMLString));
//			System.out.println("mode 2");
		} else {
			retVal = formatXML(CommonUtil.safeCDATAForXML(StringUtil.handleUnicode(newXMLString)), "UTF-16");
//			System.out.println("mode 3");
		}

		return retVal;
	}

	public static String encodeSQLForwardSlashes(final String sqlScript) {
		return StringUtil.replace(sqlScript, "\\/", "SQL_FORWARD_SLASH");
	}

	public static String decodeSQLForwardSlashes(final String sqlScript) {
		return StringUtil.replace(sqlScript, "SQL_FORWARD_SLASH", "/");
	}

	public static String getErrorMessageString(final String[][] message) {
		// System.out.println("error message is '" + message[0][0] + "'");
		return message[0][0];
	}

	public static String formatXML(final String xmlString,
			final String encodingFormat) throws Exception {
		String retVal = xmlString;
		
		if(formatAllowed) {
			// Step 1: create a DocumentBuilderFactory
			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// dbf.setValidating(true);
	
			// Step 2: create a DocumentBuilder
			final DocumentBuilder db = dbf.newDocumentBuilder();
			final ByteArrayInputStream xmlStream = new ByteArrayInputStream(
					xmlString.getBytes());
	
			// Step 3: parse the input file to get a Document object
			final Document document = db.parse(xmlStream);
			db.setErrorHandler(new SQLMgr.ErrorHandler());
			final OutputFormat format = new OutputFormat(document);
	
			// === start - these settings are important, without them, formatting
			// won't work !!!
			format.setLineSeparator(LineSeparator.Windows);
			format.setIndenting(true);
	
			// format.setLineWidth(59);
			format.setLineWidth(CLIENT_DEFAULT_LINE_WIDTH);
			format.setIndent(3);
			// format.setEncoding("UTF-16");
			format.setEncoding(encodingFormat);
	
			// System.out.println("XML line width set to '" + format.getLineWidth()
			// + "'");
	
			// format.setPreserveSpace(true); //don't use this - ugly output !!!
	
			// === end - these settings are important, without them, formatting
			// won't work !!!
			final StringWriter stringOut = new StringWriter();
			final XMLSerializer serial = new XMLSerializer(stringOut, format);
			serial.asDOMSerializer();
			serial.serialize(document.getDocumentElement());
			retVal = stringOut.toString();
		}
		
		return retVal;
	}

	private static String handleSQLValue(final String str) {
		String retVal = str;

		if (str != null) {
			retVal = str;
			// return URLEncoder.encode(retVal);
			retVal = StringUtil.replace(retVal.toString(), "count(*)", "count");
			retVal = StringUtil.replace(retVal.toString(), "COUNT(*)", "count");
			// === TBD - these supposed to be fine-grained and not supposed to
			// be here !!!
			retVal = StringUtil.replace(retVal.toString(), "&amp;",
					"-REPLACED_AMP-");
			retVal = StringUtil.replace(retVal.toString(), "&apos;",
					"-REPLACED_APOS-");
			retVal = StringUtil.replace(retVal.toString(), "&quot;",
					"-REPLACED_QUOT-");
			retVal = StringUtil
					.replace(retVal.toString(), ";", "-REPLACED_SC-");
		}

		return retVal;
	}

	public static String handleXMLDataInHTML(final String xmlValue) {
		String retVal = null;
		if ((xmlValue != null) && !xmlValue.trim().equals("")) {
			retVal = safeCDATAForXML(xmlValue);
		}
		return retVal;
	}

	public static String getCLOBString(final Clob clobObj) throws Exception {
		String retVal = "";
		int i = -1;

		if (clobObj != null) {
			int lineChar = -1;

			try {
				final BufferedReader in = new BufferedReader(clobObj
						.getCharacterStream());
				final long maxLength = clobObj.length();

				// System.out.println("getCLOBString: clobLength = '" +
				// maxLength + "'");
				for (i = 0; i < maxLength; i++) {
					lineChar = in.read();
					if (i == maxLength) {
						break;
					}
					retVal += (char) lineChar;
				}
				in.close();
			} catch (final Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		return retVal;
	} // end of method

	public static String getBLOBString(final Blob blobObj) throws Exception {
		String retVal = "";

		if (blobObj != null) {
			try {
				final byte[] bdata = blobObj
						.getBytes(1, (int) blobObj.length());
				retVal = new String(bdata);
			} catch (final Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		return retVal;
	} // end of method

	// not working - "Invalid byte 2 of 4-byte UTF-8 sequence." exception !!!
	public static String safeCDATAForXML(String charData) {
		try {
			// == see if it is safe without CDATA tag
			CommonUtil.formatXML("<?xml version=\"1.0\"?><root>" + charData
					+ "</root>", "UTF-8");
		} catch (final Exception e) {
			charData = "<![CDATA[" + charData + "]]>";
		}

		return charData;
	}

	public static String handleLOBValue(final Object obj) throws Exception {
		String retVal = "";

		try {
			if (obj instanceof java.sql.Clob || obj instanceof oracle.sql.CLOB) {
				// System.out.println("clob to string ...");
				retVal = getCLOBString((Clob) obj);
			} else if (obj instanceof java.sql.Blob
					|| obj instanceof oracle.sql.BLOB) {
				// System.out.println("blob to string ...");
				retVal = getBLOBString((Blob) obj);
			} else {
				// System.out.println("unknown type to string ...");
				retVal = String.valueOf(obj);
			}
		} catch (final Exception e) {
			e.printStackTrace();
			throw e;
		}

		return retVal;
	}

}
