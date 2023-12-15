
package AccesoDatos;

import Entidades.Inquilino;
//import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;
import java.util.List;

/**
 *
 * @author Abel
 */
public class InquilinoData {
    
    private Connection con=null;
    
    public InquilinoData(){
        con=Conectar.getConectar();
        
    }
    
    public boolean guardarInquilino(Inquilino inqui){
        boolean resultado = false;
        String sql = "INSERT INTO inquilino (cuit,nombre,apellido,lugarTrabajo,dniGarante,nombreGarante) "
                + "VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, inqui.getCuit());
            ps.setString(2, inqui.getNombre());
            ps.setString(3, inqui.getApellido());
            ps.setString(4, inqui.getLugarTrabajo());
            ps.setInt(5, inqui.getDniGarante());
            ps.setString(6, inqui.getNombreGarante());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                inqui.setIdInquilino(rs.getInt(1));
                resultado = true;
                JOptionPane.showMessageDialog(null, "Se agrego el Inquilino");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return resultado;
    }
    
    public void modificarInquilino(Inquilino inqui){
        String sql = "UPDATE inquilino SET cuit = ?, nombre = ?, apellido = ?, lugarTrabajo = ?, dniGarante = ?,"
                + "nombreGarante = ? WHERE idInquilino = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, inqui.getCuit());
            ps.setString(2, inqui.getNombre());
            ps.setString(3, inqui.getApellido());
            ps.setString(4, inqui.getLugarTrabajo());
            ps.setInt(5, inqui.getDniGarante());
            ps.setString(6, inqui.getNombreGarante());
            ps.setInt(7, inqui.getIdInquilino());
            int valor = ps.executeUpdate();
            if (valor>0) {
                JOptionPane.showMessageDialog(null, "El Inquilino se actualizo");
            }
            ps.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public List<Inquilino> listarInquilino(){
        String sql = "SELECT * FROM inquilino";
        List<Inquilino> listado = new ArrayList<>();
        Inquilino inq = null;
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
              inq = new Inquilino(); 
              inq.setIdInquilino(rs.getInt("idInquilino"));
              inq.setCuit(rs.getString("cuit"));
              inq.setNombre(rs.getString("nombre"));
              inq.setApellido(rs.getString("apellido"));
              inq.setLugarTrabajo(rs.getString("lugarTrabajo"));
              inq.setDniGarante(rs.getInt("dniGarante"));
              inq.setNombreGarante(rs.getString("nombreGarante"));
              listado.add(inq);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
        return listado;
    }
    
    
    public Inquilino buscoInquilino(int id){
        Inquilino inq=new Inquilino();
        String sql="SELECT * FROM inquilino WHERE idInquilino=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
               inq.setIdInquilino(rs.getInt(1));
               inq.setCuit(rs.getString("cuit"));
               inq.setNombre(rs.getString("nombre"));
               inq.setApellido(rs.getString("apellido"));
               inq.setLugarTrabajo(rs.getString("lugarTrabajo"));
               inq.setDniGarante(rs.getInt("dniGarante"));
               inq.setNombreGarante(rs.getString("nombreGarante"));
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos "+e.getMessage());
        }
        return inq;
    }
    
}
