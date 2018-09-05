/*
 *  XMLWriter.java
 *  
 *  Generated with jNerd's XML2Java Version 1.2 Preview 2
 *  
 *  Date: Fri Jul 04 19:50:19 EDT 2008
 *  
 *  No warranty; no copyright -- use this as you will.
 */
package com.googlecode.dbyoutil.db.xml2java;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.File;
import java.io.Writer;
import java.io.FilterWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.util.Stack;
import java.util.Iterator;
import java.util.LinkedList;
/**
 *  Utility class to comfortably write XML Data.<p>
 *  The <code>XMLWriter</code> filters the predefinied Entities and keeps track of well-formedness.
 *  <p>
 *  @author Generated with jNerd's XML2Java tool Version 1.2 Preview 2 on Fri Jul 04 19:50:19 EDT 2008
 *  @author <br>Please send BugReports to <a href="mailto:xml2java@jNerd.de">xml2java@jNerd.de</a>
 */
public class XMLWriter extends FilterWriter implements DTD{
	// ---------------------------
	//   for internal use only:
	// ---------------------------	
	private Stack stack;
	private boolean writingStarted=false;
	//      current ident level state
	private int lastindent=-1;
	// encding to be used:
	private final String encoding;
	// ---------------------------
	//   configurable properties:
	// ---------------------------
	private boolean beautify;
	private String beautification="\t";
	private boolean noHeader=false;
	private int documentTypeDeclarationStyle=DEFAULT_DOCUMENT_TYPE_DECLARATION_STYLE;
	/** Nomen est omen.
	 *  <p>
	 *  Wow! Congratulations, you found it. Well I am really sorry if you are bothered by my
	 *  little nagging advertisement. You can believe me, that I spend a lot of time
	 *  setting everything up. So at least I wanted to earn some honour...
	 *  (Money donations are welcome as well ;) )
	 *  <p>
	 *  Just switch it to false and you wont see the comment again.
	 */
	private static final boolean TELL_THE_PEOPLE_WHO_PROGRAMMED_THIS_STUFF_IN_A_LITTLE_COMMENT_WITHIN_THE_DOCUMENTS_PROLOG=true;
	/** This is actually the real constructor.
	 *  <p>
	 *  All following are calling this one.
	 */
	private XMLWriter(Writer out,boolean beautify,String encoding) {
		super(out);
		this.beautify=beautify;
		this.encoding=encoding;
	}
	
	/** 
	*  Creates a <code>XMLWriter</code> that writes to the specified <code>Writer</code>.
	*  <p>
	*  Beautification is by default off.<br>
	*  An encoding declaration will only be written, if the specified <code>Writer</code>
	*  is an instance of <code>OutputStreamWriter</code>.
	*  
	*  @param out The <code>Writer</code> to write to.
	*/
	public XMLWriter(Writer out) {
		this(out,false);
	}
	
	/** 
	*  Creates a <code>XMLWriter</code> that writes to the specified <code>Writer</code>
	*  with beautification on or off.
	*  <p>
	*  An encoding declaration will not be written.
	*
	*  @param out The <code>Writer</code> to write to.
	*  @param beautify Specify if the <code>XMLWriter</code> shall beautify the output.
	*/
	public XMLWriter(Writer out,boolean beautify) {
		this(out,beautify,null);
	}
	
	/** 
	*  Creates a <code>XMLWriter</code> that writes to the specified <code>OutputStream</code>.
	*  <p>
	*  Beautification is by default off.
	*  
	*  @param out The <code>OutputStream</code> to write to.
	*
	*  @throws IOException On any error.
	*/
	public XMLWriter(OutputStream out) throws IOException {
		this(out,false);
	}
	
	/** 
	*  Creates a <code>XMLWriter</code> that writes to the specified <code>OutputStream</code>
	*  with beautification on or off.
	*
	*  @param out The <code>OutputStream</code> to write to.
	*  @param beautify Specify if the <code>XMLWriter</code> shall beautify the output.
	*
	*  @throws IOException On any error.
	*/
	public XMLWriter(OutputStream out,boolean beautify) throws IOException{
		this(out,beautify,DEFAULT_ENCODING);
	}
	
	/** 
	*  Creates a <code>XMLWriter</code> that writes to the specified <code>OutputStream</code>
	*  using the specified encoding.
	*  <p>
	*  Beautification is by default off.
	*  
	*  @param out The <code>OutputStream</code> to write to.
	*  @param encoding The encoding to be used.
	*
	*  @throws IOException On any error.
	*/
	public XMLWriter(OutputStream out,String encoding) throws IOException {
		this(out,false,encoding);
	}
	
	/** 
	*  Creates a <code>XMLWriter</code> that writes to the specified <code>OutputStream</code>
	*  using the specified encoding with beautification on or off.
	*
	*  @param out The <code>OutputStream</code> to write to.
	*  @param beautify Specify if the <code>XMLWriter</code> shall beautify the output.
	*  @param encoding The encoding to be used.
	*
	*  @throws IOException On any error.
	*/
	public XMLWriter(OutputStream out,boolean beautify,String encoding) throws IOException{
		this((encoding==null)?(new OutputStreamWriter(out)):(new OutputStreamWriter(out,encoding)),beautify,encoding);
	}
	
	/** 
	*  Creates a <code>XMLWriter</code> that writes to the specified <code>File</code>.
	*  <br>
	*  Beautification is by default off.
	*
	*  @param xmlFile The <code>File</code> to be written.
	*  @throws IOException On any error
	*  @see #setBeautification
	*/
	public XMLWriter(File xmlFile) throws IOException{
		this(xmlFile,false);
	}
	
	/** 
	*  Creates a <code>XMLWriter</code> that writes to the specified <code>File</code>.
	*  <br>
	*  Beautification is by default off.
	*
	*  @param xmlFile The <code>File</code> to be written.
	*  @param beautify Specify if the <code>XMLWriter</code> shall beautify the output.
	*
	*  @throws IOException On any error.
	*
	*  @see #setBeautification
	*/
	public XMLWriter(File xmlFile,boolean beautify) throws IOException{
		this(xmlFile,beautify,DEFAULT_ENCODING);
	}
	
	/** 
	*  Creates a <code>XMLWriter</code> that writes to the specified <code>File</code>
	*  using the specified encoding.
	*  <br>
	*  Beautification is by default off.
	*  @param xmlFile The <code>File</code> to be written.
	*  @param encoding The encoding to be used.
	*  @throws IOException On any error.
	*  @see #setBeautification
	*/
	public XMLWriter(File xmlFile,String encoding) throws IOException{
		this(xmlFile,false,encoding);
	}
	
	/** 
	*  Creates a <code>XMLWriter</code> that writes to the specified <code>File</code>
	*  using the specified encoding and the specified beautification.
	*  <br>
	*  @param xmlFile The <code>File</code> to be written.
	*  @param beautify Specifies weher to beautify the output or not.
	*  @param encoding The encoding to be used.
	*
	*  @throws IOException On any error.
	*  @see #setBeautification
	*/
	public XMLWriter(File xmlFile, boolean beautify, String encoding) throws IOException{
		this(new BufferedOutputStream(new FileOutputStream(xmlFile)),beautify,encoding);
	}
	
	/**
	*  Writes a XML Document with the specified root.<p>
	*  The <code>XMLElement</code> and all SubElements get written.
	*  The Header and Doctype declaration will be written according to the <code>elem</code> Parameter.
	*
	*  @param elem The root Elemenent of the XML document, to be created.
	*  @throws IOException On any Problems with the underlying <code>Writer</code> or
	*          if a <code>XMLElement</code> has allready been written and the document
	*          type declaration style is not <code>NO_DOCUMENT_TYPE_DECLARATION</code>.
	*/
	public synchronized void write(XMLElement elem) throws IOException {
		if (!this.getSuppressProlog()) {
			if (!this.writingStarted) {
				this.writingStarted=true;
				this.out.write("<?xml version=\"1.0\" ");
				if (this.encoding!=null) {
					this.out.write("encoding=\""+this.encoding+"\" ");
				}
				this.out.write("?>\n");
				if (TELL_THE_PEOPLE_WHO_PROGRAMMED_THIS_STUFF_IN_A_LITTLE_COMMENT_WITHIN_THE_DOCUMENTS_PROLOG) {
					this.out.write("<!--\n"+
						  "     Document created with generated XMLWriter of\n"+
						  "     jNerd's XML2Java Tool Version 1.2 Preview 2 \n"+
						  "     http://www.jNerd.de/xml2java.html\n"+
						  "-->\n");
				}
				  
					this.writeDocumentTypeDeclaration(elem.getElementName());
			}
			 else {
				throw new IOException("Cannot write multiple XMLElements to a single XML document.");
			}
		}
		this.stack=new Stack();
		elem.write(this);
		if (!this.stack.isEmpty()) {
			throw new IOException("Writing element '"+elem.getElementName()+"' failed: Stack is not empty.");
		}
		this.stack=null;
		this.flush();
	}
	
	/** Accesses the document type declaration style used when writing/serializing 
	 *  a XML document.
	 *  <p>
	 *  The return value will be one of:
	 *	<ul>
	 *  <li><code>DTD.NO_DOCUMENT_TYPE_DECLARATION</code></li>
	 *  <li><code>DTD.INTERNAL_DOCUMENT_TYPE_DECLARATION</code></li>
	 *  <li><code>DTD.SYSTEM_DOCUMENT_TYPE_DECLARATION</code></li>
	 *  <li><code>DTD.PUBLIC_DOCUMENT_TYPE_DECLARATION</code></li>	 
	 *  </ul>
	 *
	 *  @return the current setting for document type declaration style.
	 *
	 *  @see #setDocumentTypeDeclaration
	 *  @see DTD#NO_DOCUMENT_TYPE_DECLARATION
	 *  @see DTD#INTERNAL_DOCUMENT_TYPE_DECLARATION
	 *  @see DTD#SYSTEM_DOCUMENT_TYPE_DECLARATION
	 *  @see DTD#PUBLIC_DOCUMENT_TYPE_DECLARATION
	 */
	public int getDocumentTypeDeclaration() {
		return this.documentTypeDeclarationStyle;
	}
	
	/** Sets the document type declaration style used when writing/serializing 
	 *  a XML document.
	 *  <p>
	 *  There are four choices:
	 *	<ul>
	 *  <li><code>DTD.NO_DOCUMENT_TYPE_DECLARATION</code> : No document type declaration will be written.
	 *  <br>NOTE: This means there will be no possibility to validate the written document.</li>
	 *  <li><code>DTD.INTERNAL_DOCUMENT_TYPE_DECLARATION</code> : The whole DTD will be included internally.
	 *  <br>NOTE: The DTD written is not correct if it contains relative parameter entities.</li>
	 *  <li><code>DTD.SYSTEM_DOCUMENT_TYPE_DECLARATION</code> : Within he Document type declaration a relative URL to the DTD is specified.
	 *  <br>NOTE: Don't forget to check if the DTD exists. Otherwise create it with <code>createDTD</code> or 
	 *  <code>createDTDRelativeTo</code></li>
	 *  <li><code>DTD.PUBLIC_DOCUMENT_TYPE_DECLARATION</code> : Within he Document type declaration a absolute URL to the DTD is specified.</li>	 
	 *  </ul>
	 *
	 *  @param style The document type declaration style to be used.
	 *
	 *  @throws IllegalArgumentException If the semantic of the specified parameter is unknown. 
	 *          (Stricly speaking if is not within range of -2 to 1)
	 *  @throws UnsupportedOperationException If the document type declaration style is not supported by this XMLWriter.
	 *          (This depends on the DTD processed for generating these classes.)
	 *
	 *  @see DTD#NO_DOCUMENT_TYPE_DECLARATION
	 *  @see DTD#INTERNAL_DOCUMENT_TYPE_DECLARATION
	 *  @see DTD#SYSTEM_DOCUMENT_TYPE_DECLARATION
	 *  @see DTD#PUBLIC_DOCUMENT_TYPE_DECLARATION	 	 	 
	 */
	public void setDocumentTypeDeclaration(int style) throws UnsupportedOperationException {
		if (style<-2 || style>1) {
			throw new IllegalArgumentException("Paramter style must be in range [-2,1] (was "+style+").");
		}
		if(style>=0 && this.DOCUMENT_TYPE_DECLARATION[style]==null) {
			throw new UnsupportedOperationException("The specified document type declaration style is not supported.");
		}
		this.documentTypeDeclarationStyle=style;
	}
	
	private void writeDocumentTypeDeclaration(String rootElement) throws IOException {
		if (this.getDocumentTypeDeclaration()==NO_DOCUMENT_TYPE_DECLARATION) {
			return;
		}
		this.out.write("<!DOCTYPE ");
		this.out.write(rootElement);
		switch (this.getDocumentTypeDeclaration()) {
			case INTERNAL_DOCUMENT_TYPE_DECLARATION:
				this.out.write(" [\n");
			if (DTD_INTERNAL_SUBSET!=null) {
				this.out.write(DTD_INTERNAL_SUBSET);
			}
			this.out.write(DTD_ORIGINAL);
			this.out.write("\n] >\n");
			return;
			case SYSTEM_DOCUMENT_TYPE_DECLARATION:
				this.out.write(" SYSTEM \"");
			this.out.write(DOCUMENT_TYPE_DECLARATION[SYSTEM_DOCUMENT_TYPE_DECLARATION]);
			break;
			case PUBLIC_DOCUMENT_TYPE_DECLARATION:
				if (DOCUMENT_TYPE_DECLARATION[this.getDocumentTypeDeclaration()]==null) {
				throw new IOException("XMLWriter is in an illegal State: the current docment type declaration style	is not supported.");
			}
			this.out.write(" PUBLIC \"");
			this.out.write(DOCUMENT_TYPE_DECLARATION[PUBLIC_DOCUMENT_TYPE_DECLARATION]);
			this.out.write("\" \"");
			this.out.write(DOCUMENT_TYPE_DECLARATION[PUBLIC_DOCUMENT_TYPE_DECLARATION+1]);
			break;
			default:
				throw new IOException("XMLWriter is in an illegal State: unknown docment type declaration style = "+this.getDocumentTypeDeclaration());
		}
		this.out.write("\"");
		if (DTD_INTERNAL_SUBSET!=null) {
			this.out.write(" [\n");
			this.out.write(DTD_INTERNAL_SUBSET);
			this.out.write("\n]");
		}
		this.out.write(" >\n");
	}
	
	/** Writes the DTD to the specified File.
	 *  <p>
	 *  If a file exists at the given location it will be overwritten.<br>
	 *  If the directory of the specified file does not exist, it will be created.
	 *  <p>
	 *  Note: If there are relative external parameter entities within the original DTD they 
	 *  might not be resolvable, as relative paths are possibly incorrect.
	 *
	 *  @param dtdFile File to be written
	 *
	 *  @throws IOException on any I/O Error
	 *
	 *  @see #createDTDRelativeTo
	 */
	public void createDTD(File dtdFile) throws IOException {
		if (!dtdFile.getParentFile().isDirectory()) {
			dtdFile.getParentFile().mkdirs();
		}
		Writer w;
		if (this.encoding!=null) {
			w=new OutputStreamWriter(new FileOutputStream(dtdFile),this.encoding);
		}
		 else {
			w=new FileWriter(dtdFile);
		}
		w=new BufferedWriter(w);
		w.write(DTD_ORIGINAL);
		w.flush();
		w.close();
	}
	
	/** Writes the DTD to a File relative to the specified XML document. 
	 *  The relative path used is the relative path used when writing a
	 *  XML document with a SYSTEM document type declaration.
	 *  <p>
	 *  If a file exists at the given location it will be overwritten.
	 *  <p>
	 *  Note: If there are relative external parameter entities within the original DTD they 
	 *  might not be resolvable, as relative paths are incorrect.
	 *
	 *  @param location File to be written
	 *
	 *  @throws IOException on any I/O Error
	 *
	 *  @see #setDocumentTypeDeclaration
	 *  @see #createDTD
	 *  @see DTD#SYSTEM_DOCUMENT_TYPE_DECLARATION
	 */
	public void createDTDRelativeTo(File xmlFile) throws IOException {
		this.createDTD(new File(xmlFile.getAbsoluteFile().getParent(),DOCUMENT_TYPE_DECLARATION[SYSTEM_DOCUMENT_TYPE_DECLARATION]));
	}
	
	/** Sets XML prolog suppression on or off.
	 *  <p>
	 *  If this property is set the <code>XMLWriter</code> starts directly
	 *  with writing the <code>XMLElement</code>. No XML prolog 
	 *  (<tt>&lt;?xml version='1.0' ?&gt;&lt;!DOCTYPE ...</tt>) will be written.
	 *  <p>
	 *  This feature is handy if you want to write short XML fragments only.
	 *  It is not meant to be used a lot. If you want to write fragment with multiple
	 *  XML elements in it, you have to set the document type declaration style to
	 *  <code>NO_DOCUMENT_TYPE_DECLARATION</code>.
	 *  <p>
	 *  NOTE that the generated Document will <b>not</b> be a wellformed XML document.
	 *
	 *  @param noHeader Specifies if the prolog should be outputted or not.
	 *
	 *  @see #setDocumentTypeDeclaration
	 *  @see #write(XMLElement)
	 */
	public void setSuppressProlog(boolean noHeader) {
		this.noHeader=noHeader;
	}
	
	/** Accesses the XML prolog suppression prperty.
	 *  
	 *  @return <code>true</code> if xml prolog suppression is enabled, <code>false</code> otherwise.
	 *
	 *  @see #setSuppressProlog
	 */
	public boolean getSuppressProlog() {
		return this.noHeader;
	}
	
	/** Turn output beautification on or off
	 *  @param onOff true is on; false is off
	 */
	public void setBeautification(boolean onOff) {
		this.beautify=onOff;
	}
	
	/** Access to the Beautification Property
	 *  @returns the String the beautification facility 
	 *  @returns uses for identing the XML-document or 
	 *  @returns <code>null</code> if bautification is disabled
	 */
	public String getBeautification() {
		if (!this.beautify) {
			return null;
		}
		return this.beautification;
	}
	
	/** Checks if the XMLWriter is beautifying the output
	 *  @returns true if the beautification is enabled
	 */
	public boolean isBeautifying() {
		return this.beautify;
	}
	
	/** Sets the String the XMLWriter uses for indenting the output
	 *  and enables beautification 
	 *  @param indent String used for indenting the output
	 *  @throws IllegalArgumentException if the parameter is null or does not consist solely of Whitespace.
	 */
	public void setBeautification(String indent) {
		if (indent==null) {
			throw new IllegalArgumentException("Cannot Indent with null.");
		}
		if(!indent.trim().equals("")) {
			throw new IllegalArgumentException("["+indent+"] does not consist solely of Whitespace.");
		}
		this.setBeautification(true);
		this.beautification=indent;
	}
	
	/** Sets the Number of Spaces the XMLWriter uses for indenting the output
	 *  and enables beautification 
	 *  @param indent Number of Space characters used for indenting the output
	 */
	public void setBeautification(int indent) {
		this.setBeautification(indent,new StringBuffer());
	}
	
	private void setBeautification(int indent,StringBuffer sb) {
		if (indent<=0) {
			this.setBeautification(sb.toString());
			return;
		}
		this.setBeautification(indent-1,sb.append(" "));
	}
	
	private void indent(int amount) throws IOException{
		if (this.lastindent<amount) {
			this.writeClean("\n");
			for(int i=0;
			i<amount;
			i++) {
				this.writeClean(this.beautification);
			}
		}
		this.lastindent=amount;
	}
	
	private void indent() throws IOException{
		this.indent(this.stack.size());
	}
	
	void writeStartTag(String name,AttributePool atts) throws IOException{
		if (this.isBeautifying()) {
			this.indent();
		}
		this.writeTag(name,atts);
		this.writeClean(">");
		this.stack.push(name);
	}
	
	void writeEmptyTag(String name,AttributePool atts) throws IOException {
		if (this.isBeautifying()) {
			// allways indent empty tags
			this.lastindent=-1;
			this.indent();
		}
		this.writeTag(name,atts);
		this.writeClean("/>");
		this.lastindent=-1;
	}
	
	void writeEndTag() throws IOException{
		Object element=this.stack.pop();
		if (this.isBeautifying()) {
			indent();
		}
		writeTag("/"+element,null);
		writeClean(">");
		this.lastindent=-1;
	}
	
	private void writeTag(String name,AttributePool atts) throws IOException {
		writeClean("<");
		writeClean(name);
		if (atts==null) {
			return;
		}
		Iterator itr=atts.iterator();
		AttPair ap;
		while (itr.hasNext()) {
			writeClean(" ");
			ap=(AttPair) itr.next();
			writeClean(ap.getName());
			writeClean("=\"");
			this.write(ap.getValue());
			writeClean("\"");
		}
	}
	
	private void writeClean(String s) throws IOException{
		this.out.write(s);
	}
	
	boolean hasWritingStarted(){
		return this.writingStarted;
	}
	
	/** Makes sure that everything is written through the filter.
	 *  @see <a href="#filter"><code>write(int s)</code></a>
	 */
	public void write(String s, int off, int len) throws IOException{
		write(s.toCharArray(),off,len);
	}
	
	/** Makes sure that everything is written through the filter.
	 *  @see <a href="#filter"><code>write(int s)</code></a>
	 */
	public void write(char[] arr, int off, int length) throws IOException{
		while(length-->0) {
			write((int) arr[off++]);
		}
	}
	
	/** Flushes the underlying <code>Writer</code>.
	 */
	public void flush() throws IOException {
		out.flush();
	}
	
	/** Closes the underlying <code>Writer</code>.
	 */
	public void close() throws IOException {
		flush();
		out.close();
	}
	
	/** <a name="filter" />Filters the predefined Entities.
	 */
	public void write(int s) throws IOException{
		switch (s) {
			case '>':
				this.out.write("&gt;");
			return;
			case '<':
				this.out.write("&lt;");
			return;
			case '"':
				this.out.write("&quot;");
			return;
			case '\'':
				this.out.write("&apos;");
			return;
			case '&':
				this.out.write("&amp;");
			return;
			default:
				this.out.write(s);
		}
	}
	
	/** A little Helper class for representing the Name-Value Pairs of the Attributes.
	 */
	static class AttributePool extends LinkedList {
		/** Adds a Name Value AttributePair to this Pool. 
		 *  @param name The Name
		 *  @param value The Value
		 *  @return <code>true</code> if the Pool has changed.
		 *  @throws <code>NullPointerException</code> when <code>name==null</code>
		 */
		boolean add(String name,String value) {
			if (name==null) {
				throw new NullPointerException();
			}
			if (value==null) {
				return false;
			}
			AttPair atp=new AttPair(name,value);
			return add(atp);
			 
				
		}
	}
	
	private static class AttPair {
		private final String name;
		private final String value;
		AttPair(String name,String value) {
			this.name=name;
			this.value=value;
		}
		String getName() {
			return this.name;
		}
		String getValue() {
			return this.value;
		}
	}
	
}
 
	