package org.apache.tool;

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

	private static String addSampleValue(final int i, final ResultSet rs)
			throws Exception {
		final String retVal = "";
		if (rs != null) {
			// retVal =
			// safeCDATAForXML(retrieveString(rs.getString(i+1),stopIndex));
		}

		return retVal;
	}

	/**
	 * Method to sort report list based on report title.
	 */
	public static ColumnComparator[] sortColumnArrayList(ResultSet rs,
			final ResultSetMetaData rsmd, StringBuffer xmlString)
			throws Exception {
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
						">").append(addSampleValue(i, rs))
						.append(
								"</"
										+ columnArray[i].getName().replace(' ',
												'_') + ">");
			}
		}

		return column;
	}
}
