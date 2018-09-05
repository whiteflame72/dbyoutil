package com.googlecode.dbyoutil.transformer;

/*********************************************************************************
 Copyright (C) 2004-2002  James Clarke

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *********************************************************************************/

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.googlecode.dbyoutil.tool.CommonUtil;
import com.googlecode.dbyoutil.tool.Spreadsheet;
import com.googlecode.dbyoutil.tool.StringUtil;

import file.util.FileUtil;

public class SQLClient {
	public static final String buildNum = "RC_6.3.5.3";

	private static String stat = "";

	private static byte[] bin = null;

	private static final String SEARCH_AND_REPLACE_MODE = "replace";

	public static final int NO_COLUMN_NAME_AND_PURE_DATA = 0;

	public static final int NO_COLUMN_NAME_AND_URL_ENCODE_DATA = 1;

	public static final int NO_COLUMN_NAME_AND_HTML_DATA = 2;

	public static final int DISPLAY_COLUMN_NAME_AND_PURE_DATA = 10;

//	public static final int DISPLAY_COLUMN_NAME_AND_URL_ENCODE_DATA = 11;

	public static final int DISPLAY_COLUMN_NAME_AND_HTML_DATA = 12; // default

	// public static final int DISPLAY_COLUMN_NAME_COLUMN_TYPE_AND_HTML_DATA =
	// 13;

	public static final int DISPLAY_RAW_XML = 22;

	public static final int CSV_EXCEL_DATA = 20;

	public static final int CSV_EXCEL_PAGING_DATA = 30;

	public static final int UPDATE_NO_COLUMN_NAME_AND_PURE_DATA = 100;

	/**
	 * The current path on the local system.
	 */
	private static File dir = new File(".");
	private static String currentDirAbsolutePath = "";

	public static void main(String[] args) {
		String strSQL = "";
		String driver = "";
		String dbUrl = "";
		String user = "";
		String pwd = "";
		String strAction = "";
		
		// === support column based search and replace function
		String searchAndReplaceTableName = null;
		String searchAndReplaceColumnName = null;
		String searchAndReplaceOldString = null;
		String searchAndReplaceNewString = null;

		// logger.info("DB2XML 1.0 instantiated.");
		try {
			currentDirAbsolutePath = dir.getCanonicalPath()
					+ System.getProperty("file.separator");
		} catch (IOException e) {
			e.printStackTrace();
		}

		SQLMgr dbXML = new DB2XML();
		//CommonUtil.setWaterMark("James Clarke " + CommonUtil.getWaterMark());
		
		// strSQL = "SELECT Title FROM Movies";
		if (args.length == 3) {
			System.out.println("DB driver '" + args[0] + "' url '" + args[1]
					+ "' SLQ QUERY is '" + args[2]
					+ "' No explicit SQL action specified.");
			driver = args[0];
			dbUrl = args[1];
			user = "";
			pwd = "";
			strSQL = args[2];
			strAction = "";
		} else {
			if (args.length == 6) {
				System.out.println("DB driver '" + args[0] + "' url '"
						+ args[1] + "' SLQ QUERY is '" + args[2] + "' action '"
						+ args[3] + "'");
				driver = args[0];
				dbUrl = args[1];
				user = args[2];
				pwd = args[3];
				strSQL = args[4];
				strAction = args[5];
				
				//=== just for the test - to comment out before release
				//MS Access
//				strSQL = "descv movies";
//				driver = "sun.jdbc.odbc.JdbcOdbcDriver";
//				dbUrl = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=/share/allmovies5.mdb";
//				user = "user";
//				pwd = "pass";

				//MS SQL
//				driver = "net.sourceforge.jtds.jdbc.Driver";
//				dbUrl = "jdbc:jtds:sqlserver://localhost:1333/dev;instance=MSSQL";
//				user = "sa";
//				pwd = "test123";
//				
//				strSQL = "C:/DBUtil/sql/dw-vibu2.txt";

				if ((strAction != null)
						&& strAction.equals(SEARCH_AND_REPLACE_MODE)) {
					StringTokenizer st = new StringTokenizer(strSQL);
					System.out.println("replace mode: strSQL = '" + strSQL
							+ "'");
					searchAndReplaceTableName = st.nextToken();
					searchAndReplaceColumnName = st.nextToken();
					searchAndReplaceOldString = URLDecoder.decode(st
							.nextToken());
					searchAndReplaceNewString = URLDecoder.decode(st
							.nextToken());
				}
			} else {
				System.out
						.println("Error: Parameters should be 6 but only "
								+ args.length
								+ " is supplied, it should be in the following format :");
				System.out
						.println("DB2XML jdbcDriver dbUrl dbUserID dbUserPassword SLQString/filename SQLAction where SQLString is [SQL]/[table_name column_name old_string new_string] and SQLAction is 0,1,10,11,12,13/replace.");
			}
		}

		try {
			if (StringUtil.isAFile(strSQL)) {
				System.out
						.println("strSQL is a file, so reading SQL from file ...");
				strSQL = SQLMgr.readSQLFileData(strSQL);
				System.out.println("strSQL is = '" + strSQL + "'");
			}
		} catch (Exception e) {
			// do nothing
		}

		System.out.println("SQL action is = '" + strAction + "'");

		try {
			dbXML.setDBDriver(driver);
			dbXML.setDBUrl(dbUrl);
			dbXML.setDBUserName(user);
			dbXML.setDBUserPassword(pwd);
			dbXML.loadDBDriver();
			String[][] data = null;
			System.out.println("Processing ... (started "
					+ FileUtil.getCurrentTime() + ", may take a while)");

			if ((strAction != null)
					&& strAction.equals(String.valueOf(CSV_EXCEL_PAGING_DATA))) {
				Spreadsheet wb = new Spreadsheet();
				bin = dbXML.getBinaryData(CSV_EXCEL_PAGING_DATA, strSQL, wb,
						"Sheet", true);

				String finalXMLFile = currentDirAbsolutePath + "getSQLData.xls";
				StringUtil.writeIntoFile(finalXMLFile, bin, false);
				StringUtil.showXML(currentDirAbsolutePath, finalXMLFile);
			} else if ((strAction != null)
					&& strAction.equals(String.valueOf(CSV_EXCEL_DATA))) {
				byte[] bin = null;
				Spreadsheet wb = new Spreadsheet();
				bin = dbXML.getBinaryData(CSV_EXCEL_DATA, strSQL, wb, "Sheet",
						true);

				String finalXMLFile = currentDirAbsolutePath + "getSQLData.xls";
				StringUtil.writeIntoFile(finalXMLFile, bin, false);
				StringUtil.showXML(currentDirAbsolutePath, finalXMLFile);
			} else if ((strAction != null)
					&& strAction.equals(String
							.valueOf(NO_COLUMN_NAME_AND_PURE_DATA))) {
				stat = dbXML.getXMLData(NO_COLUMN_NAME_AND_PURE_DATA, strSQL);

				// stat = String.valueOf(dbXML.setSQLData(strSQL));
				String finalXMLFile = currentDirAbsolutePath + "getSQLData.txt";
				StringUtil.writeIntoFile(finalXMLFile, stat, false);
				StringUtil.showXML(currentDirAbsolutePath, finalXMLFile);
			} else {
				if ((strAction != null)
						&& strAction.equals(String
								.valueOf(NO_COLUMN_NAME_AND_URL_ENCODE_DATA))) {
					stat = dbXML.getXMLData(NO_COLUMN_NAME_AND_URL_ENCODE_DATA,
							strSQL);

					// stat = String.valueOf(dbXML.setSQLData(strSQL));
					String finalXMLFile = currentDirAbsolutePath
							+ "getSQLData.txt";
					StringUtil.writeIntoFile(finalXMLFile, stat, false);
					StringUtil.showXML(currentDirAbsolutePath, finalXMLFile);

					/*
					 * } else if ((strAction != null) &&
					 * strAction.equals(String.valueOf(DISPLAY_COLUMN_NAME_AND_URL_ENCODE_DATA))) {
					 * //stat =
					 * dbXML.getSQLData(DB2XML.DISPLAY_COLUMN_NAME_AND_URL_ENCODE_DATA,
					 * strSQL); data = dbXML.getSQLData(strSQL);
					 * 
					 * String[] subData = null; StringBuffer str = new
					 * StringBuffer();
					 * 
					 * for (int i = 0; i < data.length; i++) {
					 * ////logger.info("row" + i); str.append("row").append(i +
					 * 1).append(System.getProperty("line.separator")); subData =
					 * data[i];
					 * 
					 * for (int j = 0; j < subData.length; j++) {
					 * ////logger.info(" col" + (j+1) + " '" + subData[j] +
					 * "'"); str.append(" col").append(j + 1).append(" '")
					 * .append(subData[j]).append("'").append(System.getProperty("line.separator")); } }
					 * 
					 * stat = str.toString();
					 * 
					 * String finalXMLFile = dbXML.currentDirAbsolutePath +
					 * "getSQLData.txt"; StringUtil.writeIntoFile(finalXMLFile,
					 * stat, false); StringUtil.showXML(currentDirAbsolutePath,
					 * finalXMLFile); } else if ((strAction != null) &&
					 * strAction.equals(String.valueOf(DISPLAY_COLUMN_NAME_AND_PURE_DATA))) {
					 * //stat =
					 * dbXML.getSQLData(DB2XML.DISPLAY_COLUMN_NAME_AND_PURE_DATA,
					 * strSQL);
					 * dbXML.setDefaultMode(DISPLAY_COLUMN_NAME_AND_PURE_DATA);
					 * data = dbXML.getSQLData(strSQL);
					 * 
					 * String[] subData = null; StringBuffer str = new
					 * StringBuffer();
					 * 
					 * for (int i = 0; i < data.length; i++) {
					 * ////logger.info("row" + i); str.append("row").append(i +
					 * 1).append(System.getProperty("line.separator")); subData =
					 * data[i];
					 * 
					 * for (int j = 0; j < subData.length; j++) {
					 * ////logger.info(" col" + (j+1) + " '" + subData[j] +
					 * "'"); str.append(" col").append(j + 1).append(" '")
					 * .append(subData[j]).append("'").append(System.getProperty("line.separator")); } }
					 * 
					 * stat = str.toString();
					 * 
					 * String finalXMLFile = dbXML.currentDirAbsolutePath +
					 * "getSQLData.txt"; StringUtil.writeIntoFile(finalXMLFile,
					 * stat, false); StringUtil.showXML(currentDirAbsolutePath,
					 * finalXMLFile);
					 */
				} else {
					if ((strAction != null)
							&& strAction.equals(String
									.valueOf(NO_COLUMN_NAME_AND_HTML_DATA))) {
						stat = String.valueOf(dbXML.getXMLData(
								NO_COLUMN_NAME_AND_HTML_DATA, strSQL));
						String finalXMLFile = currentDirAbsolutePath
								+ "getSQLData.txt";
						StringUtil.writeIntoFile(finalXMLFile, stat, false);
						StringUtil
								.showXML(currentDirAbsolutePath, finalXMLFile);
					} else {
						if ((strAction != null)
								&& strAction
										.equals(String
												.valueOf(DISPLAY_COLUMN_NAME_AND_HTML_DATA))) {
							stat = dbXML.getXMLData(
									DISPLAY_COLUMN_NAME_AND_HTML_DATA, strSQL);
							String finalXMLFile = currentDirAbsolutePath
									+ "getSQLData.xml";
							StringUtil.writeIntoFile(finalXMLFile, stat, false);
							StringUtil.showXML(currentDirAbsolutePath,
									finalXMLFile);
						} else {
							if ((strAction != null)
									&& strAction
											.equals(String
													.valueOf(DISPLAY_COLUMN_NAME_AND_PURE_DATA))) {
								stat = dbXML.getXMLData(
										DISPLAY_COLUMN_NAME_AND_PURE_DATA,
										strSQL);
								String finalXMLFile = currentDirAbsolutePath
										+ "getSQLData.xml";
								StringUtil.writeIntoFile(finalXMLFile, stat,
										false);
								StringUtil.showXML(currentDirAbsolutePath,
										finalXMLFile);
							} else {
								if ((strAction != null)
										&& strAction
												.equals(String
														.valueOf(UPDATE_NO_COLUMN_NAME_AND_PURE_DATA))) {
									stat = dbXML
											.setSQLData(
													UPDATE_NO_COLUMN_NAME_AND_PURE_DATA,
													strSQL);
									String finalXMLFile = currentDirAbsolutePath
											+ "getSQLData.xml";
									StringUtil.writeIntoFile(finalXMLFile,
											stat, false);
									StringUtil.showXML(currentDirAbsolutePath,
											finalXMLFile);
								} else {
									if ((strAction != null)
											&& strAction
													.equals(SEARCH_AND_REPLACE_MODE)) {

										String finalSelectSQLString = "select "
												+ searchAndReplaceColumnName
												+ " from "
												+ searchAndReplaceTableName
												+ " where "
												+ searchAndReplaceColumnName
												+ " like " + "'%"
												+ searchAndReplaceOldString
												+ "%'";
										data = dbXML
												.getSQLData(finalSelectSQLString);
										if (data != null) {
											String[] oldColumnString = new String[data.length];
											String[] newColumnString = new String[data.length];
											String finalUpdateSQLString = null;
											for (int i = 0; i < data.length; i++) {
												oldColumnString[i] = data[i][0];
												newColumnString[i] = StringUtil
														.replace(
																oldColumnString[i],
																searchAndReplaceOldString,
																searchAndReplaceNewString);
												finalUpdateSQLString = "update "
														+ searchAndReplaceTableName
														+ " set "
														+ searchAndReplaceColumnName
														+ " = '"
														+ newColumnString[i]
														+ "' where "
														+ searchAndReplaceColumnName
														+ " = '"
														+ oldColumnString[i]
														+ "'";
												stat = String
														.valueOf(dbXML
																.setSQLData(finalUpdateSQLString));
											}
											StringBuffer headerXmlString = new StringBuffer();
											headerXmlString
													.append(
															"<dataSet count=\""
																	+ data.length
																	+ "\" tag=\""
																	+ CommonUtil.getWaterMark()
																	+ "\">")
													.append(
															System
																	.getProperty("line.separator"))
													.append("</dataSet>");
											String results = data.length
													+ " row(s) are updated in search and replace mode.";
											String finalXMLFile = currentDirAbsolutePath
													+ "getSQLData.xml";
											StringUtil.writeIntoFile(
													finalXMLFile,
													headerXmlString.toString(),
													false);
											StringUtil.showXML(
													currentDirAbsolutePath,
													finalXMLFile);
											System.out.println(results);
										}
									} else {
										stat = dbXML
												.getXMLData(
														DISPLAY_COLUMN_NAME_AND_HTML_DATA,
														strSQL);
										String finalXMLFile = currentDirAbsolutePath
												+ "getSQLData.xml";
										StringUtil.writeIntoFile(finalXMLFile,
												stat, false);
										if(strAction.equals(DISPLAY_RAW_XML)) {
											finalXMLFile = currentDirAbsolutePath
											+ "rawXML.xml";
										}
										StringUtil.showXML(
												currentDirAbsolutePath,
												finalXMLFile);
									}
								}
							}
						}
					}
				}
			}

			// //logger.info(stat);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("BUILD [" + buildNum + "] done.");
	}

	public static String getStat() {
		return stat;
	}

	public static byte[] getBin() {
		return bin;
	}

}
