import Crypt.Crypto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Ing. Juan José Guzmán Cruz
 */

public class HelperDB {
 
    // Variables para la conexion
    static Connection cn;
    static Statement s;
    static ResultSet rs;
    
    DefaultTableModel model= new DefaultTableModel();
    
    // CONECTAR A LA BASE DE DATOS Incidencias UTILIZANDO EL MÉTODO
    // Conexion.Conectar()
    public void conn(){
        try {
            cn = Conexion.Conectar(cn);
        } catch (SQLException ex) {
            Logger.getLogger(HelperDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarConexion() throws SQLException {
      if (cn != null) {
         cn.close();
         System.out.println("Mensaje desde Conexion.cerrar() : Conexión de base de datos cerrada");
      }
   }
   
    // CREAR UN MODELO A PARTIR DE LA ESTRUCTURA DE UNA TABLA EN PARTICULAR
    public DefaultTableModel getModel(String query){ 
        conn();
        try{
            enviarQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            
            //OBTENER EL NÚMERO Y NOMBRE DE LAS COLUMNAS DE LA TABLA
            // Y AGREGARLOS AL MODELO
            int columnas = rsmd.getColumnCount(); 
            for(int i=1; i<=columnas; i++){
                // Agregar los nombres de columnas al model
                model.addColumn(rsmd.getColumnLabel(i));
            }
            cerrarConexion();
        }catch(Exception e){
            System.out.println("query desde HelperDB.getModel() : " + query);
            JOptionPane.showMessageDialog(null, 
                "Mensaje desde HelperDB.getModel() \n Error al obtener el modelo de la tabla \n"+e.toString());
        }
        return model;
    }
    
    public DefaultTableModel getDataModel(String query, String table){ 
        conn();
        try{
            Crypto crypto = new Crypto();
            enviarQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            
            //OBTENER EL NÚMER DE COLUMNAS DE LA TABLA
            int columnas = rsmd.getColumnCount(); 
            //OBTENER CADA UNA DE LAS FILAS DE LA TABLA Y AGREGARLAS AL MODELO
            while (rs.next()){
                // Crear array para insertar las filas 
                Object[] fila = new Object[columnas];
                
                // Cargar los datos al model
                if (table.equals("alfabetico")) {
                    fila[0] = rs.getObject(1);
                    for(int i=1; i<columnas; i++){
                        String data = crypto.decrypt(rs.getString(i+1));
                        fila[i] = data;
                    }
                }
                else if (table.equals("vaclic")) {
                    fila[0] = rs.getObject(1);
                    fila[1] = rs.getObject(2);
                    for(int i=2; i<columnas; i++){
                        String data = crypto.decrypt(rs.getString(i+1));
                        fila[i] = data;
                    }
                }
                model.addRow(fila);
            }
            cerrarConexion();
        }catch(Exception e){
            System.out.println("query desde HelperDB.getDataModel() : " + query);
            JOptionPane.showMessageDialog(null,
                "Mensaje desde HelperDB.getDataModel() \n Error al obtener el modelo de los datos \n"+e.toString());
        }
        return model;
    }
    
    public DefaultTableModel getTableModel(String query, String table){ 
        conn();
        try{
            Crypto crypto = new Crypto();
            enviarQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            
            //OBTENER EL NÚMERO Y NOMBRE DE LAS COLUMNAS DE LA TABLA
            // Y AGREGARLOS AL MODELO
            int columnas = rsmd.getColumnCount();
            for(int i=1; i<=columnas; i++){
                // Agregar los nombres de columnas al model
                model.addColumn(rsmd.getColumnLabel(i));
            }
            //OBTENER CADA UNA DE LAS FILAS DE LA TABLA Y AGREGARLAS AL MODELO
            while (rs.next()){
                // Crear array para insertar las filas 
                Object[] fila = new Object[columnas];
                
                // Cargar los datos al model
                if (table.equals("alfabetico")) {
                    fila[0] = rs.getObject(1);
                    for(int i=1; i<columnas; i++){
                        String data = crypto.decrypt(rs.getString(i+1));
                        fila[i] = data;
                    }
                }
                else if (table.equals("vaclic")) {
                    fila[0] = rs.getObject(1);
                    fila[1] = rs.getObject(2);
                    for(int i=2; i<columnas; i++){
                        String data = crypto.decrypt(rs.getString(i+1));
                        fila[i] = data;
                    }
                }
                model.addRow(fila);
            }
            cerrarConexion();
        }catch(Exception e){
            System.out.println("query desde HelperDB.getDataModel() : " + query);
            JOptionPane.showMessageDialog(null,
                "Mensaje desde HelperDB.getDataModel() \n Error al obtener el modelo de los datos \n"+e.toString());
        }
        return model;
    }
    
    // OBTENER LOS DATOS Y DEVOLVERLOS EN UN ArrayList
    public ArrayList<String> getData(String query){ 
        ArrayList<String> data = new ArrayList();
        conn();
        try{
            enviarQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            Crypto crypto = new Crypto();
            
            //Obtener el número de columnas 
            int columnas = rsmd.getColumnCount();
            while (rs.next()){
                data.add(rs.getString(1));
                for(int i=1; i<columnas; i++){
                    data.add(crypto.decrypt(rs.getString(i+1)));                    }
            }
            cerrarConexion();
        }catch(Exception e){
            System.out.println("query desde HelperDB.getData() : " + query);
            JOptionPane.showMessageDialog(null, 
                "Mensaje desde HelperDB.getData() \n Error al obtener datos de tabla \n"+e.toString());
        }
        return data;
    }
    
    // ENVIAR SENTENCIA SQL
    public void enviarQuery(String query){
        try {
            Statement s = cn.createStatement();
            rs = s.executeQuery(query);            
        } catch (SQLException ex) {
            Logger.getLogger(HelperDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("query desde HelperDB.enviarQuery() : " + query);
            JOptionPane.showMessageDialog(null, 
                    "Mensaje desde HelperDB.enviarQuery() \n Error al enviar query \n"+ex.toString());
        }
    }
    
    // ENVIAR SENTENCIA SQL
    public boolean updateDB(String query){
        boolean r = false;
        conn();
        try {
            Statement s = cn.createStatement();
            s.executeUpdate(query);
            r = true;
            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(HelperDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("query desde HelperDB.updateDB() : " + query);
            JOptionPane.showMessageDialog(null, 
                    "Mensaje desde HelperDB.updateDB() \n Error al actualizar base de datos \n"+ex.toString());
        }
        return r;
    }
    
    // OBTENER LOS DATOS Y DEVOLVERLOS EN UN ArrayList
    public ArrayList<String> getUserData(String query){ 
        ArrayList<String> data = new ArrayList();
        conn();
        try{
            enviarQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            
            //Obtener el número de columnas 
            int columnas = rsmd.getColumnCount();
            while (rs.next()){
                for(int i=0; i<columnas; i++){                   
                    data.add(rs.getString(i+1));    
                }
            }
            cerrarConexion();
        }catch(Exception e){
            System.out.println("query desde HelperDB.getUserData() : " + query);
            JOptionPane.showMessageDialog(null, 
                "Mensaje desde HelperDB.getUserData() \n Error al obtener datos del usuario \n"+e.toString());
        }
        return data;
    }
}
