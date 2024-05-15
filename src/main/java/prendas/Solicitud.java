package prendas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Solicitud {

    private int id;
    private String origen;
    private String cantidad;
    
    public Solicitud(int id, String origen, String cantidad) {
        this.id = id;
        this.origen = origen;
        this.cantidad = cantidad;
    }

    public Solicitud(){} //Constructor por defecto


    public int getId() {
        return id;
    }
    @JsonProperty
    public void setId(int id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }
    @JsonProperty
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getCantidad() {
        return cantidad;
    }
    @JsonProperty
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }




}





