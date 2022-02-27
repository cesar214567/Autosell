package com.example.autosell.entities;

import com.example.autosell.utils.entities.Accesorio;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class AutoSemiNuevo implements Cloneable {
    @Column(name = "id_auto_semi_nuevo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(length = 35)
    String placa;

    @Column(length = 35)
    String serie;

    @Column
    String vin;

    @Column
    String correoDueno;

    @Column
    String nombreDueno;

    @Column(length = 20)
    String telefonoDueno;

    @Column(length = 100)
    String marca;

    @Column(length = 100)
    String modelo;

    @Column
    Integer anoFabricacion;

    @Column(length = 100)
    String tipoCambios;

    @Column(length = 100)
    String tipoCombustible;

    @Column(length = 100)
    String tipoCarroceria;

    @Column
    Float cilindrada;

    @Column
    Long kilometraje;

    @Column
    Integer numeroPuertas;

    @Column(length = 50)
    String tipoTraccion;

    @Column(length = 50)
    String color;

    @Column(length = 100)
    Integer numeroCilindros;

    @Column
    Float precioVenta;

    @Column
    String locacion;

    @Column
    Boolean comprado;

    @Column
    Boolean enabled;

    @Column
    Boolean validado;

    @Column
    Date fechaPublicacion;

    @ManyToMany
    List<Accesorio> accesorios;

    @Column(length = 1000)
    String fotoPrincipal;

    @Column(length = 1000)
    String descripcion;

    @Column
    String version;

    @Column
    String mantenimiento;

    @Column
    String tag;

    @Column
    Boolean unicoDueno;

    @Column
    Integer choques;

    @Column
    Boolean fallaMecanica;

    @Column
    Boolean llaves;

    @Column
    Boolean fumado;

    @ElementCollection
    List<String> fotos;

    @JoinColumn(name = "registrador")
    @JsonIgnoreProperties({"password"})
    @ManyToOne
    Users registrador;

    @Column
    String canalRegistro;

    @Column
    String detalleRegistro;

    @JoinColumn(name = "sellout")
    @JsonIgnoreProperties({"password"})
    @OneToOne
    Users sellout;

    @Column
    String canalVenta;

    @Column
    String detalleVenta;

    @Column
    Boolean ruc;

    @Column
    String documento;

    @Column
    String dniComprador;

    @Column
    String nombreComprador;

    @Column
    Float precioFinalVenta;

    @Column
    Float comision;

    @Column
    Float margen;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void info(AutoSemiNuevo autoSemiNuevo){


        if(this.correoDueno!=null)autoSemiNuevo.setCorreoDueno(this.correoDueno);
        if(this.nombreDueno!=null)autoSemiNuevo.setNombreDueno(this.nombreDueno);
        if(this.telefonoDueno!=null)autoSemiNuevo.setTelefonoDueno(this.telefonoDueno);
        if(this.anoFabricacion!=null)autoSemiNuevo.setAnoFabricacion(this.anoFabricacion);
        if(this.tipoCambios!=null)autoSemiNuevo.setTipoCambios(this.tipoCambios);
        if(this.tipoCombustible!=null)autoSemiNuevo.setTipoCombustible(this.tipoCombustible);
        if(this.tipoCarroceria!=null)autoSemiNuevo.setTipoCarroceria(this.tipoCarroceria);
        if(this.cilindrada!=null)autoSemiNuevo.setCilindrada(this.cilindrada);
        if(this.kilometraje!=null)autoSemiNuevo.setKilometraje(this.kilometraje);
        if(this.numeroPuertas!=null)autoSemiNuevo.setNumeroPuertas(this.numeroPuertas);
        if(this.tipoTraccion!=null)autoSemiNuevo.setTipoTraccion(this.tipoTraccion);
        if(this.color!=null)autoSemiNuevo.setColor(this.color);
        if(this.numeroCilindros!=null)autoSemiNuevo.setNumeroCilindros(this.numeroCilindros);
        if(this.precioVenta!=null)autoSemiNuevo.setPrecioVenta(this.precioVenta);
        if(this.fechaPublicacion!=null)autoSemiNuevo.setFechaPublicacion(this.fechaPublicacion);
        if(this.accesorios!=null)autoSemiNuevo.setAccesorios(this.accesorios);
        if(this.descripcion!=null)autoSemiNuevo.setDescripcion(this.descripcion);
        if(this.version!=null)autoSemiNuevo.setVersion(this.version);
        if(this.mantenimiento!=null)autoSemiNuevo.setMantenimiento(this.mantenimiento);
        if(this.unicoDueno!=null)autoSemiNuevo.setUnicoDueno(this.unicoDueno);
        if(this.choques!=null)autoSemiNuevo.setChoques(this.choques);
        if(this.fallaMecanica!=null)autoSemiNuevo.setFallaMecanica(this.fallaMecanica);
        if(this.llaves!=null)autoSemiNuevo.setLlaves(this.llaves);
        if(this.fumado!=null)autoSemiNuevo.setFumado(this.fumado);
        if(this.vin!=null)autoSemiNuevo.setVin(this.vin);
        if(this.enabled!=null)autoSemiNuevo.setEnabled(this.enabled);
        if(this.validado!=null)autoSemiNuevo.setValidado(this.validado);


    }
    public void reverseInfo(AutoSemiNuevo autoSemiNuevo){
        this.setComprado(autoSemiNuevo.getComprado());
        this.setFotoPrincipal(autoSemiNuevo.getFotoPrincipal());
        this.setFotos(autoSemiNuevo.getFotos());
        this.setId(autoSemiNuevo.getId());
        this.setPlaca(autoSemiNuevo.getPlaca());
        this.setMarca(autoSemiNuevo.getMarca());
        this.setModelo(autoSemiNuevo.getModelo());
        this.setSerie(autoSemiNuevo.getSerie());

    }

    public void sellInfo(AutoSemiNuevo autoSemiNuevo){
        if(this.sellout!=null)autoSemiNuevo.setSellout(this.sellout);
        if(this.canalVenta!=null)autoSemiNuevo.setCanalVenta(this.canalVenta);
        if(this.detalleVenta!=null)autoSemiNuevo.setDetalleVenta(this.detalleVenta);
        if(this.ruc!=null)autoSemiNuevo.setRuc(this.ruc);
        if(this.documento!=null)autoSemiNuevo.setDocumento(this.documento);
        if(this.dniComprador!=null)autoSemiNuevo.setDniComprador(this.dniComprador);
        if(this.nombreComprador!=null)autoSemiNuevo.setNombreComprador(this.nombreComprador);
        if(this.precioFinalVenta!=null)autoSemiNuevo.setPrecioFinalVenta(this.precioFinalVenta);
        if(this.comision!=null)autoSemiNuevo.setComision(this.comision);

    }

    public List<Object> serializeSell() {
        List<Object> serializedData = new ArrayList<>();

        serializedData.add(checkVoid(id));
        if (registrador != null) {
            serializedData.add(checkVoid(registrador.getEmail()));
        } else {
            serializedData.add("null");
        }
        serializedData.add(checkVoid(canalRegistro));
        serializedData.add(checkVoid(detalleRegistro));
        if (sellout != null){
            serializedData.add(checkVoid(sellout.getEmail()));
        }else{
            serializedData.add("null");
        }
        serializedData.add(checkVoid(canalVenta));
        serializedData.add(checkVoid(detalleVenta));
        serializedData.add(checkVoid(ruc));
        serializedData.add(checkVoid(documento));
        serializedData.add(checkVoid(dniComprador));
        serializedData.add(checkVoid(nombreComprador));
        serializedData.add(checkVoid(precioFinalVenta));
        serializedData.add(checkVoid(comision));
        serializedData.add(checkVoid(margen));
        return serializedData;
    }


    public String checkVoid(Object temp) {
        return temp == null ? "null" : temp.toString();
    }

    public List<Object> serialize(){
        List<Object> serializedData = new ArrayList<>();

        serializedData.add(checkVoid(id));
        serializedData.add(checkVoid(anoFabricacion));
        serializedData.add(checkVoid(choques));
        serializedData.add(checkVoid(cilindrada));
        serializedData.add(checkVoid(color));
        serializedData.add(checkVoid(comprado));
        serializedData.add(checkVoid(correoDueno));
        serializedData.add(checkVoid(descripcion));
        serializedData.add(checkVoid(fallaMecanica));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        serializedData.add(checkVoid(dateFormat.format(fechaPublicacion)));

        serializedData.add(checkVoid(fotoPrincipal));
        serializedData.add(checkVoid(fumado));
        serializedData.add(checkVoid(kilometraje));
        serializedData.add(checkVoid(llaves));
        serializedData.add(checkVoid(locacion));
        serializedData.add(checkVoid(mantenimiento));
        serializedData.add(checkVoid(marca));
        serializedData.add(checkVoid(modelo));
        serializedData.add(checkVoid(nombreDueno));
        serializedData.add(checkVoid(numeroCilindros));
        serializedData.add(checkVoid(numeroPuertas));
        serializedData.add(checkVoid(placa));
        serializedData.add(checkVoid(precioVenta));
        serializedData.add(checkVoid(serie));
        serializedData.add(checkVoid(telefonoDueno));
        serializedData.add(checkVoid(tipoCambios));
        serializedData.add(checkVoid(tipoCarroceria));
        serializedData.add(checkVoid(tipoCombustible));
        serializedData.add(checkVoid(tipoTraccion));
        serializedData.add(checkVoid(unicoDueno));
        serializedData.add(checkVoid(version));
        serializedData.add(checkVoid(vin));

        serializedData.addAll(fotos);

        return serializedData;
    }



}
