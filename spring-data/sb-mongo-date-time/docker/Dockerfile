FROM mongo:latest

COPY testdata.json /tmp/testdata.json
COPY importscript.sh /docker-entrypoint-initdb.d/

ADD init-mongo.js /docker-entrypoint-initdb.d/