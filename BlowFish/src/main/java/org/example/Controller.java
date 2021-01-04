package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import org.example.algorithm.BlowFish;

import java.util.List;

public class Controller {

    /**
     * Функція для перетворення списку 64-розрядних чисел у текст
     */
    public static String convertNumberListToString(List<Long> inputList) {
        final StringBuilder builder = new StringBuilder();
        for (long v : inputList) {
            builder.append((char) v);
        }
        return builder.toString();
    }

    private final BlowFish blowFish = new BlowFish();

    @FXML
    public TextArea inputText;

    @FXML
    public TextArea cipheredText;

    @FXML
    public TextArea decipheredText;

    @FXML
    public Button calcButton;

    @FXML
    public CheckBox isDecipheredTextCorrect;

    @FXML
    public TextArea keyArea;

    /**
     * Попереднє значення ключа
     */
    private String prevKey = null;

    @FXML
    public void onCalcButtonClicked(ActionEvent ignored) {
        final String keyAreaText = keyArea.getText();

        // Якщо попереднє значення ключа рівне поточному - не робимо повторну ініціалізацію
        if (!keyAreaText.equals(prevKey)) {

            if (keyAreaText.length() > BlowFish.KEY_MAX_BYTES / 2) {
                blowFish.setKey(keyAreaText.substring(0, BlowFish.KEY_MAX_BYTES / 2));
            } else {
                blowFish.setKey(keyAreaText);
            }

            prevKey = keyAreaText;
        }

        isDecipheredTextCorrect.setVisible(true);

        // Робимо текстове поле не активним
        inputText.setDisable(true);

        final String input = inputText.getText();

        // Перетворення вхідного тексту в список чисел
        final List<Long> inputList = BlowFish.Algorithms.convertStringToList(input);

        // Обрахунок шифрування і дешифрування
        final List<Long> cipheredList = blowFish.cipher(inputList);
        final List<Long> decipheredList = blowFish.decipher(cipheredList);

        final String cipheredValue = convertNumberListToString(cipheredList);
        final String decipheredValue = convertNumberListToString(decipheredList);

        cipheredText.setText(cipheredValue);
        decipheredText.setText(decipheredValue);

        // Встановлюємо значення прапорця у вікні в залежності від правильності розшифрування
        isDecipheredTextCorrect.setSelected(input.equals(decipheredValue));

        // Розблоковуємо текстове поле
        inputText.setDisable(false);
    }

}
