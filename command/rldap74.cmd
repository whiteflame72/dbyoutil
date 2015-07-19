Call ../setEnv.cmd

%COMMAND_DIR%/rDB2XML com.octetstring.jdbcLdap.sql.JdbcLdapDriver "jdbc:ldap://38.118.41.74:3060/dc=gce2000,dc=com?SEARCH_SCOPE:=subTreeScope" "cn=orcladmin" oracl3 %1 %2