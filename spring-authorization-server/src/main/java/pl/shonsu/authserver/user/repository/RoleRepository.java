package pl.shonsu.authserver.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.shonsu.authserver.user.entity.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    Set<Role> findByUsersId(Long id);
}