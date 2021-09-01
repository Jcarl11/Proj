package proj.db.persistence.datasource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import proj.annotations.SingletonConstructor;
import utils.general.PropertiesUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class OracleDS extends DataSourceInterface {

  private static final Logger logger = LoggerFactory.getLogger(OracleDS.class);
  private static BasicDataSource basicDataSource;
  private static OracleDS instance;

  private OracleDS() {
  }

  //Mandatory to add SingletonConstructor annotation on datasource implementations
  @SingletonConstructor
  public static OracleDS createInstance() {
    if (instance == null) {
      instance = new OracleDS();
    }

    return instance;
  }

  static {
    PropertiesUtils.load("database_details.properties");
    basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName(PropertiesUtils.get("db.oracle.driver"));
    basicDataSource.setUrl(buildConnectionURL());
    basicDataSource.setUsername(PropertiesUtils.get("db.oracle.username"));
    basicDataSource.setPassword(PropertiesUtils.get("db.oracle.password"));
    basicDataSource.setInitialSize(Integer.parseInt(PropertiesUtils.get("db.maxInitial.size")));
    basicDataSource.setMaxTotal(Integer.parseInt(PropertiesUtils.get("db.max.total.size")));
    basicDataSource.setMaxOpenPreparedStatements(
        Integer.parseInt(PropertiesUtils.get("db.maxopened.preparedstatement")));
    basicDataSource.setMaxWaitMillis(Long.parseLong(PropertiesUtils.get("db.max.wait")));
    basicDataSource.setMinIdle(Integer.parseInt(PropertiesUtils.get("db.minIdle.count")));
    basicDataSource.setMaxIdle(Integer.parseInt(PropertiesUtils.get("db.maxIdle.count")));
  }

  public static String buildConnectionURL() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("jdbc:oracle");
    stringBuffer.append(":");
    stringBuffer.append(PropertiesUtils.get("db.oracle.drivertype"));
    stringBuffer.append(":");
    stringBuffer.append("@");
    stringBuffer.append(PropertiesUtils.get("db.oracle.host"));
    stringBuffer.append(":");
    stringBuffer.append(PropertiesUtils.get("db.oracle.port"));
    stringBuffer.append(":");
    stringBuffer.append(PropertiesUtils.get("db.oracle.database"));
    String url = stringBuffer.toString();
    logger.info("================ Connection URL: {}", url);

    return url;
  }

  public Connection getConnection() throws SQLException {
    return basicDataSource.getConnection();
  }
}
