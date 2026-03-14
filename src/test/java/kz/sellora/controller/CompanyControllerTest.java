package kz.sellora.controller;

import kz.sellora.api.model.CompanyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static kz.sellora.util.Endpoints.COMPANIES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CompanyControllerTest extends ControllerTest {

    @Test
    void saveOne_shouldReturnCreatedCompany() {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName("Test Company");

        MvcResult result = sendPostRequest(COMPANIES, companyDTO, status().isOk());
        CompanyDTO response = readMvcResultAsString(result, CompanyDTO.class);

        assertNotNull(response.getId());
        assertEquals("Test Company", response.getName());
        assertNotNull(response.getCreatedAt());
        assertNotNull(response.getUpdatedAt());
    }

    @Test
    void getOne_shouldReturnCompany() {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName("Test Company");

        MvcResult createResult = sendPostRequest(COMPANIES, companyDTO, status().isOk());
        CompanyDTO created = readMvcResultAsString(createResult, CompanyDTO.class);

        MvcResult getResult = sendGetRequest(COMPANIES + "/" + created.getId(), status().isOk());
        CompanyDTO response = readMvcResultAsString(getResult, CompanyDTO.class);

        assertEquals(created.getId(), response.getId());
        assertEquals("Test Company", response.getName());
    }

    @Test
    void getOne_shouldReturn404WhenNotFound() {
        sendGetRequest(COMPANIES + "/nonexistent-id", status().is4xxClientError());
    }
}
