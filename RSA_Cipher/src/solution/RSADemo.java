package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RSADemo {

    private static List<Integer> pickRandomTwo(List<Integer> inputList) {
        Random random = new Random();
        final int N = inputList.size();
        int a = Math.abs(random.nextInt()) % N;
        int b = Math.abs(random.nextInt()) % N;
        while (b == a) {
            b = Math.abs(random.nextInt()) % N;
        }
        return List.of(inputList.get(a), inputList.get(b));
    }

    private static List<Integer> stringToIntList(String text) {
        final int N = text.length();
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            integers.add((int)text.charAt(i));
        }
        return integers;
    }

    private static String intListToString(List<Integer> integers) {
        final int N = integers.size();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int v = integers.get(i);
            builder.append((char) v);
        }
        return builder.toString();
    }

    public static void main(String[] args) {

        // Генеруємо 60 перших простих чисел і обираємо два довільних випадково
        List<Integer> inputValues = pickRandomTwo(RSAlgorithms.generatePrimeNumbers(1200));

        int p = inputValues.get(0);
        int q = inputValues.get(1);

        // Обєкт що описує RSA конфігурацію
        RSA rsa = new RSA(p, q);

        System.out.println("Параметри RSA: ");
        System.out.println("p: " +p);
        System.out.println("q: " +q);
        System.out.println("n: " + rsa.getN());
        System.out.println("e: " + rsa.getE());
        System.out.println("d: " + rsa.getD());

        // Обєкти шифрування і дешифрування
        RSACipher cipher = new RSACipher(rsa.getE(), rsa.getN());
        RSADecipher decipher = new RSADecipher(rsa.getD(), rsa.getN());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть текст:");
        while (scanner.hasNext()) {
            String inputText = scanner.nextLine();
            if ("stop".equals(inputText)) {
                break;
            }
            List<Integer> inputList = stringToIntList(inputText);
            List<Integer> cipheredList = cipher.cipher(inputList);
            List<Integer> decipheredList = decipher.decipher(cipheredList);

            System.out.println("Тестове подання:");
            System.out.println("Вхідний текст: " + inputText);
            System.out.println("Зашифрований текст: " + intListToString(cipheredList));
            System.out.println("Розшифрований текст: " + intListToString(decipheredList));

            System.out.println("\nПодання у вигляді списку чисел: ");
            System.out.println("Вхідне повідомлення: " + inputList);
            System.out.println("Зашифроване повідомлення: " + cipheredList);
            System.out.println("Розшифроване повідомлення: " + decipheredList);
        }

        System.out.println("Програму завершено");
    }

}
