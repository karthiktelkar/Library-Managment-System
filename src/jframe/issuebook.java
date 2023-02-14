/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Locale;
import java.sql.*;
import java.sql.DriverManager.*;
import javax.swing.JOptionPane;
import javax.swing.table.*; 
import java.sql.ResultSet;
import javax.swing.UnsupportedLookAndFeelException;
/**
 *
 * @author HP
 */
public class issuebook extends javax.swing.JFrame {

    /**
     * Creates new form issuebook
     */ Locale l = null;
    public issuebook() {
        initComponents();
        l = new Locale("en", "US");
        jDateChooser1.setLocale(l);
    }
    
    public void getBookDetails (){
       int bookId = Integer.parseInt(txt_issue_bookid.getText());
       
       try{
           Connection con = DBconnection.getConnection();
           PreparedStatement pst = con.prepareStatement("select * from book_details where book_id = ?");
           pst.setInt(1,bookId);
           ResultSet rs = pst.executeQuery();
           
           if(rs.next()){
              lbl_book_id.setText(rs.getString("book_id"));
              lbl_book_name.setText(rs.getString("book_name"));
              lbl_author.setText(rs.getString("author"));
              lbl_quantity.setText(rs.getString("quantity"));
           }else{
               lbl_bookerror.setText("invalid book id");
           }
       } catch (Exception e){
           e.printStackTrace();
       }
    }
    
    public void getStudentDetails (){
        int studentId = Integer.parseInt(txt_issue_studentid.getText());

        try {
            Connection con = DBconnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from student_details where student_id = ?");
            pst.setInt(1, studentId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lbl_studentid.setText(rs.getString(studentId));
                lbl_student_name.setText(rs.getString("name"));
                lbl_student_sem.setText(rs.getString("sem"));
                lbl_branch.setText(rs.getString("branch"));
            } else {
                lbl_bookerror.setText("invalid student id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean issueBook() {
        boolean isIssued = false;
        int bookId = Integer.parseInt(txt_issue_bookid.getText());
        int studentId = Integer.parseInt(txt_issue_studentid.getText());
        String bookName = lbl_book_name.getText();
        String studentName = lbl_student_name.getText();

        Date uIssueDate = jDateChooser1.getDate();
        Date uDueDate = jDateChooser2.getDate();

        Long l1 = uIssueDate.getTime();
        long l2 = uDueDate.getTime();

        java.sql.Date sIssueDate = new java.sql.Date(l1);
        java.sql.Date sDueDate = new java.sql.Date(l2);

        try {
            Connection con = DBconnection.getConnection();
            String sql = "insert into issue_book_details(book_id,book_name,student_id,student_name,"
                    + "issue_date,due_date,status) values(?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setString(2, bookName);
            pst.setInt(3, studentId);
            pst.setString(4, studentName);
            pst.setDate(5, sIssueDate);
            pst.setDate(6, sDueDate);
            pst.setString(7, "pending");

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isIssued = true;
            } else {
                isIssued = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isIssued;

    }
    public void updateBookCount() {
        int bookId = Integer.parseInt(txt_issue_bookid.getText());
        try {
            Connection con = DBconnection.getConnection();
            String sql = "update book_details set quantity = quantity - 1 where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);

            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "book count updated");
                int initialCount = Integer.parseInt(lbl_quantity.getText());
                lbl_quantity.setText(Integer.toString(initialCount - 1));
            } else {
                JOptionPane.showMessageDialog(this, "can't update book count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //checking whether book already allocated or not
    public boolean isAlreadyIssued() {

        boolean isAlreadyIssued = false;
        int bookId = Integer.parseInt(txt_issue_bookid.getText());
        int studentId = Integer.parseInt(txt_issue_studentid.getText());

        try {
            Connection con = DBconnection.getConnection();
            String sql = "select * from issue_book_details where book_id = ? and student_id = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setInt(2, studentId);
            pst.setString(3, "pending");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                isAlreadyIssued = true;
            } else {
                isAlreadyIssued = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAlreadyIssued;

    }/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_book_id = new javax.swing.JLabel();
        lbl_book_name = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        lbl_bookerror = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbl_student_name = new javax.swing.JLabel();
        lbl_student_sem = new javax.swing.JLabel();
        lbl_studentid = new javax.swing.JLabel();
        lbl_branch = new javax.swing.JLabel();
        lbl_studenterror = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txt_issue_bookid = new app.bolivia.swing.JCTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_issue_studentid = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        issue_book_button = new rojerusan.RSMaterialButtonCircle();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Book_50px_1.png"))); // NOI18N
        jLabel2.setText("Book Details");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setAlignmentX(0.2F);
        jPanel4.setAlignmentY(0.3F);
        jPanel4.setPreferredSize(new java.awt.Dimension(376, 4));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BOOK ID :");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("BOOK NAME :");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("AUTHOR :");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("QUANTITY :");

        lbl_book_id.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N

        lbl_book_name.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N

        lbl_author.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N

        lbl_quantity.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N

        lbl_bookerror.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_bookerror.setForeground(new java.awt.Color(204, 0, 0));

        jPanel7.setBackground(new java.awt.Color(0, 51, 255));

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(76, 76, 76))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_author, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_bookerror, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_book_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_book_name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbl_book_id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_book_name, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(76, 76, 76)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(lbl_author, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(lbl_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(lbl_bookerror, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(908, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 102, 0));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Male_100px.png"))); // NOI18N
        jLabel10.setText("Student Details");

        jPanel5.setBackground(new java.awt.Color(255, 102, 0));
        jPanel5.setForeground(new java.awt.Color(255, 102, 0));
        jPanel5.setPreferredSize(new java.awt.Dimension(376, 4));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 102, 0));
        jLabel12.setText("STUDENT ID :");

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 102, 0));
        jLabel14.setText("STUDENT NAME :");

        jLabel16.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 102, 0));
        jLabel16.setText("STUDENT SEM :");

        jLabel18.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 102, 0));
        jLabel18.setText("BRANCH :");

        lbl_student_name.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N

        lbl_student_sem.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N

        lbl_studentid.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N

        lbl_branch.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N

        lbl_studenterror.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studenterror.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_studenterror, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(jLabel18))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_branch, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_student_name, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_student_sem, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_studentid, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(lbl_studentid, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(lbl_student_name, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(84, 84, 84)
                        .addComponent(jLabel16))
                    .addComponent(lbl_student_sem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(lbl_branch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(lbl_studenterror, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel6.setText("Issue Book");

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setForeground(new java.awt.Color(255, 102, 0));
        jPanel6.setPreferredSize(new java.awt.Dimension(376, 4));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Book Id :");

        txt_issue_bookid.setBackground(new java.awt.Color(204, 204, 204));
        txt_issue_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_issue_bookid.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 13)); // NOI18N
        txt_issue_bookid.setPlaceholder("enter Book id");
        txt_issue_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_issue_bookidFocusLost(evt);
            }
        });
        txt_issue_bookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_issue_bookidActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Student Id :");

        txt_issue_studentid.setBackground(new java.awt.Color(204, 204, 204));
        txt_issue_studentid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_issue_studentid.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 13)); // NOI18N
        txt_issue_studentid.setPlaceholder("enter Student id");
        txt_issue_studentid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_issue_studentidFocusLost(evt);
            }
        });
        txt_issue_studentid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_issue_studentidActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Issue Date :");

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Due Date :");

        issue_book_button.setText("Issue Book");
        issue_book_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issue_book_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(130, 130, 130))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(27, 27, 27))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(58, 58, 58)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_issue_bookid, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_issue_studentid, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(issue_book_button, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_issue_bookid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_issue_studentid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(issue_book_button, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_issue_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_issue_bookidFocusLost

    }//GEN-LAST:event_txt_issue_bookidFocusLost

    private void txt_issue_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_issue_bookidActionPerformed
        if (!txt_issue_bookid.getText().equals("")) {
            getBookDetails(); 
        }
               // TODO add your handling code here:
    }//GEN-LAST:event_txt_issue_bookidActionPerformed

    private void txt_issue_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_issue_studentidFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_issue_studentidFocusLost

    private void txt_issue_studentidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_issue_studentidActionPerformed
        if (!txt_issue_studentid.getText().equals("")){
        getStudentDetails ();
        }// TODO add your handling code here:
    }//GEN-LAST:event_txt_issue_studentidActionPerformed

    private void issue_book_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issue_book_buttonActionPerformed
        if (lbl_quantity.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "book is not available");
        } else {

            if (isAlreadyIssued() == false) {

                if (issueBook() == true) {
                    JOptionPane.showMessageDialog(this, "book issued successfully");
                    updateBookCount();
                } else {
                    JOptionPane.showMessageDialog(this, "can't issue the book");
                }

            } else {
                JOptionPane.showMessageDialog(this, "this student already has this book");
            }

        }// TODO add your handling code here:
    }//GEN-LAST:event_issue_book_buttonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       new homepage().setVisible(true); // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(issuebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(issuebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(issuebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(issuebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new issuebook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonCircle issue_book_button;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_book_id;
    private javax.swing.JLabel lbl_book_name;
    private javax.swing.JLabel lbl_bookerror;
    private javax.swing.JLabel lbl_branch;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_student_name;
    private javax.swing.JLabel lbl_student_sem;
    private javax.swing.JLabel lbl_studenterror;
    private javax.swing.JLabel lbl_studentid;
    private app.bolivia.swing.JCTextField txt_issue_bookid;
    private app.bolivia.swing.JCTextField txt_issue_studentid;
    // End of variables declaration//GEN-END:variables
}
