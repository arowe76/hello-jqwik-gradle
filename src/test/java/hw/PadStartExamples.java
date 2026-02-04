package hw;

import net.jqwik.api.Example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PadStartExamples {

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

    @Example
    void nullStringThrows() {
        assertThrows(IllegalArgumentException.class, () -> PadStart.padStart(null, 3, "0"));
    }

    @Example
    void negativeLengthThrows() {
        assertThrows(IllegalArgumentException.class, () -> PadStart.padStart("a", -1, "0"));
    }

    @Example
    void alreadyLongEnoughReturnsOriginal() {
        assertEquals("abcd", PadStart.padStart("abcd", 2, "0"));
    }

    @Example
    void emptyFillReturnsOriginal() {
        assertEquals("x",  PadStart.padStart("x", 3, ""));
    }

    @Example
    void longFillIsTruncated() {
        // padLen = 2, fill = "xyz" --> "xy"
        assertEquals("xyA", PadStart.padStart("A", 3, "xyz"));
    }

    @Example
    void repeatedFillIsUsed() {
        assertEquals("ababaZ", PadStart.padStart("Z", 6, "ab"));
    }

    @Example
    void nullFillReturnsOriginal() {
        assertEquals("x", PadStart.padStart("x", 3, null));
    }
}
