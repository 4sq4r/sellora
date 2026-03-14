package kz.sellora.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import kz.sellora.core.model.enums.CompanyStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyDTO extends BaseDTO {

    @NotNull
    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CompanyStatus status;
}
