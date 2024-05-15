package prendas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Solicitud {

    private int id;
    private int id_prenda;
    private String origen;
    private String cantidad;
    
    public Solicitud(int id,int id_prenda,String origen, String cantidad) {
        this.id = id;
        this.id_prenda = id_prenda;
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

    
    public int getId_prenda() {
        return id_prenda;
    }
    @JsonProperty
    public void setId_prenda(int id_prenda) {
        this.id_prenda = id_prenda;
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





