package utils.general;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtilsTest.class);

    @BeforeEach
    void setUp() {
        logger.trace("setUp");
        PropertiesUtils.load("database_details.properties");
    }

    @Test
    void propertiesTest() {
        String result = PropertiesUtils.get("db.oracle.host");
        logger.debug("Test result: {}",result);
        assertEquals("localhost", result);
    }

    @AfterEach
    void tearDown() {
    }
}