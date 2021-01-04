package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RabinDemo {

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

    private static void printDecipherResult(List<Integer> list) {
        final int N = list.size();
        List<Integer> localList = new ArrayList<>();
        for (int i = 0; i < N - 3; i += 4) {
            localList.add(list.get(i));
            localList.add(list.get(i + 1));
            localList.add(list.get(i + 2));
            localList.add(list.get(i + 3));
            System.out.println(localList);
            localList.clear();
        }
    }

    public static void main(String[] args) {

        // Генеруємо 60 перших простих чисел, залишаємо тільки такі, що a mod 4 = 3 і обираємо два довільних випадково
        List<Integer> primeNumbers = Algorithms
                .generatePrimeNumbers(60)
                .stream()
                .filter(a -> a % 4 == 3)
                .collect(Collectors.toList());
        List<Integer> inputValues = pickRandomTwo(primeNumbers);

        final int p = 167;
        final int q = 59;

        System.out.println("p = " + p);
        System.out.println("q = " + q);

        final int N = p * q;

        RabinCipher cipher = new RabinCipher(N);
        RabinSecret secret = new RabinSecret(p, q);
        RabinDecipher decipher = new RabinDecipher(p, q);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String inputText = scanner.nextLine();
            if ("stop".equals(inputText)) {
                break;
            }
            final List<Integer> inputList = stringToIntList(inputText);
            final List<Integer> cipheredList = cipher.cipher(inputList);
            final List<Integer> secretKey = secret.formSecret(inputList, cipheredList);
            final List<Integer> decipheredList = decipher.decipher(cipheredList, secretKey);
            System.out.println("Вхідний текст: " + intListToString(inputList));
            System.out.println("Зашифрований текст: " + intListToString(cipheredList));
            System.out.println("Розшифрований текст: " + intListToString(decipheredList));
            System.out.println("Співпадає " + inputList.equals(decipheredList));
        }
        System.out.println("Програму завершено");
    }

}
