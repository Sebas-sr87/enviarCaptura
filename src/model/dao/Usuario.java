/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

/**
 *
 * @author Sebastian Oliveros
 */
public class Usuario {
    public  int id_usuario;
    public  String nombre;
    public  String apellido;
    public  boolean estado; 
    public static Usuario usuario;
    public String correo;

    public Usuario(int id_usuario, String nombre, String apellido, boolean estado,String correo) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
        this.correo = correo;
    }

    public Usuario() {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String nombre) {
        this.correo = nombre;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
