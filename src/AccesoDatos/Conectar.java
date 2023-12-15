package AccesoDatos;

import java.sql.*;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

public class Conectar {
    
    private static final String URL="jdbc:mariadb://localhost:3306/";
    private static final String DB="inmobiliaria";
    private static final String USUARIO="root";
    private static final String CLAVE="";
    private static Connection conexion;
    
    public Conectar(){}
    
    public static Connection getConectar(){
        if(conexion==null){
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                
                //Coneccion mia. Abel por connection de mariadb
                //String cadena="jdbc:mariadb://localhost:3306/obrador01?user=administrador&password=Sc@pL#BmDB";
                //conexion=Driver.connect(Configuration.parse(cadena));
                
                //Conección a base por java SQL
                conexion=(Connection) DriverManager.getConnection(URL+DB,USUARIO,CLAVE);
                JOptionPane.showMessageDialog(null, "Se conectó");
                
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Problemas accediendo a la clase "+e.getMessage());
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos "+ex.getMessage());
            }
        }
        return conexion;
    }
}
