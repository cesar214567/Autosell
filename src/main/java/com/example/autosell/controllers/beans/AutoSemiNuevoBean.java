package com.example.autosell.controllers.beans;

import com.example.autosell.entities.Users;
import com.example.autosell.utils.entities.Accesorio;
import lombok.Data;
import net.minidev.json.JSONObject;

import java.util.Date;
import java.util.List;

@Data
public class AutoSemiNuevoBean {

    Long id;

    String placa;

    String serie;

    String vin;

    String correoDueno;

    String nombreDueno;

    String telefonoDueno;

    String marca;

    String modelo;

    Integer anoFabricacion;

    String tipoCambios;

    String tipoCombustible;

    String tipoCarroceria;

    Float cilindrada;

    Long kilometraje;

    Integer numeroPuertas;

    String tipoTraccion;

    String color;

    Integer numeroCilindros;

    Float precioVenta;

    String locacion;

    Boolean comprado;

    Boolean enabled;

    Boolean validado;

    String fechaPublicacion;

    String fotoPrincipal;

    String descripcion;

    String version;

    String mantenimiento;

    String tag;

    String canalRegistro;

    String detalleRegistro;

    String canalVenta;

    String detalleVenta;

    Boolean ruc;

    String documento;

    String nombreComprador;

    Float precioFinalVenta;

    Float comision;

    Float margen;

    String fechaVenta;

    public AutoSemiNuevoBean(JSONObject jsonObject) {
        this.id = Long.parseLong(jsonObject.getAsString("id_auto_semi_nuevo")) ;
        this.placa = jsonObject.getAsString("placa");
        this.vin = jsonObject.getAsString("vin");
        this.serie = jsonObject.getAsString("serie");
        this.correoDueno = jsonObject.getAsString("correo_dueno");
        this.nombreDueno = jsonObject.getAsString("nombre_dueno");
        this.telefonoDueno = jsonObject.getAsString("telefono_dueno");
        this.marca = jsonObject.getAsString("marca");
        this.modelo = jsonObject.getAsString("modelo");
        this.anoFabricacion = (Integer) jsonObject.get("ano_fabricacion");
        this.tipoCambios = jsonObject.getAsString("tipo_cambios");
        this.tipoCombustible = jsonObject.getAsString("tipo_combustible");
        this.tipoCarroceria = jsonObject.getAsString("tipo_carroceria");
        this.cilindrada = (Float) jsonObject.get("cilindrada");
        this.kilometraje = Long.parseLong(jsonObject.getAsString("kilometraje"));
        this.numeroPuertas = (Integer) jsonObject.get("numero_puertas");
        this.tipoTraccion = jsonObject.getAsString("tipo_traccion");
        this.color = jsonObject.getAsString("color");
        this.numeroCilindros = (Integer) jsonObject.get("numero_cilindros");
        this.precioVenta = (Float) jsonObject.get("precio_venta");
        this.locacion = jsonObject.getAsString("locacion");
        this.comprado = (Boolean) jsonObject.get("comprado");
        this.enabled = (Boolean) jsonObject.get("enabled");
        this.validado = (Boolean) jsonObject.get("validado");
        this.fechaPublicacion = jsonObject.getAsString("fecha_publicacion");
        this.fotoPrincipal = jsonObject.getAsString("foto_principal");
        this.descripcion = jsonObject.getAsString("descripcion");
        this.version = jsonObject.getAsString("version");
        this.mantenimiento = jsonObject.getAsString("mantenimiento");
        this.tag = jsonObject.getAsString("tag");
        this.canalRegistro = jsonObject.getAsString("canal_registro");
        this.detalleRegistro = jsonObject.getAsString("detalle_registro");
        this.canalVenta = jsonObject.getAsString("canal_venta");
        this.detalleVenta = jsonObject.getAsString("detalle_venta");
        this.ruc = (Boolean) jsonObject.get("ruc");
        this.documento = jsonObject.getAsString("documento");
        this.nombreComprador = jsonObject.getAsString("nombre_comprador");
        this.precioFinalVenta = (Float) jsonObject.get("precio_final_venta");
        this.comision = (Float) jsonObject.get("comision");
        this.margen = (Float) jsonObject.get("margen");
        this.fechaVenta = jsonObject.getAsString("fecha_venta");
    }
}
