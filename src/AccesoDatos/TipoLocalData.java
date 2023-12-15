package AccesoDatos;

import Entidades.TipoLocal;
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
public class TipoLocalData {
    
    private Connection con = null;
    
    public TipoLocalData(){
        con = Conectar.getConectar();
    }
    
    public void agregarTipoLocal(TipoLocal tipo){
        String sql="INSERT INTO tipoLocal(nombre,descripcion,superficieMinima)"
                + "VALUES(?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tipo.getNombre());
            ps.setString(2, tipo.getDescripcion());
            ps.setDouble(3, tipo.getSuperficieMinima());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                tipo.setIdTipoLocal(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "El tipo de local se agregado correctamente");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el tipo de local "+e.getMessage());
        }
    }
    
    public void modificarTipoLocal(TipoLocal tipo){
        String sql="UODATE tipoLocal SET nombre=?,descripcion=?,superficieMinima=? WHERE idTipoLocal=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, tipo.getNombre());
            ps.setString(2, tipo.getDescripcion());
            ps.setDouble(3, tipo.getSuperficieMinima());
            int valor=ps.executeUpdate();
            if(valor>0){
                JOptionPane.showMessageDialog(null, "Se ha modificado correctamente el tipo de local");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el registro en la base de datos "+e.getMessage());
        }
    }
    
    public List<TipoLocal> listarTipoLocal(){
        List<TipoLocal> listado=new ArrayList();
        TipoLocal temp=null;
        String sql="SELECT * FROM tipoLocal";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                temp=new TipoLocal();
                temp.setIdTipoLocal(rs.getInt(1));
                temp.setNombre(rs.getString("nombre"));
                temp.setDescripcion(rs.getString("descripcion"));
                temp.setSuperficieMinima(rs.getDouble("superficieMinima"));
                listado.add(temp);
            }
            ps.close();
        } catch (Exception e) {
        }
        return listado;
    }
    
    public TipoLocal buscoTipoLocal(int id){
        TipoLocal temp=null;
        String sql="SELECT * FROM tipoLocal WHERE idTipoLocal=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                temp=new TipoLocal();
                temp.setIdTipoLocal(rs.getInt(1));
                temp.setNombre(rs.getString("nombre"));
                temp.setDescripcion(rs.getString("descripcion"));
                temp.setSuperficieMinima(rs.getDouble(4));
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el Tipo de Local "+e.getMessage());
        }
        return temp;
    }
}
