package atmproject;

import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Cashdeposit extends javax.swing.JFrame {

    int user2,pass2;
    public Cashdeposit(int u,int p) {
        initComponents();
        user2=u;
        pass2=p;
        System.out.println(user2+pass2);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jLabel1 = new javax.swing.JLabel();
        cid = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jButton1, org.jdesktop.beansbinding.ELProperty.create("cash_deposit"), this, org.jdesktop.beansbinding.BeanProperty.create("title"));
        bindingGroup.addBinding(binding);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(164, 72, 72));
        jLabel1.setText("ENTER THE AMOUNT TO DEPOSIT");

        cid.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        cid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cidActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(102, 204, 0));
        jButton1.setText("DEPOSIT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 0, 0));
        jButton2.setText("BACK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jButton1)
                        .addGap(172, 172, 172)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(cid, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jLabel1)))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(cid, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(111, 111, 111))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String depo=cid.getText();
        int casd=Integer.parseInt(depo);
        Connection con=null;
        PreparedStatement pst1=null;
        PreparedStatement pst2=null;
        Statement  st=null;
        ResultSet rs1=null;
        ResultSet rs2=null;
        float newbalance=0;
        float newbalance1 = 0;
        if(casd<20000)
        {
           try
           { 
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kits_w","root","");            
            String query="select balance from atm where user=? and pin=?";
            st=con.createStatement();
            pst1=con.prepareStatement(query);
            pst1.setInt(1, user2);
            pst1.setInt(2, pass2);
            System.out.println(user2+" "+pass2);
            rs1=pst1.executeQuery();
            while(rs1.next())
            {
                 newbalance=rs1.getFloat(1);
                 newbalance=(float)newbalance+casd;
            }
              System.out.println("newbalance is"+" "+newbalance);
              System.out.println("user is"+user2);
              String query1="update atm set balance="+newbalance+" where user="+user2;
               int n=st.executeUpdate(query1);
                if(n==1)
                {
                   try{
                   String query3="select balance from atm where user=? and pin=?";
                         st=con.createStatement();
                         pst2=con.prepareStatement(query3);
                         pst2.setInt(1, user2);
                         pst2.setInt(2, pass2);
                         System.out.println(user2+" "+pass2);
                         rs2=pst2.executeQuery();
                         while(rs2.next())
                           {
                                newbalance1=rs2.getFloat(1);
                            }
                
                         JOptionPane.showMessageDialog(null,"updated balance is"+newbalance1);
                }
                   catch(Exception e)
                   {
                       JOptionPane.showMessageDialog(null, e);
                   }
                }
        }
        catch(ClassNotFoundException | SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
       }
        else
        {
            Pannumber obj=new Pannumber(user2,casd,pass2);
            obj.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Loginsuccess obj=new Loginsuccess(user2,pass2,"");
        obj.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cidActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.getContentPane().setBackground(Color.yellow);
    }//GEN-LAST:event_formWindowOpened
    public static void main(String args[])
    {
    java.awt.EventQueue.invokeLater(new Runnable()
     {
           public void run()
           {
             new Cashdeposit(' ',' ').setVisible(true);
           }
     }
    );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cid;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
