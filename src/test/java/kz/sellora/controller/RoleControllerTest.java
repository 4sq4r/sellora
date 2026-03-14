package kz.sellora.controller;

import kz.sellora.api.model.RoleDTO;
import kz.sellora.core.model.enums.RoleContext;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static kz.sellora.util.Endpoints.ROLES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RoleControllerTest extends ControllerTest {

    @Test
    void saveOne_shouldReturnCreatedRole() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("ADMIN");
        roleDTO.setContext(RoleContext.SELLORA);
        roleDTO.setDescription("Admin role");

        MvcResult result = sendPostRequest(ROLES, roleDTO, status().isOk());
        RoleDTO response = readMvcResultAsString(result, RoleDTO.class);

        assertNotNull(response.getId());
        assertEquals("ADMIN", response.getName());
        assertEquals(RoleContext.SELLORA, response.getContext());
        assertEquals("Admin role", response.getDescription());
    }

    @Test
    void getOne_shouldReturnRole() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("USER");
        roleDTO.setContext(RoleContext.TENANT);

        MvcResult createResult = sendPostRequest(ROLES, roleDTO, status().isOk());
        RoleDTO created = readMvcResultAsString(createResult, RoleDTO.class);

        MvcResult getResult = sendGetRequest(ROLES + "/" + created.getId(), status().isOk());
        RoleDTO response = readMvcResultAsString(getResult, RoleDTO.class);

        assertEquals(created.getId(), response.getId());
        assertEquals("USER", response.getName());
    }

    @Test
    void getOne_shouldReturn404WhenNotFound() {
        sendGetRequest(ROLES + "/nonexistent-id", status().is4xxClientError());
    }
}
