package solution;

import java.util.Random;

public class Feistel {
    private final int ROUNDS;
    private int[] KEYS;
    private final FeistelFunc feistelFunc;

    public Feistel(int rounds, FeistelFunc feistelFunc) {
        this.ROUNDS = rounds;
        this.feistelFunc = feistelFunc;
        initKeys();
    }

    private void initKeys() {
        int[] keys = new int[ROUNDS];
        Random random = new Random();
        for (int i = 0; i < ROUNDS; i++) {
            keys[i] = random.nextInt();
        }
        this.KEYS = keys;
    }

    public Result cipherText(String text) {
        boolean textIsOdd = text.length() % 2 == 1;
        String correctText = assertTextLengthIsCorrect(text);
        final int N = correctText.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < N - 1; i += 2) {
            int[] ciphered = cipher(correctText.charAt(i), correctText.charAt(i + 1));
            builder.append((char) ciphered[0]);
            builder.append((char) ciphered[1]);
        }
        return new Result(builder.toString(), textIsOdd);
    }

    public String decipherText(Result result) {
        String text = result.getText();
        final int N = text.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < N - 1; i += 2) {
            int[] ciphered = decipher(text.charAt(i), text.charAt(i + 1));
            builder.append((char) ciphered[0]);
            builder.append((char) ciphered[1]);
        }
        String textResult = builder.toString();
        return result.getLenWasOdd() ? textResult.substring(0, N - 1) : textResult;
    }

    private String assertTextLengthIsCorrect(String text) {
        return text.length() % 2 == 0 ? text : text + " ";
    }

    private int[] cipher(int a, int b) {
        for (int i = 0; i < ROUNDS; i++) {
            int t = a ^ feistelFunc.calc(b, KEYS[i]);
            a = b;
            b = t;
        }
        return new int[] {b, a};
    }

    private int[] decipher(int a, int b) {
        for (int i = ROUNDS - 1; i >= 0; i--) {
            int t = a ^ feistelFunc.calc(b, KEYS[i]);
            a = b;
            b = t;
        }
        return new int[] {b, a};
    }

    public static class Result {
        private final String text;
        private final Boolean lenWasOdd;

        public Result(String text, Boolean lenWasOdd) {
            this.text = text;
            this.lenWasOdd = lenWasOdd;
        }

        public Boolean getLenWasOdd() {
            return lenWasOdd;
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

}
