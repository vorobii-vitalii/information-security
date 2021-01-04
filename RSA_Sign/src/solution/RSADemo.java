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
        for (int v : integers) {
            builder.append((char) v);
        }
        return builder.toString();
    }

    public static void main(String[] args) {

        // Генеруємо 60 перших простих чисел і обираємо два довільних випадково
        List<Integer> inputValues = pickRandomTwo(RSAlgorithms.generatePrimeNumbers(60));

        final int p = inputValues.get(0);
        final int q = inputValues.get(1);

        // Обєкт що описує RSA конфігурацію
        RSA rsa = new RSA(p, q);

        System.out.println("Параметри RSA: ");
        System.out.println("p: " +p);
        System.out.println("q: " +q);
        System.out.println("n: " + rsa.getN());
        System.out.println("e: " + rsa.getE());
        System.out.println("d: " + rsa.getD());

        // Обєкт для знаходження хеш-коду
        RSAHasher hasher = new RSAHasher(rsa.getN());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть текст:");
        while (scanner.hasNext()) {
            String inputText = scanner.nextLine();
            if ("stop".equals(inputText)) {
                break;
            }
            List<Integer> inputList = stringToIntList(inputText);
            final int m = hasher.hash(inputList);
            final int S = RSAlgorithms.discretePower(m, rsa.getD(), rsa.getN());
            final int mHashed = RSAlgorithms.discretePower(S, rsa.getE(), rsa.getN());
            System.out.println("m = " + m);
            System.out.println("S = " + S);
            System.out.println("m' = " + mHashed);
            System.out.println("Збігаються? " + ((m == mHashed) ? "Так" : "Ні"));
        }

        System.out.println("Програму завершено");
    }

}
