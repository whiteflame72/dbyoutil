Call ../setEnv.cmd

%COMMAND_DIR%/rDB2XML com.octetstring.jdbcLdap.sql.JdbcLdapDriver "jdbc:ldap://%1:%2/o=dhs,c=us?SEARCH_SCOPE:=subTreeScope" %3 %4 %5 %6