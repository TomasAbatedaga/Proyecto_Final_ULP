/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author abate
 */
public class TipoLocal {
    private int idTipoLocal;
    private String nombre;
    private String descripcion;
    private double superficieMinima;

    public TipoLocal() {
    }

    public TipoLocal(String nombre, String descripcion, double superficieMinima) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.superficieMinima = superficieMinima;
    }

    public TipoLocal(int idTipoLocal, String nombre, String descripcion, double superficieMinima) {
        this.idTipoLocal = idTipoLocal;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.superficieMinima = superficieMinima;
    }

    public int getIdTipoLocal() {
        return idTipoLocal;
    }

    public void setIdTipoLocal(int idTipoLocal) {
        this.idTipoLocal = idTipoLocal;
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

    public double getSuperficieMinima() {
        return superficieMinima;
    }

    public void setSuperficieMinima(double superficieMinima) {
        this.superficieMinima = superficieMinima;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
