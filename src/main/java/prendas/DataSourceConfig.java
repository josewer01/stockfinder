package prendas;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;



public class DataSourceConfig {

    public static DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        // Definidas en app.properties
       // dataSourceBuilder.url("jdbc:sqlserver://stockfindertiendas.database.windows.net:1433;database=tiendas;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=120;");
      //  dataSourceBuilder.username("stockadmin@stockfindertiendas");
     //   dataSourceBuilder.password("I_yj73PRBlnBOOyhhcEfDw");
        return dataSourceBuilder.build();
    }
}
