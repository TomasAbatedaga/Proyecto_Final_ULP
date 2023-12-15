package AccesoDatos;

import Entidades.ContratoAlquiler;
import Entidades.EstadoContrato;
import Entidades.Inquilino;
import Entidades.PropiedadInmueble;
import Entidades.Vendedor;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;

/**
 *
 * @author manuv
 */
public class ContratoAlquilerData {
    
    private InquilinoData abmInquilino = new InquilinoData();
    private PropiedadData abmPropiedad = new PropiedadData();
    private VendedorData abmVendedor = new VendedorData();
    private EstadoContratoData abmEstadoContrato = new EstadoContratoData();
    private Inquilino inqui = null;
    private PropiedadInmueble propInmueble = null;
    private Vendedor vendedor = null;
    private EstadoContrato estContrato = null;
    private ContratoAlquiler conAlq = null;
    //ContratoAlquiler contratoAlquiler = null;
    private Connection con = null;
    
    public ContratoAlquilerData(){
        con = Conectar.getConectar();
    }
    
    public void agregarContrato(ContratoAlquiler contrato){
        String sql = "INSERT INTO contratoAlquiler(idInquilino, "
                + "idPropiedadInmueble, fechaFinal, fechaInical, "
                + "fechaRealizacion, marca, idVendedor,idEstadoContrato) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, contrato.getInquilino().getIdInquilino());
            ps.setInt(2, contrato.getPropiedadInmueble().getIdPropiedadInmueble());
            ps.setDate(3, Date.valueOf(contrato.getFechaFinal()));
            ps.setDate(4, Date.valueOf(contrato.getFechaInicio()));
            ps.setDate(5, Date.valueOf(contrato.getFechaRealizacion()));
            ps.setString(6, contrato.getMarca());
            ps.setInt(7, contrato.getVendedor().getIdVendedor());
            ps.setInt(8, contrato.getEstado().getIdEstadoContrato());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                contrato.setIdContratoAlquiler(rs.getInt(1));
                JOptionPane.showMessageDialog(null,"Se ha agregado un nuevo Contrato de Alquiler correctamente");
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar Contrato de Alquiler "+ ex.getMessage());
        }
    }
    
    public void modificarContrato(ContratoAlquiler contrato){
        String sql = "UPDATE contratoAlquiler SET idInquilino=?,"
                + "idPropiedadInmueble=?,fechaFinal=?,fechaInical=?,"
                + "fechaRealizacion=?,marca=?,idVendedor=?,idEstadoContrato=? "
                + "WHERE idContratoAlquiler = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, contrato.getInquilino().getIdInquilino());
            ps.setInt(2, contrato.getPropiedadInmueble().getIdPropiedadInmueble());
            ps.setDate(3, Date.valueOf(contrato.getFechaFinal()));
            ps.setDate(4, Date.valueOf(contrato.getFechaInicio()));
            ps.setDate(5, Date.valueOf(contrato.getFechaRealizacion()));
            ps.setString(6, contrato.getMarca());
            ps.setInt(7, contrato.getVendedor().getIdVendedor());
            ps.setInt(8, contrato.getEstado().getIdEstadoContrato());
            ps.setInt(9, contrato.getIdContratoAlquiler());
            
            int rs = ps.executeUpdate();
            
//            if(rs > 0){
//                JOptionPane.showMessageDialog(null,"El Contrato de Alquiler fue modificado correctamente");
//            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar Contrato de Alquiler "+ ex.getMessage());
        }
        
        
    }
    
    public List<ContratoAlquiler> listarContrato (){
        
        List<ContratoAlquiler> listado = new ArrayList<>();
        String sql = "SELECT * FROM contratoAlquiler";
        try {
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
               inqui = abmInquilino.buscoInquilino(rs.getInt("idInquilino"));
               propInmueble = abmPropiedad.buscarPropiedadPorId(rs.getInt("idPropiedadInmueble"));
               vendedor = abmVendedor.buscoVendedor(rs.getInt("idVendedor"));
               estContrato = abmEstadoContrato.BuscarEstadoContratoPorId(rs.getInt("idEstadoContrato"));
               LocalDate ldf = LocalDate.parse(rs.getString(4));
               LocalDate ldi = LocalDate.parse(rs.getString(5));
               LocalDate ldr = LocalDate.parse(rs.getString(6));
               
               
               //Empezamos a construir el objeto que va al listado.
               conAlq = new ContratoAlquiler();
               conAlq.setIdContratoAlquiler(rs.getInt(1));
               conAlq.setInquilino(inqui);
               conAlq.setPropiedadInmueble(propInmueble);
               conAlq.setFechaFinal(ldf);
               conAlq.setFechaInicio(ldi);
               conAlq.setFechaRealizacion(ldr);
               conAlq.setVendedor(vendedor);
               conAlq.setEstado(estContrato);
               listado.add(conAlq);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error "+e.getMessage());
        }
        return listado;
    }
    
    public List<ContratoAlquiler> listarContratoxIDProp (int idProp){
        
        List<ContratoAlquiler> listado = new ArrayList<>();
        String sql = "SELECT * FROM contratoAlquiler where idPropiedadInmueble = ?";
        try {
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setInt(1, idProp);
           ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
               inqui = abmInquilino.buscoInquilino(rs.getInt("idInquilino"));
               propInmueble = abmPropiedad.buscarPropiedadPorId(rs.getInt("idPropiedadInmueble"));
               vendedor = abmVendedor.buscoVendedor(rs.getInt("idVendedor"));
               estContrato = abmEstadoContrato.BuscarEstadoContratoPorId(rs.getInt("idEstadoContrato"));
               LocalDate ldf = LocalDate.parse(rs.getString(4));
               LocalDate ldi = LocalDate.parse(rs.getString(5));
               LocalDate ldr = LocalDate.parse(rs.getString(6));
               
               
               //Empezamos a construir el objeto que va al listado.
               conAlq = new ContratoAlquiler();
               conAlq.setIdContratoAlquiler(rs.getInt(1));
               conAlq.setInquilino(inqui);
               conAlq.setPropiedadInmueble(propInmueble);
               conAlq.setFechaFinal(ldf);
               conAlq.setFechaInicio(ldi);
               conAlq.setFechaRealizacion(ldr);
               conAlq.setVendedor(vendedor);
               conAlq.setEstado(estContrato);
               listado.add(conAlq);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error "+e.getMessage());
        }
        return listado;
    }
    
    
    public ContratoAlquiler buscarContratoAlquilerPorId (int id){
        
        String sql = "SELECT * FROM contratoAlquiler where idContratoAlquiler = ?";
        
//        inqui = null;
//        propInmueble = null;
//        vendedor = null;
//        estContrato = null;
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
               inqui = abmInquilino.buscoInquilino(rs.getInt("idInquilino"));
               propInmueble = abmPropiedad.buscarPropiedadPorId(rs.getInt("idPropiedadInmueble"));
               vendedor = abmVendedor.buscoVendedor(rs.getInt("idVendedor"));
               estContrato = abmEstadoContrato.BuscarEstadoContratoPorId(rs.getInt("idEstadoContrato"));
               LocalDate ldf = LocalDate.parse(rs.getString("fechaFinal"));
               LocalDate ldi = LocalDate.parse(rs.getString("fechaInicial"));
               LocalDate ldr = LocalDate.parse(rs.getString("fechaRealizacion"));
               
               conAlq = new ContratoAlquiler();
               conAlq.setIdContratoAlquiler(1);
               conAlq.setInquilino(inqui);
               conAlq.setPropiedadInmueble(propInmueble);
               conAlq.setFechaFinal(ldf);
               conAlq.setFechaInicio(ldi);
               conAlq.setFechaRealizacion(ldr);
               conAlq.setVendedor(vendedor);
               conAlq.setEstado(estContrato);
                
            }
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return conAlq;
    }
    
    public List<ContratoAlquiler> buscarContratosRelacion(){
        List<ContratoAlquiler> listado = new ArrayList();
        String sql = "SELECT ca.*,inqui.nombre,inqui.apellido,pi.idTipoLocal, vend.nombre, vend.apellido, ec.nombre "
                +"FROM contratoAlquiler ca JOIN inquilino inqui on(ca.idInquilino=inqui.idInquilino) "
                +"JOIN propiedadInmueble pi on(ca.idPropiedadInmueble=pi.idPropiedad) "
                +"JOIN vendedor vend on(ca.idVendedor=vend.idVendedor) "
                +"JOIN estadoContrato ec on(ca.idEstadoContrato=ec.idEstadoContrato)";
        try {
            
        } catch (Exception e) {
        }
        
        return listado;
    }
    
    public void AnularContrato (int idPropiedad){
        
        String sql = "UPDATE contratoAlquiler SET idEstadoContrato = 4 WHERE idPropiedadInmueble = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, idPropiedad);
            
            int rs = ps.executeUpdate();
            
        } catch (SQLException e){
            
        }
        
    }
    
    public boolean verificarSiLaPropiedadEstaOcupada(ContratoAlquiler contrato){
        boolean condicion = false;
        String sql = "SELECT * FROM contratoAlquiler WHERE ((idPropiedadInmueble = ?)"
                    + " AND ((fechaInical > ?) OR (fechaFinal < ?)))";
        
        try {
           PreparedStatement ps = con.prepareStatement(sql);
           
           ps.setInt(1, contrato.getPropiedadInmueble().getIdPropiedadInmueble());
           ps.setDate(2, Date.valueOf(contrato.getFechaFinal()));
           ps.setDate(3, Date.valueOf(contrato.getFechaInicio()));
           
           ResultSet rs = ps.executeQuery();

           if (rs.next()) {
               condicion = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error "+e.getMessage());
        }
        return condicion;
    }
    
}
