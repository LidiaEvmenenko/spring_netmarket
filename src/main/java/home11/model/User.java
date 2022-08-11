package home11.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Введите логин")
    @Length(min = 3, max = 255, message = "Длина логина должна составлять 3-255 символов")
    @Column(name = "username")
    private String username;

    @NotNull(message = "Введите пароль")
    @Length(min = 3, max = 255, message = "Длина пароля должна составлять 3-255 символов")
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

//    public User() {
//    }


    //    public User(String username, String password, String email) {
//     //   this.id = id;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//    }

    //    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
}
