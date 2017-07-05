/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ClassModel.DBConnection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Callum
 */
public class EnrollForm extends javax.swing.JPanel {

    private Connection connection;
    private Statement statement;
    private JFrame frame;

    /**
     * Creates new form NewStudentForm
     *
     * @param frame the frame storing the panel
     */
    public EnrollForm(JFrame frame) {
        initialiseConstructor(frame);
    }

    /**
     * This method was created to reduce duplicate code for the two different
     * constructors. Initial values for the class are set within this method.
     *
     * @param frame the frame storing the panel
     */
    public void initialiseConstructor(JFrame frame) {
        this.frame = frame;
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        initComponents();
        comboStudent.setModel(new javax.swing.DefaultComboBoxModel<>(getStudents()));
        comboStudent.setSelectedItem(null);

        txtGrade.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                String key = String.valueOf(e.getKeyChar());
                Pattern pattern = Pattern.compile("[A-Z+-]");
                Matcher matcher = pattern.matcher(key);
                if (txtGrade.getText().length() >= 4) {
                    e.consume();
                } else if (!matcher.find()) {
                    e.consume();
                }
            }
        });

        comboClass.setModel(new javax.swing.DefaultComboBoxModel<>(getClasses()));
        comboClass.setSelectedItem(null);

        txtGrade.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                String key = String.valueOf(e.getKeyChar());
                Pattern pattern = Pattern.compile("[A-Z+-]");
                Matcher matcher = pattern.matcher(key);
                if (txtGrade.getText().length() >= 4) {
                    e.consume();
                } else if (!matcher.find()) {
                    e.consume();
                }
            }
        });
    }

    /**
     * Clears all fields in the course form
     */
    public void clearForm() {
        comboClass.setSelectedItem(null);
        comboStudent.setSelectedItem(null);
        txtGrade.setText(null);
    }

    /**
     * This method is used for populating the student combo box.
     *
     * @return a string array of all parents in the database
     */
    public String[] getStudents() {
        try {
            ArrayList<String> studentList = new ArrayList<String>();
            statement = connection.createStatement();
            ResultSet rowCountSet = statement.executeQuery("SELECT COUNT(*) AS count FROM student");
            rowCountSet.next();
            int count = rowCountSet.getInt("count");

            String[] studentArray = new String[count];
            int i = 0;
            ResultSet studentSet = statement.executeQuery("SELECT * FROM student");

            while (studentSet.next()) {
                int studentID = studentSet.getInt("studentID");
                String firstName = studentSet.getString("firstName");
                String lastName = studentSet.getString("lastName");
                String fullName = firstName + " " + lastName;

                studentArray[i] = "" + studentID + ")" + fullName;
                i++;
            }
            return studentArray;
        } catch (SQLException ex) {
            Logger.getLogger(EnrollForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @return an array of all classes stored in the system database
     */
    public String[] getClasses() {
        try {
            ArrayList<String> classList = new ArrayList<String>();
            statement = connection.createStatement();
            ResultSet rowCountSet = statement.executeQuery("SELECT COUNT(*) AS count FROM class");
            rowCountSet.next();
            int count = rowCountSet.getInt("count");

            String[] classArray = new String[count];
            int i = 0;
            ResultSet classSet = statement.executeQuery("SELECT * FROM class");

            while (classSet.next()) {
                int classID = classSet.getInt("classID");
                String classCode = classSet.getString("classCode");

                classArray[i] = "" + classID + ")" + classCode;
                i++;
            }
            return classArray;
        } catch (SQLException ex) {
            Logger.getLogger(EnrollForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblClassCode = new javax.swing.JLabel();
        lblTeacher = new javax.swing.JLabel();
        comboStudent = new javax.swing.JComboBox<>();
        lblTitle = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        comboClass = new javax.swing.JComboBox<>();
        lblGrade = new javax.swing.JLabel();
        txtGrade = new javax.swing.JFormattedTextField();

        setBackground(new java.awt.Color(0, 204, 204));

        lblClassCode.setText("Class:");
        lblClassCode.setPreferredSize(new java.awt.Dimension(90, 20));

        lblTeacher.setText("Student:");
        lblTeacher.setPreferredSize(new java.awt.Dimension(90, 20));

        comboStudent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboStudent.setPreferredSize(new java.awt.Dimension(56, 20));

        lblTitle.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        lblTitle.setText("Enroll Student");
        lblTitle.setPreferredSize(new java.awt.Dimension(200, 44));

        btnSubmit.setText("Submit");
        btnSubmit.setMaximumSize(new java.awt.Dimension(60, 25));
        btnSubmit.setMinimumSize(new java.awt.Dimension(60, 25));
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        comboClass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboClass.setPreferredSize(new java.awt.Dimension(56, 20));

        lblGrade.setText("Grade:");
        lblGrade.setPreferredSize(new java.awt.Dimension(90, 20));

        txtGrade.setBackground(new java.awt.Color(255, 255, 255));
        txtGrade.setPreferredSize(new java.awt.Dimension(4, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblClassCode, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(lblTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGrade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                        .addComponent(btnHome))
                    .addComponent(comboStudent, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGrade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(125, 125, 125))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClassCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHome))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public boolean addEnrollment(int studentID, int classID, String grade) {
        try {
            statement = connection.createStatement();
            ResultSet enrollSet = statement.executeQuery("SELECT count(*) AS count FROM enroll WHERE classID = " + classID + " AND studentID = " + studentID);
            enrollSet.next();
            int enrollCount = enrollSet.getInt("count");

            if (enrollCount == 0) {
                statement.execute("SET FOREIGN_KEY_CHECKS = 0");
                statement.execute("INSERT INTO enroll (studentID, classID, grade) "
                        + "VALUES (" + studentID + ", " + classID + ", '" + grade + "')");
                statement.execute("SET FOREIGN_KEY_CHECKS = 1");
                JOptionPane.showMessageDialog(this,
                        "Student enrolled successfully added",
                        "RFID System",
                        JOptionPane.PLAIN_MESSAGE);
                clearForm();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnrollForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        Object comboRetrieveStudent = comboStudent.getSelectedItem();
        String comboStringStudent = comboRetrieveStudent.toString();
        String[] comboSplitStudent = comboStringStudent.split("\\)");
        int studentID = Integer.parseInt(comboSplitStudent[0]);

        Object comboRetrieveClass = comboClass.getSelectedItem();
        String comboStringClass = comboRetrieveClass.toString();
        String[] comboSplitClass = comboStringClass.split("\\)");
        int classID = Integer.parseInt(comboSplitClass[0]);
        String grade = txtGrade.getText();
        addEnrollment(studentID, classID, grade);
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        frame.remove(this);
        frame.add(new HomeForm(frame));
        frame.pack();
    }//GEN-LAST:event_btnHomeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox<String> comboClass;
    private javax.swing.JComboBox<String> comboStudent;
    private javax.swing.JLabel lblClassCode;
    private javax.swing.JLabel lblGrade;
    private javax.swing.JLabel lblTeacher;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JFormattedTextField txtGrade;
    // End of variables declaration//GEN-END:variables
}
