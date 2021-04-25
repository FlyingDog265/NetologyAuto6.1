package ru.netology.helpers;

public class VerificationHelper {
    private VerificationHelper() {
    }

    public static class VerificationCode {

        public VerificationCode(String code) {
        }

        public String getCode() {
            return "12345";
        }

        public static VerificationCode getVerificationCodeFor(LoginHelper.AuthInfo authInfo) {
            return new VerificationCode("12345");
        }
    }
}
