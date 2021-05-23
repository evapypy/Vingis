/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vingis;

import java.awt.FileDialog;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 *
 * @author TwoTap
 */
public class Pagrindinis extends javax.swing.JDialog {

    /**
     * Creates new form Pagrindinis
     */
    
    
    
    public Pagrindinis(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        jCheckBox1.setSelected(true);
         setRequiredInfo();
         checkForVisablitiy();
         Status.Lytis="";
         setAdminTools();
         jButton5.setVisible(false);
        Pagrindinis.this.setTitle("Šokėjų informacija");
        

        
        

}
  void  setAdminTools(){
      if(Status.loginName.equals("Vadovas")){
        jToggleButton8.setVisible(false);
        jToggleButton9.setVisible(false);
        jToggleButton10.setVisible(false);
        jToggleButton11.setVisible(false);
        jMenu4.setVisible(false);
        jToggleButton6.setVisible(false);
    }}
    
    
    
    
    
        
    
    
    
    
    
    
    
    
    
    
    
    void checkForVisablitiy(){
         if (Status.Option.equals("Sokejai")){visibleAktyvumas();
    visiblePasiekimai();jButton1.setVisible(true);}
        else if (Status.Option.equals("Muzikantai")){visibleAktyvumas();
    visiblePasiekimai();jButton1.setVisible(true);}
        else if (Status.Option.equals("Dainininkai")){visibleAktyvumas();
    visiblePasiekimai();jButton1.setVisible(true);}
        else if (Status.Option.equals("Sokiai")){hidePasiekimai();hideAktyvumas();jButton1.setVisible(false);}
        else if (Status.Option.equals("Koncertai")){visiblePasiekimai();hideAktyvumas();jButton1.setVisible(true);}
    }
    
    
    
    
    
    
    
     void setRequiredInfo(){
         if (Status.Option.equals("Sokejai")){setSokejaiTable(); setSokejaiTableInfo();}
        else if (Status.Option.equals("Muzikantai")){setMuzikantaiTable(); setMuzikantaiTableInfo();}
        else if (Status.Option.equals("Dainininkai")){setDainininkaiTable(); setDainininkaiTableInfo();}
        else if (Status.Option.equals("Sokiai")){setSokiaiTable(); setSokiaiTableInfo();}
        else if (Status.Option.equals("Koncertai")){setKoncertaiTable(); setKoncertaiTableInfo();}
     }
    
    
    
    
    
    
    public void insertDalyvi()
    {
        try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "INSERT INTO Dalyvis (Aktyvumas,Metai,Vardas,Pavarde,Lytis,Zanras,Gimimo_data)VALUES (1,2008,'PEtras','Pava','V','S', '1999-04-1'       );";

 st.executeUpdate(sqlrequest);

    }catch(Exception e){System.out.println(e.getMessage()); 
}}
    
    
   public  String lytis(){
        if(jRadioButtonMenuItem1.isSelected()){return "";}
        else if(jRadioButtonMenuItem2.isSelected()){return " and Lytis='V' " ;}
        else if (jRadioButtonMenuItem3.isSelected()){return " and Lytis='M' ";}
       
        return null;
    }
    
    
    
    public void deleteDalyvi()
    {
        try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "delete from dalyvis where Metai=2008 and Vardas='PEtras' and Pavarde='Pava'";

 st.executeUpdate(sqlrequest);

    }catch(Exception e){System.out.println(e.getMessage()); 
}}
    
     public void setSokejaiTableInfo(){
try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String aktyvumoCheck= "";
    if (jCheckBox1.isSelected()){aktyvumoCheck="and Aktyvumas=1";}
    else aktyvumoCheck = "";

    
    
    
String sqlrequest = "select metai,vardas,pavarde,gimimo_data,el_pastas,Tel_Nr from dalyvis where Zanras = 'S' "+aktyvumoCheck+" "+lytis()+"  order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String metai = String.valueOf(rs.getInt("metai"));
    String vardas = rs.getString("vardas");
    String pavarde = rs.getString("pavarde");
    String gimimoData = rs.getString("gimimo_data");
    String elPastas = rs.getString("el_pastas");
    String gimtasisMiestas = rs.getString("Tel_Nr");
    
String tbData[] = {metai,vardas,pavarde,gimimoData,elPastas,gimtasisMiestas};
DefaultTableModel tblModel = (DefaultTableModel)jTable1.getModel();

tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}   
     
      public void setMuzikantaiTableInfo(){
try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

String aktyvumoCheck= "";
    if (jCheckBox1.isSelected()){aktyvumoCheck="and Aktyvumas=1";}
    else aktyvumoCheck = "";

String sqlrequest = "select metai,vardas,pavarde,gimimo_data,el_pastas,Tel_Nr,Instrumentas from dalyvis where  Zanras = 'M'"+aktyvumoCheck+" "+lytis()+" order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String metai = String.valueOf(rs.getInt("metai"));
    String vardas = rs.getString("vardas");
    String pavarde = rs.getString("pavarde");
    String gimimoData = rs.getString("gimimo_data");
    String elPastas = rs.getString("el_pastas");
    String gimtasisMiestas = rs.getString("Tel_Nr");
    String instrumentas = rs.getString("Instrumentas");
    
String tbData[] = {metai,vardas,pavarde,gimimoData,elPastas,gimtasisMiestas,instrumentas};
DefaultTableModel tblModel = (DefaultTableModel)jTable1.getModel();

tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}  
     
    
    
     public void setSokiaiTableInfo(){
try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select Sokio_Pavadinimas, Vaikinu_kiekis,Merginu_kiekis,Sokio_Trukme,Sukurimo_Metai from sokis order by sokio_Pavadinimas asc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String pavadinimas = rs.getString("Sokio_Pavadinimas");
    String vKiekis = String.valueOf(rs.getInt("Vaikinu_Kiekis"));
    String mKiekis = String.valueOf(rs.getInt("Merginu_Kiekis"));
    String sokioTrukme = String.valueOf(rs.getInt("Sokio_Trukme"));
    String sokioSuk =rs.getString("Sukurimo_Metai");
    
String tbData[] = {pavadinimas,vKiekis,mKiekis,sokioTrukme,sokioSuk};
DefaultTableModel tblModel = (DefaultTableModel)jTable1.getModel();

tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}    
     
     
       public void setDainininkaiTableInfo(){
try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

String aktyvumoCheck= "";
    if (jCheckBox1.isSelected()){aktyvumoCheck="and Aktyvumas=1";}
    else aktyvumoCheck = "";

String sqlrequest = "select metai,vardas,pavarde,gimimo_data,el_pastas,Tel_Nr from dalyvis where  Zanras = 'D' "+aktyvumoCheck +"  "+lytis()+"order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String metai = String.valueOf(rs.getInt("metai"));
    String vardas = rs.getString("vardas");
    String pavarde = rs.getString("pavarde");
    String gimimoData = rs.getString("gimimo_data");
    String elPastas = rs.getString("el_pastas");
    String gimtasisMiestas = rs.getString("Tel_Nr");
    
String tbData[] = {metai,vardas,pavarde,gimimoData,elPastas,gimtasisMiestas};
DefaultTableModel tblModel = (DefaultTableModel)jTable1.getModel();

tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}  
     
     
     
     
   void setKoncertaiTableInfo()
     {
    try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String pavadinimas = rs.getString("Pavadinimas");
    String proga = rs.getString("Proga");
    String vieta = rs.getString("Vieta");
    String data = rs.getString("Data");
    
String tbData[] = {pavadinimas,proga,vieta,data};
DefaultTableModel tblModel = (DefaultTableModel)jTable1.getModel();

tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}
         
         
         
}
     
     
        
    
 

 void setSokejaiTable()
{ jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Metai", "Vardas", "Pavardė", "Gimimo data", "El. Paštas", "Tel. Nr."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class,java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
jButton2.setText("Pridėti šokėją");
jButton1.setText("Papildoma šokėjo informacija");
jButton4.setText("Pašalinti šokėją");
jButton3.setText("Šokėjo pasiekimai");




}
  void setMuzikantaiTable()
{ jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Metai", "Vardas", "Pavardė", "Gimimo data", "El. Paštas", "Tel. Nr.", "Instrumentas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class,java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
jButton2.setText("Pridėti muzikantą");
jButton1.setText("Papildoma muzikanto informacija");
jButton4.setText("Pašalinti muzikantą");
jButton3.setText("Muzikanto pasiekimai");


}
  
  void setDainininkaiTable()
{ jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Metai", "Vardas", "Pavardė", "Gimimo data", "El. Paštas", "Tel. Nr."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class,java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
jButton2.setText("Pridėti dainininką");
jButton1.setText("Papildoma dainininko informacija");
jButton4.setText("Pašalinti dainininką");
jButton3.setText("Dainininko pasiekimai");

}
  
  void setSokiaiTable()
{ jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Šokio pavadinimas", "Merginų kiekis", "Vaikinų kiekis", "Kūrinio trukmė","Sukūrimo metai"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class,java.lang.String.class, java.lang.String.class, java.lang.String.class,java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
jButton2.setText("Pridėti šokį");
jButton1.setText("Papildoma šokio informacija");
jButton4.setText("Pašalinti šokį");


}
  
   void setKoncertaiTable()
{ jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pavadinimas", "Proga", "Vieta", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class,java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
jButton2.setText("Pridėti koncertą");
jButton1.setText("Pridėti muzikantus/dainininkus");
jButton4.setText("Pašalinti koncertą");
jButton3.setText("Koncerto programos kūrimas");
}
  
  
  
  
  void hideAktyvumas()
  {
      jCheckBox1.setVisible(false);
      
  }
  void visibleAktyvumas()
  {jCheckBox1.setVisible(true);
  }
  void visiblePasiekimai(){
      jButton3.setVisible(true);
  }  
  void hidePasiekimai()
  {
      jButton3.setVisible(false);
  }
  void findKoncertoID(){
        int row = jTable1.getSelectedRow();
            
            String pav = (jTable1.getModel().getValueAt(row, 0).toString());
            String data = (jTable1.getModel().getValueAt(row, 3).toString());
            
            
            
            
          
        
        
        try{
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  KoncertoID from Koncertas where Pavadinimas='"+pav+"' and Data='"+data+"'";
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String id = rs.getString("KoncertoID");

    
KoncertoID=id;
Status.KoncertoID=id;
}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}
  
 String KoncertoID="";
  
  
  void findSokioID(){
        int row = jTable1.getSelectedRow();
            
            String pav = (jTable1.getModel().getValueAt(row, 0).toString());
            String vaik = (jTable1.getModel().getValueAt(row, 1).toString());
            String merg = (jTable1.getModel().getValueAt(row, 2).toString());
            
            
            
          
        
        
        try{
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  SokioID from Sokis where Sokio_Pavadinimas='"+pav+"' and Vaikinu_Kiekis='"+vaik+"'and Merginu_Kiekis='"+merg+"'";
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String id = rs.getString("SokioID");

    
SokioID=id;
Status.SokioID=id;
}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}
 String SokioID="";
 
  void findDalyvioID(){
       int row = jTable1.getSelectedRow();
            
            String metai = (jTable1.getModel().getValueAt(row, 0).toString());
            String vardas = (jTable1.getModel().getValueAt(row, 1).toString());
            String pav = (jTable1.getModel().getValueAt(row, 2).toString());

            
           
         
           
           
         try{
        
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String sqlrequest = "select  DalyvioID from Dalyvis  where  Metai='"+metai+"' and Vardas='"+vardas+"'and Pavarde='"+pav+"'";
//String sqlrequest = "select  Pavadinimas, Proga, Vieta,Data from koncertas order by Data desc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String id = rs.getString("DalyvioID");

    
DalyvioID=id;
}}
catch(Exception e){System.out.println(e.getMessage()); 
}}
  
  String DalyvioID="";
  
  
  
  
  
  
  
  
  
  void deleteRysius(){
        try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "delete from Vyksta; delete from Dalyvauja; ";
 st.executeUpdate(sqlrequest);
JOptionPane.showMessageDialog(null, "Ryšiai pašalinti!");

}
catch(Exception e){System.out.println(e.getMessage()); 
}
    }
  
  void deleteAll(){
        try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "delete from Vyksta; delete from Dalyvauja; delete from Sokis; delete from Dalyvis; delete from Koncertas";
 st.executeUpdate(sqlrequest);
JOptionPane.showMessageDialog(null, "Duomenų bazė išvalyta!");

}
catch(Exception e){System.out.println(e.getMessage()); 
}
    }
  
  
  void createKoncertasExcel(){
      
      
      FileOutputStream excelFOU = null;
            BufferedOutputStream excelBOU = null;
            XSSFWorkbook excelJTableExporter=null;
        
            String koncertoData="";
            String koncertoPavadinimas="";
        
        JFileChooser excelFileChooser = new JFileChooser();
        excelFileChooser.setDialogTitle("Išsaugojama kaip");
     
        FileNameExtensionFilter fnef= new FileNameExtensionFilter("EXCEL FILES","xls","xlsx","xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);
        int z=0;
        if(excelChooser==JFileChooser.APPROVE_OPTION){
            
            
            //*************************************************************************
             
      excelJTableExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExporter.createSheet("Koncerto informacija");
             XSSFRow excelRow= excelSheet.createRow(0);
                 XSSFCell excelCell = excelRow.createCell(1);
                  excelCell.setCellValue("Koncerto pavadinimas:");
            
            
//**************************************************************************************
            try {
                
           
                
                 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select * from Koncertas where KoncertoID='"+KoncertoID+"'";
ResultSet rs = st.executeQuery(sqlrequest);

                  while (rs.next()){                
                   excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("Pavadinimas"));
                   excelRow= excelSheet.createRow(1);
                   koncertoPavadinimas=rs.getString("Pavadinimas");
                   excelCell = excelRow.createCell(1);excelCell.setCellValue("Koncerto proga:");
                   excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("Proga"));
                   excelRow= excelSheet.createRow(2);
                    excelCell = excelRow.createCell(1);excelCell.setCellValue("Koncerto vieta:");
                   excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("Vieta"));
                   excelRow= excelSheet.createRow(3);
                    excelCell = excelRow.createCell(1);excelCell.setCellValue("Koncerto data:");
                   excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("Data"));
                   koncertoData=rs.getString("Data");
                }
                  
                 
                 }catch(Exception e){System.out.println(e.getMessage());}           
                  
                 excelRow= excelSheet.createRow(5);
 excelCell = excelRow.createCell(0);excelCell.setCellValue("Nr:");
 excelCell = excelRow.createCell(1);excelCell.setCellValue("Koncerto programa:");     
                
                
                
 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select  Sokio_Pavadinimas from Sokis as s, vyksta as v, koncertas as k where k.koncertoID = v.koncertoID and s.sokioID = v.SokioID and k.koncertoID='"+KoncertoID+"' order by PasirodymoNr ASC";
int skaiciuokle=1;
ResultSet rs = st.executeQuery(sqlrequest);
z=6;excelRow= excelSheet.createRow(z);
                  while (rs.next()){        
                      
                   excelCell = excelRow.createCell(0);excelCell.setCellValue(skaiciuokle);
                   excelCell = excelRow.createCell(1);excelCell.setCellValue(rs.getString("Sokio_Pavadinimas"));
                  z++;
                  skaiciuokle++;
                   excelRow= excelSheet.createRow(z);
                  
                }
                  
                 
                 }catch(Exception e){System.out.println(e.getMessage());}  
 

 z=z+1;
  excelRow= excelSheet.createRow(z);
 excelCell = excelRow.createCell(1);excelCell.setCellValue("Koncerto dalyviai:");
 z=z+1;
  excelRow= excelSheet.createRow(z);
 excelCell = excelRow.createCell(0);excelCell.setCellValue("Nr:");
 excelCell = excelRow.createCell(1);excelCell.setCellValue("Pavardė:");
  excelCell = excelRow.createCell(2);excelCell.setCellValue("Vardas:");    
 
z++;
 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select distinct pavarde,vardas from dalyvis as d, dalyvauja as da, sokis as s, vyksta as v, koncertas as k where d.DalyvioID=da.DalyvioID and s.SokioID=da.SokioID and s.SokioID=v.SokioID and k.KoncertoID=v.KoncertoID and k.koncertoID='"+KoncertoID+"' order by  pavarde asc, vardas asc";
int skaiciuokle=1;
ResultSet rs = st.executeQuery(sqlrequest);
excelRow= excelSheet.createRow(z);
                  while (rs.next()){        
                      
                   excelCell = excelRow.createCell(0);excelCell.setCellValue(skaiciuokle);
                   excelCell = excelRow.createCell(1);excelCell.setCellValue(rs.getString("Pavarde"));
                   excelCell = excelRow.createCell(2);excelCell.setCellValue(rs.getString("Vardas"));
                  z++;
                  skaiciuokle++;
                   excelRow= excelSheet.createRow(z);
                  
                }
                  
                 
                 }catch(Exception e){System.out.println(e.getMessage());}  
 
 // Pirmo lapo pabaiga ****************************************************************************************************************************************
 //Sudarinejam sheetus
 int koncertuKiekis=0;
 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select distinct Sokio_Pavadinimas from dalyvis as d, dalyvauja as da, sokis as s, vyksta as v, koncertas as k where d.DalyvioID=da.DalyvioID and s.SokioID=da.SokioID and s.SokioID=v.SokioID and k.KoncertoID=v.KoncertoID and k.koncertoID='"+KoncertoID+"' order by  v.PasirodymoNr asc";
ResultSet rs = st.executeQuery(sqlrequest);

                  while (rs.next()){        
                   excelJTableExporter.createSheet(rs.getString("Sokio_Pavadinimas"));
                  koncertuKiekis++;
                }
                  
                 
                 }catch(Exception e){System.out.println(e.getMessage());}  
 
 
                
 
 
 for(int i=1;i<=koncertuKiekis;i++)
 {
    excelSheet = excelJTableExporter.getSheetAt(i);
     
      excelRow= excelSheet.createRow(0);
      excelCell = excelRow.createCell(0);excelCell.setCellValue("Poros nr:");
      excelCell = excelRow.createCell(1);excelCell.setCellValue("Vaikinas");
      excelCell = excelRow.createCell(4);excelCell.setCellValue("Mergina");
     
     try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

int q=1;
String sqlrequest = "select d.vardas, d.pavarde, da.Poros_Nr, d.lytis from dalyvis as d, dalyvauja as da, sokis as s, vyksta as v, koncertas as k where d.DalyvioID=da.DalyvioID and s.SokioID=da.SokioID and s.SokioID=v.SokioID and k.KoncertoID=v.KoncertoID and da.Dalyvavimo_Data=k.Data and k.koncertoID='"+KoncertoID+"' and Sokio_Pavadinimas='"+excelJTableExporter.getSheetName(i)+"' and d.Zanras='S' order by da.Poros_Nr ASC";
ResultSet rs = st.executeQuery(sqlrequest);
int porosnr=0;
                  while (rs.next()){    
                      if(porosnr!=rs.getInt("Poros_Nr")){
                   porosnr= rs.getInt("Poros_Nr"); 
                   excelRow= excelSheet.createRow(q);
                   q++;
                   excelCell = excelRow.createCell(0);excelCell.setCellValue(porosnr);
                           }
                  if(rs.getString("Lytis").equals("V")){
                      excelCell = excelRow.createCell(1);excelCell.setCellValue(rs.getString("Vardas"));
                      excelCell = excelRow.createCell(2);excelCell.setCellValue(rs.getString("Pavarde"));
                  }
                  if(rs.getString("Lytis").equals("M")){
                      excelCell = excelRow.createCell(4);excelCell.setCellValue(rs.getString("Vardas"));
                      excelCell = excelRow.createCell(5);excelCell.setCellValue(rs.getString("Pavarde"));
                  }    
                      
                }
                  
                 
                 }catch(Exception e){System.out.println(e.getMessage());}  
      
      
      
      
      
      
     
     
     
 }
 
 
 
 
 
 
 
 
 
                excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile()+".xlsx");
                excelBOU = new BufferedOutputStream(excelFOU);
                excelJTableExporter.write(excelBOU);
                JOptionPane.showMessageDialog(null, "Duomenys iškelti");
                
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }   catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                try {
                    
                    if (excelBOU!=null){
                       excelBOU.close(); 
                    }
                    
                    
                    
                    if (excelFOU!=null){
                       excelFOU.close(); 
                    }
                    
                    if (excelJTableExporter!=null){
                       excelJTableExporter.close(); 
                    }
                    
                    
                } catch (IOException ex) {//error?
                    ex.printStackTrace();
                }
            }
            
            
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jToggleButton9 = new javax.swing.JToggleButton();
        jToggleButton10 = new javax.swing.JToggleButton();
        jToggleButton11 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jCheckBox1 = new javax.swing.JCheckBoxMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Šokėjai");
        setPreferredSize(new java.awt.Dimension(1200, 520));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                Exit(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 255));

        jToggleButton1.setText("Šokėjai");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton2.setText("Muzikantai");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton3.setText("Dainininkai");
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        jToggleButton4.setText("Kūriniai");
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });

        jToggleButton5.setText("Koncertai");
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });

        jToggleButton6.setBackground(new java.awt.Color(255, 51, 51));
        jToggleButton6.setText("Finansai");
        jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton6ActionPerformed(evt);
            }
        });

        jToggleButton7.setText("Kalendorius");
        jToggleButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton7ActionPerformed(evt);
            }
        });

        jToggleButton8.setBackground(new java.awt.Color(255, 51, 51));
        jToggleButton8.setText("Trinti ryšius");
        jToggleButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton8ActionPerformed(evt);
            }
        });

        jToggleButton9.setBackground(new java.awt.Color(255, 51, 51));
        jToggleButton9.setText("Išvalyti DB");
        jToggleButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton9ActionPerformed(evt);
            }
        });

        jToggleButton10.setBackground(new java.awt.Color(255, 51, 51));
        jToggleButton10.setText("V slaptažodis");
        jToggleButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton10ActionPerformed(evt);
            }
        });

        jToggleButton11.setBackground(new java.awt.Color(255, 51, 51));
        jToggleButton11.setText("A slaptažodis");
        jToggleButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jToggleButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jToggleButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jToggleButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jToggleButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton4)
                .addGap(4, 4, 4)
                .addComponent(jToggleButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Papildoma informacija");
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

        jButton4.setText("Pašalinti");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("Šokėjo pasiekimai");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Papildoma koncerto informacija");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jMenu1.setText("Sistema");

        jMenuItem1.setText("Atsijungti");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Iškelti");

        jMenuItem6.setText("Visus narius");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem2.setText("Visus aktyvius narius");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);
        jMenu2.add(jSeparator2);

        jMenuItem3.setText("Visus aktyvius šokėjus");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Visus aktyvius muzikantus");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Visus aktyvius dainininkus");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);
        jMenu2.add(jSeparator3);

        jMenuItem8.setText("Pasirinktą koncertą");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Filtravimas");

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Tik aktyvūs nariai");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jMenu3.add(jCheckBox1);
        jMenu3.add(jSeparator1);

        buttonGroup1.add(jRadioButtonMenuItem1);
        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("Vaikinai ir merginos");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jRadioButtonMenuItem1);

        buttonGroup1.add(jRadioButtonMenuItem2);
        jRadioButtonMenuItem2.setText("Tik vaikinai");
        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jRadioButtonMenuItem2);

        buttonGroup1.add(jRadioButtonMenuItem3);
        jRadioButtonMenuItem3.setText("Tik merginos");
        jRadioButtonMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jRadioButtonMenuItem3);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Duombazė");

        jMenuItem7.setText("Prijungti duomenis");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1055, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addGap(48, 61, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("pagrindinisLangas");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
    // Button sokejai
    jToggleButton1.setSelected(false);
    Status.Option = "Sokejai";
    Pagrindinis.this.setTitle("Šokėjų informacija");
    setSokejaiTable();
    setSokejaiTableInfo();
    visibleAktyvumas();
    jButton5.setVisible(false);
    jButton1.setVisible(true);
    visiblePasiekimai();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
    //Button Muzikantai
    jToggleButton2.setSelected(false);
    Status.Option = "Muzikantai";
    jButton5.setVisible(false);
    Pagrindinis.this.setTitle("Muzikantų informacija");
    setMuzikantaiTable();
    setMuzikantaiTableInfo();
    jButton1.setVisible(true);
    visibleAktyvumas();
    visiblePasiekimai();
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
    //Button Dainininkai
    jToggleButton3.setSelected(false);
    Status.Option = "Dainininkai";
    jButton5.setVisible(false);
    Pagrindinis.this.setTitle("Dainininkų informacija");
    setDainininkaiTable();
    setDainininkaiTableInfo();
    jButton1.setVisible(true);
    visibleAktyvumas();
    visiblePasiekimai();
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
     //Button Sokiai
     jToggleButton4.setSelected(false);
     jButton5.setVisible(false);
     Pagrindinis.this.setTitle("Šokių informacija");
     Status.Option = "Sokiai";
     setSokiaiTable();
     setSokiaiTableInfo();
    
     hideAktyvumas();
     hidePasiekimai();
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
    //Button Koncertai
    jToggleButton5.setSelected(false);
    Status.Option = "Koncertai";
    Pagrindinis.this.setTitle("Koncertų informacija");
    setKoncertaiTable();
    setKoncertaiTableInfo();
    jButton5.setVisible(true);
    hideAktyvumas();
    visiblePasiekimai();
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void jToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton6ActionPerformed
jToggleButton6.setSelected(false);
        //Button Finansai
         
        
    }//GEN-LAST:event_jToggleButton6ActionPerformed

    private void jToggleButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton7ActionPerformed
        // TODO add your handling code here:
        jToggleButton7.setSelected(false);
   
        
    }//GEN-LAST:event_jToggleButton7ActionPerformed

    private void Exit(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Exit
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_Exit

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
     //Delete button
     if(Status.Option.equals("Sokiai")){
         try {
            String url = "jdbc:sqlite:vingisdb.db";
            Connection con = DriverManager.getConnection(url);
          //  Statement st = con.createStatement();

            findSokioID();
            String query = "DELETE from Sokis where SokioID='" + SokioID + "'";
            String query1 = "DELETE from Vyksta where SokioID='" + SokioID + "'";
            String query2 = "DELETE from Dalyvauja where SokioID='" + SokioID + "'";

            PreparedStatement pst = con.prepareStatement(query);
            PreparedStatement pst1 = con.prepareStatement(query1);
            PreparedStatement pst2 = con.prepareStatement(query2);
            pst.executeUpdate();
            pst1.executeUpdate();
            pst2.executeUpdate();

            setSokiaiTable();setSokiaiTableInfo();
            
            JOptionPane.showMessageDialog(null, "Šokis pašalintas!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     }
     else if(Status.Option.equals("Koncertai")){
         try {
            String url = "jdbc:sqlite:vingisdb.db";
            Connection con = DriverManager.getConnection(url);
          //  Statement st = con.createStatement();
        
         findKoncertoID();
            
            
            String query = "DELETE from Koncertas where KoncertoID='" + KoncertoID + "'";
            String query1 = "DELETE from Vyksta where KoncertoID='" + KoncertoID + "'";

            PreparedStatement pst = con.prepareStatement(query);
            PreparedStatement pst1 = con.prepareStatement(query1);
            pst.executeUpdate();
            pst1.executeUpdate();

            setKoncertaiTable();setKoncertaiTableInfo();
            
            JOptionPane.showMessageDialog(null, "Koncertas pašalintas!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     }
     
     
     
     if (Status.Option.equals("Sokejai") ||Status.Option.equals("Dainininkai") || Status.Option.equals("Muzikantai") ){
     try {
            String url = "jdbc:sqlite:vingisdb.db";
            Connection con = DriverManager.getConnection(url);
          //  Statement st = con.createStatement();
            int row = jTable1.getSelectedRow();
            String metai = (jTable1.getModel().getValueAt(row, 0).toString());
            String vardas = (jTable1.getModel().getValueAt(row, 1).toString());
            String pavarde = (jTable1.getModel().getValueAt(row, 2).toString());
            
            
            
            
            
            
            
            findDalyvioID();
            
            String query1 = "DELETE from Dalyvis where DalyvioID='" + DalyvioID + "'";
            String query2 = "DELETE from Dalyvauja where DalyvioID='" + DalyvioID + "'";

            PreparedStatement pst1 = con.prepareStatement(query1);
            PreparedStatement pst2 = con.prepareStatement(query2);
            pst1.executeUpdate();
            pst2.executeUpdate();

            if (Status.Option.equals("Sokejai")){setSokejaiTable(); setSokejaiTableInfo();}
        else if (Status.Option.equals("Muzikantai")){setMuzikantaiTable(); setMuzikantaiTableInfo();}
        else if (Status.Option.equals("Dainininkai")){setDainininkaiTable(); setDainininkaiTableInfo();}
            
            JOptionPane.showMessageDialog(null, "Dalyvis pašalintas!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    // Senas basic delete
    deleteDalyvi();
     }
     
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Insert button
        
        if(Status.Option.equals("Sokiai")||Status.Option.equals("Koncertai") ){
            Status.Action="Add";
          
        new skInsertField(null, true).show();
        
        }
        else{
    
       Status.Action="Add";
   new addDalyvis(null, true).show();
   
        }
       // Senas basic metodas 
       //insertDalyvi();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Button Update
        if(Status.Option.equals("Sokiai")){
            try {
                findSokioID();
                Status.Action="update";
            int row = jTable1.getSelectedRow();
            Status.Metai = (jTable1.getModel().getValueAt(row, 0).toString());
            Status.Vardas = (jTable1.getModel().getValueAt(row, 1).toString());
            Status.Pavarde = (jTable1.getModel().getValueAt(row, 2).toString());
            
            new skInsertField (null, true).show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nepasirinkote šokio!");
        }
        
        }
        
        
        
        else if (Status.Option.equals("Koncertai")){
           try {
            int row = jTable1.getSelectedRow();
            
            Status.Pavadinimas = (jTable1.getModel().getValueAt(row, 0).toString());
            Status.Data = (jTable1.getModel().getValueAt(row, 3).toString());
               
            
              new pridetiPapDalyvius(null, true).show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nepasirinkote koncerto!");
        }
        }
        
        
        
        else if (Status.Option.equals("Sokejai") ||Status.Option.equals("Dainininkai") || Status.Option.equals("Muzikantai") ){
        Status.Action="Update";
          try {
            int row = jTable1.getSelectedRow();
            Status.Metai = (jTable1.getModel().getValueAt(row, 0).toString());
            Status.Vardas = (jTable1.getModel().getValueAt(row, 1).toString());
            Status.Pavarde = (jTable1.getModel().getValueAt(row, 2).toString());
           
            new addDalyvis (null, true).show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nepasirinkote dalyvio!");
        }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        if (Status.Option.equals("Koncertai")){
            
            try {
            int row = jTable1.getSelectedRow();
            
            Status.Pavadinimas = (jTable1.getModel().getValueAt(row, 0).toString());
            Status.Data = (jTable1.getModel().getValueAt(row, 3).toString());
               
           
              new insideKoncertas(null, true).show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nepasirinkote koncerto!");
        }
        }
        
        
        
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jRadioButtonMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem3ActionPerformed
        // TODO add your handling code here:
        
        Status.Lytis=" and Lytis='M' "; 
          if (Status.Option.equals("Sokejai")){setSokejaiTable(); setSokejaiTableInfo();}
        else if (Status.Option.equals("Muzikantai")){setMuzikantaiTable(); setMuzikantaiTableInfo();}
        else if (Status.Option.equals("Dainininkai")){setDainininkaiTable(); setDainininkaiTableInfo();}
    }//GEN-LAST:event_jRadioButtonMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
            new Prisijungimas(null, true).show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
               // TODO add your handling code here:
           if (Status.Option.equals("Sokejai")){setSokejaiTable(); setSokejaiTableInfo();}
        else if (Status.Option.equals("Muzikantai")){setMuzikantaiTable(); setMuzikantaiTableInfo();}
        else if (Status.Option.equals("Dainininkai")){setDainininkaiTable(); setDainininkaiTableInfo();}
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
        // TODO add your handling code here:
        Status.Lytis="";
          if (Status.Option.equals("Sokejai")){setSokejaiTable(); setSokejaiTableInfo();}
        else if (Status.Option.equals("Muzikantai")){setMuzikantaiTable(); setMuzikantaiTableInfo();}
        else if (Status.Option.equals("Dainininkai")){setDainininkaiTable(); setDainininkaiTableInfo();}
    }//GEN-LAST:event_jRadioButtonMenuItem1ActionPerformed

    private void jRadioButtonMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem2ActionPerformed
        // TODO add your handling code here:
        Status.Lytis=" and Lytis='V'  ";
          if (Status.Option.equals("Sokejai")){setSokejaiTable(); setSokejaiTableInfo();}
        else if (Status.Option.equals("Muzikantai")){setMuzikantaiTable(); setMuzikantaiTableInfo();}
        else if (Status.Option.equals("Dainininkai")){setDainininkaiTable(); setDainininkaiTableInfo();}
    }//GEN-LAST:event_jRadioButtonMenuItem2ActionPerformed

    private void jToggleButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton8ActionPerformed
        // TODO add your handling code here:
        jToggleButton8.setSelected(false);
        deleteRysius();
        
        
        
    }//GEN-LAST:event_jToggleButton8ActionPerformed

    private void jToggleButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton9ActionPerformed
        // TODO add your handling code here:
        jToggleButton9.setSelected(false);
        
        deleteAll();
        setRequiredInfo();
        
        
    }//GEN-LAST:event_jToggleButton9ActionPerformed

    private void jToggleButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton10ActionPerformed
        // TODO add your handling code here:
        jToggleButton10.setSelected(false);
        Status.changeWhat="vadovas";
        new changePassword(null, true).show();
    }//GEN-LAST:event_jToggleButton10ActionPerformed

    private void jToggleButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton11ActionPerformed
        // TODO add your handling code here:
        jToggleButton11.setSelected(false);
        Status.changeWhat="admin";
        new changePassword(null, true).show();
        
        
    }//GEN-LAST:event_jToggleButton11ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        
        //*************************************************************************
            FileOutputStream excelFOU = null;
            BufferedOutputStream excelBOU = null;
            XSSFWorkbook excelJTableExporter=null;
        
        
        JFileChooser excelFileChooser = new JFileChooser();
        excelFileChooser.setDialogTitle("Išsaugojama kaip");
     
        FileNameExtensionFilter fnef= new FileNameExtensionFilter("EXCEL FILES","xls","xlsx","xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);
        int z=2;
        if(excelChooser==JFileChooser.APPROVE_OPTION){
            
            
            //*************************************************************************
             
    
            
            
            
//**************************************************************************************
            try {
                
              excelJTableExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExporter.createSheet("Visi aktyvūs šokėjai");
                
                
                
                XSSFRow excelRow= excelSheet.createRow(0);
                 XSSFCell excelCell = excelRow.createCell(0);
                  excelCell.setCellValue("Vaikinai:");
                  
                   excelRow= excelSheet.createRow(1);
                 excelCell = excelRow.createCell(0);excelCell.setCellValue("Metai");
                 excelCell = excelRow.createCell(1);excelCell.setCellValue("Vardas");
                 excelCell = excelRow.createCell(2);excelCell.setCellValue("Pavardė");
                 excelCell = excelRow.createCell(3);excelCell.setCellValue("Gimimo data");
                 excelCell = excelRow.createCell(4);excelCell.setCellValue("Ūgis");
                 excelCell = excelRow.createCell(5);excelCell.setCellValue("El. Paštas");
                 excelCell = excelRow.createCell(6);excelCell.setCellValue("Gimtasis Miestas");
                 excelCell = excelRow.createCell(7);excelCell.setCellValue("Telefono numeris");
                 excelCell = excelRow.createCell(8);excelCell.setCellValue("Universitetas");
                 excelCell = excelRow.createCell(9);excelCell.setCellValue("Fakultetas");
                 excelCell = excelRow.createCell(10);excelCell.setCellValue("Grupė");
                 excelCell = excelRow.createCell(11);excelCell.setCellValue("Studento numeris");
                 excelCell = excelRow.createCell(12);excelCell.setCellValue("Adresas");
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select metai,vardas,pavarde,gimimo_data,ugis,el_pastas,gimtasis_miestas,Tel_Nr,Universitetas,Fakultetas,Grupe,Studento_Nr,Adresas from dalyvis where Zanras = 'S' and Lytis='V' and Aktyvumas='1' order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);



 
                
                    
                    
    while (rs.next()){                
                    
                    
                     excelRow= excelSheet.createRow(z);

                 excelCell = excelRow.createCell(0);excelCell.setCellValue(rs.getString("Metai"));
                 excelCell = excelRow.createCell(1);excelCell.setCellValue(rs.getString("Vardas"));
                 excelCell = excelRow.createCell(2);excelCell.setCellValue(rs.getString("Pavarde"));
                 excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("gimimo_data"));
                 excelCell = excelRow.createCell(4);excelCell.setCellValue(rs.getString("ugis"));
                 excelCell = excelRow.createCell(5);excelCell.setCellValue(rs.getString("El_Pastas"));
                 excelCell = excelRow.createCell(6);excelCell.setCellValue(rs.getString("Gimtasis_miestas"));
                 excelCell = excelRow.createCell(7);excelCell.setCellValue(rs.getString("Tel_Nr"));
                 excelCell = excelRow.createCell(8);excelCell.setCellValue(rs.getString("Universitetas"));
                 excelCell = excelRow.createCell(9);excelCell.setCellValue(rs.getString("Fakultetas"));
                 excelCell = excelRow.createCell(10);excelCell.setCellValue(rs.getString("Grupe"));
                 excelCell = excelRow.createCell(11);excelCell.setCellValue(rs.getString("Studento_Nr"));
                 excelCell = excelRow.createCell(12);excelCell.setCellValue(rs.getString("Adresas"));
                       // excelCell.setCellValue("");
                    
                    z++;
                }



                 
                 }catch(Exception e){System.out.println(e.getMessage()); 
}           
z++;
excelRow= excelSheet.createRow(z);
                excelCell = excelRow.createCell(0);
                  excelCell.setCellValue("Merginos:");   
                 z++;
                 
                      excelRow= excelSheet.createRow(z);
                 excelCell = excelRow.createCell(0);excelCell.setCellValue("Metai");
                 excelCell = excelRow.createCell(1);excelCell.setCellValue("Vardas");
                 excelCell = excelRow.createCell(2);excelCell.setCellValue("Pavardė");
                 excelCell = excelRow.createCell(3);excelCell.setCellValue("Gimimo data");
                 excelCell = excelRow.createCell(4);excelCell.setCellValue("Ūgis");
                 excelCell = excelRow.createCell(5);excelCell.setCellValue("El. Paštas");
                 excelCell = excelRow.createCell(6);excelCell.setCellValue("Gimtasis Miestas");
                 excelCell = excelRow.createCell(7);excelCell.setCellValue("Telefono numeris");
                 excelCell = excelRow.createCell(8);excelCell.setCellValue("Universitetas");
                 excelCell = excelRow.createCell(9);excelCell.setCellValue("Fakultetas");
                 excelCell = excelRow.createCell(10);excelCell.setCellValue("Grupė");
                 excelCell = excelRow.createCell(11);excelCell.setCellValue("Studento numeris");
                 excelCell = excelRow.createCell(12);excelCell.setCellValue("Adresas");
                 
                 z++;
                 
                 
                 
                 
                 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select metai,vardas,pavarde,gimimo_data,ugis,el_pastas,gimtasis_miestas,Tel_Nr,Universitetas,Fakultetas,Grupe,Studento_Nr,Adresas from dalyvis where Zanras = 'S' and Lytis='M' and Aktyvumas='1' order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);



 
                
                    
                    
    while (rs.next()){                
                    
                    
                     excelRow= excelSheet.createRow(z);

                 excelCell = excelRow.createCell(0);excelCell.setCellValue(rs.getString("Metai"));
                 excelCell = excelRow.createCell(1);excelCell.setCellValue(rs.getString("Vardas"));
                 excelCell = excelRow.createCell(2);excelCell.setCellValue(rs.getString("Pavarde"));
                 excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("gimimo_data"));
                 excelCell = excelRow.createCell(4);excelCell.setCellValue(rs.getString("ugis"));
                 excelCell = excelRow.createCell(5);excelCell.setCellValue(rs.getString("El_Pastas"));
                 excelCell = excelRow.createCell(6);excelCell.setCellValue(rs.getString("Gimtasis_miestas"));
                 excelCell = excelRow.createCell(7);excelCell.setCellValue(rs.getString("Tel_Nr"));
                 excelCell = excelRow.createCell(8);excelCell.setCellValue(rs.getString("Universitetas"));
                 excelCell = excelRow.createCell(9);excelCell.setCellValue(rs.getString("Fakultetas"));
                 excelCell = excelRow.createCell(10);excelCell.setCellValue(rs.getString("Grupe"));
                 excelCell = excelRow.createCell(11);excelCell.setCellValue(rs.getString("Studento_Nr"));
                 excelCell = excelRow.createCell(12);excelCell.setCellValue(rs.getString("Adresas"));
                       // excelCell.setCellValue("");
                    
                    z++;
                }



                 
                 }catch(Exception e){System.out.println(e.getMessage()); 
}
                 
                 
                 
                 
//                for(int i=2;i<=z;i++){
//                    //Pirma dalis
//                    
//                    
//                    
//                    
//                    
//                     excelRow= excelSheet.createRow(i);
//                    for(int j=0;j<10;j++){
//                        // Antra dalis
//                        
//                         excelCell = excelRow.createCell(j);
//                       // excelCell.setCellValue("");
//                    }
//                    
//                }
                
                
               
                
                
                
                
                
                
                excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile()+".xlsx");
                excelBOU = new BufferedOutputStream(excelFOU);
                excelJTableExporter.write(excelBOU);
                JOptionPane.showMessageDialog(null, "Duomenys iškelti");
                
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }   catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                try {
                    
                    if (excelBOU!=null){
                       excelBOU.close(); 
                    }
                    
                    
                    
                    if (excelFOU!=null){
                       excelFOU.close(); 
                    }
                    
                    if (excelJTableExporter!=null){
                       excelJTableExporter.close(); 
                    }
                    
                    
                } catch (IOException ex) {//error?
                    ex.printStackTrace();
                }
            }
            
            
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
         FileOutputStream excelFOU = null;
            BufferedOutputStream excelBOU = null;
            XSSFWorkbook excelJTableExporter=null;
        
        
        JFileChooser excelFileChooser = new JFileChooser();
        excelFileChooser.setDialogTitle("Išsaugojama kaip");
     
        FileNameExtensionFilter fnef= new FileNameExtensionFilter("EXCEL FILES","xls","xlsx","xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);
        int z=2;
        if(excelChooser==JFileChooser.APPROVE_OPTION){
            
            
            //*************************************************************************
             
    
            
            
            
//**************************************************************************************
            try {
                
              excelJTableExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExporter.createSheet("Visi aktyvūs muzikantai");
                
                
                
                XSSFRow excelRow= excelSheet.createRow(0);
                 XSSFCell excelCell = excelRow.createCell(0);
                  excelCell.setCellValue("Aktyvūs muzikantai:");
                  
                   excelRow= excelSheet.createRow(1);
                 excelCell = excelRow.createCell(0);excelCell.setCellValue("Metai");
                 excelCell = excelRow.createCell(1);excelCell.setCellValue("Vardas");
                 excelCell = excelRow.createCell(2);excelCell.setCellValue("Pavardė");
                 excelCell = excelRow.createCell(3);excelCell.setCellValue("Gimimo data");
                 excelCell = excelRow.createCell(4);excelCell.setCellValue("Ūgis");
                 excelCell = excelRow.createCell(5);excelCell.setCellValue("El. Paštas");
                 excelCell = excelRow.createCell(6);excelCell.setCellValue("Gimtasis Miestas");
                 excelCell = excelRow.createCell(7);excelCell.setCellValue("Telefono numeris");
                 excelCell = excelRow.createCell(8);excelCell.setCellValue("Universitetas");
                 excelCell = excelRow.createCell(9);excelCell.setCellValue("Fakultetas");
                 excelCell = excelRow.createCell(10);excelCell.setCellValue("Grupė");
                 excelCell = excelRow.createCell(11);excelCell.setCellValue("Studento numeris");
                 excelCell = excelRow.createCell(12);excelCell.setCellValue("Adresas");
                 excelCell = excelRow.createCell(13);excelCell.setCellValue("Instrumentas");
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select metai,vardas,pavarde,gimimo_data,ugis,el_pastas,gimtasis_miestas,Tel_Nr,Universitetas,Fakultetas,Grupe,Studento_Nr,Adresas,Instrumentas from dalyvis where Zanras = 'M' and Aktyvumas='1' order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);



 
                
                    
                    
    while (rs.next()){                
                    
                    
                     excelRow= excelSheet.createRow(z);

                 excelCell = excelRow.createCell(0);excelCell.setCellValue(rs.getString("Metai"));
                 excelCell = excelRow.createCell(1);excelCell.setCellValue(rs.getString("Vardas"));
                 excelCell = excelRow.createCell(2);excelCell.setCellValue(rs.getString("Pavarde"));
                 excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("gimimo_data"));
                 excelCell = excelRow.createCell(4);excelCell.setCellValue(rs.getString("ugis"));
                 excelCell = excelRow.createCell(5);excelCell.setCellValue(rs.getString("El_Pastas"));
                 excelCell = excelRow.createCell(6);excelCell.setCellValue(rs.getString("Gimtasis_miestas"));
                 excelCell = excelRow.createCell(7);excelCell.setCellValue(rs.getString("Tel_Nr"));
                 excelCell = excelRow.createCell(8);excelCell.setCellValue(rs.getString("Universitetas"));
                 excelCell = excelRow.createCell(9);excelCell.setCellValue(rs.getString("Fakultetas"));
                 excelCell = excelRow.createCell(10);excelCell.setCellValue(rs.getString("Grupe"));
                 excelCell = excelRow.createCell(11);excelCell.setCellValue(rs.getString("Studento_Nr"));
                 excelCell = excelRow.createCell(12);excelCell.setCellValue(rs.getString("Adresas"));
                 excelCell = excelRow.createCell(13);excelCell.setCellValue(rs.getString("Instrumentas"));
                       // excelCell.setCellValue("");
                    
                    z++;
                }



                 
                 }catch(Exception e){System.out.println(e.getMessage()); 
}           

                 
                 
                 
                 
//                for(int i=2;i<=z;i++){
//                    //Pirma dalis
//                    
//                    
//                    
//                    
//                    
//                     excelRow= excelSheet.createRow(i);
//                    for(int j=0;j<10;j++){
//                        // Antra dalis
//                        
//                         excelCell = excelRow.createCell(j);
//                       // excelCell.setCellValue("");
//                    }
//                    
//                }
                
                
               
                
                
                
                
                
                
                excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile()+".xlsx");
                excelBOU = new BufferedOutputStream(excelFOU);
                excelJTableExporter.write(excelBOU);
                JOptionPane.showMessageDialog(null, "Duomenys iškelti");
                
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }   catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                try {
                    
                    if (excelBOU!=null){
                       excelBOU.close(); 
                    }
                    
                    
                    
                    if (excelFOU!=null){
                       excelFOU.close(); 
                    }
                    
                    if (excelJTableExporter!=null){
                       excelJTableExporter.close(); 
                    }
                    
                    
                } catch (IOException ex) {//error?
                    ex.printStackTrace();
                }
            }
            
            
        }
        
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        FileOutputStream excelFOU = null;
            BufferedOutputStream excelBOU = null;
            XSSFWorkbook excelJTableExporter=null;
        
        
        JFileChooser excelFileChooser = new JFileChooser();
        excelFileChooser.setDialogTitle("Išsaugojama kaip");
     
        FileNameExtensionFilter fnef= new FileNameExtensionFilter("EXCEL FILES","xls","xlsx","xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);
        int z=2;
        if(excelChooser==JFileChooser.APPROVE_OPTION){
            
            
            //*************************************************************************
             
    
            
            
            
//**************************************************************************************
            try {
                
              excelJTableExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExporter.createSheet("Visi aktyvūs dainininkai");
                
                
                
                XSSFRow excelRow= excelSheet.createRow(0);
                 XSSFCell excelCell = excelRow.createCell(0);
                  excelCell.setCellValue("Aktyvūs dainininkai:");
                  
                   excelRow= excelSheet.createRow(1);
                 excelCell = excelRow.createCell(0);excelCell.setCellValue("Metai");
                 excelCell = excelRow.createCell(1);excelCell.setCellValue("Vardas");
                 excelCell = excelRow.createCell(2);excelCell.setCellValue("Pavardė");
                 excelCell = excelRow.createCell(3);excelCell.setCellValue("Gimimo data");
                 excelCell = excelRow.createCell(4);excelCell.setCellValue("Ūgis");
                 excelCell = excelRow.createCell(5);excelCell.setCellValue("El. Paštas");
                 excelCell = excelRow.createCell(6);excelCell.setCellValue("Gimtasis Miestas");
                 excelCell = excelRow.createCell(7);excelCell.setCellValue("Telefono numeris");
                 excelCell = excelRow.createCell(8);excelCell.setCellValue("Universitetas");
                 excelCell = excelRow.createCell(9);excelCell.setCellValue("Fakultetas");
                 excelCell = excelRow.createCell(10);excelCell.setCellValue("Grupė");
                 excelCell = excelRow.createCell(11);excelCell.setCellValue("Studento numeris");
                 excelCell = excelRow.createCell(12);excelCell.setCellValue("Adresas");
                
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select metai,vardas,pavarde,gimimo_data,ugis,el_pastas,gimtasis_miestas,Tel_Nr,Universitetas,Fakultetas,Grupe,Studento_Nr,Adresas from dalyvis where Zanras = 'D' and Aktyvumas='1' order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);



 
                
                    
                    
    while (rs.next()){                
                    
                    
                     excelRow= excelSheet.createRow(z);

                 excelCell = excelRow.createCell(0);excelCell.setCellValue(rs.getString("Metai"));
                 excelCell = excelRow.createCell(1);excelCell.setCellValue(rs.getString("Vardas"));
                 excelCell = excelRow.createCell(2);excelCell.setCellValue(rs.getString("Pavarde"));
                 excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("gimimo_data"));
                 excelCell = excelRow.createCell(4);excelCell.setCellValue(rs.getString("ugis"));
                 excelCell = excelRow.createCell(5);excelCell.setCellValue(rs.getString("El_Pastas"));
                 excelCell = excelRow.createCell(6);excelCell.setCellValue(rs.getString("Gimtasis_miestas"));
                 excelCell = excelRow.createCell(7);excelCell.setCellValue(rs.getString("Tel_Nr"));
                 excelCell = excelRow.createCell(8);excelCell.setCellValue(rs.getString("Universitetas"));
                 excelCell = excelRow.createCell(9);excelCell.setCellValue(rs.getString("Fakultetas"));
                 excelCell = excelRow.createCell(10);excelCell.setCellValue(rs.getString("Grupe"));
                 excelCell = excelRow.createCell(11);excelCell.setCellValue(rs.getString("Studento_Nr"));
                 excelCell = excelRow.createCell(12);excelCell.setCellValue(rs.getString("Adresas"));
             
                       // excelCell.setCellValue("");
                    
                    z++;
                }



                 
                 }catch(Exception e){System.out.println(e.getMessage()); 
}           

                 
                 
                 
                 
//                for(int i=2;i<=z;i++){
//                    //Pirma dalis
//                    
//                    
//                    
//                    
//                    
//                     excelRow= excelSheet.createRow(i);
//                    for(int j=0;j<10;j++){
//                        // Antra dalis
//                        
//                         excelCell = excelRow.createCell(j);
//                       // excelCell.setCellValue("");
//                    }
//                    
//                }
                
                
               
                
                
                
                
                
                
                excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile()+".xlsx");
                excelBOU = new BufferedOutputStream(excelFOU);
                excelJTableExporter.write(excelBOU);
                JOptionPane.showMessageDialog(null, "Duomenys iškelti");
                
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }   catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                try {
                    
                    if (excelBOU!=null){
                       excelBOU.close(); 
                    }
                    
                    
                    
                    if (excelFOU!=null){
                       excelFOU.close(); 
                    }
                    
                    if (excelJTableExporter!=null){
                       excelJTableExporter.close(); 
                    }
                    
                    
                } catch (IOException ex) {//error?
                    ex.printStackTrace();
                }
            }
            
            
        }
        
        
        
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        //*************************************************************************
            FileOutputStream excelFOU = null;
            BufferedOutputStream excelBOU = null;
            XSSFWorkbook excelJTableExporter=null;
        
        
        JFileChooser excelFileChooser = new JFileChooser();
        excelFileChooser.setDialogTitle("Išsaugojama kaip");
     
        FileNameExtensionFilter fnef= new FileNameExtensionFilter("EXCEL FILES","xls","xlsx","xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);
        int z=2;
        if(excelChooser==JFileChooser.APPROVE_OPTION){
            
            
            //*************************************************************************
             
    
            
            
            
//**************************************************************************************
            try {
                
              excelJTableExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExporter.createSheet("Visi aktyvūs šokėjai");
                
                
                
                XSSFRow excelRow= excelSheet.createRow(0);
                 XSSFCell excelCell = excelRow.createCell(0);
                  excelCell.setCellValue("Šokėjai vaikinai:");
                  
                   excelRow= excelSheet.createRow(1);
                 excelCell = excelRow.createCell(0);excelCell.setCellValue("Metai");
                 excelCell = excelRow.createCell(1);excelCell.setCellValue("Vardas");
                 excelCell = excelRow.createCell(2);excelCell.setCellValue("Pavardė");
                 excelCell = excelRow.createCell(3);excelCell.setCellValue("Gimimo data");
                 excelCell = excelRow.createCell(4);excelCell.setCellValue("Ūgis");
                 excelCell = excelRow.createCell(5);excelCell.setCellValue("El. Paštas");
                 excelCell = excelRow.createCell(6);excelCell.setCellValue("Gimtasis Miestas");
                 excelCell = excelRow.createCell(7);excelCell.setCellValue("Telefono numeris");
                 excelCell = excelRow.createCell(8);excelCell.setCellValue("Universitetas");
                 excelCell = excelRow.createCell(9);excelCell.setCellValue("Fakultetas");
                 excelCell = excelRow.createCell(10);excelCell.setCellValue("Grupė");
                 excelCell = excelRow.createCell(11);excelCell.setCellValue("Studento numeris");
                 excelCell = excelRow.createCell(12);excelCell.setCellValue("Adresas");
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select metai,vardas,pavarde,gimimo_data,ugis,el_pastas,gimtasis_miestas,Tel_Nr,Universitetas,Fakultetas,Grupe,Studento_Nr,Adresas from dalyvis where Zanras = 'S' and Lytis='V' and Aktyvumas='1' order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);



 
                
                    
                    
    while (rs.next()){                
                    
                    
                     excelRow= excelSheet.createRow(z);

                 excelCell = excelRow.createCell(0);excelCell.setCellValue(rs.getString("Metai"));
                 excelCell = excelRow.createCell(1);excelCell.setCellValue(rs.getString("Vardas"));
                 excelCell = excelRow.createCell(2);excelCell.setCellValue(rs.getString("Pavarde"));
                 excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("gimimo_data"));
                 excelCell = excelRow.createCell(4);excelCell.setCellValue(rs.getString("ugis"));
                 excelCell = excelRow.createCell(5);excelCell.setCellValue(rs.getString("El_Pastas"));
                 excelCell = excelRow.createCell(6);excelCell.setCellValue(rs.getString("Gimtasis_miestas"));
                 excelCell = excelRow.createCell(7);excelCell.setCellValue(rs.getString("Tel_Nr"));
                 excelCell = excelRow.createCell(8);excelCell.setCellValue(rs.getString("Universitetas"));
                 excelCell = excelRow.createCell(9);excelCell.setCellValue(rs.getString("Fakultetas"));
                 excelCell = excelRow.createCell(10);excelCell.setCellValue(rs.getString("Grupe"));
                 excelCell = excelRow.createCell(11);excelCell.setCellValue(rs.getString("Studento_Nr"));
                 excelCell = excelRow.createCell(12);excelCell.setCellValue(rs.getString("Adresas"));
                       // excelCell.setCellValue("");
                    
                    z++;
                }



                 
                 }catch(Exception e){System.out.println(e.getMessage()); 
}           
z++;
excelRow= excelSheet.createRow(z);
                excelCell = excelRow.createCell(0);
                  excelCell.setCellValue("Šokėjos merginos:");   
                 z++;
                 
                      excelRow= excelSheet.createRow(z);
                 excelCell = excelRow.createCell(0);excelCell.setCellValue("Metai");
                 excelCell = excelRow.createCell(1);excelCell.setCellValue("Vardas");
                 excelCell = excelRow.createCell(2);excelCell.setCellValue("Pavardė");
                 excelCell = excelRow.createCell(3);excelCell.setCellValue("Gimimo data");
                 excelCell = excelRow.createCell(4);excelCell.setCellValue("Ūgis");
                 excelCell = excelRow.createCell(5);excelCell.setCellValue("El. Paštas");
                 excelCell = excelRow.createCell(6);excelCell.setCellValue("Gimtasis Miestas");
                 excelCell = excelRow.createCell(7);excelCell.setCellValue("Telefono numeris");
                 excelCell = excelRow.createCell(8);excelCell.setCellValue("Universitetas");
                 excelCell = excelRow.createCell(9);excelCell.setCellValue("Fakultetas");
                 excelCell = excelRow.createCell(10);excelCell.setCellValue("Grupė");
                 excelCell = excelRow.createCell(11);excelCell.setCellValue("Studento numeris");
                 excelCell = excelRow.createCell(12);excelCell.setCellValue("Adresas");
                 
                 z++;
                 
                 
                 
                 
                 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select metai,vardas,pavarde,gimimo_data,ugis,el_pastas,gimtasis_miestas,Tel_Nr,Universitetas,Fakultetas,Grupe,Studento_Nr,Adresas from dalyvis where Zanras = 'S' and Lytis='M' and Aktyvumas='1' order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);



 
                
                    
                    
    while (rs.next()){                
                    
                    
                     excelRow= excelSheet.createRow(z);

                 excelCell = excelRow.createCell(0);excelCell.setCellValue(rs.getString("Metai"));
                 excelCell = excelRow.createCell(1);excelCell.setCellValue(rs.getString("Vardas"));
                 excelCell = excelRow.createCell(2);excelCell.setCellValue(rs.getString("Pavarde"));
                 excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("gimimo_data"));
                 excelCell = excelRow.createCell(4);excelCell.setCellValue(rs.getString("ugis"));
                 excelCell = excelRow.createCell(5);excelCell.setCellValue(rs.getString("El_Pastas"));
                 excelCell = excelRow.createCell(6);excelCell.setCellValue(rs.getString("Gimtasis_miestas"));
                 excelCell = excelRow.createCell(7);excelCell.setCellValue(rs.getString("Tel_Nr"));
                 excelCell = excelRow.createCell(8);excelCell.setCellValue(rs.getString("Universitetas"));
                 excelCell = excelRow.createCell(9);excelCell.setCellValue(rs.getString("Fakultetas"));
                 excelCell = excelRow.createCell(10);excelCell.setCellValue(rs.getString("Grupe"));
                 excelCell = excelRow.createCell(11);excelCell.setCellValue(rs.getString("Studento_Nr"));
                 excelCell = excelRow.createCell(12);excelCell.setCellValue(rs.getString("Adresas"));
                       // excelCell.setCellValue("");
                    
                    z++;
                }



                 
                 }catch(Exception e){System.out.println(e.getMessage()); 
}
                 
                 z++;
                 
                 
                 
                 
                 
                 
                   excelRow= excelSheet.createRow(z);
                 excelCell = excelRow.createCell(0);
                  excelCell.setCellValue("Aktyvūs muzikantai:");
                  z++;
                   excelRow= excelSheet.createRow(z);
                 excelCell = excelRow.createCell(0);excelCell.setCellValue("Metai");
                 excelCell = excelRow.createCell(1);excelCell.setCellValue("Vardas");
                 excelCell = excelRow.createCell(2);excelCell.setCellValue("Pavardė");
                 excelCell = excelRow.createCell(3);excelCell.setCellValue("Gimimo data");
                 excelCell = excelRow.createCell(4);excelCell.setCellValue("Ūgis");
                 excelCell = excelRow.createCell(5);excelCell.setCellValue("El. Paštas");
                 excelCell = excelRow.createCell(6);excelCell.setCellValue("Gimtasis Miestas");
                 excelCell = excelRow.createCell(7);excelCell.setCellValue("Telefono numeris");
                 excelCell = excelRow.createCell(8);excelCell.setCellValue("Universitetas");
                 excelCell = excelRow.createCell(9);excelCell.setCellValue("Fakultetas");
                 excelCell = excelRow.createCell(10);excelCell.setCellValue("Grupė");
                 excelCell = excelRow.createCell(11);excelCell.setCellValue("Studento numeris");
                 excelCell = excelRow.createCell(12);excelCell.setCellValue("Adresas");
                 excelCell = excelRow.createCell(13);excelCell.setCellValue("Instrumentas");
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select metai,vardas,pavarde,gimimo_data,ugis,el_pastas,gimtasis_miestas,Tel_Nr,Universitetas,Fakultetas,Grupe,Studento_Nr,Adresas,Instrumentas from dalyvis where Zanras = 'M' and Aktyvumas='1' order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);



 
                z++;
                    
                    
    while (rs.next()){                
                    
                    
                     excelRow= excelSheet.createRow(z);

                 excelCell = excelRow.createCell(0);excelCell.setCellValue(rs.getString("Metai"));
                 excelCell = excelRow.createCell(1);excelCell.setCellValue(rs.getString("Vardas"));
                 excelCell = excelRow.createCell(2);excelCell.setCellValue(rs.getString("Pavarde"));
                 excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("gimimo_data"));
                 excelCell = excelRow.createCell(4);excelCell.setCellValue(rs.getString("ugis"));
                 excelCell = excelRow.createCell(5);excelCell.setCellValue(rs.getString("El_Pastas"));
                 excelCell = excelRow.createCell(6);excelCell.setCellValue(rs.getString("Gimtasis_miestas"));
                 excelCell = excelRow.createCell(7);excelCell.setCellValue(rs.getString("Tel_Nr"));
                 excelCell = excelRow.createCell(8);excelCell.setCellValue(rs.getString("Universitetas"));
                 excelCell = excelRow.createCell(9);excelCell.setCellValue(rs.getString("Fakultetas"));
                 excelCell = excelRow.createCell(10);excelCell.setCellValue(rs.getString("Grupe"));
                 excelCell = excelRow.createCell(11);excelCell.setCellValue(rs.getString("Studento_Nr"));
                 excelCell = excelRow.createCell(12);excelCell.setCellValue(rs.getString("Adresas"));
                 excelCell = excelRow.createCell(13);excelCell.setCellValue(rs.getString("Instrumentas"));
                       // excelCell.setCellValue("");
                    
                    z++;
                }



                 
                 }catch(Exception e){System.out.println(e.getMessage()); 
}
               
                 
                 
                 z++;
                 
                 
                   excelRow= excelSheet.createRow(z);
                  excelCell = excelRow.createCell(0);
                  excelCell.setCellValue("Aktyvūs dainininkai:");
                  z++;
                   excelRow= excelSheet.createRow(z);
                 excelCell = excelRow.createCell(0);excelCell.setCellValue("Metai");
                 excelCell = excelRow.createCell(1);excelCell.setCellValue("Vardas");
                 excelCell = excelRow.createCell(2);excelCell.setCellValue("Pavardė");
                 excelCell = excelRow.createCell(3);excelCell.setCellValue("Gimimo data");
                 excelCell = excelRow.createCell(4);excelCell.setCellValue("Ūgis");
                 excelCell = excelRow.createCell(5);excelCell.setCellValue("El. Paštas");
                 excelCell = excelRow.createCell(6);excelCell.setCellValue("Gimtasis Miestas");
                 excelCell = excelRow.createCell(7);excelCell.setCellValue("Telefono numeris");
                 excelCell = excelRow.createCell(8);excelCell.setCellValue("Universitetas");
                 excelCell = excelRow.createCell(9);excelCell.setCellValue("Fakultetas");
                 excelCell = excelRow.createCell(10);excelCell.setCellValue("Grupė");
                 excelCell = excelRow.createCell(11);excelCell.setCellValue("Studento numeris");
                 excelCell = excelRow.createCell(12);excelCell.setCellValue("Adresas");
                
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select metai,vardas,pavarde,gimimo_data,ugis,el_pastas,gimtasis_miestas,Tel_Nr,Universitetas,Fakultetas,Grupe,Studento_Nr,Adresas from dalyvis where Zanras = 'D' and Aktyvumas='1' order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);



 
                
                    z++;
                    
    while (rs.next()){                
                    
                    
                     excelRow= excelSheet.createRow(z);

                 excelCell = excelRow.createCell(0);excelCell.setCellValue(rs.getString("Metai"));
                 excelCell = excelRow.createCell(1);excelCell.setCellValue(rs.getString("Vardas"));
                 excelCell = excelRow.createCell(2);excelCell.setCellValue(rs.getString("Pavarde"));
                 excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("gimimo_data"));
                 excelCell = excelRow.createCell(4);excelCell.setCellValue(rs.getString("ugis"));
                 excelCell = excelRow.createCell(5);excelCell.setCellValue(rs.getString("El_Pastas"));
                 excelCell = excelRow.createCell(6);excelCell.setCellValue(rs.getString("Gimtasis_miestas"));
                 excelCell = excelRow.createCell(7);excelCell.setCellValue(rs.getString("Tel_Nr"));
                 excelCell = excelRow.createCell(8);excelCell.setCellValue(rs.getString("Universitetas"));
                 excelCell = excelRow.createCell(9);excelCell.setCellValue(rs.getString("Fakultetas"));
                 excelCell = excelRow.createCell(10);excelCell.setCellValue(rs.getString("Grupe"));
                 excelCell = excelRow.createCell(11);excelCell.setCellValue(rs.getString("Studento_Nr"));
                 excelCell = excelRow.createCell(12);excelCell.setCellValue(rs.getString("Adresas"));
             
                       // excelCell.setCellValue("");
                    
                    z++;
                }



                 
                 }catch(Exception e){System.out.println(e.getMessage()); 
}    
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
//                for(int i=2;i<=z;i++){
//                    //Pirma dalis
//                    
//                    
//                    
//                    
//                    
//                     excelRow= excelSheet.createRow(i);
//                    for(int j=0;j<10;j++){
//                        // Antra dalis
//                        
//                         excelCell = excelRow.createCell(j);
//                       // excelCell.setCellValue("");
//                    }
//                    
//                }
                
                
               
                
                
                
                
                
                
                excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile()+".xlsx");
                excelBOU = new BufferedOutputStream(excelFOU);
                excelJTableExporter.write(excelBOU);
                JOptionPane.showMessageDialog(null, "Duomenys iškelti");
                
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }   catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                try {
                    
                    if (excelBOU!=null){
                       excelBOU.close(); 
                    }
                    
                    
                    
                    if (excelFOU!=null){
                       excelFOU.close(); 
                    }
                    
                    if (excelJTableExporter!=null){
                       excelJTableExporter.close(); 
                    }
                    
                    
                } catch (IOException ex) {//error?
                    ex.printStackTrace();
                }
            }
            
            
        }
        
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        
          //*************************************************************************
            FileOutputStream excelFOU = null;
            BufferedOutputStream excelBOU = null;
            XSSFWorkbook excelJTableExporter=null;
        
        
        JFileChooser excelFileChooser = new JFileChooser();
        excelFileChooser.setDialogTitle("Išsaugojama kaip");
     
        FileNameExtensionFilter fnef= new FileNameExtensionFilter("EXCEL FILES","xls","xlsx","xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);
        int z=2;
        if(excelChooser==JFileChooser.APPROVE_OPTION){
            
            
            //*************************************************************************
             
    
            
            
            
//**************************************************************************************
            try {
                
              excelJTableExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExporter.createSheet("Visi aktyvūs šokėjai");
                
                
                
                XSSFRow excelRow= excelSheet.createRow(0);
                 XSSFCell excelCell = excelRow.createCell(0);
                  excelCell.setCellValue("Šokėjai vaikinai:");
                  
                   excelRow= excelSheet.createRow(1);
                 excelCell = excelRow.createCell(0);excelCell.setCellValue("Metai");
                 excelCell = excelRow.createCell(1);excelCell.setCellValue("Vardas");
                 excelCell = excelRow.createCell(2);excelCell.setCellValue("Pavardė");
                 excelCell = excelRow.createCell(3);excelCell.setCellValue("Gimimo data");
                 excelCell = excelRow.createCell(4);excelCell.setCellValue("Ūgis");
                 excelCell = excelRow.createCell(5);excelCell.setCellValue("El. Paštas");
                 excelCell = excelRow.createCell(6);excelCell.setCellValue("Gimtasis Miestas");
                 excelCell = excelRow.createCell(7);excelCell.setCellValue("Telefono numeris");
                 excelCell = excelRow.createCell(8);excelCell.setCellValue("Universitetas");
                 excelCell = excelRow.createCell(9);excelCell.setCellValue("Fakultetas");
                 excelCell = excelRow.createCell(10);excelCell.setCellValue("Grupė");
                 excelCell = excelRow.createCell(11);excelCell.setCellValue("Studento numeris");
                 excelCell = excelRow.createCell(12);excelCell.setCellValue("Adresas");
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select metai,vardas,pavarde,gimimo_data,ugis,el_pastas,gimtasis_miestas,Tel_Nr,Universitetas,Fakultetas,Grupe,Studento_Nr,Adresas from dalyvis where Zanras = 'S' and Lytis='V'  order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);



 
                
                    
                    
    while (rs.next()){                
                    
                    
                     excelRow= excelSheet.createRow(z);

                 excelCell = excelRow.createCell(0);excelCell.setCellValue(rs.getString("Metai"));
                 excelCell = excelRow.createCell(1);excelCell.setCellValue(rs.getString("Vardas"));
                 excelCell = excelRow.createCell(2);excelCell.setCellValue(rs.getString("Pavarde"));
                 excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("gimimo_data"));
                 excelCell = excelRow.createCell(4);excelCell.setCellValue(rs.getString("ugis"));
                 excelCell = excelRow.createCell(5);excelCell.setCellValue(rs.getString("El_Pastas"));
                 excelCell = excelRow.createCell(6);excelCell.setCellValue(rs.getString("Gimtasis_miestas"));
                 excelCell = excelRow.createCell(7);excelCell.setCellValue(rs.getString("Tel_Nr"));
                 excelCell = excelRow.createCell(8);excelCell.setCellValue(rs.getString("Universitetas"));
                 excelCell = excelRow.createCell(9);excelCell.setCellValue(rs.getString("Fakultetas"));
                 excelCell = excelRow.createCell(10);excelCell.setCellValue(rs.getString("Grupe"));
                 excelCell = excelRow.createCell(11);excelCell.setCellValue(rs.getString("Studento_Nr"));
                 excelCell = excelRow.createCell(12);excelCell.setCellValue(rs.getString("Adresas"));
                       // excelCell.setCellValue("");
                    
                    z++;
                }



                 
                 }catch(Exception e){System.out.println(e.getMessage()); 
}           
z++;
excelRow= excelSheet.createRow(z);
                excelCell = excelRow.createCell(0);
                  excelCell.setCellValue("Šokėjos merginos:");   
                 z++;
                 
                      excelRow= excelSheet.createRow(z);
                 excelCell = excelRow.createCell(0);excelCell.setCellValue("Metai");
                 excelCell = excelRow.createCell(1);excelCell.setCellValue("Vardas");
                 excelCell = excelRow.createCell(2);excelCell.setCellValue("Pavardė");
                 excelCell = excelRow.createCell(3);excelCell.setCellValue("Gimimo data");
                 excelCell = excelRow.createCell(4);excelCell.setCellValue("Ūgis");
                 excelCell = excelRow.createCell(5);excelCell.setCellValue("El. Paštas");
                 excelCell = excelRow.createCell(6);excelCell.setCellValue("Gimtasis Miestas");
                 excelCell = excelRow.createCell(7);excelCell.setCellValue("Telefono numeris");
                 excelCell = excelRow.createCell(8);excelCell.setCellValue("Universitetas");
                 excelCell = excelRow.createCell(9);excelCell.setCellValue("Fakultetas");
                 excelCell = excelRow.createCell(10);excelCell.setCellValue("Grupė");
                 excelCell = excelRow.createCell(11);excelCell.setCellValue("Studento numeris");
                 excelCell = excelRow.createCell(12);excelCell.setCellValue("Adresas");
                 
                 z++;
                 
                 
                 
                 
                 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select metai,vardas,pavarde,gimimo_data,ugis,el_pastas,gimtasis_miestas,Tel_Nr,Universitetas,Fakultetas,Grupe,Studento_Nr,Adresas from dalyvis where Zanras = 'S' and Lytis='M'  order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);



 
                
                    
                    
    while (rs.next()){                
                    
                    
                     excelRow= excelSheet.createRow(z);

                 excelCell = excelRow.createCell(0);excelCell.setCellValue(rs.getString("Metai"));
                 excelCell = excelRow.createCell(1);excelCell.setCellValue(rs.getString("Vardas"));
                 excelCell = excelRow.createCell(2);excelCell.setCellValue(rs.getString("Pavarde"));
                 excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("gimimo_data"));
                 excelCell = excelRow.createCell(4);excelCell.setCellValue(rs.getString("ugis"));
                 excelCell = excelRow.createCell(5);excelCell.setCellValue(rs.getString("El_Pastas"));
                 excelCell = excelRow.createCell(6);excelCell.setCellValue(rs.getString("Gimtasis_miestas"));
                 excelCell = excelRow.createCell(7);excelCell.setCellValue(rs.getString("Tel_Nr"));
                 excelCell = excelRow.createCell(8);excelCell.setCellValue(rs.getString("Universitetas"));
                 excelCell = excelRow.createCell(9);excelCell.setCellValue(rs.getString("Fakultetas"));
                 excelCell = excelRow.createCell(10);excelCell.setCellValue(rs.getString("Grupe"));
                 excelCell = excelRow.createCell(11);excelCell.setCellValue(rs.getString("Studento_Nr"));
                 excelCell = excelRow.createCell(12);excelCell.setCellValue(rs.getString("Adresas"));
                       // excelCell.setCellValue("");
                    
                    z++;
                }



                 
                 }catch(Exception e){System.out.println(e.getMessage()); 
}
                 
                 z++;
                 
                 
                 
                 
                 
                 
                   excelRow= excelSheet.createRow(z);
                 excelCell = excelRow.createCell(0);
                  excelCell.setCellValue("Muzikantai:");
                  z++;
                   excelRow= excelSheet.createRow(z);
                 excelCell = excelRow.createCell(0);excelCell.setCellValue("Metai");
                 excelCell = excelRow.createCell(1);excelCell.setCellValue("Vardas");
                 excelCell = excelRow.createCell(2);excelCell.setCellValue("Pavardė");
                 excelCell = excelRow.createCell(3);excelCell.setCellValue("Gimimo data");
                 excelCell = excelRow.createCell(4);excelCell.setCellValue("Ūgis");
                 excelCell = excelRow.createCell(5);excelCell.setCellValue("El. Paštas");
                 excelCell = excelRow.createCell(6);excelCell.setCellValue("Gimtasis Miestas");
                 excelCell = excelRow.createCell(7);excelCell.setCellValue("Telefono numeris");
                 excelCell = excelRow.createCell(8);excelCell.setCellValue("Universitetas");
                 excelCell = excelRow.createCell(9);excelCell.setCellValue("Fakultetas");
                 excelCell = excelRow.createCell(10);excelCell.setCellValue("Grupė");
                 excelCell = excelRow.createCell(11);excelCell.setCellValue("Studento numeris");
                 excelCell = excelRow.createCell(12);excelCell.setCellValue("Adresas");
                 excelCell = excelRow.createCell(13);excelCell.setCellValue("Instrumentas");
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select metai,vardas,pavarde,gimimo_data,ugis,el_pastas,gimtasis_miestas,Tel_Nr,Universitetas,Fakultetas,Grupe,Studento_Nr,Adresas,Instrumentas from dalyvis where Zanras = 'M'  order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);



 
                z++;
                    
                    
    while (rs.next()){                
                    
                    
                     excelRow= excelSheet.createRow(z);

                 excelCell = excelRow.createCell(0);excelCell.setCellValue(rs.getString("Metai"));
                 excelCell = excelRow.createCell(1);excelCell.setCellValue(rs.getString("Vardas"));
                 excelCell = excelRow.createCell(2);excelCell.setCellValue(rs.getString("Pavarde"));
                 excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("gimimo_data"));
                 excelCell = excelRow.createCell(4);excelCell.setCellValue(rs.getString("ugis"));
                 excelCell = excelRow.createCell(5);excelCell.setCellValue(rs.getString("El_Pastas"));
                 excelCell = excelRow.createCell(6);excelCell.setCellValue(rs.getString("Gimtasis_miestas"));
                 excelCell = excelRow.createCell(7);excelCell.setCellValue(rs.getString("Tel_Nr"));
                 excelCell = excelRow.createCell(8);excelCell.setCellValue(rs.getString("Universitetas"));
                 excelCell = excelRow.createCell(9);excelCell.setCellValue(rs.getString("Fakultetas"));
                 excelCell = excelRow.createCell(10);excelCell.setCellValue(rs.getString("Grupe"));
                 excelCell = excelRow.createCell(11);excelCell.setCellValue(rs.getString("Studento_Nr"));
                 excelCell = excelRow.createCell(12);excelCell.setCellValue(rs.getString("Adresas"));
                 excelCell = excelRow.createCell(13);excelCell.setCellValue(rs.getString("Instrumentas"));
                       // excelCell.setCellValue("");
                    
                    z++;
                }



                 
                 }catch(Exception e){System.out.println(e.getMessage()); 
}
               
                 
                 
                 z++;
                 
                 
                   excelRow= excelSheet.createRow(z);
                  excelCell = excelRow.createCell(0);
                  excelCell.setCellValue("Dainininkai:");
                  z++;
                   excelRow= excelSheet.createRow(z);
                 excelCell = excelRow.createCell(0);excelCell.setCellValue("Metai");
                 excelCell = excelRow.createCell(1);excelCell.setCellValue("Vardas");
                 excelCell = excelRow.createCell(2);excelCell.setCellValue("Pavardė");
                 excelCell = excelRow.createCell(3);excelCell.setCellValue("Gimimo data");
                 excelCell = excelRow.createCell(4);excelCell.setCellValue("Ūgis");
                 excelCell = excelRow.createCell(5);excelCell.setCellValue("El. Paštas");
                 excelCell = excelRow.createCell(6);excelCell.setCellValue("Gimtasis Miestas");
                 excelCell = excelRow.createCell(7);excelCell.setCellValue("Telefono numeris");
                 excelCell = excelRow.createCell(8);excelCell.setCellValue("Universitetas");
                 excelCell = excelRow.createCell(9);excelCell.setCellValue("Fakultetas");
                 excelCell = excelRow.createCell(10);excelCell.setCellValue("Grupė");
                 excelCell = excelRow.createCell(11);excelCell.setCellValue("Studento numeris");
                 excelCell = excelRow.createCell(12);excelCell.setCellValue("Adresas");
                
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();


String sqlrequest = "select metai,vardas,pavarde,gimimo_data,ugis,el_pastas,gimtasis_miestas,Tel_Nr,Universitetas,Fakultetas,Grupe,Studento_Nr,Adresas from dalyvis where Zanras = 'D'  order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);



 
                
                    z++;
                    
    while (rs.next()){                
                    
                    
                     excelRow= excelSheet.createRow(z);

                 excelCell = excelRow.createCell(0);excelCell.setCellValue(rs.getString("Metai"));
                 excelCell = excelRow.createCell(1);excelCell.setCellValue(rs.getString("Vardas"));
                 excelCell = excelRow.createCell(2);excelCell.setCellValue(rs.getString("Pavarde"));
                 excelCell = excelRow.createCell(3);excelCell.setCellValue(rs.getString("gimimo_data"));
                 excelCell = excelRow.createCell(4);excelCell.setCellValue(rs.getString("ugis"));
                 excelCell = excelRow.createCell(5);excelCell.setCellValue(rs.getString("El_Pastas"));
                 excelCell = excelRow.createCell(6);excelCell.setCellValue(rs.getString("Gimtasis_miestas"));
                 excelCell = excelRow.createCell(7);excelCell.setCellValue(rs.getString("Tel_Nr"));
                 excelCell = excelRow.createCell(8);excelCell.setCellValue(rs.getString("Universitetas"));
                 excelCell = excelRow.createCell(9);excelCell.setCellValue(rs.getString("Fakultetas"));
                 excelCell = excelRow.createCell(10);excelCell.setCellValue(rs.getString("Grupe"));
                 excelCell = excelRow.createCell(11);excelCell.setCellValue(rs.getString("Studento_Nr"));
                 excelCell = excelRow.createCell(12);excelCell.setCellValue(rs.getString("Adresas"));
             
                       // excelCell.setCellValue("");
                    
                    z++;
                }



                 
                 }catch(Exception e){System.out.println(e.getMessage()); 
}    
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
//                for(int i=2;i<=z;i++){
//                    //Pirma dalis
//                    
//                    
//                    
//                    
//                    
//                     excelRow= excelSheet.createRow(i);
//                    for(int j=0;j<10;j++){
//                        // Antra dalis
//                        
//                         excelCell = excelRow.createCell(j);
//                       // excelCell.setCellValue("");
//                    }
//                    
//                }
                
                
               
                
                
                
                
                
                
                excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile()+".xlsx");
                excelBOU = new BufferedOutputStream(excelFOU);
                excelJTableExporter.write(excelBOU);
                JOptionPane.showMessageDialog(null, "Duomenys iškelti");
                
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }   catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                try {
                    
                    if (excelBOU!=null){
                       excelBOU.close(); 
                    }
                    
                    
                    
                    if (excelFOU!=null){
                       excelFOU.close(); 
                    }
                    
                    if (excelJTableExporter!=null){
                       excelJTableExporter.close(); 
                    }
                    
                    
                } catch (IOException ex) {//error?
                    ex.printStackTrace();
                }
            }
            
            
        }
        
     
        
    
        
        
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
    
         new DBinsert (null, true).show();
        
        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         if(Status.Option.equals("Koncertai")){
            try {
                findKoncertoID();
                Status.Action="update";
       
            new skInsertField (null, true).show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nepasirinkote šokio!");
        }
        
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
          try {
              if(Status.Option.equals("Koncertai")){
                int row = jTable1.getSelectedRow();
        String Pav =  jTable1.getModel().getValueAt(row, 0).toString();
            findKoncertoID();
            createKoncertasExcel();
              }
              else JOptionPane.showMessageDialog(null, "Nepasirinkote koncerto iš koncertų skilties");
        } catch (Exception e) {
              System.out.println("nepavyko");
        }
        
    }//GEN-LAST:event_jMenuItem8ActionPerformed

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
            java.util.logging.Logger.getLogger(Pagrindinis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pagrindinis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pagrindinis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pagrindinis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Pagrindinis dialog = new Pagrindinis(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBoxMenuItem jCheckBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    public static javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton10;
    private javax.swing.JToggleButton jToggleButton11;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JToggleButton jToggleButton9;
    // End of variables declaration//GEN-END:variables
}
