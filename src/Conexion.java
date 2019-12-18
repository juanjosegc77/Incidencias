import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @author Ing. Juan José Guzmán Cruz
 */

public class Conexion {
    static Connection cn = null;
    
    public static Connection Conectar(Connection cn)throws SQLException{
        
        // Para conección a servidor local
        String ruta = "//localhost/Incidencias";
        String usuario = "Incidencias";
        String contraseña = "pass";
                
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //cn = DriverManager.getConnection("jdbc:mysql:"+ruta, usuario, contraseña);        
            cn = DriverManager.getConnection("jdbc:mysql:"+ruta, usuario, contraseña);
            System.out.println("Mensaje desde Conexion.Conectar() : Conectado con la base de datos");
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,
                    "Mensaje desde Conexion.Conectar() \n Error al realizar la conexion \n"+e.toString());}
        return cn;
    }
}