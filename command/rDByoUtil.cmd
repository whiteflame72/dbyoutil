cls

:@echo off
REM FOR ORACLE LITE 10g - must find oljdbc40.dll and etc
REM FOR EXCEL, - must have xlSQL_Y7.jar, jconfig.jar.

REM For www.infozoom.de MS Access jdbc driver
set PATH=%PATH%;%APP_HOME%\lib\jdbc

IF "%6"=="11" (del /Q %COMMAND_DIR%\getSQLData.xml)
IF "%6"=="20" (del /Q %COMMAND_DIR%\getSQLData.xls)

%JAVA_HOME%\bin\java -XX:MaxPermSize=128M -Xms250M -Xmx400M -classpath .;%APP_HOME%\lib\dbyoutil.jar;%APP_HOME%\lib\parser\xercesImpl.jar;%APP_HOME%\lib\jdbc\OLITE40.JAR;%APP_HOME%\lib\jdbc\classes12.jar;%APP_HOME%\lib\jdbc\jdbc-ldap.jar;%APP_HOME%\lib\jdbc\ldap.jar;%APP_HOME%\lib\xlSQL_Y7.jar;%APP_HOME%\lib\jconfig.jar;%APP_HOME%\lib\jxl.jar;%APP_HOME%\lib\jdbc\hsqldb.jar;%APP_HOME%\lib\jdbc\mysql-connector-java-3.1.14-bin.jar;%APP_HOME%\lib\jdbc\jtds-1.2.5.jar;%APP_HOME%\lib\excel\poi-3.0-rc4-20070503.jar;%APP_HOME%\lib\spring\spring.jar;%APP_HOME%\lib\fileutil.jar;%APP_HOME%\lib\aspectjrt.jar;%APP_HOME%\lib\wt_fw.jar;%APP_HOME%\lib\jdbc\izmado.jar com.googlecode.dbyoutil.transformer.SQLClient %1 %2 %3 %4 %5 %6

IF "%6"=="11" (start %COMMAND_DIR%\getSQLData.xml)
IF "%6"=="20" (start %COMMAND_DIR%\getSQLData.xls)
IF "%6"=="22" (start %COMMAND_DIR%\rawXML.xml)

:pause