package proj.db.persistence.datasource;

import java.lang.annotation.Annotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import proj.annotations.SingletonConstructor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import utils.general.PropertiesUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

public class DBSourceFactory {

  private static final Logger logger = LoggerFactory.getLogger(DBSourceFactory.class);
  public static final String MAIN = "MAIN";
  public static final String LOCAL = "LOCAL";
  public static final String activeDatabaseClass;

  private DBSourceFactory() {
  }

  static {
    PropertiesUtils.load("database_details.properties");
    activeDatabaseClass = PropertiesUtils.get("db.main.active");
    logger.info("================= {}", activeDatabaseClass);
  }

  public static Connection getConnection(String type) {
    logger.trace("getting connection...");
    logger.debug("connection type: {}", type);
    Connection connection = null;
    Method annotatedMethod = null;
    if (type.equalsIgnoreCase(DBSourceFactory.MAIN)) {
      try {
        Class mainDatabaseClass = Class.forName(activeDatabaseClass);
        Method[] methodList = mainDatabaseClass.getMethods();
        for (int x = 0; x < methodList.length; x++) {
					if(methodList[x].isAnnotationPresent(SingletonConstructor.class))
					  annotatedMethod = methodList[x];
        }
        DataSourceInterface dataSourceInterface = (DataSourceInterface) annotatedMethod.invoke(
            null);
        connection = dataSourceInterface.getConnection();
      } catch (SQLException sqlException) {
        logger.error(sqlException.getMessage(), sqlException);
      } catch (ClassNotFoundException classNotFoundException) {
        logger.error(classNotFoundException.getMessage(), classNotFoundException);
      } catch (InvocationTargetException e) {
        logger.error(e.getMessage(), e);
      } catch (IllegalAccessException e) {
        logger.error(e.getMessage(), e);
      }
    } else if (type.equalsIgnoreCase(DBSourceFactory.LOCAL)) {
      //To implement
      throw new NotImplementedException();
    }
    return connection;
  }

}
