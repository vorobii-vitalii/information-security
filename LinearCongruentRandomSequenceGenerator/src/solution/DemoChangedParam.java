package solution;

import java.util.Arrays;

public class DemoChangedParam {
    private static final int A = 6; // Змінено параметр, що не задовільняє вимогам
    private static final int C = 3;
    private static final int M = 128;
    private static final int T_0 = 1;

    public static void main(String[] args) {
        String text = "abcdefghj";
        LinearCongruentSequenceGenerator gen = new LinearCongruentSequenceGenerator(A, C, M, T_0);
        CipherPseudoRandom cipher = new CipherPseudoRandom(gen);
        DecipherPseudoRandom decipher = new DecipherPseudoRandom(gen);
        System.out.printf("Вхідний текст: %s%n", text);
        System.out.println("Згенеровані псевдовипадкові числа: " + Arrays.toString(gen.generateSequence(text.length())));
        String encoded = cipher.cipher(text);
        System.out.printf("Зашифрований текст: %s%n", encoded);
        String decoded = decipher.decipher(encoded);
        System.out.printf("Розшифрований текст: %s%n", decoded);
        System.out.printf("Розшифрування пройшло успішно: %s", decoded.equals(text)? "так" : "ні");
    }

}
