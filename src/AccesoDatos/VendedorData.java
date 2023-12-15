package AccesoDatos;

import Entidades.Vendedor;
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
 * @author manuv
 */
public class VendedorData {
    
    private Connection con = null;
    
    public VendedorData(){
        con = Conectar.getConectar();
    }
    
    public void agregarVendedor(Vendedor vend){
        String sql="INSERT INTO vendedor(nombre,apellido,cantidadVentas,estado)"
                + "VALUES(?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, vend.getNombre());
            ps.setString(2, vend.getApellido());
            ps.setInt(3, vend.getCantidadVentas());
            ps.setBoolean(4, vend.isEstado());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                vend.setIdVendedor(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Se ha agregado correctamente el vendedor");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el vendedor "+e.getMessage());
        }
    }
    
    public void modificarVendedor(Vendedor vend){
        String sql="UPDATE vendedor SET nombre=?,apellido=?,cantidadVentas=?,estado=? WHERE idVendedor=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, vend.getNombre());
            ps.setString(2, vend.getApellido());
            ps.setInt(3, vend.getCantidadVentas());
            ps.setBoolean(4, vend.isEstado());
            ps.setInt(5, vend.getIdVendedor());
            int valor=ps.executeUpdate();
            if(valor>0){
                JOptionPane.showMessageDialog(null, "El vendedor se ha actualizado correcamente");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el registro de el vendedor "+e.getMessage());
        }
    }
    
    public List<Vendedor> listadoVendedor(){
        List<Vendedor> listado=new ArrayList();
        String sql="SELECT * FROM vendedor where idVendedor > 1";
        Vendedor vende=null;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                vende=new Vendedor();
                vende.setIdVendedor(rs.getInt(1));
                vende.setNombre(rs.getString("nombre"));
                vende.setApellido(rs.getString("apellido"));
                vende.setCantidadVentas(rs.getInt(4));
                vende.setEstado(rs.getBoolean(5));
                listado.add(vende);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar la base de datos "+e.getMessage());
        }
        
        return listado;
    }
    
    public List<Vendedor> listadoVendedorOriginal(){
        List<Vendedor> listado=new ArrayList();
        String sql="SELECT * FROM vendedor where idVendedor";
        Vendedor vende=null;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                vende=new Vendedor();
                vende.setIdVendedor(rs.getInt(1));
                vende.setNombre(rs.getString("nombre"));
                vende.setApellido(rs.getString("apellido"));
                vende.setCantidadVentas(rs.getInt(4));
                vende.setEstado(rs.getBoolean(5));
                listado.add(vende);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar la base de datos "+e.getMessage());
        }
        
        return listado;
    }
    
    public Vendedor buscoVendedor(int id){
        Vendedor temp=null;
        String sql="SELECT * FROM vendedor WHERE idVendedor=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                temp=new Vendedor();
                temp.setIdVendedor(rs.getInt(1));
                temp.setNombre(rs.getString(2));
                temp.setApellido(rs.getString(3));
                temp.setCantidadVentas(rs.getInt(4));
                temp.setEstado(rs.getBoolean(5));
            }
            ps.close();
        } catch (SQLException e) {
        }
        return temp;
    }
    
    public void actualizarCantidadVentas(int idVend){
        String sql="SELECT COUNT(idContratoAlquiler) FROM contratoAlquiler WHERE idVendedor = ?";
        String cadena="UPDATE vendedor set cantidadVentas = ? where idVendedor = ?";
        int contador = 0;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idVend);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                contador = rs.getInt(1);
            }
            //Acá actualizo la cantidad de ventas de el vendedor
            PreparedStatement ps2=con.prepareStatement(cadena);
            ps2.setInt(1, contador);
            ps2.setInt(2, idVend);
            int valor = ps2.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
        }
        
    }
}
