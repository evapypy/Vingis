/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vingis;

/**
 *
 * @author TwoTap
 */
public class Status{
    //Login
    public static String loginName;
    public static String changeWhat;
    //ID
    public static String SokioID;
    public static String KoncertoID;
    public static String DalyvioID;
    
    //Visi option
    public static String Option;
    public static String Lytis;
    
    //Vartotojai
    public static String Metai;
    public static String Vardas;
    public static String Pavarde;
    public static String Action;
    
    //Koncertai
    public static String Data;
    public static String Pavadinimas;
    public static int Eile;
    public static int bendrasLaikas;
    
}
// FileOutputStream excelFOU = null;
//            BufferedOutputStream excelBOU = null;
//            XSSFWorkbook excelJTableExporter=null;
//        
//        
//        JFileChooser excelFileChooser = new JFileChooser();
//        excelFileChooser.setDialogTitle("Išsaugojama kaip");
//     
//        FileNameExtensionFilter fnef= new FileNameExtensionFilter("EXCEL FILES","xls","xlsx","xlsm");
//        excelFileChooser.setFileFilter(fnef);
//        int excelChooser = excelFileChooser.showSaveDialog(null);
//        
//        if(excelChooser==JFileChooser.APPROVE_OPTION){
////**************************************************************************************
//            try {
//                
//              excelJTableExporter = new XSSFWorkbook();
//                XSSFSheet excelSheet = excelJTableExporter.createSheet("Nariai");
//                for(int i=0;i<10;i++){
//                    //Pirma dalis
//                    
//                    XSSFRow excelRow= excelSheet.createRow(i);
//                    for(int j=0;j<10;j++){
//                        // Antra dalis
//                        
//                        XSSFCell excelCell = excelRow.createCell(j);
//                        excelCell.setCellValue("a");
//                    }
//                    
//                }
//                
//                
//                 for(int i=20;i<30;i++){
//                    //Pirma dalis
//                    
//                    XSSFRow excelRow= excelSheet.createRow(i);
//                    for(int j=0;j<10;j++){
//                        // Antra dalis
//                        
//                        XSSFCell excelCell = excelRow.createCell(j);
//                        excelCell.setCellValue("a");
//                    }
//                    
//                }
//                
//                
//                
//                
//                
//                
//                excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile()+".xlsx");
//                excelBOU = new BufferedOutputStream(excelFOU);
//                excelJTableExporter.write(excelBOU);
//                JOptionPane.showMessageDialog(null, "Duomenys iškelti");
//                
//            } catch (FileNotFoundException ex) {
//                ex.printStackTrace();
//            }   catch (IOException ex) {
//                    ex.printStackTrace();
//                } finally {
//                try {
//                    
//                    if (excelBOU!=null){
//                       excelBOU.close(); 
//                    }
//                    
//                    
//                    
//                    if (excelFOU!=null){
//                       excelFOU.close(); 
//                    }
//                    
//                    if (excelJTableExporter!=null){
//                       excelJTableExporter.close(); 
//                    }
//                    
//                    
//                } catch (IOException ex) {//error?
//                    ex.printStackTrace();
//                }
//            }
//            
//            
//        }