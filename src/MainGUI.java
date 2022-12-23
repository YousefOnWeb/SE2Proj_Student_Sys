/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jdk.jfr.StackTrace;

/**
 *
 * @author yousef
 */
public class MainGUI extends javax.swing.JFrame {

    Properties connectionInfo = new Properties();
    Properties loginData = new Properties();
    String schoolDatabaseName = "SchoolDatabase";
    String studentsTableName = "Students";
    String teachersTableName = "Teachers";
    String connectionURL;
    Connection con = null;
    Statement statement = null;
    ResultSet resSetForCount = null;

    /**
     * Creates new form NewJFrame
     *
     */
    public MainGUI() {
        initComponents();

        try {
            loginData.load(new FileInputStream("loginData.properties"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        LoginIndicatorLabel.setText("Currently logged as: " + loginData.get("name").toString());

        // TODO code application logic here
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  // load the driver
            System.out.println("JDBC Loaded!");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :" + e.getMessage());
        }

        // Retrieving connection user name and password from manually-filled local file 'connectionInfo.properties',
        // and storing them into key-value 'Properties' object 'connectionInfo'
        try {
            connectionInfo.load(new FileInputStream("connectionInfo.properties"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        // forming DB conneciton URL
        connectionURL = "jdbc:sqlserver://localhost:1433;"
                + MessageFormat.format("user={0};", connectionInfo.get("user"))
                + MessageFormat.format("password={0};", connectionInfo.get("password"))
                + "encrypt=true;"
                + "trustServerCertificate=true;";

        try {
            con = DriverManager.getConnection(connectionURL); // initiate connection
            System.out.println("Connection done!");
        } catch (SQLException e) {
            System.out.println("Sql Exception :" + e.getMessage());
        }

        try {
            con.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            statement = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        Defined.QueryFactory.createDBIfNotExists(schoolDatabaseName, statement);
        Defined.QueryFactory.createTableIfNotExists(schoolDatabaseName,
                teachersTableName,
                "Name varchar(25)"
                + ", Username varchar(15) NOT NULL "
                + ", YearsofExperience varchar(15)"
                + ", Address varchar(255)"
                + ", Password varchar(30)",
                statement);
        // ALTER TABLE Teachers ADD PRIMARY KEY (Username);

        Defined.QueryFactory.createTableIfNotExists(schoolDatabaseName,
                studentsTableName,
                "Name varchar(25)"
                + ", Username varchar(15) NOT NULL "
                + ", Grade varchar(15)"
                + ", Address varchar(255)"
                + ", Password varchar(30)",
                statement);
        // ALTER TABLE Teachers ADD PRIMARY KEY (Username);
        
        
        // Display count of registered users
        String queryCount = String.format("""
                            USE [%1$s]
                            ;
                            SELECT  (
                                    SELECT COUNT(*)
                                    FROM   %2$s
                                    )+
                                    (
                                    SELECT COUNT(*)
                                    FROM   %3$s
                                    )
                            AS    SumCount
                            """, schoolDatabaseName, studentsTableName, teachersTableName);
        try {
            resSetForCount = statement.executeQuery(queryCount);
            resSetForCount.next();
            CountIndicatorLabel.setText("Registered users: " +resSetForCount.getString("SumCount"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


//        Defined.QueryFactory.createDBIfNotExists("asdfa", statement);
//        Defined.QueryFactory.setDBAsCurrent("asdfa", statement);
//        Defined.QueryFactory.createTableIfNotExists("asdfa", "table2", "a7aCol1 varchar(255), a7aCol2 varchar(255)", statement);
//        Defined.QueryFactory.insertInto("asdfa", "table2", "'Valioooo','Hellloooo'", statement);
//        
//        ArrayList<String> row = Defined.QueryFactory.selectUniqueRow("asdfa", "table2", "a7aCol1","Valioooo", statement);
//        System.out.println(row.toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainTabbedPanel = new javax.swing.JTabbedPane();
        HomePanel = new javax.swing.JPanel();
        background = new javax.swing.JLabel();
        LoginPanel = new javax.swing.JPanel();
        LoginLabel = new javax.swing.JLabel();
        UsernameLabel = new javax.swing.JLabel();
        UsernameTxtField = new javax.swing.JTextField();
        PasswordLabel = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        RemembermeCheck = new javax.swing.JCheckBox();
        LoginBTN = new javax.swing.JButton();
        LoginTypeChooser = new javax.swing.JComboBox<>();
        LoginIndicatorLabel = new javax.swing.JLabel();
        CountIndicatorLabel = new javax.swing.JLabel();
        RegistrationPanel = new javax.swing.JPanel();
        RegistrationAsATeacher = new javax.swing.JPanel();
        TeacherNameLabel = new javax.swing.JLabel();
        TeacherNameTextField = new javax.swing.JTextField();
        TeacherUsernameLabel = new javax.swing.JLabel();
        TeacherUsernameTextField = new javax.swing.JTextField();
        YoELabel = new javax.swing.JLabel();
        YoETextField = new javax.swing.JTextField();
        TeacherAddressLabel = new javax.swing.JLabel();
        TeacherAddressAreaScrollPane = new javax.swing.JScrollPane();
        TeacherAddressArea = new javax.swing.JTextArea();
        RegisterAsTeacherButton = new javax.swing.JButton();
        TeacherPasswordLabel = new javax.swing.JLabel();
        TeacherPasswordField = new javax.swing.JPasswordField();
        RegistrationAsAStudent = new javax.swing.JPanel();
        StudentNameLabel = new javax.swing.JLabel();
        StudentNameTextField = new javax.swing.JTextField();
        StudentUsernameLabel = new javax.swing.JLabel();
        StudentUsernameTextField = new javax.swing.JTextField();
        GradeLabel = new javax.swing.JLabel();
        GradeTextField = new javax.swing.JTextField();
        StudentAddressLabel = new javax.swing.JLabel();
        StudentAddressAreaScrollPane = new javax.swing.JScrollPane();
        StudentAddressArea = new javax.swing.JTextArea();
        RegisterAsStudentButton = new javax.swing.JButton();
        StudentPasswordLabel = new javax.swing.JLabel();
        StudentPasswordField = new javax.swing.JPasswordField();
        ContactUsPanel = new javax.swing.JPanel();
        ContactHeadingLabel = new javax.swing.JLabel();
        StudentSupportLabel = new javax.swing.JLabel();
        StudentSupportEmail = new javax.swing.JLabel();
        TeacherSupportLabel = new javax.swing.JLabel();
        TeacherSupportEmail = new javax.swing.JLabel();
        AboutUsPanel = new javax.swing.JPanel();
        Headlabel = new javax.swing.JLabel();
        AboutUsLabel = new javax.swing.JLabel();
        AboutUs = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background.jpg"))); // NOI18N

        javax.swing.GroupLayout HomePanelLayout = new javax.swing.GroupLayout(HomePanel);
        HomePanel.setLayout(HomePanelLayout);
        HomePanelLayout.setHorizontalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        HomePanelLayout.setVerticalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        MainTabbedPanel.addTab("Home", HomePanel);

        LoginPanel.setBackground(new java.awt.Color(130, 160, 177));

        LoginLabel.setBackground(new java.awt.Color(159, 242, 242));
        LoginLabel.setFont(new java.awt.Font("Eras Bold ITC", 1, 36)); // NOI18N
        LoginLabel.setForeground(new java.awt.Color(45, 35, 46));
        LoginLabel.setText("     Login");

        UsernameLabel.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        UsernameLabel.setForeground(new java.awt.Color(45, 35, 46));
        UsernameLabel.setText("Username");

        UsernameTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameTxtFieldActionPerformed(evt);
            }
        });

        PasswordLabel.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        PasswordLabel.setForeground(new java.awt.Color(45, 35, 46));
        PasswordLabel.setText("Password");

        PasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordFieldActionPerformed(evt);
            }
        });

        RemembermeCheck.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        RemembermeCheck.setText("Remember me");
        RemembermeCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemembermeCheckActionPerformed(evt);
            }
        });

        LoginBTN.setBackground(new java.awt.Color(45, 35, 46));
        LoginBTN.setForeground(new java.awt.Color(255, 255, 255));
        LoginBTN.setText("Login");
        LoginBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBTNActionPerformed(evt);
            }
        });

        LoginTypeChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Teacher", "Student" }));

        LoginIndicatorLabel.setBackground(new java.awt.Color(255, 255, 255));
        LoginIndicatorLabel.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        LoginIndicatorLabel.setText("Currently Logged As: None");

        CountIndicatorLabel.setText("Registered users: 0");

        javax.swing.GroupLayout LoginPanelLayout = new javax.swing.GroupLayout(LoginPanel);
        LoginPanel.setLayout(LoginPanelLayout);
        LoginPanelLayout.setHorizontalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LoginPanelLayout.createSequentialGroup()
                        .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LoginPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 302, Short.MAX_VALUE)
                                .addComponent(LoginTypeChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginPanelLayout.createSequentialGroup()
                                .addComponent(CountIndicatorLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LoginBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(RemembermeCheck)
                                    .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(PasswordLabel)
                                        .addComponent(UsernameLabel)
                                        .addComponent(UsernameTxtField)
                                        .addComponent(LoginLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                                        .addComponent(PasswordField)))))
                        .addContainerGap(212, Short.MAX_VALUE))
                    .addGroup(LoginPanelLayout.createSequentialGroup()
                        .addComponent(LoginIndicatorLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        LoginPanelLayout.setVerticalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(LoginIndicatorLabel)
                .addGap(99, 99, 99)
                .addComponent(LoginLabel)
                .addGap(41, 41, 41)
                .addComponent(UsernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsernameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RemembermeCheck)
                .addGap(3, 3, 3)
                .addComponent(LoginTypeChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginBTN)
                    .addComponent(CountIndicatorLabel))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        MainTabbedPanel.addTab("Login", LoginPanel);

        RegistrationAsATeacher.setBorder(javax.swing.BorderFactory.createTitledBorder("As A Teacher"));
        RegistrationAsATeacher.setName(""); // NOI18N

        TeacherNameLabel.setText("Name");

        TeacherNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TeacherNameTextFieldActionPerformed(evt);
            }
        });

        TeacherUsernameLabel.setText("Username");

        TeacherUsernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TeacherUsernameTextFieldActionPerformed(evt);
            }
        });

        YoELabel.setText("Years of experience");

        YoETextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YoETextFieldActionPerformed(evt);
            }
        });

        TeacherAddressLabel.setText("Address");

        TeacherAddressArea.setColumns(20);
        TeacherAddressArea.setRows(5);
        TeacherAddressAreaScrollPane.setViewportView(TeacherAddressArea);

        RegisterAsTeacherButton.setText("Register New Teacher");
        RegisterAsTeacherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterAsTeacherButtonActionPerformed(evt);
            }
        });

        TeacherPasswordLabel.setText("Password");

        javax.swing.GroupLayout RegistrationAsATeacherLayout = new javax.swing.GroupLayout(RegistrationAsATeacher);
        RegistrationAsATeacher.setLayout(RegistrationAsATeacherLayout);
        RegistrationAsATeacherLayout.setHorizontalGroup(
            RegistrationAsATeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistrationAsATeacherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RegistrationAsATeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RegistrationAsATeacherLayout.createSequentialGroup()
                        .addGroup(RegistrationAsATeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(YoELabel)
                            .addComponent(TeacherAddressLabel))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(RegistrationAsATeacherLayout.createSequentialGroup()
                        .addGroup(RegistrationAsATeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RegistrationAsATeacherLayout.createSequentialGroup()
                                .addGroup(RegistrationAsATeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TeacherUsernameLabel)
                                    .addComponent(TeacherNameLabel))
                                .addGap(168, 168, 168)
                                .addGroup(RegistrationAsATeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TeacherAddressAreaScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                                    .addGroup(RegistrationAsATeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(TeacherUsernameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TeacherNameTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                        .addComponent(YoETextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(RegistrationAsATeacherLayout.createSequentialGroup()
                                .addComponent(TeacherPasswordLabel)
                                .addGap(170, 170, 170)
                                .addGroup(RegistrationAsATeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(RegisterAsTeacherButton)
                                    .addComponent(TeacherPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(8, 8, 8))))
        );
        RegistrationAsATeacherLayout.setVerticalGroup(
            RegistrationAsATeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistrationAsATeacherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RegistrationAsATeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TeacherNameLabel)
                    .addComponent(TeacherNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RegistrationAsATeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TeacherUsernameLabel)
                    .addComponent(TeacherUsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RegistrationAsATeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(YoELabel)
                    .addComponent(YoETextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RegistrationAsATeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TeacherAddressLabel)
                    .addComponent(TeacherAddressAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RegistrationAsATeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TeacherPasswordLabel)
                    .addComponent(TeacherPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RegisterAsTeacherButton)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        RegistrationAsAStudent.setBorder(javax.swing.BorderFactory.createTitledBorder("As A Student"));
        RegistrationAsAStudent.setName(""); // NOI18N

        StudentNameLabel.setText("Name");

        StudentNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudentNameTextFieldActionPerformed(evt);
            }
        });

        StudentUsernameLabel.setText("Username");

        StudentUsernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudentUsernameTextFieldActionPerformed(evt);
            }
        });

        GradeLabel.setText("Grade");

        GradeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GradeTextFieldActionPerformed(evt);
            }
        });

        StudentAddressLabel.setText("Address");

        StudentAddressArea.setColumns(20);
        StudentAddressArea.setRows(5);
        StudentAddressAreaScrollPane.setViewportView(StudentAddressArea);

        RegisterAsStudentButton.setText("Register New Student");
        RegisterAsStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterAsStudentButtonActionPerformed(evt);
            }
        });

        StudentPasswordLabel.setText("Password");

        javax.swing.GroupLayout RegistrationAsAStudentLayout = new javax.swing.GroupLayout(RegistrationAsAStudent);
        RegistrationAsAStudent.setLayout(RegistrationAsAStudentLayout);
        RegistrationAsAStudentLayout.setHorizontalGroup(
            RegistrationAsAStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistrationAsAStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RegistrationAsAStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RegistrationAsAStudentLayout.createSequentialGroup()
                        .addGroup(RegistrationAsAStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(GradeLabel)
                            .addComponent(StudentAddressLabel))
                        .addGap(180, 180, 180)
                        .addGroup(RegistrationAsAStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(StudentAddressAreaScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                            .addGroup(RegistrationAsAStudentLayout.createSequentialGroup()
                                .addComponent(GradeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(RegistrationAsAStudentLayout.createSequentialGroup()
                        .addGroup(RegistrationAsAStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RegistrationAsAStudentLayout.createSequentialGroup()
                                .addGroup(RegistrationAsAStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(StudentUsernameLabel)
                                    .addComponent(StudentNameLabel))
                                .addGap(168, 168, 168)
                                .addGroup(RegistrationAsAStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(StudentNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                    .addComponent(StudentUsernameTextField)))
                            .addGroup(RegistrationAsAStudentLayout.createSequentialGroup()
                                .addComponent(StudentPasswordLabel)
                                .addGap(168, 168, 168)
                                .addGroup(RegistrationAsAStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(RegisterAsStudentButton)
                                    .addComponent(StudentPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        RegistrationAsAStudentLayout.setVerticalGroup(
            RegistrationAsAStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistrationAsAStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RegistrationAsAStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StudentNameLabel)
                    .addComponent(StudentNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RegistrationAsAStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StudentUsernameLabel)
                    .addComponent(StudentUsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RegistrationAsAStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GradeLabel)
                    .addComponent(GradeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RegistrationAsAStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StudentAddressLabel)
                    .addComponent(StudentAddressAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RegistrationAsAStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StudentPasswordLabel)
                    .addComponent(StudentPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RegisterAsStudentButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout RegistrationPanelLayout = new javax.swing.GroupLayout(RegistrationPanel);
        RegistrationPanel.setLayout(RegistrationPanelLayout);
        RegistrationPanelLayout.setHorizontalGroup(
            RegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistrationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RegistrationAsATeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RegistrationAsAStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        RegistrationPanelLayout.setVerticalGroup(
            RegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistrationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RegistrationAsATeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RegistrationAsAStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainTabbedPanel.addTab("Registration", RegistrationPanel);

        ContactHeadingLabel.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        ContactHeadingLabel.setText("Our Communication Channels");

        StudentSupportLabel.setText("Student Support");

        StudentSupportEmail.setText("stsupport@xyz.com");

        TeacherSupportLabel.setText("Teacher Support");

        TeacherSupportEmail.setText("tsupport@xyz.com");

        javax.swing.GroupLayout ContactUsPanelLayout = new javax.swing.GroupLayout(ContactUsPanel);
        ContactUsPanel.setLayout(ContactUsPanelLayout);
        ContactUsPanelLayout.setHorizontalGroup(
            ContactUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContactUsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ContactUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ContactHeadingLabel)
                    .addGroup(ContactUsPanelLayout.createSequentialGroup()
                        .addGroup(ContactUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(StudentSupportLabel)
                            .addComponent(TeacherSupportLabel))
                        .addGap(71, 71, 71)
                        .addGroup(ContactUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TeacherSupportEmail)
                            .addComponent(StudentSupportEmail))))
                .addContainerGap(309, Short.MAX_VALUE))
        );
        ContactUsPanelLayout.setVerticalGroup(
            ContactUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContactUsPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(ContactHeadingLabel)
                .addGap(18, 18, 18)
                .addGroup(ContactUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StudentSupportLabel)
                    .addComponent(StudentSupportEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ContactUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TeacherSupportLabel)
                    .addComponent(TeacherSupportEmail))
                .addContainerGap(441, Short.MAX_VALUE))
        );

        MainTabbedPanel.addTab("Contact Us", ContactUsPanel);

        AboutUsPanel.setBackground(new java.awt.Color(130, 160, 177));

        Headlabel.setFont(new java.awt.Font("Gill Sans Ultra Bold", 1, 24)); // NOI18N
        Headlabel.setForeground(new java.awt.Color(45, 35, 46));
        Headlabel.setText("WHERE EVERY LEARNER IS A LEADER");

        AboutUsLabel.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        AboutUsLabel.setForeground(new java.awt.Color(255, 255, 255));
        AboutUsLabel.setText("                            About Us");

        AboutUs.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        AboutUs.setText("<html>"
            + "We build a bright future for your child.<br>"
            + "We are best education in the global world.<br>"
            + "Small Class sizes with well trained teachers.<br>"
            + "Loving and caring enviroment for students.<br>"
        );

        javax.swing.GroupLayout AboutUsPanelLayout = new javax.swing.GroupLayout(AboutUsPanel);
        AboutUsPanel.setLayout(AboutUsPanelLayout);
        AboutUsPanelLayout.setHorizontalGroup(
            AboutUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AboutUsPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(Headlabel, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(AboutUsPanelLayout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(AboutUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AboutUs)
                    .addComponent(AboutUsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AboutUsPanelLayout.setVerticalGroup(
            AboutUsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AboutUsPanelLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(Headlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(AboutUsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AboutUs)
                .addContainerGap(281, Short.MAX_VALUE))
        );

        MainTabbedPanel.addTab("About Us", AboutUsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainTabbedPanel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainTabbedPanel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TeacherNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TeacherNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TeacherNameTextFieldActionPerformed

    private void TeacherUsernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TeacherUsernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TeacherUsernameTextFieldActionPerformed

    private void YoETextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YoETextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_YoETextFieldActionPerformed

    private void RegisterAsTeacherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterAsTeacherButtonActionPerformed
        // TODO add your handling code here:
        String nameTeacher = TeacherNameTextField.getText();
        String usernameTeacher = TeacherUsernameTextField.getText();
        String YearsOfExperince = YoETextField.getText();
        String AddressTeacher = TeacherAddressArea.getText();
        String passwordTeacher = String.valueOf(TeacherPasswordField.getPassword());

        if (nameTeacher.isEmpty() || usernameTeacher.isEmpty() || YearsOfExperince.isEmpty() || AddressTeacher.isEmpty() || passwordTeacher.isEmpty()) {
            String Output = "Please Enter All Data " + nameTeacher;
            JOptionPane.showMessageDialog(this, Output);

        } else {

            String RowValueToInsert = String.format(" '%1$s','%2$s',%3$s,'%4$s','%5$s' ",
                    nameTeacher,
                    usernameTeacher,
                    YearsOfExperince,
                    AddressTeacher,
                    passwordTeacher);

            Defined.QueryFactory.insertInto(schoolDatabaseName, teachersTableName, RowValueToInsert, statement);

            String Output = "Register Successfull " + nameTeacher;
            JOptionPane.showMessageDialog(this, Output);

        }
    }//GEN-LAST:event_RegisterAsTeacherButtonActionPerformed

    private void StudentNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StudentNameTextFieldActionPerformed

    private void StudentUsernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentUsernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StudentUsernameTextFieldActionPerformed

    private void GradeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GradeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GradeTextFieldActionPerformed

    private void RegisterAsStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterAsStudentButtonActionPerformed
        // TODO add your handling code here:
        String nameStudent = StudentNameTextField.getText();
        String usernameStudent = StudentUsernameTextField.getText();
        String Grade = GradeTextField.getText();
        String AddressStudent = StudentAddressArea.getText();
        String passwordStudent = String.valueOf(StudentPasswordField.getPassword());

        if (nameStudent.isEmpty() || usernameStudent.isEmpty() || Grade.isEmpty() || AddressStudent.isEmpty() || passwordStudent.isEmpty()) {
            String Output = "Please Enter All Data " + nameStudent;
            JOptionPane.showMessageDialog(this, Output);

        } else {

            String valuesToInsertIntoRow = String.format(" '%1$s','%2$s',%3$s,'%4$s','%5$s' ",
                    nameStudent,
                    usernameStudent,
                    Grade,
                    AddressStudent,
                    passwordStudent);

            Defined.QueryFactory.insertInto(schoolDatabaseName, studentsTableName, valuesToInsertIntoRow, statement);

            String Output = "Register Successfull " + nameStudent;
            JOptionPane.showMessageDialog(this, Output);

        }
    }//GEN-LAST:event_RegisterAsStudentButtonActionPerformed

    private void UsernameTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameTxtFieldActionPerformed

    private void PasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordFieldActionPerformed

    private void RemembermeCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemembermeCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RemembermeCheckActionPerformed

    private void LoginBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBTNActionPerformed
        // TODO add your handling code here:
        String usernameEntered = UsernameTxtField.getText();
        String passwordEntered = String.valueOf(PasswordField.getPassword());
        String loginTypeSelected = LoginTypeChooser.getSelectedItem().toString();

        if (usernameEntered.isEmpty() || passwordEntered.isEmpty()) {
            String Output = "Enter Your Login Data ";
            JOptionPane.showMessageDialog(this, Output);
        } else {

            ArrayList<String> row = Defined.QueryFactory.selectUniqueRow(schoolDatabaseName,
                    loginTypeSelected.equals("Teacher") ? teachersTableName : studentsTableName,
                    "Username" // Column
                    ,
                     "\'" + usernameEntered + "\'",
                    statement);

            if (row.isEmpty() || !row.get(4).equals(passwordEntered)) {
                JOptionPane.showMessageDialog(this, "Wrong Login Credentials");
            } else {
                String messageText = String.format("""
                                              Your Profile:
                                              =====================
                                                   
                                              Name: %1$s
                                              ------
                                              Username: %2$s
                                              ------
                                              %3$s: %4$s
                                              ------
                                              Address:
                                              %5$s
                                              ------
                                              
                                              """, row.get(0),
                        row.get(1),
                        loginTypeSelected.equals("Teacher") ? "Years of experience" : "Grade",
                        row.get(2),
                        row.get(3));
                JOptionPane.showMessageDialog(this, messageText);

                LoginIndicatorLabel.setText("Currently logged as: " + row.get(0));

                if (RemembermeCheck.isSelected()) {
                    loginData.setProperty("name", row.get(0));
                } else {
                    loginData.setProperty("name", "None");
                }

                try {
                    loginData.store(new FileOutputStream("loginData.properties"), null);
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }

        }
    }//GEN-LAST:event_LoginBTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AboutUs;
    private javax.swing.JLabel AboutUsLabel;
    private javax.swing.JPanel AboutUsPanel;
    private javax.swing.JLabel ContactHeadingLabel;
    private javax.swing.JPanel ContactUsPanel;
    private javax.swing.JLabel CountIndicatorLabel;
    private javax.swing.JLabel GradeLabel;
    private javax.swing.JTextField GradeTextField;
    private javax.swing.JLabel Headlabel;
    private javax.swing.JPanel HomePanel;
    private javax.swing.JButton LoginBTN;
    private javax.swing.JLabel LoginIndicatorLabel;
    private javax.swing.JLabel LoginLabel;
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JComboBox<String> LoginTypeChooser;
    private javax.swing.JTabbedPane MainTabbedPanel;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JButton RegisterAsStudentButton;
    private javax.swing.JButton RegisterAsTeacherButton;
    private javax.swing.JPanel RegistrationAsAStudent;
    private javax.swing.JPanel RegistrationAsATeacher;
    private javax.swing.JPanel RegistrationPanel;
    private javax.swing.JCheckBox RemembermeCheck;
    private javax.swing.JTextArea StudentAddressArea;
    private javax.swing.JScrollPane StudentAddressAreaScrollPane;
    private javax.swing.JLabel StudentAddressLabel;
    private javax.swing.JLabel StudentNameLabel;
    private javax.swing.JTextField StudentNameTextField;
    private javax.swing.JPasswordField StudentPasswordField;
    private javax.swing.JLabel StudentPasswordLabel;
    private javax.swing.JLabel StudentSupportEmail;
    private javax.swing.JLabel StudentSupportLabel;
    private javax.swing.JLabel StudentUsernameLabel;
    private javax.swing.JTextField StudentUsernameTextField;
    private javax.swing.JTextArea TeacherAddressArea;
    private javax.swing.JScrollPane TeacherAddressAreaScrollPane;
    private javax.swing.JLabel TeacherAddressLabel;
    private javax.swing.JLabel TeacherNameLabel;
    private javax.swing.JTextField TeacherNameTextField;
    private javax.swing.JPasswordField TeacherPasswordField;
    private javax.swing.JLabel TeacherPasswordLabel;
    private javax.swing.JLabel TeacherSupportEmail;
    private javax.swing.JLabel TeacherSupportLabel;
    private javax.swing.JLabel TeacherUsernameLabel;
    private javax.swing.JTextField TeacherUsernameTextField;
    private javax.swing.JLabel UsernameLabel;
    private javax.swing.JTextField UsernameTxtField;
    private javax.swing.JLabel YoELabel;
    private javax.swing.JTextField YoETextField;
    private javax.swing.JLabel background;
    // End of variables declaration//GEN-END:variables
}
