package home11.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthResponse {//отправляем обратно
    private String token;
    private String message;

    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }
}
