Call ../setEnv.cmd

set PATH=%JAVA_HOME%\bin

:start javaw -XX:MaxPermSize=128M -Xms250M -Xmx400M -classpath .;%APP_HOME%\src;%APP_HOME%\lib\db2xml.jar;%APP_HOME%\lib\xercesImpl.jar;%APP_HOME%\lib\OLITE40.JAR;%APP_HOME%\lib\classes12.jar;%APP_HOME%\lib\jdbc-ldap.jar;%APP_HOME%\lib\ldap.jar;%APP_HOME%\lib\xlSQL_Y7.jar;%APP_HOME%\lib\jconfig.jar;%APP_HOME%\lib\jxl.jar;%APP_HOME%\lib\hsqldb.jar;%APP_HOME%\lib\mysql-connector-java-3.0.10-stable-bin.jar;%APP_HOME%\lib\poi-3.0-rc4-20070503.jar;%APP_HOME%\lib\spring.jar;%APP_HOME%\lib\fileutil.jar;%APP_HOME%\lib\commons-collections-3.2.1.jar org.apache.tool.db.DBAlert
java -XX:MaxPermSize=128M -Xms250M -Xmx400M -classpath .;%APP_HOME%\src;%APP_HOME%\lib\db2xml.jar;%APP_HOME%\lib\xercesImpl.jar;%APP_HOME%\lib\OLITE40.JAR;%APP_HOME%\lib\classes12.jar;%APP_HOME%\lib\jdbc-ldap.jar;%APP_HOME%\lib\ldap.jar;%APP_HOME%\lib\xlSQL_Y7.jar;%APP_HOME%\lib\jconfig.jar;%APP_HOME%\lib\jxl.jar;%APP_HOME%\lib\hsqldb.jar;%APP_HOME%\lib\mysql-connector-java-3.0.10-stable-bin.jar;%APP_HOME%\lib\poi-3.0-rc4-20070503.jar;%APP_HOME%\lib\spring.jar;%APP_HOME%\lib\fileutil.jar;%APP_HOME%\lib\commons-collections-3.2.1.jar org.apache.tool.db.DBAlert

exit