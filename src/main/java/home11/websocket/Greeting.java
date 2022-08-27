package home11.websocket;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Greeting {
    private String[] contentURL;

    public Greeting(String[] openBook) {
        this.contentURL = openBook;
    }
}
