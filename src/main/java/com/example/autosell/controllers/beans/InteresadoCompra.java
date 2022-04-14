package com.example.autosell.controllers.beans;


import java.util.ArrayList;
import java.util.List;

public class InteresadoCompra {

    Long autoSemiNuevoId;

    String placa;

    String nombres;

    String numTelefono;

    String correo;

    String dni;

    String apellidos;

    public Long getAutoSemiNuevoId() {
        return autoSemiNuevoId;
    }

    public void setAutoSemiNuevoId(Long autoSemiNuevoId) {
        this.autoSemiNuevoId = autoSemiNuevoId;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    private String checkVoid(Object temp) {
        return temp == null ? "null" : temp.toString();
    }
    public List<Object> serialize() {
        List<Object> serializedData = new ArrayList<>();

        serializedData.add(checkVoid(autoSemiNuevoId));
        serializedData.add(checkVoid(placa));
        serializedData.add(checkVoid(nombres));
        serializedData.add(checkVoid(numTelefono));
        serializedData.add(checkVoid(correo));
        serializedData.add(checkVoid(dni));
        serializedData.add(checkVoid(apellidos));

        return serializedData;

    }
}