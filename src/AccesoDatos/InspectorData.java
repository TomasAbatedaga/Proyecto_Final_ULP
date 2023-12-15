package AccesoDatos;

import Entidades.Inspector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

/**
 *
 * @author Abel
 */
public class InspectorData {
    
    private Connection con = null;
    
    public InspectorData(){
        con = Conectar.getConectar();
    }
    
    public void agregarInspector(Inspector insp){
        String sql="INSERT INTO inspector (nombre,apellido,especialidad,estado)"
                + "VALUES (?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, insp.getNombre());
            ps.setString(2, insp.getApellido());
            ps.setString(3, insp.getEspecialidad());
            ps.setBoolean(4, insp.isEstado());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "El inspector se agregado correctamente");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el inspector "+e.getMessage());
        }
    }
    
    public void modificarInspector(Inspector insp){
        //nombre,apellido,especialidad,estado
        String sql="UPDATE inspector SET nombre=?, apellido=?, especialidad=?, estado=? WHERE idInspector=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, insp.getNombre());
            ps.setString(2, insp.getApellido());
            ps.setString(3, insp.getEspecialidad());
            ps.setBoolean(4, insp.isEstado());
            ps.setInt(5, insp.getIdInspector());
            int valor=ps.executeUpdate();
            if(valor>0){
                JOptionPane.showMessageDialog(null, "Se ha modificado el inspector correctamente");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el inspector "+e.getMessage());
        }
    }
    
    public List<Inspector> listadoInspectoeres(){
        List<Inspector> listado=new ArrayList();
        Inspector temp=null;
        String sql="SELECT * FROM inspector where idInspector > 1";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                //nombre,apellido,especialidad,estado
                temp=new Inspector();
                temp.setIdInspector(rs.getInt(1));
                temp.setNombre(rs.getString("nombre"));
                temp.setApellido(rs.getString("apellido"));
                temp.setEspecialidad(rs.getString("especialidad"));
                temp.setEstado(rs.getBoolean("estado"));
                listado.add(temp);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro al acceder a la base de datos "+e.getMessage());
        }
        
        return listado;
    }
    
    public List<Inspector> listadoInspectoeresJCB(){
        List<Inspector> listado=new ArrayList();
        Inspector temp=null;
        String sql="SELECT * FROM inspector";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                //nombre,apellido,especialidad,estado
                temp=new Inspector();
                temp.setIdInspector(rs.getInt(1));
                temp.setNombre(rs.getString("nombre"));
                temp.setApellido(rs.getString("apellido"));
                temp.setEspecialidad(rs.getString("especialidad"));
                temp.setEstado(rs.getBoolean("estado"));
                listado.add(temp);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro al acceder a la base de datos "+e.getMessage());
        }
        
        return listado;
    }
    
    public Inspector buscoInspector(int id){
        Inspector insp=new Inspector();
        String sql="SELECT * FROM inspector WHERE idInspector = ?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                //nombre,apellido,especialidad,estado
                insp.setIdInspector(rs.getInt(1));
                insp.setNombre(rs.getString("nombre"));
                insp.setApellido(rs.getString("apellido"));
                insp.setEspecialidad(rs.getString("especialidad"));
                insp.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos "+e.getMessage());
        }
        
        return insp;
    }
}
