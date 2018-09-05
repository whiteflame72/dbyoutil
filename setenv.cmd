REM ========================= USER MAY HAVE TO CHANGE THE FOLLOWING LINE ===========================

:call \digital\case\base\common\setPortableDrive.cmd
set PDRIVE=c:\Users\User-05

:call %PDRIVE%\digital\case\base\common\setJAVAHome.cmd
set JAVA_HOME="C:\Program Files\Java\jdk1.8.0_152"

:SET APP_HOME=%PDRIVE%\digital\case\base\dbutil
SET APP_HOME=%PDRIVE%\dbutil

REM ============== USER SHOULD NOT HAVE TO CHANGE ANYTHING AFTER THE FOLLOWING LINE ================
set JDBC_DIR=%APP_HOME%\lib
set COMMAND_DIR=%APP_HOME%\command
set SQL_DIR=%APP_HOME%\sql