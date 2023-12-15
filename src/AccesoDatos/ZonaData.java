package AccesoDatos;

import Entidades.PropiedadInmueble;
import Entidades.Zona;
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
public class ZonaData {
    
    private Connection con = null;
    
    public ZonaData(){
        con = Conectar.getConectar();
    }
    
    public void agregarZona (Zona zona){
        String sql = "INSERT INTO zona(nombre, descripcion, estado) VALUES (?, ?, 1)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, zona.getNombre());
            ps.setString(2, zona.getDescripcion());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                zona.setIdZona(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Se ha agregado una nueva Zona correctamente");
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar zona "+ ex.getMessage());
        }
        
    }
    
    public void modificarZona (Zona zona){
        String sql = "UPDATE zona SET nombre = ?, descripcion = ?,"
                + " estado = ? WHERE idZona = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, zona.getNombre());
            ps.setString(2, zona.getDescripcion());
            ps.setBoolean(3, zona.isEstado());
            ps.setInt(4, zona.getIdZona());
            
            int rs = ps.executeUpdate();
            
            if(rs > 0){
                JOptionPane.showMessageDialog(null, "La Zona se ha modificado correctamente");
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar zona "+ ex.getMessage());
        }
        
        
        
    }
    
    public List<Zona> listarZonas (){
        List<Zona> listaZona = new ArrayList();
        Zona zona;
        String sql = "SELECT * FROM zona";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                zona = new Zona();
                zona.setIdZona(rs.getInt("idZona"));
                zona.setNombre(rs.getString("nombre"));
                zona.setDescripcion(rs.getString("descripcion"));
                zona.setEstado(rs.getBoolean("estado"));
                listaZona.add(zona);
            }
            
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,  "Error al consultar la base de datos "+ex.getMessage());
        }
        
        return listaZona;
    
    }
    
    public List<Zona> listarZonasPorNombre (String nombre){
        List<Zona> listaZona = new ArrayList();
        Zona zona;
        String sql = "SELECT * FROM zona WHERE nombre LIKE '"+nombre+"%' AND idZona > 1";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                zona = new Zona();
                zona.setIdZona(rs.getInt("idZona"));
                zona.setNombre(rs.getString("nombre"));
                zona.setDescripcion(rs.getString("descripcion"));
                zona.setEstado(rs.getBoolean("estado"));
                listaZona.add(zona);
            }
            
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,  "Error al consultar la base de datos "+ex.getMessage());
        }
        
        return listaZona;
    
    }
    
    public List<Zona> listarZonasPorDescripcion (String descripcion){
        List<Zona> listaZona = new ArrayList();
        Zona zona;
        String sql = "SELECT * FROM zona WHERE descripcion LIKE '"+descripcion+"%' AND idZona > 1";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                zona = new Zona();
                zona.setIdZona(rs.getInt("idZona"));
                zona.setNombre(rs.getString("nombre"));
                zona.setDescripcion(rs.getString("descripcion"));
                zona.setEstado(rs.getBoolean("estado"));
                listaZona.add(zona);
            }
            
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,  "Error al consultar la base de datos "+ex.getMessage());
        }
        
        return listaZona;
    
    }
    
    public List<Zona> listarZonasPorNombre_Y_Descripcion (String nombre, String descripcion){
        List<Zona> listaZona = new ArrayList();
        Zona zona;
        String sql = "SELECT * FROM zona WHERE nombre LIKE '"+nombre+"%' AND "
                + "descripcion LIKE '"+descripcion+"%' AND idZona > 1";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                zona = new Zona();
                zona.setIdZona(rs.getInt("idZona"));
                zona.setNombre(rs.getString("nombre"));
                zona.setDescripcion(rs.getString("descripcion"));
                zona.setEstado(rs.getBoolean("estado"));
                listaZona.add(zona);
            }
            
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,  "Error al consultar la base de datos "+ex.getMessage());
        }
        
        return listaZona;
    
    }
    
    
    
    public Zona buscarZona (int id){
        Zona zona = new Zona();
        String sql = "SELECT * FROM zona WHERE idZona = ?";
        PreparedStatement ps;
        
        try {
            
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                zona.setIdZona(rs.getInt("idZona"));
                zona.setNombre(rs.getString("nombre"));
                zona.setDescripcion(rs.getString("descripcion"));
                zona.setEstado(rs.getBoolean("estado"));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,  "Error al consultar la base de datos "+ex.getMessage());
        }
        
        return zona;
    }
    
    public void darDeBaja(int id){
        String sql = "UPDATE zona SET estado = 0 WHERE idZona = "+ id +"";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            int rs = ps.executeUpdate();

            if(rs > 0){
                JOptionPane.showMessageDialog(null, "La Zona se ha dado de baja");
            }
            JOptionPane.showMessageDialog(null, "Se Han Anulado Los contratos asociados con esta Zona.\n"
                + "Para Volver a Modificarlos se Debera Volver A Habilitar Esta Zona");
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al dar de Baja la zona "+ ex.getMessage());
        }
        //Busco Todas Las Propiedades Que Se encuentren En Esta Zona
        PropiedadData pd = new PropiedadData();
        List<PropiedadInmueble> listaProp = new ArrayList();
        listaProp = pd.busquedaXZona(id);
        
        ContratoAlquilerData cd = new ContratoAlquilerData();
        
        for(PropiedadInmueble p : listaProp){
            cd.AnularContrato(p.getIdPropiedadInmueble());
        }

        
        
        
    }
    
    public void habilitarZona(int id){
        String sql = "UPDATE zona SET estado = 1 WHERE idZona = "+ id +"";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            int rs = ps.executeUpdate();

            if(rs > 0){
                JOptionPane.showMessageDialog(null, "La Zona se ha habilitado correctamente");
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al habilitar la zona "+ ex.getMessage());
        }
    }
    
}
