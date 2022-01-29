package com.example.autosell.controllers.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class InteresadoCompra {

    Long autoSemiNuevoId;

    String nombres;

    String numTelefono;

    String correo;

    String dni;

    String apellidos;

    private String checkVoid(Object temp) {
        return temp == null ? "null" : temp.toString();
    }
    public List<Object> serialize() {
        List<Object> serializedData = new ArrayList<>();

        serializedData.add(checkVoid(autoSemiNuevoId));
        serializedData.add(checkVoid(nombres));
        serializedData.add(checkVoid(numTelefono));
        serializedData.add(checkVoid(correo));
        serializedData.add(checkVoid(dni));
        serializedData.add(checkVoid(apellidos));

        return serializedData;

    }
}