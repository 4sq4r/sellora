package kz.sellora.controller;

import kz.sellora.api.model.PermissionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static kz.sellora.util.Endpoints.PERMISSIONS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PermissionControllerTest extends ControllerTest {

    @Test
    void saveOne_shouldReturnCreatedPermission() {
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setName("READ_USERS");
        permissionDTO.setDescription("Permission to read users");

        MvcResult result = sendPostRequest(PERMISSIONS, permissionDTO, status().isOk());
        PermissionDTO response = readMvcResultAsString(result, PermissionDTO.class);

        assertNotNull(response.getId());
        assertEquals("READ_USERS", response.getName());
        assertEquals("Permission to read users", response.getDescription());
    }

    @Test
    void getOne_shouldReturnPermission() {
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setName("WRITE_USERS");

        MvcResult createResult = sendPostRequest(PERMISSIONS, permissionDTO, status().isOk());
        PermissionDTO created = readMvcResultAsString(createResult, PermissionDTO.class);

        MvcResult getResult = sendGetRequest(PERMISSIONS + "/" + created.getId(), status().isOk());
        PermissionDTO response = readMvcResultAsString(getResult, PermissionDTO.class);

        assertEquals(created.getId(), response.getId());
        assertEquals("WRITE_USERS", response.getName());
    }

    @Test
    void getOne_shouldReturn404WhenNotFound() {
        sendGetRequest(PERMISSIONS + "/nonexistent-id", status().is4xxClientError());
    }
}
