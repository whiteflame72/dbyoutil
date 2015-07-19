Call ../setEnv.cmd

%COMMAND_DIR%/rDB2XML sun.jdbc.odbc.JdbcOdbcDriver "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=C:\Documents and Settings\jt\My Documents\My Dropbox\Database\allmovies5.mdb;DriverId=25;FIL=MS Access;ImplicitCommitSync=Yes;MaxBufferSize=2048;MaxScanRows=8;PageTimeout=15;SafeTransactions=0;Threads=3;UserCommitSync=Yes" user pass %1 %2

:pause

