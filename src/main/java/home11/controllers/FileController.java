package home11.controllers;

import home11.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping(value = "/{fileName}", produces = "application/octet-stream")
    public byte[] getFile(@PathVariable String fileName){
        try {
            return fileService.getFileData(fileName);
        } catch (IOException e) {
            return new byte[] {};
        }
    }
    @GetMapping("/report")
    public String[] createReport(){
        return fileService.openBook();

    }
}
