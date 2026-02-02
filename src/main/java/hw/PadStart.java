package hw;

public class PadStart {

    private PadStart() {  }

    public static String padStart(String s, int n, String fill) {
        if (s == null) throw new IllegalArgumentException("s cannot be null");
        if (n < 0) throw new IllegalArgumentException("n must be greater or equal to 0");

        if (s.length() >= n) return s;

        if (fill == null || fill.isEmpty()) {
            return s;
        }

        int padLen = n - s.length();
        StringBuilder sb = new StringBuilder(n);

        while (sb.length() < padLen) sb.append(fill);

        sb.setLength(padLen);
        sb.append(s);

        return sb.toString();
    }
}