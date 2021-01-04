package org.example.solution.service;

public interface CaptchaService {
    CaptchaResult generateCaptcha();
    boolean match(CaptchaResult captchaResult, String entered);
}
