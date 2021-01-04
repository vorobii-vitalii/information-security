package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
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

    public static void main(String[] args) {
        List<Integer> primes = Algorithms.generatePrimeNumbers(NUMBER_OF_PRIMES);

        // Фільтруємо прості числа менші 10 000
        primes = primes
                .stream()
                .filter(prime -> prime > 10_000)
                .collect(Collectors.toList());

        // Залишаємо два випадкові
        primes = pickRandomTwo(primes);

        final int p = Math.max(primes.get(0), primes.get(1));
        final int q = Math.min(primes.get(0), primes.get(1));

        System.out.println("p = " + p);
        System.out.println("q = " + q);

        final ElHamal elHamal = new ElHamal(p, q);

        System.out.println("x = " + elHamal.getX());
        System.out.println("y = " + elHamal.getY());

        final ElHamalHasher hasher = new ElHamalHasher(elHamal);
        final ElHamalKeyValidator validator = new ElHamalKeyValidator(elHamal);

        final Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть текст: ");
        while (scanner.hasNext()) {
            final String inputText = scanner.nextLine();
            if (inputText.equals("stop")) {
                break;
            }
            List<Integer> inputList = stringToIntList(inputText);

            final int hash = hasher.hash(inputList);
            final ElHamalKey key = new ElHamalKey(elHamal, hash);

            System.out.println("Вхідний текст: " + inputText);
            System.out.println("Хеш-код: " + hash);
            System.out.println("a: " + key.getA());
            System.out.println("b: " + key.getB());
            System.out.println("Ключ достовірний : " + (validator.validate(key, hash) ? "Так" : "Ні"));
        }
        System.out.println("Програму завершено");

    }

}
