package AccesoDatos;


import Entidades.Propietario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mariadb.jdbc.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author manuv
 */
public class PropietarioData {
    
    private Connection con = null;
    
    public PropietarioData(){
        con = Conectar.getConectar();
    }
    
    public void agrearPropietario(Propietario prop){
        String sql="INSERT INTO propietario (dni,nombre,apellido,domicilio,telefono)"
                + "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, prop.getDni());
            ps.setString(2, prop.getNombre());
            ps.setString(3, prop.getApellido());
            ps.setString(4, prop.getDomicilio());
            ps.setLong(5, prop.getTelefono());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                prop.setIdPropietario(1);
                //JOptionPane.showMessageDialog(null, "El propietario se ha agregado correctamente");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el propietario "+e.getMessage());
        }
    }
    
    
    public void modificarPropietario(Propietario prop){
        String cadena="UPDATE propietario SET dni=?, nombre=?, apellido=?, domicilio=?, telefono=? WHERE idPropietario=?";
        try {
            PreparedStatement ps=con.prepareStatement(cadena);
            ps.setInt(1, prop.getDni());
            ps.setString(2, prop.getNombre());
            ps.setString(3, prop.getApellido());
            ps.setString(4, prop.getDomicilio());
            ps.setLong(5, prop.getTelefono());
            ps.setInt(6, prop.getIdPropietario());
            int valor =ps.executeUpdate();
            if(valor>0){
                //JOptionPane.showMessageDialog(null, "Se ha actualizado el propietario");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el propietario "+e.getMessage());
        }
    }
    
    public List<Propietario> listarPropietario(){
        List<Propietario> listado=new ArrayList();
        Propietario temp=null;
        String sql="SELECT * FROM propietario";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                //dni,nombre,apellido,domucilio,telefono
                temp=new Propietario();
                temp.setIdPropietario(rs.getInt(1));
                temp.setDni(rs.getInt(2));
                temp.setNombre(rs.getString("nombre"));
                temp.setApellido(rs.getString("apellido"));
                temp.setDomicilio(rs.getString("domicilio"));
                temp.setTelefono(rs.getLong("telefono"));
                listado.add(temp);
            }
            ps.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro al acceder a la base de datos "+e.getMessage());
        }
        return listado;
    }
    
    public List<Propietario> listarPropietarioOrdenado(){
        List<Propietario> listado=new ArrayList();
        Propietario temp=null;
        String sql="SELECT * FROM propietario order by apellido";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                //dni,nombre,apellido,domucilio,telefono
                temp=new Propietario();
                temp.setIdPropietario(rs.getInt(1));
                temp.setDni(rs.getInt(2));
                temp.setNombre(rs.getString("nombre"));
                temp.setApellido(rs.getString("apellido"));
                temp.setDomicilio(rs.getString("domicilio"));
                temp.setTelefono(rs.getLong("telefono"));
                listado.add(temp);
            }
            ps.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro al acceder a la base de datos "+e.getMessage());
        }
        return listado;
    }
    
    public Propietario buscopPropietario(int id){
        Propietario elprop=new Propietario();
        String sql="SELECT * FROM propietario WHERE idPropietario=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
               elprop.setIdPropietario(rs.getInt(1));
               elprop.setNombre(rs.getString("nombre"));
               elprop.setApellido(rs.getString("apellido"));
               elprop.setDni(rs.getInt("dni"));
               elprop.setDomicilio(rs.getString("domicilio"));
               elprop.setTelefono(rs.getLong("telefono"));
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos "+e.getMessage());
        }
        return elprop;
    }
    
    
    public Propietario buscoxDNI(int dni){
        Propietario prop=new Propietario();
        String sql="SELECT * FROM propietario WHERE dni=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                prop.setIdPropietario(rs.getInt(1));
                prop.setDni(rs.getInt(2));
                prop.setNombre(rs.getString(3));
                prop.setApellido(rs.getString(4));
                prop.setDomicilio(rs.getString(5));
                prop.setTelefono(rs.getLong(6));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Eror "+e.getMessage());
        }
        
        return prop;
    }
    
}
