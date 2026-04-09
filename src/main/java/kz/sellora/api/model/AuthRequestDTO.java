package kz.sellora.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRequestDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String deviceId;
}
