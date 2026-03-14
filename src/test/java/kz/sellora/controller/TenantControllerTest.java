package kz.sellora.controller;

import kz.sellora.api.model.TenantDTO;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static kz.sellora.util.Endpoints.TENANTS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TenantControllerTest extends ControllerTest {

    @Test
    void saveOne_shouldReturnCreatedTenant() {
        TenantDTO tenantDTO = new TenantDTO();
        tenantDTO.setName("Test Tenant");

        MvcResult result = sendPostRequest(TENANTS, tenantDTO, status().isOk());
        TenantDTO response = readMvcResultAsString(result, TenantDTO.class);

        assertNotNull(response.getId());
        assertEquals("Test Tenant", response.getName());
        assertNotNull(response.getCreatedAt());
        assertNotNull(response.getUpdatedAt());
    }

    @Test
    void getOne_shouldReturnTenant() {
        TenantDTO tenantDTO = new TenantDTO();
        tenantDTO.setName("Test Tenant");

        MvcResult createResult = sendPostRequest(TENANTS, tenantDTO, status().isOk());
        TenantDTO created = readMvcResultAsString(createResult, TenantDTO.class);

        MvcResult getResult = sendGetRequest(TENANTS + "/" + created.getId(), status().isOk());
        TenantDTO response = readMvcResultAsString(getResult, TenantDTO.class);

        assertEquals(created.getId(), response.getId());
        assertEquals("Test Tenant", response.getName());
    }

    @Test
    void getOne_shouldReturn404WhenNotFound() {
        sendGetRequest(TENANTS + "/nonexistent-id", status().is4xxClientError());
    }
}
