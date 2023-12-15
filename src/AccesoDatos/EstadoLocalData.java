package AccesoDatos;

import Entidades.EstadoLocal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

/**
 *
 * @author Abel
 */
public class EstadoLocalData {
    
    private Connection con = null;
    
    public EstadoLocalData(){
        con = Conectar.getConectar();
    }
    
    public void agregarEstadoLocal(EstadoLocal local){
        String sql = "INSERT INTO estadoLocal (nombre, descripcion)"
                + "VALUES(?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, local.getNombre());
            ps.setString(2, local.getDescripcion());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                local.setIdEstadoLocal(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "El estado de el local se agregado correctamente");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el estado de el local "+e.getMessage());
        }
    }
    
    public void modificarEstadoLocal(EstadoLocal local){
        String sql="UPDATE estadoLocal SET nombre=?, descripcion=? where idEstadoLocal=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, local.getNombre());
            ps.setString(2, local.getDescripcion());
            ps.setInt(3, local.getIdEstadoLocal());
            int valor=ps.executeUpdate();
            if(valor>0){
                JOptionPane.showMessageDialog(null, "El Estado de el local se ha actualizado correctamente");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el estado de el local "+e.getMessage());
        }
    }
    
    public List<EstadoLocal> listadoLocales(){
        List<EstadoLocal> listado=new ArrayList();
        EstadoLocal temp=null;
        String sql="SELECT * FROM estadoLocal";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                temp=new EstadoLocal();
                temp.setIdEstadoLocal(rs.getInt(1));
                temp.setNombre(rs.getString("nombre"));
                temp.setDescripcion(rs.getString("descripcion"));
                listado.add(temp);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos "+e.getMessage());
        }
        
        return listado;
    }
    
    public EstadoLocal buscoEstadoLocal(int id){
        EstadoLocal local=new EstadoLocal();
        String sql="SELECT * FROM estadoLocal WHERE idEstadoLocal = ?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                local.setIdEstadoLocal(rs.getInt(1));
                local.setNombre(rs.getString("nombre"));
                local.setDescripcion("descripcion");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro al acceder a la base de datos "+e.getMessage());
        }
        return local;
    }
}
