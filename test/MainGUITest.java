/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import Defined.QueryFactory;
import java.text.MessageFormat;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yousef
 */
public class MainGUITest {

    public MainGUITest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class MainGUI.
     */
    @Test
    public void testRegisterAndLogin() {
        // initializing test input data
        System.out.println("Testing Login...");

        MainGUI mainGuiForTesting = new MainGUI();
        
        String testDatabaseName = "TestDB", testTableName = "TestTable";
        
        String usernameColName = "testUsername";
        String testTableCols = usernameColName + " varchar(64), "
                + "TestPassword varchar(64)";

        String testUsername = "imATestUsername", testPassword = "imATestPassword";
 
        // before performing test, clean up any remains in DB from previous test/s
        QueryFactory.dropDatabaseIfExists(testDatabaseName, "tempDB" ,mainGuiForTesting.statement);
        
        // Supposed to create a test DB
        QueryFactory.createDBIfNotExists(testDatabaseName, mainGuiForTesting.statement);
        // Supposed to create a test table
        QueryFactory.createTableIfNotExists(testDatabaseName, testTableName, testTableCols, mainGuiForTesting.statement);

        // Supposed to simulate creating a registration in test table
        QueryFactory.insertInto(testDatabaseName,
                testTableName,
                "\'" + testUsername + "\'" + ", " + "\'" + testPassword + "\'",
                mainGuiForTesting.statement);
        
        // Retrieve row entry matching username
        ArrayList<String> rowRetieved
                = QueryFactory.selectUniqueRow(testDatabaseName,
                        testTableName,
                        usernameColName,
                        "\'" + testUsername + "\'",
                        mainGuiForTesting.statement);
        
        
        // Assert login is simulated like it should
        //                                          // index 1 holds password in DB
        assertTrue(!rowRetieved.isEmpty() && rowRetieved.get(1).equals(testPassword));

    }

}
