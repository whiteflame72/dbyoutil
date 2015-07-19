Call ../setEnv.cmd

%COMMAND_DIR%/rDB2XML com.octetstring.jdbcLdap.sql.JdbcLdapDriver "jdbc:ldap://nolldapndt.nextel.com:5389/ou=SalesSupportUsers,ou=People,o=nextel.com?SEARCH_SCOPE:=subTreeScope" "cn=ka146475" ldappwndt1 %1 %2