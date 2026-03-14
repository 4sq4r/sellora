package kz.sellora.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.sellora.core.model.enums.TenantStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class TenantDTO extends BaseDTO {

    private String name;

    private TenantStatus status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<MembershipDTO> memberships;
}
