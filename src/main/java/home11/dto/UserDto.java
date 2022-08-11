package home11.dto;

import home11.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotNull(message = "Введите логин")
    @Length(min = 3, max = 255, message = "Длина логина должна составлять 3-255 символов")
    private String username;

    @NotNull(message = "Введите пароль")
    @Length(min = 3, max = 255, message = "Длина пароля должна составлять 3-255 символов")
    private String password;

    private String email;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }

//    public UserDto() {
//
//    }
}
