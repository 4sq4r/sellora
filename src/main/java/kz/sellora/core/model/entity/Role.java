package kz.sellora.core.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import kz.sellora.core.model.enums.RoleContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(
    name = "roles",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "context"})}
)
@EqualsAndHashCode(callSuper = true)
public class Role extends Base {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "context", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleContext context;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permissions",
        joinColumns = @JoinColumn(name = "role_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "permission_id", nullable = false))
    private Set<Permission> permissions = new HashSet<>();
}
