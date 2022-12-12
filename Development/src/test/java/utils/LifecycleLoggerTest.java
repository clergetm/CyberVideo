package utils;


import java.util.logging.Logger;

import org.junit.jupiter.api.*;

/**
 * Interface to Log each Test
 * @author MathysC
 *
 */
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public interface LifecycleLoggerTest {

    static final Logger LOG = Logger.getLogger(LifecycleLoggerTest.class.getName());

    /**
     * Log BeforeAll tests from a class
     * @author MathysC
     *
     * @param testInfo
     */
    @BeforeAll
    default void setUp(TestInfo testInfo) {
        LOG.info("@BeforeAll - Testing " + testInfo.getTestClass().get().getName());
    }

    /**
     * Log AfterAll tests from a class
     * @author MathysC
     *
     * @param testInfo
     */
    @AfterAll
    default void done(TestInfo testInfo) {
        LOG.info("@AfterAll - End Testing " + testInfo.getTestClass().get().getName());
    }

    /**
     * Log BeforeEach test from a class
     * @author MathysC
     *
     * @param testInfo
     */
    @BeforeEach
    default void beforeEachTest(TestInfo testInfo) {
        LOG.info(() -> String.format("About to execute [%s]",
            testInfo.getDisplayName()) + "\n");
    }

    /**
     * Log AfterEach test from a class
     * @author MathysC
     *
     * @param testInfo
     */
    @AfterEach
    default void afterEachTest(TestInfo testInfo) {
        LOG.info(() -> String.format("Finished executing [%s]",
            testInfo.getDisplayName()) + "\n");
    }
}