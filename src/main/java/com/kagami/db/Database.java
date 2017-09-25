package com.kagami.db;

public class Database {
    private String dbName;
    private String tableName;
    private DBType databaseType;
    private String schema;


    private enum DBType {
        MYSQL, MS_ACCESS, MS_SQL, ORACLE
    }
}
