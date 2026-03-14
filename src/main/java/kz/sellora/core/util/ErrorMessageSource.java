package kz.sellora.core.util;


public enum ErrorMessageSource {

    INVALID_REFRESH_TOKEN("Invalid refresh token."),
    FAILED_TO_ROTATE_REFRESH_TOKEN("Failed to rotate refresh token."),
    REFRESH_TOKEN_NOT_FOUND("Refresh token not found."),
    USERNAME_ALREADY_EXISTS("Username already exists: %s"),
    USER_NOT_FOUND("User not found: {}"),
    INVALID_USERNAME_OR_PASSWORD("Invalid username or password."),
    TENANT_ALREADY_EXISTS("Tenant already exists: %s"),
    TENANT_NOT_FOUND("Tenant not found: %s"),
    COMPANY_ALREADY_EXISTS("Company already exists: %s"),
    COMPANY_NOT_FOUND("Company not found: %s"),
    ROLE_NOT_FOUND("Role not found: %s"),
    MEMBERSHIP_NOT_FOUND("Membership not found: %s"),
    PERMISSION_NOT_FOUND("Permission not found: %s");

    private String text;

    ErrorMessageSource(String text) {
        this.text = text;
    }

    public String getText(String... params) {
        return String.format(this.text, params);
    }
}
