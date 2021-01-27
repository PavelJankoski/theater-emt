package mk.ukim.finki.usermanagement.domain.repository;

import mk.ukim.finki.usermanagement.domain.enums.ERole;
import mk.ukim.finki.usermanagement.domain.model.Role;
import mk.ukim.finki.usermanagement.domain.model.RoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, RoleId> {
    Optional<Role> findByName(ERole name);
}
