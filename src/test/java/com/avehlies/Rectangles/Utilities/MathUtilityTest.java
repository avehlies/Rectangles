package com.avehlies.Rectangles.Utilities;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for MathUtility
 *
 * NOTE: Arguments are being passed as Strings in the parameterized tests. This is for the sake
 * of verbosity. A String can easily be made into a BigDecimal without having to worry about
 * the parameter types for the test methods. If we want to test Integer and Float values, we can
 * pass them all as Strings and let BigDecimal do the conversion work.
 */
public class MathUtilityTest {

    public static Stream<Arguments> gtProvider() {
        return Stream.of(
                Arguments.of("10", "10", false),
                Arguments.of("10.000001", "10", true),
                Arguments.of("10", "10.00000000001", false)
        );
    }

    @ParameterizedTest
    @MethodSource("gtProvider")
    public void testGt(String aString, String bString, boolean expected) {
        BigDecimal a = new BigDecimal(aString);
        BigDecimal b = new BigDecimal(bString);

        assertEquals(expected, MathUtility.gt(a, b));
    }
    public static Stream<Arguments> gteProvider() {
        return Stream.of(
                Arguments.of("10", "10", true),
                Arguments.of("10.000001", "10", true),
                Arguments.of("10", "10.00000000001", false)
        );
    }

    @ParameterizedTest
    @MethodSource("gteProvider")
    public void testGte(String aString, String bString, boolean expected) {
        BigDecimal a = new BigDecimal(aString);
        BigDecimal b = new BigDecimal(bString);

        assertEquals(expected, MathUtility.gte(a, b));
    }

    public static Stream<Arguments> ltProvider() {
        return Stream.of(
                Arguments.of("10", "10", false),
                Arguments.of("10.000001", "10", false),
                Arguments.of("10", "10.00000000001", true)
        );
    }

    @ParameterizedTest
    @MethodSource("ltProvider")
    public void testLt(String aString, String bString, boolean expected) {
        BigDecimal a = new BigDecimal(aString);
        BigDecimal b = new BigDecimal(bString);

        assertEquals(expected, MathUtility.lt(a, b));
    }
    public static Stream<Arguments> lteProvider() {
        return Stream.of(
                Arguments.of("10", "10", true),
                Arguments.of("10.000001", "10", false),
                Arguments.of("10", "10.00000000001", true)
        );
    }

    @ParameterizedTest
    @MethodSource("lteProvider")
    public void testLte(String aString, String bString, boolean expected) {
        BigDecimal a = new BigDecimal(aString);
        BigDecimal b = new BigDecimal(bString);

        assertEquals(expected, MathUtility.lte(a, b));
    }

    public static Stream<Arguments> eqProvider() {
        return Stream.of(
                Arguments.of("10", "10", true),
                Arguments.of("10", "10.00", true),
                Arguments.of("10.001", "10.001", true),
                Arguments.of("10.000001", "10", false),
                Arguments.of("10", "10.00000000001", false)
        );
    }

    @ParameterizedTest
    @MethodSource("eqProvider")
    public void testEq(String aString, String bString, boolean expected) {
        BigDecimal a = new BigDecimal(aString);
        BigDecimal b = new BigDecimal(bString);

        assertEquals(expected, MathUtility.eq(a, b));
    }

    public static Stream<Arguments> minProvider() {
        return Stream.of(
                Arguments.of("10", "11", "10"),
                Arguments.of("10", "10.00", "10"),
                Arguments.of("10.001", "10.0001", "10.0001"),
                Arguments.of("10.000001", "10", "10"),
                Arguments.of("10", "10.00000000001", "10")
        );
    }

    @ParameterizedTest
    @MethodSource("minProvider")
    public void testMin(String aString, String bString, String expectedString) {
        BigDecimal a = new BigDecimal(aString);
        BigDecimal b = new BigDecimal(bString);
        BigDecimal expected = new BigDecimal(expectedString);

        assertEquals(expected, MathUtility.min(a, b));
    }

    public static Stream<Arguments> maxProvider() {
        return Stream.of(
                Arguments.of("10", "11", "11"),
                Arguments.of("10", "10.00", "10.00"),
                Arguments.of("10.001", "10.0001", "10.001"),
                Arguments.of("10.000001", "10", "10.000001"),
                Arguments.of("10", "10.00000000001", "10.00000000001")
        );
    }

    @ParameterizedTest
    @MethodSource("maxProvider")
    public void testMax(String aString, String bString, String expectedString) {
        BigDecimal a = new BigDecimal(aString);
        BigDecimal b = new BigDecimal(bString);
        BigDecimal expected = new BigDecimal(expectedString);

        assertEquals(0, expected.compareTo(MathUtility.max(a, b)));
    }
}
