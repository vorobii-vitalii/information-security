package org.example.solution.service;

import java.util.Random;

public class AdditionCaptchaService implements CaptchaService {
    private final Random r = new Random();

    @Override
    public CaptchaResult generateCaptcha() {

        // Генеруємо два випадкові числа

        int firstNumber = r.nextInt(20);
        int secondNumber = r.nextInt(20);

        // Обчислюємо суму як вірну відповідь
        final int sum = firstNumber + secondNumber;

        // Формуємо запитання і вірну відповідь
        final String question = String.format("%d + %d = ?", firstNumber, secondNumber);
        final String rightAnswer = Integer.toString(sum);

        return new CaptchaResult(question, rightAnswer);
    }

    @Override
    public boolean match(CaptchaResult captchaResult, String entered) {
        return captchaResult.getRightAnswer().equals(entered.trim());
    }
}
