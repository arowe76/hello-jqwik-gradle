package hw;

import static org.junit.jupiter.api.Assertions.*;

import net.jqwik.api.*;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.*;


class PadStartProperties {

    /**
     * ZOMBIE-style Examples:
     */

    @Example
    void zero() {
        assertEquals("", PadStart.padStart("", 0, "0"));
    }

    @Example
    void one() {
        assertEquals("0a", PadStart.padStart("a", 2, "0"));
    }

    @Example
    void many() {
        assertEquals("00000a", PadStart.padStart("a", 6, "0"));
    }

    @Example
    void boundaryNoPaddingNeeded() {
        assertEquals("abc", PadStart.padStart("abc", 3, "0"));
    }

    @Example
    void emptyFillCannotPad() {
        assertEquals("a", PadStart.padStart("a", 3, ""));
    }

    /**
     * Property #1 - Length Guarantee
     * |padStart(s,n,f)| = max(|s|, n)...when padding is possible
     */

    @Property
    boolean lengthGuaranteeWhenFillNonEmpty(
            @ForAll String s,
            @ForAll @IntRange(min = 0, max = 200) int n,
            @ForAll("nonEmptyFill") String f
    ) {
        String out = PadStart.padStart(s, n, f);
        return out.length() == Math.max(s.length(), n);
    }

    @Provide
    Arbitrary<String> nonEmptyFill() {
        return Arbitraries.strings().ascii().ofMinLength(1).ofMaxLength(10);
    }

    /**
     * Property #2 - No padding needed
     * |s| >= n --> padStart(s,n,f) = s
     */

    @Property
    boolean noPaddingWhenAlreadyLongEnough(
            @ForAll String s,
            @ForAll @IntRange(min = 0, max = 200) int n,
            @ForAll String f
    ) {
        Assume.that(s.length() >= n);
        return PadStart.padStart(s, n, f).equals(s);
    }

    /**
     * Property #3 - Original string is always a suffix
     */

    @Property
    boolean originalIsSuffix(
            @ForAll String s,
            @ForAll @IntRange(min = 0, max = 200) int n,
            @ForAll String f
    ) {
        String out = PadStart.padStart(s, n, f);
        return out.endsWith(s);
    }
}

