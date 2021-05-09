/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vingis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import static vingis.Pagrindinis.jTable1;

/**
 *
 * @author TwoTap
 */
public class skInsertField extends javax.swing.JDialog {

    /**
     * Creates new form skInsertField
     */
    public skInsertField(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        if(Status.Option.equals("Sokiai")){setSokejasUpdateFields();}
        else if (Status.Option.equals("Koncertai")){setKoncertaiUpdateFields();}
        fillFields();
        
        
        
    }

    void setKoncertaiTableInfo()
     {
    try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);
DefaultTableModel tblModel = (DefaultTableModel)Pagrindinis.jTable1.getModel();
tblModel.setRowCount(0);
while (rs.next()){
    String pavadinimas = rs.getString("Pavadinimas");
    String proga = rs.getString("Proga");
    String vieta = rs.getString("Vieta");
    String data = rs.getString("Data");
    
String tbData[] = {pavadinimas,proga,vieta,data};



tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}
         
         
         
}
    
    
    
    
   void  fillFields(){
       if(Status.Option.equals("Sokiai")){
       
       
       
       
       }
       
       
       
       
       else if (Status.Option.equals("Koncertai")){
       
       
       }
       
       
    }
    
    
    
    void setKoncertaiUpdateFields(){
        jTextField5.setVisible(false);
        jLabel5.setVisible(false);
        
         jLabel1.setText("Koncerto Pavadinimas*:");
        jLabel2.setText("Koncerto data*:");
        jLabel3.setText("Koncerto vieta:");
        jLabel4.setText ("Koncerto proga:");
       
        jLabel6.setText("Koncerto pridėjimas");
    }
    
    
    
    void setSokejasUpdateFields(){
       
         jLabel1.setText("Šokio pavadinimas*:");
        jLabel2.setText("Vaikinų kiekis*:");
        jLabel3.setText("Merginų kiekis*:");
        jLabel4.setText("Šokio trukmė (min)*:");
        jLabel5.setText("Sukūrimo metai:");
        jLabel6.setText("Šokio pridėjimas");
    }
    
    
    void addSokis()
    {
         String sokioPavadinimas = jTextField1.getText();
         String vaikinuKiekis = jTextField2.getText();
         String merginuKiekis = jTextField3.getText();
         String sokioTrukme = jTextField4.getText();
         String sokioSukurimoM = jTextField5.getText();
         
         try {
            String url = "jdbc:sqlite:vingisdb.db";
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            
            String sqlrequest = "INSERT INTO Sokis (Sokio_Pavadinimas,Vaikinu_Kiekis,Merginu_Kiekis,Sokio_Trukme,Sukurimo_Metai)VALUES ('"+ sokioPavadinimas+
                    "','" + vaikinuKiekis +"','" + merginuKiekis +"','" + sokioTrukme +"','" + sokioSukurimoM + "')";
            
            st.executeUpdate(sqlrequest);
            
            } catch (Exception e) {
            System.out.println(e.getMessage());            
        }
         this.dispose();
    }
    
    
    void addKoncertas()
    {
         String sokioPavadinimas = jTextField1.getText();
         String vaikinuKiekis = jTextField2.getText();
         String merginuKiekis = jTextField4.getText();
         String sokioTrukme = jTextField3.getText();
         
         
         try {
            String url = "jdbc:sqlite:vingisdb.db";
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            
            String sqlrequest = "INSERT INTO Koncertas (Pavadinimas,Data,Proga,Vieta)VALUES ('"+ sokioPavadinimas+
                    "','" + vaikinuKiekis +"','" + merginuKiekis +"','" + sokioTrukme + "')";
            
            st.executeUpdate(sqlrequest);
            
            } catch (Exception e) {
            System.out.println(e.getMessage());            
        }
         this.dispose();
    }
    
    
    public void setSokiaiTableInfo(){
try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select Sokio_Pavadinimas, Vaikinu_kiekis,Merginu_kiekis,Sokio_Trukme,Sukurimo_Metai from sokis order by sokio_Pavadinimas asc";
ResultSet rs = st.executeQuery(sqlrequest);




DefaultTableModel tblModel = (DefaultTableModel)Pagrindinis.jTable1.getModel();
tblModel.setRowCount(0);
while (rs.next()){
    String pavadinimas = rs.getString("Sokio_Pavadinimas");
    String vKiekis = String.valueOf(rs.getInt("Vaikinu_Kiekis"));
    String mKiekis = String.valueOf(rs.getInt("Merginu_Kiekis"));
    String sokioTrukme = String.valueOf(rs.getInt("Sokio_Trukme"));
    String sokioSuk =rs.getString("Sukurimo_Metai");
    
String tbData[] = {pavadinimas,vKiekis,mKiekis,sokioTrukme,sokioSuk};


tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel1");

        jLabel3.setText("jLabel1");

        jLabel4.setText("jLabel1");

        jLabel5.setText("jLabel1");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Pridejimas");

        jButton1.setText("Atšaukti");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Pridėti");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jButton1)
                                .addGap(60, 60, 60)
                                .addComponent(jButton2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jLabel6)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // add/update button
        //Status.Action
        
        if (Status.Option.equals("Sokiai")){
        if(Status.Action.equals("Add")){
           if(jTextField1.getText().equals("")){}else addSokis();setSokiaiTableInfo();
        }}
        if (Status.Option.equals("Koncertai")){
            if(Status.Action.equals("Add")){
            addKoncertas();setKoncertaiTableInfo(); 
            }
            
        }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        
        dispose();
     
    }//GEN-LAST:event_formWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
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
            java.util.logging.Logger.getLogger(skInsertField.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(skInsertField.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(skInsertField.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(skInsertField.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                skInsertField dialog = new skInsertField(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
