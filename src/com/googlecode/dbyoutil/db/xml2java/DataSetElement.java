/*
 *  DataSetElement.java
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
 *  Class corresponding to a <i>dataSet</i> element in XML.
 *  <p>
 *  @author Generated with jNerd's XML2Java tool Version 1.2 Preview 2 on Fri Jul 04 19:50:19 EDT 2008
 *  @author <br>Please send BugReports to <a href="mailto:xml2java@jNerd.de">xml2java@jNerd.de</a>
 */
public class DataSetElement  implements XMLElement {
	/** The name of this class' corresponding XML element. (<i>"dataSet"</i>) */
	public static final String ELEMENT_NAME="dataSet";
	//----------------------------------------------------------------------------- attribute member
	/** Property <code>count</code> is corresponding
	 *  to the <i>dataSet</i> element's
	 *  <i>count</i> attribute.
	 */
	private String count;
	/** Property <code>tag</code> is corresponding
	 *  to the <i>dataSet</i> element's
	 *  <i>tag</i> attribute.
	 */
	private String tag;
	//----------------------------------------------------------------------------- content member
	/** The nested <i>row1</i> element
	 */
	private Row1 row1;
	/** The nested <i>row2</i> element
	 */
	private Row2 row2;
	/** The nested <i>row3</i> element
	 */
	private Row3 row3;
	/** The nested <i>row4</i> element
	 */
	private Row4 row4;
	/** The nested <i>row5</i> element
	 */
	private Row5 row5;
	/** The nested <i>row6</i> element
	 */
	private Row6 row6;
	/** The nested <i>row7</i> element
	 */
	private Row7 row7;
	/** The nested <i>row8</i> element
	 */
	private Row8 row8;
	/** The nested <i>row9</i> element
	 */
	private Row9 row9;
	/** The nested <i>row10</i> element
	 */
	private Row10 row10;
	//----------------------------------------------------------------------------- element name access
	/** Accesses the XML element name.
	 *  @return <i>"dataSet"</i> which is this class' corresponding XML Element.
	 */
	public String getElementName(){
		return ELEMENT_NAME;
	}
	
	//----------------------------------------------------------------------------- attribute access
	/** Accesses the <code>count</code> property.<br>
	 *  The property <code>count</code> corresponds to
	 *  the <i>dataSet</i> element's <i>count</i> attribute.
	 *
	 *  @return A <code>String</code> representing the value of the <code>count</code> property.
	 */
	public String getCount() {
		return this.count;
	}
	
	/** Sets the <code>count</code> property.<br>
	 *  The <code>count</code> property corresponds to the
	 *  <i>dataSet</i> element's <i>count</i> attribute.<br>
	 *  Note that this attribute is required and may not be set to <code>null</code>
	 *
	 *  @param count the new value of the <code>count</code> property.
	 *  @throw IllegalArgumentException If the new value <code>count</code> for this property is <code>null</code>.
	 */
	public void setCount(String count) {
		if (count==null) {
			throw new IllegalArgumentException("The attribute 'count' is required and may not be null.");
		}
		this.count=count;
	}
	
	/** Accesses the <code>tag</code> property.<br>
	 *  The property <code>tag</code> corresponds to
	 *  the <i>dataSet</i> element's <i>tag</i> attribute.
	 *
	 *  @return A <code>String</code> representing the value of the <code>tag</code> property.
	 */
	public String getTag() {
		return this.tag;
	}
	
	/** Sets the <code>tag</code> property.<br>
	 *  The <code>tag</code> property corresponds to the
	 *  <i>dataSet</i> element's <i>tag</i> attribute.<br>
	 *  Note that this attribute is required and may not be set to <code>null</code>
	 *
	 *  @param tag the new value of the <code>tag</code> property.
	 *  @throw IllegalArgumentException If the new value <code>tag</code> for this property is <code>null</code>.
	 */
	public void setTag(String tag) {
		if (tag==null) {
			throw new IllegalArgumentException("The attribute 'tag' is required and may not be null.");
		}
		this.tag=tag;
	}
	
	//----------------------------------------------------------------------------- content access
	/** Access to property <code>row1</code> 
	 *  corresponding to the nested <i>row1</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row1</i> element is represented by 
	 *  property <code>row1</code>, an instance of
	 *  class <code>Row1</code>.
	 *
	 *  @return Property <code>row1</code>, which is the 
	 *          current nested <i>row1</i> node.
	 *
	 *  @see Row1
	 */
	public Row1 getRow1() {
		return this.row1;
	}
	
	/** Sets this class' nested <i>row1</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row1</i> element is represented by 
	 *  property <code>row1</code>, an instance of
	 *  class <code>Row1</code>.
	 *
	 *  @param prow1 The <code>Row1</code> to be
	 *         set as the property <code>row1</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see Row1
	 */
	public void setRow1(Row1 prow1) {
		this.row1=prow1;
	}
	
	/** Access to property <code>row2</code> 
	 *  corresponding to the nested <i>row2</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row2</i> element is represented by 
	 *  property <code>row2</code>, an instance of
	 *  class <code>Row2</code>.
	 *
	 *  @return Property <code>row2</code>, which is the 
	 *          current nested <i>row2</i> node.
	 *
	 *  @see Row2
	 */
	public Row2 getRow2() {
		return this.row2;
	}
	
	/** Sets this class' nested <i>row2</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row2</i> element is represented by 
	 *  property <code>row2</code>, an instance of
	 *  class <code>Row2</code>.
	 *
	 *  @param prow2 The <code>Row2</code> to be
	 *         set as the property <code>row2</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see Row2
	 */
	public void setRow2(Row2 prow2) {
		this.row2=prow2;
	}
	
	/** Access to property <code>row3</code> 
	 *  corresponding to the nested <i>row3</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row3</i> element is represented by 
	 *  property <code>row3</code>, an instance of
	 *  class <code>Row3</code>.
	 *
	 *  @return Property <code>row3</code>, which is the 
	 *          current nested <i>row3</i> node.
	 *
	 *  @see Row3
	 */
	public Row3 getRow3() {
		return this.row3;
	}
	
	/** Sets this class' nested <i>row3</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row3</i> element is represented by 
	 *  property <code>row3</code>, an instance of
	 *  class <code>Row3</code>.
	 *
	 *  @param prow3 The <code>Row3</code> to be
	 *         set as the property <code>row3</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see Row3
	 */
	public void setRow3(Row3 prow3) {
		this.row3=prow3;
	}
	
	/** Access to property <code>row4</code> 
	 *  corresponding to the nested <i>row4</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row4</i> element is represented by 
	 *  property <code>row4</code>, an instance of
	 *  class <code>Row4</code>.
	 *
	 *  @return Property <code>row4</code>, which is the 
	 *          current nested <i>row4</i> node.
	 *
	 *  @see Row4
	 */
	public Row4 getRow4() {
		return this.row4;
	}
	
	/** Sets this class' nested <i>row4</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row4</i> element is represented by 
	 *  property <code>row4</code>, an instance of
	 *  class <code>Row4</code>.
	 *
	 *  @param prow4 The <code>Row4</code> to be
	 *         set as the property <code>row4</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see Row4
	 */
	public void setRow4(Row4 prow4) {
		this.row4=prow4;
	}
	
	/** Access to property <code>row5</code> 
	 *  corresponding to the nested <i>row5</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row5</i> element is represented by 
	 *  property <code>row5</code>, an instance of
	 *  class <code>Row5</code>.
	 *
	 *  @return Property <code>row5</code>, which is the 
	 *          current nested <i>row5</i> node.
	 *
	 *  @see Row5
	 */
	public Row5 getRow5() {
		return this.row5;
	}
	
	/** Sets this class' nested <i>row5</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row5</i> element is represented by 
	 *  property <code>row5</code>, an instance of
	 *  class <code>Row5</code>.
	 *
	 *  @param prow5 The <code>Row5</code> to be
	 *         set as the property <code>row5</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see Row5
	 */
	public void setRow5(Row5 prow5) {
		this.row5=prow5;
	}
	
	/** Access to property <code>row6</code> 
	 *  corresponding to the nested <i>row6</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row6</i> element is represented by 
	 *  property <code>row6</code>, an instance of
	 *  class <code>Row6</code>.
	 *
	 *  @return Property <code>row6</code>, which is the 
	 *          current nested <i>row6</i> node.
	 *
	 *  @see Row6
	 */
	public Row6 getRow6() {
		return this.row6;
	}
	
	/** Sets this class' nested <i>row6</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row6</i> element is represented by 
	 *  property <code>row6</code>, an instance of
	 *  class <code>Row6</code>.
	 *
	 *  @param prow6 The <code>Row6</code> to be
	 *         set as the property <code>row6</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see Row6
	 */
	public void setRow6(Row6 prow6) {
		this.row6=prow6;
	}
	
	/** Access to property <code>row7</code> 
	 *  corresponding to the nested <i>row7</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row7</i> element is represented by 
	 *  property <code>row7</code>, an instance of
	 *  class <code>Row7</code>.
	 *
	 *  @return Property <code>row7</code>, which is the 
	 *          current nested <i>row7</i> node.
	 *
	 *  @see Row7
	 */
	public Row7 getRow7() {
		return this.row7;
	}
	
	/** Sets this class' nested <i>row7</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row7</i> element is represented by 
	 *  property <code>row7</code>, an instance of
	 *  class <code>Row7</code>.
	 *
	 *  @param prow7 The <code>Row7</code> to be
	 *         set as the property <code>row7</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see Row7
	 */
	public void setRow7(Row7 prow7) {
		this.row7=prow7;
	}
	
	/** Access to property <code>row8</code> 
	 *  corresponding to the nested <i>row8</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row8</i> element is represented by 
	 *  property <code>row8</code>, an instance of
	 *  class <code>Row8</code>.
	 *
	 *  @return Property <code>row8</code>, which is the 
	 *          current nested <i>row8</i> node.
	 *
	 *  @see Row8
	 */
	public Row8 getRow8() {
		return this.row8;
	}
	
	/** Sets this class' nested <i>row8</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row8</i> element is represented by 
	 *  property <code>row8</code>, an instance of
	 *  class <code>Row8</code>.
	 *
	 *  @param prow8 The <code>Row8</code> to be
	 *         set as the property <code>row8</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see Row8
	 */
	public void setRow8(Row8 prow8) {
		this.row8=prow8;
	}
	
	/** Access to property <code>row9</code> 
	 *  corresponding to the nested <i>row9</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row9</i> element is represented by 
	 *  property <code>row9</code>, an instance of
	 *  class <code>Row9</code>.
	 *
	 *  @return Property <code>row9</code>, which is the 
	 *          current nested <i>row9</i> node.
	 *
	 *  @see Row9
	 */
	public Row9 getRow9() {
		return this.row9;
	}
	
	/** Sets this class' nested <i>row9</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row9</i> element is represented by 
	 *  property <code>row9</code>, an instance of
	 *  class <code>Row9</code>.
	 *
	 *  @param prow9 The <code>Row9</code> to be
	 *         set as the property <code>row9</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see Row9
	 */
	public void setRow9(Row9 prow9) {
		this.row9=prow9;
	}
	
	/** Access to property <code>row10</code> 
	 *  corresponding to the nested <i>row10</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row10</i> element is represented by 
	 *  property <code>row10</code>, an instance of
	 *  class <code>Row10</code>.
	 *
	 *  @return Property <code>row10</code>, which is the 
	 *          current nested <i>row10</i> node.
	 *
	 *  @see Row10
	 */
	public Row10 getRow10() {
		return this.row10;
	}
	
	/** Sets this class' nested <i>row10</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>row10</i> element is represented by 
	 *  property <code>row10</code>, an instance of
	 *  class <code>Row10</code>.
	 *
	 *  @param prow10 The <code>Row10</code> to be
	 *         set as the property <code>row10</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see Row10
	 */
	public void setRow10(Row10 prow10) {
		this.row10=prow10;
	}
	
	//----------------------------------------------------------------------------- writer support
	/** Assembles specified and non-default
	 *  Attributes to a <code>XMLWriter.AttributePool</code>.
	 *
	 *  @return All Attributes that have to be written.
	 */
	private XMLWriter.AttributePool processAttributes(){
		XMLWriter.AttributePool ret=new XMLWriter.AttributePool();
		if(this.getCount()!=null){
			ret.add("count",this.getCount());
		}
		if(this.getTag()!=null){
			ret.add("tag",this.getTag());
		}
		return ret;
	}
	
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
		if(this.getRow1()!=null)
			this.getRow1().write(out);
		if(this.getRow2()!=null)
			this.getRow2().write(out);
		if(this.getRow3()!=null)
			this.getRow3().write(out);
		if(this.getRow4()!=null)
			this.getRow4().write(out);
		if(this.getRow5()!=null)
			this.getRow5().write(out);
		if(this.getRow6()!=null)
			this.getRow6().write(out);
		if(this.getRow7()!=null)
			this.getRow7().write(out);
		if(this.getRow8()!=null)
			this.getRow8().write(out);
		if(this.getRow9()!=null)
			this.getRow9().write(out);
		if(this.getRow10()!=null)
			this.getRow10().write(out);
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
		out.writeStartTag(this.getElementName(),this.processAttributes());
		this.writeContent(out);
		out.writeEndTag();
	}
	
}