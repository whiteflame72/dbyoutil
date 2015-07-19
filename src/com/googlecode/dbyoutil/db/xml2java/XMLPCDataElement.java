/*
 *  XMLPCDataElement.java
 *  
 *  Generated with jNerd's XML2Java Version 1.2 Preview 2
 *  
 *  Date: Fri Jul 04 19:50:19 EDT 2008
 *  
 *  No warranty; no copyright -- use this as you will.
 */
package com.googlecode.dbyoutil.db.xml2java;
/**
 *  An interface for all Elements containing PCData.
 *  <p>
 *  @author Generated with jNerd's XML2Java tool Version 1.2 Preview 2 on Fri Jul 04 19:50:19 EDT 2008
 *  @author <br>Please send BugReports to <a href="mailto:xml2java@jNerd.de">xml2java@jNerd.de</a>
 */
public interface XMLPCDataElement  extends XMLElement  {
	/** Adds more <tt>PCDATA</tt> to the currently
	 *  declared <tt>PCDATA</tt>.<br>
	 *  Strickly speaking, it appends the specified 
	 *  <code>String</code> to the current 
	 *  character sequence stored in this class'
	 *  <code>pcdata</code> property.
	 *
	 *  @param s <tt>PCDATA</tt> to be added.
	 *
	 *  @return <code>true</code> as the <code>pcdata</code>
	 *          property will change anyway.
	 */
	boolean add(String s);
}