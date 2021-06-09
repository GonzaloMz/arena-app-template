#!/bin/bash
java -cp ./tools/hsqldb-2.5.0/hsqldb/lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:database/realestate --dbname.0 realestate &
cd /backend/; ./start-backend.sh

