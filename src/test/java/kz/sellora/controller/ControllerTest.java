package kz.sellora.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.sellora.api.model.AuthRequestDTO;
import kz.sellora.api.model.AuthResponseDTO;
import kz.sellora.configuration.TestContainerConfiguration;
import kz.sellora.core.model.entity.Company;
import kz.sellora.core.model.entity.User;
import kz.sellora.core.model.enums.CompanyStatus;
import kz.sellora.core.repository.jpa.CompanyRepository;
import kz.sellora.core.repository.jpa.UserRepository;
import kz.sellora.util.Fields;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static kz.sellora.util.Endpoints.AUTH_SIGN_IN;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Rollback
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestContainerConfiguration.class)
@ActiveProfiles("test")
public abstract class ControllerTest {

    protected static final String AUTHORIZATION_HEADER = "Authorization";
    protected static final String BEARER_PREFIX = "Bearer ";

    protected static final String USERNAME = "test";
    protected static final String PASSWORD = "test";
    protected static final String DEVICE_ID = "deviceId";


    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;


    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        assertNotNull(mockMvc);
    }

    protected <T> MvcResult sendPostRequest(String url, T body, ResultMatcher expectedStatus) {
        try {
            return mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(body)))
                .andExpect(expectedStatus)
                .andReturn();
        } catch (Exception e) {
            throw new RuntimeException("MockMvc POST request failed", e);
        }
    }

    protected <T> MvcResult sendPostRequestWithAuth(String url, T body, String token, ResultMatcher expectedStatus) {
        try {
            return mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(body))
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + token))
                .andExpect(expectedStatus)
                .andReturn();
        } catch (Exception e) {
            throw new RuntimeException("MockMvc POST request with auth failed", e);
        }
    }

    protected <T> MvcResult sendPostRequestWithoutAuth(String url, T body) {
        try {
            return mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isUnauthorized())
                .andReturn();
        } catch (Exception e) {
            throw new RuntimeException("MockMvc POST request with auth failed", e);
        }
    }

    protected MvcResult sendGetRequest(String url, ResultMatcher expectedStatus) {
        try {
            return mockMvc.perform(MockMvcRequestBuilders.get(url)
                    .contentType(APPLICATION_JSON)
                    .characterEncoding(UTF_8))
                .andExpect(expectedStatus)
                .andReturn();

        } catch (Exception e) {
            throw new RuntimeException("MockMvc GET request failed", e);
        }
    }

    protected MvcResult sendGetRequestWithAuth(String url, String token, ResultMatcher expectedStatus) {
        try {
            return mockMvc.perform(MockMvcRequestBuilders.get(url)
                    .contentType(APPLICATION_JSON)
                    .characterEncoding(UTF_8)
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + token))
                .andExpect(expectedStatus)
                .andReturn();

        } catch (Exception e) {
            throw new RuntimeException("MockMvc GET request with auth failed", e);
        }
    }

    protected MvcResult sendDeleteRequest(String url, ResultMatcher expectedStatus) {
        try {
            return mockMvc.perform(MockMvcRequestBuilders.delete(url)
                    .contentType(APPLICATION_JSON)
                    .characterEncoding(UTF_8))
                .andExpect(expectedStatus)
                .andReturn();
        } catch (Exception e) {
            throw new RuntimeException("MockMvc DELETE request failed", e);
        }
    }

    protected MvcResult sendDeleteRequestWithAuth(String url, String token, ResultMatcher expectedStatus) {
        try {
            return mockMvc.perform(MockMvcRequestBuilders.delete(url)
                    .contentType(APPLICATION_JSON)
                    .characterEncoding(UTF_8)
                    .header(AUTHORIZATION_HEADER, BEARER_PREFIX + token))
                .andExpect(expectedStatus)
                .andReturn();
        } catch (Exception e) {
            throw new RuntimeException("MockMvc DELETE request with auth failed", e);
        }
    }

    protected <T> T readMvcResultAsString(MvcResult mvcResult, Class<T> valueType) {
        try {
            String json = mvcResult.getResponse().getContentAsString(UTF_8);
            return objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse MVC response", e);
        }
    }

    protected Map<String, Object> readMvcResultAsMap(MvcResult mvcResult) {
        try {
            String json = mvcResult.getResponse().getContentAsString(UTF_8);
            return objectMapper.readValue(json, new TypeReference<HashMap<String, Object>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse MVC response to map", e);
        }
    }

    protected void assertSystemValuesIsNotNull(MvcResult mvcResult) {
        try {
            Map<String, Object> systemValues = objectMapper.readValue(
                mvcResult.getResponse()
                    .getContentAsString(), new TypeReference<HashMap<String, Object>>() {
                }
            );
            assertNotNull(systemValues.get(Fields.ID));
            assertNotNull(systemValues.get(Fields.CREATED_AT));
            assertNotNull(systemValues.get(Fields.UPDATED_AT));
        } catch (Exception e) {
            throw new RuntimeException("Failed to assert system values from MvcResult", e);
        }
    }

    protected String login() {
//        return loginWithCompany("Test Company");
        return null;
    }

    protected String loginWithCompany(String username, String password, String companyName) {
        Company company = new Company();
        company.setName(companyName);
        company.setStatus(CompanyStatus.ACTIVE);
        company = companyRepository.save(company);

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setCompany(company);
        userRepository.save(user);

        AuthRequestDTO auth = new AuthRequestDTO();
        auth.setUsername(username);
        auth.setPassword(password);
        auth.setDeviceId(DEVICE_ID);

        MvcResult result = sendPostRequest(AUTH_SIGN_IN, auth, status().isOk());
        AuthResponseDTO response = readMvcResultAsString(result, AuthResponseDTO.class);

        return response.getToken();
    }

}
