package com.example.autosell.entities;

import com.example.autosell.utils.entities.Accesorio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Lazy
public class AutoSemiNuevo implements Cloneable, Serializable {
    @Column(name = "id_auto_semi_nuevo")
    @GeneratedValue(generator = "auto-semi-nuevo-generator")
    @GenericGenerator(
            name = "auto-semi-nuevo-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "auto_semi_nuevo_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "118"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
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

    @Lazy
    @ManyToMany(fetch = FetchType.LAZY)
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

    @ElementCollection
    List<String> fotos;

    @Lazy
    @JoinColumn(name = "registrador")
    @JsonIgnoreProperties({"password"})
    @ManyToOne()
    Users registrador;

    @Column
    String canalRegistro;

    @Column
    String detalleRegistro;

    @Lazy
    @JoinColumn(name = "sellout")
    @JsonIgnoreProperties({"password"})
    @ManyToOne()
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
    String nombreComprador;

    @Column
    Float precioFinalVenta;

    @Column
    Float comision;

    @Column
    Float margen;

    @Column
    Date fechaVenta;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCorreoDueno() {
        return correoDueno;
    }

    public void setCorreoDueno(String correoDueno) {
        this.correoDueno = correoDueno;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getTelefonoDueno() {
        return telefonoDueno;
    }

    public void setTelefonoDueno(String telefonoDueno) {
        this.telefonoDueno = telefonoDueno;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnoFabricacion() {
        return anoFabricacion;
    }

    public void setAnoFabricacion(Integer anoFabricacion) {
        this.anoFabricacion = anoFabricacion;
    }

    public String getTipoCambios() {
        return tipoCambios;
    }

    public void setTipoCambios(String tipoCambios) {
        this.tipoCambios = tipoCambios;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public String getTipoCarroceria() {
        return tipoCarroceria;
    }

    public void setTipoCarroceria(String tipoCarroceria) {
        this.tipoCarroceria = tipoCarroceria;
    }

    public Float getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(Float cilindrada) {
        this.cilindrada = cilindrada;
    }

    public Long getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(Long kilometraje) {
        this.kilometraje = kilometraje;
    }

    public Integer getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(Integer numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }

    public String getTipoTraccion() {
        return tipoTraccion;
    }

    public void setTipoTraccion(String tipoTraccion) {
        this.tipoTraccion = tipoTraccion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getNumeroCilindros() {
        return numeroCilindros;
    }

    public void setNumeroCilindros(Integer numeroCilindros) {
        this.numeroCilindros = numeroCilindros;
    }

    public Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getLocacion() {
        return locacion;
    }

    public void setLocacion(String locacion) {
        this.locacion = locacion;
    }

    public Boolean getComprado() {
        return comprado;
    }

    public void setComprado(Boolean comprado) {
        this.comprado = comprado;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getValidado() {
        return validado;
    }

    public void setValidado(Boolean validado) {
        this.validado = validado;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public List<Accesorio> getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(List<Accesorio> accesorios) {
        this.accesorios = accesorios;
    }

    public String getFotoPrincipal() {
        return fotoPrincipal;
    }

    public void setFotoPrincipal(String fotoPrincipal) {
        this.fotoPrincipal = fotoPrincipal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(String mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public Users getRegistrador() {
        return registrador;
    }

    public void setRegistrador(Users registrador) {
        this.registrador = registrador;
    }

    public String getCanalRegistro() {
        return canalRegistro;
    }

    public void setCanalRegistro(String canalRegistro) {
        this.canalRegistro = canalRegistro;
    }

    public String getDetalleRegistro() {
        return detalleRegistro;
    }

    public void setDetalleRegistro(String detalleRegistro) {
        this.detalleRegistro = detalleRegistro;
    }

    public Users getSellout() {
        return sellout;
    }

    public void setSellout(Users sellout) {
        this.sellout = sellout;
    }

    public String getCanalVenta() {
        return canalVenta;
    }

    public void setCanalVenta(String canalVenta) {
        this.canalVenta = canalVenta;
    }

    public String getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(String detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public Boolean getRuc() {
        return ruc;
    }

    public void setRuc(Boolean ruc) {
        this.ruc = ruc;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public Float getPrecioFinalVenta() {
        return precioFinalVenta;
    }

    public void setPrecioFinalVenta(Float precioFinalVenta) {
        this.precioFinalVenta = precioFinalVenta;
    }

    public Float getComision() {
        return comision;
    }

    public void setComision(Float comision) {
        this.comision = comision;
    }

    public Float getMargen() {
        return margen;
    }

    public void setMargen(Float margen) {
        this.margen = margen;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
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
        if(this.vin!=null)autoSemiNuevo.setVin(this.vin);
        if(this.enabled!=null)autoSemiNuevo.setEnabled(this.enabled);
        if(this.validado!=null)autoSemiNuevo.setValidado(this.validado);
        if(this.tag!=null)autoSemiNuevo.setTag(this.tag);


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
        serializedData.add(checkVoid(nombreComprador));
        serializedData.add(checkVoid(precioFinalVenta));
        serializedData.add(checkVoid(comision));
        serializedData.add(checkVoid(margen));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        serializedData.add(checkVoid(dateFormat.format(fechaVenta)));

        return serializedData;
    }


    public String checkVoid(Object temp) {
        return temp == null ? "null" : temp.toString();
    }

    public List<Object> serialize(){
        List<Object> serializedData = new ArrayList<>();

        serializedData.add(checkVoid(id));
        serializedData.add(checkVoid(anoFabricacion));
        serializedData.add(checkVoid(cilindrada));
        serializedData.add(checkVoid(color));
        serializedData.add(checkVoid(comprado));
        serializedData.add(checkVoid(correoDueno));
        serializedData.add(checkVoid(descripcion));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        serializedData.add(checkVoid(dateFormat.format(fechaPublicacion)));

        serializedData.add(checkVoid(fotoPrincipal));
        serializedData.add(checkVoid(kilometraje));
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
        serializedData.add(checkVoid(version));
        serializedData.add(checkVoid(vin));

        serializedData.addAll(fotos);

        return serializedData;
    }



}
