/*
 *  DTD.java
 *  
 *  Generated with jNerd's XML2Java Version 1.2 Preview 2
 *  
 *  Date: Fri Jul 04 19:50:19 EDT 2008
 *  
 *  No warranty; no copyright -- use this as you will.
 */
package com.googlecode.dbyoutil.db.xml2java;
/**
 *  This interface contains the DTD, 
 *  which was the source for creating all other classes.
 *  <br>
 *  It is used by the <code>XMLWriter</code> to output 
 *  the appropriate DocumentTypeDeclaration and/or 
 *  DocumentTypeDefinition
 *  @see XMLWriter
 *  <p>
 *  @author Generated with jNerd's XML2Java tool Version 1.2 Preview 2 on Fri Jul 04 19:50:19 EDT 2008
 *  @author <br>Please send BugReports to <a href="mailto:xml2java@jNerd.de">xml2java@jNerd.de</a>
 */
public interface DTD {
	/** Specifies that no document type declaration 
	 *  shall be written.
	 *
	 *  @see XMLWriter#setDocumentTypeDeclaration
	 */
	int NO_DOCUMENT_TYPE_DECLARATION   = -2;
	/** Specifies that the document type declaration 
	 *  shall include the whole DTD internally.
	 *  <p>
	 *  Note that all parameter entities will be 
	 *  resolved, so the written DTD might not match
	 *  the original DTD exactly.
	 *
	 *  @see XMLWriter#setDocumentTypeDeclaration
	 */
	int INTERNAL_DOCUMENT_TYPE_DECLARATION = -1;
	/** Specifies that the document type declaration 
	 *  written, shall contain a relative path to the DTD.
	 *  <p>
	 *  If the DTD does not exists in this location,
	 *  you have the possibility to create it with 
	 *  <code>XMLWriter.createDTD(File)</code>
	 *  or <code>XMLWriter.createDTDRelativeTo(File)</code>.
	 *
	 *  @see XMLWriter#setDocumentTypeDeclaration
	 */
	int SYSTEM_DOCUMENT_TYPE_DECLARATION   = 0;
	/** Specifies that the document type declaration 
	 *  written, shall contain a absolute uri to 
	 *  the public DTD.
	 *
	 *  @see XMLWriter#setDocumentTypeDeclaration
	 */
	int PUBLIC_DOCUMENT_TYPE_DECLARATION   = 1;
	/** The default encoding to be used (=<code>null</code>).
	 *  <p>
	 *  If the default encoding is <code>null</code>, Javas default encoding will be used.
	 */
	String DEFAULT_ENCODING=null;
	/** The default document type declaration style to be used (SYSTEM_DOCUMENT_TYPE_DECLARATION). 
	 * 
	 *  @see #SYSTEM_DOCUMENT_TYPE_DECLARATION	 	 
	 */
	int DEFAULT_DOCUMENT_TYPE_DECLARATION_STYLE=SYSTEM_DOCUMENT_TYPE_DECLARATION;
	/** String representation of the original DTD. */
	String DTD_ORIGINAL=
		"<!ELEMENT BUFFER_GETS ( #PCDATA ) >\n"+
		"\n"+
		"<!ELEMENT CPU_TIME ( #PCDATA ) >\n"+
		"\n"+
		"<!ELEMENT DISK_READS ( #PCDATA ) >\n"+
		"\n"+
		"<!ELEMENT ELAPSED_SECONDS ( #PCDATA ) >\n"+
		"\n"+
		"<!ELEMENT LAST_ACTIVE_TIME ( #PCDATA ) >\n"+
		"\n"+
		"<!ELEMENT MACHINE ( #PCDATA ) >\n"+
		"\n"+
		"<!ELEMENT OPTIMIZER_COST ( #PCDATA ) >\n"+
		"\n"+
		"<!ELEMENT SQL_TEXT ( #PCDATA ) >\n"+
		"\n"+
		"<!ELEMENT USERNAME ( #PCDATA ) >\n"+
		"\n"+
		"<!ELEMENT dataEnvelope ( dataSet ) >\n"+
		"\n"+
		"<!ELEMENT dataSet ( row1, row2, row3, row4, row5, row6, row7, row8, row9, row10 ) >\n"+
		"<!ATTLIST dataSet count NMTOKEN #REQUIRED >\n"+
		"<!ATTLIST dataSet tag CDATA #REQUIRED >\n"+
		"\n"+
		"<!ELEMENT row1 ( MACHINE, USERNAME, LAST_ACTIVE_TIME, ELAPSED_SECONDS, DISK_READS, BUFFER_GETS, CPU_TIME, OPTIMIZER_COST, SQL_TEXT ) >\n"+
		"\n"+
		"<!ELEMENT row2 ( MACHINE, USERNAME, LAST_ACTIVE_TIME, ELAPSED_SECONDS, DISK_READS, BUFFER_GETS, CPU_TIME, OPTIMIZER_COST, SQL_TEXT ) >\n"+
		"\n"+
		"<!ELEMENT row3 ( MACHINE, USERNAME, LAST_ACTIVE_TIME, ELAPSED_SECONDS, DISK_READS, BUFFER_GETS, CPU_TIME, OPTIMIZER_COST, SQL_TEXT ) >\n"+
		"\n"+
		"<!ELEMENT row4 ( MACHINE, USERNAME, LAST_ACTIVE_TIME, ELAPSED_SECONDS, DISK_READS, BUFFER_GETS, CPU_TIME, OPTIMIZER_COST, SQL_TEXT ) >\n"+
		"\n"+
		"<!ELEMENT row5 ( MACHINE, USERNAME, LAST_ACTIVE_TIME, ELAPSED_SECONDS, DISK_READS, BUFFER_GETS, CPU_TIME, OPTIMIZER_COST, SQL_TEXT ) >\n"+
		"\n"+
		"<!ELEMENT row6 ( MACHINE, USERNAME, LAST_ACTIVE_TIME, ELAPSED_SECONDS, DISK_READS, BUFFER_GETS, CPU_TIME, OPTIMIZER_COST, SQL_TEXT ) >\n"+
		"\n"+
		"<!ELEMENT row7 ( MACHINE, USERNAME, LAST_ACTIVE_TIME, ELAPSED_SECONDS, DISK_READS, BUFFER_GETS, CPU_TIME, OPTIMIZER_COST, SQL_TEXT ) >\n"+
		"\n"+
		"<!ELEMENT row8 ( MACHINE, USERNAME, LAST_ACTIVE_TIME, ELAPSED_SECONDS, DISK_READS, BUFFER_GETS, CPU_TIME, OPTIMIZER_COST, SQL_TEXT ) >\n"+
		"\n"+
		"<!ELEMENT row9 ( MACHINE, USERNAME, LAST_ACTIVE_TIME, ELAPSED_SECONDS, DISK_READS, BUFFER_GETS, CPU_TIME, OPTIMIZER_COST, SQL_TEXT ) >\n"+
		"\n"+
		"<!ELEMENT row10 ( MACHINE, USERNAME, LAST_ACTIVE_TIME, ELAPSED_SECONDS, DISK_READS, BUFFER_GETS, CPU_TIME, OPTIMIZER_COST, SQL_TEXT ) >";
	/** String representation of the original internal DTD subset. */
	String DTD_INTERNAL_SUBSET=null;
	/** Templates for the Document Type Declaration */
	String[] DOCUMENT_TYPE_DECLARATION= {
		"db.dtd",null
			
	}
	;
}