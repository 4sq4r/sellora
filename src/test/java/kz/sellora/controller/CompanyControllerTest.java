//package kz.sellora.controller;
//
//import kz.sellora.api.model.CompanyDTO;
//import kz.sellora.core.repository.jpa.CompanyRepository;
//import kz.sellora.core.repository.jpa.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.util.UUID;
//
//import static kz.sellora.util.Endpoints.COMPANIES;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//class CompanyControllerTest extends ControllerTest {
//
//    private static final String SELLORA_COMPANY = "SELLORA";
//    private static final String OTHER_COMPANY = "Other Company";
//
//    @Autowired
//    private CompanyRepository companyRepository;
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @BeforeEach
//    void clean() {
//        companyRepository.deleteAll();
//        userRepository.deleteAll();
//    }
//
//    @Test
//    void saveOne_throwsException_withoutAuthentication() {
//        CompanyDTO companyDTO = new CompanyDTO();
//        companyDTO.setName(UUID.randomUUID().toString());
//
//        sendPostRequestWithoutAuth(COMPANIES, companyDTO);
//    }
//
//    @Test
//    void saveOne_shouldReturnCreatedCompany() {
//        String companyName = UUID.randomUUID().toString();
//        CompanyDTO companyDTO = new CompanyDTO();
//        companyDTO.setName(companyName);
//        MvcResult result = sendPostRequestWithAuth(COMPANIES, companyDTO, login(), status().isOk());
//        CompanyDTO response = readMvcResultAsString(result, CompanyDTO.class);
//
//        assertNotNull(response.getId());
//        assertEquals(companyName, response.getName());
//        assertNotNull(response.getCreatedAt());
//        assertNotNull(response.getUpdatedAt());
//    }
//
//    @Test
//    void privateEndpoint_getCompanies_shouldReturn401WithoutAuthentication() {
//        sendGetRequest(COMPANIES, status().isUnauthorized());
//    }
//
//    @Test
//    void getOne_shouldReturnCompany() {
//        CompanyDTO companyDTO = new CompanyDTO();
//        companyDTO.setName("Test Company");
//
//        MvcResult createResult = sendPostRequest(COMPANIES, companyDTO, status().isOk());
//        CompanyDTO created = readMvcResultAsString(createResult, CompanyDTO.class);
//
//        MvcResult getResult = sendGetRequest(COMPANIES + "/" + created.getId(), status().isOk());
//        CompanyDTO response = readMvcResultAsString(getResult, CompanyDTO.class);
//
//        assertEquals(created.getId(), response.getId());
//        assertEquals("Test Company", response.getName());
//    }
//
//    @Test
//    void getOne_shouldReturn404WhenNotFound() {
//        sendGetRequest(COMPANIES + "/nonexistent-id", status().is4xxClientError());
//    }
//
//    @Test
//    void saveOne_shouldReturnCreatedCompany_whenUserBelongsToSELLORA() {
//        String companyName = UUID.randomUUID().toString();
//        CompanyDTO companyDTO = new CompanyDTO();
//        companyDTO.setName(companyName);
//
//        // Login with SELLORA company user
//        String token = loginWithCompany(SELLORA_COMPANY);
//
//        MvcResult result = sendPostRequestWithAuth(COMPANIES, companyDTO, token, status().isOk());
//        CompanyDTO response = readMvcResultAsString(result, CompanyDTO.class);
//
//        assertNotNull(response.getId());
//        assertEquals(companyName, response.getName());
//        assertNotNull(response.getCreatedAt());
//        assertNotNull(response.getUpdatedAt());
//    }
//
//    @Test
//    void saveOne_shouldReturn403Forbidden_whenUserBelongsToOtherCompany() {
//        String companyName = UUID.randomUUID().toString();
//        CompanyDTO companyDTO = new CompanyDTO();
//        companyDTO.setName(companyName);
//
//        // Login with non-SELLORA company user
//        String token = loginWithCompany(OTHER_COMPANY);
//
//        sendPostRequestWithAuth(COMPANIES, companyDTO, token, status().isForbidden());
//    }
//
//    @Test
//    void getOne_shouldReturnCompany_whenUserBelongsToSELLORA() {
//        // Login with SELLORA company user first to create company
//        String token = loginWithCompany(SELLORA_COMPANY);
//
//        CompanyDTO companyDTO = new CompanyDTO();
//        companyDTO.setName("Test Company for SELLORA");
//
//        MvcResult createResult = sendPostRequestWithAuth(COMPANIES, companyDTO, token, status().isOk());
//        CompanyDTO created = readMvcResultAsString(createResult, CompanyDTO.class);
//
//        MvcResult getResult = sendGetRequestWithAuth(COMPANIES + "/" + created.getId(), token, status().isOk());
//        CompanyDTO response = readMvcResultAsString(getResult, CompanyDTO.class);
//
//        assertEquals(created.getId(), response.getId());
//        assertEquals("Test Company for SELLORA", response.getName());
//    }
//
//    @Test
//    void getOne_shouldReturn403Forbidden_whenUserBelongsToOtherCompany() {
//        String selloraToken = loginWithCompany(String username, String password, SELLORA_COMPANY);
//
//        CompanyDTO companyDTO = new CompanyDTO();
//        companyDTO.setName(UUID.randomUUID().toString());
//
//        MvcResult createResult = sendPostRequestWithAuth(COMPANIES, companyDTO, selloraToken, status().isOk());
//        CompanyDTO created = readMvcResultAsString(createResult, CompanyDTO.class);
//
//        String otherToken = loginWithCompany(OTHER_COMPANY);
//        sendGetRequestWithAuth(COMPANIES + "/" + created.getId(), otherToken, status().isForbidden());
//    }
//}
