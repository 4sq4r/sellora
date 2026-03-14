package kz.sellora.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MembershipDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String companyId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String tenantId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CompanyDTO company;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private TenantDTO tenant;
}
