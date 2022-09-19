package com.avehlies.Rectangles.Entities;

import java.lang.annotation.*;

/**
 * Annotation for JaCoCo to ignore generated methods (hashCode, equals, toString).
 * There are entire libraries dedicated to testing generated equals methods.
 * Excluding these methods from Code Coverage gives a more accurate value for code coverage percentages
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Generated {
}
