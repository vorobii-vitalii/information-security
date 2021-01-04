package solution;

import java.util.*;
import java.util.stream.Collectors;

public class ElHamalDemo {
    private static final int NUMBER_OF_PRIMES = 5000;

    /**
     * Функція для вибору двох випадкових чисел зі списку
     */
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
        for (int v : integers) {
            builder.append((char) v);
        }
        return builder.toString();
    }

    public static void main(String[] args) {

        List<Integer> primes = Algorithms.generatePrimeNumbers(NUMBER_OF_PRIMES);

        // Фільтруємо прості числа менші 10 000
        primes = primes
                    .stream()
                    .filter(prime -> prime > 10_000)
                    .collect(Collectors.toList());

        // Залишаємо два випадкові
        primes = pickRandomTwo(primes);

        int a = primes.get(0);
        int b = primes.get(1);

        // Встановлюємо p > q
        final int p = Math.max(a, b);
        final int q = Math.min(a, b);

        System.out.println("p = " + p);
        System.out.println("q = " + q);

        final ElHamal elHamal = new ElHamal(p, q);

        System.out.println("x = " + elHamal.getX());
        System.out.println("y = " + elHamal.getY());

        final ElHamalCipher cipher = new ElHamalCipher(elHamal);
        final ElHamalDecipher decipher = new ElHamalDecipher(elHamal);

        final Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть текст: ");
        while (scanner.hasNext()) {
            final String inputText = scanner.nextLine();
            if (inputText.equals("stop")) {
                break;
            }
            List<Integer> inputList = stringToIntList(inputText);
            List<Integer> ciphered = cipher.cipher(inputList);
            List<Integer> deciphered = decipher.decipher(ciphered);

            System.out.println("Вхідний текст: " + inputText);
            System.out.println("Зашифрований текст: " + intListToString(ciphered));
            System.out.println("Розшифрований текст: " + intListToString(deciphered));
        }
        System.out.println("Програму завершено");

    }

}
