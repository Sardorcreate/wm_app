package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sardorcreate.dto.scanner.ScannerCreateDto;
import sardorcreate.service.ScannerService;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scanner")
public class ScannerController {

    private final ScannerService scannerService;

    @PostMapping("/create")
    public ResponseEntity<?> createScanner(@RequestBody ScannerCreateDto dto) {

        return scannerService.createScanner(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getScannerByInventoryId(@PathVariable long id) {

        return scannerService.getScannerByInventoryId(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteScannerByInventoryId(@PathVariable long id) {

        return scannerService.deleteScannerByInventoryId(id);
    }
}
