/*
 *  SqlText.java
 *  
 *  Generated with jNerd's XML2Java Version 1.2 Preview 2
 *  
 *  Date: Fri Jul 04 19:50:19 EDT 2008
 *  
 *  No warranty; no copyright -- use this as you will.
 */
package com.googlecode.dbyoutil.db.xml2java;
import java.io.IOException;
/**
 *  Class corresponding to a <i>SQL_TEXT</i> element in XML.
 *  <p>
 *  @author Generated with jNerd's XML2Java tool Version 1.2 Preview 2 on Fri Jul 04 19:50:19 EDT 2008
 *  @author <br>Please send BugReports to <a href="mailto:xml2java@jNerd.de">xml2java@jNerd.de</a>
 */
public class SqlText  implements XMLPCDataElement {
	/** The name of this class' corresponding XML element. (<i>"SQL_TEXT"</i>) */
	public static final String ELEMENT_NAME="SQL_TEXT";
	//----------------------------------------------------------------------------- attribute member
	/* XML element SQL_TEXT has no attributes */
	//----------------------------------------------------------------------------- content member
	/** Member for all <tt>PCDATA</tt> within this
	 *  class' corresponding element tags.
	 */
	private String pcdata;
	//----------------------------------------------------------------------------- element name access
	/** Accesses the XML element name.
	 *  @return <i>"SQL_TEXT"</i> which is this class' corresponding XML Element.
	 */
	public String getElementName(){
		return ELEMENT_NAME;
	}
	
	//----------------------------------------------------------------------------- attribute access
	/* XML element SQL_TEXT has no attributes */
	//----------------------------------------------------------------------------- content access
	/** Access to all <tt>PCDATA</tt>
	 *  within this class' corresponding element
	 *  tags.
	 */
	public String getPCData() {
		return this.pcdata;
	}
	
	/** Sets the <tt>PCDATA</tt>
	 *  within this class' corresponding element
	 *  tags.
	 */
	public void setPCData(String pcdata) {
		this.pcdata=pcdata;
	}
	
	/** Adds more <tt>PCDATA</tt> to the currently
	 *  declared <tt>PCDATA</tt>.<br>
	 *  Strickly speaking, it appends the specified 
	 *  <code>String</code> to the current 
	 *  character sequence stored in this class'
	 *  <code>pcdata</code> property.
	 *
	 *  @param pcd <tt>PCDATA</tt> to be added.
	 *
	 *  @return <code>true</code> as the <code>pcdata</code>
	 *          property will change anyway.
	 */
	public boolean add(String pcd) {
		if (this.pcdata==null) {
			this.pcdata="";
		}
		this.pcdata+=pcd;
		return true;
	}
	
	//----------------------------------------------------------------------------- writer support
	/** Method called for writing this class' content
	 *  to the specified <code>XMLWriter</code>.
	 *
	 *  @param out <code>XMLWriter</code> to be used.
	 *
	 *  @throws IOException On any I/O misbehaviour.
	 *
	 *  @see #write
	 */
	private void writeContent(XMLWriter out) throws IOException{
		if(this.getPCData()!=null){
			out.write(this.getPCData());
		}
	}
	
	/** Method called for writing this class to the specified
	 *  <code>XMLWriter</code>.
	 *  <p>
	 *  This method will be called for every Element during 
	 *  the write process. If the write process has not started yet,
	 *  the call will be delegated to <code>XMLWriter.write(XMLElement)</code>
	 *  to output the XML-Prolog.
	 *
	 *  @param out <code>XMLWriter</code> to be used.
	 *
	 *  @throws IOException On any I/O misbehaviour.
	 *
	 *  @see XMLWriter#write(XMLElement)
	 */
	public void write(XMLWriter out) throws IOException{
		if(!out.hasWritingStarted()){
			out.write(this);
			return;
		}
		out.writeStartTag(this.getElementName(),null);
		this.writeContent(out);
		out.writeEndTag();
	}
	
}