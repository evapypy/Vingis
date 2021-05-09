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
import java.util.Enumeration;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author TwoTap
 */
public class Matrica extends javax.swing.JDialog {

    /**
     * Creates new form Matrica
     */
    public Matrica(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
           setTableInfo();
        
        
    
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    int[] sokID;
    int[] sokioID;
    public static void setTableInfo()
     {
    try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select distinct d.DalyvioID, d.Vardas,d.Pavarde from Dalyvis as d, Dalyvauja as da, Sokis as s, Vyksta as v, Koncertas as k "+
        "where k.KoncertoID=v.KoncertoID and v.SokioID=s.SokioID and "
        + "da.SokioID=s.SokioID and d.DalyvioID=da.DalyvioID and da.Dalyvavimo_Data='"+Status.Data+"' and k.Data='"+Status.Data+"' and d.Lytis='V' ";
ResultSet rs = st.executeQuery(sqlrequest);
DefaultTableModel tblModel = (DefaultTableModel)jTable1.getModel();
tblModel.setRowCount(0);
tblModel.setColumnCount(0);
tblModel.addColumn("Šokėjas");

while (rs.next()){
   
    String vardas = rs.getString("Vardas");
    String pavarde = rs.getString("Pavarde");

    
String tbData[] = {vardas+" "+pavarde};

tblModel.addRow(tbData);

}
sqlrequest = "select distinct d.DalyvioID, d.Vardas,d.Pavarde from Dalyvis as d, Dalyvauja as da, Sokis as s, Vyksta as v, Koncertas as k "+
        "where k.KoncertoID=v.KoncertoID and v.SokioID=s.SokioID and "
        + "da.SokioID=s.SokioID and d.DalyvioID=da.DalyvioID and da.Dalyvavimo_Data='"+Status.Data+"' and k.Data='"+Status.Data+"' and d.Lytis='M' ";
 rs = st.executeQuery(sqlrequest);
while (rs.next()){
   
    String vardas = rs.getString("Vardas");
    String pavarde = rs.getString("Pavarde");

    
String tbData[] = {vardas+" "+pavarde};

tblModel.addRow(tbData);
}



 sqlrequest = "select distinct s.SokioID, s.Sokio_Pavadinimas from Dalyvis as d, Dalyvauja as da, Sokis as s, Vyksta as v, Koncertas as k "+
        "where k.KoncertoID=v.KoncertoID and v.SokioID=s.SokioID and da.SokioID=s.SokioID and d.DalyvioID=da.DalyvioID  and k.Data='"+Status.Data+"' order by v.PasirodymoNr asc";

 rs = st.executeQuery(sqlrequest);

while (rs.next()){

    String vardas = rs.getString("Sokio_Pavadinimas");
   
  
    
tblModel.addColumn(vardas);



}


for(int i=0;i<jTable1.getModel().getRowCount();i++)
{
    for(int j=1;j<jTable1.getModel().getColumnCount();j++){

String pavadinimas =jTable1.getModel().getColumnName(j);
String zmogus=jTable1.getValueAt(i, 0).toString();
String zvardas=zmogus.substring(0, zmogus.indexOf(" "));
String zpavarde=zmogus.substring(zmogus.indexOf(" ")+1,zmogus.length() );


Matrica.sokioPavToID(pavadinimas);

Matrica.vardasPavToDalyvioID(zvardas,zpavarde);

sqlrequest = "select * from Dalyvauja where SokioID='"+SokioID+"' and DalyvioID='"+DalyvioID+"' and Dalyvavimo_Data='"+Status.Data+"'";
rs = st.executeQuery(sqlrequest);

while (rs.next()){
jTable1.setValueAt("X", i, j);

}



}
}

for(int i=0;i<jTable1.getModel().getRowCount();i++)
{
    for(int j=1;j<jTable1.getModel().getColumnCount();j++){
if(jTable1.getValueAt(i, j) == null){jTable1.setValueAt("", i, j);}
    }}


//sqlrequest = "select * from Dalyvauja where SokioID='"+sokID[i]+"' and DalyvioID='"+sokioID[j]+"' and Dalyvavimo_Data='"+Status.Data+"'";
//rs = st.executeQuery(sqlrequest);
//while (rs.next()){
//    jTable1.getModel().setValueAt("X", i, j);

}
catch(Exception e){System.out.println(e.getMessage()); 
}}
    
    public static int SokioID;
     
     public static void sokioPavToID(String pav){
         try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select SokioID from Sokis where Sokio_Pavadinimas='"+pav+"'";
ResultSet rs = st.executeQuery(sqlrequest);


while (rs.next()){
   int sokioid= rs.getInt("SokioID");
SokioID=sokioid;



}}
catch(Exception e){System.out.println(e.getMessage()); 
}
     }
     
   public static  int DalyvioID;
    public static  void vardasPavToDalyvioID(String vardas,String pav){
         try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select DalyvioID from Dalyvis where Vardas='"+vardas+"' and Pavarde = '"+pav+"'";
ResultSet rs = st.executeQuery(sqlrequest);


while (rs.next()){
   int dalyvioid= rs.getInt("DalyvioID");
DalyvioID=dalyvioid;



}}
catch(Exception e){System.out.println(e.getMessage()); 
}
     }
     
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 524));
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(51, 51, 255));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Matrica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Matrica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Matrica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Matrica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Matrica dialog = new Matrica(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
