package proj.db.persistence.datasource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBSourceFactoryTest {

  private static final Logger logger = LoggerFactory.getLogger(DBSourceFactoryTest.class);
  private static String dbURL;
  @BeforeAll
  static void setUp() {
    logger.trace("In setUp() method");
    dbURL = "proj.db.persistence.datasource.OracleDS";
  }

  @Test
  @DisplayName("Test to get connection")
  void Testing() {
    Connection connection = DBSourceFactory.getConnection(DBSourceFactory.MAIN);
    assertNotNull(connection);
  }

  @AfterEach
  void tearDown() {
    logger.trace("in tearDown() method");
  }
}