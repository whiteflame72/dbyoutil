package com.googlecode.dbyoutil.tool;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class StringUtil {
	public static String getStandardFormat(float number) {
		DecimalFormat dFmt = new DecimalFormat("00");
		dFmt.applyPattern("#,###.##");

		return dFmt.format(number);
	}

	public static Timestamp getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		long millis = cal.getTime().getTime();
		Timestamp ts = new Timestamp(millis);
		return ts;
	}

	public static void writeIntoLogFile(OutputStreamWriter out, String msg) {
		try {
			out.write(msg, 0, msg.length());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeIntoFile(String fileName, String msg,
			boolean appendFlag) {
		try {
			// === support foreign character in stream too (UTF-16)
			OutputStreamWriter out = new OutputStreamWriter(
					new FileOutputStream(fileName, appendFlag), "UTF-16");
			out.write(msg, 0, msg.length());
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void writeIntoFile(String fileName, byte[] msg,
			boolean appendFlag) {
		try {
			// === support foreign character in stream too (UTF-16)
			FileOutputStream out = new FileOutputStream(fileName, appendFlag);
			out.write(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static String readFromFile(String fileName) throws Exception {
		String retVal = null;
		StringBuffer output = new StringBuffer();
		int line;
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			while ((line = in.read()) != -1) {
				output.append((char) line);
			}
			in.close();
		} catch (IOException e) {
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
	 * This method insert newline characters in the string bassed on the width
	 * passed. It returns the modified string with newline(s) if the length of
	 * the string is more than the width.
	 */
	public static String toStringWidth(String str, int width) throws Exception {
		String retVal = "";

		// === newline offset take care of newline which "breaks" width-rule
		// algo :)
		int newLineOffset = 0;
		int originalWidth = width;
		int lastSpaceIndex = -1;
		if (str != null && !str.trim().equals("")) {
			int lineChar = -1;
			String line = "";
			int i = -1;
			try {
				BufferedReader in = new BufferedReader(new StringReader(str));

				// === scan for newline characters, if found one, check whether
				// the

				// === truncation is at the word boundary or not. If it is,
				// retrack

				// === back to the first space encountered and truncate up to
				// that point

				// === instead of the current position.
				while (true) {
					for (i = 0; i < width; i++) {
						lineChar = in.read();
						if (lineChar == -1) {
							break;
						} else {
							if (lineChar == 10 || lineChar == 13) {
								newLineOffset = i + 1;
								width = originalWidth + newLineOffset;
							} else {
								if (lineChar == ' ') {
									in.mark(i);
								}
							}
						}
						line += (char) lineChar;
					}

					// === check to see whether in the line we have any newline
					// character
					lastSpaceIndex = line.lastIndexOf(' ', i);
					if (lastSpaceIndex > -1 && lastSpaceIndex < i
							&& lineChar != -1) {
						line = line.substring(0, line.length()
								- (i - lastSpaceIndex));
						i = lastSpaceIndex;

						// === reset read buffer back to the marking point
						in.reset();
					}
					retVal += line + System.getProperty("line.separator");

					// === reset everything
					line = "";
					width = originalWidth;
					if (lineChar == -1) {
						break;
					}
				}
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.toString());
			}
		}
		return retVal;
	} // end of method

	public static String handleUnicode(String str) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try {
			OutputStreamWriter out = new OutputStreamWriter(output, "UTF-16");
			out.write(str, 0, str.length());
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}

		return output.toString();
		// return str;
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

	public static String encodeXMLReservedCharsForHTML(String charData) {

		// replace "&" first because that is contained in other values
		charData = replace(charData, "&", "&amp;");
		charData = replace(charData, "\'", "&apos;");
		charData = replace(charData, "\"", "&quot;");
		charData = replace(charData, "<", "&lt;");
		charData = replace(charData, ">", "&gt;");
		return charData;
	}

	public static boolean isAFile(String value) throws Exception {
		boolean aFile = true;
		try {
			new FileReader(value);
		} catch (Exception e) {
			aFile = false;
		}
		return aFile;
	}

	/**
	 * This method split the characters into 32k chunck to overcome JDBC driver
	 * bug.
	 */
	private static ArrayList<String> getStringChunks(String bigString,
			int splitBoundaryLength) {
		int count, hop;
		String temp;
		ArrayList<String> stringObjects = new ArrayList<String>();

		temp = bigString;
		count = 0;
		hop = 0;
		// === tokenize the string
		if (temp != null && temp.length() >= splitBoundaryLength) {
			do {
				count = count + 1;
				try {
					temp = temp.substring(hop, hop + splitBoundaryLength);
					stringObjects.add(temp);
					hop = hop + splitBoundaryLength;
					temp = bigString;
				} catch (Exception e) {
					temp = temp.substring(hop, temp.length());
					if (!temp.equals("")) {
						stringObjects.add(temp);
					}
					temp = "";
				}
			} while (!temp.equals(""));
		} else {
			stringObjects.add(bigString);
		}

		return stringObjects;
	}

	// === This method overcome the clob limitation in Oracle lite by chopping
	// up the long text into
	// === smaller pieces. It was found that 3000 is the magic number.
	public static String handleClobString(String longString) {
		StringBuffer retVal = new StringBuffer();
		ArrayList<?> temp = getStringChunks(longString, 3000);
		int count = 0;

		if (temp.size() > 0) {
			Iterator<?> iterList = temp.iterator();

			while (iterList.hasNext()) {
				if (count > 0)
					retVal.append("' || '");

				retVal.append((String) iterList.next()).append("");
				count++;
			}
		} else {
			retVal = new StringBuffer(longString);
		} // if(count > 0) //
		System.out.println("clob string = '" + retVal.toString() + "'");

		return retVal.toString();
	}

	public static String handleSQLNewlineChar(String sql) throws Exception {
		String retVal = sql;

		if (sql != null && !sql.trim().equals("")) {
			retVal = StringUtil.replace(sql, "\\n", "\n");
		}

		return retVal;
	}

	public static int showXML(String currentDirAbsolutePath, String fileName) {
		String externalScriptLauncher = currentDirAbsolutePath
				+ "launch_external.bat ";
		Runtime rt = Runtime.getRuntime();
		String command = externalScriptLauncher + fileName;
		Process p = null;
		int num = -1;
		try {
			p = rt.exec(command);
			num = p.waitFor();
		} catch (Exception e) {
			// e.printStackTrace();
			num = 0;
		}

		return num;
	}

	public static String handleEmptyString(String str) {
		return str == null ? "" : str;
	}
}
