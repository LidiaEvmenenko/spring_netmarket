package home11.controllers;

import home11.aspect.ControllerLogAspect;
import home11.model.MapDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/statistic")
@Slf4j
public class InfoController {
    private final ControllerLogAspect controllerLogAspect;

    public InfoController(ControllerLogAspect controllerLogAspect) {
        this.controllerLogAspect = controllerLogAspect;
    }

    @GetMapping
    public List<MapDto> printMap(){
        List<MapDto> list = new ArrayList<>();
        HashMap<String, Long> m = (HashMap<String, Long>) controllerLogAspect.getMapService();
        m.toString();
        for (Map.Entry<String, Long> e : m.entrySet()) {
            String key = e.getKey();
            Long value = e.getValue();
            MapDto dto = new MapDto();
            dto.setTitle(key);
            dto.setTimeService(value);
            list.add(dto);
        }
        return list;
    }
}
