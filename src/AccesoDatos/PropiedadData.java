package AccesoDatos;

import Entidades.EstadoLocal;
import Entidades.Inspector;
import Entidades.PropiedadInmueble;
import Entidades.Propietario;
import Entidades.TipoLocal;
import Entidades.Vendedor;
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
public class PropiedadData {

    private Connection con = null;
    private EstadoLocalData abmEstadoLocal = new EstadoLocalData();
    private InspectorData abmInsp = new InspectorData();
    private PropietarioData abmProp = new PropietarioData();
    private TipoLocalData abmTipoLocal = new TipoLocalData();
    private VendedorData abmVendedor = new VendedorData();
    private ZonaData abmZona = new ZonaData();

    public PropiedadData() {
        con = Conectar.getConectar();

    }

    public PropiedadInmueble guardarPropiedad(PropiedadInmueble prop) {
        String sql = "INSERT INTO propiedadInmueble(idpropietario,accesibilidad,caracteristicas,direccion,"
                + "idEstadoLocal,precioTrazado,idInspector,idTipoLocal,idZona,disponibilidad)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, prop.getPropietario().getIdPropietario());
            ps.setString(2, prop.getAccesibilidad());
            ps.setString(3, prop.getCaracteristicasString());
            ps.setString(4, prop.getDireccion());
            ps.setInt(5, prop.getEstadoLocal().getIdEstadoLocal());
            ps.setDouble(6, prop.getPrecioTrazado());
            //ps.setInt(7, prop.getVendedor().getIdVendedor());
            ps.setInt(7, prop.getInspector().getIdInspector());
            ps.setInt(8, prop.getTipoLocal().getIdTipoLocal());
            ps.setInt(9, prop.getZona().getIdZona());
            ps.setBoolean(10, prop.isDisponibilidad());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                prop.setIdPropiedadInmueble(rs.getInt(1));
                //JOptionPane.showMessageDialog(null, "Se agregó la propiedad correctamente");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la propiedad " + e.getMessage());
        }
        return prop;
    }

    public void modificarpropiedad(PropiedadInmueble prop) {
        //idpropietario,accesibilidad,caracteristicas,direccion,
        //idEstadoLocal,precioTrazado,idInspector,idTipoLocal,idZona,disponibilidad
        String sql = "UPDATE propiedadInmueble SET idPropietario=?, accesibilidad=?, caracteristicas=?, "
                + "direccion=?, idEstadoLocal=?, precioTrazado=?, idInspector=?, idTipoLocal=?, idZona=?, disponibilidad=? "
                + "WHERE idPropiedad=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, prop.getPropietario().getIdPropietario());
            ps.setString(2, prop.getAccesibilidad());
            ps.setString(3, prop.getCaracteristicasString());
            ps.setString(4, prop.getDireccion());
            ps.setInt(5, prop.getEstadoLocal().getIdEstadoLocal());
            ps.setDouble(6, prop.getPrecioTrazado());
            //ps.setInt(7, prop.getVendedor().getIdVendedor());
            ps.setInt(7, prop.getInspector().getIdInspector());
            ps.setInt(8, prop.getTipoLocal().getIdTipoLocal());
            ps.setInt(9, prop.getZona().getIdZona());
            ps.setBoolean(10, prop.isDisponibilidad());
            ps.setInt(11, prop.getIdPropiedadInmueble());
            int valor = ps.executeUpdate();
            if (valor > 0) {
                JOptionPane.showMessageDialog(null, "La propiedad se ha actualizado correctamente");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el registro de la propiedad " + e.getMessage());
        }
    }

    public List<PropiedadInmueble> buscarPropiedad() {
        List<PropiedadInmueble> listado = new ArrayList();
        PropiedadInmueble temp = null;
        Propietario elProp = null;
        Zona zon = null;
        EstadoLocal elEstado = null;
        TipoLocal elTipo = null;
        //Vendedor ven=null;
        Inspector insp = null;
        String sql = "SELECT pi.*, prop.nombre, prop.apellido, prop.domicilio, prop.telefono, "
                + "el.nombre, ins.nombre, ins.apellido, tp.nombre, tp.superficieMinima, z.nombre, z.descripcion "
                + "FROM propiedadInmueble pi JOIN propietario prop on(pi.idPropietario=prop.idPropietario) "
                + "JOIN estadoLocal el on(pi.idEstadoLocal=el.idEstadoLocal) "
                + "JOIN inspector ins on(pi.idInspector=ins.idInspector) "
                + "JOIN tipoLocal tp on(pi.idTipoLocal=tp.idTipoLocal) "
                + "JOIN zona z on(pi.idZona=z.idZona) "
                + "ORDER BY idPropiedad";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                zon = new Zona();
                elProp = new Propietario();
                elEstado = new EstadoLocal();
                elTipo = new TipoLocal();
                //ven=new Vendedor();
                insp = new Inspector();
                temp = new PropiedadInmueble();
                zon = abmZona.buscarZona(rs.getInt("idZona"));
                elProp = abmProp.buscopPropietario(rs.getInt("idpropietario"));
                elEstado = abmEstadoLocal.buscoEstadoLocal(rs.getInt("idEstadoLocal"));
                elTipo = abmTipoLocal.buscoTipoLocal(rs.getInt("idTipoLocal"));
                //ven=abmVendedor.buscoVendedor(rs.getInt("idVendedor"));
                insp = abmInsp.buscoInspector(rs.getInt("idInspector"));

                //Empiezo armar el la propiedad inmuelble
                temp.setIdPropiedadInmueble(rs.getInt("idPropiedad"));
                temp.setPropietario(elProp);
                temp.setAccesibilidad(rs.getString("accesibilidad"));
                temp.setCaracteristicasString(rs.getString("caracteristicas"));
                temp.setDireccion(rs.getString("direccion"));
                temp.setEstadoLocal(elEstado);
                temp.setPrecioTrazado(rs.getFloat("precioTrazado"));
                //temp.setVendedor(ven);
                temp.setInspector(insp);
                temp.setTipoLocal(elTipo);
                temp.setZona(zon);
                temp.setDisponibilidad(rs.getBoolean("disponibilidad"));
                listado.add(temp);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error a acceder a los datos de la tabla " + e.getMessage());
        }
        return listado;
    }

    public List<PropiedadInmueble> rangoPrecios(float ini, float fin) {
        List<PropiedadInmueble> elListado = new ArrayList();
        PropiedadInmueble rangoProp = null;
        Propietario rangoPropietario = null;
        EstadoLocal rangoEstadoLocal = null;
        TipoLocal rangoTipoLocal = null;
        Inspector rangoInspector = null;
        //Vendedor rangoVendedor = null;
        Zona rangoZona = null;
        String sql = "SELECT pi.*, prop.nombre, prop.apellido, prop.domicilio, prop.telefono, "
                + "el.nombre, ins.nombre, ins.apellido, tp.nombre, tp.superficieMinima, z.nombre, z.descripcion "
                + "FROM propiedadInmueble pi JOIN propietario prop on(pi.idPropietario=prop.idPropietario) "
                + "JOIN estadoLocal el on(pi.idEstadoLocal=el.idEstadoLocal) "
                + "JOIN inspector ins on(pi.idInspector=ins.idInspector) "
                + "JOIN tipoLocal tp on(pi.idTipoLocal=tp.idTipoLocal) "
                + "JOIN zona z on(pi.idZona=z.idZona) "
                + "WHERE pi.precioTrazado > " + ini + " AND pi.precioTrazado <" + fin + " "
                + "ORDER BY idPropiedad";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //Construyo los objetos para el inmueble
                rangoPropietario = abmProp.buscopPropietario(rs.getInt("idPropietario"));
                rangoEstadoLocal = abmEstadoLocal.buscoEstadoLocal(rs.getInt("idEstadoLocal"));
                rangoTipoLocal = abmTipoLocal.buscoTipoLocal(rs.getInt("idTipoLocal"));
                rangoInspector = abmInsp.buscoInspector(rs.getInt("idInspector"));
                //rangoVendedor = abmVendedor.buscoVendedor(rs.getInt("idVendedor"));
                rangoZona = abmZona.buscarZona(rs.getInt("idZona"));
                //Construyo el objeto inmueble para agragar al listado
                rangoProp = new PropiedadInmueble();
                rangoProp.setIdPropiedadInmueble(rs.getInt(1));
                rangoProp.setPropietario(rangoPropietario);
                rangoProp.setAccesibilidad(rs.getString("accesibilidad"));
                rangoProp.setCaracteristicasString(rs.getString("caracteristicas"));
                rangoProp.setDireccion(rs.getString("direccion"));
                rangoProp.setEstadoLocal(rangoEstadoLocal);
                rangoProp.setPrecioTrazado(rs.getFloat("precioTrazado"));
                //rangoProp.setVendedor(rangoVendedor);
                rangoProp.setInspector(rangoInspector);
                rangoProp.setTipoLocal(rangoTipoLocal);
                rangoProp.setZona(rangoZona);
                rangoProp.setDisponibilidad(rs.getBoolean("disponibilidad"));
                elListado.add(rangoProp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
        return elListado;
    }

    public PropiedadInmueble buscarPropiedadPorId(int id) {
        PropiedadInmueble temp = null;
        Propietario elProp = null;
        Zona zon = null;
        EstadoLocal elEstado = null;
        TipoLocal elTipo = null;
        //Vendedor ven=null;
        Inspector insp = null;
        String sql = "SELECT pi.*, prop.nombre, prop.apellido, prop.domicilio, prop.telefono, "
                + "el.nombre, ins.nombre, ins.apellido, tp.nombre, tp.superficieMinima, z.nombre, z.descripcion "
                + "FROM propiedadInmueble pi JOIN propietario prop on(pi.idPropietario=prop.idPropietario) "
                + "JOIN estadoLocal el on(pi.idEstadoLocal=el.idEstadoLocal) "
                + "JOIN inspector ins on(pi.idInspector=ins.idInspector) "
                + "JOIN tipoLocal tp on(pi.idTipoLocal=tp.idTipoLocal) "
                + "JOIN zona z on(pi.idZona=z.idZona) "
                + "where pi.idPropiedad = ? "
                + "ORDER BY idPropiedad";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                zon = new Zona();
                elProp = new Propietario();
                elEstado = new EstadoLocal();
                elTipo = new TipoLocal();
                //ven=new Vendedor();
                insp = new Inspector();
                temp = new PropiedadInmueble();
                zon = abmZona.buscarZona(rs.getInt("idZona"));
                elProp = abmProp.buscopPropietario(rs.getInt("idpropietario"));
                elEstado = abmEstadoLocal.buscoEstadoLocal(rs.getInt("idEstadoLocal"));
                elTipo = abmTipoLocal.buscoTipoLocal(rs.getInt("idTipoLocal"));
                //ven=abmVendedor.buscoVendedor(rs.getInt("idVendedor"));
                insp = abmInsp.buscoInspector(rs.getInt("idInspector"));

                //Empiezo armar el la propiedad inmuelble
                temp.setIdPropiedadInmueble(rs.getInt("idPropiedad"));
                temp.setPropietario(elProp);
                temp.setAccesibilidad(rs.getString("accesibilidad"));
                temp.setCaracteristicasString(rs.getString("caracteristicas"));
                temp.setDireccion(rs.getString("direccion"));
                temp.setEstadoLocal(elEstado);
                temp.setPrecioTrazado(rs.getFloat("precioTrazado"));
                //temp.setVendedor(ven);
                temp.setInspector(insp);
                temp.setTipoLocal(elTipo);
                temp.setZona(zon);
                temp.setDisponibilidad(rs.getBoolean("disponibilidad"));
            }
            ps.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return temp;
    }

    public List<PropiedadInmueble> buscarxPropietario(int idPropietario) {
        List<PropiedadInmueble> listado = new ArrayList();
        PropiedadInmueble elInmueble = null;
        Propietario elProp = null;
        Zona laZona = null;
        EstadoLocal elLocal = null;
        TipoLocal tipoLocal = null;
        //Vendedor elVendedor=null;
        Inspector elInspector = null;
        String sql = "SELECT pi.*, prop.nombre, prop.apellido, prop.domicilio, prop.telefono, "
                + "el.nombre, ins.nombre, ins.apellido, tp.nombre, tp.superficieMinima, z.nombre, z.descripcion "
                + "FROM propiedadInmueble pi JOIN propietario prop on(pi.idPropietario=prop.idPropietario) "
                + "JOIN estadoLocal el on(pi.idEstadoLocal=el.idEstadoLocal) "
                + "JOIN inspector ins on(pi.idInspector=ins.idInspector) "
                + "JOIN tipoLocal tp on(pi.idTipoLocal=tp.idTipoLocal) "
                + "JOIN zona z on(pi.idZona=z.idZona)"
                + "where pi.idPropietario = ? "
                + "ORDER BY idPropiedad";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPropietario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                elProp = abmProp.buscopPropietario(rs.getInt("idPropietario"));
                laZona = abmZona.buscarZona(rs.getInt("idZona"));
                elLocal = abmEstadoLocal.buscoEstadoLocal(rs.getInt("idEstadoLocal"));
                tipoLocal = abmTipoLocal.buscoTipoLocal(rs.getInt("idTipoLocal"));
                //elVendedor=abmVendedor.buscoVendedor(rs.getInt("idVendedor"));
                elInspector = abmInsp.buscoInspector(rs.getInt("idInspector"));
                //Armo el inmueble antes de agregarlo al listado
                elInmueble = new PropiedadInmueble();
                elInmueble.setIdPropiedadInmueble(rs.getInt(1));
                elInmueble.setPropietario(elProp);
                elInmueble.setAccesibilidad(rs.getString(3));
                elInmueble.setCaracteristicasString(rs.getString(4));
                elInmueble.setDireccion(rs.getString(5));
                elInmueble.setEstadoLocal(elLocal);
                elInmueble.setPrecioTrazado(rs.getFloat(7));
                //elInmueble.setVendedor(elVendedor);
                elInmueble.setInspector(elInspector);
                elInmueble.setTipoLocal(tipoLocal);
                elInmueble.setZona(laZona);
                elInmueble.setDisponibilidad(rs.getBoolean(11));
                listado.add(elInmueble);
            }
            ps.close();
        } catch (SQLException e) {
        }

        return listado;
    }
    
    public List<PropiedadInmueble> buscarxPropietarioZonaFiltrada(int idPropietario) {
        List<PropiedadInmueble> listado = new ArrayList();
        PropiedadInmueble elInmueble = null;
        Propietario elProp = null;
        Zona laZona = null;
        EstadoLocal elLocal = null;
        TipoLocal tipoLocal = null;
        //Vendedor elVendedor=null;
        Inspector elInspector = null;
        String sql = "SELECT ppi.*, z.estado FROM propiedadInmueble ppi JOIN zona z on(ppi.idZona=z.idZona)"
                + " where z.estado = true and idPropietario =  ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPropietario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                elProp = abmProp.buscopPropietario(rs.getInt("idPropietario"));
                laZona = abmZona.buscarZona(rs.getInt("idZona"));
                elLocal = abmEstadoLocal.buscoEstadoLocal(rs.getInt("idEstadoLocal"));
                tipoLocal = abmTipoLocal.buscoTipoLocal(rs.getInt("idTipoLocal"));
                //elVendedor=abmVendedor.buscoVendedor(rs.getInt("idVendedor"));
                elInspector = abmInsp.buscoInspector(rs.getInt("idInspector"));
                //Armo el inmueble antes de agregarlo al listado
                elInmueble = new PropiedadInmueble();
                elInmueble.setIdPropiedadInmueble(rs.getInt(1));
                elInmueble.setPropietario(elProp);
                elInmueble.setAccesibilidad(rs.getString(3));
                elInmueble.setCaracteristicasString(rs.getString(4));
                elInmueble.setDireccion(rs.getString(5));
                elInmueble.setEstadoLocal(elLocal);
                elInmueble.setPrecioTrazado(rs.getFloat(7));
                //elInmueble.setVendedor(elVendedor);
                elInmueble.setInspector(elInspector);
                elInmueble.setTipoLocal(tipoLocal);
                elInmueble.setZona(laZona);
                elInmueble.setDisponibilidad(rs.getBoolean(11));
                listado.add(elInmueble);
            }
            ps.close();
        } catch (SQLException e) {
        }

        return listado;
    }
    

    public List<PropiedadInmueble> busquedaXZona(int idZona) {
        List<PropiedadInmueble> listaXZona = new ArrayList();
        PropiedadInmueble zonaInmueble = null;
        Propietario zonaPropietario = null;
        TipoLocal zonaTipo = null;
        EstadoLocal zonaEstado = null;
        Inspector zonainsInspector = null;
        //Vendedor zonaVendedor=null;
        Zona laZona = null;
        String sqlZona = "SELECT pi.*, prop.nombre, prop.apellido, prop.domicilio, prop.telefono, "
                + "el.nombre, ins.nombre, ins.apellido, tp.nombre, tp.superficieMinima, z.nombre, z.descripcion "
                + "FROM propiedadInmueble pi JOIN propietario prop on(pi.idPropietario=prop.idPropietario) "
                + "JOIN estadoLocal el on(pi.idEstadoLocal=el.idEstadoLocal) "
                + "JOIN inspector ins on(pi.idInspector=ins.idInspector) "
                + "JOIN tipoLocal tp on(pi.idTipoLocal=tp.idTipoLocal) "
                + "JOIN zona z on(pi.idZona=z.idZona)"
                + "where pi.idZona = ? "
                + "ORDER BY idPropiedad";
        try {
            PreparedStatement ps = con.prepareStatement(sqlZona);
            ps.setInt(1, idZona);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //Empiezo a buscar los datos para construir los objetos relacionados con el inmueble
                zonaPropietario = abmProp.buscopPropietario(rs.getInt("idPropietario"));
                zonaTipo = abmTipoLocal.buscoTipoLocal(rs.getInt("idTipoLocal"));
                zonaEstado = abmEstadoLocal.buscoEstadoLocal(rs.getInt("idEstadoLocal"));
                zonainsInspector = abmInsp.buscoInspector(rs.getInt("idInspector"));
                //zonaVendedor=abmVendedor.buscoVendedor(rs.getInt("idVendedor"));
                laZona = abmZona.buscarZona(rs.getInt("idZona"));
                //Empiezo a construir el objeto inmueble
                zonaInmueble = new PropiedadInmueble();
                zonaInmueble.setIdPropiedadInmueble(rs.getInt(1));
                zonaInmueble.setPropietario(zonaPropietario);
                zonaInmueble.setAccesibilidad(rs.getString(3));
                zonaInmueble.setCaracteristicasString(rs.getString(4));
                zonaInmueble.setDireccion(rs.getString(5));
                zonaInmueble.setEstadoLocal(zonaEstado);
                zonaInmueble.setPrecioTrazado(rs.getFloat(7));
                //zonaInmueble.setVendedor(zonaVendedor);
                zonaInmueble.setInspector(zonainsInspector);
                zonaInmueble.setTipoLocal(zonaTipo);
                zonaInmueble.setZona(laZona);
                zonaInmueble.setDisponibilidad(rs.getBoolean(11));
                listaXZona.add(zonaInmueble);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
        return listaXZona;
    }

    public List<PropiedadInmueble> listadoInmueblexTipo(int idTipo) {
        List<PropiedadInmueble> laLista = new ArrayList();
        PropiedadInmueble tipoProp = null;
        Propietario tipoPropietario = null;
        TipoLocal elTipoLocal = null;
        EstadoLocal tipoEstadoLocal = null;
        Inspector tipoInspector = null;
        //Vendedor tipoVendedor=null;
        Zona tipoZona = null;
        String cadena = "SELECT pi.*, prop.nombre, prop.apellido, prop.domicilio, prop.telefono, "
                + "el.nombre, ins.nombre, ins.apellido, tp.nombre, tp.superficieMinima, z.nombre, z.descripcion "
                + "FROM propiedadInmueble pi JOIN propietario prop on(pi.idPropietario=prop.idPropietario) "
                + "JOIN estadoLocal el on(pi.idEstadoLocal=el.idEstadoLocal) "
                + "JOIN inspector ins on(pi.idInspector=ins.idInspector) "
                + "JOIN tipoLocal tp on(pi.idTipoLocal=tp.idTipoLocal) "
                + "JOIN zona z on(pi.idZona=z.idZona)"
                + "where pi.idTipoLocal = ? "
                + "ORDER BY idPropiedad";
        try {
            PreparedStatement ps = con.prepareStatement(cadena);
            ps.setInt(1, idTipo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tipoPropietario = abmProp.buscopPropietario(rs.getInt("idPropietario"));
                elTipoLocal = abmTipoLocal.buscoTipoLocal(rs.getInt("idTipoLocal"));
                tipoEstadoLocal = abmEstadoLocal.buscoEstadoLocal(rs.getInt("idEstadoLocal"));
                tipoInspector = abmInsp.buscoInspector(rs.getInt("idInspector"));
                //tipoVendedor=abmVendedor.buscoVendedor(rs.getInt("idVendedor"));
                tipoZona = abmZona.buscarZona(rs.getInt("idZona"));
                tipoProp = new PropiedadInmueble();
                tipoProp.setIdPropiedadInmueble(rs.getInt("idPropiedad"));
                tipoProp.setPropietario(tipoPropietario);
                tipoProp.setAccesibilidad(rs.getString("accesibilidad"));
                tipoProp.setCaracteristicasString(rs.getString("caracteristicas"));
                tipoProp.setDireccion(rs.getString("direccion"));
                tipoProp.setEstadoLocal(tipoEstadoLocal);
                tipoProp.setPrecioTrazado(rs.getFloat("precioTrazado"));
                //tipoProp.setVendedor(tipoVendedor);
                tipoProp.setInspector(tipoInspector);
                tipoProp.setTipoLocal(elTipoLocal);
                tipoProp.setZona(tipoZona);
                tipoProp.setDisponibilidad(rs.getBoolean("disponibilidad"));
                laLista.add(tipoProp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }

        return laLista;
    }

    public List<PropiedadInmueble> rangoPreciosYporTipoLocal(float ini, float fin, int id) {
        List<PropiedadInmueble> elListado = new ArrayList();
        PropiedadInmueble rangoProp = null;
        Propietario rangoPropietario = null;
        EstadoLocal rangoEstadoLocal = null;
        TipoLocal rangoTipoLocal = null;
        Inspector rangoInspector = null;
        //Vendedor rangoVendedor = null;
        Zona rangoZona = null;
        String sql = "SELECT pi.*, prop.nombre, prop.apellido, prop.domicilio, prop.telefono, "
                + "el.nombre, ins.nombre, ins.apellido, tp.nombre, tp.superficieMinima, z.nombre, z.descripcion "
                + "FROM propiedadInmueble pi JOIN propietario prop on(pi.idPropietario=prop.idPropietario) "
                + "JOIN estadoLocal el on(pi.idEstadoLocal=el.idEstadoLocal) "
                + "JOIN inspector ins on(pi.idInspector=ins.idInspector) "
                + "JOIN tipoLocal tp on(pi.idTipoLocal=tp.idTipoLocal) "
                + "JOIN zona z on(pi.idZona=z.idZona) "
                + "WHERE pi.precioTrazado > " + ini + " AND pi.precioTrazado <" + fin + " AND "
                + "pi.idTipoLocal = " + id + " "
                + "ORDER BY idPropiedad";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //Construyo los objetos para el inmueble
                rangoPropietario = abmProp.buscopPropietario(rs.getInt("idPropietario"));
                rangoEstadoLocal = abmEstadoLocal.buscoEstadoLocal(rs.getInt("idEstadoLocal"));
                rangoTipoLocal = abmTipoLocal.buscoTipoLocal(rs.getInt("idTipoLocal"));
                rangoInspector = abmInsp.buscoInspector(rs.getInt("idInspector"));
                //rangoVendedor = abmVendedor.buscoVendedor(rs.getInt("idVendedor"));
                rangoZona = abmZona.buscarZona(rs.getInt("idZona"));
                //Construyo el objeto inmueble para agragar al listado
                rangoProp = new PropiedadInmueble();
                rangoProp.setIdPropiedadInmueble(rs.getInt(1));
                rangoProp.setPropietario(rangoPropietario);
                rangoProp.setAccesibilidad(rs.getString("accesibilidad"));
                rangoProp.setCaracteristicasString(rs.getString("caracteristicas"));
                rangoProp.setDireccion(rs.getString("direccion"));
                rangoProp.setEstadoLocal(rangoEstadoLocal);
                rangoProp.setPrecioTrazado(rs.getFloat("precioTrazado"));
                //rangoProp.setVendedor(rangoVendedor);
                rangoProp.setInspector(rangoInspector);
                rangoProp.setTipoLocal(rangoTipoLocal);
                rangoProp.setZona(rangoZona);
                rangoProp.setDisponibilidad(rs.getBoolean("disponibilidad"));
                elListado.add(rangoProp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
        return elListado;
    }

    public List<PropiedadInmueble> busquedaXZonaYPrecio(float min, float max, int idZona) {
        List<PropiedadInmueble> listaXZona = new ArrayList();
        PropiedadInmueble zonaInmueble = null;
        Propietario zonaPropietario = null;
        TipoLocal zonaTipo = null;
        EstadoLocal zonaEstado = null;
        Inspector zonainsInspector = null;
        //Vendedor zonaVendedor=null;
        Zona laZona = null;
        String sqlZona = "SELECT pi.*, prop.nombre, prop.apellido, prop.domicilio, prop.telefono, "
                + "el.nombre, ins.nombre, ins.apellido, tp.nombre, tp.superficieMinima, z.nombre, z.descripcion "
                + "FROM propiedadInmueble pi JOIN propietario prop on(pi.idPropietario=prop.idPropietario) "
                + "JOIN estadoLocal el on(pi.idEstadoLocal=el.idEstadoLocal) "
                + "JOIN inspector ins on(pi.idInspector=ins.idInspector) "
                + "JOIN tipoLocal tp on(pi.idTipoLocal=tp.idTipoLocal) "
                + "JOIN zona z on(pi.idZona=z.idZona)"
                + "where pi.idZona = " + idZona + " AND pi.precioTrazado > " + min + " AND pi.precioTrazado < " + max + " "
                + "ORDER BY idPropiedad";
        try {
            PreparedStatement ps = con.prepareStatement(sqlZona);
            ps.setInt(1, idZona);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //Empiezo a buscar los datos para construir los objetos relacionados con el inmueble
                zonaPropietario = abmProp.buscopPropietario(rs.getInt("idPropietario"));
                zonaTipo = abmTipoLocal.buscoTipoLocal(rs.getInt("idTipoLocal"));
                zonaEstado = abmEstadoLocal.buscoEstadoLocal(rs.getInt("idEstadoLocal"));
                zonainsInspector = abmInsp.buscoInspector(rs.getInt("idInspector"));
                //zonaVendedor=abmVendedor.buscoVendedor(rs.getInt("idVendedor"));
                laZona = abmZona.buscarZona(rs.getInt("idZona"));
                //Empiezo a construir el objeto inmueble
                zonaInmueble = new PropiedadInmueble();
                zonaInmueble.setIdPropiedadInmueble(rs.getInt(1));
                zonaInmueble.setPropietario(zonaPropietario);
                zonaInmueble.setAccesibilidad(rs.getString(3));
                zonaInmueble.setCaracteristicasString(rs.getString(4));
                zonaInmueble.setDireccion(rs.getString(5));
                zonaInmueble.setEstadoLocal(zonaEstado);
                zonaInmueble.setPrecioTrazado(rs.getFloat(7));
                //zonaInmueble.setVendedor(zonaVendedor);
                zonaInmueble.setInspector(zonainsInspector);
                zonaInmueble.setTipoLocal(zonaTipo);
                zonaInmueble.setZona(laZona);
                zonaInmueble.setDisponibilidad(rs.getBoolean(11));
                listaXZona.add(zonaInmueble);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
        return listaXZona;
    }

    public List<PropiedadInmueble> busquedaXZonaYTipoLocal(int idZona, int idTipoLocal) {
        List<PropiedadInmueble> listaXZona = new ArrayList();
        PropiedadInmueble zonaInmueble = null;
        Propietario zonaPropietario = null;
        TipoLocal zonaTipo = null;
        EstadoLocal zonaEstado = null;
        Inspector zonainsInspector = null;
        //Vendedor zonaVendedor=null;
        Zona laZona = null;
        String sqlZona = "SELECT pi.*, prop.nombre, prop.apellido, prop.domicilio, prop.telefono, "
                + "el.nombre, ins.nombre, ins.apellido, tp.nombre, tp.superficieMinima, z.nombre, z.descripcion "
                + "FROM propiedadInmueble pi JOIN propietario prop on(pi.idPropietario=prop.idPropietario) "
                + "JOIN estadoLocal el on(pi.idEstadoLocal=el.idEstadoLocal) "
                + "JOIN inspector ins on(pi.idInspector=ins.idInspector) "
                + "JOIN tipoLocal tp on(pi.idTipoLocal=tp.idTipoLocal) "
                + "JOIN zona z on(pi.idZona=z.idZona)"
                + "WHERE pi.idZona = " + idZona + " AND pi.idTipoLocal = " + idTipoLocal + " "
                + "ORDER BY idPropiedad";
        try {
            PreparedStatement ps = con.prepareStatement(sqlZona);
            ps.setInt(1, idZona);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //Empiezo a buscar los datos para construir los objetos relacionados con el inmueble
                zonaPropietario = abmProp.buscopPropietario(rs.getInt("idPropietario"));
                zonaTipo = abmTipoLocal.buscoTipoLocal(rs.getInt("idTipoLocal"));
                zonaEstado = abmEstadoLocal.buscoEstadoLocal(rs.getInt("idEstadoLocal"));
                zonainsInspector = abmInsp.buscoInspector(rs.getInt("idInspector"));
                //zonaVendedor=abmVendedor.buscoVendedor(rs.getInt("idVendedor"));
                laZona = abmZona.buscarZona(rs.getInt("idZona"));
                //Empiezo a construir el objeto inmueble
                zonaInmueble = new PropiedadInmueble();
                zonaInmueble.setIdPropiedadInmueble(rs.getInt(1));
                zonaInmueble.setPropietario(zonaPropietario);
                zonaInmueble.setAccesibilidad(rs.getString(3));
                zonaInmueble.setCaracteristicasString(rs.getString(4));
                zonaInmueble.setDireccion(rs.getString(5));
                zonaInmueble.setEstadoLocal(zonaEstado);
                zonaInmueble.setPrecioTrazado(rs.getFloat(7));
                //zonaInmueble.setVendedor(zonaVendedor);
                zonaInmueble.setInspector(zonainsInspector);
                zonaInmueble.setTipoLocal(zonaTipo);
                zonaInmueble.setZona(laZona);
                zonaInmueble.setDisponibilidad(rs.getBoolean(11));
                listaXZona.add(zonaInmueble);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
        return listaXZona;
    }

    public List<PropiedadInmueble> busquedaXZona_Precio_TipoLocal(float min, float max, int idZona, int idTipoLocal) {
        List<PropiedadInmueble> listaXZona = new ArrayList();
        PropiedadInmueble zonaInmueble = null;
        Propietario zonaPropietario = null;
        TipoLocal zonaTipo = null;
        EstadoLocal zonaEstado = null;
        Inspector zonainsInspector = null;
        //Vendedor zonaVendedor=null;
        Zona laZona = null;
        String sqlZona = "SELECT pi.*, prop.nombre, prop.apellido, prop.domicilio, prop.telefono, "
                + "el.nombre, ins.nombre, ins.apellido, tp.nombre, tp.superficieMinima, z.nombre, z.descripcion "
                + "FROM propiedadInmueble pi JOIN propietario prop on(pi.idPropietario=prop.idPropietario) "
                + "JOIN estadoLocal el on(pi.idEstadoLocal=el.idEstadoLocal) "
                + "JOIN inspector ins on(pi.idInspector=ins.idInspector) "
                + "JOIN tipoLocal tp on(pi.idTipoLocal=tp.idTipoLocal) "
                + "JOIN zona z on(pi.idZona=z.idZona)"
                + "where pi.idZona = " + idZona + " AND pi.precioTrazado > " + min + " AND pi.precioTrazado < " + max + " "
                + "AND pi.idTipoLocal = " + idTipoLocal + " "
                + "ORDER BY idPropiedad";
        try {
            PreparedStatement ps = con.prepareStatement(sqlZona);
            ps.setInt(1, idZona);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //Empiezo a buscar los datos para construir los objetos relacionados con el inmueble
                zonaPropietario = abmProp.buscopPropietario(rs.getInt("idPropietario"));
                zonaTipo = abmTipoLocal.buscoTipoLocal(rs.getInt("idTipoLocal"));
                zonaEstado = abmEstadoLocal.buscoEstadoLocal(rs.getInt("idEstadoLocal"));
                zonainsInspector = abmInsp.buscoInspector(rs.getInt("idInspector"));
                //zonaVendedor=abmVendedor.buscoVendedor(rs.getInt("idVendedor"));
                laZona = abmZona.buscarZona(rs.getInt("idZona"));
                //Empiezo a construir el objeto inmueble
                zonaInmueble = new PropiedadInmueble();
                zonaInmueble.setIdPropiedadInmueble(rs.getInt(1));
                zonaInmueble.setPropietario(zonaPropietario);
                zonaInmueble.setAccesibilidad(rs.getString(3));
                zonaInmueble.setCaracteristicasString(rs.getString(4));
                zonaInmueble.setDireccion(rs.getString(5));
                zonaInmueble.setEstadoLocal(zonaEstado);
                zonaInmueble.setPrecioTrazado(rs.getFloat(7));
                //zonaInmueble.setVendedor(zonaVendedor);
                zonaInmueble.setInspector(zonainsInspector);
                zonaInmueble.setTipoLocal(zonaTipo);
                zonaInmueble.setZona(laZona);
                zonaInmueble.setDisponibilidad(rs.getBoolean(11));
                listaXZona.add(zonaInmueble);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
        return listaXZona;
    }

    public void ActualizarPreciosPorTipoLocal(float precio, int idTipoLocal) {
        String sql = "UPDATE propiedadInmueble SET precioTrazado = ? WHERE idPropiedad = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setFloat(1, precio);
            ps.setInt(2, idTipoLocal);
            int valor = ps.executeUpdate();
//            if(valor>0){
//                JOptionPane.showMessageDialog(null, "Los precios se han actualizado correctamente");
//            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }

    }

    public void ActualizarPreciosPorZOna(float precio, int idZona) {
        String sql = "UPDATE propiedadInmueble SET precioTrazado = ? WHERE idPropiedad = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setFloat(1, precio);
            ps.setInt(2, idZona);
            int valor = ps.executeUpdate();
//            if(valor>0){
//                JOptionPane.showMessageDialog(null, "Los precios se han actualizado correctamente");
//            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }

    }
    
    
    public boolean estaAlquilada(int idProp){
        String cadena = "SELECT * FROM contratoAlquiler WHERE idPropiedadInmueble = ?";
        boolean salida=false;
        try {
            PreparedStatement ps = con.prepareStatement(cadena);
            ps.setInt(1, idProp);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                salida=true;
            }
        } catch (Exception e) {
        }
        return salida;
    }

}
