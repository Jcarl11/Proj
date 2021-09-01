package proj.db.persistence.datasource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DataSourceInterface {

  public abstract Connection getConnection() throws SQLException;
}
