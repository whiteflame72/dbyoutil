package com.googlecode.dbyoutil.tool;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;

public class ColumnComparator implements Comparable {
	// === fields to be sorted
	private String name;

	private String type;

	private String precision;

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getPrecision() {
		return precision;
	}

	// === initializer
	public ColumnComparator(String name, String type, String precision) {
		this.name = name;
		this.type = type;
		this.precision = precision;
	}

	// === the required compareTo method.
	public int compareTo(Object o) {
		ColumnComparator column = (ColumnComparator) o;

		// System.out.println("comparing '" + name + "' with '" +
		// column.name + "' ...");

		// === sort ascending
		return name.compareTo(column.name);
	}

	private static String addSampleValue(final int i, final ResultSet rs,
			boolean withValue) throws Exception {
		String retVal = "";
		if (rs != null) {
			if (withValue) {
					String encoding = "US-ASCII";
					if(rs != null) {
						String temp = "";
						try {
							//System.out.println("addSampleValue:1 i = " + i);
							temp = rs.getString(i + 1);
						} catch (Exception e) {
							//avoid error like "Illegal operation on empty result set." especially on mysql
							//System.out.println("addSampleValue:2 " + e.getMessage() + " table = " + rs.getMetaData().getTableName(1));
						}
						//retVal = CommonUtil.safeCDATAForXML(rs.getString(i + 1)!=null?new String(rs.getString(i + 1).getBytes(encoding),encoding):"");
						retVal = temp!=null?new String(temp.getBytes(),encoding):"";
						//TBD - cause "invalid byte 1 of 1-byte UTF-8 sequence" for European language !!!
						//retVal = CommonUtil.safeCDATAForXML(rs.getString(i + 1)!=null?new String(rs.getString(i + 1).getBytes(),"8859_1"):"");
						//retVal = rs.getString(i + 1)!=null?rs.getString(i + 1):"";
					}
			}
		}

		return CommonUtil.safeCDATAForXML(retVal);
	}

	/**
	 * Method to sort report list based on report title.
	 */
	public static ColumnComparator[] sortColumnArrayList(ResultSet rs,
			final ResultSetMetaData rsmd, StringBuffer xmlString,
			boolean withValue) throws Exception {
		final int columnCount = rsmd.getColumnCount();
		final ColumnComparator column[] = new ColumnComparator[columnCount];

		for (int i = 1; i <= columnCount; i++) {
			column[i - 1] = new ColumnComparator(rsmd.getColumnName(i), rsmd
					.getColumnTypeName(i), String.valueOf(rsmd.getPrecision(i)));
		}

		Arrays.sort(column);

		// === column is sorted right now
		final ColumnComparator[] columnArray = column;

		// === sort the array
		if (columnArray != null && columnArray.length > 0) {
			for (int i = 0; i < columnArray.length; i++) {
				xmlString.append("<").append(
						columnArray[i].getName().replace(' ', '_')).append(
						" type = '" + columnArray[i].getType() + "' size = '"
								+ columnArray[i].getPrecision() + "'").append(
						">")
						.append(addSampleValue(i, rs, withValue))
						.append(
								"</"
										+ columnArray[i].getName().replace(' ',
												'_') + ">");
			}
		}

		return column;
	}
}
