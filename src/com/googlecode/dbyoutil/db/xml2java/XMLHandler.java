/*
 *  XMLHandler.java
 *  
 *  Generated with jNerd's XML2Java Version 1.2 Preview 2
 *  
 *  Date: Fri Jul 04 19:50:19 EDT 2008
 *  
 *  No warranty; no copyright -- use this as you will.
 */
package com.googlecode.dbyoutil.db.xml2java;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
/**
 *  The 'heart' of the <code>SAX2Reader</code>.<p>
 *  The XMLHandler dispatches the SAX events and delegates them to the appropriate SubHandler.
 *  @see SAX2Reader
 *  <p>
 *  @author Generated with jNerd's XML2Java tool Version 1.2 Preview 2 on Fri Jul 04 19:50:19 EDT 2008
 *  @author <br>Please send BugReports to <a href="mailto:xml2java@jNerd.de">xml2java@jNerd.de</a>
 */
public class XMLHandler extends DefaultHandler {
	private final Map[] subHandlers=new HashMap[ARRAY_SIZE];
	private Stack stack;
	private StringBuffer sb=null;
	private SubHandler result;
	/** Constructs a new XMLHandler <br>
	 *  You can use an instance of <code>XMLHandler</code> several times, if you want to.
	 *  Normally you don't have to bother with this killer class. It is much
	 *  easier to use the <code>SAX2Reader</code> instead.
	 *
	 *  @see SAX2Reader
	 */
	public XMLHandler() {
		this.stack=new Stack();
		this.subHandlers[0]=new HashMap();
		this.subHandlers[0].put("ELAPSED_SECONDS",new ElapsedSecondsHandler());
		this.subHandlers[0].put("MACHINE",new MachineHandler());
		this.subHandlers[0].put("SQL_TEXT",new SqlTextHandler());
		this.subHandlers[0].put("row1",new Row1Handler());
		this.subHandlers[0].put("CPU_TIME",new CpuTimeHandler());
		this.subHandlers[0].put("row4",new Row4Handler());
		this.subHandlers[0].put("row5",new Row5Handler());
		this.subHandlers[0].put("OPTIMIZER_COST",new OptimizerCostHandler());
		this.subHandlers[0].put("row2",new Row2Handler());
		this.subHandlers[0].put("row3",new Row3Handler());
		this.subHandlers[0].put("row8",new Row8Handler());
		this.subHandlers[0].put("row9",new Row9Handler());
		this.subHandlers[0].put("BUFFER_GETS",new BufferGetsHandler());
		this.subHandlers[0].put("row6",new Row6Handler());
		this.subHandlers[0].put("dataSet",new DataSetElementHandler());
		this.subHandlers[0].put("row7",new Row7Handler());
		this.subHandlers[0].put("dataEnvelope",new DataEnvelopeHandler());
		this.subHandlers[0].put("LAST_ACTIVE_TIME",new LastActiveTimeHandler());
		this.subHandlers[0].put("row10",new Row10Handler());
		this.subHandlers[0].put("USERNAME",new UsernameHandler());
		this.subHandlers[0].put("DISK_READS",new DiskReadsHandler());
	}
	
	/** Receive notification of the beginning of the document. 
	 *  <p>
	 *  If you overwrite this method, do not forget to call
	 *  <code>super.startDocument()</code>
	 *
	 *  @throw SAXException Any SAX exception, possibly wrapping another exception.
	 *         This is the case if the document is invalid and therefor a content model 
	 *         could not be constructed correctly.
	 */
	public void startDocument() throws SAXException{
		this.stack=new Stack();
		this.result=null;
	}
	
	/** Receive notification of the end of the document. 
	 *  <p>
	 *  If IDREF-Attributes are present within the document
	 *  they get associated with the referenced elements.
	 *  <p>
	 *  If you overwrite this method, do not forget to call
	 *  <code>super.endDocument()</code>.
	 *
	 *  @throw SAXException Any SAX exception, possibly wrapping another exception.
	 *         This is the case if the document is invalid and therefor a content model 
	 *         could not be constructed correctly.
	 */
	public void endDocument() throws SAXException {
		if (!this.stack.isEmpty()) {
			throw this.invalidState(new IllegalStateException("Stack is not empty at the end of document."));
		}
	}
	
	/** Receive notification of the start of an element.
	 *  <p>
	 *  This call will be delegated to the appriate <code>SubHandler</code>.
	 *
	 *  @param name The element type name.
	 *  @param attributes The specified or defaulted attributes.
	 *  @throw SAXException Any SAX exception, possibly wrapping another exception.
	 */
	 public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
		this.processPCData();
		this.getSubHandler(localName).startElement(uri,localName,qName,attributes);
	}
	
	/** Receive notification of the end of an element.
	 *  <p>
	 *  This call will be delegated to the appriate <code>SubHandler</code>.
	 *
	 *  @param name The element type name.
	 *  @param attributes The specified or defaulted attributes.
	 *  @throw SAXException Any SAX exception, possibly wrapping another exception.
	 */
	 public void endElement(String uri,String localName,String qName) throws SAXException {
		this.processPCData();
		((SubHandler) this.stack.peek()).endElement(uri,localName,qName);
	}
	
	/** Returns the root element of the parsed document.
	 *
	 *  @return the root node of the parsed document or <code>null</code>
	 *          if an eror occurred while parsing or nothing was parsed yet.
	 */
	public XMLElement getResult() {
		if (this.result==null) {
			return null;
		}
		return (XMLElement) this.result.getXMLContent();
	}
	
	/** Factory Method to instantiate a <code>ElapsedSeconds</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>ElapsedSeconds</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected ElapsedSeconds createElapsedSeconds() {
		return new ElapsedSeconds();
	}
	
	/** Factory Method to instantiate a <code>Machine</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>Machine</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected Machine createMachine() {
		return new Machine();
	}
	
	/** Factory Method to instantiate a <code>SqlText</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>SqlText</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected SqlText createSqlText() {
		return new SqlText();
	}
	
	/** Factory Method to instantiate a <code>Row1</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>Row1</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected Row1 createRow1() {
		return new Row1();
	}
	
	/** Factory Method to instantiate a <code>CpuTime</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>CpuTime</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected CpuTime createCpuTime() {
		return new CpuTime();
	}
	
	/** Factory Method to instantiate a <code>Row4</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>Row4</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected Row4 createRow4() {
		return new Row4();
	}
	
	/** Factory Method to instantiate a <code>Row5</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>Row5</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected Row5 createRow5() {
		return new Row5();
	}
	
	/** Factory Method to instantiate a <code>OptimizerCost</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>OptimizerCost</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected OptimizerCost createOptimizerCost() {
		return new OptimizerCost();
	}
	
	/** Factory Method to instantiate a <code>Row2</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>Row2</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected Row2 createRow2() {
		return new Row2();
	}
	
	/** Factory Method to instantiate a <code>Row3</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>Row3</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected Row3 createRow3() {
		return new Row3();
	}
	
	/** Factory Method to instantiate a <code>Row8</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>Row8</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected Row8 createRow8() {
		return new Row8();
	}
	
	/** Factory Method to instantiate a <code>Row9</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>Row9</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected Row9 createRow9() {
		return new Row9();
	}
	
	/** Factory Method to instantiate a <code>BufferGets</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>BufferGets</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected BufferGets createBufferGets() {
		return new BufferGets();
	}
	
	/** Factory Method to instantiate a <code>Row6</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>Row6</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected Row6 createRow6() {
		return new Row6();
	}
	
	/** Factory Method to instantiate a <code>DataSetElement</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>DataSetElement</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected DataSetElement createDataSetElement() {
		return new DataSetElement();
	}
	
	/** Factory Method to instantiate a <code>Row7</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>Row7</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected Row7 createRow7() {
		return new Row7();
	}
	
	/** Factory Method to instantiate a <code>DataEnvelope</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>DataEnvelope</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected DataEnvelope createDataEnvelope() {
		return new DataEnvelope();
	}
	
	/** Factory Method to instantiate a <code>LastActiveTime</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>LastActiveTime</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected LastActiveTime createLastActiveTime() {
		return new LastActiveTime();
	}
	
	/** Factory Method to instantiate a <code>Row10</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>Row10</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected Row10 createRow10() {
		return new Row10();
	}
	
	/** Factory Method to instantiate a <code>Username</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>Username</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected Username createUsername() {
		return new Username();
	}
	
	/** Factory Method to instantiate a <code>DiskReads</code> object.
	 *  <p>
	 *  Overwrite this method if you packed business logic into a sub class
	 *  of <code>DiskReads</code> and return an appropriate instance.
	 *  <br>
	 *  Do not return <code>null</code>. This would result in a
	 *  <code>NullPointerException</code> and the parse process will fail.
	 */
	protected DiskReads createDiskReads() {
		return new DiskReads();
	}
	
	public void characters(char[] ch,int start,int length) throws SAXException{
		if(sb==null){
			sb=new StringBuffer();
		}
		sb.append(ch,start,length);
	}
	
	private void processPCData(){
		if (sb==null) {
			return;
		}
		String pcdata=this.sb.toString();
		XMLContent current=((SubHandler) this.stack.peek()).getXMLContent();
		if(current instanceof XMLPCDataElement){
			((XMLPCDataElement) current).add(pcdata);
			sb=null;
			return;
		}
		if (!pcdata.trim().equals("")) {
			System.err.println("WARNING!: PCDATA lost ('"+pcdata.trim()+"') target:"+current);
		}
	}
	
	private SubHandler getSubHandler(String localName) throws SAXException{
		SubHandler ret;
		if (this.stack.isEmpty()) {
			// the first time (when the stack is still empty. we have to 
			// search for a handler for the root element.
			if (this.result!=null) {
				// something is terribly wrong!
				throw this.invalidState(new IllegalStateException("Result is present, but stack is empty."));
			}
			ret=(SubHandler) this.subHandlers[0].get(localName);
			// we don't need a new instance, by definition the subHandlers[0] only 
			// contains potential root elements
			this.result=ret;
			this.stack.push(ret);
			if (ret==null) {
				  throw new SAXException("No Handler found for root element "+localName+".");
			}
			return ret;
		}
		// the SubHandler on top of the stack knows what to do!
		return ((SubHandler) this.stack.peek()).getSubHandler(localName);
	}
	
	/** Called if the XML document is judged invalid.
	 *  <p> 
	 *  It simply build a SAXException, complaining about an unexpected element.
	 *  Normally a validation exception should be throw by the underlying SAX
	 *  parser.
	 *
	 *  @param encounteredElement The unexpected element.
	 *
	 *  @return a new SAXException.
	 */
	private SAXException invalidDocument(String encounteredElement) throws SAXException {
		return new SAXException("The parsed XML document is invalid. Unexpected element '"+encounteredElement+"'.");
	}
	
	/** Called if something is wrong with the generated code.
	 *  <p>
	 *  This should of course never happen. If it does, please be
	 *  so kind an d send me a bug report (the source DTD would be a
	 *  great help).<br>
	 *  mailto:xml2java@jNerd.de
	 *  <p> 
	 *  It simply builds a SAXException, because of a method that should
	 *  never be called was called.
	 *
	 *  @return A new SAXException.
	 */
	private SAXException invalidMethod() throws SAXException {
		return this.invalidState(new IllegalStateException("Method should never be called."));
	}
	
	/** Called if something is wrong with the generated code.
	 *  <p>
	 *  This should of course never happen. If it does, please be
	 *  so kind an d send me a bug report (the source DTD would be a
	 *  great help).<br>
	 *  mailto:xml2java@jNerd.de
	 *  <p> 
	 *  It simply builds a SAXException, because there was 
	 *  a unexcepted SubHandler on top of the stack.
	 *
	 *  @return A new SAXException.
	 */
	private SAXException invalidStack() throws SAXException {
		return this.invalidState(new IllegalStateException("Stack misbehaviour."));
	}
	
	/** Called if some thing is wrong with the genrerated code.
	 *  <p>
	 *  This should of course never happen. It it does so please be
	 *  so kind an d send me a bug report (the source DTD would be a
	 *  great help).<br>
	 *  mailto:xml2java@jNerd.de	 
	 *  <p> 
	 *  It simply builds a SAXException.
	 *
	 *  @return A new SAXException.
	 */
	private SAXException invalidState(Exception damn) throws SAXException {
		return new SAXException("Generated Code is not working properly!",damn);
	}
	
	//-----------------------------------------------------------\
	//-------------------- SubHandler Section -------------------\
	//-----------------------------------------------------------\
	private abstract class SubHandler extends DefaultHandler {
		protected SubHandler getSubHandler(String localName) throws SAXException {
			SubHandler ret=(SubHandler) XMLHandler.this.subHandlers[this.index()].get(localName);
			if (ret==null) {
				  return this.noHandlerFound(localName);
			}
			ret=ret.getInstance();
			XMLHandler.this.stack.push(ret);
			return ret;
		}
		protected SubHandler noHandlerFound(String localName) throws SAXException {
			throw new SAXException("No Handler found for Element "+localName+".");
		}
		protected int index() {
			return 0;
		}
		protected abstract SubHandler getInstance() throws SAXException;
		protected abstract XMLContent getXMLContent();
	}
	
	interface XMLContentAddable {
		//protected abstract 
		void add(XMLContent particle) throws SAXException;
	}
	
	private abstract class SequenceContentSubHandler extends SubHandler implements XMLContentAddable{
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			throw XMLHandler.this.invalidMethod();
		}
		public void endElement(String uri,String localName,String qName) throws SAXException{
			if (XMLHandler.this.stack.pop()!=this) {
				throw XMLHandler.this.invalidStack();
			}
			((XMLContentAddable) XMLHandler.this.stack.peek()).add(this.getXMLContent());
			XMLHandler.this.endElement(uri,localName,qName);
		}
	}
	
	private abstract class ElementSubHandler extends SubHandler {
		protected SubHandler getSubHandler(String localName) throws SAXException {
			// if startElement hasn't been called yet. this is the Handler
			if (this.getXMLContent()==null) {
				return this;
			}
			return super.getSubHandler(localName);
		}
		public void endElement(String uri,String localName,String qName) throws SAXException {
			if (XMLHandler.this.stack.pop()!=this) {
				throw XMLHandler.this.invalidStack();
			}
			if (!XMLHandler.this.stack.isEmpty()) {
				((XMLContentAddable) XMLHandler.this.stack.peek()).add(this.getXMLContent());
			}
		}
		public abstract void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException;
	}
	
	private abstract class ChoiceElementSubHandler extends ElementSubHandler implements XMLContentAddable {
		protected SubHandler getSubHandler(String localName) throws SAXException {
			if (this.getXMLContent()==null) {
				return this;
			}
			// otherwise look for what is the right choice SubHandler,
			// push it to the stack and see what it suggests
			SubHandler sub=super.getSubHandler(localName);
			return sub.getSubHandler(localName);
		}
		protected abstract int index();
	}
	
	private abstract class ChoiceContentSubHandler extends SubHandler implements XMLContentAddable {
		protected SubHandler getSubHandler(String localName) throws SAXException {
			// we are not interrested in startingElements
			SubHandler sub=super.getSubHandler(localName);
			return sub.getSubHandler(localName);
		}
		public void endElement(String uri,String localName,String qName) throws SAXException {
			throw XMLHandler.this.invalidMethod();
		}
		protected abstract int index();
		protected XMLContent getXMLContent() {
			return null;
		}
		public void add(XMLContent content) throws SAXException {
			if (XMLHandler.this.stack.pop()!=this) {
				throw XMLHandler.this.invalidStack();
			}
			((XMLContentAddable) XMLHandler.this.stack.peek()).add(content);
		}
	}
	
	class ElapsedSecondsHandler extends ElementSubHandler {
		private ElapsedSeconds pcdataElement;
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final ElapsedSeconds element=XMLHandler.this.createElapsedSeconds();
			this.pcdataElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.pcdataElement;
		}
		protected SubHandler getInstance() {
			return new ElapsedSecondsHandler();
		}
	}
	
	class MachineHandler extends ElementSubHandler {
		private Machine pcdataElement;
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final Machine element=XMLHandler.this.createMachine();
			this.pcdataElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.pcdataElement;
		}
		protected SubHandler getInstance() {
			return new MachineHandler();
		}
	}
	
	class SqlTextHandler extends ElementSubHandler {
		private SqlText pcdataElement;
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final SqlText element=XMLHandler.this.createSqlText();
			this.pcdataElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.pcdataElement;
		}
		protected SubHandler getInstance() {
			return new SqlTextHandler();
		}
	}
	
	class Row1Handler extends ElementSubHandler implements XMLContentAddable{
		private int slot=0;
		private Row1 sequenceElement=null;
		protected SubHandler getSubHandler(String localName) throws SAXException {
			if (this.getXMLContent()==null) {
				return this;
			}
			// required single
			if(this.slot<1){
				if("MACHINE".equals(localName)){
					this.slot=1;
					XMLHandler.this.stack.push(new MachineHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<2){
				if("USERNAME".equals(localName)){
					this.slot=2;
					XMLHandler.this.stack.push(new UsernameHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<3){
				if("LAST_ACTIVE_TIME".equals(localName)){
					this.slot=3;
					XMLHandler.this.stack.push(new LastActiveTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<4){
				if("ELAPSED_SECONDS".equals(localName)){
					this.slot=4;
					XMLHandler.this.stack.push(new ElapsedSecondsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<5){
				if("DISK_READS".equals(localName)){
					this.slot=5;
					XMLHandler.this.stack.push(new DiskReadsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<6){
				if("BUFFER_GETS".equals(localName)){
					this.slot=6;
					XMLHandler.this.stack.push(new BufferGetsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<7){
				if("CPU_TIME".equals(localName)){
					this.slot=7;
					XMLHandler.this.stack.push(new CpuTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<8){
				if("OPTIMIZER_COST".equals(localName)){
					this.slot=8;
					XMLHandler.this.stack.push(new OptimizerCostHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<9){
				if("SQL_TEXT".equals(localName)){
					this.slot=9;
					XMLHandler.this.stack.push(new SqlTextHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			throw XMLHandler.this.invalidDocument(localName);
		}
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final Row1 element=XMLHandler.this.createRow1();
			this.sequenceElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.sequenceElement;
		}
		public void add(XMLContent content) throws SAXException {
			//Exception e=null;
			try {
				switch (this.slot) {
					case 1:this.sequenceElement.setMachine((Machine) content);
					return;
					case 2:this.sequenceElement.setUsername((Username) content);
					return;
					case 3:this.sequenceElement.setLastActiveTime((LastActiveTime) content);
					return;
					case 4:this.sequenceElement.setElapsedSeconds((ElapsedSeconds) content);
					return;
					case 5:this.sequenceElement.setDiskReads((DiskReads) content);
					return;
					case 6:this.sequenceElement.setBufferGets((BufferGets) content);
					return;
					case 7:this.sequenceElement.setCpuTime((CpuTime) content);
					return;
					case 8:this.sequenceElement.setOptimizerCost((OptimizerCost) content);
					return;
					case 9:this.sequenceElement.setSqlText((SqlText) content);
					return;
					default:throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row1'.",new IllegalStateException("Invalid state in '"+this.getClass().getName()+"'. Slot ("+this.slot+") does not exist."));
				}
			}
			 catch(ClassCastException cce) {
				throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row1'."+
					" XML document is not valid.",cce);
			}
		}
		protected SubHandler getInstance() {
			return new Row1Handler();
		}
	}
	
	class CpuTimeHandler extends ElementSubHandler {
		private CpuTime pcdataElement;
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final CpuTime element=XMLHandler.this.createCpuTime();
			this.pcdataElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.pcdataElement;
		}
		protected SubHandler getInstance() {
			return new CpuTimeHandler();
		}
	}
	
	class Row4Handler extends ElementSubHandler implements XMLContentAddable{
		private int slot=0;
		private Row4 sequenceElement=null;
		protected SubHandler getSubHandler(String localName) throws SAXException {
			if (this.getXMLContent()==null) {
				return this;
			}
			// required single
			if(this.slot<1){
				if("MACHINE".equals(localName)){
					this.slot=1;
					XMLHandler.this.stack.push(new MachineHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<2){
				if("USERNAME".equals(localName)){
					this.slot=2;
					XMLHandler.this.stack.push(new UsernameHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<3){
				if("LAST_ACTIVE_TIME".equals(localName)){
					this.slot=3;
					XMLHandler.this.stack.push(new LastActiveTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<4){
				if("ELAPSED_SECONDS".equals(localName)){
					this.slot=4;
					XMLHandler.this.stack.push(new ElapsedSecondsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<5){
				if("DISK_READS".equals(localName)){
					this.slot=5;
					XMLHandler.this.stack.push(new DiskReadsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<6){
				if("BUFFER_GETS".equals(localName)){
					this.slot=6;
					XMLHandler.this.stack.push(new BufferGetsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<7){
				if("CPU_TIME".equals(localName)){
					this.slot=7;
					XMLHandler.this.stack.push(new CpuTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<8){
				if("OPTIMIZER_COST".equals(localName)){
					this.slot=8;
					XMLHandler.this.stack.push(new OptimizerCostHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<9){
				if("SQL_TEXT".equals(localName)){
					this.slot=9;
					XMLHandler.this.stack.push(new SqlTextHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			throw XMLHandler.this.invalidDocument(localName);
		}
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final Row4 element=XMLHandler.this.createRow4();
			this.sequenceElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.sequenceElement;
		}
		public void add(XMLContent content) throws SAXException {
			//Exception e=null;
			try {
				switch (this.slot) {
					case 1:this.sequenceElement.setMachine((Machine) content);
					return;
					case 2:this.sequenceElement.setUsername((Username) content);
					return;
					case 3:this.sequenceElement.setLastActiveTime((LastActiveTime) content);
					return;
					case 4:this.sequenceElement.setElapsedSeconds((ElapsedSeconds) content);
					return;
					case 5:this.sequenceElement.setDiskReads((DiskReads) content);
					return;
					case 6:this.sequenceElement.setBufferGets((BufferGets) content);
					return;
					case 7:this.sequenceElement.setCpuTime((CpuTime) content);
					return;
					case 8:this.sequenceElement.setOptimizerCost((OptimizerCost) content);
					return;
					case 9:this.sequenceElement.setSqlText((SqlText) content);
					return;
					default:throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row4'.",new IllegalStateException("Invalid state in '"+this.getClass().getName()+"'. Slot ("+this.slot+") does not exist."));
				}
			}
			 catch(ClassCastException cce) {
				throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row4'."+
					" XML document is not valid.",cce);
			}
		}
		protected SubHandler getInstance() {
			return new Row4Handler();
		}
	}
	
	class Row5Handler extends ElementSubHandler implements XMLContentAddable{
		private int slot=0;
		private Row5 sequenceElement=null;
		protected SubHandler getSubHandler(String localName) throws SAXException {
			if (this.getXMLContent()==null) {
				return this;
			}
			// required single
			if(this.slot<1){
				if("MACHINE".equals(localName)){
					this.slot=1;
					XMLHandler.this.stack.push(new MachineHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<2){
				if("USERNAME".equals(localName)){
					this.slot=2;
					XMLHandler.this.stack.push(new UsernameHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<3){
				if("LAST_ACTIVE_TIME".equals(localName)){
					this.slot=3;
					XMLHandler.this.stack.push(new LastActiveTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<4){
				if("ELAPSED_SECONDS".equals(localName)){
					this.slot=4;
					XMLHandler.this.stack.push(new ElapsedSecondsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<5){
				if("DISK_READS".equals(localName)){
					this.slot=5;
					XMLHandler.this.stack.push(new DiskReadsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<6){
				if("BUFFER_GETS".equals(localName)){
					this.slot=6;
					XMLHandler.this.stack.push(new BufferGetsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<7){
				if("CPU_TIME".equals(localName)){
					this.slot=7;
					XMLHandler.this.stack.push(new CpuTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<8){
				if("OPTIMIZER_COST".equals(localName)){
					this.slot=8;
					XMLHandler.this.stack.push(new OptimizerCostHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<9){
				if("SQL_TEXT".equals(localName)){
					this.slot=9;
					XMLHandler.this.stack.push(new SqlTextHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			throw XMLHandler.this.invalidDocument(localName);
		}
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final Row5 element=XMLHandler.this.createRow5();
			this.sequenceElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.sequenceElement;
		}
		public void add(XMLContent content) throws SAXException {
			//Exception e=null;
			try {
				switch (this.slot) {
					case 1:this.sequenceElement.setMachine((Machine) content);
					return;
					case 2:this.sequenceElement.setUsername((Username) content);
					return;
					case 3:this.sequenceElement.setLastActiveTime((LastActiveTime) content);
					return;
					case 4:this.sequenceElement.setElapsedSeconds((ElapsedSeconds) content);
					return;
					case 5:this.sequenceElement.setDiskReads((DiskReads) content);
					return;
					case 6:this.sequenceElement.setBufferGets((BufferGets) content);
					return;
					case 7:this.sequenceElement.setCpuTime((CpuTime) content);
					return;
					case 8:this.sequenceElement.setOptimizerCost((OptimizerCost) content);
					return;
					case 9:this.sequenceElement.setSqlText((SqlText) content);
					return;
					default:throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row5'.",new IllegalStateException("Invalid state in '"+this.getClass().getName()+"'. Slot ("+this.slot+") does not exist."));
				}
			}
			 catch(ClassCastException cce) {
				throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row5'."+
					" XML document is not valid.",cce);
			}
		}
		protected SubHandler getInstance() {
			return new Row5Handler();
		}
	}
	
	class OptimizerCostHandler extends ElementSubHandler {
		private OptimizerCost pcdataElement;
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final OptimizerCost element=XMLHandler.this.createOptimizerCost();
			this.pcdataElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.pcdataElement;
		}
		protected SubHandler getInstance() {
			return new OptimizerCostHandler();
		}
	}
	
	class Row2Handler extends ElementSubHandler implements XMLContentAddable{
		private int slot=0;
		private Row2 sequenceElement=null;
		protected SubHandler getSubHandler(String localName) throws SAXException {
			if (this.getXMLContent()==null) {
				return this;
			}
			// required single
			if(this.slot<1){
				if("MACHINE".equals(localName)){
					this.slot=1;
					XMLHandler.this.stack.push(new MachineHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<2){
				if("USERNAME".equals(localName)){
					this.slot=2;
					XMLHandler.this.stack.push(new UsernameHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<3){
				if("LAST_ACTIVE_TIME".equals(localName)){
					this.slot=3;
					XMLHandler.this.stack.push(new LastActiveTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<4){
				if("ELAPSED_SECONDS".equals(localName)){
					this.slot=4;
					XMLHandler.this.stack.push(new ElapsedSecondsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<5){
				if("DISK_READS".equals(localName)){
					this.slot=5;
					XMLHandler.this.stack.push(new DiskReadsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<6){
				if("BUFFER_GETS".equals(localName)){
					this.slot=6;
					XMLHandler.this.stack.push(new BufferGetsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<7){
				if("CPU_TIME".equals(localName)){
					this.slot=7;
					XMLHandler.this.stack.push(new CpuTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<8){
				if("OPTIMIZER_COST".equals(localName)){
					this.slot=8;
					XMLHandler.this.stack.push(new OptimizerCostHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<9){
				if("SQL_TEXT".equals(localName)){
					this.slot=9;
					XMLHandler.this.stack.push(new SqlTextHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			throw XMLHandler.this.invalidDocument(localName);
		}
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final Row2 element=XMLHandler.this.createRow2();
			this.sequenceElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.sequenceElement;
		}
		public void add(XMLContent content) throws SAXException {
			//Exception e=null;
			try {
				switch (this.slot) {
					case 1:this.sequenceElement.setMachine((Machine) content);
					return;
					case 2:this.sequenceElement.setUsername((Username) content);
					return;
					case 3:this.sequenceElement.setLastActiveTime((LastActiveTime) content);
					return;
					case 4:this.sequenceElement.setElapsedSeconds((ElapsedSeconds) content);
					return;
					case 5:this.sequenceElement.setDiskReads((DiskReads) content);
					return;
					case 6:this.sequenceElement.setBufferGets((BufferGets) content);
					return;
					case 7:this.sequenceElement.setCpuTime((CpuTime) content);
					return;
					case 8:this.sequenceElement.setOptimizerCost((OptimizerCost) content);
					return;
					case 9:this.sequenceElement.setSqlText((SqlText) content);
					return;
					default:throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row2'.",new IllegalStateException("Invalid state in '"+this.getClass().getName()+"'. Slot ("+this.slot+") does not exist."));
				}
			}
			 catch(ClassCastException cce) {
				throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row2'."+
					" XML document is not valid.",cce);
			}
		}
		protected SubHandler getInstance() {
			return new Row2Handler();
		}
	}
	
	class Row3Handler extends ElementSubHandler implements XMLContentAddable{
		private int slot=0;
		private Row3 sequenceElement=null;
		protected SubHandler getSubHandler(String localName) throws SAXException {
			if (this.getXMLContent()==null) {
				return this;
			}
			// required single
			if(this.slot<1){
				if("MACHINE".equals(localName)){
					this.slot=1;
					XMLHandler.this.stack.push(new MachineHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<2){
				if("USERNAME".equals(localName)){
					this.slot=2;
					XMLHandler.this.stack.push(new UsernameHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<3){
				if("LAST_ACTIVE_TIME".equals(localName)){
					this.slot=3;
					XMLHandler.this.stack.push(new LastActiveTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<4){
				if("ELAPSED_SECONDS".equals(localName)){
					this.slot=4;
					XMLHandler.this.stack.push(new ElapsedSecondsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<5){
				if("DISK_READS".equals(localName)){
					this.slot=5;
					XMLHandler.this.stack.push(new DiskReadsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<6){
				if("BUFFER_GETS".equals(localName)){
					this.slot=6;
					XMLHandler.this.stack.push(new BufferGetsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<7){
				if("CPU_TIME".equals(localName)){
					this.slot=7;
					XMLHandler.this.stack.push(new CpuTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<8){
				if("OPTIMIZER_COST".equals(localName)){
					this.slot=8;
					XMLHandler.this.stack.push(new OptimizerCostHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<9){
				if("SQL_TEXT".equals(localName)){
					this.slot=9;
					XMLHandler.this.stack.push(new SqlTextHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			throw XMLHandler.this.invalidDocument(localName);
		}
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final Row3 element=XMLHandler.this.createRow3();
			this.sequenceElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.sequenceElement;
		}
		public void add(XMLContent content) throws SAXException {
			//Exception e=null;
			try {
				switch (this.slot) {
					case 1:this.sequenceElement.setMachine((Machine) content);
					return;
					case 2:this.sequenceElement.setUsername((Username) content);
					return;
					case 3:this.sequenceElement.setLastActiveTime((LastActiveTime) content);
					return;
					case 4:this.sequenceElement.setElapsedSeconds((ElapsedSeconds) content);
					return;
					case 5:this.sequenceElement.setDiskReads((DiskReads) content);
					return;
					case 6:this.sequenceElement.setBufferGets((BufferGets) content);
					return;
					case 7:this.sequenceElement.setCpuTime((CpuTime) content);
					return;
					case 8:this.sequenceElement.setOptimizerCost((OptimizerCost) content);
					return;
					case 9:this.sequenceElement.setSqlText((SqlText) content);
					return;
					default:throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row3'.",new IllegalStateException("Invalid state in '"+this.getClass().getName()+"'. Slot ("+this.slot+") does not exist."));
				}
			}
			 catch(ClassCastException cce) {
				throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row3'."+
					" XML document is not valid.",cce);
			}
		}
		protected SubHandler getInstance() {
			return new Row3Handler();
		}
	}
	
	class Row8Handler extends ElementSubHandler implements XMLContentAddable{
		private int slot=0;
		private Row8 sequenceElement=null;
		protected SubHandler getSubHandler(String localName) throws SAXException {
			if (this.getXMLContent()==null) {
				return this;
			}
			// required single
			if(this.slot<1){
				if("MACHINE".equals(localName)){
					this.slot=1;
					XMLHandler.this.stack.push(new MachineHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<2){
				if("USERNAME".equals(localName)){
					this.slot=2;
					XMLHandler.this.stack.push(new UsernameHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<3){
				if("LAST_ACTIVE_TIME".equals(localName)){
					this.slot=3;
					XMLHandler.this.stack.push(new LastActiveTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<4){
				if("ELAPSED_SECONDS".equals(localName)){
					this.slot=4;
					XMLHandler.this.stack.push(new ElapsedSecondsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<5){
				if("DISK_READS".equals(localName)){
					this.slot=5;
					XMLHandler.this.stack.push(new DiskReadsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<6){
				if("BUFFER_GETS".equals(localName)){
					this.slot=6;
					XMLHandler.this.stack.push(new BufferGetsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<7){
				if("CPU_TIME".equals(localName)){
					this.slot=7;
					XMLHandler.this.stack.push(new CpuTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<8){
				if("OPTIMIZER_COST".equals(localName)){
					this.slot=8;
					XMLHandler.this.stack.push(new OptimizerCostHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<9){
				if("SQL_TEXT".equals(localName)){
					this.slot=9;
					XMLHandler.this.stack.push(new SqlTextHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			throw XMLHandler.this.invalidDocument(localName);
		}
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final Row8 element=XMLHandler.this.createRow8();
			this.sequenceElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.sequenceElement;
		}
		public void add(XMLContent content) throws SAXException {
			//Exception e=null;
			try {
				switch (this.slot) {
					case 1:this.sequenceElement.setMachine((Machine) content);
					return;
					case 2:this.sequenceElement.setUsername((Username) content);
					return;
					case 3:this.sequenceElement.setLastActiveTime((LastActiveTime) content);
					return;
					case 4:this.sequenceElement.setElapsedSeconds((ElapsedSeconds) content);
					return;
					case 5:this.sequenceElement.setDiskReads((DiskReads) content);
					return;
					case 6:this.sequenceElement.setBufferGets((BufferGets) content);
					return;
					case 7:this.sequenceElement.setCpuTime((CpuTime) content);
					return;
					case 8:this.sequenceElement.setOptimizerCost((OptimizerCost) content);
					return;
					case 9:this.sequenceElement.setSqlText((SqlText) content);
					return;
					default:throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row8'.",new IllegalStateException("Invalid state in '"+this.getClass().getName()+"'. Slot ("+this.slot+") does not exist."));
				}
			}
			 catch(ClassCastException cce) {
				throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row8'."+
					" XML document is not valid.",cce);
			}
		}
		protected SubHandler getInstance() {
			return new Row8Handler();
		}
	}
	
	class Row9Handler extends ElementSubHandler implements XMLContentAddable{
		private int slot=0;
		private Row9 sequenceElement=null;
		protected SubHandler getSubHandler(String localName) throws SAXException {
			if (this.getXMLContent()==null) {
				return this;
			}
			// required single
			if(this.slot<1){
				if("MACHINE".equals(localName)){
					this.slot=1;
					XMLHandler.this.stack.push(new MachineHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<2){
				if("USERNAME".equals(localName)){
					this.slot=2;
					XMLHandler.this.stack.push(new UsernameHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<3){
				if("LAST_ACTIVE_TIME".equals(localName)){
					this.slot=3;
					XMLHandler.this.stack.push(new LastActiveTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<4){
				if("ELAPSED_SECONDS".equals(localName)){
					this.slot=4;
					XMLHandler.this.stack.push(new ElapsedSecondsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<5){
				if("DISK_READS".equals(localName)){
					this.slot=5;
					XMLHandler.this.stack.push(new DiskReadsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<6){
				if("BUFFER_GETS".equals(localName)){
					this.slot=6;
					XMLHandler.this.stack.push(new BufferGetsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<7){
				if("CPU_TIME".equals(localName)){
					this.slot=7;
					XMLHandler.this.stack.push(new CpuTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<8){
				if("OPTIMIZER_COST".equals(localName)){
					this.slot=8;
					XMLHandler.this.stack.push(new OptimizerCostHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<9){
				if("SQL_TEXT".equals(localName)){
					this.slot=9;
					XMLHandler.this.stack.push(new SqlTextHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			throw XMLHandler.this.invalidDocument(localName);
		}
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final Row9 element=XMLHandler.this.createRow9();
			this.sequenceElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.sequenceElement;
		}
		public void add(XMLContent content) throws SAXException {
			//Exception e=null;
			try {
				switch (this.slot) {
					case 1:this.sequenceElement.setMachine((Machine) content);
					return;
					case 2:this.sequenceElement.setUsername((Username) content);
					return;
					case 3:this.sequenceElement.setLastActiveTime((LastActiveTime) content);
					return;
					case 4:this.sequenceElement.setElapsedSeconds((ElapsedSeconds) content);
					return;
					case 5:this.sequenceElement.setDiskReads((DiskReads) content);
					return;
					case 6:this.sequenceElement.setBufferGets((BufferGets) content);
					return;
					case 7:this.sequenceElement.setCpuTime((CpuTime) content);
					return;
					case 8:this.sequenceElement.setOptimizerCost((OptimizerCost) content);
					return;
					case 9:this.sequenceElement.setSqlText((SqlText) content);
					return;
					default:throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row9'.",new IllegalStateException("Invalid state in '"+this.getClass().getName()+"'. Slot ("+this.slot+") does not exist."));
				}
			}
			 catch(ClassCastException cce) {
				throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row9'."+
					" XML document is not valid.",cce);
			}
		}
		protected SubHandler getInstance() {
			return new Row9Handler();
		}
	}
	
	class BufferGetsHandler extends ElementSubHandler {
		private BufferGets pcdataElement;
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final BufferGets element=XMLHandler.this.createBufferGets();
			this.pcdataElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.pcdataElement;
		}
		protected SubHandler getInstance() {
			return new BufferGetsHandler();
		}
	}
	
	class Row6Handler extends ElementSubHandler implements XMLContentAddable{
		private int slot=0;
		private Row6 sequenceElement=null;
		protected SubHandler getSubHandler(String localName) throws SAXException {
			if (this.getXMLContent()==null) {
				return this;
			}
			// required single
			if(this.slot<1){
				if("MACHINE".equals(localName)){
					this.slot=1;
					XMLHandler.this.stack.push(new MachineHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<2){
				if("USERNAME".equals(localName)){
					this.slot=2;
					XMLHandler.this.stack.push(new UsernameHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<3){
				if("LAST_ACTIVE_TIME".equals(localName)){
					this.slot=3;
					XMLHandler.this.stack.push(new LastActiveTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<4){
				if("ELAPSED_SECONDS".equals(localName)){
					this.slot=4;
					XMLHandler.this.stack.push(new ElapsedSecondsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<5){
				if("DISK_READS".equals(localName)){
					this.slot=5;
					XMLHandler.this.stack.push(new DiskReadsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<6){
				if("BUFFER_GETS".equals(localName)){
					this.slot=6;
					XMLHandler.this.stack.push(new BufferGetsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<7){
				if("CPU_TIME".equals(localName)){
					this.slot=7;
					XMLHandler.this.stack.push(new CpuTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<8){
				if("OPTIMIZER_COST".equals(localName)){
					this.slot=8;
					XMLHandler.this.stack.push(new OptimizerCostHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<9){
				if("SQL_TEXT".equals(localName)){
					this.slot=9;
					XMLHandler.this.stack.push(new SqlTextHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			throw XMLHandler.this.invalidDocument(localName);
		}
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final Row6 element=XMLHandler.this.createRow6();
			this.sequenceElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.sequenceElement;
		}
		public void add(XMLContent content) throws SAXException {
			//Exception e=null;
			try {
				switch (this.slot) {
					case 1:this.sequenceElement.setMachine((Machine) content);
					return;
					case 2:this.sequenceElement.setUsername((Username) content);
					return;
					case 3:this.sequenceElement.setLastActiveTime((LastActiveTime) content);
					return;
					case 4:this.sequenceElement.setElapsedSeconds((ElapsedSeconds) content);
					return;
					case 5:this.sequenceElement.setDiskReads((DiskReads) content);
					return;
					case 6:this.sequenceElement.setBufferGets((BufferGets) content);
					return;
					case 7:this.sequenceElement.setCpuTime((CpuTime) content);
					return;
					case 8:this.sequenceElement.setOptimizerCost((OptimizerCost) content);
					return;
					case 9:this.sequenceElement.setSqlText((SqlText) content);
					return;
					default:throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row6'.",new IllegalStateException("Invalid state in '"+this.getClass().getName()+"'. Slot ("+this.slot+") does not exist."));
				}
			}
			 catch(ClassCastException cce) {
				throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row6'."+
					" XML document is not valid.",cce);
			}
		}
		protected SubHandler getInstance() {
			return new Row6Handler();
		}
	}
	
	class DataSetElementHandler extends ElementSubHandler implements XMLContentAddable{
		private int slot=0;
		private DataSetElement sequenceElement=null;
		protected SubHandler getSubHandler(String localName) throws SAXException {
			if (this.getXMLContent()==null) {
				return this;
			}
			// required single
			if(this.slot<1){
				if("row1".equals(localName)){
					this.slot=1;
					XMLHandler.this.stack.push(new Row1Handler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<2){
				if("row2".equals(localName)){
					this.slot=2;
					XMLHandler.this.stack.push(new Row2Handler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<3){
				if("row3".equals(localName)){
					this.slot=3;
					XMLHandler.this.stack.push(new Row3Handler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<4){
				if("row4".equals(localName)){
					this.slot=4;
					XMLHandler.this.stack.push(new Row4Handler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<5){
				if("row5".equals(localName)){
					this.slot=5;
					XMLHandler.this.stack.push(new Row5Handler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<6){
				if("row6".equals(localName)){
					this.slot=6;
					XMLHandler.this.stack.push(new Row6Handler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<7){
				if("row7".equals(localName)){
					this.slot=7;
					XMLHandler.this.stack.push(new Row7Handler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<8){
				if("row8".equals(localName)){
					this.slot=8;
					XMLHandler.this.stack.push(new Row8Handler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<9){
				if("row9".equals(localName)){
					this.slot=9;
					XMLHandler.this.stack.push(new Row9Handler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<10){
				if("row10".equals(localName)){
					this.slot=10;
					XMLHandler.this.stack.push(new Row10Handler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			throw XMLHandler.this.invalidDocument(localName);
		}
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final DataSetElement element=XMLHandler.this.createDataSetElement();
			element.setCount(attributes.getValue("count"));
			element.setTag(attributes.getValue("tag"));
			this.sequenceElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.sequenceElement;
		}
		public void add(XMLContent content) throws SAXException {
			//Exception e=null;
			try {
				switch (this.slot) {
					case 1:this.sequenceElement.setRow1((Row1) content);
					return;
					case 2:this.sequenceElement.setRow2((Row2) content);
					return;
					case 3:this.sequenceElement.setRow3((Row3) content);
					return;
					case 4:this.sequenceElement.setRow4((Row4) content);
					return;
					case 5:this.sequenceElement.setRow5((Row5) content);
					return;
					case 6:this.sequenceElement.setRow6((Row6) content);
					return;
					case 7:this.sequenceElement.setRow7((Row7) content);
					return;
					case 8:this.sequenceElement.setRow8((Row8) content);
					return;
					case 9:this.sequenceElement.setRow9((Row9) content);
					return;
					case 10:this.sequenceElement.setRow10((Row10) content);
					return;
					default:throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'DataSetElement'.",new IllegalStateException("Invalid state in '"+this.getClass().getName()+"'. Slot ("+this.slot+") does not exist."));
				}
			}
			 catch(ClassCastException cce) {
				throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'DataSetElement'."+
					" XML document is not valid.",cce);
			}
		}
		protected SubHandler getInstance() {
			return new DataSetElementHandler();
		}
	}
	
	class Row7Handler extends ElementSubHandler implements XMLContentAddable{
		private int slot=0;
		private Row7 sequenceElement=null;
		protected SubHandler getSubHandler(String localName) throws SAXException {
			if (this.getXMLContent()==null) {
				return this;
			}
			// required single
			if(this.slot<1){
				if("MACHINE".equals(localName)){
					this.slot=1;
					XMLHandler.this.stack.push(new MachineHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<2){
				if("USERNAME".equals(localName)){
					this.slot=2;
					XMLHandler.this.stack.push(new UsernameHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<3){
				if("LAST_ACTIVE_TIME".equals(localName)){
					this.slot=3;
					XMLHandler.this.stack.push(new LastActiveTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<4){
				if("ELAPSED_SECONDS".equals(localName)){
					this.slot=4;
					XMLHandler.this.stack.push(new ElapsedSecondsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<5){
				if("DISK_READS".equals(localName)){
					this.slot=5;
					XMLHandler.this.stack.push(new DiskReadsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<6){
				if("BUFFER_GETS".equals(localName)){
					this.slot=6;
					XMLHandler.this.stack.push(new BufferGetsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<7){
				if("CPU_TIME".equals(localName)){
					this.slot=7;
					XMLHandler.this.stack.push(new CpuTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<8){
				if("OPTIMIZER_COST".equals(localName)){
					this.slot=8;
					XMLHandler.this.stack.push(new OptimizerCostHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<9){
				if("SQL_TEXT".equals(localName)){
					this.slot=9;
					XMLHandler.this.stack.push(new SqlTextHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			throw XMLHandler.this.invalidDocument(localName);
		}
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final Row7 element=XMLHandler.this.createRow7();
			this.sequenceElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.sequenceElement;
		}
		public void add(XMLContent content) throws SAXException {
			//Exception e=null;
			try {
				switch (this.slot) {
					case 1:this.sequenceElement.setMachine((Machine) content);
					return;
					case 2:this.sequenceElement.setUsername((Username) content);
					return;
					case 3:this.sequenceElement.setLastActiveTime((LastActiveTime) content);
					return;
					case 4:this.sequenceElement.setElapsedSeconds((ElapsedSeconds) content);
					return;
					case 5:this.sequenceElement.setDiskReads((DiskReads) content);
					return;
					case 6:this.sequenceElement.setBufferGets((BufferGets) content);
					return;
					case 7:this.sequenceElement.setCpuTime((CpuTime) content);
					return;
					case 8:this.sequenceElement.setOptimizerCost((OptimizerCost) content);
					return;
					case 9:this.sequenceElement.setSqlText((SqlText) content);
					return;
					default:throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row7'.",new IllegalStateException("Invalid state in '"+this.getClass().getName()+"'. Slot ("+this.slot+") does not exist."));
				}
			}
			 catch(ClassCastException cce) {
				throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row7'."+
					" XML document is not valid.",cce);
			}
		}
		protected SubHandler getInstance() {
			return new Row7Handler();
		}
	}
	
	class DataEnvelopeHandler extends ElementSubHandler implements XMLContentAddable{
		private DataEnvelope myElement=null;
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final DataEnvelope element=XMLHandler.this.createDataEnvelope();
			this.myElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.myElement;
		}
		public void add(XMLContent content) throws SAXException {
			try{
				if(content instanceof DataSetElement){
					this.myElement.setDataSetElement((DataSetElement) content);
					return;
				}
			}
			catch(ClassCastException cce){
				throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'DataEnvelope'."+
					" XML document is not valid.",cce);
			}
		}
		protected SubHandler getInstance() {
			return new DataEnvelopeHandler();
		}
	}
	
	class LastActiveTimeHandler extends ElementSubHandler {
		private LastActiveTime pcdataElement;
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final LastActiveTime element=XMLHandler.this.createLastActiveTime();
			this.pcdataElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.pcdataElement;
		}
		protected SubHandler getInstance() {
			return new LastActiveTimeHandler();
		}
	}
	
	class Row10Handler extends ElementSubHandler implements XMLContentAddable{
		private int slot=0;
		private Row10 sequenceElement=null;
		protected SubHandler getSubHandler(String localName) throws SAXException {
			if (this.getXMLContent()==null) {
				return this;
			}
			// required single
			if(this.slot<1){
				if("MACHINE".equals(localName)){
					this.slot=1;
					XMLHandler.this.stack.push(new MachineHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<2){
				if("USERNAME".equals(localName)){
					this.slot=2;
					XMLHandler.this.stack.push(new UsernameHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<3){
				if("LAST_ACTIVE_TIME".equals(localName)){
					this.slot=3;
					XMLHandler.this.stack.push(new LastActiveTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<4){
				if("ELAPSED_SECONDS".equals(localName)){
					this.slot=4;
					XMLHandler.this.stack.push(new ElapsedSecondsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<5){
				if("DISK_READS".equals(localName)){
					this.slot=5;
					XMLHandler.this.stack.push(new DiskReadsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<6){
				if("BUFFER_GETS".equals(localName)){
					this.slot=6;
					XMLHandler.this.stack.push(new BufferGetsHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<7){
				if("CPU_TIME".equals(localName)){
					this.slot=7;
					XMLHandler.this.stack.push(new CpuTimeHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<8){
				if("OPTIMIZER_COST".equals(localName)){
					this.slot=8;
					XMLHandler.this.stack.push(new OptimizerCostHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			// required single
			if(this.slot<9){
				if("SQL_TEXT".equals(localName)){
					this.slot=9;
					XMLHandler.this.stack.push(new SqlTextHandler());
					return XMLHandler.this.getSubHandler(localName);
				}
				 else {
					throw XMLHandler.this.invalidDocument(localName);
				}
			}
			throw XMLHandler.this.invalidDocument(localName);
		}
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final Row10 element=XMLHandler.this.createRow10();
			this.sequenceElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.sequenceElement;
		}
		public void add(XMLContent content) throws SAXException {
			//Exception e=null;
			try {
				switch (this.slot) {
					case 1:this.sequenceElement.setMachine((Machine) content);
					return;
					case 2:this.sequenceElement.setUsername((Username) content);
					return;
					case 3:this.sequenceElement.setLastActiveTime((LastActiveTime) content);
					return;
					case 4:this.sequenceElement.setElapsedSeconds((ElapsedSeconds) content);
					return;
					case 5:this.sequenceElement.setDiskReads((DiskReads) content);
					return;
					case 6:this.sequenceElement.setBufferGets((BufferGets) content);
					return;
					case 7:this.sequenceElement.setCpuTime((CpuTime) content);
					return;
					case 8:this.sequenceElement.setOptimizerCost((OptimizerCost) content);
					return;
					case 9:this.sequenceElement.setSqlText((SqlText) content);
					return;
					default:throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row10'.",new IllegalStateException("Invalid state in '"+this.getClass().getName()+"'. Slot ("+this.slot+") does not exist."));
				}
			}
			 catch(ClassCastException cce) {
				throw new SAXException("Failed to add '"+content.getClass().getName()+"' to 'Row10'."+
					" XML document is not valid.",cce);
			}
		}
		protected SubHandler getInstance() {
			return new Row10Handler();
		}
	}
	
	class UsernameHandler extends ElementSubHandler {
		private Username pcdataElement;
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final Username element=XMLHandler.this.createUsername();
			this.pcdataElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.pcdataElement;
		}
		protected SubHandler getInstance() {
			return new UsernameHandler();
		}
	}
	
	class DiskReadsHandler extends ElementSubHandler {
		private DiskReads pcdataElement;
		public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException {
			final DiskReads element=XMLHandler.this.createDiskReads();
			this.pcdataElement=element;
		}
		protected XMLContent getXMLContent() {
			return this.pcdataElement;
		}
		protected SubHandler getInstance() {
			return new DiskReadsHandler();
		}
	}
	
	/** Number of indexed <code>SubHandler</code>s.
	 *  <p>
	 *  This valiable is the array size needed for the sub handler
	 *  array. For technical reasons at generation time 
	 *  it has to be at the end of the file.
	 *  <br> Just ignore this thing!
	 */
	private static final int ARRAY_SIZE=1;
}