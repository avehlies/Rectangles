package com.avehlies.Rectangles.Utilities;

import com.avehlies.Rectangles.Entities.Range;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.avehlies.Rectangles.TestUtilities.TestUtilities.range;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for RangeUtility
 */
public class RangeUtilityTest {

    public static Stream<Arguments> getIntersectedRangeProvider() {
        return Stream.of(
                Arguments.of(range(0, 10), range(11, 20), range(null, null)),
                Arguments.of(range(0, 10), range(10, 15), range(10, 10)),
                Arguments.of(range(0, 10), range(0, 10), range(0, 10)),
                Arguments.of(range(11, 20), range(0, 10), range(null, null)),
                Arguments.of(range(10, 15), range(0, 10), range(10, 10))
        );
    }

    @ParameterizedTest
    @MethodSource("getIntersectedRangeProvider")
    public void testGetIntersectedRange(
            Range a,
            Range b,
            Range expected
    ) {
        Range result = RangeUtility.getIntersectedRange(a, b);
        assertTrue(expected.equalTo(result));
    }

}
