package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}
