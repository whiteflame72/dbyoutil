ldapadd -c -h 38.118.10.23 -p 389 -D "cn=orcladmin" -w "changeme1" -f addEntry.ldif

:ldapadd -c -h 38.118.10.143 -p 389 -D "cn=orcladmin" -w "changeme1" -f addEntry.ldif