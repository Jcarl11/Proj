package proj.db.persistence.datasource;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import proj.annotations.SingletonConstructor;

class OracleDSTest {

  private static final Logger logger = LoggerFactory.getLogger(OracleDSTest.class);
  private static String dbClass;

  @BeforeAll
  public static void setUp() {
    dbClass = "proj.db.persistence.datasource.OracleDS";
  }

  @Test
  @DisplayName("SingletonConstructor annotation test")
  public void annotationTest() {

    Annotation annotation = null;
    try {
      Class oracleDSClass = Class.forName(dbClass);
      Method[] methodsList = oracleDSClass.getMethods();
      for (int x = 0; x < methodsList.length; x++) {
        if (methodsList[x].isAnnotationPresent(SingletonConstructor.class)) {
          annotation = methodsList[x].getAnnotation(SingletonConstructor.class);
        }
      }

      assertEquals(SingletonConstructor.class, annotation.annotationType());

    } catch (ClassNotFoundException e) {
      logger.error(e.getMessage(), e);
    }
  }

}