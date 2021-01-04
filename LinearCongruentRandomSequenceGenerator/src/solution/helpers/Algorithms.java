package solution.helpers;

public final class Algorithms {

    /**
     * @throws IndexOutOfBoundsException коли довжина маски менше довжини тексту
     */
    public static String xorTextWithMask(String text, Integer [] mask) {
        final int n = text.length();
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append((char) ( mask[i] ^ ((int) text.charAt(i)) ));
        }
        return builder.toString();
    }

}
