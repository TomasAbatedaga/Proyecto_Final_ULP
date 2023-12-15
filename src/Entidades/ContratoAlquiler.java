package Entidades;

import java.time.LocalDate;


/*
 * @author manuv
 */

public class ContratoAlquiler {
    private int idContratoAlquiler;
    private Inquilino inquilino;
    private PropiedadInmueble propiedadInmueble;
    private LocalDate fechaFinal;
    private LocalDate fechaInicio;
    private LocalDate fechaRealizacion;
    private final String marca = "La43";
    private Vendedor vendedor;
    private EstadoContrato estado;
    
    public ContratoAlquiler(){};

    public ContratoAlquiler(Inquilino inquilino, 
            PropiedadInmueble PropiedadInmueble, LocalDate fechaFinal, 
            LocalDate fechaInicio, LocalDate fechaRealizacion, 
            Vendedor vendedor, EstadoContrato estado) {
        
        this.inquilino = inquilino;
        this.propiedadInmueble = PropiedadInmueble;
        this.fechaFinal = fechaFinal;
        this.fechaInicio = fechaInicio;
        this.fechaRealizacion = fechaRealizacion;
        this.vendedor = vendedor;
        this.estado = estado;
    }

    public ContratoAlquiler(int idContratoAlquiler, Inquilino inquilino, 
            PropiedadInmueble PropiedadInmueble, LocalDate fechaFinal, 
            LocalDate fechaInicio, LocalDate fechaRealizacion, 
            Vendedor vendedor, EstadoContrato estado) {
        
        this.idContratoAlquiler = idContratoAlquiler;
        this.inquilino = inquilino;
        this.propiedadInmueble = PropiedadInmueble;
        this.fechaFinal = fechaFinal;
        this.fechaInicio = fechaInicio;
        this.fechaRealizacion = fechaRealizacion;
        this.vendedor = vendedor;
        this.estado = estado;
    }

    
    public int getIdContratoAlquiler() {
        return idContratoAlquiler;
    }

    public void setIdContratoAlquiler(int idContratoAlquiler) {
        this.idContratoAlquiler = idContratoAlquiler;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public PropiedadInmueble getPropiedadInmueble() {
        return propiedadInmueble;
    }

    public void setPropiedadInmueble(PropiedadInmueble idPropiedadInmueble) {
        this.propiedadInmueble = idPropiedadInmueble;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDate fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor idVendedor) {
        this.vendedor = idVendedor;
    }

    public String getMarca() {
        return marca;
    }

    public EstadoContrato getEstado() {
        return estado;
    }

    public void setEstado(EstadoContrato estado) {
        this.estado = estado;
    }
    
    
    
    @Override
    public String toString() {
        return "contratoAlquiler{" + "idContratoAlquiler=" + idContratoAlquiler + ", idInquilino=" + inquilino + ", idPropiedadInmueble=" + propiedadInmueble + ", fechaFinal=" + fechaFinal + ", fechaInicio=" + fechaInicio + ", fechaRealizacion=" + fechaRealizacion + ", marca=" + marca + ", idVendedor=" + vendedor +", estado="+estado.getNombre()+ '}';
    }
    
}
