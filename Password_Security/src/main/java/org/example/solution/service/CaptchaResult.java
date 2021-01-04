package org.example.solution.service;

/**
 * Клас що описує CAPTCHA
 */
public class CaptchaResult {
    private final String question;
    private final String rightAnswer;

    public CaptchaResult(String question, String rightAnswer) {
        this.question = question;
        this.rightAnswer = rightAnswer;
    }

    protected String getRightAnswer() {
        return rightAnswer;
    }

    public String getQuestion() {
        return question;
    }
}