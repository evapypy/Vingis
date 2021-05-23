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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TwoTap
 */
public class insideKoncertas extends javax.swing.JDialog {

    /**
     * Creates new form insideKoncertas
     */
    public insideKoncertas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        findKoncertoID();
        setSokiaiTableInfo();
        
       
        
       
        
        
        
    }

   
    
    
    void setSokejaiTableInfo(){
        try{
            
        setSokejaiTable();
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
findSokioID();
String sqlrequest = "select * from dalyvauja as da, sokis as s, dalyvis as d where s.SokioID=da.SokioID and d.DalyvioID=da.DalyvioID and da.SokioID='"+SokioID+"' and da.Dalyvavimo_Data='"+Status.Data+"'  and d.Lytis='V' and d.Zanras='S' order by da.Poros_Nr asc"   ;                 
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String porNr = rs.getString("Poros_Nr");
    String vardas = rs.getString("Vardas");
    String pavarde = rs.getString("Pavarde");
    
    
String tbData[] = {porNr+":"+vardas+" "+pavarde};
DefaultTableModel tblModel = (DefaultTableModel)jTable5.getModel();

tblModel.addRow(tbData);


//Delete senus vardus_pav
String pilnasVardas= vardas+" "+pavarde;
for (int i=0;i<jTable3.getRowCount();i++){
      
            
     String a=jTable3.getModel().getValueAt(i, 0).toString();
     if(a.contains(pilnasVardas)){
         DefaultTableModel model = (DefaultTableModel)jTable3.getModel();
         model.removeRow(i);
     }
}


}





}
catch(Exception e){System.out.println(e.getMessage()); 
}
    }
    void setSokejosTableInfo(){
        try{
            
        setSokejosTable();
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
findSokioID();
String sqlrequest = "select * from dalyvauja as da, sokis as s, dalyvis as d where s.SokioID=da.SokioID and d.DalyvioID=da.DalyvioID and da.SokioID='"+SokioID+"' and da.Dalyvavimo_Data='"+Status.Data+"' and d.Lytis='M' and d.Zanras='S'  order by da.Poros_Nr asc"   ;                 
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String porNr = rs.getString("Poros_Nr");
    String vardas = rs.getString("Vardas");
    String pavarde = rs.getString("Pavarde");
    
    
String tbData[] = {porNr+":"+vardas+" "+pavarde};
DefaultTableModel tblModel = (DefaultTableModel)jTable4.getModel();

tblModel.addRow(tbData);

//Delete senus vardus_pav
String pilnasVardas= vardas+" "+pavarde;
for (int i=0;i<jTable2.getRowCount();i++){
      
            
     String a=jTable2.getModel().getValueAt(i, 0).toString();
     if(a.contains(pilnasVardas)){
         DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
         model.removeRow(i);
     }
}




}
}
catch(Exception e){System.out.println(e.getMessage()); 
}
    }
    
    void vyraiVisible(){
        
        int row = jTable1.getSelectedRow();
            
            String Pavadinimas = (jTable1.getModel().getValueAt(row, 0).toString());
            
            
            
            
            int vienasPo=Pavadinimas.indexOf("(")+1;
            int vienasPries=Pavadinimas.indexOf(")");
            int kablelis=Pavadinimas.indexOf(",");
         String vienas=Pavadinimas.substring(vienasPo, kablelis);
         int i=Integer.parseInt(vienas);
        
         
         
         setSokejaiTable();
         setSokejaiTableInfo();
         if(jTable5.getModel().getRowCount()==i){jButton1.setVisible(false);}
         if(jTable5.getModel().getRowCount()<i){jButton1.setVisible(true);}
        
        
    }
     void merginosVisible(){
        
        int row = jTable1.getSelectedRow();
            
            String Pavadinimas = (jTable1.getModel().getValueAt(row, 0).toString());
            
            
            
            
            int vienasPo=Pavadinimas.indexOf("(")+1;
            int vienasPries=Pavadinimas.indexOf(")");
            int kablelis=Pavadinimas.indexOf(",");
         String vienas=Pavadinimas.substring(kablelis+1, vienasPries);
         int i=Integer.parseInt(vienas);
      
         
         
         setSokejosTable();
         setSokejosTableInfo();
         if(jTable4.getModel().getRowCount()==i){jButton3.setVisible(false);}
         if(jTable4.getModel().getRowCount()<i){jButton3.setVisible(true);}
        
        
    }
    
    void setMerginosTableInfo(){
        try{
            
        setMerginosTable();
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  Metai, Vardas,Pavarde from Dalyvis where  Lytis='M' and Zanras='S'  and Aktyvumas=1  order by metai desc, vardas asc, pavarde asc"   ;                 
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String metai = rs.getString("Metai");
    String vardas = rs.getString("Vardas");
    String pavarde = rs.getString("Pavarde");
    
    
String tbData[] = {metai+":"+vardas+" "+pavarde};
DefaultTableModel tblModel = (DefaultTableModel)jTable2.getModel();

tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}
    }
    void setSokejaiTable(){
        
       
 jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PorosNr:Vardas Pavardė"
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
    void setSokejosTable(){
        
       
 jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PorosNr:Vardas Pavardė"
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
    
    
    void setVyraiTableInfo(){
        try{
            
        setVyraiTable();
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  Metai, Vardas,Pavarde from Dalyvis where  Lytis='V' and Zanras='S'  and Aktyvumas=1  order by metai desc, vardas asc, pavarde asc"   ;                 
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String metai = rs.getString("Metai");
    String vardas = rs.getString("Vardas");
    String pavarde = rs.getString("Pavarde");
    
    
String tbData[] = {metai+":"+vardas+" "+pavarde};
DefaultTableModel tblModel = (DefaultTableModel)jTable3.getModel();

tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}
    }
    
    
    
    void findKoncertoID(){
        try{
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  koncertoID from koncertas where koncertas.pavadinimas='"+Status.Pavadinimas+"' and data='"+Status.Data+"'";
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String id = rs.getString("koncertoID");

    
koncertoID=id;
}
}
catch(Exception e){System.out.println(e.getMessage()); 
}
    }
    
    String koncertoID = "";
    
    void setVyraiTable(){
        
       
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
    void setMerginosTable(){
        
       
 jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
    
    void switchSokejasNumbers(){
findDalyvioIDT5();
findSokioID();

        
             try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

String sqlrequest = 
        "select da.SokioID , da.DalyvioID , da.Dalyvavimo_Data , da.Poros_Nr "
        + "from Dalyvauja as da, Dalyvis as d  "
        + "where da.DalyvioID=d.DalyvioID and da.SokioID='"+SokioID+"' and da.Dalyvavimo_Data='"+Status.Data+"' and d.Lytis='V' order by da.Poros_Nr ASC";
ResultSet rs = st.executeQuery(sqlrequest);
int number = 1;

while (rs.next()){
    
    String sid = rs.getString("SokioID");
    String did = rs.getString("DalyvioID");
    String dat=rs.getString("Dalyvavimo_Data");
    
  
    
    
    Statement stat = con.createStatement();

String sql = "Update Dalyvauja set Poros_Nr='"+number+"' where SokioID='"+sid+"' and DalyvioID='"+did+"' and Dalyvavimo_Data='"+dat+"'";
stat.executeUpdate(sql);
number++;
   
   
    


}
}
catch(Exception e){System.out.println(e.getMessage()); 
}

        
    }
    void switchSokejosNumbers(){
findDalyvioIDT4();
findSokioID();

        
             try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

String sqlrequest = 
        "select da.SokioID , da.DalyvioID , da.Dalyvavimo_Data , da.Poros_Nr "
        + "from Dalyvauja as da, Dalyvis as d  "
        + "where da.DalyvioID=d.DalyvioID and da.SokioID='"+SokioID+"' and da.Dalyvavimo_Data='"+Status.Data+"' and d.Lytis='M' and d.Zanras='S' order by da.Poros_Nr ASC";
ResultSet rs = st.executeQuery(sqlrequest);
int number = 1;

while (rs.next()){
    
    String sid = rs.getString("SokioID");
    String did = rs.getString("DalyvioID");
    String dat=rs.getString("Dalyvavimo_Data");
    
  
    
    
    Statement stat = con.createStatement();

String sql = "Update Dalyvauja set Poros_Nr='"+number+"' where SokioID='"+sid+"' and DalyvioID='"+did+"' and Dalyvavimo_Data='"+dat+"'";
stat.executeUpdate(sql);
number++;
   
   
    


}
}
catch(Exception e){System.out.println(e.getMessage()); 
}

        
    }
    
    
    
    void setSokiaiTable(){
        
       
 jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Šokiai"
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
    
    void setSokiaiTableInfo()
     {
    try{
        setSokiaiTable();
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  Sokio_Pavadinimas, Vaikinu_Kiekis,Merginu_Kiekis,Sokio_Trukme from Sokis as s, vyksta as v, koncertas as k where k.koncertoID = v.koncertoID and s.sokioID = v.SokioID and k.koncertoID="+koncertoID + " order by PasirodymoNr ASC";                          
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);
Status.bendrasLaikas=0;
while (rs.next()){
    String pavadinimas = rs.getString("Sokio_Pavadinimas");
    String mer = rs.getString("Vaikinu_Kiekis");
    String vai = rs.getString("Merginu_Kiekis");
    int laikas=rs.getInt("Sokio_Trukme");
Status.bendrasLaikas=Status.bendrasLaikas+laikas+1;
    
String tbData[] = {pavadinimas+" ("+mer+","+vai+")"};
DefaultTableModel tblModel = (DefaultTableModel)jTable1.getModel();

tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}
       
}
    
    void findSokioID(){
        int row = jTable1.getSelectedRow();
            
            String Pavadinimas = (jTable1.getModel().getValueAt(row, 0).toString());
            
            
            
            int tarpas = Pavadinimas.indexOf("(")-1;
            int vienasPo=Pavadinimas.indexOf("(");
            int vienasPries=Pavadinimas.indexOf(")");
            int kablelis=Pavadinimas.indexOf(",");
           NaujasPav =Pavadinimas.substring(0,tarpas);
            
           VaikinuNr=Pavadinimas.substring(vienasPo+1, kablelis);
           MerginuNr=Pavadinimas.substring(kablelis+1, vienasPries);
        
        
        try{
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  SokioID from Sokis where Sokio_Pavadinimas='"+NaujasPav+"' and Vaikinu_Kiekis='"+VaikinuNr+"'and Merginu_Kiekis='"+MerginuNr+"'";
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String id = rs.getString("SokioID");

    
SokioID=id;
}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}
    
    String DalyvioID = "";
    String dalyvioMetai="";
    String dalyvioVardas="";
    String dalyvioPavarde="";
    
    
    void findDalyvioIDT5(){
       int row = jTable5.getSelectedRow();
            
            String Pavadinimas = (jTable5.getModel().getValueAt(row, 0).toString());
            

            
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
String sqlrequest = "select  d.DalyvioID from Dalyvis as d, Dalyvauja as da where d.DalyvioID=da.DalyvioID and da.Poros_Nr='"+dalyvioNr+"' and Vardas='"+dalyvioVardas+"'and Pavarde='"+dalyvioPavarde+"' and da.SokioID='"+SokioID+"'";
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String id = rs.getString("DalyvioID");

    
DalyvioID=id;
}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}
    void findDalyvioIDT4(){
       int row = jTable4.getSelectedRow();
            
            String Pavadinimas = (jTable4.getModel().getValueAt(row, 0).toString());
            

            
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
String sqlrequest = "select  d.DalyvioID from Dalyvis as d, Dalyvauja as da where d.DalyvioID=da.DalyvioID and da.Poros_Nr='"+dalyvioNr+"' and Vardas='"+dalyvioVardas+"'and Pavarde='"+dalyvioPavarde+"' and da.SokioID='"+SokioID+"'";
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String id = rs.getString("DalyvioID");

    
DalyvioID=id;
}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}
    
    void findDalyvioIDT2(){
       int row = jTable2.getSelectedRow();
            
            String Pavadinimas = (jTable2.getModel().getValueAt(row, 0).toString());
            

            
            int tarpas = Pavadinimas.indexOf(" ");
            int dvitaskis=Pavadinimas.indexOf(":");
            int visasIlgis=Pavadinimas.length();


           dalyvioMetai =Pavadinimas.substring(0,dvitaskis);
           dalyvioVardas=Pavadinimas.substring(dvitaskis+1, tarpas);
           dalyvioPavarde=Pavadinimas.substring(tarpas+1, visasIlgis);
           
         
           
           
         try{
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  DalyvioID from Dalyvis where Metai="+dalyvioMetai+" and Vardas='"+dalyvioVardas+"'and Pavarde='"+dalyvioPavarde+"'";
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String id = rs.getString("DalyvioID");

    
DalyvioID=id;
}
}
catch(Exception e){System.out.println(e.getMessage()); 
}

        
    }
    
    
   
   void findDalyvioID(){
       int row = jTable3.getSelectedRow();
            
            String Pavadinimas = (jTable3.getModel().getValueAt(row, 0).toString());
            

            
            int tarpas = Pavadinimas.indexOf(" ");
            int dvitaskis=Pavadinimas.indexOf(":");
            int visasIlgis=Pavadinimas.length();


           dalyvioMetai =Pavadinimas.substring(0,dvitaskis);
           dalyvioVardas=Pavadinimas.substring(dvitaskis+1, tarpas);
           dalyvioPavarde=Pavadinimas.substring(tarpas+1, visasIlgis);
           
         
           
           
         try{
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  DalyvioID from Dalyvis where Metai="+dalyvioMetai+" and Vardas='"+dalyvioVardas+"'and Pavarde='"+dalyvioPavarde+"'";
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String id = rs.getString("DalyvioID");

    
DalyvioID=id;
}
}
catch(Exception e){System.out.println(e.getMessage()); 
}

        
    }
    String dalyvioNr="";
    
    void deleteDalyveFromKoncertas(){
       
        
            int row = jTable4.getSelectedRow();
            
            String Pavadinimas = (jTable4.getModel().getValueAt(row, 0).toString());
            
    
            
             int tarpas = Pavadinimas.indexOf(" ");
            int dvitaskis=Pavadinimas.indexOf(":");
            int visasIlgis=Pavadinimas.length();
           
         dalyvioNr =Pavadinimas.substring(0,dvitaskis);
           dalyvioVardas=Pavadinimas.substring(dvitaskis+1, tarpas);
           dalyvioPavarde=Pavadinimas.substring(tarpas+1, visasIlgis);

            
            findDalyvioIDT4();
     
            findSokioID();
        
           
           
          try { 
                String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

String sqlrequest = "Delete from Dalyvauja where DalyvioID='"+DalyvioID+"' and SokioID='"+SokioID+"' and Dalyvavimo_Data='"+Status.Data+"' ";
st.executeUpdate(sqlrequest);



            }catch(Exception e){System.out.println(e.getMessage()); 
}
       
       
       
        
    }
    
    
    
    
    
    
    
    void deleteDalyvisFromKoncertas(){
       
        
            int row = jTable5.getSelectedRow();
            
            String Pavadinimas = (jTable5.getModel().getValueAt(row, 0).toString());
            
    
            
             int tarpas = Pavadinimas.indexOf(" ");
            int dvitaskis=Pavadinimas.indexOf(":");
            int visasIlgis=Pavadinimas.length();
           
         dalyvioNr =Pavadinimas.substring(0,dvitaskis);
           dalyvioVardas=Pavadinimas.substring(dvitaskis+1, tarpas);
           dalyvioPavarde=Pavadinimas.substring(tarpas+1, visasIlgis);

            
            findDalyvioIDT5();
     
            findSokioID();
        
           
           
          try { 
                String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

String sqlrequest = "Delete from Dalyvauja where DalyvioID='"+DalyvioID+"' and SokioID='"+SokioID+"' and Dalyvavimo_Data='"+Status.Data+"' ";
st.executeUpdate(sqlrequest);



            }catch(Exception e){System.out.println(e.getMessage()); 
}
       
       
       
        
    }
    
    
    
    
    
    
    String SokioID = "";
    String NaujasPav = "";
    String VaikinuNr = "";
    String MerginuNr = "";
    
   void deleteSokisFromKoncertas(){
       
        
            int row = jTable1.getSelectedRow();
            
            String Pavadinimas = (jTable1.getModel().getValueAt(row, 0).toString());
            
            
            
            int tarpas = Pavadinimas.indexOf("(")-1;
            int vienasPo=Pavadinimas.indexOf("(");
            int vienasPries=Pavadinimas.indexOf(")");
            int kablelis=Pavadinimas.indexOf(",");
           NaujasPav =Pavadinimas.substring(0,tarpas);
            
           VaikinuNr=Pavadinimas.substring(vienasPo+1, kablelis);
           MerginuNr=Pavadinimas.substring(kablelis+1, vienasPries);
           
            
            
            
            findKoncertoID();
          
            findSokioID();
         
           
           
          try { 
                String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

String sqlrequest = "Delete from Vyksta where KoncertoId='"+koncertoID+"' and SokioID='"+SokioID+"'";
st.executeUpdate(sqlrequest);

JOptionPane.showMessageDialog(null, "Šokis pašalintas!");

            }catch(Exception e){System.out.println(e.getMessage()); 
}
       
       
       
        
    }
    
    
    void setNewNumbers(){
        
        
            
             try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select KoncertoID,SokioID,PasirodymoNr from Vyksta where KoncertoID='"+koncertoID+"' order by PasirodymoNr ASC";
ResultSet rs = st.executeQuery(sqlrequest);
int number = 0;
while (rs.next()){
    
    String kid = rs.getString("KoncertoID");
    String sid = rs.getString("SokioID");
    
    
    Statement stat = con.createStatement();

String sql = "Update Vyksta set PasirodymoNr='"+number+"' where KoncertoID='"+kid+"' and SokioID='"+sid+"'";
stat.executeUpdate(sql);
number++;
   
   
    


}
}
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 255));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Šokis"
            }
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Metai:Vardas Pavardė"
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

            },
            new String [] {
                "Metai:Vardas Pavardė"
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

            },
            new String [] {
                "PorosNr:Vardas Pavardė"
            }
        ));
        jTable4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable4FocusGained(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jButton2.setText("+");
        jButton2.setPreferredSize(new java.awt.Dimension(40, 25));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Pašalinti iš sąrašo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PorosNr:Vardas Pavardė"
            }
        ));
        jTable5.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable5FocusGained(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jButton5.setText("-");
        jButton5.setPreferredSize(new java.awt.Dimension(40, 25));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 153, 255));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Merginos porose");
        jLabel4.setMaximumSize(new java.awt.Dimension(93, 24));
        jLabel4.setMinimumSize(new java.awt.Dimension(93, 24));
        jLabel4.setPreferredSize(new java.awt.Dimension(93, 24));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 255));
        jPanel4.setForeground(new java.awt.Color(51, 51, 255));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Vaikinai porose");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2)
        );

        jPanel5.setBackground(new java.awt.Color(204, 153, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 46));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Visos merginos");
        jLabel1.setMaximumSize(new java.awt.Dimension(93, 24));
        jLabel1.setMinimumSize(new java.awt.Dimension(93, 24));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel6.setBackground(new java.awt.Color(51, 51, 255));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Visi vaikinai");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5))
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 255));
        jPanel3.setForeground(new java.awt.Color(51, 51, 255));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Programa");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        jButton6.setText("Šokių matrica");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

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

        jButton7.setText("Analizė");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton1.setText(">>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("<<");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jButton1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(111, 111, 111))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton7)
                                    .addComponent(jButton4)
                                    .addComponent(jButton6)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
          dispose();
   
        
    }//GEN-LAST:event_formWindowClosed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Status.Eile=jTable1.getRowCount();
        new addSokisToKoncertas(null, true).show();
        
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        
        setSokiaiTableInfo();
        
        
        
        
    }//GEN-LAST:event_formWindowGainedFocus

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        deleteSokisFromKoncertas();
        setNewNumbers();
        
        
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
        // TODO add your handling code here:
        jTable2.clearSelection();
        jTable3.clearSelection();
        jTable4.clearSelection();
        jTable5.clearSelection();
        
        
        
    }//GEN-LAST:event_jTable1FocusGained

    private void jTable3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable3FocusGained
        // TODO add your handling code here:
      //  jTable1.clearSelection();
        jTable2.clearSelection();
        jTable4.clearSelection();
        jTable5.clearSelection();
        
    }//GEN-LAST:event_jTable3FocusGained

    private void jTable5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable5FocusGained
        // TODO add your handling code here:
      //  jTable1.clearSelection();
        jTable2.clearSelection();
        jTable3.clearSelection();
        jTable4.clearSelection();
    }//GEN-LAST:event_jTable5FocusGained

    private void jTable4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable4FocusGained
        // TODO add your handling code here:
    //    jTable1.clearSelection();
        jTable2.clearSelection();
        jTable3.clearSelection();
        jTable5.clearSelection();
    }//GEN-LAST:event_jTable4FocusGained

    private void jTable2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable2FocusGained
        // TODO add your handling code here:
     //   jTable1.clearSelection();
        jTable3.clearSelection();
        jTable4.clearSelection();
        jTable5.clearSelection();
    }//GEN-LAST:event_jTable2FocusGained

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         jTable2.clearSelection();
        jTable3.clearSelection();
        jTable4.clearSelection();
        jTable5.clearSelection();
        setVyraiTableInfo();
        setMerginosTableInfo();
        setSokejaiTableInfo();
        setSokejosTableInfo();
        vyraiVisible();
        merginosVisible();
                
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
         try {deleteDalyveFromKoncertas();switchSokejosNumbers();setMerginosTableInfo();setSokejosTableInfo();merginosVisible();} catch (Exception e) {}
         try {deleteDalyvisFromKoncertas();switchSokejasNumbers();setVyraiTableInfo();setSokejaiTableInfo();vyraiVisible();
         } catch (Exception e) {}
        
       
        
       
       
       
       
       
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
        new Matrica(null, true).show();
        
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        new koncertasProblems(null,true).show();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
            // TODO add your handling code here:
        //insert vyras mygtukas
        findDalyvioID();
        findSokioID();

        try {
            String url = "jdbc:sqlite:vingisdb.db";
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            int numeris=jTable5.getRowCount()+1;
            String sqlrequest = "INSERT INTO Dalyvauja (SokioID,DalyvioID,Dalyvavimo_Data,Poros_Nr)VALUES ('"+ SokioID+
            "','" + DalyvioID +"','" + Status.Data +"','" + numeris + "')";

            st.executeUpdate(sqlrequest);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        vyraiVisible();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        findDalyvioIDT2();
        findSokioID();

        try {
            String url = "jdbc:sqlite:vingisdb.db";
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            int numeris=jTable4.getRowCount()+1;
            String sqlrequest = "INSERT INTO Dalyvauja (SokioID,DalyvioID,Dalyvavimo_Data,Poros_Nr)VALUES ('"+ SokioID+
            "','" + DalyvioID +"','" + Status.Data +"','" + numeris + "')";

            st.executeUpdate(sqlrequest);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        setSokejosTable();
        setSokejosTableInfo();
        merginosVisible();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(insideKoncertas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(insideKoncertas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(insideKoncertas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(insideKoncertas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                insideKoncertas dialog = new insideKoncertas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    // End of variables declaration//GEN-END:variables
}
