/*
 *  XMLElement.java
 *  
 *  Generated with jNerd's XML2Java Version 1.2 Preview 2
 *  
 *  Date: Fri Jul 04 19:50:19 EDT 2008
 *  
 *  No warranty; no copyright -- use this as you will.
 */
package com.googlecode.dbyoutil.db.xml2java;
/**
 *  All classes in package <code>org.apache.tool.db.xml2java</code> which 
 *  correspond directly to an element in XML implement
 *  this interface.
 *  <p>
 *  @author Generated with jNerd's XML2Java tool Version 1.2 Preview 2 on Fri Jul 04 19:50:19 EDT 2008
 *  @author <br>Please send BugReports to <a href="mailto:xml2java@jNerd.de">xml2java@jNerd.de</a>
 */
public interface XMLElement  extends XMLContent  {
	/** Access to XML element's name the implementing class 
	 *  corresponds to, as used in the DTD/XML-Document.
	 *  @return The elemnt's name as used in the DTD/XML document.
	 */
	String getElementName();
}