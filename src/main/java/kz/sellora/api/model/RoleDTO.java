package kz.sellora.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.sellora.core.model.enums.RoleContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends BaseDTO {

    private String name;

    private RoleContext context;

    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<PermissionDTO> permissions;
}
