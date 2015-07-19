/*
 *  DataEnvelope.java
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
 *  Class corresponding to a <i>dataEnvelope</i> element in XML.
 *  <p>
 *  @author Generated with jNerd's XML2Java tool Version 1.2 Preview 2 on Fri Jul 04 19:50:19 EDT 2008
 *  @author <br>Please send BugReports to <a href="mailto:xml2java@jNerd.de">xml2java@jNerd.de</a>
 */
public class DataEnvelope  implements XMLElement {
	/** The name of this class' corresponding XML element. (<i>"dataEnvelope"</i>) */
	public static final String ELEMENT_NAME="dataEnvelope";
	//----------------------------------------------------------------------------- attribute member
	/* XML element dataEnvelope has no attributes */
	//----------------------------------------------------------------------------- content member
	/** The nested <i>dataSet</i> element
	 */
	private DataSetElement dataSetElement;
	//----------------------------------------------------------------------------- element name access
	/** Accesses the XML element name.
	 *  @return <i>"dataEnvelope"</i> which is this class' corresponding XML Element.
	 */
	public String getElementName(){
		return ELEMENT_NAME;
	}
	
	//----------------------------------------------------------------------------- attribute access
	/* XML element dataEnvelope has no attributes */
	//----------------------------------------------------------------------------- content access
	/** Access to property <code>dataSetElement</code> 
	 *  corresponding to the nested <i>dataSet</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>dataSet</i> element is represented by 
	 *  property <code>dataSetElement</code>, an instance of
	 *  class <code>DataSetElement</code>.
	 *
	 *  @return Property <code>dataSetElement</code>, which is the 
	 *          current nested <i>dataSet</i> node.
	 *
	 *  @see DataSetElement
	 */
	public DataSetElement getDataSetElement() {
		return this.dataSetElement;
	}
	
	/** Sets this class' nested <i>dataSet</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>dataSet</i> element is represented by 
	 *  property <code>dataSetElement</code>, an instance of
	 *  class <code>DataSetElement</code>.
	 *
	 *  @param pdataSetElement The <code>DataSetElement</code> to be
	 *         set as the property <code>dataSetElement</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see DataSetElement
	 */
	public void setDataSetElement(DataSetElement pdataSetElement) {
		this.dataSetElement=pdataSetElement;
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
		if(this.getDataSetElement()!=null)
			this.getDataSetElement().write(out);
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