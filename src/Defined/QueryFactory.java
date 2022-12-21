/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Defined;

/**
 *
 * @author yousef
 */
public class QueryFactory {

    public static String createDBIfNotExists(String dataBaseName) {
        return String.format("""
               IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = '%1$s')
               BEGIN
                CREATE DATABASE [%1$s]
               END
               
               GO
                USE [%1$s]
               GO""", dataBaseName);
    }
    
    public static String createTableIfNotExists(String dataBaseName, String tableName, String tableColumnsDefinition) {
        return String.format("""
               use %1$s
               GO
               IF OBJECT_ID(N'%2$s', N'U') IS NULL
               BEGIN
                   CREATE TABLE %2$s (%3$s);
               END;
               GO""", dataBaseName, tableName,tableColumnsDefinition);
    }
    
    public static String dropDatabaseIfExists(String dataBaseName, String tempDatabaseName) {
        return String.format("""
               USE %1$s;
               GO
               DECLARE @SQL nvarchar(max);
               IF EXISTS (SELECT 1 FROM sys.databases WHERE [name] = '%2$s') 
               BEGIN
                   SET @SQL = 
                       N'USE %2$s;
                         ALTER DATABASE %2$s SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
                         USE master;
                         DROP DATABASE %2$s;';
                   EXEC (@SQL);
                   USE %1$s;
               END;
               GO""", tempDatabaseName,dataBaseName);
    }
    public static String dropTableIfExists(String databaseName, String tableName) {
        return String.format("""
               use %1$s
               GO
               DROP TABLE IF EXISTS %2$s;
               GO
               """, databaseName,tableName);
    }
    public static String insertInto(String databaseName, String tableName, String valuesCommaSeparated) {
        return String.format("""
               use %1$s
               GO
               INSERT INTO %2$s
               VALUES (%3$s);
               GO
               """, databaseName,tableName,valuesCommaSeparated);
    }
    

}
