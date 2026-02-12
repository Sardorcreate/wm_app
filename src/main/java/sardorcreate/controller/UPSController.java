package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sardorcreate.dto.FilterDto;
import sardorcreate.dto.ups.UPSCreateDto;
import sardorcreate.service.UPSService;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ups")
public class UPSController {

    private final UPSService upsService;

    @PostMapping("/create")
    public ResponseEntity<?> createUps(@RequestBody UPSCreateDto dto) {

        return upsService.createUps(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUpsByInventoryId(@PathVariable long id) {

        return upsService.getUpsByInventoryId(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUpsByInventoryId(@PathVariable long id) {

        return upsService.deleteUpsByInventoryId(id);
    }

    @PostMapping()
    public ResponseEntity<?> getUpsByFilter(@RequestBody FilterDto dto) {

        return upsService.getUpsByFilter(dto);
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<?> getUpsById(@PathVariable long id) {

        return upsService.getUpsById(id);
    }
}
