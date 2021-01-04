package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.example.solution.Response;
import org.example.solution.service.*;

import java.util.Random;

public class Controller {

    private static final Random r = new Random();

    @FXML
    public AnchorPane authorization_pane;

    @FXML
    public AnchorPane secret_pane;

    @FXML
    public TextField username_login;

    @FXML
    public ProgressBar loading_indicator;

    @FXML
    public Label info_label;

    @FXML
    public Button try_get_resource_button;

    @FXML
    public PasswordField provided_num_input;

    @FXML
    public Label captcha_question;

    @FXML
    public TextField captcha_answer;

    @FXML
    public Label error;

    @FXML
    public ImageView secret_image;

    // Сервіс каптчі
    private final CaptchaService captchaService = new AdditionCaptchaService();

    // Сервіс авторизації
    private final UserAuthorizationService userAuthorizationService = new UserAuthorizationServiceImpl();

    // Затримка вводу пароля
    private static final int DELAY_WRONG = 4000; // У мілісекундах

    // Поточне згенероване число
    private Integer currentGeneratedValue = r.nextInt(50);

    private CaptchaResult captchaResult;

    @FXML
    public void handle_try_get_resource(ActionEvent event) {

        final String userName = username_login.getText();

        // Перевірка вводу ім'я користувача
        if (isInputEmpty(userName)) {
            error.setVisible(true);
            error.setText("Ім'я користувача пусте");
            return;
        } else {
            error.setVisible(false);
        }

        // Перевірка вводу катчі (за наявності)
        if (captcha_question.isVisible()) {

            if (!captchaService.match(captchaResult, captcha_answer.getText())) {
                error.setVisible(true);
                error.setText("Капчу введено не вірно");
            } else {
                captcha_question.setVisible(false);
                captcha_answer.setVisible(false);
                return;
            }
        }

        // Перевірка вводу числа
        final int enteredNum;
        try {
            enteredNum = Integer.parseInt(provided_num_input.getText().trim());
        }
        catch (NumberFormatException e) {
            error.setVisible(true);
            error.setText("Не вірний формат числа");
            return;
        }

        // Спроба отримати доступ
        final Response response =
                userAuthorizationService.tryToGetAccess(userName, currentGeneratedValue, enteredNum);

        switch (response) {
            case WRONG: {
                makeDelay();
                break;
            }
            case SUCCESS: {
                disableCaptcha();
                openResource();
                break;
            }
            case WRONG_TOO_MANY_TRIES: {
                initCaptcha();
                makeDelay();
            }
        }

    }
    
    private void openResource() {
        authorization_pane.setVisible(false);
        secret_pane.setVisible(true);

        // Встановлення зображення
        Image file = new Image(getClass().getResourceAsStream("/secret.jpg"));
        secret_image.setImage(file);
    }

    /**
     * Приховання каптчі
     */
    private void disableCaptcha() {
        captcha_question.setVisible(false);
        captcha_answer.setVisible(false);
    }

    /**
     * Ініціалізація каптчі
     */
    private void initCaptcha() {
        captchaResult = captchaService.generateCaptcha();
        captcha_question.setVisible(true);
        captcha_question.setText(captchaResult.getQuestion());
        captcha_answer.setVisible(true);
    }

    @FXML
    public void initialize() {
        info_label.setText("Згенероване число системою: " + currentGeneratedValue);
    }

    /**
     * Функція створення затримки вводу
     */
    private void makeDelay() {

        // Робимо кнопку недоступну
        try_get_resource_button.setDisable(true);

        // В окремому потоці запускаємо програму що змінює індикатор прогрес бару
        new Thread(() -> {
            final int numberOfIterations = 100;
            loading_indicator.setVisible(true);
            for (int i = 1; i <= numberOfIterations; i++) {
                try {
                    Thread.sleep(DELAY_WRONG / numberOfIterations);
                } catch (InterruptedException ignored) {}
                loading_indicator.setProgress((double) i / numberOfIterations);
            }
            loading_indicator.setVisible(false);
        }).start();

        // В іншому потоці запускаємо програму, що перешкоджає натисканні на кнопку деякий час
        new Thread(() -> {
            try {
                Thread.sleep(DELAY_WRONG);
            } catch (InterruptedException ignored) {}
            try_get_resource_button.setDisable(false);
        }).start();
    }

    /**
     * Функція для перевірки значення на пустоту
     * @param value Вхідне значення
     */
    private boolean isInputEmpty(String value) {
        return value.trim().equals("");
    }

}
