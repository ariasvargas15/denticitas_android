package com.amongusdev.denticitas.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Servicio implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("imagen")
    @Expose
    private String imagen;
    @SerializedName("precio")
    @Expose
    private Integer precio;
    @SerializedName("areaId")
    @Expose
    private Area area;

    public Servicio(String nombre, String descripcion, Integer precio, Area area) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.area = area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Area getArea() {
        return area;
    }

    public void setAreaId(Area areaId) {
        this.area = areaId;
    }

}