package kz.sellora.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionDTO extends BaseDTO {

    private String name;

    private String description;
}
