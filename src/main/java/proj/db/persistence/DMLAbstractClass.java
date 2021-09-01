package proj.db.persistence;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import proj.db.persistence.datasource.DBSourceFactory;

public abstract class DMLAbstractClass<T> {

  private static final Logger logger = LoggerFactory.getLogger(DMLAbstractClass.class);
  private Connection connection = null;
  private PreparedStatement prepareStmt = null;

  protected Connection getConnection()
      throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    logger.debug("===================Attempting to get connection===================");
    connection = DBSourceFactory.getConnection(DBSourceFactory.MAIN);
    logger.debug("===================Connection obtained!===================");
    return connection;
  }

  protected void prepareStatement(String sql) throws SQLException {
    if (connection == null) {
      logger.error("No connection initialized! call getConnection() to get connection");
      throw new SQLException("Connection instance is null!");
    }
    prepareStmt = connection.prepareStatement(sql);
  }

  protected void setString(int parameter, String value) throws SQLException {
    prepareStmt.setString(parameter, value);
  }

  protected void setInt(int parameter, int value) throws SQLException {
    prepareStmt.setInt(parameter, value);
  }

  protected void setDouble(int parameter, double value) throws SQLException {
    prepareStmt.setDouble(parameter, value);
  }

  protected void setByte(int parameter, byte value) throws SQLException {
    prepareStmt.setByte(parameter, value);
  }

  protected void setBytes(int parameter, byte[] value) throws SQLException {
    prepareStmt.setBytes(parameter, value);
  }

  protected void setDate(int parameter, Date value) throws SQLException {
    prepareStmt.setDate(parameter, value);
  }

  protected int executeUpdate() throws SQLException {
    return prepareStmt.executeUpdate();
  }

  protected ResultSet executeQuery() throws SQLException {
    return prepareStmt.executeQuery();
  }

  protected void closeResources() throws SQLException {
    logger.debug("Closing resources..");
    if (connection != null || connection.isClosed()) {
      connection.close();
      connection = null;
    }
    if (prepareStmt != null || prepareStmt.isClosed()) {
      prepareStmt.close();
      prepareStmt = null;
    }
  }

  public abstract T selectData(T searchCriteria);

  public abstract T[] selectBulk(T searchCriteria);

  public abstract int updateData(T data);

  public abstract int deleteData(T data);

  public abstract T readSingleResult(ResultSet resultSet);

}