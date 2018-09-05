package com.googlecode.dbyoutil.db;

//: c11:WordCount.java
//From 'Thinking in Java, 2nd ed.' by Bruce Eckel
//www.BruceEckel.com. See copyright notice in CopyRight.txt.
//Counts words from a file, outputs
//results in sorted form.
import java.io.*;
import java.util.*;

import common.util.StringInputStream;

import com.googlecode.dbyoutil.tool.StringUtil;

class Counter {
	private int i = 1;

	int read() {
		return i;
	}

	void increment() {
		i++;
	}
}

public class WordCount {
	private FileReader file;
	private StreamTokenizer st;
	// A TreeMap keeps keys in sorted order:
	private TreeMap counts = new TreeMap();

	WordCount() {
	}

	WordCount(String text) {
		Reader r = new BufferedReader(new StringReader(text));
		st = new StreamTokenizer(r);
		st.ordinaryChar('.');
		st.ordinaryChar('-');
		st.wordChars(33, 126);
	}

	void WordCountFile(String filename) throws FileNotFoundException {
		try {
			file = new FileReader(filename);
			st = new StreamTokenizer(new BufferedReader(file));
			st.ordinaryChar('.');
			st.ordinaryChar('-');
			st.wordChars(33, 126);
		} catch (FileNotFoundException e) {
			System.err.println("Could not open '" + filename + "'");
			throw e;
		}
	}

	// public void countWord(String text) throws Exception {
	// try {
	// st = new StreamTokenizer(new InputStream(text));
	// st.ordinaryChar('.');
	// st.ordinaryChar('-');
	// st.wordChars(33, 126);
	// } catch (Exception e) {
	// System.err.println("Could not tokenize '" + text + "'");
	// throw e;
	// }
	// }

	void cleanup() {
		try {
			file.close();
		} catch (IOException e) {
			System.err.println("file.close() unsuccessful");
		}
	}

	void countWords() {
		try {
			while (st.nextToken() != StreamTokenizer.TT_EOF) {
				String s;
				switch (st.ttype) {
				case StreamTokenizer.TT_EOL:
					s = new String("EOL");
					break;
				case StreamTokenizer.TT_NUMBER:
					s = Double.toString(st.nval);
					break;
				case StreamTokenizer.TT_WORD:
					s = st.sval; // Already a String
					break;
				default: // single character in ttype
					s = String.valueOf((char) st.ttype);
				}
				if (counts.containsKey(s))
					((Counter) counts.get(s)).increment();
				else
					counts.put(s, new Counter());
			}
		} catch (IOException e) {
			System.err.println("st.nextToken() unsuccessful");
		}
	}

	Collection values() {
		return counts.values();
	}

	Set keySet() {
		return counts.keySet();
	}

	Counter getCounter(String s) {
		return (Counter) counts.get(s);
	}

	public static void main(String[] args) throws FileNotFoundException {
		WordCount wc = null;
		String s = null;

		try {
			s = StringUtil.readFromFile(args[0]);
			wc = new WordCount(s);
			wc.countWords();
			Iterator keys = wc.keySet().iterator();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				System.out.println(key + ": " + wc.getCounter(key).read());
			}
			// wc.cleanup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}