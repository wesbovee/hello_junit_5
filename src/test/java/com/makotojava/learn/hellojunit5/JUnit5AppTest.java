package com.makotojava.learn.hellojunit5;

import com.makotojava.learn.hellojunit5.solution.App;
import org.junit.jupiter.api.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 * JUnit 5 (with JUnitPlatform.class)
 * 
 * Class-level Exercises:
 * <ol>
 * <li>Enable Eclipse JUnit support for this test class using the {@link org.junit.runner.RunWith @RunWith} and
 * {@link org.junit.platform.runner.JUnitPlatform
 * JUnitPlatform} class.</li>
 * <li>Give the class a cool {@link org.junit.jupiter.api.DisplayName @DisplayName} that shows up in the JUnit test
 * report.</li>
 * </ol>
 *
 */
@RunWith(JUnitPlatform.class)
public class JUnit5AppTest {

  private static final Logger log = LoggerFactory.getLogger(com.makotojava.learn.hellojunit5.solution.JUnit5AppTest.class);

  private com.makotojava.learn.hellojunit5.App classUnderTest;

  @BeforeAll
  public static void init() {
    // Do something before ANY test is run in this class
    log.info("@BeforeAll: init()");
  }

  @AfterAll
  public static void done() {
    // Do something after ALL tests in this class are run
    log.info("@AfterAll: done()");
  }

  @BeforeEach
  public void setUp() throws Exception {
    log.info("@BeforeEach: setUp()");
    classUnderTest = new com.makotojava.learn.hellojunit5.App();
  }

  @AfterEach
  public void tearDown() throws Exception {
    log.info("@AfterEach: tearDown()");
    classUnderTest = null;
  }

  @Test
  @Disabled
  @DisplayName("A disabled test")
  void testNotRun() {
    log.info("This test will not run (it is disabled, silly).");
  }

  @Test
  @DisplayName("When numbers are > 0")
  public void testAdd() {
    log.info("@Test: testAdd()");
    assertNotNull(classUnderTest);
    assertAll(
            () -> {
              //
              // Test #1
              long[] numbersToSum = { 1, 2, 3, 4 };
              long expectedSum = 10;
              long actualSum = classUnderTest.add(numbersToSum);
              assertEquals(expectedSum, actualSum);
            },
            () -> {
              //
              // Test #2
              long[] numbersToSum = new long[] { 20, 934, 110 };
              long expectedSum = 1064;
              long actualSum = classUnderTest.add(numbersToSum);
              assertEquals(expectedSum, actualSum);
            },
            () -> {
              //
              // Test #3
              long[] numbersToSum = new long[] { 2, 4, 6 };
              long expectedSum = 12;
              long actualSum = classUnderTest.add(numbersToSum);
              assertEquals(expectedSum, actualSum);
            });
  }

  @Nested
  @DisplayName("When numbers to add are < 0")
  class NegativeNumbersTest {

    private com.makotojava.learn.hellojunit5.App classUnderTest;

    @BeforeEach
    public void setUp() throws Exception {
      classUnderTest = new com.makotojava.learn.hellojunit5.App();
    }

    @AfterEach
    public void tearDown() throws Exception {
      classUnderTest = null;
    }

    @Test
    @DisplayName("Three tests with numbers < 0")
    public void testAdd() {
      assertNotNull(classUnderTest);
      assertAll(
              () -> {
                //
                // Test #1
                long[] numbersToSum = { -1, -2, -3, -4 };
                long expectedSum = -10;
                long actualSum = classUnderTest.add(numbersToSum);
                assertEquals(expectedSum, actualSum);
              },
              () -> {
                //
                // Test #2
                long[] numbersToSum = { -20, -934, -110 };
                long expectedSum = -1064;
                long actualSum = classUnderTest.add(numbersToSum);
                assertEquals(expectedSum, actualSum);
              },
              () -> {
                //
                // Test #3
                long[] numbersToSum = new long[] { -2, -4, -6 };
                long expectedSum = -12;
                long actualSum = classUnderTest.add(numbersToSum);
                assertEquals(expectedSum, actualSum);
              });
    }
  }

  @Nested
  @DisplayName("When 0 < numbers > 0")
  class PositiveAndNegativeNumbersTest {

    @Test
    @DisplayName("Three tests with both positive and negative numbers")
    public void testAdd() {
      assertNotNull(classUnderTest);
      assertAll(
              () -> {
                //
                // Test #1
                long[] numbersToSum = { -1, 2, -3, 4 };
                long expectedSum = 2;
                long actualSum = classUnderTest.add(numbersToSum);
                assertEquals(expectedSum, actualSum);
              },
              () -> {
                //
                // Test #2
                long[] numbersToSum = { -20, 934, -110 };
                long expectedSum = 804;
                long actualSum = classUnderTest.add(numbersToSum);
                assertEquals(expectedSum, actualSum);
              },
              () -> {
                //
                // Test #3
                long[] numbersToSum = new long[] { -2, -4, 6 };
                long expectedSum = 0;
                long actualSum = classUnderTest.add(numbersToSum);
                assertEquals(expectedSum, actualSum);
              });
    }

    @Test
    @DisplayName("This test is only run on Fridays")
    public void testAdd_OnlyOnFriday() {
      assertNotNull(classUnderTest);
      LocalDateTime ldt = LocalDateTime.now();
      assumeTrue(ldt.getDayOfWeek().getValue() == 5, "Test skipped... it's not Friday!");
      assumeTrue(ldt.getDayOfWeek().getValue() == 5);
      long[] operands = { 1, 2, 3, 4, 5 };
      long expectedSum = 15;
      long actualSum = classUnderTest.add(operands);
      assertEquals(expectedSum, actualSum);
    }

    @Test
    @DisplayName("This test is only run on Fridays (with lambda)")
    public void testAdd_OnlyOnFriday_WithLambda() {
      assertNotNull(classUnderTest);
      LocalDateTime ldt = LocalDateTime.now();
      assumingThat(ldt.getDayOfWeek().getValue() == 5,
              () -> {
                long[] operands = { 1, 2, 3, 4, 5 };
                long expectedSum = 15;
                long actualSum = classUnderTest.add(operands);
                assertEquals(expectedSum, actualSum);
              });
    }

  }

  @Nested
  @DisplayName("When using a single operand")
  class JUnit5AppSingleOperandTest {

    @Test
    @DisplayName("Numbers > 0")
    public void testAdd_NumbersGt0() {
      assertNotNull(classUnderTest);
      assertAll(
              () -> {
                long[] numbersToSum = { 1 };
                long expectedSum = 1;
                long actualSum = classUnderTest.add(numbersToSum);
                assertEquals(expectedSum, actualSum);
              },
              () -> {
                long[] numbersToSum = { 0 };
                long expectedSum = 0;
                long actualSum = classUnderTest.add(numbersToSum);
                assertEquals(expectedSum, actualSum);
              });
    }

    @Test
    @DisplayName("Numbers < 0")
    public void testAdd_NumbersLt0() {
      assertNotNull(classUnderTest);
      assertAll(
              () -> {
                long[] numbersToSum = { -1 };
                long expectedSum = -1;
                long actualSum = classUnderTest.add(numbersToSum);
                assertEquals(expectedSum, actualSum);
              },
              () -> {
                long[] numbersToSum = { -10 };
                long expectedSum = -10;
                long actualSum = classUnderTest.add(numbersToSum);
                assertEquals(expectedSum, actualSum);
              });
    }
  }

  @Nested
  @DisplayName("When zero operands")
  class JUnit5AppZeroOperandsTest {

    @Test()
    @DisplayName("Empty argument")
    public void testAdd_ZeroOperands_EmptyArgument() {
      assertNotNull(classUnderTest);
      long[] numbersToSum = {};
      assertThrows(IllegalArgumentException.class, () -> classUnderTest.add(numbersToSum));
    }

    @Test
    @DisplayName("Null argument")
    public void testAdd_ZeroOperands_NullArgument() {
      assertNotNull(classUnderTest);
      long[] numbersToSum = null;
      Throwable expectedException = assertThrows(IllegalArgumentException.class,
              () -> classUnderTest.add(numbersToSum));
      assertEquals("Operands argument cannot be null", expectedException.getLocalizedMessage());
    }

  }

}
