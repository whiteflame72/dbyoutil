/*
 *  Row9.java
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
 *  Class corresponding to a <i>row9</i> element in XML.
 *  <p>
 *  @author Generated with jNerd's XML2Java tool Version 1.2 Preview 2 on Fri Jul 04 19:50:19 EDT 2008
 *  @author <br>Please send BugReports to <a href="mailto:xml2java@jNerd.de">xml2java@jNerd.de</a>
 */
public class Row9  implements XMLElement {
	/** The name of this class' corresponding XML element. (<i>"row9"</i>) */
	public static final String ELEMENT_NAME="row9";
	//----------------------------------------------------------------------------- attribute member
	/* XML element row9 has no attributes */
	//----------------------------------------------------------------------------- content member
	/** The nested <i>MACHINE</i> element
	 */
	private Machine machine;
	/** The nested <i>USERNAME</i> element
	 */
	private Username username;
	/** The nested <i>LAST_ACTIVE_TIME</i> element
	 */
	private LastActiveTime lastActiveTime;
	/** The nested <i>ELAPSED_SECONDS</i> element
	 */
	private ElapsedSeconds elapsedSeconds;
	/** The nested <i>DISK_READS</i> element
	 */
	private DiskReads diskReads;
	/** The nested <i>BUFFER_GETS</i> element
	 */
	private BufferGets bufferGets;
	/** The nested <i>CPU_TIME</i> element
	 */
	private CpuTime cpuTime;
	/** The nested <i>OPTIMIZER_COST</i> element
	 */
	private OptimizerCost optimizerCost;
	/** The nested <i>SQL_TEXT</i> element
	 */
	private SqlText sqlText;
	//----------------------------------------------------------------------------- element name access
	/** Accesses the XML element name.
	 *  @return <i>"row9"</i> which is this class' corresponding XML Element.
	 */
	public String getElementName(){
		return ELEMENT_NAME;
	}
	
	//----------------------------------------------------------------------------- attribute access
	/* XML element row9 has no attributes */
	//----------------------------------------------------------------------------- content access
	/** Access to property <code>machine</code> 
	 *  corresponding to the nested <i>MACHINE</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>MACHINE</i> element is represented by 
	 *  property <code>machine</code>, an instance of
	 *  class <code>Machine</code>.
	 *
	 *  @return Property <code>machine</code>, which is the 
	 *          current nested <i>MACHINE</i> node.
	 *
	 *  @see Machine
	 */
	public Machine getMachine() {
		return this.machine;
	}
	
	/** Sets this class' nested <i>MACHINE</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>MACHINE</i> element is represented by 
	 *  property <code>machine</code>, an instance of
	 *  class <code>Machine</code>.
	 *
	 *  @param pmachine The <code>Machine</code> to be
	 *         set as the property <code>machine</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see Machine
	 */
	public void setMachine(Machine pmachine) {
		this.machine=pmachine;
	}
	
	/** Access to property <code>username</code> 
	 *  corresponding to the nested <i>USERNAME</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>USERNAME</i> element is represented by 
	 *  property <code>username</code>, an instance of
	 *  class <code>Username</code>.
	 *
	 *  @return Property <code>username</code>, which is the 
	 *          current nested <i>USERNAME</i> node.
	 *
	 *  @see Username
	 */
	public Username getUsername() {
		return this.username;
	}
	
	/** Sets this class' nested <i>USERNAME</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>USERNAME</i> element is represented by 
	 *  property <code>username</code>, an instance of
	 *  class <code>Username</code>.
	 *
	 *  @param pusername The <code>Username</code> to be
	 *         set as the property <code>username</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see Username
	 */
	public void setUsername(Username pusername) {
		this.username=pusername;
	}
	
	/** Access to property <code>lastActiveTime</code> 
	 *  corresponding to the nested <i>LAST_ACTIVE_TIME</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>LAST_ACTIVE_TIME</i> element is represented by 
	 *  property <code>lastActiveTime</code>, an instance of
	 *  class <code>LastActiveTime</code>.
	 *
	 *  @return Property <code>lastActiveTime</code>, which is the 
	 *          current nested <i>LAST_ACTIVE_TIME</i> node.
	 *
	 *  @see LastActiveTime
	 */
	public LastActiveTime getLastActiveTime() {
		return this.lastActiveTime;
	}
	
	/** Sets this class' nested <i>LAST_ACTIVE_TIME</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>LAST_ACTIVE_TIME</i> element is represented by 
	 *  property <code>lastActiveTime</code>, an instance of
	 *  class <code>LastActiveTime</code>.
	 *
	 *  @param plastActiveTime The <code>LastActiveTime</code> to be
	 *         set as the property <code>lastActiveTime</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see LastActiveTime
	 */
	public void setLastActiveTime(LastActiveTime plastActiveTime) {
		this.lastActiveTime=plastActiveTime;
	}
	
	/** Access to property <code>elapsedSeconds</code> 
	 *  corresponding to the nested <i>ELAPSED_SECONDS</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>ELAPSED_SECONDS</i> element is represented by 
	 *  property <code>elapsedSeconds</code>, an instance of
	 *  class <code>ElapsedSeconds</code>.
	 *
	 *  @return Property <code>elapsedSeconds</code>, which is the 
	 *          current nested <i>ELAPSED_SECONDS</i> node.
	 *
	 *  @see ElapsedSeconds
	 */
	public ElapsedSeconds getElapsedSeconds() {
		return this.elapsedSeconds;
	}
	
	/** Sets this class' nested <i>ELAPSED_SECONDS</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>ELAPSED_SECONDS</i> element is represented by 
	 *  property <code>elapsedSeconds</code>, an instance of
	 *  class <code>ElapsedSeconds</code>.
	 *
	 *  @param pelapsedSeconds The <code>ElapsedSeconds</code> to be
	 *         set as the property <code>elapsedSeconds</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see ElapsedSeconds
	 */
	public void setElapsedSeconds(ElapsedSeconds pelapsedSeconds) {
		this.elapsedSeconds=pelapsedSeconds;
	}
	
	/** Access to property <code>diskReads</code> 
	 *  corresponding to the nested <i>DISK_READS</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>DISK_READS</i> element is represented by 
	 *  property <code>diskReads</code>, an instance of
	 *  class <code>DiskReads</code>.
	 *
	 *  @return Property <code>diskReads</code>, which is the 
	 *          current nested <i>DISK_READS</i> node.
	 *
	 *  @see DiskReads
	 */
	public DiskReads getDiskReads() {
		return this.diskReads;
	}
	
	/** Sets this class' nested <i>DISK_READS</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>DISK_READS</i> element is represented by 
	 *  property <code>diskReads</code>, an instance of
	 *  class <code>DiskReads</code>.
	 *
	 *  @param pdiskReads The <code>DiskReads</code> to be
	 *         set as the property <code>diskReads</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see DiskReads
	 */
	public void setDiskReads(DiskReads pdiskReads) {
		this.diskReads=pdiskReads;
	}
	
	/** Access to property <code>bufferGets</code> 
	 *  corresponding to the nested <i>BUFFER_GETS</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>BUFFER_GETS</i> element is represented by 
	 *  property <code>bufferGets</code>, an instance of
	 *  class <code>BufferGets</code>.
	 *
	 *  @return Property <code>bufferGets</code>, which is the 
	 *          current nested <i>BUFFER_GETS</i> node.
	 *
	 *  @see BufferGets
	 */
	public BufferGets getBufferGets() {
		return this.bufferGets;
	}
	
	/** Sets this class' nested <i>BUFFER_GETS</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>BUFFER_GETS</i> element is represented by 
	 *  property <code>bufferGets</code>, an instance of
	 *  class <code>BufferGets</code>.
	 *
	 *  @param pbufferGets The <code>BufferGets</code> to be
	 *         set as the property <code>bufferGets</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see BufferGets
	 */
	public void setBufferGets(BufferGets pbufferGets) {
		this.bufferGets=pbufferGets;
	}
	
	/** Access to property <code>cpuTime</code> 
	 *  corresponding to the nested <i>CPU_TIME</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>CPU_TIME</i> element is represented by 
	 *  property <code>cpuTime</code>, an instance of
	 *  class <code>CpuTime</code>.
	 *
	 *  @return Property <code>cpuTime</code>, which is the 
	 *          current nested <i>CPU_TIME</i> node.
	 *
	 *  @see CpuTime
	 */
	public CpuTime getCpuTime() {
		return this.cpuTime;
	}
	
	/** Sets this class' nested <i>CPU_TIME</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>CPU_TIME</i> element is represented by 
	 *  property <code>cpuTime</code>, an instance of
	 *  class <code>CpuTime</code>.
	 *
	 *  @param pcpuTime The <code>CpuTime</code> to be
	 *         set as the property <code>cpuTime</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see CpuTime
	 */
	public void setCpuTime(CpuTime pcpuTime) {
		this.cpuTime=pcpuTime;
	}
	
	/** Access to property <code>optimizerCost</code> 
	 *  corresponding to the nested <i>OPTIMIZER_COST</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>OPTIMIZER_COST</i> element is represented by 
	 *  property <code>optimizerCost</code>, an instance of
	 *  class <code>OptimizerCost</code>.
	 *
	 *  @return Property <code>optimizerCost</code>, which is the 
	 *          current nested <i>OPTIMIZER_COST</i> node.
	 *
	 *  @see OptimizerCost
	 */
	public OptimizerCost getOptimizerCost() {
		return this.optimizerCost;
	}
	
	/** Sets this class' nested <i>OPTIMIZER_COST</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>OPTIMIZER_COST</i> element is represented by 
	 *  property <code>optimizerCost</code>, an instance of
	 *  class <code>OptimizerCost</code>.
	 *
	 *  @param poptimizerCost The <code>OptimizerCost</code> to be
	 *         set as the property <code>optimizerCost</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see OptimizerCost
	 */
	public void setOptimizerCost(OptimizerCost poptimizerCost) {
		this.optimizerCost=poptimizerCost;
	}
	
	/** Access to property <code>sqlText</code> 
	 *  corresponding to the nested <i>SQL_TEXT</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>SQL_TEXT</i> element is represented by 
	 *  property <code>sqlText</code>, an instance of
	 *  class <code>SqlText</code>.
	 *
	 *  @return Property <code>sqlText</code>, which is the 
	 *          current nested <i>SQL_TEXT</i> node.
	 *
	 *  @see SqlText
	 */
	public SqlText getSqlText() {
		return this.sqlText;
	}
	
	/** Sets this class' nested <i>SQL_TEXT</i>
	 *  node within this class.
	 *  <p>
	 *  The <i>SQL_TEXT</i> element is represented by 
	 *  property <code>sqlText</code>, an instance of
	 *  class <code>SqlText</code>.
	 *
	 *  @param psqlText The <code>SqlText</code> to be
	 *         set as the property <code>sqlText</code> or
	 *         <code>null</code> if the property is not set.
	 *
	 *  @see SqlText
	 */
	public void setSqlText(SqlText psqlText) {
		this.sqlText=psqlText;
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
		if(this.getMachine()!=null)
			this.getMachine().write(out);
		if(this.getUsername()!=null)
			this.getUsername().write(out);
		if(this.getLastActiveTime()!=null)
			this.getLastActiveTime().write(out);
		if(this.getElapsedSeconds()!=null)
			this.getElapsedSeconds().write(out);
		if(this.getDiskReads()!=null)
			this.getDiskReads().write(out);
		if(this.getBufferGets()!=null)
			this.getBufferGets().write(out);
		if(this.getCpuTime()!=null)
			this.getCpuTime().write(out);
		if(this.getOptimizerCost()!=null)
			this.getOptimizerCost().write(out);
		if(this.getSqlText()!=null)
			this.getSqlText().write(out);
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