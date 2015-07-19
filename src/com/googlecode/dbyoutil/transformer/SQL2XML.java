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

import com.googlecode.dbyoutil.tool.CommonUtil;

public class SQL2XML {
	public static final String buildNum = "1.0";

	private static final String SEARCH_AND_REPLACE_MODE = "replace";
    //private static String sqlDelimiter = System.getProperty("line.separator") + "/";
	public static final int NO_COLUMN_NAME_AND_PURE_DATA = 0;

	public static final int NO_COLUMN_NAME_AND_URL_ENCODE_DATA = 1;

	public static final int NO_COLUMN_NAME_AND_HTML_DATA = 2;

	public static final int DISPLAY_COLUMN_NAME_AND_PURE_DATA = 10;

	public static final int DISPLAY_COLUMN_NAME_AND_URL_ENCODE_DATA = 11;

	public static final int DISPLAY_COLUMN_NAME_AND_HTML_DATA = 12; // default

	// public static final int DISPLAY_COLUMN_NAME_COLUMN_TYPE_AND_HTML_DATA =
	// 13;
	public static final int UPDATE_NO_COLUMN_NAME_AND_PURE_DATA = 100;

	//private static final String SEARCH_AND_REPLACE_MODE = "replace";
	/**
	 * The current path on the local system.
	 */
	public static File dir = new File(".");
	public static String currentDirAbsolutePath = "";

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
			if (isAFile(strSQL)) {
				System.out
						.println("strSQL is a file, so reading SQL from file ...");
				strSQL = DB2XML.readSQLFileData(strSQL);
				System.out.println("strSQL is = '" + strSQL + "'");
			}
		} catch (Exception e) {
			// do nothing
		}

		System.out.println("SQL action is = '" + strAction + "'");

		try {

			// dbXML.setDBDriver("sun.jdbc.odbc.JdbcOdbcDriver");

			// dbXML.setDBUrl("jdbc:odbc:sprData");

			// dbXML.setDBUrl("jdbc:odbc:test");
			dbXML.setDBDriver(driver);
			dbXML.setDBUrl(dbUrl);
			dbXML.setDBUserName(user);
			dbXML.setDBUserPassword(pwd);
			dbXML.loadDBDriver();
			String stat = "";
			String[][] data = null;
			if ((strAction != null)
					&& strAction.equals(String
							.valueOf(NO_COLUMN_NAME_AND_PURE_DATA))) {
				stat = dbXML.getXMLData(NO_COLUMN_NAME_AND_PURE_DATA,
						strSQL);

				// stat = String.valueOf(dbXML.setSQLData(strSQL));
				String finalXMLFile = currentDirAbsolutePath
						+ "getSQLData.txt";
				writeIntoFile(finalXMLFile, stat, false);
				showXML(finalXMLFile);
			} else {
				if ((strAction != null)
						&& strAction.equals(String
								.valueOf(NO_COLUMN_NAME_AND_URL_ENCODE_DATA))) {
					stat = dbXML.getXMLData(
							NO_COLUMN_NAME_AND_URL_ENCODE_DATA, strSQL);

					// stat = String.valueOf(dbXML.setSQLData(strSQL));
					String finalXMLFile = currentDirAbsolutePath
							+ "getSQLData.txt";
					writeIntoFile(finalXMLFile, stat, false);
					showXML(finalXMLFile);

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
					 * "getSQLData.txt"; writeIntoFile(finalXMLFile, stat,
					 * false); showXML(finalXMLFile); } else if
					 * ((strAction != null) &&
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
					 * "getSQLData.txt"; writeIntoFile(finalXMLFile, stat,
					 * false); showXML(finalXMLFile);
					 */
				} else {
					if ((strAction != null)
							&& strAction.equals(String
									.valueOf(NO_COLUMN_NAME_AND_HTML_DATA))) {
						stat = String.valueOf(dbXML.getXMLData(
								NO_COLUMN_NAME_AND_HTML_DATA, strSQL));
						String finalXMLFile = currentDirAbsolutePath
								+ "getSQLData.txt";
						writeIntoFile(finalXMLFile, stat, false);
						showXML(finalXMLFile);
					} else {
						if ((strAction != null)
								&& strAction
										.equals(String
												.valueOf(DISPLAY_COLUMN_NAME_AND_HTML_DATA))) {
							stat = dbXML.getXMLData(
									DISPLAY_COLUMN_NAME_AND_HTML_DATA,
									strSQL);
							String finalXMLFile = currentDirAbsolutePath
									+ "getSQLData.xml";
							writeIntoFile(finalXMLFile, stat, false);
							showXML(finalXMLFile);
						} else {
							if ((strAction != null)
									&& strAction
											.equals(String
													.valueOf(DISPLAY_COLUMN_NAME_AND_PURE_DATA))) {
								stat = dbXML
										.getXMLData(
												DISPLAY_COLUMN_NAME_AND_PURE_DATA,
												strSQL);
								String finalXMLFile = currentDirAbsolutePath
										+ "getSQLData.xml";
								writeIntoFile(finalXMLFile, stat, false);
								showXML(finalXMLFile);
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
									writeIntoFile(finalXMLFile, stat, false);
									showXML(finalXMLFile);
								} else {
									if ((strAction != null)
											&& strAction
													.equals(SEARCH_AND_REPLACE_MODE)) {

										// ArrayList results = new ArrayList();

										// results.add(dbXML.getSQLData(strSQL));
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
												newColumnString[i] = replace(
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
											writeIntoFile(finalXMLFile,
													headerXmlString.toString(),
													false);
											showXML(finalXMLFile);
											System.out.println(results);
										}
									} else {
										stat = dbXML
												.getXMLData(
														DISPLAY_COLUMN_NAME_AND_HTML_DATA,
														strSQL);
										String finalXMLFile = currentDirAbsolutePath
												+ "getSQLData.xml";
										writeIntoFile(finalXMLFile, stat, false);
										showXML(finalXMLFile);
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

		System.out.println("BUILD [" + buildNum + "]");
	}

	public static boolean isAFile(String value) throws Exception {
		boolean aFile = true;
		try {
			new FileReader(value);
		} catch (Exception e) {
			aFile = false;

			// System.out.println("Exception in isAFile, e: " + e);
		}
		return aFile;
	}

	public static String encodeXMLReservedCharsForHTML(String charData) {

		// replace "&" first because that is contained in other values
		charData = replace(charData, "&", "&amp;");
		charData = replace(charData, "\'", "&apos;");
		charData = replace(charData, "\"", "&quot;");
		charData = replace(charData, "<", "&lt;");
		charData = replace(charData, ">", "&gt;");
		return charData;
	}

	public static int showXML(String fileName) {
		String externalScriptLauncher = currentDirAbsolutePath
				+ "launch_external.bat ";
		Runtime rt = Runtime.getRuntime();
		String command = externalScriptLauncher + fileName;
		Process p = null;
		int num = 999;
		try {
			p = rt.exec(command);
			num = p.waitFor();
		} catch (Exception e) {

			// if (logger != null) {

			// logger.info("Exception: " + e);

			// }
		}

		// if (logger != null) {

		// logger.info("----------------> executing external command \'" +
		// command + "\' ");

		// logger.info(fileName + " displayed, exit status = " + num);

		// }
		return num;
	}
	
    public static void writeIntoFile(String fileName, String msg, boolean appendFlag)
    {
        try
        {
	    //=== support foreign character in stream too (UTF-16)
   	    OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileName, appendFlag), "UTF-16");
            out.write(msg, 0, msg.length());
            out.flush();
            out.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

	/**
	 * This function replaces all occurrences of toReplace with replaceWith in
	 * value
	 */

	public static String replace(String value, String toReplace,
			String replaceWith) {
		boolean done = false;
		StringBuffer sb;
		String before;
		String after;
		int pos = 0;
		while (!done) {
			pos = value.indexOf(toReplace, pos);
			if (pos == -1) {
				done = true;
			} else {
				done = false;
				sb = new StringBuffer(value);

				// insert replaceWith
				sb.insert(pos + toReplace.length(), replaceWith);

				// remove toReplace
				before = sb.toString().substring(0, pos);
				after = sb.toString().substring(pos + toReplace.length());
				value = before + after;
				pos = pos + replaceWith.length();
			}
		}
		return value;
	}


	/*
	 * //=== assumes next() has been called already static public Object[]
	 * resultSetToArray(ResultSet rs) throws SQLException { ResultSetMetaData
	 * meta = rs.getMetaData(); int sz = meta.getColumnCount(); Object[] objs =
	 * new Object[sz]; for(int i=0; i<sz; i++) { Object obj =
	 * rs.getObject(i+1); if(rs.wasNull()) { obj = null; } objs[i] = obj; }
	 * return objs; }
	 * 
	 * static public String[] getColumnNames(ResultSet rs) throws SQLException {
	 * ResultSetMetaData rsmd = rs.getMetaData(); int sz =
	 * rsmd.getColumnCount(); String[] names = new String[sz]; for(int i=0; i<sz;
	 * i++) { names[i] = rsmd.getColumnName(i+1); System.err.println(names[i]); }
	 * return names; }
	 * 
	 * static public int getColumnType(String column, Connection conn) throws
	 * SQLException { DatabaseMetaData meta = conn.getMetaData(); String catalog =
	 * null; String schema = null; String table = null; String columnName =
	 * null; String[] args = StringUtils.split(column, "."); if(args.length ==
	 * 4) { catalog = args[0]; schema = args[1]; table = args[2]; columnName =
	 * args[3]; } else if(args.length == 3) { schema = args[0]; table = args[1];
	 * columnName = args[2]; } else { table = args[0]; columnName = args[1]; }
	 * 
	 * System.err.println("Getting from: "+catalog+";"+schema+";"+table);
	 * ResultSet rs = meta.getColumns(catalog, schema, table, columnName);
	 * if(rs.next()) { return rs.getInt(5); } else { return -1; } }
	 * 
	 * static public String[] getPrimaryKeys(String tablename, Connection conn)
	 * throws SQLException { DatabaseMetaData meta = conn.getMetaData(); String
	 * catalog = null; String schema = null; String table = null; String[] args =
	 * StringUtils.split(tablename, "."); if(args.length == 3) { catalog =
	 * args[0]; schema = args[1]; table = args[2]; } else if(args.length == 2) {
	 * schema = args[0]; table = args[1]; } else { table = args[0]; }
	 * System.err.println("Getting from: "+catalog+";"+schema+";"+table);
	 * ResultSet rs = meta.getPrimaryKeys(catalog, schema, table); ArrayList
	 * list = new ArrayList(); while(rs.next()) { rs.getObject(1);
	 * rs.getObject(2); rs.getObject(3); list.add(rs.getObject(4)); } return
	 * (String[])list.toArray(new String[0]); }
	 * 
	 * class DOMParserFormatter implements java.io.Serializable { public
	 * DOMParserFormatter() { }
	 * 
	 * public Document getFileDocument(String file) throws Exception { // Step
	 * 1: create a DocumentBuilderFactory DocumentBuilderFactory dbf =
	 * DocumentBuilderFactory.newInstance();
	 *  // Step 2: create a DocumentBuilder DocumentBuilder db =
	 * dbf.newDocumentBuilder();
	 *  // Step 3: parse the input file to get a Document object Document doc =
	 * db.parse(new File(file));
	 * 
	 * return doc; }
	 * 
	 * public Document getXMLDocument(String xml) throws Exception { // Step 1:
	 * create a DocumentBuilderFactory DocumentBuilderFactory dbf =
	 * DocumentBuilderFactory.newInstance();
	 *  // Step 2: create a DocumentBuilder DocumentBuilder db =
	 * dbf.newDocumentBuilder();
	 * 
	 * ByteArrayInputStream xmlStream = new
	 * ByteArrayInputStream(xml.getBytes());
	 *  // Step 3: parse the input file to get a Document object Document doc =
	 * db.parse(xmlStream);
	 * 
	 * return doc; }
	 * 
	 * public String toXML(Document doc) { return String.valueOf(doc); } }
	 */
}
