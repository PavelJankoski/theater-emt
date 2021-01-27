package mk.ukim.finki.usermanagement.application.service;

import mk.ukim.finki.usermanagement.domain.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    public Role getRolesByName(Set<String> roles);
}
