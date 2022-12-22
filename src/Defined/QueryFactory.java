/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Defined;

import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yousef
 */
public class QueryFactory {

    /**
     * Creates a database of given 'databaseName' if not previously exists
     *
     * @param databaseName name of DB to be created
     * @param statement SQL Connection's Statement object
     * @return True if query has a result (e.g. SELECT), False if it doesn't
     * (e.g. INSERT or DROP)
     */
    public static boolean createDBIfNotExists(String databaseName, Statement statement) {
        String query = String.format("""
               IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = '%1$s')
               BEGIN
                CREATE DATABASE [%1$s]
               END
               """, databaseName);
        boolean execResult = false;
        try {
            execResult = statement.execute(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return execResult;
    }

    /**
     * Sets database of given 'databaseName' as the DB to be affected by next
     * SQL instruction/s
     *
     * @param databaseName name of DB to be set
     * @param statement SQL Connection's Statement object
     * @return True if query has a result (e.g. SELECT), False if it doesn't
     * (e.g. INSERT or DROP)
     */
    public static boolean setDBAsCurrent(String databaseName, Statement statement) {
        String query = String.format("""
               USE [%1$s]
               """, databaseName);
        boolean execResult = false;
        try {
            execResult = statement.execute(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return execResult;
    }

    /**
     * Creates a table of given 'tableName' in given BD 'dataBaseName' if not
     * previously exists
     *
     * @param databaseName name of DB in which table to be created
     * @param tableName name of table to be created
     * @param tableColumnsDefinition string specifying table columns. e.g. "col1
     * varchar(64), col2 int"
     * @param statement SQL Connection's Statement object
     * @return True if query has a result (e.g. SELECT), False if it doesn't
     * (e.g. INSERT or DROP)
     */
    public static boolean createTableIfNotExists(String dataBaseName, String tableName, String tableColumnsDefinition, Statement statement) {
        String query = String.format("""
               use %1$s
               IF OBJECT_ID(N'%2$s', N'U') IS NULL
               BEGIN
                   CREATE TABLE %2$s (%3$s);
               END;
                 """, dataBaseName, tableName, tableColumnsDefinition);
        boolean execResult = false;
        try {
            execResult = statement.execute(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return execResult;
    }

    public static boolean dropDatabaseIfExists(String dataBaseName, String tempDatabaseName, Statement statement) {
        String query = String.format("""
               USE %1$s;
               ;
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
               ;""", tempDatabaseName, dataBaseName);
        boolean execResult = false;
        try {
            execResult = statement.execute(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return execResult;
    }

    public static boolean dropTableIfExists(String databaseName, String tableName, Statement statement) {
        String query = String.format("""
               use %1$s
               ;
               DROP TABLE IF EXISTS %2$s;
               ;
               """, databaseName, tableName);
        boolean execResult = false;
        try {
            execResult = statement.execute(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return execResult;
    }

    //not tested but mostly working
    public static boolean insertInto(String databaseName, String tableName, String valuesCommaSeparated, Statement statement) {
        String query = String.format("""
               USE %1$s
               ;
               INSERT INTO %2$s
               VALUES (%3$s);
               ;
               """, databaseName, tableName, valuesCommaSeparated);
        boolean execResult = false;
        try {
            execResult = statement.execute(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return execResult;
    }

    
    /**
     * takes name of column holding primary key (e.g. 'username'),
     * and primary key value of a specific row,
     * and returns that row as an ArrayList of strings
     */
    public static ArrayList<String> selectUniqueRow(String databaseName, String tableName, String primaryKeyColName, String primaryKey, Statement statement) {
        String query = String.format("""
               USE %1$s
               ;
               SELECT * FROM %2$s WHERE %3$s = '%4$s';
               """, databaseName, tableName, primaryKeyColName, primaryKey);
        
        ResultSet resultSet = null;
        ArrayList<String> row = new ArrayList<>();

        int nOfCols = -1;

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            nOfCols = resultSet.getMetaData().getColumnCount();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            if (resultSet.next()) {
                for (int i = 1; i <= nOfCols; i++) {
                    row.add(resultSet.getString(i));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return row;
    }

}
