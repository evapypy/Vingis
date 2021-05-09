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
public class pridetiPapDalyvius extends javax.swing.JDialog {

    /**
     * Creates new form pridetiPapDalyvius
     */
    public pridetiPapDalyvius(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setMuzikantaiTableInfo();
        setDainininkaiTableInfo();
        setKMuzikantaiTableInfo();
        setKDainininkaiTableInfo();

        
        
    }
    int row;
String dal="";
String Pavadinimas="";
String DalyvioID="";
String dalyvioNr="";
String dalyvioVardas="";
String dalyvioPavarde="";

    void findDalyvioID(String dal){
        
        if (dal.equals("muz")){ row = jTable2.getSelectedRow();}
        else if (dal.equals("dai")){ row = jTable1.getSelectedRow();}
        else if (dal.equals("muz1")){ row = jTable3.getSelectedRow();}
        else if (dal.equals("dai1")){ row = jTable4.getSelectedRow();}
       
        if (dal.equals("muz")){     Pavadinimas = (jTable2.getModel().getValueAt(row, 0).toString());}
           else if (dal.equals("dai")){   Pavadinimas = (jTable1.getModel().getValueAt(row, 0).toString());}
        else if (dal.equals("muz1")){   Pavadinimas = (jTable3.getModel().getValueAt(row, 0).toString());}
        else if (dal.equals("dai1")){   Pavadinimas = (jTable4.getModel().getValueAt(row, 0).toString());}
            

            
            int tarpas = Pavadinimas.indexOf(" ");
            int dvitaskis=Pavadinimas.indexOf(":");
            int visasIlgis=Pavadinimas.length();


           dalyvioNr =Pavadinimas.substring(0,dvitaskis);
           dalyvioVardas=Pavadinimas.substring(dvitaskis+1, tarpas);
           dalyvioPavarde=Pavadinimas.substring(tarpas+1, visasIlgis);
           
         
           
           
         try{
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  DalyvioID from Dalyvis where  Metai='"+dalyvioNr+"' and Vardas='"+dalyvioVardas+"'and Pavarde='"+dalyvioPavarde+"'";
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String id = rs.getString("DalyvioID");

    
DalyvioID=id;
}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}
    
    


    void  insertInfo(){
    try{
            
       
         String sqlrequest="insert into dalyvauja(SokioID,DalyvioID,Dalyvavimo_Data) values ('"+SokioID+"' , '"+DalyvioID+"' , '"+Status.Data+"')"  ;  
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

      


 st.executeUpdate(sqlrequest);




    





}
catch(Exception e){System.out.println(e.getMessage()); 
}}
    
    
    
    
    
    
    
    
    
    
    
    
    
    String SokioID="";
    
   void  findVisusSokius(){
    try{
            
       
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);

Statement st = con.createStatement();

String sqlrequest = "select  SokioID from Vyksta where  KoncertoID='"+KoncertoID+"'   "   ;       


ResultSet rs = st.executeQuery(sqlrequest);



while (rs.next()){
    String id = rs.getString("SokioID");
    SokioID=id;
    System.out.println("sokis="+SokioID);
 


    




}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    String KoncertoID = "";
    
    void findKoncertoID(){
       try{
            
       
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  KoncertoID from Koncertas where  Pavadinimas='"+Status.Pavadinimas+"'  and Data='"+Status.Data+"'  "   ;                 
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);


while (rs.next()){
    String id = rs.getString("KoncertoID");
KoncertoID=id;


}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}
    
    
    
    
    void setDainininkaiTable(){
        
       
 jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Metai:Vardas Pavardė"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
     }
     
        
    void setDainininkaiTableInfo(){
        try{
            
        setDainininkaiTable();
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  Metai, Vardas,Pavarde from Dalyvis where  Zanras='D'  and Aktyvumas=1  order by metai desc, vardas asc, pavarde asc"   ;                 
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);
DefaultTableModel tblModel = (DefaultTableModel)jTable1.getModel();
tblModel.setRowCount(0);
while (rs.next()){
    String metai = rs.getString("Metai");
    String vardas = rs.getString("Vardas");
    String pavarde = rs.getString("Pavarde");
    
    
String tbData[] = {metai+":"+vardas+" "+pavarde};


tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}
    }
    
    
     void setMuzikantaiTable(){
        
       
 jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Metai:Vardas Pavardė"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
     }
     
        
     
      void setKMuzikantaiTable(){
        
       
 jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Metai:Vardas Pavardė"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
     }
     void setKDainininkaiTable(){
        
       
 jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Metai:Vardas Pavardė"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
     }
     
    void setMuzikantaiTableInfo(){
        try{
            
        setMuzikantaiTable();
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  Metai, Vardas,Pavarde from Dalyvis where  Zanras='M'  and Aktyvumas=1  order by metai desc, vardas asc, pavarde asc"   ;                 
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);
DefaultTableModel tblModel = (DefaultTableModel)jTable2.getModel();
tblModel.setRowCount(0);
while (rs.next()){
    String metai = rs.getString("Metai");
    String vardas = rs.getString("Vardas");
    String pavarde = rs.getString("Pavarde");
    
    
String tbData[] = {metai+":"+vardas+" "+pavarde};


tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}
    }
    
    
    void setKMuzikantaiTableInfo(){
        try{
            
        setKMuzikantaiTable();
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

            System.out.println("good");
String sqlrequest = "select  distinct d.Metai, d.Vardas, d.Pavarde from Dalyvis as d, Dalyvauja as da, Sokis as s, Vyksta as v, Koncertas as k "
+" where d.DalyvioID=da.DalyvioID and da.SokioID=s.SokioID and s.SokioID=v.SokioID and k.KoncertoID=v.KoncertoID "
+" and d.Zanras='M' and da.Dalyvavimo_Data=k.Data and da.Dalyvavimo_Data='"+Status.Data+"'";





ResultSet rs = st.executeQuery(sqlrequest);
DefaultTableModel tblModel = (DefaultTableModel)jTable3.getModel();
tblModel.setRowCount(0);
while (rs.next()){
    
    String metai = rs.getString("Metai");
    String vardas = rs.getString("Vardas");
    String pavarde = rs.getString("Pavarde");
    
    
String tbData[] = {metai+":"+vardas+" "+pavarde};

//Delete senus vardus_pav
String pilnasVardas= vardas+" "+pavarde;
for (int i=0;i<jTable2.getRowCount();i++){
      
            
     String a=jTable2.getModel().getValueAt(i, 0).toString();
     if(a.contains(pilnasVardas)){
         DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
         model.removeRow(i);
     }
}





tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}
    }
    
    
    
    void setKDainininkaiTableInfo(){
        try{
            
        setKDainininkaiTable();
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

            System.out.println("good");
String sqlrequest = "select  distinct d.Metai, d.Vardas, d.Pavarde from Dalyvis as d, Dalyvauja as da, Sokis as s, Vyksta as v, Koncertas as k "
+" where d.DalyvioID=da.DalyvioID and da.SokioID=s.SokioID and s.SokioID=v.SokioID and k.KoncertoID=v.KoncertoID "
+" and d.Zanras='D' and da.Dalyvavimo_Data=k.Data and da.Dalyvavimo_Data='"+Status.Data+"'";





ResultSet rs = st.executeQuery(sqlrequest);
DefaultTableModel tblModel = (DefaultTableModel)jTable4.getModel();
tblModel.setRowCount(0);
while (rs.next()){
    
    String metai = rs.getString("Metai");
    String vardas = rs.getString("Vardas");
    String pavarde = rs.getString("Pavarde");
    
    
String tbData[] = {metai+":"+vardas+" "+pavarde};

//Delete senus vardus_pav
String pilnasVardas= vardas+" "+pavarde;
for (int i=0;i<jTable1.getRowCount();i++){
      
            
     String a=jTable1.getModel().getValueAt(i, 0).toString();
     if(a.contains(pilnasVardas)){
         DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
         model.removeRow(i);
     }
}





tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}
    }
    
    
 void deleteMuzikantasFromKoncertas(){
       
        
            int row = jTable3.getSelectedRow();
            
            String Pavadinimas = (jTable3.getModel().getValueAt(row, 0).toString());
            
    
            
             int tarpas = Pavadinimas.indexOf(" ");
            int dvitaskis=Pavadinimas.indexOf(":");
            int visasIlgis=Pavadinimas.length();
           
         dalyvioNr =Pavadinimas.substring(0,dvitaskis);
           dalyvioVardas=Pavadinimas.substring(dvitaskis+1, tarpas);
           dalyvioPavarde=Pavadinimas.substring(tarpas+1, visasIlgis);

            
            findDalyvioID(dal);
     
          
        
           
           
          try { 
                String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

String sqlrequest = "Delete from Dalyvauja where DalyvioID='"+DalyvioID+"'  and Dalyvavimo_Data='"+Status.Data+"' ";
st.executeUpdate(sqlrequest);



            }catch(Exception e){System.out.println(e.getMessage()); 
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 520));
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable2FocusGained(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jTable3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable3FocusGained(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jTable4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable4FocusGained(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel3.setBackground(new java.awt.Color(51, 51, 255));
        jPanel3.setForeground(new java.awt.Color(51, 51, 255));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Visi muzikantai");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 255));
        jPanel4.setForeground(new java.awt.Color(51, 51, 255));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Koncerto muzikantai");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );

        jPanel8.setBackground(new java.awt.Color(51, 51, 255));
        jPanel8.setForeground(new java.awt.Color(51, 51, 255));

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Koncerto dainininkai");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8))
        );

        jPanel9.setBackground(new java.awt.Color(51, 51, 255));
        jPanel9.setForeground(new java.awt.Color(51, 51, 255));

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Visi dainininkai");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel9))
        );

        jButton1.setText(">>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Pašalinti");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("<<");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Pašalinti");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(111, 111, 111))
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jButton1)
                .addGap(162, 162, 162)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(155, 155, 155)
                .addComponent(jButton3)
                .addGap(176, 176, 176))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dal="muz";
                findKoncertoID();
        findDalyvioID(dal);
        System.out.println("koncid="+KoncertoID);
        System.out.println("DALid="+DalyvioID);
        findVisusSokius();
        insertInfo();
                setKMuzikantaiTableInfo();
        setKDainininkaiTableInfo();
               
       //
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        dal="dai";
                  findKoncertoID();
        findDalyvioID(dal);
        System.out.println("koncid="+KoncertoID);
        System.out.println("DALid="+DalyvioID);
        findVisusSokius();
        insertInfo();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dal="muz1";
        deleteMuzikantasFromKoncertas();
        setMuzikantaiTableInfo();
         setKMuzikantaiTableInfo();
     
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        dal="dai1";
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable2FocusGained
        // TODO add your handling code here:
              jTable1.clearSelection();
        jTable3.clearSelection();
        jTable4.clearSelection();
    }//GEN-LAST:event_jTable2FocusGained

    private void jTable3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable3FocusGained
        // TODO add your handling code here:
          jTable1.clearSelection();
        jTable2.clearSelection();
        jTable4.clearSelection();
    }//GEN-LAST:event_jTable3FocusGained

    private void jTable4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable4FocusGained
        // TODO add your handling code here:
          jTable1.clearSelection();
        jTable2.clearSelection();
        jTable3.clearSelection();
    }//GEN-LAST:event_jTable4FocusGained

    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
        // TODO add your handling code here:
          jTable2.clearSelection();
        jTable3.clearSelection();
        jTable4.clearSelection();
    }//GEN-LAST:event_jTable1FocusGained

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
            java.util.logging.Logger.getLogger(pridetiPapDalyvius.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pridetiPapDalyvius.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pridetiPapDalyvius.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pridetiPapDalyvius.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                pridetiPapDalyvius dialog = new pridetiPapDalyvius(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    // End of variables declaration//GEN-END:variables
}
