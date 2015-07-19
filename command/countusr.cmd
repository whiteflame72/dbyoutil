:rldap 38.118.10.23 389 "cn=admin,cn=users,o=dhs,c=us" wzur2386vy "select cn from cn=users,o=dhs,c=us" 11
:rldap 38.118.10.181 389 "cn=admin,cn=users,o=dhs,c=us" wzur2386vy "select cn from cn=users,o=dhs,c=us order by -cn" 11
rldap 38.118.10.181 389 "cn=admin,cn=users,o=dhs,c=us" wzur2386vy "select count(*) from cn=users,o=dhs,c=us" 11