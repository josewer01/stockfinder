
package prendas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;


@RestController
public class PrendaController {

    private Connection c = null; // Objeto de tipo coneccion donde se guardaran los datos de coneccion
	private PreparedStatement s = null; // Objeto de tipo sentencia SQL
	private ResultSet rs = null; // Objeto de tipo resultado Query SQL
	private Prenda objeto;


    public PrendaController() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:sqlserver://stockfindertiendas.database.windows.net:1433;database=tiendas;user=stockadmin@stockfindertiendas;password=I_yj73PRBlnBOOyhhcEfDw;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
			c.setAutoCommit(true);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			c.close();
		}
	}
 
    @GetMapping("/prenda")
    public Prenda prenda(
    @RequestParam(value = "id") int id
   , @RequestParam(value = "tienda", defaultValue="sevilla") String tienda) throws SQLException
    {
	if (c != null) {
			

		    String query = "SELECT * FROM "+tienda+" WHERE id = ?";
			 s = c.prepareStatement(query);
     		s.setInt(1, id);
			
   			 ResultSet rs = s.executeQuery();

			if (rs == null){
				cerrarDatabase();
				return null;
			}
			else {
				while (rs.next()){
				    objeto = new Prenda(rs.getInt("id"),rs.getString("nombre"),rs.getString("descripcion"),rs.getString("categoria"),
										rs.getString("talla"),rs.getInt("stock"),rs.getInt("precio"),
										rs.getString("composicion"),rs.getString("proveedor"),rs.getString("imagen"));
					}

				return objeto;
			}
		} else
			return null;
	}

 @GetMapping("/prendas")
    public List<Prenda> prendaLista(
	@RequestParam(value="tienda", defaultValue="sevilla") String tienda) throws SQLException
    {
	List<Prenda> l = new LinkedList<Prenda>();
	if (c != null) {

		    String query = "SELECT * FROM "+tienda;
			 s = c.prepareStatement(query);

   			 ResultSet rs = s.executeQuery();

			if (rs == null){
				cerrarDatabase();
				return null;
			}
			else {
				while (rs.next()){
				    objeto = new Prenda(rs.getInt("id"),rs.getString("nombre"),rs.getString("descripcion"),rs.getString("categoria"),
										rs.getString("talla"),rs.getInt("stock"),rs.getInt("precio"),
										rs.getString("composicion"),rs.getString("proveedor"),rs.getString("imagen"));
					l.add(objeto);					

}

				return l;
			}
		} else
			return null;
	}

	//Método para añadir una nueva entrada
	@PostMapping(path = "/create/{tienda}")
    public  ResponseEntity<String> creator(
		@PathVariable(value = "tienda") String tienda
		, @RequestBody Prenda nuevaPrenda)  throws SQLException
    {
		int resultado = 0;
		ResponseEntity<String> respuesta = null;
	if (c != null) {
		

		try{
			
		    String query = "INSERT INTO " + tienda + " (id, nombre, stock, composicion, proveedor, talla, categoria, precio, imagen, descripcion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			 s = c.prepareStatement(query);
     		s.setInt(1, nuevaPrenda.getId());
			s.setString(2, nuevaPrenda.getNombre());
            s.setInt(3, nuevaPrenda.getStock());
            s.setString(4, nuevaPrenda.getComposicion());
            s.setString(5, nuevaPrenda.getProveedor());
            s.setString(6, nuevaPrenda.getTalla());
            s.setString(7, nuevaPrenda.getCategoria());
            s.setInt(8, nuevaPrenda.getPrecio());
            s.setString(9, nuevaPrenda.getImagen());
            s.setString(10, nuevaPrenda.getDescripcion());
			
   		     resultado  = s.executeUpdate();
			} catch (SQLException e){
				System.out.println("PrendaController: Excepción SQLException:" + e.getMessage());
				} finally {
				try {
				if ( s != null ) s.close();
				if ( c != null ) c.close();
				} catch (SQLException e){
				System.out.println("PrendaController: Excepción SQLException:" + e.getMessage());
				}
			}
			if (resultado >= 1){
				respuesta = new ResponseEntity<>("Insertado correctamente", HttpStatus.CREATED);	
			}
		} else{
			respuesta = new ResponseEntity<>("No insertado",HttpStatus.INTERNAL_SERVER_ERROR);
			cerrarDatabase();
		}
		return respuesta;
	}

	@DeleteMapping(path = "/delete")
    public  ResponseEntity<String> delete(
		@RequestParam(value = "id") int id
		, @RequestParam(value = "tienda", defaultValue="sevilla") String tienda)  throws SQLException
    {
		int numRegBorrados = 0;
		ResponseEntity<String> respuesta = null;
	if (c != null) {
		

		try{
			
		    String query = "DELETE FROM "+tienda+" WHERE id = ?";
			 s = c.prepareStatement(query);
     		s.setInt(1, id);
			
			 numRegBorrados  = s.executeUpdate();
			} catch (SQLException e){
				System.out.println("PrendaController: Excepción SQLException:" + e.getMessage());
				} finally {
				try {
				if ( s != null ) s.close();
				if ( c != null ) c.close();
				} catch (SQLException e){
				System.out.println("PrendaController: Excepción SQLException:" + e.getMessage());
				}
			}
			if (numRegBorrados >= 1){
				respuesta = new ResponseEntity<>("Borrado correctamente", HttpStatus.CREATED);	
			} else {
				respuesta = new ResponseEntity<>("No ha sido posible borrar",HttpStatus.INTERNAL_SERVER_ERROR);
				cerrarDatabase();
			}
		} else{
			respuesta = new ResponseEntity<>("No ha sido posible borrar",HttpStatus.INTERNAL_SERVER_ERROR);
			cerrarDatabase();
		}
		return respuesta;
	}

	@GetMapping("/sumar")
    public ResponseEntity<String>  modificarPrendaSumar(
    @RequestParam(value = "id") int id
   , @RequestParam(value = "tienda", defaultValue="sevilla") String tienda) throws SQLException
    {
		int columnasAfectadas = 0;
		ResponseEntity<String> respuesta = null;
	if (c != null) {
			
		    String query = "UPDATE "+tienda+" SET stock = stock + 1 WHERE id = ?";
			 s = c.prepareStatement(query);
     		s.setInt(1, id);
			
			 columnasAfectadas = s.executeUpdate();

			if (columnasAfectadas >= 1){
				respuesta = new ResponseEntity<>("Incrementado correctamente", HttpStatus.CREATED);	
				
			}
			else {
				respuesta = new ResponseEntity<>("No ha sido posible incrementar",HttpStatus.INTERNAL_SERVER_ERROR);
				cerrarDatabase();
			}
		} else{
			respuesta = new ResponseEntity<>("No ha sido posible incrementar",HttpStatus.INTERNAL_SERVER_ERROR);
				cerrarDatabase();
		}
		return respuesta;
	}

	@GetMapping("/restar")
    public ResponseEntity<String>  modificarPrendaRestar(
    @RequestParam(value = "id") int id
   , @RequestParam(value = "tienda", defaultValue="sevilla") String tienda) throws SQLException
    {
		int columnasAfectadas = 0;
		ResponseEntity<String> respuesta = null;
	if (c != null) {
			//Evitamos que el valor pueda ir negativo
		    String query = "UPDATE "+tienda+" SET stock = stock - 1 WHERE id = ? AND stock > 0";
			 s = c.prepareStatement(query);
     		s.setInt(1, id);
			
			 columnasAfectadas = s.executeUpdate();

			if (columnasAfectadas >= 1){
				respuesta = new ResponseEntity<>("Decrementado correctamente", HttpStatus.CREATED);	
				
			}
			else {
				respuesta = new ResponseEntity<>("No ha sido posible decrementar",HttpStatus.INTERNAL_SERVER_ERROR);
				cerrarDatabase();
			}
		} else{
			respuesta = new ResponseEntity<>("No ha sido posible decrementar",HttpStatus.INTERNAL_SERVER_ERROR);
				cerrarDatabase();
		}
		return respuesta;
	}

	
public void cerrarDatabase() throws SQLException {
		if(rs != null) rs.close();
		if(s != null) s.close();

		c.close();
	}

    }

