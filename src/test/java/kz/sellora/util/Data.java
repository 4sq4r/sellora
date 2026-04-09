package kz.sellora.util;

import kz.sellora.core.model.entity.Company;
import kz.sellora.core.model.entity.User;
import kz.sellora.core.model.enums.CompanyStatus;

public final class Data {

    public static final String VALID_USERNAME = "validUsername";
    public static final String VALID_PASSWORD = "validPassword";
    public static final String DEVICE_ID = "deviceId";
    public static final String INVALID_USERNAME = "invalidUsername";
    public static final String INVALID_PASSWORD = "invalidPassword";
    public static final String COMPANY_NAME = "companyName";

    public static final Company COMPANY = buildCompany();
    public static final User USER = buildUser();


    private static Company buildCompany() {
        var company = new Company();
        company.setName(COMPANY_NAME);
        company.setStatus(CompanyStatus.ACTIVE);

        return company;
    }
    private static User buildUser() {
        var user = new User();
        user.setUsername(VALID_USERNAME);
        user.setPassword(VALID_PASSWORD);
        user.setCompany(buildCompany());

        return user;
    }
}
