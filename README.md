Features
--------

. Supports any database with JDBC driver.
. Supports Oracle-style "desc" command with all the databases.
. Supports "desc" command with sample value(s) via "descv".
. Supports file based SQL input or directly via command line.
. Supports multiple SQL statements in an input file. Results will appear in a single XML file or MS Excel spreadsheet (as tabs).
. Supports the database level (all tables) "desc" and "descv" via "desc *" and "descv *" (experimental).

Operation Guide
---------------

. Use action code 100 for update/delete, 11 for the rest.
. For MS Access based data source, remember to set "ImplicitCommitSync" option to "yes" in the ODBC DSN setup.
. If some other external launcher/helper application is needed, it could be specified inside command/launch_external.bat (please rename _launch_external.bat accordingly to launch_external.bat).

Installation Guide
------------------
Make sure <IASUtil_HOME>\command, <JBOSS_HOME> and <Oc4J_HOME> are in the classpath
and PowerAnalyzerSDK is installed in <JBOSS_HOME> dir.

Original directories of the libraries are as the following :

set JAVA_HOME=/IASUtil/jdk141_02
set IAS_HOME=/jboss_ipa/server/informatica/deploy/ias.ear/iasWebApp.war/api4
set PA_HOME=/jboss_ipa/server/informatica/pa_lib
set EJB_LIB_HOME=/oc4j/j2ee/home
set JBOSS_LIB_HOME=/jboss_ipa/client
set JBOSS_INFORMATICA_LIB_HOME=/jboss_ipa/server/informatica/lib
set GCE_HOME=.;/pc/personal/ias
set EXTRA=%IAS_HOME%/axis.jar;%IAS_HOME%/commons-discovery.jar;%IAS_HOME%/commons-logging.jar;%IAS_HOME%/jaxrpc.jar;%IAS_HOME%/;%IAS_HOME%/saaj.jar;%IAS_HOME%/wsdl4j.jar
set PI_HOME=/jboss_ipa/PowerAnalyzerSDK/PortalKit
set SERVLET_HOME=/oc4j1012/j2ee/home/lib

To Test in debugger
-------------------
sun.jdbc.odbc.JdbcOdbcDriver "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=/digital/life/database/allmovies5.mdb" user pass /digital/case/base/DBUtil/sql/pending5.txt 11

sun.jdbc.odbc.JdbcOdbcDriver "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=/digital/life/database/allmovies5.mdb" user pass "select top 1 * from movies" 11

/digital/case/base/DBUtil/sql/media.txt

Limitation
----------
. Currently do not support "desc" syntax in multi-SQL mode. Example like the following won't work -

desc movies
/
select title from movies
/

. Currently "select 1 from dual" would not work, since 1 in this case is directly translated into XML element <1>. 
Workaround is like to replace it with '*' e.g. select * from dual.


Source
------
https://github.com/whiteflame72/dbyoutil
