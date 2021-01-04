package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DesDemo {

    /**
     * Функція для перетворення тексту у список 64 бітних чисел
     * @param text Вхідний текст
     */
    public static List<Long> stringToList(String text) {
        final List<Long> list = new ArrayList<>();
        for (char c : text.toCharArray()) {
            list.add((long) c);
        }
        return list;
    }

    /**
     * Функція для перетворення списку чисел у текст
     */
    public static String listToString(List<Long> list) {
        final int N = list.size();
        final StringBuilder stringBuilder = new StringBuilder();
        for (long aLong : list) {
            stringBuilder.append((char) aLong);
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        final DES des = new DES();

        final Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть текст: ");

        while (scanner.hasNext()) {
            final String inputText = scanner.nextLine();
            if (inputText.equals("stop")) {
                break;
            }
            final List<Long> inputList = stringToList(inputText);
            final List<Long> cipheredList = des.cipher(inputList);
            final List<Long> decipheredList = des.decipher(cipheredList);

            System.out.println("Вхідний текст: " + inputText);
            System.out.println("Зашифрований текст: " + listToString(cipheredList));
            System.out.println("Розшифрований текст: " + listToString(decipheredList));
            System.out.println("Розшифрування пройшло успішно? " + (inputList.equals(decipheredList) ? "Так" : "Ні"));
        }

        System.out.println("Програму завершено");
    }

}
