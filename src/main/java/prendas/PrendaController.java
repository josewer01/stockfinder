
package prendas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
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

	
public void cerrarDatabase() throws SQLException {
		if(rs != null) rs.close();
		if(s != null) s.close();

		c.close();
	}

    }

