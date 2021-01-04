package solution;

import java.util.Scanner;

public class FeistelDemo {
    private static final String STOP_TEXT = "stop";
    private static final Feistel feistel = new Feistel(10, (R, K) -> R << K);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введіть текст");
        while (scan.hasNext()) {
            String text = scan.nextLine();
            if (text.toLowerCase().equals(STOP_TEXT)) {
                break;
            }
            Feistel.Result cipherResult = feistel.cipherText(text);
            String decipheredText = feistel.decipherText(cipherResult);
            System.out.println("Вхідний текст: " + text);
            System.out.println("Зашифрований текст: " + cipherResult);
            System.out.println("Розшифрований текст: " + decipheredText);
            System.out.println("Розшифрування пройшло успішно: " + (text.equals(decipheredText) ? "Так" : "Ні"));
            System.out.println();
        }
        System.out.println("Програму завершено");
    }

}
