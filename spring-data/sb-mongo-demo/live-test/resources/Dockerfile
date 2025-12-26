FROM mongo:4.2.1

ENV MONGO_INITDB_ROOT_USERNAME root
ENV MONGO_INITDB_ROOT_PASSWORD root
ENV MONGO_INITDB_DATABASE testdb

COPY init-session.js /docker-entrypoint-initdb.d/

EXPOSE 27017

HEALTHCHECK --interval=5s --timeout=3s --start-period=10s CMD mongo db.stats()
CMD ["mongod", "--replSet", "rs0"]
