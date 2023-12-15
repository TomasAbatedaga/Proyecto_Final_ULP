package Entidades;

/**
 *
 * @author manuv
 */
public class Vendedor {
    private int idVendedor;
    private String nombre;
    private String apellido;
    private int cantidadVentas;
    private boolean estado;
    
    public Vendedor(){}

    public Vendedor(String nombre, String apellido, int cantidadVentas, boolean estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cantidadVentas = cantidadVentas;
        this.estado = estado;
    }

    public Vendedor(int idVendedor, String nombre, String apellido, int cantidadVentas, boolean estado) {
        this.idVendedor = idVendedor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cantidadVentas = cantidadVentas;
        this.estado = estado;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return  nombre + " " + apellido ;
    }
    
}
