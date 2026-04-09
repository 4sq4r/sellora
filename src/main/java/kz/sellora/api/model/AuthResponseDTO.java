package kz.sellora.api.model;

import lombok.Data;

@Data
public class AuthResponseDTO {

    private String token;
    private String refreshToken;
}
