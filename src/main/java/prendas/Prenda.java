
package prendas;


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


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getComposicion() {
        return composicion;
    }

    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}

