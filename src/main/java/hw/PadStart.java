package hw;

public class PadStart {

    private PadStart() {  }

    /**
     * padStart() returns a new string by left-padding the original string with a specified fill string until the
     * result reaches a given target length.
     */

    public static String padStart(String s, int n, String fill) {

        // Guard against invalid input...
        if (s == null) throw new IllegalArgumentException("s cannot be null");
        if (n < 0) throw new IllegalArgumentException("n must be greater or equal to 0");

        // If the string is already long enough, no padding is needed...
        if (s.length() >= n) return s;

        // Padding is impossible without characters to prepend...
        if (fill == null || fill.isEmpty()) {
            return s;
        }

        // Number of characters that must be added to the left...
        int padLen = n - s.length();
        StringBuilder sb = new StringBuilder(n);

        // Repeatedly prepend the fill string until enough padding exists...
        while (sb.length() < padLen) sb.append(fill);

        // Truncate to excess padding and append the original string...
        sb.setLength(padLen);
        sb.append(s);

        return sb.toString();
    }
}