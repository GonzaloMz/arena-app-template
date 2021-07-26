#!/bin/bash
java -cp ./tools/hsqldb-2.5.0/hsqldb/lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:database/realestate --dbname.0 realestate &
/backend-src/start-backend.sh $1

