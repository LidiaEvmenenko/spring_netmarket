package home11.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthRequest {// присылает клиент
    private String username;
    private String password;

}
