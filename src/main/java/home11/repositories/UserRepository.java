package home11.repositories;

import home11.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findAll();

    @Modifying
    @Query(value = "insert into users_roles (user_id, role_id) values(:user_id, :role_id)",
    nativeQuery = true)
    void insertQ(@Param("user_id") Long user_id, @Param("role_id") Long role_id);

    @Override
    <S extends User> S saveAndFlush(S entity);

    @Modifying
    @Query(value = "insert into users (username, password, email) values(:username, :password, :email)",
            nativeQuery = true)
    void insertU(@Param("username") String username, @Param("password") String password, @Param("email") String email);
}
