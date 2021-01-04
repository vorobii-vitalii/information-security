package org.example.solution.service;


import org.example.solution.Response;

import java.util.HashMap;
import java.util.Map;

public class UserAuthorizationServiceImpl implements UserAuthorizationService {
    private static final int TOO_MANY_TRIES = 3;

    // Кеш користувачів
    private final Map<String, Integer> userAuthorizationAttempts = new HashMap<>();

    // Функція перетворення
    private final TransformationFunction<Integer> transformationFunction = new DoubleTransformationFunction();

    @Override
    public Response tryToGetAccess(String userName, Integer input, Integer entered) {

        // Поточна кількість спроб
        final int currentNumberOfAttempts =
                userAuthorizationAttempts.getOrDefault(userName, 0);

        // Якщо число введене вірно - запит пройшов успішно
        if (transformationFunction.compute(input).equals(entered)) {
            // Додатково видаляємо користувча з кешу
            userAuthorizationAttempts.remove(userName);
            return Response.SUCCESS;
        }

        final int incrementedNumberOfAttempts = currentNumberOfAttempts + 1;

        // Збільшуємо число спроб вводу пароля користувача на одиницю в кеші
        userAuthorizationAttempts.put(userName, incrementedNumberOfAttempts);

        // Якщо число спроб вводу пароля кратна TOO_MANY_TRIES (3) ми просимо ввести CAPTCHA
        if (incrementedNumberOfAttempts % TOO_MANY_TRIES == 0) {
            return Response.WRONG_TOO_MANY_TRIES;
        }

        // В іншому випадку просто просимо користувача ввести пароль ще раз
        return Response.WRONG;
    }
}
