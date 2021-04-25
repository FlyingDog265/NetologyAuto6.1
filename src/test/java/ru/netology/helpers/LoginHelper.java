package ru.netology.helpers;

public class LoginHelper {
    private LoginHelper() {
    }

    public static class AuthInfo {
        String login;
        String password;

        public AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }
}
