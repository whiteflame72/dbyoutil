Call ../setEnv.cmd

%COMMAND_DIR%/rDB2XML com.octetstring.jdbcLdap.sql.JdbcLdapDriver "jdbc:ldap://%1:%2/OU=Users,OU=Administrators,DC=gce2000,DC=com?SEARCH_SCOPE:=subTreeScope" %3 %4 %5 %6