/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author abate
 */
public class Inquilino {
    private int idInquilino;
    private String cuit;
    private String nombre;
    private String apellido;
    private String lugarTrabajo;
    private int dniGarante;
    private String nombreGarante;

    public Inquilino() {
    }

    public Inquilino(String cuit, String nombre, String apellido, String lugarTrabajo, int dniGarante, String nombreGarante) {
        this.cuit = cuit;
        this.nombre = nombre;
        this.apellido = apellido;
        this.lugarTrabajo = lugarTrabajo;
        this.dniGarante = dniGarante;
        this.nombreGarante = nombreGarante;
    }

    public Inquilino(int idInquilino, String cuit, String nombre, String apellido, String lugarTrabajo, int dniGarante, String nombreGarante) {
        this.idInquilino = idInquilino;
        this.cuit = cuit;
        this.nombre = nombre;
        this.apellido = apellido;
        this.lugarTrabajo = lugarTrabajo;
        this.dniGarante = dniGarante;
        this.nombreGarante = nombreGarante;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
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

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public int getDniGarante() {
        return dniGarante;
    }

    public void setDniGarante(int dniGarante) {
        this.dniGarante = dniGarante;
    }

    public String getNombreGarante() {
        return nombreGarante;
    }

    public void setNombreGarante(String nombreGarante) {
        this.nombreGarante = nombreGarante;
    }

    @Override
    public String toString() {
        return "Inquilino{" + "idInquilino=" + idInquilino + ", cuit=" + cuit + ", nombre=" + nombre + ", apellido=" + apellido + ", lugarTrabajo=" + lugarTrabajo + ", dniGarante=" + dniGarante + ", nombreGarante=" + nombreGarante + '}';
    }
    
    public String otraForma(){
        return nombre + " " + apellido;
    }
}
