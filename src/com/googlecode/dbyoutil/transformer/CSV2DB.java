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

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.googlecode.dbyoutil.tool.StringUtil;

public class CSV2DB {
	// === general stuff
	public static final String buildNum = "RC_6.3.3";

	public static final int META_COLUMNS_MAX = 7;

	private static DB2XML dbXML = null;

	// === statistics stuff
	public static final String CR = System.getProperty("line.separator");

	public static final String LOG_LINE_STRING = "------------------------------------------------------------------------------------------------------------------------";

	public static void main(String[] args) {
		CSV2DB csvObj = new CSV2DB();

		if (args.length >= 6) {
			System.out.println("DB driver '" + args[0] + "' url '" + args[1]
					+ "' userid '" + args[2] + "' passwd '" + args[3]
					+ "' csv file '" + args[4] + "' delimiter '" + args[5]
					+ "''");
			csvObj.setDBUserName(args[2]);
			csvObj.setDBUserPassword(args[3]);
			csvObj.setDBUrl(args[1]);
			csvObj.setDBDriver(args[0]);
			csvObj.connectToDB();
		} else {
			System.out
					.println("DB driver, dburl, userid and passwd must all be specified.");
			System.exit(0);
		}

		try {
			if (args.length == 7) {
				System.out.println(" log '" + args[6] + "''");
				if (args[6] != null && !args[6].trim().equals("")) {
					csvObj.setLogFileName(args[6]);
					csvObj.logSQLCBOStatistics(
							StringUtil.readFromFile(args[4]), args[5]);
				}
			} else {
				// make the Oracle date format the same as MYSQL DATETIME format
				csvObj
						.setOracleDateFormat("ALTER SESSION SET NLS_DATE_FORMAT='YYYY-MM-DD HH24:MI:SS");
				csvObj.loadCSVData(StringUtil.readFromFile(args[4]), args[5]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(-1);
	}

	private String dbUrl = null;

	private String dbDriver = null;

	private String userName = null;

	private String userPassword = null;

	// === keep track of unique document name
	private Hashtable uniqueDocumentHash = null;

	long startTime;

	long endTime;

	private boolean writeIntoLogFile = true;

	private OutputStreamWriter out;

	private OutputStreamWriter summaryOut;

	private String logFileName;

	private int fullDebug = -1;

	private String sqlTrailerID = " --DB_CASE_ID ";

	public CSV2DB() {
		dbXML = new DB2XML();
		// out = openLogFile(logFileName);
		System.out.println("CSV2DB: " + buildNum + " instantiated.");
	}

	public void closeLogFile(OutputStreamWriter out) {
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void connectToDB() {
		// dbXML.setDBDriver(dbDriver);
		// dbXML.setDBUrl(dbUrl);
		// dbXML.setDBUserName(userName);
		// dbXML.setDBUserPassword(userPassword);
		try {
			dbXML.loadDBDriver(dbDriver, dbUrl, userName, userPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Execute the delete SQL against document name.
	 */
	private void executeSQLDelete(String documentName) throws Exception {
		String currentSQL = null;
		String stat1 = "", stat2 = "";
		String[][] res1 = null, res2 = null;
		try {
			if (documentName != null && !documentName.equals("")) {
				currentSQL = "delete from fpdsrpt where Document = '"
						+ documentName + "'";
				// System.out.println("Executing SQL >" + currentSQL + "<");
				res1 = dbXML.setSQLData(currentSQL);
				stat1 = String.valueOf(res1[0][0]);
				res2 = dbXML.setSQLData("commit");
				stat2 = String.valueOf(res2[0][0]);
				if (stat1 != null
						&& !stat1.equals(DB2XML.systemMessageStartLabel + "1"
								+ DB2XML.systemMessageEndLabel)) {
					System.out.print("Error while trying to execute ["
							+ currentSQL + "], exception is '" + stat1 + "' ("
							+ stat2 + ")");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Execute the insert SQLs.
	 */
	private void executeSQLInsert(ArrayList<String> sqlInsertStatements)
			throws Exception {
		String currentSQL = null;
		String stat1 = "", stat2 = "";
		String[][] res1 = null, res2 = null;
		int count = 0;

		try {
			if (sqlInsertStatements != null && sqlInsertStatements.size() > 0) {
				Iterator iterList = sqlInsertStatements.iterator();

				while (iterList.hasNext()) {
					currentSQL = (String) iterList.next();
					// System.out.println("Executing SQL >" + currentSQL + "<");
					// generateLogHeader(out);
					res1 = dbXML.setSQLData(currentSQL);
					// generateLogFooter(out);
					stat1 = String.valueOf(res1[0][0]);
					res2 = dbXML.setSQLData("commit");
					stat2 = String.valueOf(res2[0][0]);
					if (stat1 != null
							&& !stat1.equals(DB2XML.systemMessageStartLabel
									+ "1" + DB2XML.systemMessageEndLabel)) {
						System.out.print("Error while trying to execute ["
								+ currentSQL + "], exception is '" + stat1
								+ "' (" + stat2 + ")");
					} else {
						count++;
					}

					System.out.print("." + count + ".");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Execute the insert SQLs.
	 */
	private void executeSQLSelect(ArrayList<String> sqlSelectStatements)
			throws Exception {
		String currentSQL = null;
		// String[][] res1 = null;
		// String stat1 = "";
		int count = 0;

		try {
			if (sqlSelectStatements != null && sqlSelectStatements.size() > 0) {
				Iterator iterList = sqlSelectStatements.iterator();

				// res1 = dbXML.setSQLData("ALTER SESSION SET SQL_TRACE=FALSE");
				// stat1 = String.valueOf(res1[0][0]);
				while (iterList.hasNext()) {
					currentSQL = (String) iterList.next();
					if ((currentSQL != null && currentSQL.toLowerCase()
							.indexOf("select") > -1)) {
						// System.out.println("Executing SQL >" + currentSQL +
						// "<");
						try {
							log(out, "Executing SQL >" + currentSQL + "<");
							log(summaryOut, currentSQL.substring(currentSQL
									.indexOf(sqlTrailerID)
									+ sqlTrailerID.length(), currentSQL
									.length())
									+ "-->"
									+ dbUrl
									+ "-->"
									+ " SQL >"
									+ removeSQLNewlineChar(currentSQL) + "<");

							generateLogHeader(out);
						} finally {
							generateLogFooter(out);
							generateLogFooter(summaryOut);
						}

						count++;
						System.out.print("." + count + ".");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void generateLogFooter(OutputStreamWriter out) throws Exception {
		endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		log(out, "End: " + String.valueOf(StringUtil.getCurrentTime())
				+ " Total time = " + (totalTime / 1000) / 60 + " minutes ("
				+ (totalTime / 1000) + " s)");
		log(out, LOG_LINE_STRING + CR);
	}

	private void generateLogHeader(OutputStreamWriter out) throws Exception {
		startTime = System.currentTimeMillis();
		log(out, LOG_LINE_STRING);
		log(out, "Begin: " + String.valueOf(StringUtil.getCurrentTime()));
	}

	/**
	 * Parse the csv file and initialize the insert array.
	 */
	private ArrayList<String> getSQLArrayList(String csvFileContent,
			String delimiter) throws Exception {
		StringTokenizer st = new StringTokenizer(csvFileContent, delimiter,
				false);
		String currentStr = null;
		ArrayList<String> sqlArray = null;

		if (csvFileContent != null && !csvFileContent.equals("")
				&& delimiter != null && !delimiter.equals("")) {
			sqlArray = new ArrayList<String>();
		}

		while (st.hasMoreTokens()) {
			currentStr = st.nextToken().replace('"', ' ').trim();
			if (currentStr != null && !currentStr.equals("")) {
				// System.out.println(">" + currentStr + "<");
				sqlArray.add((String) currentStr);
			}
		}

		return sqlArray;
	}

	/**
	 * Generate the insert SQL based on the SQL array list.
	 */
	private final ArrayList<String> getSQLArrayList(final String csvFileContent,
			final String delimiter, final String sqlType) throws Exception {
		String currentStr = null;
		ArrayList<String> sqlArray = null, tempArray = null;

		if (csvFileContent != null && !csvFileContent.equals("")
				&& delimiter != null && !delimiter.equals("")) {
			sqlArray = new ArrayList<String>();
			uniqueDocumentHash = new Hashtable();
		}

		String line;
		try {
			final BufferedReader in = new BufferedReader(new StringReader(
					csvFileContent));
			while ((line = in.readLine()) != null) {
				if (line != null && !line.trim().equals("")) {
					tempArray = getSQLArrayList(line, ";");
					// System.out.println("========= columns size is " +
					// tempArray.size() + " ==========");
					if (tempArray.size() == META_COLUMNS_MAX - 1) {

						if ("insert".equals(sqlType.toLowerCase())) {
							currentStr = "INSERT INTO fpdsrpt (Refreshed, Document, No, Category, Object, Value, Platform) VALUES ('"
									+ (String) tempArray.get(0)
									+ "','"
									+ (String) tempArray.get(1)
									+ "',"
									+ (String) tempArray.get(2)
									+ ", '"
									+ (String) tempArray.get(3)
									+ "', '"
									+ (String) tempArray.get(4)
									+ "', '"
									+ (String) tempArray.get(5)
									+ "', '"
									+ ""
									+ "')";
							// System.out.println(">" + currentStr + "<");
							sqlArray.add((String) currentStr);
						} else if ("select".equals(sqlType.toLowerCase())) {
							currentStr = StringUtil
									.handleSQLNewlineChar((String) tempArray
											.get(5)
											+ sqlTrailerID
											+ (String) tempArray.get(3));
							;
							// System.out.println("modified SQL
							// --------------------------->" + currentStr +
							// "<");
							sqlArray.add((String) currentStr);
						}
						uniqueDocumentHash.put((String) tempArray.get(1),
								(String) tempArray.get(1));
					} else if (tempArray.size() == META_COLUMNS_MAX) {
						currentStr = "INSERT INTO fpdsrpt (Refreshed, Document, No, Category, Object, Value, Platform) VALUES ('"
								+ (String) tempArray.get(0)
								+ "','"
								+ (String) tempArray.get(1)
								+ "',"
								+ (String) tempArray.get(2)
								+ ", '"
								+ (String) tempArray.get(3)
								+ "', '"
								+ (String) tempArray.get(4)
								+ "', '"
								// +
								// handleClobString(handleSQLNewlineChar((String)tempArray.get(5)))
								// + "', '" + //for Oracle/OLite only
								+ StringUtil
										.handleSQLNewlineChar((String) tempArray
												.get(5))
								+ "', '"
								+ (String) tempArray.get(6) + "')";
						sqlArray.add((String) currentStr);

						uniqueDocumentHash.put((String) tempArray.get(1),
								(String) tempArray.get(1));
					} else {
						// === do something if the parsing fails
						// System.out.println("columns size is " +
						// tempArray.size() + " ?");
					}
				}
			}
			in.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		if (uniqueDocumentHash != null) {
			String docName = null;
			// === check message
			System.out.println("\nunique document detected = "
					+ uniqueDocumentHash.size());
			Enumeration docEnum = null;

			docEnum = uniqueDocumentHash.elements();

			if (docEnum != null) {
				while (docEnum.hasMoreElements()) {
					docName = (String) docEnum.nextElement();
					System.out.println(docName);
				}
				System.out.println("\n");
			}
		}

		return sqlArray;
	}

	/**
	 * Web service method.
	 */
	public String getSQLXMLData(String clientID, String p1, String p2,
			String p3, String p4, String sql) throws Exception {
		String retVal = "";

		if (clientID == null || clientID.trim().equals("")) {
			retVal = "Client ID can not be NULL or empty.";
		} else {
			System.out.println(clientID);

			if (p1 != null && !p1.equals("") && p2 != null && !p2.equals("")
					&& p3 != null && !p3.equals("") && p4 != null
					&& !p4.equals("") && sql != null && !sql.equals("")) {
				/*
				 * setDBUserName("scott"); setDBUserPassword("tiger");
				 * setDBUrl("jdbc:Polite:tapold");
				 * setDBDriver("oracle.lite.poljdbc.POLJDBCDriver");
				 */
				setDBUserName(p1);
				setDBUserPassword(p2);
				setDBUrl(p3);
				setDBDriver(p4);
				connectToDB();

				if (StringUtil.isAFile(sql)) {
					sql = DB2XML.readSQLFileData(sql);
				}

				System.out.println("CSV2DB:" + clientID + " querying '" + sql
						+ "' ...");
				retVal = dbXML.getXMLData(11, sql);
				System.out.println("CSV2DB:" + clientID
						+ " getSQLXMLData done.");
			}
		}

		return retVal;
	}

	private void handleDuplicateDocument() throws Exception {
		if (uniqueDocumentHash != null) {
			String docName = null;
			// === check message
			System.out.println("\npurging total document "
					+ uniqueDocumentHash.size());
			Enumeration docEnum = null;

			docEnum = uniqueDocumentHash.elements();

			if (docEnum != null) {
				while (docEnum.hasMoreElements()) {
					docName = (String) docEnum.nextElement();
					executeSQLDelete(docName);
					System.out.println(docName + " deleted.");
				}
				System.out.println("\n");
			}
		}
	}

	public void loadCSVData(String sql, String delimiter) throws Exception {
		ArrayList<String> temp = null;
		try {
			temp = getSQLArrayList(sql, delimiter);
			handleDuplicateDocument();
			System.out.println("inserting ...");
			executeSQLInsert(temp);
			System.out.println("done.");
			// temp = csvObj.getStringChunks(input, 10);
			// csvObj.getStringChunks("12345678901", 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void log(OutputStreamWriter out, String message) throws Exception {

		// System.out.println(message + CR);
		if (out == null) {
			if (fullDebug > 0) {
				throw new Exception("Log file is null !");
			}
			/*
			 * else { System.out.println("Log file is null !"); }
			 */
		} else if (writeIntoLogFile) {
			StringUtil.writeIntoLogFile(out, message + CR);
		}
	}

	public void logSQLCBOStatistics(String sql, String delimiter)
			throws Exception {
		ArrayList<String> temp = null;
		try {
			out = openLogFile(logFileName);
			summaryOut = openLogFile(logFileName + ".short.txt");
			temp = getSQLArrayList(sql, delimiter, "select");
			System.out.println("gathering statistics ...");
			executeSQLSelect(temp);
			closeLogFile(out);
			System.out.println("done.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OutputStreamWriter openLogFile(String fileName) {
		OutputStreamWriter out = null;

		if (fileName != null && !fileName.trim().equals("")) {
			System.out.println("log file to be opened = '" + fileName + "'");
			try {
				out = new OutputStreamWriter(new FileOutputStream(fileName,
						true));
				System.out.println("log file '" + fileName + "' opened.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return out;
	}

	private String removeSQLNewlineChar(String sql) throws Exception {
		String retVal = sql;

		if (sql != null && !sql.trim().equals("")) {
			retVal = StringUtil.replace(sql, "\n", "\\n");
		}

		return retVal;
	}

	public void setDBDriver(String driver) {
		dbDriver = driver;
	}

	public void setDBUrl(String url) {
		dbUrl = url;
	}

	public void setDBUserName(String userName) {
		this.userName = userName;
	}

	public void setDBUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}

	public void setOracleDateFormat(String dateFormatSpecifier)
			throws Exception {
		dbXML.setSQLData(dateFormatSpecifier); // "ALTER SESSION SET
		// NLS_DATE_FORMAT='YYYY-MM-DD
		// HH24:MI:SS'"
		// stat1 = String.valueOf(res1[0][0]);
	}

	/**
	 * Web service method.
	 */
	public boolean store(String clientID, String p1, String p2, String p3,
			String p4, String documentName, String csvFileContent,
			String delimiter) throws Exception {
		boolean retVal = false;

		if (clientID != null && !clientID.trim().equals("")) {
			System.out.println(clientID);

			if (p1 != null && !p1.equals("") && p2 != null && !p2.equals("")
					&& p3 != null && !p3.equals("") && p4 != null
					&& !p4.equals("") && csvFileContent != null
					&& !csvFileContent.equals("") && delimiter != null
					&& !delimiter.equals("")) {
				/*
				 * setDBUserName("scott"); setDBUserPassword("tiger");
				 * setDBUrl("jdbc:Polite:tapold");
				 * setDBDriver("oracle.lite.poljdbc.POLJDBCDriver");
				 */
				setDBUserName(p1);
				setDBUserPassword(p2);
				setDBUrl(p3);
				setDBDriver(p4);
				connectToDB();

				System.out.println("CSV2DB:" + clientID + " deleting '"
						+ documentName + "' ...");
				executeSQLDelete(documentName);
				System.out.println("CSV2DB:" + clientID
						+ " generating insert SQL ...");
				ArrayList<String> temp = getSQLArrayList(csvFileContent,
						delimiter);
				System.out.println("CSV2DB:" + clientID + " inserting ...");
				executeSQLInsert(temp);
				System.out.println("CSV2DB:" + clientID + " store done.");

				retVal = true;
			}
		}

		return retVal;
	}
}
