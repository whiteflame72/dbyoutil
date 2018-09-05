/*
 *  XMLContent.java
 *  
 *  Generated with jNerd's XML2Java Version 1.2 Preview 2
 *  
 *  Date: Fri Jul 04 19:50:19 EDT 2008
 *  
 *  No warranty; no copyright -- use this as you will.
 */
package org.apache.tool.db.xml2java;
import java.io.IOException;
/**
 *  A marker interface for all content that can be contained within
 *  a class corresponding to a xml element.
 *  <p>
 *  This interface also specifies the method <code>write</code>,
 *  which will be called by the <code>XMLWriter</code> while XML serialisation.
 *  <p>
 *  @author Generated with jNerd's XML2Java tool Version 1.2 Preview 2 on Fri Jul 04 19:50:19 EDT 2008
 *  @author <br>Please send BugReports to <a href="mailto:xml2java@jNerd.de">xml2java@jNerd.de</a>
 */
public interface XMLContent   {
	/** This Method is called while serializing this class
	 *  to a <code>XMLWriter</code>.
	 *  <p>
	 *  Do not call this method, use 
	 *  <code>XMLWriter.write(XMLElement)</code> instead.
	 *  
	 *  @throws <code>java.io.IOException</code> If an I/O error occurs
	 *
	 *  @see XMLWriter#write(XMLElement)
	 */
	void write(XMLWriter out) throws IOException;
}