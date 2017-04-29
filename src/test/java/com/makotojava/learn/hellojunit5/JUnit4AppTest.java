package com.makotojava.learn.hellojunit5;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * JUnit 4
 *
 */
public class JUnit4AppTest {

  private App classUnderTest;

  @Before
  public void setUp() throws Exception {
    classUnderTest = new App();
  }

  @After
  public void tearDown() throws Exception {
    classUnderTest = null;
  }

  @Test
  public void testAdd() {
    //
    // Test #1
    long[] numbersToSum = { 1, 2, 3, 4 };
    long expectedSum = 10;
    long actualSum = classUnderTest.add(numbersToSum);
    assertEquals(expectedSum, actualSum);
    //
    // Test #2
    numbersToSum = new long[] { 20, 934, 110 };
    expectedSum = 1064;
    actualSum = classUnderTest.add(numbersToSum);
    assertEquals(expectedSum, actualSum);
    //
    // Test #3
    numbersToSum = new long[] { 2, 4, 6 };
    expectedSum = 12;
    actualSum = classUnderTest.add(numbersToSum);
    assertEquals(expectedSum, actualSum);
  }

  @Test
  public void testAdd_NegativeNumbers() {
    //
    // Test #1
    long[] numbersToSum = { -1, -2, -3, -4 };
    long expectedSum = -10;
    long actualSum = classUnderTest.add(numbersToSum);
    assertEquals(expectedSum, actualSum);
    //
    // Test #2
    numbersToSum = new long[] { -20, -934, -110 };
    expectedSum = -1064;
    actualSum = classUnderTest.add(numbersToSum);
    assertEquals(expectedSum, actualSum);
    //
    // Test #3
    numbersToSum = new long[] { -2, -4, -6 };
    expectedSum = -12;
    actualSum = classUnderTest.add(numbersToSum);
    assertEquals(expectedSum, actualSum);
  }

  @Test
  public void testAdd_SingleOperand_NumbersGt0() {
    long[] numbersToSum = { 1 };
    long expectedSum = 1;

    long actualSum = classUnderTest.add(numbersToSum);
    assertEquals(expectedSum, actualSum);

    numbersToSum = new long[] { 0 };
    expectedSum = 0;
    actualSum = classUnderTest.add(numbersToSum);
    assertEquals(expectedSum, actualSum);
  }

  @Test
  public void testAdd_SingleOperand_NumbersLt0() {
    long[] numbersToSum = { -1 };
    long expectedSum = -1;

    long actualSum = classUnderTest.add(numbersToSum);
    assertEquals(expectedSum, actualSum);

    numbersToSum = new long[] { -10 };
    expectedSum = -10;
    actualSum = classUnderTest.add(numbersToSum);
    assertEquals(expectedSum, actualSum);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAdd_ZeroOperands_EmptyArgument() {
    long[] numbersToSum = {};
    classUnderTest.add(numbersToSum);
  }

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void testAdd_ZeroOperands_NullArgument() {
    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Operands argument cannot be null");

    classUnderTest.add(null);
  }

}
