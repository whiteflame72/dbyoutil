copy /Y d:\digital\life\Database\allmovies5.mdb c:\digital\life\Database\allmovies5.mdb
copy /Y \digital\life\Database\allmovies5.mdb \tmp
copy /Y \digital\life\Database\xxx5.mdb \tmp
rg.cmd  ../sql/nodisk.txt 20
:rg.cmd  ../sql/pending5.txt 20
:r "select title from movies" 20

:pause