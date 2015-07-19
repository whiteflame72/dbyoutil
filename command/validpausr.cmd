:rldap 38.118.41.74 3060 "cn=orcladmin" oracl3 "select * from ou=Users,ou=Administrators,dc=gce2000,dc=com where cn=%1" 11
:rldap 38.118.10.23 389 "cn=orcladmin" oracl3 "select * from ou=Users,ou=Administrators,dc=gce2000,dc=com where cn=%1" 11
:rldap 38.118.10.23 389 "cn=admin,ou=Users,ou=Administrators,dc=gce2000,dc=com" adminpass1 "select * from ou=Users,ou=Administrators,dc=gce2000,dc=com where cn=%1" 11
:rmsldap 38.118.10.6 389 "CN=orcladmin,OU=Users,OU=Administrators,DC=gce2000,DC=com" oracl3 "select * from OU=Users,OU=Administrators,DC=gce2000,DC=com where cn=%1" 11
:rmsldap 38.118.10.6 389 "CN=admin,OU=Users,OU=Administrators,DC=gce2000,DC=com" 1g3t2g0 "select * from OU=Users,OU=Administrators,DC=gce2000,DC=com where cn=%1" 11

:rldap 38.118.10.23 389 "cn=admin,cn=users,o=dhs,c=us" wzur2386vy "select * from cn=users,o=dhs,c=us where cn=%1" 11

rldap 38.118.10.23 389 "cn=orcladmin" changeme1 "select * from cn=users,o=dhs,c=us where cn=%1" 11
