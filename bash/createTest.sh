FILENAME=$1ServiceTest.java
cp -f test.template $FILENAME
sed -i -e "s/{ENTITY}/$1/g" $FILENAME 
sed -i -e "s/{ENTITY_LOWER}/$2/g" $FILENAME
