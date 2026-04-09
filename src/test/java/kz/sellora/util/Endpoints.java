package kz.sellora.util;

public final class Endpoints {

    private static final String PRIVATE = "/private";
    private static final String PUBLIC = "/public";

    // Private endpoints
    public static final String COMPANIES = PRIVATE + "/companies";
    public static final String ROLES = PRIVATE + "/roles";
    public static final String PERMISSIONS = PRIVATE + "/permissions";
    public static final String TENANTS = PRIVATE + "/tenants";
    public static final String USERS = PRIVATE + "/users";
    public static final String MEMBERSHIPS = PRIVATE + "/memberships";

    // Public endpoints
    public static final String AUTH_SIGN_IN = PUBLIC + "/auth/sign-in";
    public static final String AUTH_REFRESH = PUBLIC + "/auth/refresh";
}
