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

/**
 *
 * @author TwoTap
 */
public class Pasiekimai extends javax.swing.JDialog {

    /**
     * Creates new form Pasiekimai
     */
    public Pasiekimai(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLabels();
        setVardasPavarde();
        setMegstamiausias();
        setKoncertaiTable();
        if (Status.Option.equals("Sokejai")){setSokiaiTable();}
        else if (Status.Option.equals("Muzikantai")){findIds();muzikantaiInfo();}
        else if (Status.Option.equals("Dainininkai")){findIds();muzikantaiInfo();}
        
       
        
        
    }
    
    int[] ids=new int[500];
    
    void muzikantaiInfo(){
         try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String Duplicate="no";
    
    for(int j=0;j<ids.length;j++){
String sqlrequest = "select distinct s.Sokio_Pavadinimas from Dalyvis as d, Dalyvauja as da, Sokis as s, Vyksta as v, Koncertas as K where d.DalyvioID=da.DalyvioID and da.SokioID=s.SokioID and s.SokioID=v.SokioID and k.KoncertoID=v.KoncertoID and k.koncertoID='"+ids[j]+"' order by s.Sokio_Pavadinimas asc ";

ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    
    String pav = rs.getString("Sokio_Pavadinimas");
 
    
String tbData[] = {pav};
DefaultTableModel tblModel = (DefaultTableModel)jTable1.getModel();

for(int f=0;f<jTable1.getModel().getRowCount();f++){
    if(jTable1.getModel().getValueAt(f, 0).toString().equals(pav)){
        Duplicate="yes";
    }
}
if(Duplicate.equals("no")){tblModel.addRow(tbData);}
else if(Duplicate.equals("yes")){Duplicate="no";};

}
}}
catch(Exception e){System.out.println(e.getMessage()); 
}
    }
        
        
    
    
    
    
    
    
    
    void findIds(){
        try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

    
    
String sqlrequest = "select distinct k.koncertoID from Dalyvis as d, Dalyvauja as da, Sokis as s, Vyksta as v, Koncertas as K where d.DalyvioID=da.DalyvioID and da.SokioID=s.SokioID and s.SokioID=v.SokioID and k.KoncertoID=v.KoncertoID and d.DalyvioID='"+Status.DalyvioID+"'  ";

ResultSet rs = st.executeQuery(sqlrequest);
int i=0;
while (rs.next()){
    
    int id = rs.getInt("koncertoID");
    System.out.println(id);
   ids[i]=id;
   i++;
    

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}
    }
    
    
void setLabels(){
    if(Status.Option.equals("Muzikantai")){
    jLabel6.setText("Kūriniai kuriuos grojo");}
    if(Status.Option.equals("Dainininkai")){
    jLabel6.setText("Kūriniai kuriuos dainavo");}
}

    public void setSokiaiTable(){
try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

    
    
String sqlrequest = "select distinct s.sokio_pavadinimas from Dalyvis as d, Dalyvauja as da, Sokis as s, Vyksta as v, Koncertas as K where d.DalyvioID=da.DalyvioID and da.SokioID=s.SokioID and s.SokioID=v.SokioID and k.KoncertoID=v.KoncertoID and d.DalyvioID='"+Status.DalyvioID+"'  order by s.Sokio_Pavadinimas asc";

ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String pavad = rs.getString("Sokio_Pavadinimas");
   
 
    
String tbData[] = {pavad};
DefaultTableModel tblModel = (DefaultTableModel)jTable1.getModel();

tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}
    
    
    
    
    
    
    
    void setVardasPavarde(){
        try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

    
String sqlrequest = "select vardas,pavarde from Dalyvis where dalyvioID='"+Status.DalyvioID+"'";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String vardas = rs.getString("vardas");
    String pavarde = rs.getString("pavarde");

jTextField1.setText(vardas+" "+pavarde);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}   
    }
     void setMegstamiausias(){
        try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

    
String sqlrequest = "select  s.Sokio_Pavadinimas ,count(*) from Dalyvis as d, Dalyvauja as da, Sokis as s, Vyksta as v, Koncertas as K where d.DalyvioID=da.DalyvioID and da.SokioID=s.SokioID and s.SokioID=v.SokioID and k.KoncertoID=v.KoncertoID and d.DalyvioID='"+Status.DalyvioID+"' group by s.Sokio_Pavadinimas order by count(*) desc limit 1";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String pavadinimas = rs.getString("Sokio_Pavadinimas");


jTextField2.setText(pavadinimas);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}   
    }
    
    
    public void setKoncertaiTable(){
try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

    
    
String sqlrequest = "select distinct k.Pavadinimas, k.Data from Dalyvis as d, Dalyvauja as da, Sokis as s, Vyksta as v, Koncertas as K where d.DalyvioID=da.DalyvioID and da.SokioID=s.SokioID and s.SokioID=v.SokioID and k.KoncertoID=v.KoncertoID and d.DalyvioID='"+Status.DalyvioID+"' order by k.Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String pavad = rs.getString("Pavadinimas");
    String data = rs.getString("Data");
 
    
String tbData[] = {pavad,data};
DefaultTableModel tblModel = (DefaultTableModel)jTable2.getModel();

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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Šokėjas:");

        jTextField1.setText("Vardas, pav");
        jTextField1.setFocusable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Šokio pavadinimas"
            }
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Koncerto pavadinimas", "Koncerto data"
            }
        ));
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable2);

        jLabel4.setText("Mėgstamiausias kūrinys:");

        jPanel26.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTextField2.setFocusable(false);

        jPanel6.setBackground(new java.awt.Color(51, 51, 255));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Koncertai kuriuose dalyvavo");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5))
        );

        jPanel7.setBackground(new java.awt.Color(51, 51, 255));

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Šokiai kuriuos šoko");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(59, Short.MAX_VALUE))
            .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(Pasiekimai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pasiekimai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pasiekimai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pasiekimai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Pasiekimai dialog = new Pasiekimai(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
