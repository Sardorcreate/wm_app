package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sardorcreate.dto.sep_monitor.SepMonitorCreateDto;
import sardorcreate.service.SepMonService;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sep_monitor")
public class SepMonController {

    private final SepMonService sepMonService;

    @PostMapping("/create")
    public ResponseEntity<?> createSepMonitor(@RequestBody SepMonitorCreateDto dto) {

        return sepMonService.createSepMon(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSepMonitor(@PathVariable long id) {

        return sepMonService.getSepMonByInventoryId(id);
    }
}
