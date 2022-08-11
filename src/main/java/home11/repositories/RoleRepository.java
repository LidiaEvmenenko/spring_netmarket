package home11.repositories;

import home11.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
//    @Query("select r from Role r where r.name = name")
//    Role findR(String name);
}
