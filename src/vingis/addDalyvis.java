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
import static vingis.Pagrindinis.jTable1;

/**
 *
 * @author TwoTap
 */
public class addDalyvis extends javax.swing.JDialog {

    /**
     * Creates new form addDalyvis
     */
    public addDalyvis(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        //Set ICON
        if (Status.Option.equals("Sokejai")){logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vingis/images/rsz_dance.png")));}
        else if (Status.Option.equals("Muzikantai")){logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vingis/images/rsz_musician.png")));}
        else if (Status.Option.equals("Dainininkai")){logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vingis/images/rsz_singer.png")));}
        showInstrumentai();
        if(Status.Action.equals("Add"))jButton1.setText("Pridėti");
        if(Status.Action.equals("Update"))jButton1.setText("Atnaujinti");
        
        if(Status.Action.equals("Update"))fillFields();
        
        
    }
String dID = "";
    public void setSokejaiTableInfo(){
try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();
String aktyvumoCheck= "";
    if (jCheckBox1.isSelected()){aktyvumoCheck="and Aktyvumas=1";}
    else aktyvumoCheck = "";

    
    
    
String sqlrequest = "select metai,vardas,pavarde,gimimo_data,el_pastas,Tel_Nr from dalyvis where Zanras = 'S' "+aktyvumoCheck+" "+Status.Lytis+"  order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String metai = String.valueOf(rs.getInt("metai"));
    String vardas = rs.getString("vardas");
    String pavarde = rs.getString("pavarde");
    String gimimoData = rs.getString("gimimo_data");
    String elPastas = rs.getString("el_pastas");
    String gimtasisMiestas = rs.getString("Tel_Nr");
    
String tbData[] = {metai,vardas,pavarde,gimimoData,elPastas,gimtasisMiestas};
DefaultTableModel tblModel = (DefaultTableModel)Pagrindinis.jTable1.getModel();

tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    void showInstrumentai(){
        if (Status.Option.equals("Muzikantai")){instrLabel.setVisible(true); instrText.setVisible(true);}
        else {instrLabel.setVisible(false); instrText.setVisible(false);}
    }
    
    void fillFields() {
        try {
            String url = "jdbc:sqlite:vingisdb.db";
            Connection con = DriverManager.getConnection(url);

            String query = "select DalyvioID,Aktyvumas,Metai,Vardas,Pavarde,Lytis,Zanras,Gimimo_data,Ugis,El_Pastas,Gimtasis_Miestas,Tel_Nr,Universitetas,Fakultetas,Grupe,Studento_Nr,Adresas,Instrumentas from  Dalyvis where Metai = '" + Status.Metai + "' and Vardas ='" + Status.Vardas + "' and Pavarde='" + Status.Pavarde + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            String Aktyvumas = "", Metai= "", Vardas= "", Pavarde= "", Lytis= "", Zanras= "", Gimimo_data= "", Ugis= "", El_Pastas= "", Gimtasis_Miestas= "", Tel_Nr= "", Universitetas= "", Fakultetas= "", Grupe= "", Studento_Nr= "", Adresas= "", Instrumentas = "";
            while (rs.next()) {
                dID=rs.getString("DalyvioID");
                Aktyvumas = rs.getString("Aktyvumas");
                Metai = rs.getString("Metai");
                Vardas = rs.getString("Vardas");
                Pavarde = rs.getString("Pavarde");
                Lytis = rs.getString("Lytis");
                Zanras = rs.getString("Zanras");
                Gimimo_data = rs.getString("Gimimo_Data");
                Ugis = rs.getString("Ugis");
                El_Pastas = rs.getString("El_Pastas");
                Gimtasis_Miestas = rs.getString("Gimtasis_Miestas");
                Tel_Nr = rs.getString("Tel_Nr");
                Universitetas = rs.getString("Universitetas");
                Fakultetas = rs.getString("Fakultetas");
                Grupe = rs.getString("Grupe");
                Studento_Nr = rs.getString("Studento_Nr");
                Adresas = rs.getString("Adresas");
                Instrumentas = rs.getString("Instrumentas");
            }
            
            if(Aktyvumas.equals("1")){jCheckBox1.setSelected(rootPaneCheckingEnabled);}
            jTextField1.setText(Metai);
            if(Lytis.equals("V")){jRadioButton1.setSelected(rootPaneCheckingEnabled);}
            else if(Lytis.equals("M")){jRadioButton2.setSelected(rootPaneCheckingEnabled);}
            
            jTextField2.setText(Vardas);
            jTextField3.setText(Pavarde);
            jTextField5.setText(El_Pastas);
            jTextField4.setText(Gimimo_data);
            jTextField6.setText(Gimtasis_Miestas);
            instrText.setText(Instrumentas);
            jTextField8.setText(Ugis);
            jTextField9.setText(Tel_Nr);
            jTextField11.setText(Universitetas);
            jTextField10.setText(Fakultetas);
            jTextField12.setText(Grupe);
            jTextField14.setText(Studento_Nr);
            jTextField13.setText(Adresas);
           
            
            
        
        
    }catch (Exception e) {
        JOptionPane.showMessageDialog(null,e);
    }
    }
    
    
    
    
    public void setMuzikantaiTableInfo(){
try{
String url = "jdbc:sqlite:vingisdb.db";
Connection con = DriverManager.getConnection(url);
Statement st = con.createStatement();

String aktyvumoCheck= "";
    if (jCheckBox1.isSelected()){aktyvumoCheck="and Aktyvumas=1";}
    else aktyvumoCheck = "";

String sqlrequest = "select metai,vardas,pavarde,gimimo_data,el_pastas,gimtasis_miestas,Instrumentas from dalyvis where  Zanras = 'M'"+aktyvumoCheck+" "+Status.Lytis+" order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String metai = String.valueOf(rs.getInt("metai"));
    String vardas = rs.getString("vardas");
    String pavarde = rs.getString("pavarde");
    String gimimoData = rs.getString("gimimo_data");
    String elPastas = rs.getString("el_pastas");
    String gimtasisMiestas = rs.getString("gimtasis_miestas");
    String instrumentas = rs.getString("Instrumentas");
    
String tbData[] = {metai,vardas,pavarde,gimimoData,elPastas,gimtasisMiestas,instrumentas};
DefaultTableModel tblModel = (DefaultTableModel)Pagrindinis.jTable1.getModel();

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

String sqlrequest = "select metai,vardas,pavarde,gimimo_data,el_pastas,gimtasis_miestas from dalyvis where  Zanras = 'D' "+aktyvumoCheck +"  "+Status.Lytis+"order by metai desc, vardas asc, pavarde asc";
ResultSet rs = st.executeQuery(sqlrequest);

while (rs.next()){
    String metai = String.valueOf(rs.getInt("metai"));
    String vardas = rs.getString("vardas");
    String pavarde = rs.getString("pavarde");
    String gimimoData = rs.getString("gimimo_data");
    String elPastas = rs.getString("el_pastas");
    String gimtasisMiestas = rs.getString("gimtasis_miestas");
    
String tbData[] = {metai,vardas,pavarde,gimimoData,elPastas,gimtasisMiestas};
DefaultTableModel tblModel = (DefaultTableModel)Pagrindinis.jTable1.getModel();

tblModel.addRow(tbData);

}
}
catch(Exception e){System.out.println(e.getMessage()); 
}}  
    
    
    
    
    
    
    
    
    
    void updateMember(){ try {
            String url = "jdbc:sqlite:vingisdb.db";
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            
            
            
            String metai = jTextField1.getText();
        String vardas = "'" + jTextField2.getText() + "'";
        String pavarde = "'" + jTextField3.getText() + "'";
        String elPastas = "'" + jTextField5.getText() + "'";
        String gimData = "'" + jTextField4.getText() + "'";
        String gimMiestas = "'" + jTextField6.getText() + "'";
        String instrumentas = "''";
        String ugis = "'" + jTextField8.getText() + "'";
        String telNr = "'" + jTextField9.getText() + "'";
        String universitetas = "'" + jTextField11.getText() + "'";
        String fakultetas = "'" + jTextField10.getText() + "'";
        String grupe = "'" + jTextField12.getText() + "'";
        String studentoNr = "'" + jTextField14.getText() + "'";
        String adresas = "'" + jTextField13.getText() + "'";
        
        String lytis = "";
        String zanras = "";
        Boolean aktyvumas;
        
        if (Status.Option.equals("Sokejai")) {
            zanras = "'S'";
        } else if (Status.Option.equals("Dainininkai")) {
            zanras = "'D'";
        } else if (Status.Option.equals("Muzikantai")) {
            zanras = "'M'";
        }
        
        if (jRadioButton1.isSelected()) {
            lytis = "'V'";
        } else if (jRadioButton2.isSelected()) {
            lytis = "'M'";
        }
        
        if (jCheckBox1.isSelected()) {
            aktyvumas = true;
        } else {
            aktyvumas = false;
        }        
        
        if (Status.Option.equals("Muzikantai")) {
            instrumentas = "'" + instrText.getText() + "'";
        }
            
            
            
            
            
            
            
            String sqlrequest = "Update Dalyvis set aktyvumas= "+aktyvumas+",Metai = "+metai+",Vardas= "+vardas+",Pavarde="+pavarde+",Lytis="+lytis+",Zanras="+zanras
                    +",Gimimo_data="+gimData+",El_Pastas="+elPastas+",Gimtasis_Miestas="+gimMiestas+",Instrumentas="+instrumentas+
                    ",Ugis="+ugis+",Tel_Nr="+telNr+",Universitetas="+universitetas+",Grupe="+grupe+",Fakultetas="+fakultetas+",Studento_Nr="+studentoNr+",Adresas="+adresas+" where DalyvioID="+dID+"";
            
            st.executeUpdate(sqlrequest);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());            
        }
        this.dispose();
    
        
        
        
        
    }
    void addMember(){
        String metai = jTextField1.getText();
        String vardas = "'" + jTextField2.getText() + "'";
        String pavarde = "'" + jTextField3.getText() + "'";
        String elPastas = "'" + jTextField5.getText() + "'";
        String gimData = "'" + jTextField4.getText() + "'";
        String gimMiestas = "'" + jTextField6.getText() + "'";
        String instrumentas = "''";
        String ugis = "'" + jTextField8.getText() + "'";
        String telNr = "'" + jTextField9.getText() + "'";
        String universitetas = "'" + jTextField11.getText() + "'";
        String fakultetas = "'" + jTextField10.getText() + "'";
        String grupe = "'" + jTextField12.getText() + "'";
        String studentoNr = "'" + jTextField14.getText() + "'";
        String adresas = "'" + jTextField13.getText() + "'";
        
        String lytis = "";
        String zanras = "";
        Boolean aktyvumas;
        
        if (Status.Option.equals("Sokejai")) {
            zanras = "'S'";
        } else if (Status.Option.equals("Dainininkai")) {
            zanras = "'D'";
        } else if (Status.Option.equals("Muzikantai")) {
            zanras = "'M'";
        }
        
        if (jRadioButton1.isSelected()) {
            lytis = "'V'";
        } else if (jRadioButton2.isSelected()) {
            lytis = "'M'";
        }
        
        if (jCheckBox1.isSelected()) {
            aktyvumas = true;
        } else {
            aktyvumas = false;
        }        
        
        if (Status.Option.equals("Muzikantai")) {
            instrumentas = "'" + instrText.getText() + "'";
        }
        
        try {
            String url = "jdbc:sqlite:vingisdb.db";
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            String sqlrequest = "INSERT INTO Dalyvis (Aktyvumas,Metai,Vardas,Pavarde,Lytis,Zanras,Gimimo_data,El_Pastas,Gimtasis_Miestas,Instrumentas,Ugis,Tel_Nr,Universitetas,Fakultetas,Grupe,Studento_Nr,Adresas)VALUES ("
                    + aktyvumas + "," + metai + "," + vardas + "," + pavarde + "," + lytis + "," + zanras + "," + gimData + "," + elPastas + "," + gimMiestas + "," + instrumentas + "," + ugis + "," + telNr + "," + universitetas + "," + fakultetas + "," + grupe + "," + studentoNr + "," + adresas + ")";
            
            st.executeUpdate(sqlrequest);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());            
        }
        this.dispose();
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
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        instrLabel = new javax.swing.JLabel();
        instrText = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 520));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                openPagrindinis(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel1.setText("Vardas*:");

        jLabel2.setText("Prisijungimo metai*:");

        jLabel3.setText("Pavardė*:");

        jLabel4.setText("Pagrindinė dalyvio informacija:");

        jLabel5.setText("El. Paštas");

        jLabel6.setText("Gimimo data:");

        jLabel7.setText("Gimtasis miestas:");

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Vaikinas");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Mergina");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vingis/images/rsz_mergina.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vingis/images/rsz_vaikinas.png"))); // NOI18N

        jLabel8.setText("Papildoma dalyvio informacija:");

        jLabel11.setText("Tel. Nr.:");

        jLabel12.setText("Universitetas:");

        jLabel13.setText("Fakultetas:");

        jLabel14.setText("Ūgis:");

        jCheckBox1.setText("Aktyvumas");
        jCheckBox1.setToolTipText("");

        jLabel15.setText("Grupė:");

        jLabel16.setText("Studento Nr.:");

        jLabel17.setText("Adresas:");

        instrLabel.setText("Instrumentas:");

        jButton1.setText("Pridėti");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Atšaukti");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vingis/images/rsz_dance.png"))); // NOI18N

        jLabel18.setText("Lytis*:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(34, 123, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel18)
                                        .addGap(31, 31, 31)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jRadioButton1))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jRadioButton2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(instrLabel)
                                    .addComponent(jLabel11))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField4)
                                    .addComponent(jTextField5)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(instrText)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBox1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                                    .addComponent(jTextField9))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(288, 288, 288)
                        .addComponent(logo))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField6)
                            .addComponent(jTextField11)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4)
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(instrLabel)
                    .addComponent(instrText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel8)
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logo)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void openPagrindinis(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_openPagrindinis
        // Open Pagrindinis
        dispose();
        
        
    }//GEN-LAST:event_openPagrindinis

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Add member button
     if(Status.Action.equals("Add"))addMember();
     else if (Status.Action.equals("Update"))updateMember();
        this.dispose();
        DefaultTableModel model = (DefaultTableModel) Pagrindinis.jTable1.getModel();
         model.setRowCount(0);
        if (Status.Option.equals("Sokejai")) setSokejaiTableInfo();
        if(Status.Option.equals("Dainininkai")) setDainininkaiTableInfo();
        if(Status.Option.equals("Muzikantai")) setMuzikantaiTableInfo();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Atšaukti button
       this.dispose(); 
       
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(addDalyvis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addDalyvis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addDalyvis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addDalyvis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                addDalyvis dialog = new addDalyvis(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel instrLabel;
    private javax.swing.JTextField instrText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}
