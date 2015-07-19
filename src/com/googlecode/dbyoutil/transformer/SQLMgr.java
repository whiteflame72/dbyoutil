package com.googlecode.dbyoutil.transformer;

/*******************************************************************************
 * Copyright (C) 2006-2002 James Clarke
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 * 
 * Filename : SQLProvider.java Description : To provide SQL to XML bridge to RDBMS. This
 * class is fully tested on MS Internet Explorer 5.5/6.0 only. 
 * Change Log : Date Ver# Description
 * ---------------------------------------------------------------------------------
 * 8/2/2002 	1.0 Initial Creation. 
 * 11/7/2002 	1.01 Provide error/no record found
 * message back to SOAP client as String array of arrays. 
 * 12/6/2002 	1.02 Expose insert/update/delete function to SOAP client. 
 * 3/25/2003 	1.03 Support generic
 * column based search and replace function - this feature seems buggy and not
 * fully tested; looks like it always does not fully update all rows. Workaround
 * is to run it more than once. 
 * 3/31/2003 	1.05 Make use of
 * replaceXMLReservedChars to filter away reserved characters in returned XML
 * data of getSQLData in HTML mode. 
 * 1/4/2003 	Add formatXML() before returning to
 * tidy up XML in getSQLData. 
 * 10/4/2003 	1.06 Add
 * NO_COLUMN_NAME_COLUMN_TYPE_AND_HTML_DATA mode for getSQLData.
 * 
 * 11/4/2003 	1.07 Implement getNonStandardSQLData/getStandardSQLData. But doing
 * "desc" on a table that does not have data may not work (have not tested it
 * yet though).
 * 
 * 23/4/2003 	1.08 Currently forward slashes within a script has to be escaped
 * using backward slashes - to to figure out why StringTokenizer failed to
 * separate the string.
 * 
 * 25/11/2004 	1.09 Support UTF-16 character set. This will allow the XML to be
 * rendered properly if the stream contain foreign character.
 * 
 * 29/11/2004 	1.10 Support comment in sql script (in readFileData).
 * 
 * 3/12/2004 	1.11 Support CLOB data type.
 * 
 * 6/23/2007	1.2	MS Excel output support.
 * 
 * 7/27/2007	1.3	Support BLOB data type (automatic conversion to text).
 *
 * 1/23/2008	1.5 Using CachedRowSet for paging data support.
 * 
 * 4/20/2010	1.6 Support raw XML (unformatted XML). This output XML will not be not well-formed XML.
 * 
 * Bug fixes
 * ---------------------------------------------------------------------------------
 * DB1 1/13/2003 Close cursor when done to avoid "Function sequence error" in MS
 * Access database. 
 * DB2 1/31/2003 Compromize functionality over performance if
 * it is MS Access database by always closing database connection. 
 * DB3 4/2/2003  Temporary not closing the connection to support multiple database support in
 * ControllerService.
 * DB4 4/4/2003  Added handleXMLDataInHTML before returning in
 * setSQLData.
 * DB5 6/4/2008  Open/close connection if MS Access (this means DB3 is not valid in this case)
 ******************************************************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.util.StringUtils;

import com.googlecode.dbyoutil.tool.CommonUtil;
import com.googlecode.dbyoutil.tool.Spreadsheet;
import com.googlecode.dbyoutil.tool.StringUtil;

public abstract class SQLMgr {
	public static final String systemMessageStartLabel = ">>>>>>>>>>>>>>>>>>";

	public static final String systemMessageEndLabel = "<<<<<<<<<<<<<<<<<<";

	private static Connection con = null;

	private static Statement stmt = null;

	private static ResultSet rs = null;

	private static String dbUrl = null;

	private static String dbDriver = null;

	private static String userName = null;

	private static String userPassword = null;

	private static boolean driverLoaded = false;

	public static final int DATABASE = CommonUtil.MS_ACCESS;

	public static Connection getCon() {
		return con;
	}

	public static Statement getStmt() {
		return stmt;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public void setDBUserName(final String userName) {
		this.userName = userName;
	}

	public void setDBUserPassword(final String userPassword) {
		this.userPassword = userPassword;
	}

	public void setDBUrl(final String url) {
		dbUrl = url;
	}

	public void setDBDriver(final String driver) {
		dbDriver = driver;
	}

	public static String[][] connect() throws Exception {
		String[][] retVal = null;
		if (DATABASE == CommonUtil.MS_ACCESS) {
			retVal = loadDBDriver();
			// System.out.println("200 ret = '" + retVal + "'");
		}
		return retVal;
	}

	// DB2
	public void disconnect() throws Exception {
		if (DATABASE == CommonUtil.MS_ACCESS) {
			unLoadDBDriver(); // DB3
		}
	}

	public String[][] loadDBDriver(String dbDriver, String dbUrl,
			String userName, String userPassword) throws Exception {
		this.dbDriver = dbDriver;
		this.dbUrl = dbUrl;
		this.userName = userName;
		this.userPassword = userPassword;

		return loadDBDriver();
	}

	public static String[][] loadDBDriver() throws Exception {
		try {
			if (!driverLoaded) {
				Class.forName(dbDriver).newInstance();
				System.out.println("driver '" + dbDriver + "' loaded.");
				con = DriverManager
						.getConnection(dbUrl, userName, userPassword);
				stmt = con.createStatement();
				con.setAutoCommit(true); // DB5
			}

			// use JDBC 2.0 feature

			// stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			// ResultSet.CONCUR_READ_ONLY);

			// stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			// ResultSet.CONCUR_READ_ONLY);

			// stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			// ResultSet.CONCUR_UPDATABLE);

			// stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			// ResultSet.CONCUR_UPDATABLE);
		} catch (final Exception e) {
			driverLoaded = false;
			e.printStackTrace();
			return createErrorMessage(e.getMessage());
		}

		driverLoaded = true;

		return CommonUtil.SYSTEM_ERROR;
	}

	private boolean isMSAccess(final String dbUrl) {
		boolean retVal = false;

		if (dbUrl != null) {
			if (dbUrl.indexOf("Microsoft Access Driver") > -1)
				retVal = true;
		}

		return retVal;
	}

	private static boolean isCommentLine(final String sqlCommand) {
		boolean retVal = false;

		// System.out.println("sqlCommand '" + sqlCommand + "'");

		if (sqlCommand != null && (sqlCommand.trim().startsWith("--") 
		//		|| sqlCommand.trim().indexOf("/") > -1
		)) {
			// System.out.println("pos -- is '" +
			// sqlCommand.trim().indexOf("--") + "'");
			retVal = true;
		}

		return retVal;
	}

	public static String readSQLFileData(final String fileName)
			throws Exception {
		String retVal = null;
		final StringBuffer output = new StringBuffer();
		String line;
		int index = -1;
		CommonUtil.sqlName = new ArrayList<String>();
		String temp = null;

		try {
			final BufferedReader in = new BufferedReader(new FileReader(
					fileName));
			while ((line = in.readLine()) != null) {
				line = line.trim();
				// === handle comment
				if (!isCommentLine(line)) {
					temp = getSQLName(line);
					if (!"".equals(temp)) {
						CommonUtil.sqlName.add(temp);
					}
					index = line.indexOf("*/");
					if (index > -1)
						index = index + 2;
					else
						index = 0;
					output.append(line.substring(index, line.length())
							+ System.getProperty("line.separator"));
				} else {
					output.append(CommonUtil.SQL_DELIMITER);
				}
			}
			in.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		if (output != null) {
			retVal = output.toString();
		} else {
			retVal = "";
		}
		return retVal;
	}

	/** supposed to support comments within SQL !!! */
	public static String readSQLFileData_Buggy(final String fileName)
			throws Exception {
		String retVal = null;
		final StringBuffer output = new StringBuffer();
		String line;
		int index = -1;
		CommonUtil.sqlName = new ArrayList<String>();
		String temp = null;

		try {
			final BufferedReader in = new BufferedReader(new FileReader(
					fileName));
			while ((line = in.readLine()) != null) {
				line = line.trim();
				// === handle comment
				if (!isCommentLine(line)) {
					temp = getSQLName(line);
					if (!"".equals(temp)) {
						CommonUtil.sqlName.add(temp);
					}
					index = line.indexOf("*/");
					if (index > -1)
						index = index + 2;
					else
						index = 0;

					int index0 = line.indexOf("/*");
					if (index > -1 && index0 > -1) {
						String beginStr = line.substring(0, index0);
						String endStr = line.substring(index, line.length());

						output.append(beginStr + endStr
								+ System.getProperty("line.separator"));
					} else {
						output.append(line
								+ System.getProperty("line.separator"));
					}
				} else {
					output.append(CommonUtil.SQL_DELIMITER);
				}
			}
			in.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		if (output != null) {
			retVal = output.toString();
		} else {
			retVal = "";
		}

		return retVal;
	}

	/**
	 * This is the first entry method of all for XML output.
	 */
	public String getXMLData(final int mode, final String sql) throws Exception {
//		CommonUtil.setWaterMark(CommonUtil.sqlName.get(0) + " #" + CommonUtil.getWaterMark() + "#");
		CommonUtil.setWaterMark(sql + " #" + CommonUtil.getWaterMark() + "#");

		String retVal = getIntermediateXMLData(mode, sql);

		retVal = "<?xml version=\"1.0\" encoding=\"UTF-16\"?>"
				+ System.getProperty("line.separator") + "<dataEnvelope>"
				+ System.getProperty("line.separator") + retVal
				+ "</dataEnvelope>";

		return retVal;
	}

	/**
	 * This is the first entry method of all for XML output.
	 */
	private String getIntermediateXMLData(final int mode, String sql)
			throws Exception {
		String retVal = "";

		// System.out.println("SQL command '" + sql + "'");
		if (sql != null && !sql.trim().equals("")) {
			final int descCommandLength = 5;
			String currentCommandGrabbed = sql.trim().substring(0,
					descCommandLength);

			if (sql.length() >= descCommandLength
					&& currentCommandGrabbed.trim().startsWith("desc")) {
				// logger.info("processing non-standard compliant command '" +
				// sql + "' ...");
				final String param = sql.trim().substring(descCommandLength,
						sql.trim().length());

				int dbType = -1;
				if (isMSAccess(dbUrl)) {
					dbType = CommonUtil.MS_ACCESS;
				}
				// === support advanced "descv" (all tables)
				if (sql != null && sql.trim().startsWith("desc")
						&& sql.contains("*")) {
					List tbl = CommonUtil.getTables(SQLMgr.getCon());
					System.out.println("\nTotal table = " + tbl.size());
					String tblName = null;
					Iterator iter = tbl.iterator();
					while (iter.hasNext()) {
						tblName = (String) iter.next();
						CommonUtil.setCurrentTable(tblName);
						retVal += CommonUtil.getNonStandardSQLData(dbType,
								stmt, rs, mode, currentCommandGrabbed.trim(),
								tblName, sql);
					}
				} else {

					retVal = CommonUtil.getNonStandardSQLData(dbType, stmt, rs,
							mode, currentCommandGrabbed.trim(), param, sql);
				}
			} else {
				// === support multiple SQLs concurrently in one sql string
				if (sql != null && sql.trim().length() > 1
						&& sql.indexOf(CommonUtil.SQL_DELIMITER) > -1) {
					sql = CommonUtil.encodeSQLForwardSlashes(sql); // will be
					// removed
					// in
					// the future once
					// StringTokenizer bug
					// is fixed !?

					final StringTokenizer st = new StringTokenizer(sql,
							CommonUtil.SQL_DELIMITER); // buggy
					// if SQL contains "/" as part of command not script
					// delimiter !!!

					// String[] st = StringUtils.split(sql, sqlDelimiter);
					retVal = "";

					while (st.hasMoreTokens()) {
						currentCommandGrabbed = st
								.nextToken(CommonUtil.SQL_DELIMITER);
						currentCommandGrabbed = CommonUtil
								.decodeSQLForwardSlashes(currentCommandGrabbed); // will
						// be
						// removed
						// in
						// the
						// future
						// once
						// StringTokenizer
						// bug
						// is
						// fixed
						// !?

						retVal += getIntermediateXMLData(mode,
								currentCommandGrabbed);
					}

				} else {
					retVal = CommonUtil.getStandardSQLData(stmt, rs, mode, sql);
					retVal = StringUtils.replace(retVal,
							"<?xml version=\"1.0\" encoding=\"UTF-16\"?>", "");
				}
			}
		}

		return retVal;
	}

	/**
	 * This function assumes that the name is provided as sql comments limited
	 * by \/* *\/ and it is the same line as the SQL.
	 * 
	 * @param sql
	 * @return
	 */
	private static String getSQLName(final String sql) {
		String retVal = "";

		if (!CommonUtil.SQL_DELIMITER.trim().equals(sql))
			retVal = "Sheet";

		try {
			if (sql != null) {
				final int beginIndex = sql.indexOf("/*");
				final int endIndex = sql.indexOf("*/");

				if (beginIndex > -1 && endIndex > -1)
					retVal = sql.trim().substring(beginIndex + 2, endIndex - 1);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		retVal = retVal.trim();

		return retVal;
	}

	public String[][] getSQLData(final String sqlQuery) throws Exception {
		if ((sqlQuery == null) || sqlQuery.trim().equals("")) {
			throw new Exception("SQL Query string can't be NULL or empty.");
		}

		final String connectSstatus[][] = connect(); // DB2

		if (connectSstatus != CommonUtil.SYSTEM_ERROR) {
			return connectSstatus;
		}

		// note that we compromize thread-safety for performance by using
		// ArrayList :)
		final ArrayList<String[]> vData = new ArrayList<String[]>();
		int i;
		int row = 0;
		try {

			// retrieve resultset
			rs = stmt.executeQuery(sqlQuery);
			final ResultSetMetaData rsmd = rs.getMetaData();
			final int numberOfColumns = rsmd.getColumnCount();

			String[] rsData = null;

			while (rs.next()) {
				row++;
				rsData = new String[numberOfColumns];
				for (i = 1; i <= numberOfColumns; i++) {
					rsData[i - 1] = String.valueOf(rs.getObject(i));
				}
				vData.add(rsData);
			}

		} catch (final Exception e) {
			System.err.println("getSQLData:1: " + e.getMessage());

			// === reconnect for later use when it is up

			// loadDBDriver(); //DB2
			return createErrorMessage(e.getMessage());
		} finally {
			disconnect(); // DB2
		}

		// choose String array as the common denominator for SOAP client
		String[][] finalData = null;
		if (vData.size() == 0) {
			final String[][] data = {

			{ systemMessageStartLabel + " No record found. "
					+ systemMessageEndLabel } };

			// === just in case there is no record found
			finalData = data;
		} else {
			final String[][] data = new String[vData.size()][];
			finalData = data;
		}
		for (i = 0; i < vData.size(); i++) {
			finalData[i] = (String[]) vData.get(i);
		}
		return finalData;
	}

	/**
	 * Error handler for validating parser.
	 */
	public static class ErrorHandler implements org.xml.sax.ErrorHandler {
		/**
		 * Report the warning to the console.
		 */
		public void warning(final org.xml.sax.SAXParseException ex)
				throws org.xml.sax.SAXException {
			System.out.println("Warning: " + ex.getMessage());
		}

		/**
		 * Report the error to the console.
		 */
		public void error(final org.xml.sax.SAXParseException ex)
				throws org.xml.sax.SAXException {
			System.out.println("Error: " + ex.getMessage());
		}

		/**
		 * Report the fatal error to the console.
		 */
		public void fatalError(final org.xml.sax.SAXParseException ex)
				throws org.xml.sax.SAXException {
			System.out.println("Fatal error: " + ex.getMessage());
		}
	}

	public String setSQLData(final int mode, final String sql) throws Exception {
		int retVal = -1;
		if ((sql == null) | sql.equals("")) {
			throw new Exception("SQL action string can't be NULL or empty.");
		}
		final String connectSstatus[][] = connect(); // DB2
		if (connectSstatus != CommonUtil.SYSTEM_ERROR) {
			return StringUtil.toStringWidth(CommonUtil
					.getErrorMessageString(connectSstatus),
					CommonUtil.CLIENT_DEFAULT_LINE_WIDTH);
		}
		StringBuffer headerXmlString = null;
		final StringBuffer xmlString = new StringBuffer();
		try {

			retVal = stmt.executeUpdate(sql);

			final int i = 1;
//			if (mode == SQLClient.DISPLAY_COLUMN_NAME_AND_URL_ENCODE_DATA) {
//				xmlString.append("<column").append(i).append(">").append(
//						URLEncoder.encode(String.valueOf(retVal))).append(
//						"</column").append(i).append(">");
//			} else {
				if (mode == SQLClient.NO_COLUMN_NAME_AND_URL_ENCODE_DATA) {
					xmlString.append("<column").append(i).append(">").append(
							URLEncoder.encode(String.valueOf(retVal))).append(
							"</column").append(i).append(">");
				} else {
					if (mode == SQLClient.NO_COLUMN_NAME_AND_PURE_DATA) {
						xmlString.append("<column").append(i).append(">")
								.append(String.valueOf(retVal)).append(
										"</column").append(i).append(">");
					} else {
						if (mode == SQLClient.DISPLAY_COLUMN_NAME_AND_PURE_DATA) {
							xmlString.append("<column").append(i).append(">")
									.append(String.valueOf(retVal)).append(
											"</column").append(i).append(">");
						} else {
							if (mode == SQLClient.DISPLAY_COLUMN_NAME_AND_HTML_DATA) {
								xmlString.append("<column").append(i).append(
										">").append(
										CommonUtil.handleXMLDataInHTML(String
												.valueOf(retVal))).append(
										"</column").append(i).append(">");
							}
						}
					}
				}
//			}

			headerXmlString = new StringBuffer("<dataSet count=\"" + retVal
					+ "\" tag=\"" + CommonUtil.getWaterMark() + "\">"
					+ System.getProperty("line.separator"));
			headerXmlString.append(xmlString).append("</dataSet>");

		} catch (final Exception e) {
			System.out.println("setSQLData: " + e.getMessage());

			// === reconnect for later use when it is up

			// loadDBDriver(); //DB2
			return StringUtil.toStringWidth(e.getMessage(),
					CommonUtil.CLIENT_DEFAULT_LINE_WIDTH);
		} finally {
			disconnect(); // DB2
		}

		return headerXmlString.toString();
	}

	public String[][] setSQLData(final String sql) throws Exception {
		String retVal = null;

		if ((sql == null) | sql.equals("")) {
			throw new Exception("SQL action string can't be NULL or empty.");
		}

		final String connectSstatus[][] = connect(); // DB2
		if (connectSstatus != CommonUtil.SYSTEM_ERROR) {
			return connectSstatus;
		}
		try {
			retVal = String.valueOf(stmt.executeUpdate(sql));
		} catch (final Exception e) {
			System.err
					.println("Exception: " + e.getMessage() + ", SQL: " + sql);

			// === reconnect for later use when it is up

			// loadDBDriver(); //DB2
			return createErrorMessage(e.getMessage() + " for sql : " + sql);
		} finally {

			disconnect(); // DB2
		}
		final String[][] finalData = {

		{ systemMessageStartLabel + retVal + systemMessageEndLabel } };
		return finalData;
	}

	public String[][] unLoadDBDriver() throws Exception {

		// === close all connection objects
		try {
			if (rs != null) {
				rs.close(); // DB1 DB2
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (final Exception e) {
			System.err.println(e.getMessage());
			return createErrorMessage(e.getMessage());

			// throw e;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (final SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		return CommonUtil.SYSTEM_ERROR;
	}

	public static String[][] createErrorMessage(final String message) {
		final String[][] errMessage = {

		{ systemMessageStartLabel + " " + message + " " + systemMessageEndLabel } };
		return errMessage;
	}

	// public static String encloseCDATAForXML(String charData) {
	// try {
	// // == see if it is safe without CDATA tag
	// formatXML("<?xml version=\"1.0\" encoding=\"UTF-16\"?><root>" + charData
	// + "</root>", "UTF-16");
	// } catch (Exception e) {
	// charData = "<![CDATA[" + charData + "]]>";
	// }
	//
	// return charData;
	// }

	public byte[] getBinaryData(final int mode, String sql,
			final Spreadsheet wb, final String wbName, boolean pagedExcelSheet)
			throws Exception {
		return CommonUtil.getBinaryData(stmt, rs, mode, sql, wb, wbName,
				pagedExcelSheet);
	}
}
