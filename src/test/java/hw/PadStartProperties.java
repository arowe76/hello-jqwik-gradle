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


    /**
     * Property #1 - Length Guarantee
     * |padStart(s,n,f)| = max(|s|, n)...when padding is possible
     */


    /**
     * Property #2 - No padding needed
     * |s| >= n --> padStart(s,n,f) = s
     */


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
        return out.endsWith(f);
    }
}

