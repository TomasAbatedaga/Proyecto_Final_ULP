/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author kalema
 */
public class EstadoLocal {
    private int idEstadoLocal;
    private String nombre;
    private String descripcion;
    
    public EstadoLocal(){}

    public EstadoLocal(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public EstadoLocal(int idEstadoLocal, String nombre, String descripcion) {
        this.idEstadoLocal = idEstadoLocal;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdEstadoLocal() {
        return idEstadoLocal;
    }

    public void setIdEstadoLocal(int idEstadoLocal) {
        this.idEstadoLocal = idEstadoLocal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "EstadoLocal{" + "idEstadoLocal=" + idEstadoLocal + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    
}
