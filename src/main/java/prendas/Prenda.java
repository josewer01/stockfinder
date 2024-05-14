
package prendas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Prenda {

    private String imagen;
    private int id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String talla;
    private int stock;
    private int precio;
    private String composicion;
    private String proveedor;

    public Prenda(int id, String nombre, String descripcion, String categoria, String talla, int stock, int precio, String composicion, String proveedor, String url) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this. categoria =  categoria;
        this.talla = talla;
        this.stock = stock;
        this.precio = precio;
        this.composicion =  composicion;
        this.proveedor = proveedor;
        this.imagen = url;

    }

    public Prenda(){}


    public String getImagen() {
        return imagen;
    }
    @JsonProperty
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }
    @JsonProperty
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    @JsonProperty
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    @JsonProperty
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }
    @JsonProperty
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTalla() {
        return talla;
    }
    @JsonProperty
    public void setTalla(String talla) {
        this.talla = talla;
    }

    public int getStock() {
        return stock;
    }
    @JsonProperty
    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrecio() {
        return precio;
    }
    @JsonProperty
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getComposicion() {
        return composicion;
    }
    @JsonProperty
    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }
    
    public String getProveedor() {
        return proveedor;
    }
     @JsonProperty
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}

