package Main;

import AccesoDatos.Conectar;
import Vistas.MenuPrincipal;
//import AccesoDatos.EstadoLocalData;
//import AccesoDatos.InquilinoData;
//import AccesoDatos.InspectorData;
//import AccesoDatos.PropiedadData;
//import AccesoDatos.PropietarioData;
//import AccesoDatos.TipoLocalData;
//import AccesoDatos.VendedorData;
//import AccesoDatos.ZonaData;
//import Entidades.EstadoLocal;
//import Entidades.Inquilino;
//import Entidades.Inspector;
//import Entidades.PropiedadInmueble;
//import Entidades.Propietario;
//import Entidades.TipoLocal;
//import Entidades.Vendedor;
//import Entidades.Zona;

public class Proyecto_Final {

    public static void main(String[] args) {
        Conectar.getConectar();

        MenuPrincipal.main(args);
        
//        Propietario creoprop=new Propietario("Martín", "Del Potro", 27618971, 55557865, "La Rivera 1655");
//        Inquilino creoInquilino=new Inquilino("30287198908", "Marga", "Agerez", "la Luna", 28976144, "Te Garantizo");
//        EstadoLocal creoEstadoLocal=new EstadoLocal();
//        Inspector creoInspector=new Inspector();
//        TipoLocal creoTipoLocal=new TipoLocal();
//        Vendedor creoVendedor=new Vendedor();
//        Zona creoZona=new Zona();
//        // hago los abm de las clases
//        InquilinoData abmInquilino=new InquilinoData();
//        PropietarioData abmPropData=new PropietarioData();
//        EstadoLocalData abmEstLocal=new EstadoLocalData();
//        InspectorData abmInspector=new InspectorData();
//        TipoLocalData abmTipoLocal=new TipoLocalData();
//        VendedorData abmVendedor=new VendedorData();
//        ZonaData abmZona=new ZonaData();
//        
//        //abmPropData.agrearPropiedad(creoprop);
//        //abmInquilino.guardarInquilino(creoInquilino);
//        creoprop=abmPropData.buscopPropietario(1);
//        creoInquilino=abmInquilino.buscoInquilino(1);
//        creoEstadoLocal=abmEstLocal.buscoEstadoLocal(4);
//        creoInspector=abmInspector.buscoInspector(2);
//        creoTipoLocal=abmTipoLocal.buscoTipoLocal(3);
//        creoVendedor=abmVendedor.buscoVendedor(2);
//        creoZona=abmZona.buscarZona(6);
//        
//        //Uso los procedimientos de propiedad inmuelble
//        PropiedadData abmPropiedad=new PropiedadData();
//        //idpropietario,accesibilidad,caracteristicas,direccion,
//        //idEstadoLocal,precioTrazado,idInspector,idTipoLocal,idZona,disponibilidad
//        PropiedadInmueble laProp=new PropiedadInmueble();
//        laProp.setPropietario(creoprop);
//        laProp.setAccesibilidad("Colectivo, trenes");
//        laProp.setCaracteristicasString("No tenemos datos");
//        laProp.setEstadoLocal(creoEstadoLocal);
//        laProp.setPrecioTrazado((float) 23400.65);
//        laProp.setInspector(creoInspector);
//        laProp.setTipoLocal(creoTipoLocal);
//        laProp.setZona(creoZona);
//        laProp.setDisponibilidad(true);
//        
//        abmPropiedad.guardarPropiedad(laProp);
    }
    
}
