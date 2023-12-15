/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author manuv
 */
public class EstadoContrato {
    private int idEstadoContrato;
    private String nombre;
    
    public EstadoContrato(){}

    public EstadoContrato(int idEstadoContrato, String nombre) {
        this.idEstadoContrato = idEstadoContrato;
        this.nombre = nombre;
    }

    public int getIdEstadoContrato() {
        return idEstadoContrato;
    }

    public void setIdEstadoContrato(int idEstadoContrato) {
        this.idEstadoContrato = idEstadoContrato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EstadoContrato{" + "idEstadoContrato=" + idEstadoContrato + ", nombre=" + nombre + '}';
    }
    
    
    
}
