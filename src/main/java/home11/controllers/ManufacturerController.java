package home11.controllers;

import home11.entity.Manufacturer;
import home11.model.ManufacturerDto;
import home11.services.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/manufacturer")
@RequiredArgsConstructor

public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @PostMapping()
    public void createNewManufacturer(@RequestBody Manufacturer manufacturer) {
        manufacturerService.create(manufacturer.getTitle());
    }

    @GetMapping("/list")
    public List<ManufacturerDto> findAll(){
        List<ManufacturerDto> manufacturerDtos = new ArrayList<>();
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        for (Manufacturer c: manufacturers) {
            ManufacturerDto manufacturerDto = new ManufacturerDto(c);
            manufacturerDtos.add(manufacturerDto);
        }
        return manufacturerDtos;
    }
}
