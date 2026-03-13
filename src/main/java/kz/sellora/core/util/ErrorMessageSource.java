package kz.sellora.core.util;


public enum ErrorMessageSource {

    USERNAME_ALREADY_EXISTS("Username already exists: %s"),
    INVALID_USERNAME_OR_PASSWORD("Invalid username or password.")
    ;

    private String text;

    ErrorMessageSource(String text) {
        this.text = text;
    }

    public String getText(String... params) {
        return String.format(this.text, params);
    }
}
