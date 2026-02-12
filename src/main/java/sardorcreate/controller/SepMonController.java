package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sardorcreate.dto.FilterDto;
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSepMonByInventoryId(@PathVariable long id) {

        return sepMonService.deleteSepMonByInventoryId(id);
    }

    @PostMapping()
    public ResponseEntity<?> getSepMonByFilter(@RequestBody FilterDto dto) {

        return sepMonService.getSepMonByFilter(dto);
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<?> getSepMonById(@PathVariable long id) {

        return sepMonService.getSepMonById(id);
    }
}
