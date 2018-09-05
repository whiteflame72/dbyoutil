/*
 *  SAX2Reader.java
 *  
 *  Generated with jNerd's XML2Java Version 1.2 Preview 2
 *  
 *  Date: Fri Jul 04 19:50:19 EDT 2008
 *  
 *  No warranty; no copyright -- use this as you will.
 */
package com.googlecode.dbyoutil.db.xml2java;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.File;
import java.io.Reader;
import java.io.InputStream;
/**
 *  This is a Wrapper to easily read and parse XML Files.
 *  I didn't found the right interface to implement,
 *  but I think you know how to use it...
 *  <p>
 *  @author Generated with jNerd's XML2Java tool Version 1.2 Preview 2 on Fri Jul 04 19:50:19 EDT 2008
 *  @author <br>Please send BugReports to <a href="mailto:xml2java@jNerd.de">xml2java@jNerd.de</a>
 */
public class SAX2Reader {
	private final SAXParser parser;
	private XMLHandler handler;
	/**
	*  Constructs a validating <code>SAX2Reader</code>.
	*  @throws ParserConfigurationException,SAXException
	*/
	public SAX2Reader() throws ParserConfigurationException, SAXException{
		this(true);
	}
	
	/**
	*  Constructs a <code>SAX2Reader</code>.
	*  @param validating Flag if the Parser should be validating.
	*  @throws ParserConfigurationException,SAXException
	*/
	public SAX2Reader(boolean validating) throws ParserConfigurationException, SAXException{
		SAXParserFactory spf=SAXParserFactory.newInstance();
		spf.setValidating(validating);
		spf.setNamespaceAware(true);
		this.parser=spf.newSAXParser();
	}
	
	/**
	*  Parses the specified <code>File f</code> and returns the root <code>XMLElement</code>
	*  @return The root <code>XMLElement</code> of the parsed XML File.
	*  @param f The <code>File</code> to be parsed.
	*  @throws SAXException On any problem with the Parser.
	*  @throws IOException On any problem with the IO.
	*/
	public XMLElement parse(File f) throws SAXException, IOException {
		parser.parse(f,this.getXMLHandler());
		return this.handler.getResult();
	}
	
	/**
	*  Parses the specified <code>InputSource is</code> and returns the root <code>XMLElement</code>
	*  @return The root <code>XMLElement</code> of the parsed XML InputSource.
	*  @param is The <code>InputSource</code> to be parsed.
	*  @throws SAXException On any problem with the Parser.
	*  @throws IOException On any problem with the IO.
	*/
	public XMLElement parse(InputSource is) throws SAXException, IOException {
		parser.parse(is,this.getXMLHandler());
		return this.handler.getResult();
	}
	
	/**
	*  Parses the specified <code>InputStream is</code> and returns the root <code>XMLElement</code>
	*  @return The root <code>XMLElement</code> of the parsed XML InputStream.
	*  @param is The <code>InputStream</code> to be parsed.
	*  @throws SAXException On any problem with the Parser.
	*  @throws IOException On any problem with the IO.
	*/
	public XMLElement parse(InputStream is) throws SAXException, IOException {
		return this.parse(new InputSource(is));
	}
	
	/**
	*  Parses the specified <code>InputStream is</code> and returns the root <code>XMLElement</code>
	*  @return The root <code>XMLElement</code> of the parsed XML InputStream.
	*  @param is The <code>InputStream</code> to be parsed.
	*  @param systemId The <code>systemId</code> of the <code>InputStream</code> to be parsed.
	*  @throws SAXException On any problem with the Parser.
	*  @throws IOException On any problem with the IO.
	*/
	public XMLElement parse(InputStream is,String systemId) throws SAXException, IOException {
		parser.parse(is,this.getXMLHandler(),systemId);
		return this.handler.getResult();
	}
	
	/**
	*  Parses the specified <code>uri</code> and returns the root <code>XMLElement</code>
	*  @return The root <code>XMLElement</code> of the parsed XML uri.
	*  @param uri The URI to be parsed.
	*  @throws SAXException On any problem with the Parser.
	*  @throws IOException On any problem with the IO.
	*/
	public XMLElement parse(String uri) throws SAXException, IOException {
		parser.parse(uri,this.getXMLHandler());
		return this.handler.getResult();
	}
	
	/**
	 *  Parses the specified <code>Reader r</code> and returns the root <code>XMLElement</code>
	 *  @return The root <code>XMLElement</code> of the parsed XML InputStream.
	 *  @param r The <code>Reader</code> to be parsed.
	 *  @throws SAXException On any problem with the Parser.
	 *  @throws IOException On any problem with the IO.
	 */
	public XMLElement parse(Reader r) throws SAXException, IOException {
		return this.parse(new InputSource(r));
	}
	
	/**
	 *  Access to the last root element of the last XML-document parsed.
	 *  <p>
	 *  This method is delegated to <code>XMLHandler.getResult()</code>.
	 *  <br>If no document has been parsed or the parsing failed 
	 *  <code>null</code> will be returned.
	 *
	 *  @return The root <code>XMLElement</code> of the last parsed XML 
	 *          input or <code>null</code> if the parsing failed.
	 */
	public XMLElement getResult() {
		if (this.handler==null) {
			return null;
		}
		return this.handler.getResult();
	}
	
	/** Access to the <code>XMLHandler</code> to be used.
	 *  <p>
	 *  If no <code>XMLHandler</code> is currently set, a new
	 *  <code>XMLHandler</code> will be instantiated.
	 *  <p>
	 *  Note that you can overwrite the class creation by 
	 *  subclassing the <code>XMLHandler</code>.
	 *
	 *  @see XMLHandler
	 *  @see #setXMLHandler
	 *
	 *  @return the <XMLHandler</code> instance used for 
	 *          the parse process.
	 */
	 public XMLHandler getXMLHandler() {
		if (this.handler==null) {
			this.handler=new XMLHandler();
		}
		return this.handler;
		 
	}
	
	/** Sets the <code>XMLHandler</code> to be used for parsing.
	 *  <p>
	 *  Note that you can overwrite the class creation by 
	 *  subclassing the <code>XMLHandler</code>.
	 *  <p>
	 *  If the specified parameter is <code>null</code> a 
	 *  new default <code>XMLHandler</code> will be created
	 *  during the parse process.
	 *
	 *  @param xhandler <code>XMLHandler</code> to be used.
	 */
	 public void setXMLHandler(XMLHandler xhandler) {
		this.handler=xhandler;
		 
	}
	
}