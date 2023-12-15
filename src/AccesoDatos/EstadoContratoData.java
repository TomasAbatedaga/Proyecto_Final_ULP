package AccesoDatos;

import Entidades.EstadoContrato;
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
public class EstadoContratoData {
    
    private Connection con = null;
    
    public EstadoContratoData(){
        con = Conectar.getConectar();
    }
    
    public void guardarEstadoContrato(EstadoContrato estCon){
        String sql = "insert into estadoContrato (nombre) values (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, estCon.getNombre());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                estCon.setIdEstadoContrato(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Se guardo correctamente");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void modificarEstadoContrato (EstadoContrato estCon){
        String sql = "update estadoContrato set nombre=? where idEstadoContrado=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, estCon.getIdEstadoContrato());
            int valor = ps.executeUpdate();
            if (valor>0) {
                JOptionPane.showMessageDialog(null, "Se modifico correctamente");
            }
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public List<EstadoContrato> listarEstadoContratos(){
        List<EstadoContrato> listado = new ArrayList<>();
        EstadoContrato estContrato = null;
        String sql = "select * from estadoContrato";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                estContrato = new EstadoContrato();
                estContrato.setIdEstadoContrato(rs.getInt(1));
                estContrato.setNombre(rs.getString("nombre"));
                listado.add(estContrato);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listado;
    }
    
    public EstadoContrato BuscarEstadoContratoPorId(int id){
        String sql = "select * from estadoContrato where idEstadoContrato = ?";
        EstadoContrato estContrato = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                estContrato = new EstadoContrato();
                estContrato.setIdEstadoContrato(rs.getInt(1));
                estContrato.setNombre(rs.getString("nombre"));
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return estContrato;
    }
    
}
