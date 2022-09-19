package com.avehlies.Rectangles.Utilities;

import java.math.BigDecimal;

/**
 * Contains utility functions of basic math functions for BigDecimal values
 */
public final class MathUtility {

    /**
     * Compares the first parameter to the second and returns true if the first is
     * greater than the second
     *
     * @param a a BigDecimal
     * @param b a BigDecimal to compare against a
     * @return true if a is greater than b
     */
    public static boolean gt(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) > 0;
    }

    /**
     * Compares the first parameter to the second and returns true if the first
     * is greater than or equal to the second
     *
     * @param a a BigDecimal
     * @param b a BigDecimal to compare against a
     * @return true if a is greater than or equal to b
     */
    public static boolean gte(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) >= 0;
    }

    /**
     * Compares the first parameter to the second and returns true if the first
     * is less than the second
     *
     * @param a a BigDecimal
     * @param b a BigDecimal to compare against a
     * @return true if a is less than b
     */
    public static boolean lt(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) < 0;
    }

    /**
     * Compares the first parameter to the second and returns true if the first
     * is less than or equal to the second
     *
     * @param a a BigDecimal
     * @param b a BigDecimal to compare against a
     * @return true if a is less than or equal to b
     */
    public static boolean lte(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) <= 0;
    }

    /**
     * Compares the first parameter to the second and returns true if they are equal
     *
     * @param a a BigDecimal
     * @param b a BigDecimal to compare against a
     * @return true if a is equal to b
     */
    public static boolean eq(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) == 0;
    }

    /**
     * Returns the max of two parameters
     *
     * @param a a BigDecimal
     * @param b a BigDecimal
     * @return the bigger of the two values
     */
    public static BigDecimal max(BigDecimal a, BigDecimal b) {
        return (MathUtility.gte(a, b)) ? a : b;
    }

    /**
     * Returns the min of two parameters
     *
     * @param a a BigDecimal
     * @param b a BigDecimal
     * @return the lesser of the two values
     */
    public static BigDecimal min(BigDecimal a, BigDecimal b) {
        return (MathUtility.lte(a, b)) ? a : b;
    }
}
