package solution;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class EncoderText {

    public static void main(String[] args) throws FileNotFoundException {
        // Читання вхідної таблиці
        CharHashMapReader reader = new CharHashMapReader("4_map.txt");
        Map<Character, Character> map = reader.retrieveMap();

        Encoder encoder = new Encoder(map);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String inputText = scanner.nextLine();
            if (inputText.equals("stop")) {
                break;
            }
            String ciphered = encoder.cipherText(inputText);
            String deciphered = encoder.decipherText(ciphered);
            System.out.println("Вхідний текст: " + inputText);
            System.out.println("Зашифрований: " + ciphered);
            System.out.println("Розшифрований: " + deciphered);
            System.out.println("Розшифрування пройшло успішно " + ((inputText.equals(deciphered)) ? "Так" : "Ні"));
        }

        System.out.println("Програму завершено");


    }

}
