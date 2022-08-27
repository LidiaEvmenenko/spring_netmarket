package home11.websocket;

import home11.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/web-socket")
public class GreetingController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final FileService fileService;

    @PutMapping
    public Greeting put(){
        Greeting greeting = new Greeting(fileService.openBook());
        return greeting;
    }
}
